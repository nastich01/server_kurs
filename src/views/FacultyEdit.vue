<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Редактирование/создание факультета</div>
		<div class="panel-body">
			<form @submit="handleSubmit">
				<div class="form-group">
					<label for="inputTitle">Факультет</label>
					<input type="text" class="form-control" id="inputTitle" v-model="faculty_name">
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
			faculty_name: ""
		};
	},
	created() {
		if (this.id !== 0 && this.faculty) {
			this.faculty_name = this.faculty.faculty_name;
		}
	},
	methods: {
		async handleSubmit(e) {
			e.preventDefault();
			const item = {
				faculty_name: this.faculty_name
			};
			if (this.id !== 0) {
				item.id = this.id;
				await this.editFaculty(item);
			} else {
				await this.addFaculty(item);
			}
			await this.$router.push({name: "facultyLoader"});
		},
		...mapActions([
			'addFaculty',
			'editFaculty'
		])
	},
	computed: {
		faculty() {
			return this.id !== 0 ?
				this.$store.state.faculty.find(item => item.id === this.id):
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
