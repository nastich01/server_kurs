<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Редактирование/создание должности</div>
		<div class="panel-body">
			<form @submit="handleSubmit">
				<div class="form-group">
					<label for="inputTitle">Должность</label>
					<input type="text" class="form-control" id="inputTitle" v-model="dolzh_name">
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
			dolzh_name: "",
		};
	},
	created() {
		if (this.id !== 0 && this.dolzh) {
			this.dolzh_name = this.dolzh.dolzh_name;
		}
	},
	methods: {
		async handleSubmit(e) {
			e.preventDefault();
			const item = {
				dolzh_name: this.dolzh_name,
			};
			if (this.id !== 0) {
				item.id = this.id;
				await this.editDolzh(item);
			} else {
				await this.addDolzh(item);
			}
			await this.$router.push({name: "dolzhLoader"});
		},
		...mapActions([
			'addDolzh',
			'editDolzh'
		])
	},
	computed: {
		dolzh() {
			return this.id !== 0 ?
				this.$store.state.dolzh.find(item => item.id === this.id):
				null;
		}
	}
}
</script>
