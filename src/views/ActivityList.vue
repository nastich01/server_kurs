<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Список работ</div>
		<div class="panel-body">
			<router-link role="button" class="btn btn-lg btn-block btn-default" :to="{name: 'editActivity', params: {id: 0}}">
				Добавить
			</router-link>
			<a href="eventLoader">
				<button class="btn btn-lg btn-block btn-default">Мероприятия</button>
			</a>
			<a href="tipLoader">
				<button class="btn btn-lg btn-block btn-default">Типы</button>
			</a>
			<a href="directionLoader">
				<button class="btn btn-lg btn-block btn-default">Направления</button>
			</a>
			<a href="studentLoader">
				<button class="btn btn-lg btn-block btn-default">Студенты</button>
			</a>
			<a href="teacherLoader">
				<button class="btn btn-lg btn-block btn-default">Преподаватели</button>
			</a>
			<a href="facultyLoader">
				<button class="btn btn-lg btn-block btn-default">Факультеты</button>
			</a>
			<a href="kafedraLoader">
				<button class="btn btn-lg btn-block btn-default">Кафедры</button>
			</a>
			<a href="dolzhLoader">
				<button class="btn btn-lg btn-block btn-default">Должности</button>
			</a>
		</div>
<!--		<div>{{activity}}</div>-->
<!--		<div>{{event}}</div>-->
<!--		<div>{{student}}</div>-->
		<table class="table table-striped">
			<thead>
			<tr id="hdtb">
				<th style="width: 7%">№</th>
				<th style="width: 15%">Мероприятие</th>
				<th style="width: 10%">Тип</th>
				<th style="width: 10%">Направление</th>
				<th style="width: 10%">Студент</th>
				<th style="width: 10%">Преподаватель</th>
				<th style="width: 10%">Название работы</th>
				<th style="width: 10%">Дата</th>
				<th style="width: 10%">Аннотация</th>
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
			<tr class="st" v-for="(item,id) in activity" :key=item.id>
				<td>{{id+1}}</td>
				<td>{{item.eventResource.event_name}}</td>
				<td>{{ item.tipResource.tip_name }}</td>
				<td>{{ item.directionResource.direction_name }}</td>
				<td>{{ item.studentResource.fio_stud }}</td>
				<td>{{ item.teacherResource.fio_teacher }}</td>
				<td>{{ item.work_name }}</td>
				<td>{{ item.event_date }}</td>
				<td>{{ item.annotation }}</td>
				<td><router-link role="button" class="btn btn-sm btn-link" :to="{name: 'editActivity', params: {id: item.id}}">
					<span class="glyphicon glyphicon-pencil"></span>
				</router-link></td>
				<td><button type="button" class="btn btn-sm btn-link" @click="handleRemoveClick($event, item.id)">
					<span class="glyphicon glyphicon-remove"></span>
				</button></td>
			</tr>
<!--			<tr v-for="(item, index) in activity" :key="index">
				<td>{{index + 1}}</td>
				<td><a :href="item.id_event" target="_blank">{{ item.eventResource.event_name }}</a></td>
				<td><a :href="item.id_tip" target="_blank">{{ item.tipResource.tip_name }}</a></td>
				<td><a :href="item.id_direction" target="_blank">{{ item.directionResource.direction_name }}</a></td>
				<td><a :href="item.id_student" target="_blank">{{ item.studentResource.fio_stud }}</a></td>
				<td><a :href="item.id_teacher" target="_blank">{{ item.teacherResource.fio_teacher }}</a></td>
				<td><a :href="item.work_name" target="_blank">{{ item.work_name }}</a></td>
				<td><a :href="item.annotation" target="_blank">{{ item.annotation }}</a></td>

				<td><router-link role="button" class="btn btn-sm btn-link" :to="{name: 'editActivity', params: {id: item.id}}">
					<span class="glyphicon glyphicon-pencil"></span>
				</router-link></td>

				<td><button type="button" class="btn btn-sm btn-link" @click="handleRemoveClick($event, item.id)">
					<span class="glyphicon glyphicon-remove"></span>
				</button></td>
			</tr>-->
			</tbody>
		</table>
	</div>
</template>

<script>
import {formatDate, wait} from "../../utils";
import {mapActions, mapState} from 'vuex';

export default {
	/*mounted() {
		this.$nextTick(async () => {
			await this.initializeActivity();
		});
	},*/
	methods: {
		formatDate,
		async handleRemoveClick(e, id) {
			await this.removeActivity(id);
		},
		...mapActions([
			'removeActivity',
			//'initializeActivity'
		])
	},
	computed: {
		...mapState({
			activity: state => state.activity,
			event: state => state.event,
			student: state=> state.student,
			teacher: state=> state.teacher
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
