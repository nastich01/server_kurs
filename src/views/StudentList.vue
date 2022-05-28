<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Список студентов</div>
		<div class="panel-body">
			<router-link role="button" class="btn btn-lg btn-block btn-default" :to="{name: 'editStudent', params: {id: 0}}">
				Добавить
			</router-link>
			<a href="teacherLoader">
				<button class="btn btn-lg btn-block btn-default">Преподаватели</button>
			</a>
			<a href="activityLoader">
				<button class="btn btn-lg btn-block btn-default">Работы</button>
			</a>
		</div>

<!--		<div>{{student}}</div>-->

		<table class="table table-striped">
			<thead>
			<tr id="hdtb">
				<th style="width: 10%">№</th>
				<th style="width: 20%">ФИО</th>
				<th style="width: 10%">Курс</th>
				<th style="width: 10%">Группа</th>
				<th style="width: 40%">Факультет</th>
				<th ></th>
				<th ></th>
			</tr>
			</thead>
			<tbody>
			<!--<tr v-for="item in uclasses" :key=item.id>
				<td>{{item.id}}</td>
				<td>{{ item.discipline.name }}</td>
				<td>{{ item.teacher.fio }}</td>
				<td>{{ item.group.number }}</td>
				<td>{{ item.term }}</td>
				<td>{{ item.form_of_control }}</td>
				<td>{{ item.lecture_hours }}</td>
				<td>{{ item.practical_hours }}</td>
				<td>{{ item.laboratory_hours }}</td>
				<td><router-link role="button" class="btn btn-sm btn-link" :to="{name: 'editUclass', params: {id: item.id}}">
					<span class="glyphicon glyphicon-pencil"></span>
				</router-link></td>
				<td><button type="button" class="btn btn-sm btn-link" @click="handleRemoveClick($event, item.id)">
					<span class="glyphicon glyphicon-remove"></span>
				</button></td>
			</tr>-->
			<tr class="st" v-for="(item, index) in student" :key="index">
				<td>{{index + 1}}</td>
				<td>{{ item.fio_stud }}</td>
				<td>{{ item.kurs }}</td>
				<td>{{ item.group }}</td>
				<td>{{ item.facultyResource.faculty_name }}</td>

				<td><router-link role="button" class="btn btn-sm btn-link" :to="{name: 'editStudent', params: {id: item.id}}">
					<span class="glyphicon glyphicon-pencil"></span>
				</router-link></td>

				<td><button type="button" class="btn btn-sm btn-link" @click="handleRemoveClick($event, item.id)">
					<span class="glyphicon glyphicon-remove"></span>
				</button></td>
			</tr>
			</tbody>
		</table>
	</div>
</template>

<script>
import { formatDate } from "../../utils";
import {mapActions, mapState} from 'vuex';

export default {
	methods: {
		formatDate,
		async handleRemoveClick(e, id) {
			await this.removeStudent(id);
		},
		...mapActions([
			'removeStudent',
		])
	},
	computed: {
		...mapState({
			student: state => state.student,
			faculty: state => state.faculty
		})
	}
}
</script>
<style>
#hd,#hdtb{
	background: #C426E0;
	color: white;
}
.st{
	color: #434343;
}
</style>
