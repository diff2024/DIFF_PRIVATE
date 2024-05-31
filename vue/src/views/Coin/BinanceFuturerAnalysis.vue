<style>
.swal2-title{
	font-size:22px !important;
}
.ag-header-cell-label {
  justify-content: center;
}
</style>
<template>
	<v-layout wrap row>
		<v-flex xs12 sm12 md12 style="margin-bottom:20px;">
			<v-card xs12 sm12 md12>
				<v-card-title xs12 sm12 md12 class="fontBold" style="font-size:18px; font-weight:bold; padding-left: 60px; padding-top: 20px; padding-bottom: 10px;">
					바이낸스 선물 분석
				</v-card-title>
			</v-card>
		</v-flex>
		<v-flex xs12 sm12 md12>
			<v-card>
				<v-card-text>
					<template>
						<v-row>
							<v-col lg="6" md="6" sm="12" cols="12" style="padding: 20px 50px;">
								<ag-grid-vue 
									style="width: 100%; height: 500px;"
									class="ag-theme-alpine"
									:columnDefs="columnDefs"
									:rowData="rowData"
									:animateRows="true">
								</ag-grid-vue>
							</v-col>
							<v-col lg="6" md="6" sm="12" cols="12" style="padding: 20px 50px;">

							</v-col>
						</v-row>
					</template>
				</v-card-text>
			</v-card>
		</v-flex>
	</v-layout>
</template>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
import axios from 'axios';
import Swal from 'sweetalert2'
import {AgGridVue} from 'ag-grid-vue'
import router from '../../router'

export default {
	data () {
		return {
			gridOptions: null,
			columnDefs: null,
            rowData: []
		}
	},
	components: {
		AgGridVue
	},
	watch: {

	},
	beforeMount() {
       this.columnDefs = [
           {headerName: '코인', field:"id", width:100, ortable: false, filter: true, resizable:true},
           {headerName: '구분', field:"gubun", width:85, cellStyle: {textAlign: "center"}, sortable: false, filter: true, resizable:true},
		   {headerName: '시작일자 ~ 종료일자', field:"std_date_end_date", width:200, cellStyle: {textAlign: "center"}, sortable: true, filter: false, resizable:true}, 
		   {headerName: '연속일수', field:"straight_days", width:100, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true}, 
		   {headerName: '금일 변동율(%)', field:"today_rate", width:130, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true}, 
		   {headerName: '누적 변동율(%)', field:"accumulate_rate", width:130, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true},  
		   {headerName: '금일 누적 변동율(%)', field:"accumulate_today_rate", width:160, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true}
       ];
    },
	created () {
		/*
		let today = new Date();
		let year = today.getFullYear();
		let month = today.getMonth() + 1;
		let date = today.getDate();

		if(Number(month) < 10){
			month = '0'+month
		}
		if(Number(date) < 10){
			date = '0'+date
		}

		this.search_day = year+'-'+month+'-'+date

		this.days_items.push({
			yymmdd_text: this.search_day + ' [오늘]',
			yymmdd_val: this.search_day
		})

		for(var a=1; a<31; a++){
			var TMP_Date = new Date(today.setDate(today.getDate() - a));
			var TMP_day = TMP_Date.getFullYear() + '-' + ('0' + (TMP_Date.getMonth() + 1)).slice(-2)  + '-' + ('0' + TMP_Date.getDate()).slice(-2);
			this.days_items.push({
				yymmdd_text: TMP_day + ' ['+String(a)+'일 전]',
				yymmdd_val: TMP_day
			})
		}
		*/
	},
	mounted (){
		axios.get('/Main/BINANCE_FUTURES_DAY_ANALYSIS')
		.then(response => {
			for(var x=0; x<response.data.length; x++){
				this.rowData.push({
					id: response.data[x].id,
					gubun: response.data[x].gubun,
					std_date_end_date: response.data[x].std_date_end_date,
					straight_days: Number(response.data[x].straight_days),
					today_rate: Number(response.data[x].today_rate),
					accumulate_rate: Number(response.data[x].accumulate_rate),
					accumulate_today_rate: Number(response.data[x].accumulate_today_rate)
				})
			}
		});
	},
	methods: {
	}
}
window.currencyFormatter = function currencyFormatter(params) {
	if(!params.value && params.value != "0") {
		return '';
	}
	return (params.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
};

window.percentFormatter = function percentFormatter(params) {
	if(!params.value && params.value != "0") {
		return '';
	}
	return (params.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '%';
}
</script>
