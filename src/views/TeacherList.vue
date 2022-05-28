<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Список преподавателей</div>
		<div class="panel-body">
			<router-link role="button" class="btn btn-lg btn-block btn-default" :to="{name: 'editTeacher', params: {id: 0}}">
				Добавить
			</router-link>
			<a href="studentLoader">
				<button class="btn btn-lg btn-block btn-default">Студенты</button>
			</a>
			<a href="activityLoader">
				<button class="btn btn-lg btn-block btn-default">Работы</button>
			</a>
		</div>
		<table class="table table-striped">
			<thead>
			<tr id="hdtb">
				<th style="width: 10%">№</th>
				<th style="width: 20%">ФИО</th>
				<th style="width: 20%">Должность</th>
				<th style="width: 50%">Кафедра</th>
				<th style="width: 20px"></th>
				<th style="width: 20px"></th>
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
			<tr class="st" v-for="(item, index) in teacher" :key="index">
				<td class="st">{{index + 1}}</td>
				<td class="st">{{ item.fio_teacher }}</td>
				<td class="st">{{ item.dolzhResource.dolzh_name}}</td>
				<td class="st">{{ item.kafedraResource.kafedra_name }}</td>

				<td><router-link role="button" class="btn btn-sm btn-link" :to="{name: 'editTeacher', params: {id: item.id}}">
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
			await this.removeTeacher(id);
		},
		...mapActions([
			'removeTeacher',
		])
	},
	computed: {
		...mapState({
			teacher: state => state.teacher
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
