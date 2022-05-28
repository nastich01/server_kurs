<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Редактирование преподавателя</div>
		<div class="panel-body">
			<form @submit="handleSubmit">
				<div class="form-group">
					<label for="inputFIOteacher">ФИО</label>
					<input type="text" class="form-control" id="inputFIOteacher" v-model="fio_teacher">
				</div>
				<div class="form-group">
					<label for="inputDolzh">Должность</label>
					<select class="form-control" id="inputDolzh" v-model="id_dolzh">
						<option v-for="item in dolzh" v-bind:value="item.id">{{ item.dolzh_name }}</option>
					</select>
				</div>
				<div class="form-group">
					<label for="inputKafedra">Кафедра</label>
					<!--<select class="form-control" id="inputTeacher" v-model="id_teacher">
						<option v-for="item in teacher" v-bind:value="item.id">{{ item.fio }}</option>
					</select>-->
					<select class="form-control" id="inputKafedra" v-model="id_kafedra">
						<option v-for="item in kafedra" v-bind:value="item.id">{{ item.kafedra_name }}</option>
					</select>
					<!--<select class="form-control" id="inputTeacher" v-model="id_teacher">
						<option v-for="item in uclass" v-bind:value="item.id_teacher ">{{ item.teacherResourse.id }}</option>
					</select>-->
					<!--<select class="form-control" id="inputTeacher" v-model="post_body.id_teacher">
						<option v-for="item in teacher" v-bind:value="item.id " :key="item.id">{{ item.fio }}</option>
					</select>-->
					<!--<select class="form-control" id="inputTeacher" v-model="id_teacher">
						<option v-for="item in uclass" v-bind:value="teacher.getOne(item.id_teacher).fio">{{ teacher }}</option>
					</select>-->
					<!--<select class="form-control" id="inputTeacher" v-model="id_teacher">
						<option v-for="(teacher, key) in teachers" :value="key">{{ teacher }}</option>
					</select>-->
				</div>
				<button type="submit" class="btn btn-primary pull-right" id="sh">Сохранить</button>
			</form>
		</div>
	</div>
</template>

<script>
import {formsofcontrol, groups, terms} from "../../config";
import {disciplines} from "../../config";
import {teachers} from "../../config";
import { formatInputDate, formatInputTime } from "../../utils";
import {mapActions, mapState} from "vuex";

export default {
	data() {
		return {
			id: parseInt(this.$route.params.id),
			fio_teacher: "",
			id_dolzh: "",
			id_kafedra: ""
		};
	},
	created() {
		if (this.id !== 0 && this.teacher) {
			this.fio_teacher = this.teacher.fio_teacher;
			this.id_dolzh = this.teacher.id_dolzh;
			this.id_kafedra = this.teacher.id_kafedra;
		}
	},
	methods: {
		async handleSubmit(e) {
			e.preventDefault();
			const item = {
				fio_teacher: this.fio_teacher,
				id_dolzh: this.id_dolzh,
				id_kafedra: this.id_kafedra
			};
			if (this.id !== 0) {
				item.id = this.id;
				await this.editTeacher(item);
			} else {
				await this.addTeacher(item);
			}
			await this.$router.push({name: "teacherLoader"});
		},
		...mapActions([
			'addTeacher',
			'editTeacher'
		])
	},
	computed: {
		teacher() {
			return this.id !== 0 ?
				this.$store.state.teacher.find(item => item.id === this.id):
				null;
		},
		//teachers(){return teachers},
		...mapState({
			dolzh: state => state.dolzh,
			kafedra: state => state.kafedra
		})
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
