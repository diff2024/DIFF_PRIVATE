<style>
.ag-header-cell-label {
  justify-content: center;
}
</style>
<template>
    <v-flex xs12 sm12 md12>
			<v-card>
				<v-card-text>
					<template>
						<h1 id="title" style="font-weight:bold; padding-bottom:20px; color:black;">업비트 코인 15분</h1>
						<ag-grid-vue 
						  	style="width: 100%; height: 400px;"
							class="ag-theme-alpine"
							:columnDefs="columnDefs"
							:rowData="rowData"
							:animateRows="true"
							overlayNoRowsTemplate="업비트에서 데이터 가져오는중..."
							rowSelection="multiple">
						</ag-grid-vue>
					</template>
				</v-card-text>
			</v-card>
		</v-flex>
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
            rowData: []
		}
	},
	components: {
		AgGridVue
	},
	beforeMount() {
       this.columnDefs = [
		   {headerName: '코인', field:"id", width:120, cellStyle: {fontSize: '18px', fontWeight: 'bold', textAlign: 'center'}, sortable: false, filter: true, resizable:true},
		   {headerName: '시가', field:"open_price0", width:130, cellDataType: 'number', cellStyle: {fontSize: '18px', textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true},  
		   {headerName: '현재가', field:"trade_price0", width:130, cellDataType: 'number', cellStyle: {fontSize: '18px', textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true}, 
		   {headerName: '이전종가대비(%)', field:"change_percent", width:150, cellDataType: 'number', cellStyle: {fontSize: '18px', textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true},    
		   {headerName: '이전거래량대비(%)', field:"volume_change_percent", width:200, cellDataType: 'number', cellStyle: {fontSize: '18px', textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true},  
       ];
    },
	created () {
		
	},
	mounted (){
	},
	methods: {
		makeData () {
			this.rowData = [];
			axios.get('/Main/upbit_15m')
			.then(response => {
				console.log('upbit_15m     ' + String(response.data.length));
				if(response.data.length > 100){
					this.rowData = [];
					for(var x=0; x<response.data.length; x++){
						this.rowData.push({
							id: response.data[x].id,
							open_price0: Number(response.data[x].open_price0),
							trade_price0: Number(response.data[x].trade_price0),
							change_percent: Number(response.data[x].change_percent),
							volume_change_percent: Number(response.data[x].volume_change_percent)
						})
					}
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