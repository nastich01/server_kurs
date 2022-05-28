<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Список мероприятий</div>
		<div class="panel-body">
			<router-link role="button" class="btn btn-lg btn-block btn-default" :to="{name: 'editEvent', params: {id: 0}}">
				Добавить
			</router-link>
			<a href="activityLoader">
				<button class="btn btn-lg btn-block btn-default">Работы</button>
			</a>
		</div>
		<table class="table table-striped">
			<thead>
			<tr id="hdtb">
				<th style="width:20%">№</th>
				<th style="width: 20%">Название</th>
				<th style="width: 20%">Место</th>
				<th style="width: 10%">Дата</th>
				<th style="width: 10%">Время</th>
				<th style="width: 20%">Ссылка</th>
				<th></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
			<tr class="st" v-for="(item, index) in event" :key="index">
				<td >{{index + 1}}</td>
				<td>{{ item.event_name }}</td>
				<td>{{ item.place }}</td>
				<td>{{ item.date }}</td>
				<td>{{ item.time }}</td>
				<td>{{ item.link }}</td>

				<td><router-link role="button" class="btn btn-sm btn-link" :to="{name: 'editEvent', params: {id: item.id}}">
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
			await this.removeEvent(id);
		},
		...mapActions([
			'removeEvent',
		])
	},
	computed: {
		...mapState({
			event: state => state.event
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
