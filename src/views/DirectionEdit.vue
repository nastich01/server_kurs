<template>
	<div class="panel panel-primary" style="margin-top: 10px">
		<div class="panel-heading" id="hd">Редактирование/создание направления</div>
		<div class="panel-body">
			<form @submit="handleSubmit">
				<div class="form-group">
					<label for="inputTitle">Направление</label>
					<input type="text" class="form-control" id="inputTitle" v-model="direction_name">
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
			direction_name: "",
		};
	},
	created() {
		if (this.id !== 0 && this.direction) {
			this.direction_name = this.direction.direction_name;
		}
	},
	methods: {
		async handleSubmit(e) {
			e.preventDefault();
			const item = {
				direction_name: this.direction_name,
			};
			if (this.id !== 0) {
				item.id = this.id;
				await this.editDirection(item);
			} else {
				await this.addDirection(item);
			}
			await this.$router.push({name: "directionLoader"});
		},
		...mapActions([
			'addDirection',
			'editDirection'
		])
	},
	computed: {
		direction() {
			return this.id !== 0 ?
				this.$store.state.direction.find(item => item.id === this.id):
				null;
		}
	}
}
</script>
