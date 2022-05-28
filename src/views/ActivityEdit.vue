<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Редактирование работы</div>
		<div class="panel-body">
			<form @submit="handleSubmit">
<!--				<div class="form-group">
					<div>{{student}}</div>
					<div>{{teacher}}</div>
					<div>{{event}}</div>
					<label for="inputEvent">Мероприятие</label>
					<select class="form-control" id="inputEvent" v-model="id_event">
						<option v-for="item in event" v-bind:value="item.id_event">{{ item.event_name }}</option>
					</select>
				</div>-->
				<div class="form-group">
					<label for="inputEvent">Мероприятие</label>
					<select class="form-control" id="inputEvent" v-model="id_event">
						<option v-for="item in event" v-bind:value="item.id">{{ item.event_name}}</option>
					</select>
				</div>
				<div class="form-group">
					<label for="inputTip">Тип</label>
					<select class="form-control" id="inputTip" v-model="id_tip">
						<option v-for="item in tip" v-bind:value="item.id">{{ item.tip_name }}</option>
					</select>
				</div>
				<div class="form-group">
					<label for="inputDirection">Направление</label>
					<select class="form-control" id="inputDirection" v-model="id_direction">
						<option v-for="item in direction" v-bind:value="item.id">{{ item.direction_name }}</option>
					</select>
				</div>

				<div class="form-group">
					<label for="inputStudent">Студент</label>
					<select class="form-control" id="inputStudent" v-model="id_student">
						<option v-for="item in student" v-bind:value="item.id">{{ item.fio_stud }}</option>
					</select>
				</div>
				<div class="form-group">
					<label for="inputTeacher">Преподаватель</label>
					<select class="form-control" id="inputTeacher" v-model="id_teacher">
						<option v-for="item in teacher" v-bind:value="item.id">{{ item.fio_teacher }}</option>
					</select>
				</div>

<!--				<div class="form-group">
					<label for="inputEventName">Название мероприятия</label>
					<select class="form-control" id="inputEventName" v-model="event_name">
						<option v-for="item in event" v-bind:value="item.id">{{ item.event_name }}</option>
					</select>
				</div>-->

				<div class="form-group">
					<label for="inputDate">Дата</label>
					<input type="text" class="form-control" id="inputDate" v-model="event_date">
				</div>

				<div class="form-group">
					<label for="inputWorkName">Название работы</label>
					<input type="text" class="form-control" id="inputWorkName" v-model="work_name">
				</div>
				<div class="form-group">
					<label for="inputAnnotation">Аннотация</label>
					<input type="text" class="form-control" id="inputAnnotation" v-model="annotation">
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
			id_event: "",
			id_tip: "",
			id_direction: "",
			work_name: "",
			annotation: "",
			id_student: "",
			id_teacher: "",
			event_name: "",
			event_date: ""
		};
	},
	created() {
		if (this.id !== 0 && this.activity) {
			this.id_event = this.activity.id_event;
			this.id_tip = this.activity.id_tip;
			this.id_direction = this.activity.id_direction;
			this.work_name = this.activity.work_name;
			this.annotation = this.activity.annotation;
			this.id_student = this.activity.id_student;
			this.id_teacher = this.activity.id_teacher;
			this.event_name = this.activity.event_name;
			this.event_date = this.activity.event_date;
		}
	},
	methods: {
		async handleSubmit(e) {
			e.preventDefault();
			const item = {
				id_event: this.id_event,
				id_tip: this.id_tip,
				id_direction: this.id_direction,
				work_name: this.work_name,
				annotation: this.annotation,
				id_student: this.id_student,
				id_teacher: this.id_teacher,
				event_name: this.event_name,
				event_date: this.event_date
			};
			if (this.id !== 0) {
				item.id = this.id;
				await this.editActivity(item);
			} else {
				await this.addActivity(item);
			}
			await this.$router.push({name: "activityLoader"});
		},
		...mapActions([
			'editActivity',
			'addActivity'
		])
	},
	computed: {
		activity() {
			return this.id !== 0 ?
				this.$store.state.activity.find(item => item.id === this.id):
				null;
		},
		//teachers(){return teachers},

		...mapState({
			event: state => state.event,
			tip: state => state.tip,
			direction: state => state.direction,
			student: state => state.student,
			teacher: state => state.teacher
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
