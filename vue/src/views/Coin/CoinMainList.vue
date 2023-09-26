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
					일일코인분석
				</v-card-title>
				<v-card-text>
					<v-row style="padding-left:15px;">
						<v-col lg="1" md="1" sm="2" cols="2" style="text-align:right;">
							
						</v-col>
						<v-col lg="2" md="2" sm="5" cols="5" style="text-align:right;">
							<v-text-field type="date" label="시작일자" style="width:180px;" prepend-icon="event" v-model="std_date" @change="makeData" />
						</v-col>
						<v-col lg="2" md="2" sm="5" cols="5" style="text-align:left;">
							<v-text-field type="date" label="종료일자" style="width:180px;" prepend-icon="event" v-model="end_date" @change="makeData" />
						</v-col>
					</v-row>
				</v-card-text>
			</v-card>
		</v-flex>
		<v-flex xs12 sm12 md12>
			<v-card>
				<v-card-text>
					<template>
						<ag-grid-vue 
						  	style="width: 100%; height: 500px;"
							class="ag-theme-alpine"
							:columnDefs="columnDefs"
							:rowData="rowData"
							:animateRows="true"
							rowSelection="multiple">
						</ag-grid-vue>
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
export default {
	data () {
		return {
			gridOptions: null,
			columnDefs: null,
            rowData: [],
			std_date: '',
			end_date: ''
		}
	},
	components: {
		AgGridVue
	},
	beforeMount() {
       this.columnDefs = [
           {headerName: '코인', field:"id", width:120, cellStyle: {textAlign: "center"}, sortable: false, filter: true, resizable:true},
           {headerName: '코인명', field:"coin_kor_name", width:150, sortable: false, filter: true, resizable:true},
		   {headerName: '거래량', field:"trade_volume", width:100, cellStyle: {textAlign: "right"}, sortable: false, filter: false, resizable:true},  
		   {headerName: '조회종가', field:"c_price", width:130, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true}, 
		   {headerName: '최저가', field:"min_price", width:130, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true}, 
		   {headerName: '최고가', field:"max_price", width:130, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true},
		   {headerName: '최고가-최저가 백분율(%)', field:"highest_lowest_100per", cellDataType: 'number', width:200, cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true},
		   {headerName: '최고가 대비 하락율(%)', field:"highest_decline_rate", cellDataType: 'number', width:180, cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true},
		   {headerName: '최저가 대비 상승율(%)', field:"lowest_rise_rate", cellDataType: 'number', width:180, cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true},
		   {headerName: '일수(5%)', field:"o_c_price_rate_5_count", width:100, cellDataType: 'number', cellStyle: {textAlign: "right"}, sortable: true, filter: false, resizable:true},  
		   {headerName: '일수(10%)', field:"o_c_price_rate_10_count", width:100, cellDataType: 'number', cellStyle: {textAlign: "right"}, sortable: true, filter: false, resizable:true},  
		   {headerName: '일수(15%)', field:"o_c_price_rate_15_count", width:100, cellDataType: 'number', cellStyle: {textAlign: "right"}, sortable: true, filter: false, resizable:true},  
		   {headerName: '일수(20%)', field:"o_c_price_rate_20_count", width:100, cellDataType: 'number', cellStyle: {textAlign: "right"}, sortable: true, filter: false, resizable:true},  
		   {headerName: '일수(25%)', field:"o_c_price_rate_25_count", width:100, cellDataType: 'number', cellStyle: {textAlign: "right"}, sortable: true, filter: false, resizable:true},  
		   {headerName: '일수(30%)', field:"o_c_price_rate_30_count", width:100, cellDataType: 'number', cellStyle: {textAlign: "right"}, sortable: true, filter: false, resizable:true},  
		   {headerName: '최근일자(5%)', field:"o_c_price_rate_5_date", width:130, cellStyle: {textAlign: "center"}, sortable: true, filter: false, resizable:true},
		   {headerName: '최근일자(10%)', field:"o_c_price_rate_10_date", width:130, cellStyle: {textAlign: "center"}, sortable: true, filter: false, resizable:true},
		   {headerName: '최근일자(15%)', field:"o_c_price_rate_15_date", width:130, cellStyle: {textAlign: "center"}, sortable: true, filter: false, resizable:true},
		   {headerName: '최근일자(20%)', field:"o_c_price_rate_20_date", width:130, cellStyle: {textAlign: "center"}, sortable: true, filter: false, resizable:true},
		   {headerName: '최근일자(25%)', field:"o_c_price_rate_25_date", width:130, cellStyle: {textAlign: "center"}, sortable: true, filter: false, resizable:true},
		   {headerName: '최근일자(30%)', field:"o_c_price_rate_30_date", width:130, cellStyle: {textAlign: "center"}, sortable: true, filter: false, resizable:true}
       ];
    },
	created () {
		
	},
	mounted (){
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

		this.end_date = year+'-'+month+'-'+date
		this.makeData();
	},
	methods: {
		makeData () {
			if(this.std_date.length != 10){
				this.std_date = ''
			}
			if(this.end_date.length != 10){
				this.end_date = ''
			}

			this.rowData = [];
			axios.get('/Coin/MainGridList',{
			params: {
					std_date: this.std_date,
					end_date: this.end_date
				}
			})
			.then(response => {
				//console.log(response.data)
				for(var x=0; x<response.data.length; x++){
					this.rowData.push({
						id: response.data[x].id,
						coin_kor_name: response.data[x].coin_kor_name,
						trade_volume: response.data[x].trade_volume,
						c_price: Number(response.data[x].c_price),
						min_price: Number(response.data[x].min_price),
						max_price: Number(response.data[x].max_price),
						highest_lowest_100per: Number(response.data[x].highest_lowest_100per),
						highest_decline_rate: Number(response.data[x].highest_decline_rate),
						lowest_rise_rate: Number(response.data[x].lowest_rise_rate),
						o_c_price_rate_5_count: Number(response.data[x].o_c_price_rate_5_count),
						o_c_price_rate_10_count: Number(response.data[x].o_c_price_rate_10_count),
						o_c_price_rate_15_count: Number(response.data[x].o_c_price_rate_15_count),
						o_c_price_rate_20_count: Number(response.data[x].o_c_price_rate_20_count),
						o_c_price_rate_25_count: Number(response.data[x].o_c_price_rate_25_count),
						o_c_price_rate_30_count: Number(response.data[x].o_c_price_rate_30_count),
						o_c_price_rate_5_date: response.data[x].o_c_price_rate_5_date,
						o_c_price_rate_10_date: response.data[x].o_c_price_rate_10_date,
						o_c_price_rate_15_date: response.data[x].o_c_price_rate_15_date,
						o_c_price_rate_20_date: response.data[x].o_c_price_rate_20_date,
						o_c_price_rate_25_date: response.data[x].o_c_price_rate_25_date,
						o_c_price_rate_30_date: response.data[x].o_c_price_rate_30_date,
					})
				}
			})
		},
		gridSizeFit (params) {
			// 모니터나 브라우저 크기에 따라 반응하여 그리드 컬럼 사이즈를 조정합니다.
			if ( window.innerWidth > 800 ){ // 화면 가로가 800 px 이 넘을 경우
				params.api.sizeColumnsToFit() // 가로 스크롤바가 생기지 않도록 컬럼 사이즈를 그리드에 꼭 맞게 조정합니다.
			} else {
				// 컬럼의 데이터값이 잘리지 않도록 조정합니다.
				let allColumnIds = []
				this.gridOptions.columnApi.getAllColumns().forEach(function (column) {
					allColumnIds.push(column.colId)
				})
				this.gridOptions.columnApi.autoSizeColumns(allColumnIds)
			}
		}
	}
}
window.currencyFormatter = function currencyFormatter(params) {
	if(!params.value && params.value != "0") {
		return this.defaultBlank;
	}
	return (params.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
};

window.percentFormatter = function percentFormatter(params) {
	if(!params.value && params.value != "0") {
		return this.defaultBlank;
	}
	return (params.value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + '%';
}
</script>
