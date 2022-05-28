<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Редактирование мероприятия</div>
		<div class="panel-body">
			<form @submit="handleSubmit">
				<div class="form-group">
					<label for="inputEventName">Название мероприятия</label>
					<input type="text" class="form-control" id="inputEventName" v-model="event_name">
				</div>
				<div class="form-group">
					<label for="inputPlace">Место</label>
					<input type="text" class="form-control" id="inputPlace" v-model="place">
				</div>
				<div class="form-group">
					<label for="inputDate">Дата</label>
					<input type="text" class="form-control" id="inputDate" v-model="date">
				</div>
				<div class="form-group">
					<label for="inputTime">Время</label>
					<input type="text" class="form-control" id="inputTime" v-model="time">
				</div>
				<div class="form-group">
					<label for="inputLink">Ссылка</label>
					<input type="text" class="form-control" id="inputLink" v-model="link">
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
			event_name: "",
			place: "",
			date: "",
			time: "",
			link: ""
		};
	},
	created() {
		if (this.id !== 0 && this.event) {
			this.event_name = this.event.event_name;
			this.place = this.event.place;
			this.date = this.event.date;
			this.time = this.event.time;
			this.link = this.event.link;
		}
	},
	methods: {
		async handleSubmit(e) {
			e.preventDefault();
			const item = {
				event_name: this.event_name,
				place: this.place,
				date: this.date,
				time: this.time,
				link: this.link
			};
			if (this.id !== 0) {
				item.id = this.id;
				await this.editEvent(item);
			} else {
				await this.addEvent(item);
			}
			await this.$router.push({name: "eventLoader"});
		},
		...mapActions([
			'editEvent',
			'addEvent'
		])
	},
	computed: {
		event() {
			return this.id !== 0 ?
				this.$store.state.event.find(item => item.id === this.id):
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
