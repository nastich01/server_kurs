<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Редактирование/создание типа работы</div>
		<div class="panel-body">
			<form @submit="handleSubmit">
				<div class="form-group">
					<label for="inputTitle">Тип</label>
					<input type="text" class="form-control" id="inputTitle" v-model="tip_name">
				</div>
				<button type="submit" class="btn btn-primary pull-right" id="sh">Сохранить</button>
			</form>
		</div>
	</div>
</template>

<script>
import { formatInputDate, formatInputTime } from "../../utils";
import {mapActions} from "vuex";

export default {
	data() {
		return {
			id: parseInt(this.$route.params.id),
			tip_name: "",
		};
	},
	created() {
		if (this.id !== 0 && this.tip) {
			this.tip_name = this.tip.tip_name;
		}
	},
	methods: {
		async handleSubmit(e) {
			e.preventDefault();
			const item = {
				tip_name: this.tip_name,
			};
			if (this.id !== 0) {
				item.id = this.id;
				await this.editTip(item);
			} else {
				await this.addTip(item);
			}
			await this.$router.push({name: "tipLoader"});
		},
		...mapActions([
			'addTip',
			'editTip'
		])
	},
	computed: {
		tip() {
			return this.id !== 0 ?
				this.$store.state.tip.find(item => item.id === this.id):
				null;
		}
	}
}
</script>
<style>
#hd{
	background: #C426E0;
}
#sh{
	background-color: #C426E0;
	border-radius: 8px;
}
</style>
