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
					Report
				</v-card-title>
				<v-card-text>
					<v-row style="padding-left:15px; padding-top:0px; padding-bottom:0px;">
						<v-col lg="1" md="1" sm="2" cols="2" style="padding-top:0px; padding-bottom:10px; text-align:right;">
							
						</v-col>
						<v-col lg="10" md="10" sm="8" cols="8" style="padding-top:0px; padding-bottom:10px; text-align:right;">
							<v-btn depressed dark small color="primary" style="float:right; margin-top:17px; margin-left:10px;">
								<v-icon small>search</v-icon>&nbsp;<span style="padding-bottom:2px;" @click="makeDay">조회</span>
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
					<template>
						<v-row>
							<v-col lg="2" md="2" sm="12" cols="12" style="padding-top:0px;">
								<ag-grid-vue 
									style="width: 100%; height: 500px;"
									class="ag-theme-alpine"
									:columnDefs="columnDefs"
									:rowData="rowData"
									@rowClicked="onRowClicked"
									overlayNoRowsTemplate="DB에서 데이터 가져오는중...">
								</ag-grid-vue>
							</v-col>
							<v-col id="daily_report" lg="10" md="10" sm="12" cols="12" style="padding-top:0px;">
								<div v-html="report_html"></div>
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
            rowData: [],
			search_date: '',
			search_blog: '',
			report_html: ''
		}
	},
	components: {
		AgGridVue
	},
	beforeMount() {
       this.columnDefs = [
           {headerName: 'Report', field:"id", width:250, cellStyle: {textAlign: "center"}, sortable: false, filter: true, resizable:true},
		   {headerName: '일자', field:"yyyymmdd", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '거래소', field:"market", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '블로그ID', field:"blog", hide:true, sortable: false, filter: true, resizable:true},
       ];
    },
	created () {
		if(this.$route.params.date !== undefined && this.$route.params.blog !== undefined && this.$route.params.market !== undefined){
			this.search_date = this.$route.params.date
			this.search_market = this.$route.params.market
			this.search_blog = this.$route.params.blog
			this.getHTMLSearch();
		}else{
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
		}
	},
	mounted (){
		this.makeDay();
	},
	methods: {
		makeDay () {
			this.rowData = [];
			this.isLoading = true;
			axios.get('/Main/ReportList')
			.then(response => {
				for(var x=0; x<response.data.length; x++){
					this.rowData.push({
						id: response.data[x].report_name,
						yyyymmdd: response.data[x].yyyymmdd,
						market: response.data[x].market,
						blog: response.data[x].blog_id
					})
				}
				this.isLoading = false;
			})
		},
		onRowClicked(params) {
			this.search_date = params.data.yyyymmdd;
			this.search_market = params.data.market;
			this.search_blog = params.data.blog;
			this.$router.push('/Report/'+this.search_date+'/'+this.search_market+'/'+this.search_blog)
			this.getHTMLSearch();
		},
		getHTMLSearch(){
			axios.get('/Main/ReportHTMLList',{
			params: {
					date: this.search_date,
					market: this.search_market,
					blog: this.search_blog
				}
			})
			.then(response => {
				this.report_html = ''
				for(var x=0; x<response.data.length; x++){
					this.report_html += (response.data[x].html)
				}
				
				if(this.search_blog == '2' || this.search_blog == '3' || this.search_blog == '4' || this.search_blog == '5'){
					if(this.search_market == 'upbit'){
						setTimeout(function() {
							var head = document.getElementById("upbit_hour_graph_script");
							var script = document.createElement('script');
							script.type = 'application/javascript';
							script.src = '../../../upbit_hour_graph_script.js';
							head.appendChild(script);
							/*
							var head2 = document.getElementById("upbit_4hour_graph_script");
							var script2 = document.createElement('script');
							script2.type = 'application/javascript';
							script2.src = '../../../upbit_4hour_graph_script.js';
							head2.appendChild(script2);
							*/
							var head3 = document.getElementById("upbit_day_graph_script");
							var script3 = document.createElement('script');
							script3.type = 'application/javascript';
							script3.src = '../../../upbit_day_graph_script.js';
							head3.appendChild(script3);
						}, 1500);
					}else if(this.search_market == 'bithumb'){
						setTimeout(function() {
							var head4 = document.getElementById("bithumb_hour_graph_script");
							var script4 = document.createElement('script');
							script4.type = 'application/javascript';
							script4.src = '../../../bithumb_hour_graph_script.js';
							head4.appendChild(script4);
							/*
							var head5 = document.getElementById("bithumb_4hour_graph_script");
							var script5 = document.createElement('script');
							script5.type = 'application/javascript';
							script5.src = '../../../bithumb_4hour_graph_script.js';
							head5.appendChild(script5);
							*/
							var head6 = document.getElementById("bithumb_day_graph_script");
							var script6 = document.createElement('script');
							script6.type = 'application/javascript';
							script6.src = '../../../bithumb_day_graph_script.js';
							head6.appendChild(script6);
						}, 1500);
					}else if(this.search_market == 'binance'){
						setTimeout(function() {
							var head7 = document.getElementById("binance_hour_graph_script");
							var script7 = document.createElement('script');
							script7.type = 'application/javascript';
							script7.src = '../../../binance_hour_graph_script.js';
							head7.appendChild(script7);
							/*
							var head8 = document.getElementById("binance_4hour_graph_script");
							var script8 = document.createElement('script');
							script8.type = 'application/javascript';
							script8.src = '../../../binance_4hour_graph_script.js';
							head8.appendChild(script8);
							*/
							var head9 = document.getElementById("binance_day_graph_script");
							var script9 = document.createElement('script');
							script9.type = 'application/javascript';
							script9.src = '../../../binance_day_graph_script.js';
							head9.appendChild(script9);
						}, 1500);
					}else if(this.search_market == 'binance_futures'){
						setTimeout(function() {
							var head10 = document.getElementById("binance_futures_hour_graph_script");
							var script10 = document.createElement('script');
							script10.type = 'application/javascript';
							script10.src = '../../../binance_futures_hour_graph_script.js';
							head10.appendChild(script10);
							/*
							var head11 = document.getElementById("binance_futures_4hour_graph_script");
							var script11 = document.createElement('script');
							script11.type = 'application/javascript';
							script11.src = '../../../binance_futures_4hour_graph_script.js';
							head11.appendChild(script11);
							*/
							var head12 = document.getElementById("binance_futures_day_graph_script");
							var script12 = document.createElement('script');
							script12.type = 'application/javascript';
							script12.src = '../../../binance_futures_day_graph_script.js';
							head12.appendChild(script12);
						}, 1500);
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
