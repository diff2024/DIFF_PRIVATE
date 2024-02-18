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
	<!--<div id="app" v-if="$route.name !== 'main'">-->
	<div id="app">
		<v-app id="inspire">
			<v-app-bar color="#283345" dark class="fontDefault v-HeadClass" :clipped-left="$vuetify.breakpoint.lgAndUp" v-if="$route.name !== 'youtubeupbit_5_15' && $route.name !== 'youtubeupbit_60_240' && $route.name !== 'youtubebithumb_5_15' && $route.name !== 'youtubebithumb_60_240'">
				<v-toolbar-title v-on:click="goMainURL" class="fontDefault ml-2 pl-4" style="cursor:pointer;">
					<img src="./assets/logo.jpg" width="90px" style="vertical-align:middle;" />
				</v-toolbar-title>
				<v-spacer></v-spacer>
				<v-spacer></v-spacer>
					<span class="font-weight-light" style="cursor:pointer; font-size:14px;" @click="$router.push('/CoinMainList')">업비트 통계</span>
				<v-spacer></v-spacer>
					<span class="font-weight-light" style="cursor:pointer; font-size:14px;" @click="$router.push('/BithumbCoinMainList')">빗썸 통계</span>
				<v-spacer></v-spacer>
					<span class="font-weight-light" style="cursor:pointer; font-size:14px;" @click="$router.push('/BinanceCoinMainList')">바이낸스 통계</span>
				<v-spacer></v-spacer>
					<span class="font-weight-light" style="cursor:pointer; font-size:14px;" @click="$router.push('/BinanceFuturesCoinMainList')">바이낸스 선물 통계</span>
				<v-spacer></v-spacer>
					<span class="font-weight-light" style="cursor:pointer; font-size:14px;" @click="$router.push('/BybitCoinMainList')">바이비트 통계</span>
				<v-spacer></v-spacer>
					<span class="font-weight-light" style="cursor:pointer; font-size:14px;" @click="$router.push('/Report')">Report</span>
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
				<span style="font-size:20px;"></span>
			</footer>
		</v-app>
	</div>
	<!--
	<div v-else>
		<router-view></router-view>
	</div>
	-->
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
		},
		fnc_new_chang(location){
			window.open(location);
		}
	}
};
</script>
<style>
	@import './assets/Default.css';
</style>