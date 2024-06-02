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
							<v-col lg="3" md="3" sm="12" cols="12" style="padding: 10px 50px;">
								<ag-grid-vue 
									style="width: 100%; height: 600px;"
									class="ag-theme-alpine"
									:columnDefs="columnDefs"
									:rowData="rowData"
									:gridOptions="gridOptions"
									:animateRows="true">
								</ag-grid-vue>
							</v-col>
							<v-col lg="9" md="9" sm="12" cols="12" style="padding: 10px 10px;">
								<div style="text-align:left; padding:0px;" id="days_translation_rate"></div>

								<div style="text-align:left; padding:0px;" id="days_accumulate_rate"></div>
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

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback();

export default {
	data () {
		return {
			gridOptions: null,
			columnDefs: null,
            rowData: [],
			today: '',
			days_1: '',
			days_2: '',
			days_3: '',
			days_4: '',
			days_5: '',
			days_6: '',
			days_7: '',
			days_8: '',
			days_9: ''
		}
	},
	components: {
		AgGridVue
	},
	watch: {

	},
	beforeMount() {
       this.columnDefs = [
           {headerName: '코인', field:"id", width:100, sortable: false, filter: true, resizable:true},
           {headerName: '구분', field:"gubun", width:85, cellStyle: {textAlign: "center"}, sortable: false, filter: true, resizable:true},
		   {headerName: '시작일자 ~ 종료일자', field:"std_date_end_date", hide: "true", width:200, cellStyle: {textAlign: "center"}, sortable: true, filter: false, resizable:true}, 
		   {headerName: '연속일수', field:"straight_days", hide: "true", width:100, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true}, 
		   {headerName: '금일 변동율(%)', field:"today_rate", hide: "true", width:130, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true}, 
		   {headerName: '누적 변동율(%)', field:"accumulate_rate", width:110, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true},  
		   {headerName: '금일 누적 변동율(%)', field:"accumulate_today_rate", hide: "true", width:160, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true}
       ];

	   	this.gridOptions = {
			onRowClicked : function (event){
				if (this.today != ''){
					let today = new Date();
					let year = today.getFullYear();
					let month = today.getMonth() + 1;
					let date = today.getDate();

					month = ('0' + month).slice(-2);
					date = ('0' + date).slice(-2);
					this.today = year+'-'+month+'-'+date;
					
					let d = new Date();
					d.setDate(d.getDate()-1);
					let TMPyear    = d.getFullYear();
					let TMPmonth   = ('0' + (d.getMonth()+1)).slice(-2);
					let TMPday     = ('0' + d.getDate()).slice(-2);
					this.days_1 = TMPyear+"-"+TMPmonth+"-"+TMPday;

					d = new Date();
					d.setDate(d.getDate()-2);
					TMPyear    = d.getFullYear();
					TMPmonth   = ('0' + (d.getMonth()+1)).slice(-2);
					TMPday     = ('0' + d.getDate()).slice(-2);
					this.days_2 = TMPyear+"-"+TMPmonth+"-"+TMPday;

					d = new Date();
					d.setDate(d.getDate()-3);
					TMPyear    = d.getFullYear();
					TMPmonth   = ('0' + (d.getMonth()+1)).slice(-2);
					TMPday     = ('0' + d.getDate()).slice(-2);
					this.days_3 = TMPyear+"-"+TMPmonth+"-"+TMPday;

					d = new Date();
					d.setDate(d.getDate()-4);
					TMPyear    = d.getFullYear();
					TMPmonth   = ('0' + (d.getMonth()+1)).slice(-2);
					TMPday     = ('0' + d.getDate()).slice(-2);
					this.days_4 = TMPyear+"-"+TMPmonth+"-"+TMPday;

					d = new Date();
					d.setDate(d.getDate()-5);
					TMPyear    = d.getFullYear();
					TMPmonth   = ('0' + (d.getMonth()+1)).slice(-2);
					TMPday     = ('0' + d.getDate()).slice(-2);
					this.days_5 = TMPyear+"-"+TMPmonth+"-"+TMPday;

					d = new Date();
					d.setDate(d.getDate()-6);
					TMPyear    = d.getFullYear();
					TMPmonth   = ('0' + (d.getMonth()+1)).slice(-2);
					TMPday     = ('0' + d.getDate()).slice(-2);
					this.days_6 = TMPyear+"-"+TMPmonth+"-"+TMPday;

					d = new Date();
					d.setDate(d.getDate()-7);
					TMPyear    = d.getFullYear();
					TMPmonth   = ('0' + (d.getMonth()+1)).slice(-2);
					TMPday     = ('0' + d.getDate()).slice(-2);
					this.days_7 = TMPyear+"-"+TMPmonth+"-"+TMPday;

					d = new Date();
					d.setDate(d.getDate()-8);
					TMPyear    = d.getFullYear();
					TMPmonth   = ('0' + (d.getMonth()+1)).slice(-2);
					TMPday     = ('0' + d.getDate()).slice(-2);
					this.days_8 = TMPyear+"-"+TMPmonth+"-"+TMPday;

					d = new Date();
					d.setDate(d.getDate()-9);
					TMPyear    = d.getFullYear();
					TMPmonth   = ('0' + (d.getMonth()+1)).slice(-2);
					TMPday     = ('0' + d.getDate()).slice(-2);
					this.days_9 = TMPyear+"-"+TMPmonth+"-"+TMPday;
				}
				
				var coinID = event.node.data.id
				axios.get('/Main/BINANCE_FUTURES_DAY_ANALYSIS_DETAILS',{
					params: {
						coin_ticker: coinID
					}
				})
				.then(response => {
					var today_translation_rate = response.data.today_translation_rate
					var today_translation_rate_color = '';
					if(Number(today_translation_rate) > 0){
						today_translation_rate_color = "red";
					}else if(Number(today_translation_rate) < 0){
						today_translation_rate_color = "blue";
					}else{
						today_translation_rate_color = "black";
					}

					var days_1_translation_rate = response.data.days_1_translation_rate
					var days_1_translation_rate_color = ''
					if(Number(days_1_translation_rate) > 0){
						days_1_translation_rate_color = "red";
					}else if(Number(days_1_translation_rate) < 0){
						days_1_translation_rate_color = "blue";
					}else{
						days_1_translation_rate_color = "black";
					}

					var days_2_translation_rate = response.data.days_2_translation_rate
					var days_2_translation_rate_color = ''
					if(Number(days_2_translation_rate) > 0){
						days_2_translation_rate_color = "red";
					}else if(Number(days_2_translation_rate) < 0){
						days_2_translation_rate_color = "blue";
					}else{
						days_2_translation_rate_color = "black";
					}

					var days_3_translation_rate = response.data.days_3_translation_rate
					var days_3_translation_rate_color = ''
					if(Number(days_3_translation_rate) > 0){
						days_3_translation_rate_color = "red";
					}else if(Number(days_3_translation_rate) < 0){
						days_3_translation_rate_color = "blue";
					}else{
						days_3_translation_rate_color = "black";
					}

					var days_4_translation_rate = response.data.days_4_translation_rate
					var days_4_translation_rate_color = ''
					if(Number(days_4_translation_rate) > 0){
						days_4_translation_rate_color = "red";
					}else if(Number(days_4_translation_rate) < 0){
						days_4_translation_rate_color = "blue";
					}else{
						days_4_translation_rate_color = "black";
					}

					var days_5_translation_rate = response.data.days_5_translation_rate
					var days_5_translation_rate_color = ''
					if(Number(days_5_translation_rate) > 0){
						days_5_translation_rate_color = "red";
					}else if(Number(days_5_translation_rate) < 0){
						days_5_translation_rate_color = "blue";
					}else{
						days_5_translation_rate_color = "black";
					}

					var days_6_translation_rate = response.data.days_6_translation_rate
					var days_6_translation_rate_color = ''
					if(Number(days_6_translation_rate) > 0){
						days_6_translation_rate_color = "red";
					}else if(Number(days_6_translation_rate) < 0){
						days_6_translation_rate_color = "blue";
					}else{
						days_6_translation_rate_color = "black";
					}

					var days_6_translation_rate = response.data.days_6_translation_rate
					var days_6_translation_rate_color = ''
					if(Number(days_6_translation_rate) > 0){
						days_6_translation_rate_color = "red";
					}else if(Number(days_6_translation_rate) < 0){
						days_6_translation_rate_color = "blue";
					}else{
						days_6_translation_rate_color = "black";
					}

					var days_7_translation_rate = response.data.days_7_translation_rate
					var days_7_translation_rate_color = ''
					if(Number(days_7_translation_rate) > 0){
						days_7_translation_rate_color = "red";
					}else if(Number(days_7_translation_rate) < 0){
						days_7_translation_rate_color = "blue";
					}else{
						days_7_translation_rate_color = "black";
					}

					var days_8_translation_rate = response.data.days_8_translation_rate
					var days_8_translation_rate_color = ''
					if(Number(days_8_translation_rate) > 0){
						days_8_translation_rate_color = "red";
					}else if(Number(days_8_translation_rate) < 0){
						days_8_translation_rate_color = "blue";
					}else{
						days_8_translation_rate_color = "black";
					}

					var days_9_translation_rate = response.data.days_9_translation_rate
					var days_9_translation_rate_color = ''
					if(Number(days_9_translation_rate) > 0){
						days_9_translation_rate_color = "red";
					}else if(Number(days_9_translation_rate) < 0){
						days_9_translation_rate_color = "blue";
					}else{
						days_9_translation_rate_color = "black";
					}

					var days_1_accumulate_rate = response.data.days_1_accumulate_rate
					var days_1_accumulate_rate_color = ''
					if(Number(days_1_accumulate_rate) > 0){
						days_1_accumulate_rate_color = "red";
					}else if(Number(days_1_accumulate_rate) < 0){
						days_1_accumulate_rate_color = "blue";
					}else{
						days_1_accumulate_rate_color = "black";
					}

					var days_2_accumulate_rate = response.data.days_2_accumulate_rate
					var days_2_accumulate_rate_color = ''
					if(Number(days_2_accumulate_rate) > 0){
						days_2_accumulate_rate_color = "red";
					}else if(Number(days_2_accumulate_rate) < 0){
						days_2_accumulate_rate_color = "blue";
					}else{
						days_2_accumulate_rate_color = "black";
					}

					var days_3_accumulate_rate = response.data.days_3_accumulate_rate
					var days_3_accumulate_rate_color = ''
					if(Number(days_3_accumulate_rate) > 0){
						days_3_accumulate_rate_color = "red";
					}else if(Number(days_3_accumulate_rate) < 0){
						days_3_accumulate_rate_color = "blue";
					}else{
						days_3_accumulate_rate_color = "black";
					}

					var days_4_accumulate_rate = response.data.days_4_accumulate_rate
					var days_4_accumulate_rate_color = ''
					if(Number(days_4_accumulate_rate) > 0){
						days_4_accumulate_rate_color = "red";
					}else if(Number(days_4_accumulate_rate) < 0){
						days_4_accumulate_rate_color = "blue";
					}else{
						days_4_accumulate_rate_color = "black";
					}

					var days_5_accumulate_rate = response.data.days_5_accumulate_rate
					var days_5_accumulate_rate_color = ''
					if(Number(days_5_accumulate_rate) > 0){
						days_5_accumulate_rate_color = "red";
					}else if(Number(days_5_accumulate_rate) < 0){
						days_5_accumulate_rate_color = "blue";
					}else{
						days_5_accumulate_rate_color = "black";
					}

					var days_6_accumulate_rate = response.data.days_6_accumulate_rate
					var days_6_accumulate_rate_color = ''
					if(Number(days_6_accumulate_rate) > 0){
						days_6_accumulate_rate_color = "red";
					}else if(Number(days_6_accumulate_rate) < 0){
						days_6_accumulate_rate_color = "blue";
					}else{
						days_6_accumulate_rate_color = "black";
					}

					var days_7_accumulate_rate = response.data.days_7_accumulate_rate
					var days_7_accumulate_rate_color = ''
					if(Number(days_7_accumulate_rate) > 0){
						days_7_accumulate_rate_color = "red";
					}else if(Number(days_7_accumulate_rate) < 0){
						days_7_accumulate_rate_color = "blue";
					}else{
						days_7_accumulate_rate_color = "black";
					}

					var days_8_accumulate_rate = response.data.days_8_accumulate_rate
					var days_8_accumulate_rate_color = ''
					if(Number(days_8_accumulate_rate) > 0){
						days_8_accumulate_rate_color = "red";
					}else if(Number(days_8_accumulate_rate) < 0){
						days_8_accumulate_rate_color = "blue";
					}else{
						days_8_accumulate_rate_color = "black";
					}

					var days_9_accumulate_rate = response.data.days_9_accumulate_rate
					var days_9_accumulate_rate_color = ''
					if(Number(days_9_accumulate_rate) > 0){
						days_9_accumulate_rate_color = "red";
					}else if(Number(days_9_accumulate_rate) < 0){
						days_9_accumulate_rate_color = "blue";
					}else{
						days_9_accumulate_rate_color = "black";
					}

					var dataArray = [];
					dataArray.push(['X', 'Percent', { role: 'style' }, { role: 'annotation' } ]);
					dataArray.push(["9일 전\n"+this.days_9, Number(days_9_translation_rate), String(days_9_translation_rate_color), String(days_9_translation_rate)+'%']);
					dataArray.push(["8일 전\n"+this.days_8, Number(days_8_translation_rate), String(days_8_translation_rate_color), String(days_8_translation_rate)+'%']);
					dataArray.push(["7일 전\n"+this.days_7, Number(days_7_translation_rate), String(days_7_translation_rate_color), String(days_7_translation_rate)+'%']);
					dataArray.push(["6일 전\n"+this.days_6, Number(days_6_translation_rate), String(days_6_translation_rate_color), String(days_6_translation_rate)+'%']);
					dataArray.push(["5일 전\n"+this.days_5, Number(days_5_translation_rate), String(days_5_translation_rate_color), String(days_5_translation_rate)+'%']);
					dataArray.push(["4일 전\n"+this.days_4, Number(days_4_translation_rate), String(days_4_translation_rate_color), String(days_4_translation_rate)+'%']);
					dataArray.push(["3일 전\n"+this.days_3, Number(days_3_translation_rate), String(days_3_translation_rate_color), String(days_3_translation_rate)+'%']);
					dataArray.push(["2일 전\n"+this.days_2, Number(days_2_translation_rate), String(days_2_translation_rate_color), String(days_2_translation_rate)+'%']);
					dataArray.push(["1일 전\n"+this.days_1, Number(days_1_translation_rate), String(days_1_translation_rate_color), String(days_1_translation_rate)+'%']);
					dataArray.push(["오늘\n"+this.today, Number(today_translation_rate), String(today_translation_rate_color), String(today_translation_rate)+'%']);
					
					var data = new google.visualization.arrayToDataTable(dataArray);
					var options = {
						title: coinID+' 일일 변동율(%)',	
						titleTextStyle: {
							fontName: '맑은 고딕',
							fontSize: 20,
							color: 'black',
							bold: true
						},
						bar: {groupWidth: "25%"},
						chartArea: { width: '100%' },
						legend: 'none',
						hAxis: {
							textStyle: {
								fontName: '맑은 고딕',
								fontSize: 13,
								color: 'black',
								bold: true
							}
						},
						vAxis: {
							textStyle: {
								fontName: '맑은 고딕',
								fontSize: 13,
								color: 'black',
								bold: true
							},
						},
						height: 300
					};
					var chart = new google.visualization.ColumnChart(document.getElementById('days_translation_rate'));
					chart.draw(data, options);

					dataArray = [];
					dataArray.push(['X', 'Percent', { role: 'style' }, { role: 'annotation' } ]);
					dataArray.push(["9일 전\n"+this.days_9, Number(days_9_accumulate_rate), String(days_9_accumulate_rate_color), String(days_9_accumulate_rate)+'%']);
					dataArray.push(["8일 전\n"+this.days_8, Number(days_8_accumulate_rate), String(days_8_accumulate_rate_color), String(days_8_accumulate_rate)+'%']);
					dataArray.push(["7일 전\n"+this.days_7, Number(days_7_accumulate_rate), String(days_7_accumulate_rate_color), String(days_7_accumulate_rate)+'%']);
					dataArray.push(["6일 전\n"+this.days_6, Number(days_6_accumulate_rate), String(days_6_accumulate_rate_color), String(days_6_accumulate_rate)+'%']);
					dataArray.push(["5일 전\n"+this.days_5, Number(days_5_accumulate_rate), String(days_5_accumulate_rate_color), String(days_5_accumulate_rate)+'%']);
					dataArray.push(["4일 전\n"+this.days_4, Number(days_4_accumulate_rate), String(days_4_accumulate_rate_color), String(days_4_accumulate_rate)+'%']);
					dataArray.push(["3일 전\n"+this.days_3, Number(days_3_accumulate_rate), String(days_3_accumulate_rate_color), String(days_3_accumulate_rate)+'%']);
					dataArray.push(["2일 전\n"+this.days_2, Number(days_2_accumulate_rate), String(days_2_accumulate_rate_color), String(days_2_accumulate_rate)+'%']);
					dataArray.push(["1일 전\n"+this.days_1, Number(days_1_accumulate_rate), String(days_1_accumulate_rate_color), String(days_1_accumulate_rate)+'%']);
					dataArray.push(["오늘\n"+this.today, Number(today_translation_rate), String(today_translation_rate_color), String(today_translation_rate)+'%']);
					
					var LineOption = {
						title: coinID+' 누적 변동율(%)',	
						titleTextStyle: {
							fontName: '맑은 고딕',
							fontSize: 20,
							color: 'black',
							bold: true
						},
						bar: {groupWidth: "25%"},
						chartArea: { width: '100%' },
						legend: 'none',
						hAxis: {
							textStyle: {
								fontName: '맑은 고딕',
								fontSize: 13,
								color: 'black',
								bold: true
							}
						},
						vAxis: {
							textStyle: {
								fontName: '맑은 고딕',
								fontSize: 13,
								color: 'black',
								bold: true
							},
						},
						height: 300
					};

					var LineData = new google.visualization.arrayToDataTable(dataArray);
					var LineChart = new google.visualization.LineChart(document.getElementById('days_accumulate_rate'));
					LineChart.draw(LineData, LineOption);
				});
			}
		}
    },
	created () {
		
	},
	mounted (){
		axios.get('/Main/BINANCE_FUTURES_DAY_ANALYSIS')
		.then(response => {
			for(var x=0; x<response.data.length; x++){
				this.rowData.push({
					id: response.data[x].id,
					gubun: response.data[x].gubun + ' ['+response.data[x].straight_days+']',
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
		drawChart(){

		}
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
