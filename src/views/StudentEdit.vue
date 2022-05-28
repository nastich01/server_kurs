<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Редактирование студента</div>
		<div class="panel-body">
			<form @submit="handleSubmit">
				<div class="form-group">
					<label for="inputFIOstud">ФИО</label>
					<input type="text" class="form-control" id="inputFIOstud" v-model="fio_stud">
				</div>
				<div class="form-group">
					<label for="inputKurs">Курс</label>
					<input type="text" class="form-control" id="inputKurs" v-model="kurs">
				</div>
				<div class="form-group">
					<label for="inputGroup">Группа</label>
					<input type="text" class="form-control" id="inputGroup" v-model="group">
				</div>
				<div class="form-group">
					<label for="inputFaculty">Факультет</label>
					<!--<select class="form-control" id="inputTeacher" v-model="id_teacher">
						<option v-for="item in teacher" v-bind:value="item.id">{{ item.fio }}</option>
					</select>-->
					<select class="form-control" id="inputFaculty" v-model="id_faculty">
						<option v-for="item in faculty" v-bind:value="item.id">{{ item.faculty_name }}</option>
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
			fio_stud: "",
			kurs: "",
			group: "",
			id_faculty: ""
		};
	},
	created() {
		if (this.id !== 0 && this.student) {
			this.fio_stud = this.student.fio_stud;
			this.kurs = this.student.kurs;
			this.group = this.student.group;
			this.id_faculty = this.student.id_faculty;
		}
	},
	methods: {
		async handleSubmit(e) {
			e.preventDefault();
			const item = {
				fio_stud: this.fio_stud,
				kurs: this.kurs,
				group: this.group,
				id_faculty: this.id_faculty,
			};
			if (this.id !== 0) {
				item.id = this.id;
				await this.editStudent(item);
			} else {
				await this.addStudent(item);
			}
			await this.$router.push({name: "studentLoader"});
		},
		...mapActions([
			'addStudent',
			'editStudent'
		])
	},
	computed: {
		student() {
			return this.id !== 0 ?
				this.$store.state.student.find(item => item.id === this.id):
				null;
		},
		//teachers(){return teachers},
		...mapState({
			/*student: state => state.student,*/
			faculty: state => state.faculty
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
