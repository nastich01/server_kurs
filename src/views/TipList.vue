<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Типы</div>
		<div class="panel-body">
			<router-link role="button" class="btn btn-lg btn-block btn-default" :to="{name: 'editTip', params: {id: 0}}">
				Добавить
			</router-link>
			<a href="eventLoader">
				<button class="btn btn-lg btn-block btn-default">Мероприятия</button>
			</a>
			<a href="directionLoader">
				<button class="btn btn-lg btn-block btn-default">Направления</button>
			</a>
			<a href="activityLoader">
				<button class="btn btn-lg btn-block btn-default">Работы</button>
			</a>
		</div>
		<table class="table table-striped">
			<thead>
			<tr id="hdtb">
				<th style="width:20%">№</th>
				<th style="width: 70%">Тип</th>
				<th ></th>
				<th ></th>
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
			<tr class="st" v-for="(item, id) in tip" :key="id">
				<td>{{id + 1}}</td>
<!--				<td>{{ item.id }}</td>-->
				<td>{{ item.tip_name }}</td>
				<td><router-link role="button" class="btn btn-sm btn-link" :to="{name: 'editTip', params: {id: item.id}}">
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
			await this.removeTip(id);
		},
		...mapActions([
			'removeTip',
			'initializeTip'
		])
	},
	computed: {
		...mapState({
			tip: state => state.tip
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
