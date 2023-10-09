<style>
@keyframes app_bell-effect {
  50% {
    opacity: 0;
  }
}
.app_bell {
  animation: app_bell-effect 1s step-end infinite;
}
.modal_message_close{
	position: relative;
	left:82%;
	font-size:180%;
}
.v-application .pl-4 {
    padding-left:8px !important;
}
.v-application .ml-2 {
	margin-left: 0px!important; 
}
.v-HeadClass {
	z-index: 100;
}
</style>
<template>
	<div id="app" v-if="$route.name !== 'main'">
		<v-app id="inspire">
			<v-app-bar color="#283345" dark class="fontDefault v-HeadClass" :clipped-left="$vuetify.breakpoint.lgAndUp">
				<v-toolbar-title v-on:click="goMainURL" class="fontDefault ml-2 pl-4" style="cursor:pointer;">
					<img src="./assets/logo.jpg" width="90px" style="vertical-align:middle;" />
				</v-toolbar-title>
				<v-spacer></v-spacer>
				<span class="font-weight-light" style="cursor:pointer;" @click="$router.push('coinMainList')">업비트 코인 통계</span>
				<v-spacer></v-spacer>
				<span class="font-weight-light" style="cursor:pointer;" @click="$router.push('bithumbcoinMainList')">빗썸 코인 통계</span>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
			</v-app-bar>
			<v-main>
				<router-view></router-view>
			</v-main>
			<footer style="padding-bottom: 6px; padding-left:30px;">
				<span style="font-size:20px;">DIFF</span>
			</footer>
		</v-app>
	</div>
	<div v-else>
		<router-view></router-view>
	</div>
</template>
<style scss>
@import '~ag-grid-community/styles/ag-grid.css';
@import '~ag-grid-community/styles/ag-theme-alpine.css';
</style>
<script>
import axios from 'axios';
var menu_json = [];
export default {
	name: 'App',
	components: {
		
	},
	data() {
		return {

		}
	},
	beforeCreate: function () {
		
	},
	created() {
		
	},
	mounted() {	
		
	},
	methods: {
		goMainURL(){
			this.$router.push('/');
		}
	}
};
</script>
<style>
	@import './assets/Default.css';
</style>