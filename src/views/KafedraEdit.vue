<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Редактирование/создание кафедры</div>
		<div class="panel-body">
			<form @submit="handleSubmit">
				<div class="form-group">
					<label for="inputTitle">Кафедра</label>
					<input type="text" class="form-control" id="inputTitle" v-model="kafedra_name">
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
			kafedra_name: "",
		};
	},
	created() {
		if (this.id !== 0 && this.kafedra) {
			this.kafedra_name = this.kafedra.kafedra_name;
		}
	},
	methods: {
		async handleSubmit(e) {
			e.preventDefault();
			const item = {
				kafedra_name: this.kafedra_name,
			};
			if (this.id !== 0) {
				item.id = this.id;
				await this.editKafedra(item);
			} else {
				await this.addKafedra(item);
			}
			await this.$router.push({name: "kafedraLoader"});
		},
		...mapActions([
			'addKafedra',
			'editKafedra'
		])
	},
	computed: {
		kafedra() {
			return this.id !== 0 ?
				this.$store.state.kafedra.find(item => item.id === this.id):
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
