<style>
.swal2-title{
	font-size:22px !important;
}
.ag-header-cell-label {
  justify-content: center;
}
</style>
<template>
	<LoadingVue v-if="isLoading"></LoadingVue>
	<v-layout v-else wrap row>
		<v-flex xs12 sm12 md12 style="margin-bottom:20px;">
			<v-card xs12 sm12 md12>
				<v-card-title xs12 sm12 md12 class="fontBold" style="font-size:18px; font-weight:bold; padding-left: 60px; padding-top: 20px; padding-bottom: 10px;">
					빗썸 통계
				</v-card-title>
				<v-card-text>
					<v-row style="padding-left:15px; padding-top:0px; padding-bottom:0px;">
						<v-col lg="1" md="1" sm="2" cols="2" style="padding-top:0px; padding-bottom:0px; text-align:right;">
							
						</v-col>
						<v-col lg="10" md="10" sm="8" cols="8" style="padding-top:0px; padding-bottom:0px; text-align:right;">
							<v-text-field type="date" label="시작일자" style="width:180px; float:left;" prepend-icon="event" v-model="std_date" />
							<div style="float:left; padding-top:20px;">&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp;</div>
							<v-text-field type="date" label="종료일자" style="width:180px; float:left;" prepend-icon="event" v-model="end_date" />
							
							<v-btn depressed dark small color="primary" style="float:right; margin-top:17px; margin-left:10px;">
								<v-icon small>search</v-icon>&nbsp;<span style="padding-bottom:2px;" @click="makeData">조회</span>
							</v-btn>
							<v-btn depressed dark small color="success" style="float:right; margin-top:17px; margin-left:10px;">
								<v-icon small>summarize</v-icon>&nbsp;<span style="padding-bottom:2px;" @click="openExcel">엑셀</span>
							</v-btn>
							<v-btn depressed dark small color="info" style="float:right; margin-top:17px; margin-left:10px;">
								<v-icon small>menu</v-icon>&nbsp;<span id="btn_make_report" style="padding-bottom:2px;" @click="makeReport">일일 리포트 생성</span>
							</v-btn>
							<v-btn depressed dark small color="red" style="float:right; margin-top:17px; margin-left:10px;" :to="{ name: 'youtubebithumb_60_240'}" target="_blank">
								<v-icon small>tv</v-icon>&nbsp;<span id="btn_live_60_240" style="padding-bottom:2px;">60/240 Live</span>
							</v-btn>
							<v-btn depressed dark small color="red" style="float:right; margin-top:17px;" :to="{ name: 'youtubebithumb_5_15'}" target="_blank">
								<v-icon small>tv</v-icon>&nbsp;<span id="btn_live_5_15" style="padding-bottom:2px; ">5/15 Live</span>
							</v-btn>
						</v-col>
						<v-col lg="1" md="1" sm="2" cols="2" style="padding-top:0px; padding-bottom:0px; text-align:right; vertical-align: middle;">
							
						</v-col>
					</v-row>
					<v-row justify="center">
						<v-dialog v-model="excelModal" persistent max-width="450" @keydown.esc="excelModal = false">
							<v-card>
							<v-toolbar class="fontDefault" color="#283345" dark>엑셀</v-toolbar>
							<v-card-text style="padding-bottom:0px;">
								<v-row>
									<v-col xl="6" md="6" sm="6" cols="6" align-self="center"  style="padding-bottom:0px;margin-bottom:0px;padding-top:25px;">
										<v-text-field type="date" label="시작일자" style="width:180px; float:left;" prepend-icon="event" v-model="excel_std_date" />
									</v-col>
									<v-col xl="6" md="6" sm="6" cols="6" align-self="center"  style="padding-bottom:0px;margin-bottom:0px;padding-top:25px;">
										<v-text-field type="date" label="종료일자" style="width:180px; float:left;" prepend-icon="event" v-model="excel_end_date" />
									</v-col>
								</v-row>
								<v-row v-for="(coin, index) in coinInfo" v-bind:key="index">
									<v-col xl="1" md="1" sm="1" cols="1" align-self="center" style="padding-top:0px; padding-bottom:0px;">
										<v-checkbox style="left:50%;float:left;" v-model="coin.coin_YN" v-bind:disabled="coin.coin_ticker == 'BTC'" true-value="Y" false-value="N" />
									</v-col>
									<v-col xl="6" md="6" sm="6" cols="6" align-self="center" style="padding-top:0px; padding-bottom:0px;">
										<input type="hidden" v-model="coin.coin_ticker" />
										<span style="font-weight:800; font-size:18px; color:black;">{{ coin.coin_kor_name }}</span>
									</v-col>
									<v-col xl="5" md="5" sm="5" cols="5" align-self="center" style="padding-top:0px; padding-bottom:0px; text-align:right;">
										<span style="font-weight:400; font-size:18px; color:black;">{{ coin.coin_price }}</span>
									</v-col>
								</v-row>
							</v-card-text>
							<v-card-actions class="justify-end">
								<v-btn text style="font-size:18px; font-weight:bold;" @click="makeExcel">다운로드</v-btn>
								<v-btn text style="font-size:18px; font-weight:bold; color:red;" @click="excelModal = !excelModal">닫기</v-btn>
							</v-card-actions>
							</v-card>
						</v-dialog>
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
							overlayNoRowsTemplate="빗썸에서 데이터 가져오는중...">
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
import LoadingVue from '../../components/LoadingSpinner.vue';

export default {
	data () {
		return {
			isLoading: false,
			timeout: 1500000,
			gridOptions: null,
			columnDefs: null,
            rowData: [],
			today_date: '',
			yesterday: '',
			std_date: '',
			end_date: '',
			coinInfo: [{}],
			excel_std_date: '',
			excel_end_date: '',
			excelModal: false
		}
	},
	components: {
		AgGridVue,
		LoadingVue
	},
	beforeMount() {
       this.columnDefs = [
           {headerName: '코인', field:"id", width:120, cellStyle: {textAlign: "center"}, sortable: false, filter: true, resizable:true},
           {headerName: '코인명', field:"coin_kor_name", width:150, sortable: false, filter: true, resizable:true},
		   {headerName: '거래량', field:"trade_volume", width:100, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true},  
		   {headerName: '조회종가', field:"c_price", width:130, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true}, 
		   {headerName: '최저가', field:"min_price", width:130, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true}, 
		   {headerName: '최저가(일자)', field:"min_date", width:130, cellStyle: {textAlign: "center"}, sortable: true, filter: false, resizable:true},  
		   {headerName: '최고가', field:"max_price", width:130, cellDataType: 'number', cellStyle: {textAlign: "right"}, cellRenderer : currencyFormatter, sortable: true, filter: false, resizable:true},
		   {headerName: '최고가(일자)', field:"max_date", width:130, cellStyle: {textAlign: "center"}, sortable: true, filter: false, resizable:true},
		   {headerName: '최저가-최고가 백분율(%)', field:"highest_lowest_100per", cellDataType: 'number', width:200, cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true},  
		   {headerName: '최저가-최고가 등락률(%)', field:"lowest_highest_fluctuation", cellDataType: 'number', width:200, cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true},
		   {headerName: '최고가 대비 하락률(%)', field:"highest_decline_rate", cellDataType: 'number', width:180, cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true},
		   {headerName: '최저가 대비 상승률(%)', field:"lowest_rise_rate", cellDataType: 'number', width:180, cellStyle: {textAlign: "right"}, cellRenderer : percentFormatter, sortable: true, filter: false, resizable:true},
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
		this.coinInfo.splice(0, 1);
		axios.get('/Bithumb/CoinInfo')
		.then(response => {
			for(var x=0; x<response.data.length; x++){
				var coin_price = (response.data[x].c_price).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")

				this.coinInfo.push({
					coin_ticker: response.data[x].coin_ticker,
					coin_kor_name: response.data[x].coin_kor_name,
					coin_price: coin_price,
					coin_YN: 'Y'
				})
			}
		});
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
		this.today_date = year+'-'+month+'-'+date
		if(this.$route.params.date !== undefined){
			this.today_date = this.$route.params.date
			var today_date_split = (this.today_date).split('-');
			var tmp0 = today_date_split[0]
			var tmp1 = today_date_split[1]
			var tmp2 = today_date_split[2]

			if(Number(tmp1) < 10 && String(tmp1).length == 1){
				tmp1 = '0'+tmp1
			}
			if(Number(tmp2) < 10 && String(tmp2).length == 1){
				tmp2 = '0'+tmp2
			}
			this.today_date = tmp0 + '-' + tmp1 + '-' + tmp2
			
			var yesterday_date = new Date(Number(this.today_date.substring(0,4)), Number(this.today_date.substring(5,7)), Number(this.today_date.substring(8,10)));
			yesterday_date = new Date(yesterday_date.setMonth(yesterday_date.getMonth() - 1));
			yesterday_date = new Date(yesterday_date.setDate(yesterday_date.getDate() - 1));
			var yesterday_year = yesterday_date.getFullYear();
			var yesterday_month = yesterday_date.getMonth()+1;
			var yesterday_day = yesterday_date.getDate();
			
			if(Number(yesterday_month) < 10 && String(yesterday_month).length == 1){
				yesterday_month = '0'+yesterday_month
			}
			if(Number(yesterday_day) < 10 && String(yesterday_day).length == 1){
				yesterday_day = '0'+yesterday_day
			}
			this.yesterday = yesterday_year+'-'+yesterday_month+'-'+yesterday_day
		}
		this.makeData();
	},
	methods: {
		makeReport(){
			if(this.$route.params.date !== undefined){
				this.isLoading = true;
				
				axios.post('/Bithumb/CoinAnalysisCreate', null,{
					params: {
						date: this.today_date
					},
					timeout: this.timeout
				})
				.then(response => {
					this.isLoading = false;

					Swal.fire({
						title:'일일 리포트 생성이 완료되었습니다.',
						icon: 'success'
					});
				})
				.catch(error => {
					this.isLoading = false;
					
					if (error.code === 'ECONNABORTED') {
						console.log('요청이 타임아웃되었습니다.');
					} else {
						console.error('요청 실패:', error);
					}
				});
			}else{
				Swal.fire({
					icon: 'question',
					text: " 빗썸 " + this.today_date + " 일일 리포트를 생성 하시겠습니까?",
					showCancelButton: true,
					confirmButtonColor: '#3085d6',
					cancelButtonColor: '#d33',
					confirmButtonText: '생성',
					cancelButtonText: '취소',
					allowOutsideClick: false,
				}).then(function (result) {
					if (result.isConfirmed) {
						this.isLoading = true;

						axios.post('/Bithumb/CoinAnalysisCreate', null,{
							params: {
								date: ''
							},
							timeout: this.timeout
						})
						.then(response => {
							this.isLoading = false;

							Swal.fire({
								title:'일일 리포트 생성이 완료되었습니다.',
								icon: 'success'
							});
						})
						.catch(error => {
							this.isLoading = false;
							
							if (error.code === 'ECONNABORTED') {
								console.log('요청이 타임아웃되었습니다.');
							} else {
								console.error('요청 실패:', error);
							}
						});
					}
				});
			}
		},
		openExcel() {
			let today = new Date();
			let year = today.getFullYear();
			let month = today.getMonth() + 1;
			let date = today.getDate();

			axios.get('/Bithumb/CoinInfo')
			.then(response => {
				console.log(response.data)
			});

			if(Number(month) < 10){
				month = '0'+month
			}
			if(Number(date) < 10){
				date = '0'+date
			}

			var now = new Date();
			var yesterday = new Date(now.setDate(now.getDate() - 1));
			let yesterday_year = yesterday.getFullYear();
			let yesterday_month = yesterday.getMonth() + 1;
			let yesterday_date = yesterday.getDate();

			if(Number(yesterday_month) < 10){
				yesterday_month = '0'+yesterday_month
			}
			if(Number(yesterday_date) < 10){
				yesterday_date = '0'+yesterday_date
			}

			this.excel_std_date = yesterday_year + '-' + yesterday_month + '-' + yesterday_date
			this.excel_end_date = yesterday_year + '-' + yesterday_month + '-' + yesterday_date
			
			this.excelModal = true
		},
		makeExcel() {
			if(this.excel_std_date.length != 10){
				this.excel_std_date = ''
			}
			if(this.excel_end_date.length != 10){
				this.excel_end_date = ''
			}

			if(this.excel_std_date == ''){
				Swal.fire({
					title:'엑셀 시작일자를 설정 하시기 바랍니다.',
					icon: 'error'
				});
				return;
			}

			if(this.excel_end_date == ''){
				Swal.fire({
					title:'엑셀 종료일자를 설정 하시기 바랍니다.',
					icon: 'error'
				});
				return;
			}

			if(this.excel_std_date != '' && this.excel_end_date != '' && Number((this.excel_std_date).replaceAll("-", "")) > Number((this.excel_end_date).replaceAll("-", ""))){
				Swal.fire({
					title:'엑셀 시작일자가 종료일자보다 클 수 없습니다.',
					icon: 'error'
				});
				return;
			}

			var lastDate = new Date(Number(this.excel_end_date.substring(0, 4)), Number(this.excel_end_date.substring(5, 7)), 0);  
			this.excelModal = false;

			var coin_data = ""
			const TMPcoinInfo = this.coinInfo.filter(el=>el.coin_YN == 'Y');
			if(this.coinInfo.length > TMPcoinInfo.length){
				for(var x=0; x<TMPcoinInfo.length; x++){
					var tmp_ticker = TMPcoinInfo[x].coin_ticker;

					if(coin_data == ''){
						coin_data = tmp_ticker
					}else{
						coin_data = coin_data + ',' + tmp_ticker
					}
				}
			}

			if(this.excel_std_date == this.excel_end_date){
				axios.get('/Bithumb/ExcelMake_Daily',{
				params: {
						std_date: this.excel_std_date,
						end_date: this.excel_end_date,
						coin_ticker: coin_data
					},
				responseType: 'blob' 
				})
				.then(response => {
					const url = window.URL.createObjectURL(new Blob([response.data], { type: response.headers['content-type'] }));
					const link = document.createElement('a');
					link.href = url;
					link.setAttribute('download', '빗썸_Daily_'+this.excel_std_date+'.xlsx');
					document.body.appendChild(link);
					link.click();
				});
			}else if((this.excel_std_date.substring(5,7) == this.excel_end_date.substring(5,7)) && (this.excel_std_date.slice(-2)) == "01" && this.excel_end_date.slice(-2) == lastDate.getDate()){
				axios.get('/Bithumb/ExcelMake_Monthly',{
				params: {
						std_date: this.excel_std_date,
						end_date: this.excel_end_date,
						yyyy: this.excel_std_date.substring(0,4),
						mm: this.excel_std_date.substring(5,7),
						excel_title: this.excel_std_date.substring(0,4) + '년 ' + this.excel_std_date.substring(5,7) + '월',
						coin_ticker: coin_data
					},
				responseType: 'blob' 
				})
				.then(response => {
					const url = window.URL.createObjectURL(new Blob([response.data], { type: response.headers['content-type'] }));
					const link = document.createElement('a');
					link.href = url;
					link.setAttribute('download', '빗썸_Monthly_'+this.excel_std_date+'_'+this.excel_end_date+'.xlsx');
					document.body.appendChild(link);
					link.click();
				});
			}else{
				axios.get('/Bithumb/ExcelMake_Weekly',{
				params: {
						std_date: this.excel_std_date,
						end_date: this.excel_end_date,
						coin_ticker: coin_data
					},
				responseType: 'blob' 
				})
				.then(response => {
					const url = window.URL.createObjectURL(new Blob([response.data], { type: response.headers['content-type'] }));
					const link = document.createElement('a');
					link.href = url;
					link.setAttribute('download', '빗썸_Weekly_'+this.excel_std_date+'_'+this.excel_end_date+'.xlsx');
					document.body.appendChild(link);
					link.click();
				});
			}
		},
		makeData () {
			if(this.std_date.length != 10){
				this.std_date = ''
			}
			if(this.end_date.length != 10){
				this.end_date = ''
			}

			if(this.std_date != '' && this.end_date != '' && Number((this.std_date).replaceAll("-", "")) > Number((this.end_date).replaceAll("-", ""))){
				Swal.fire({
					title:'시작일자가 종료일자보다 클 수 없습니다.',
					icon: 'error'
				});
				return;
			}

			this.rowData = [];

			this.isLoading = true;
			axios.get('/Bithumb/MainGridList',{
			params: {
					std_date: this.std_date,
					end_date: this.end_date
				}
			})
			.then(response => {
				for(var x=0; x<response.data.length; x++){
					this.rowData.push({
						id: response.data[x].id,
						coin_kor_name: response.data[x].coin_kor_name,
						trade_volume: response.data[x].trade_volume,
						c_price: Number(response.data[x].c_price),
						min_price: Number(response.data[x].min_price),
						min_date: response.data[x].min_date,
						max_price: Number(response.data[x].max_price),
						max_date: response.data[x].max_date,
						lowest_highest_fluctuation: Number(response.data[x].lowest_highest_fluctuation),
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
