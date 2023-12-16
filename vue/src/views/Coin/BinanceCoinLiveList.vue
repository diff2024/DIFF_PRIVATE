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
					바이낸스 실시간 통계
				</v-card-title>
				<v-card-text>
					<v-row style="padding-left:15px; padding-top:0px; padding-bottom:0px;">
						<v-col lg="1" md="1" sm="2" cols="2" style="padding-top:0px; padding-bottom:10px; text-align:right;">
							
						</v-col>
						<v-col lg="10" md="10" sm="8" cols="8" style="padding-top:0px; padding-bottom:10px; text-align:right;">
							<v-btn depressed dark small color="primary" style="float:right; margin-top:17px; margin-left:10px;">
								<v-icon small>search</v-icon>&nbsp;<span style="padding-bottom:2px;" @click="makeData">조회</span>
							</v-btn>
						</v-col>
						<v-col lg="1" md="1" sm="2" cols="2" style="padding-top:0px; padding-bottom:10px; text-align:right; vertical-align: middle;">
							
						</v-col>
					</v-row>
				</v-card-text>
			</v-card>
		</v-flex>
		<v-flex xs12 sm12 md12>
			<v-card>
				<v-card-text>
					<v-col lg="4" md="4" sm="12" cols="12" style="padding-top:0px;">
						<template>
							<ag-grid-vue 
								style="width: 100%; height: 500px;"
								class="ag-theme-alpine"
								:columnDefs="columnDefs"
								:rowData="rowData"
								:animateRows="true"
								overlayNoRowsTemplate="바이낸스에서 데이터 가져오는중...">
							</ag-grid-vue>
						</template>
					</v-col>
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
			isLoading: false,
			search_date: '',
		}
	},
	components: {
		AgGridVue
	},
	beforeMount() {
       this.columnDefs = [
           {headerName: '순위', field:"id", width:80, cellStyle: {textAlign: "center"}, sortable: false, filter: true, resizable:true},
           {headerName: '코인명', field:"coin_kor_name", width:150, sortable: false, filter: true, resizable:true},
		   {headerName: '시가', field:"o_price", width:115, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true}, 
		   {headerName: '종가', field:"c_price", width:115, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true}, 
		   {headerName: '시가대비(%)', field:"o_c_price_rate", width:100, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true}
       ];
    },
	created () {
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

		this.search_date = year+'-'+month+'-'+date
	},
	mounted (){
		this.makeData();
	},
	methods: {
		makeData () {
			this.rowData = [];
			this.isLoading = true;
			axios.get('/Binance/MainLiveRankList',{
			params: {
					search_date: this.search_date
				}
			})
			.then(response => {
				for(var x=0; x<response.data.length; x++){
					this.rowData.push({
						id: response.data[x].ranking,
						coin_kor_name: response.data[x].coin_kor_name,
						o_price: Number(response.data[x].o_price),
						c_price: Number(response.data[x].c_price),
						o_c_price_rate: Number(response.data[x].o_c_price_rate)
					})
				}
				this.isLoading = false;
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