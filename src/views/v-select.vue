<template>
	<div class="v-select">
		<p class="title" id="tit" @click="areOptionsVisible= !areOptionsVisible">{{selected}}</p>
		<div class="options" id="ttt" v-if="areOptionsVisible">
			<p
				v-for="option in options"
				:key="option.value"
				@click="selectOption(option)">
				{{option.name}}
			</p>
		</div>
	</div>
</template>

<script>
export default {
	name: "v-select",
	props:{
		options:{
			type:Array,
			default(){
				return []
			}
		},
		selected:{
			type:String,
			default:''
		}
	},
	data(){
		return{
			areOptionsVisible: false
		}
	},
	methods:{
		selectOption(option){
			this.$emit('select',option);
			this.areOptionsVisible=false;
		},
		hideSelect(){
			this.areOptionsVisible=false;
		}
	},
	mounted(){
		document.addEventListener('click', this.hideSelect.bind(this), true)
	},
	beforeDestroy() {
		document.removeEventListener('click', this.hideSelect)
	},
	computed:{}
}
</script>

<style>
	.v-select{
		position: relative;
		width: 150px;
		cursor: pointer;
	}
	.v-select p{
		margin:0;
	}
	.title{
		border:solid 1px #aeaeae;
		border-radius: 8px;
	}
	.title p{
		margin: 6px;
	}
	#tit{
		padding: 6px;
	}

	.options{
		/*background: #E0D542;*/
		background: white;
		border: solid 1px #aeaeae;
		position: absolute;
		top:30px;
		right: 0;
		width: 100%;
	}
	.options p{
		margin: 6px;
	}
	.options p:hover{
		background: #E0D542;
	}
</style>
