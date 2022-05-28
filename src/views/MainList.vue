<template>
	<!--<a href="#openModal"><button class="btn"> Вход для модератора</button></a>-->
	<div>
		<div>
			<div id="enterDiv">
				<!--<a href="#close" title="Закрыть" class="close">X</a>-->
				<h7 id="h7">Вход модератора</h7>
				<p class="warn">{{text}}</p>
				<p class="inputLable"><label for="inputTitle">Введите логин</label></p>
				<p><input type="text" class="r"  id="inputTitle"  v-model="user_login"></p>
				<p class="inputLable"><label for="inputPassword">Введите пароль</label></p>
				<p><input type="password" class="r"  id="inputPassword"  v-model="user_password" ></p>
				<a href="#close"> <button class="btn" id="enter" @click="handleClickEnter($event),user_password=null"  > Войти </button></a>
			</div>

			<div id="buttonsDiv">
				<button id="show-modalTip" @click="showModal = true">Показать типы работ</button>
				<Teleport to="body">
					<!-- use the modal component, pass in the prop -->
					<modal :show="showModal" @close="showModal = false">
						<template #header>
							<h3 id="h3Tip">Типы работ</h3>
							<form id="searchTip">
								<p id="searchTipText">Поиск:</p>
								<input id="searchTipQuery" name="query" v-model="searchTipQuery" placeholder="введите тип">
							</form>
							<TipGrid
								:data=impTip
								:columns="gridTipColumns"
								:filter-key="searchTipQuery">
							</TipGrid>
						</template>
					</modal>
				</Teleport>


				<button id="show-modalDir" @click="showModal1 = true">Показать направления работ</button>
				<Teleport to="body">
					<!-- use the modal component, pass in the prop -->
					<modal :show="showModal1" @close="showModal1 = false">
						<template #header>
							<h3 id="h3Dir">Направления работ</h3>
							<form id="searchDir">
								<p id="searchDirText">Поиск:</p>
								<input id="searchDirQuery" name="query" v-model="searchDirQuery" placeholder="введите направление">
							</form>
							<DirGrid
								:data=impDir
								:columns="gridDirColumns"
								:filter-key="searchDirQuery">
							</DirGrid>
						</template>
					</modal>
				</Teleport>

				<button id="show-modalEv" @click="showModal2 = true">Показать мероприятия</button>
				<Teleport to="body">
					<!-- use the modal component, pass in the prop -->
					<modal :show="showModal2" @close="showModal2 = false">
						<template #header>
							<h3 id="h3Ev">Мероприятия</h3>
							<form id="searchEv">
								<p id="searchEvText">Поиск:</p>
								<input id="searchEvQuery" name="query" v-model="searchEvQuery" placeholder="введите мероприятие/место/дату">
							</form>
							<DirGrid
								:data=impEv
								:columns="gridEvColumns"
								:filter-key="searchEvQuery">
							</DirGrid>
						</template>
					</modal>
				</Teleport>

				<button id="show-modalStud" @click="showModal2 = true">Показать студентов</button>
				<button id="show-modalTeach" @click="showModal2 = true">Показать преподавателей</button>

			</div>
		</div>

		<div id="mainDiv">
			<h2 id="h2Main">Научный профиль кафедры</h2>
			<h5 id="h5Main">Список научных работ</h5>
			<form id="search">
				<p id="searchText">Поиск:</p>
				<input id="searchQuery" name="query" v-model="searchQuery" placeholder="введите мероприятие / тип / направление / студента / преподавателя / название работы">
			</form>
			<v-select
				:selected="selected"
				:options=impCatDir
				@select="sortByCategories"
			/>
<!--	без фильтра		<DemoGrid
				:data="gridData"
				:columns="gridColumns"
				:filter-key="searchQuery">
			</DemoGrid>-->
<!--	с фильтром		<DemoGrid
				:data="filteredActivities"
				:columns="gridColumns"
				:filter-key="searchQuery">
			</DemoGrid>-->

<!--{{activity}}-->
<!--			{{filteredActivities}}-->
			<TestGrid
				:data="filteredActivities"
				:columns="gridColumns"
				:filter-key="searchQuery">
			</TestGrid>
<!--			{{impAct}}-->


<!--<table id="actTable">
				<thead>
				<tr>
					<th style="width: 65%">Мероприятие</th>
					<th style="width: 10%">Тип</th>
					<th style="width: 65%">Направление</th>
					<th style="width: 10%">Студент</th>
					<th style="width: 65%">Преподаватель</th>
					<th style="width: 10%">Название работы</th>
					<th style="width: 65%">Аннотация</th>
					<th style="width: 20px"></th>
					<th style="width: 20px"></th>
				</tr>
				</thead>
				<tbody>
				<tr v-for="item in activity" :key=item.id>
					<td>{{item.event_name}}</td>
					<td>{{ item.tipResource.tip_name }}</td>
					<td>{{ item.directionResource.direction_name }}</td>
					<td>{{ item.studentResource.fio_stud }}</td>
					<td>{{ item.teacherResource.fio_teacher }}</td>
					<td>{{ item.work_name }}</td>
					<td>{{ item.event_date }}</td>
					<td>{{ item.annotation }}</td>
				</tr>
				</tbody>
			</table>-->
		</div>


	</div>
</template>
<script>
import DemoGrid from './MainGrid.vue'
import TipGrid from './MainGrid.vue'
import DirGrid from './MainGrid.vue'
import Modal from './Modal.vue'
import VSelect from "./v-select";
import TestGrid from './MainGrid.vue'
import {mapActions, mapGetters, mapState} from "vuex";
import {wait} from "../utils";
export default {
	//name: "MainList",
	components:{
		VSelect,
		DemoGrid,
		Modal,
		TipGrid,
		DirGrid,
		TestGrid
	},
	data(){
		return{
			showModal: false,
			showModal1: false,
			showModal2: false,

			user_password: '',
			user_login: '',
			text:'',
			searchQuery: '',
			gridColumns: ['Мероприятие', 'Тип', 'Направление','Название','Студент', 'Преподаватель','Дата','Аннотация'],
			/*gridData: [
				{ Мероприятие: 'Научная конференция', Тип: 'Курсовая работа', Направление:'android-разработка', Название:'Разработка приложения Example', Студент:'Петров П.П', Преподаватель:'Васильев.В.В',Дата:'12.03.2019', Аннотация:'Заняла первое место на конференции' },
				{ Мероприятие: 'Научная конференция', Тип: 'Дипломная работа', Направление:'машинное обучение', Название:'Машинное обучение в задачах автоматической обработки текстов', Студент:'Сашин П.П', Преподаватель:'Васильев.В.В',Дата:'12.03.2020', Аннотация:'Защищена на 5' }
			],*/

			searchTipQuery: '',
			gridTipColumns: ['Тип'],
			/*gridTipData: [
				{ Тип: 'Курсовая работа'},
				{ Тип: 'Дипломная работа'}
			],*/

			searchDirQuery: '',
			gridDirColumns: ['Направление'],
			/*gridDirData: [
				{ Направление: 'android-разработка'},
				{ Направление: 'машинное обучение'}
			],*/

			searchEvQuery: '',
			gridEvColumns: ['Мероприятие','Место','Дата','Время','Ссылка'],

			/*categories:[
				{name:'Все направления',value:'all'},
				{name:'android-разработка',value:'ar'},
				{name:'машинное обучение',value:'mo'},
				{name:'информационная безопасность',value:'ib'}
			],*/
			selected:'Все направления',
			sortedActivities:[],
			gridTestData:[]

			/*////!!!!!!!
			searchTestQuery: '',
			gridTestColumns: ['Мероприятие', 'Тип', 'Направление','Название','Студент', 'Преподаватель','Дата','Аннотация'],
			gridTestData: [/!*{ Мероприятие: 'Научная конференция', Тип: 'Курсовая работа', Направление:'android-разработка', Название:'Разработка приложения Example',
				Студент:'Петров П.П', Преподаватель:'Васильев.В.В',Дата:'12.03.2019', Аннотация:'Заняла первое место на конференции' },*!/
			]*/
		}
	},
	mounted() {
		this.$nextTick(async () => {
			await this.initializeActivity();
		});
	},
	computed:{
		filteredActivities(){
			if(this.sortedActivities.length){
				return this.sortedActivities
			} else {
				return this.impAct
			}
		},
		...mapState({
			activity: state => state.activity,
			event: state => state.event,
			student: state=> state.student,
			teacher: state=> state.teacher,
		}),

		...mapGetters({
			impAct: "impAct",
			impCatDir:"impCatDir",
			impDir:"impDir",
			impTip:"impTip",
			impEv:"impEv"
		})

	},
	methods: {

		handleClickEnter (event ,user_login, user_password) {
			if ((this.user_password == 'abc')&&(this.user_login == 'moderator')) {
				this.text = ""
				//this.text = "Пароль верный"
				this.$router.push({name: "activityLoader"})
			} else {
				this.text = "Неверный логин/пароль"
			}
		},
		sortByCategories(category){
			this.gridTestData=this.impAct
			this.sortedActivities = [];
			let vm = this
			this.gridTestData.map(function (item){
				if (item.Направление===category.name){
					vm.sortedActivities.push(item);
				}
			})
			this.selected=category.name;
		},
		...mapActions([
			'initializeActivity'
		])
	}

}

</script>

<style>
#h7{
	color:white;
	font-weight: bold;
}
.inputLable{
	color:white;
}
#enterDiv {
	background: #C426E0;
	position: absolute;
	border: 4px solid #C426E0;
	width: max-content;
	height: 350px;
	padding: 15px;
	border-radius: 8px;
	top: 10px; /* Расстояние от верхнего края */
	left: 10px; /* Расстояние от левого края */
}
.warn{
	color: red;
	border: 2px solid black;
	border-radius: 8px;
	background: white;
	width: max-content;
	padding: 2px;
}
#inputTitle, #inputPassword{
	border: 2px solid black;
	border-radius: 8px;
	padding: 4px;
}
#enter,#show-modalTip,#show-modalDir,#show-modalEv, #show-modalStud, #show-modalTeach{
	border-radius: 8px;
	color:#C426E0;
	background-color: white;
	border: 2px solid black;
	margin: 8px;
}
#enter:hover,#show-modalTip:hover ,#show-modalDir:hover ,#show-modalEv:hover, #show-modalStud:hover, #show-modalTeach:hover{
	background-color: #E0D542;
	color: white;
}
#show-modalTip,#show-modalDir,#show-modalEv, #show-modalStud, #show-modalTeach{
	padding: 4px;
}
#search, #searchTip, #searchDir, #searchEv{
	margin-left:3px;
	margin-bottom:10px;
	margin-top: 10px;
}
#searchQuery, #searchTipQuery, #searchDirQuery,#searchEvQuery{
	border: 1px solid #aeaeae;
	border-radius: 8px;
	padding: 4px;
	width: 570px;
}

#searchTipQuery, #searchDirQuery{
	width: 200px;
}
#buttonsDiv{
	position: absolute;
	top: 380px; /* Расстояние от верхнего края */
	left: 10px;
	display: flex;
	flex-direction: column;
}
#h3Tip,#h3Dir,#h3Ev{
	color:black;
	font-weight: bold;
	font-size: medium;
}

</style>
