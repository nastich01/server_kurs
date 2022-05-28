<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Направления</div>
		<div class="panel-body">
			<router-link role="button" class="btn btn-lg btn-block btn-default" :to="{name: 'editDirection', params: {id: 0}}">
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
				<th style="width: 70%">Направление</th>
				<th></th>
				<th></th>
			</tr>
			</thead>
			<!--<tbody>
			<tr v-for="item in discipline" :key=item.id>
				<td>{{item.id}}</td>
				<td>{{ item.name }}</td>
				<td><router-link role="button" class="btn btn-sm btn-link" :to="{name: 'editDiscipline', params: {id: item.id}}">
					<span class="glyphicon glyphicon-pencil"></span>
				</router-link></td>
				<td><button type="button" class="btn btn-sm btn-link" @click="handleRemoveClick($event, item.id)">
					<span class="glyphicon glyphicon-remove"></span>
				</button></td>
			</tr>
			</tbody>-->
			<tbody>
			<tr class="st" v-for="(item, id) in direction" :key="id">
				<td>{{id + 1}}</td>
				<td>{{ item.direction_name }}</td>
				<td><router-link role="button" class="btn btn-sm btn-link" :to="{name: 'editDirection', params: {id: item.id}}">
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
import { disciplines } from "../../config";
import {formatDate, wait} from "../../utils";
import {mapActions, mapState} from 'vuex';
export default {
	methods: {
		formatDate,
		async handleRemoveClick(e, id) {
			await this.removeDirection(id);
		},
		...mapActions([
			'removeDirection',
			'initializeDirection'
		])
	},
	computed: {
		directions: () => directions,
		...mapState({
			direction: state => state.direction
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
