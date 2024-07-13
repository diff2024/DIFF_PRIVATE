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
					유튜브 Short
				</v-card-title>
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
							<v-col id="daily_short" lg="8" md="8" sm="8" cols="8" style="padding-top:0px;">
								<div v-html="short_html"></div>
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
			short_html: '',
			search_date: '',
			search_market: '',

			/* ClickCoin */
			btc_coin_ticker: '',
			btc_coin_name: '',
			btc_coin_particle: '',
			btc_o_price: '',
			btc_l_price: '',
			btc_h_price: '',
			btc_c_price: '',
			btc_o_c_price: '',
			btc_o_c_price_rate: '',
			btc_l_h_price: '',
			btc_l_h_price_rate: '',
			rank1_coin_ticker: '',
			rank1_coin_name: '',
			rank1_coin_particle: '',
			rank1_o_price: '',
			rank1_l_price: '',
			rank1_h_price: '',
			rank1_c_price: '',
			rank1_o_c_price: '',
			rank1_o_c_price_rate: '',
			rank1_l_h_price: '',
			rank1_l_h_price_rate: '',
			rank2_coin_ticker: '',
			rank2_coin_name: '',
			rank2_coin_particle: '',
			rank2_o_price: '',
			rank2_l_price: '',
			rank2_h_price: '',
			rank2_c_price: '',
			rank2_o_c_price: '',
			rank2_o_c_price_rate: '',
			rank2_l_h_price: '',
			rank2_l_h_price_rate: '',
			rank3_coin_ticker: '',
			rank3_coin_name: '',
			rank3_coin_particle: '',
			rank3_o_price: '',
			rank3_l_price: '',
			rank3_h_price: '',
			rank3_c_price: '',
			rank3_o_c_price: '',
			rank3_o_c_price_rate: '',
			rank3_l_h_price: '',
			rank3_l_h_price_rate: '',
			rank4_coin_ticker: '',
			rank4_coin_name: '',
			rank4_coin_particle: '',
			rank4_o_price: '',
			rank4_l_price: '',
			rank4_h_price: '',
			rank4_c_price: '',
			rank4_o_c_price: '',
			rank4_o_c_price_rate: '',
			rank4_l_h_price: '',
			rank4_l_h_price_rate: '',
			rank5_coin_ticker: '',
			rank5_coin_name: '',
			rank5_coin_particle: '',
			rank5_o_price: '',
			rank5_l_price: '',
			rank5_h_price: '',
			rank5_c_price: '',
			rank5_o_c_price: '',
			rank5_o_c_price_rate: '',
			rank5_l_h_price: '',
			rank5_l_h_price_rate: '',
		}
	},
	components: {
		AgGridVue
	},
	beforeMount() {
       this.columnDefs = [
           {headerName: 'Short', field:"id", width:220, cellStyle: {textAlign: "left"}, sortable: false, filter: true, resizable:true},
		   {headerName: '일자', field:"yyyymmdd", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '거래소', field:"market", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 코인티커', field:"btc_coin_ticker", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 코인명', field:"btc_coin_name", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 조사', field:"btc_coin_particle", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 시가', field:"btc_o_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 저가', field:"btc_l_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 고가', field:"btc_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 종가', field:"btc_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 종가-시가', field:"btc_o_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 시가 대비 종가', field:"btc_o_c_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 고가-저가', field:"btc_l_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 저가 대비 고가', field:"btc_l_h_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 코인티커', field:"rank1_coin_ticker", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 코인명', field:"rank1_coin_name", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 조사', field:"rank1_coin_particle", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 시가', field:"rank1_o_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 저가', field:"rank1_l_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 고가', field:"rank1_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 종가', field:"rank1_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 종가-시가', field:"rank1_o_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 시가 대비 종가', field:"rank1_o_c_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 고가-저가', field:"rank1_l_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 저가 대비 고가', field:"rank1_l_h_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 코인티커', field:"rank2_coin_ticker", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 코인명', field:"rank2_coin_name", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 조사', field:"rank2_coin_particle", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 시가', field:"rank2_o_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 저가', field:"rank2_l_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 고가', field:"rank2_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 종가', field:"rank2_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 종가-시가', field:"rank2_o_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 시가 대비 종가', field:"rank2_o_c_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 고가-저가', field:"rank2_l_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 저가 대비 고가', field:"rank2_l_h_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 코인티커', field:"rank3_coin_ticker", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 코인명', field:"rank3_coin_name", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 조사', field:"rank3_coin_particle", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 시가', field:"rank3_o_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 저가', field:"rank3_l_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 고가', field:"rank3_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 종가', field:"rank3_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 종가-시가', field:"rank3_o_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 시가 대비 종가', field:"rank3_o_c_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 고가-저가', field:"rank3_l_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 저가 대비 고가', field:"rank3_l_h_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 코인티커', field:"rank4_coin_ticker", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 코인명', field:"rank4_coin_name", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 조사', field:"rank4_coin_particle", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 시가', field:"rank4_o_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 저가', field:"rank4_l_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 고가', field:"rank4_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 종가', field:"rank4_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 종가-시가', field:"rank4_o_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 시가 대비 종가', field:"rank4_o_c_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 고가-저가', field:"rank4_l_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 저가 대비 고가', field:"rank4_l_h_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 코인티커', field:"rank5_coin_ticker", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 코인명', field:"rank5_coin_name", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 조사', field:"rank5_coin_particle", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 시가', field:"rank5_o_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 저가', field:"rank5_l_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 고가', field:"rank5_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 종가', field:"rank5_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 종가-시가', field:"rank5_o_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 시가 대비 종가', field:"rank5_o_c_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 고가-저가', field:"rank5_l_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 저가 대비 고가', field:"rank5_l_h_price_rate", hide:true, sortable: false, filter: true, resizable:true},
       ];
    },
	created () {
		if(this.$route.params.date !== undefined && this.$route.params.market !== undefined){
			this.search_date = this.$route.params.date
			this.search_market = this.$route.params.market
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
			axios.get('/Main/ShortList')
			.then(response => {
				for(var x=0; x<response.data.length; x++){
					this.rowData.push({
						id: '['+response.data[x].yyyymmdd+'] ['+response.data[x].kor_market+']',
						yyyymmdd: response.data[x].yyyymmdd,
						market: response.data[x].market,

						btc_coin_ticker: response.data[x].btc_coin_ticker,
						btc_coin_name: response.data[x].btc_coin_name,
						btc_coin_particle: response.data[x].btc_coin_particle,
						btc_o_price: response.data[x].btc_o_price,
						btc_l_price: response.data[x].btc_l_price,
						btc_h_price: response.data[x].btc_h_price,
						btc_c_price: response.data[x].btc_c_price,
						btc_o_c_price: response.data[x].btc_o_c_price,
						btc_o_c_price_rate: response.data[x].btc_o_c_price_rate,
						btc_l_h_price: response.data[x].btc_l_h_price,
						btc_l_h_price_rate: response.data[x].btc_l_h_price_rate,

						rank1_coin_ticker: response.data[x].rank1_coin_ticker,
						rank1_coin_name: response.data[x].rank1_coin_name,
						rank1_coin_particle: response.data[x].rank1_coin_particle,
						rank1_o_price: response.data[x].rank1_o_price,
						rank1_l_price: response.data[x].rank1_l_price,
						rank1_h_price: response.data[x].rank1_h_price,
						rank1_c_price: response.data[x].rank1_c_price,
						rank1_o_c_price: response.data[x].rank1_o_c_price,
						rank1_o_c_price_rate: response.data[x].rank1_o_c_price_rate,
						rank1_l_h_price: response.data[x].rank1_l_h_price,
						rank1_l_h_price_rate: response.data[x].rank1_l_h_price_rate,

						rank2_coin_ticker: response.data[x].rank2_coin_ticker,
						rank2_coin_name: response.data[x].rank2_coin_name,
						rank2_coin_particle: response.data[x].rank2_coin_particle,
						rank2_o_price: response.data[x].rank2_o_price,
						rank2_l_price: response.data[x].rank2_l_price,
						rank2_h_price: response.data[x].rank2_h_price,
						rank2_c_price: response.data[x].rank2_c_price,
						rank2_o_c_price: response.data[x].rank2_o_c_price,
						rank2_o_c_price_rate: response.data[x].rank2_o_c_price_rate,
						rank2_l_h_price: response.data[x].rank2_l_h_price,
						rank2_l_h_price_rate: response.data[x].rank2_l_h_price_rate,

						rank3_coin_ticker: response.data[x].rank3_coin_ticker,
						rank3_coin_name: response.data[x].rank3_coin_name,
						rank3_coin_particle: response.data[x].rank3_coin_particle,
						rank3_o_price: response.data[x].rank3_o_price,
						rank3_l_price: response.data[x].rank3_l_price,
						rank3_h_price: response.data[x].rank3_h_price,
						rank3_c_price: response.data[x].rank3_c_price,
						rank3_o_c_price: response.data[x].rank3_o_c_price,
						rank3_o_c_price_rate: response.data[x].rank3_o_c_price_rate,
						rank3_l_h_price: response.data[x].rank3_l_h_price,
						rank3_l_h_price_rate: response.data[x].rank3_l_h_price_rate,

						rank4_coin_ticker: response.data[x].rank4_coin_ticker,
						rank4_coin_name: response.data[x].rank4_coin_name,
						rank4_coin_particle: response.data[x].rank4_coin_particle,
						rank4_o_price: response.data[x].rank4_o_price,
						rank4_l_price: response.data[x].rank4_l_price,
						rank4_h_price: response.data[x].rank4_h_price,
						rank4_c_price: response.data[x].rank4_c_price,
						rank4_o_c_price: response.data[x].rank4_o_c_price,
						rank4_o_c_price_rate: response.data[x].rank4_o_c_price_rate,
						rank4_l_h_price: response.data[x].rank4_l_h_price,
						rank4_l_h_price_rate: response.data[x].rank4_l_h_price_rate,

						rank5_coin_ticker: response.data[x].rank5_coin_ticker,
						rank5_coin_name: response.data[x].rank5_coin_name,
						rank5_coin_particle: response.data[x].rank5_coin_particle,
						rank5_o_price: response.data[x].rank5_o_price,
						rank5_l_price: response.data[x].rank5_l_price,
						rank5_h_price: response.data[x].rank5_h_price,
						rank5_c_price: response.data[x].rank5_c_price,
						rank5_o_c_price: response.data[x].rank5_o_c_price,
						rank5_o_c_price_rate: response.data[x].rank5_o_c_price_rate,
						rank5_l_h_price: response.data[x].rank5_l_h_price,
						rank5_l_h_price_rate: response.data[x].rank5_l_h_price_rate,
					})

					if(this.search_date == response.data[x].yyyymmdd && this.search_market == response.data[x].market){
						this.btc_coin_ticker = response.data[x].btc_coin_ticker;
						this.btc_coin_name = response.data[x].btc_coin_name;
						this.btc_coin_particle = response.data[x].btc_coin_particle;
						this.btc_o_price = response.data[x].btc_o_price;
						this.btc_l_price = response.data[x].btc_l_price;
						this.btc_h_price = response.data[x].btc_h_price;
						this.btc_c_price = response.data[x].btc_c_price;
						this.btc_o_c_price = response.data[x].btc_o_c_price;
						this.btc_o_c_price_rate = response.data[x].btc_o_c_price_rate;
						this.btc_l_h_price = response.data[x].btc_l_h_price;
						this.btc_l_h_price_rate = response.data[x].btc_l_h_price_rate;

						this.rank1_coin_ticker = response.data[x].rank1_coin_ticker;
						this.rank1_coin_name = response.data[x].rank1_coin_name;
						this.rank1_coin_particle = response.data[x].rank1_coin_particle;
						this.rank1_o_price = response.data[x].rank1_o_price;
						this.rank1_l_price = response.data[x].rank1_l_price;
						this.rank1_h_price = response.data[x].rank1_h_price;
						this.rank1_c_price = response.data[x].rank1_c_price;
						this.rank1_o_c_price = response.data[x].rank1_o_c_price;
						this.rank1_o_c_price_rate = response.data[x].rank1_o_c_price_rate;
						this.rank1_l_h_price = response.data[x].rank1_l_h_price;
						this.rank1_l_h_price_rate = response.data[x].rank1_l_h_price_rate;

						this.rank2_coin_ticker = response.data[x].rank2_coin_ticker;
						this.rank2_coin_name = response.data[x].rank2_coin_name;
						this.rank2_coin_particle = response.data[x].rank2_coin_particle;
						this.rank2_o_price = response.data[x].rank2_o_price;
						this.rank2_l_price = response.data[x].rank2_l_price;
						this.rank2_h_price = response.data[x].rank2_h_price;
						this.rank2_c_price = response.data[x].rank2_c_price;
						this.rank2_o_c_price = response.data[x].rank2_o_c_price;
						this.rank2_o_c_price_rate = response.data[x].rank2_o_c_price_rate;
						this.rank2_l_h_price = response.data[x].rank2_l_h_price;
						this.rank2_l_h_price_rate = response.data[x].rank2_l_h_price_rate;
						
						this.rank3_coin_ticker = response.data[x].rank3_coin_ticker;
						this.rank3_coin_name = response.data[x].rank3_coin_name;
						this.rank3_coin_particle = response.data[x].rank3_coin_particle;
						this.rank3_o_price = response.data[x].rank3_o_price;
						this.rank3_l_price = response.data[x].rank3_l_price;
						this.rank3_h_price = response.data[x].rank3_h_price;
						this.rank3_c_price = response.data[x].rank3_c_price;
						this.rank3_o_c_price = response.data[x].rank3_o_c_price;
						this.rank3_o_c_price_rate = response.data[x].rank3_o_c_price_rate;
						this.rank3_l_h_price = response.data[x].rank3_l_h_price;
						this.rank3_l_h_price_rate = response.data[x].rank3_l_h_price_rate;

						this.rank4_coin_ticker = response.data[x].rank4_coin_ticker;
						this.rank4_coin_name = response.data[x].rank4_coin_name;
						this.rank4_coin_particle = response.data[x].rank4_coin_particle;
						this.rank4_o_price = response.data[x].rank4_o_price;
						this.rank4_l_price = response.data[x].rank4_l_price;
						this.rank4_h_price = response.data[x].rank4_h_price;
						this.rank4_c_price = response.data[x].rank4_c_price;
						this.rank4_o_c_price = response.data[x].rank4_o_c_price;
						this.rank4_o_c_price_rate = response.data[x].rank4_o_c_price_rate;
						this.rank4_l_h_price = response.data[x].rank4_l_h_price;
						this.rank4_l_h_price_rate = response.data[x].rank4_l_h_price_rate;
						
						this.rank5_coin_ticker = response.data[x].rank5_coin_ticker;
						this.rank5_coin_name = response.data[x].rank5_coin_name;
						this.rank5_coin_particle = response.data[x].rank5_coin_particle;
						this.rank5_o_price = response.data[x].rank5_o_price;
						this.rank5_l_price = response.data[x].rank5_l_price;
						this.rank5_h_price = response.data[x].rank5_h_price;
						this.rank5_c_price = response.data[x].rank5_c_price;
						this.rank5_o_c_price = response.data[x].rank5_o_c_price;
						this.rank5_o_c_price_rate = response.data[x].rank5_o_c_price_rate;
						this.rank5_l_h_price = response.data[x].rank5_l_h_price;
						this.rank5_l_h_price_rate = response.data[x].rank5_l_h_price_rate;

						this.getYoutubeShortMake();
					}
				}
				this.isLoading = false;
			})
		},
		onRowClicked(params) {
			console.log(params)
			this.search_date = params.data.yyyymmdd;
			this.search_market = params.data.market;

			this.$router.push('/Short/'+this.search_date+'/'+this.search_market)

			this.btc_coin_ticker = params.data.btc_coin_ticker;
			this.btc_coin_name = params.data.btc_coin_name;
			this.btc_coin_particle = params.data.btc_coin_particle;
			this.btc_o_price = params.data.btc_o_price;
			this.btc_l_price = params.data.btc_l_price;
			this.btc_h_price = params.data.btc_h_price;
			this.btc_c_price = params.data.btc_c_price;
			this.btc_o_c_price = params.data.btc_o_c_price;
			this.btc_o_c_price_rate = params.data.btc_o_c_price_rate;
			this.btc_l_h_price = params.data.btc_l_h_price;
			this.btc_l_h_price_rate = params.data.btc_l_h_price_rate;

			this.rank1_coin_ticker = params.data.rank1_coin_ticker;
			this.rank1_coin_name = params.data.rank1_coin_name;
			this.rank1_coin_particle = params.data.rank1_coin_particle;
			this.rank1_o_price = params.data.rank1_o_price;
			this.rank1_l_price = params.data.rank1_l_price;
			this.rank1_h_price = params.data.rank1_h_price;
			this.rank1_c_price = params.data.rank1_c_price;
			this.rank1_o_c_price = params.data.rank1_o_c_price;
			this.rank1_o_c_price_rate = params.data.rank1_o_c_price_rate;
			this.rank1_l_h_price = params.data.rank1_l_h_price;
			this.rank1_l_h_price_rate = params.data.rank1_l_h_price_rate;

			if(this.rank1_o_price.substring(this.rank1_o_price.length-2, this.rank1_o_price.length) == '.0'){
				this.rank1_o_price = this.rank1_o_price.slice(0, -2);
			}
			if(this.rank1_l_price.substring(this.rank1_l_price.length-2, this.rank1_l_price.length) == '.0'){
				this.rank1_l_price = this.rank1_l_price.slice(0, -2);
			}
			if(this.rank1_h_price.substring(this.rank1_h_price.length-2, this.rank1_h_price.length) == '.0'){
				this.rank1_h_price = this.rank1_h_price.slice(0, -2);
			}
			if(this.rank1_c_price.substring(this.rank1_c_price.length-2, this.rank1_c_price.length) == '.0'){
				this.rank1_c_price = this.rank1_c_price.slice(0, -2);
			}

			this.rank2_coin_ticker = params.data.rank2_coin_ticker;
			this.rank2_coin_name = params.data.rank2_coin_name;
			this.rank2_coin_particle = params.data.rank2_coin_particle;
			this.rank2_o_price = params.data.rank2_o_price;
			this.rank2_l_price = params.data.rank2_l_price;
			this.rank2_h_price = params.data.rank2_h_price;
			this.rank2_c_price = params.data.rank2_c_price;
			this.rank2_o_c_price = params.data.rank2_o_c_price;
			this.rank2_o_c_price_rate = params.data.rank2_o_c_price_rate;
			this.rank2_l_h_price = params.data.rank2_l_h_price;
			this.rank2_l_h_price_rate = params.data.rank2_l_h_price_rate;
			
			if(this.rank2_o_price.substring(this.rank2_o_price.length-2, this.rank2_o_price.length) == '.0'){
				this.rank2_o_price = this.rank2_o_price.slice(0, -2);
			}
			if(this.rank2_l_price.substring(this.rank2_l_price.length-2, this.rank2_l_price.length) == '.0'){
				this.rank2_l_price = this.rank2_l_price.slice(0, -2);
			}
			if(this.rank2_h_price.substring(this.rank2_h_price.length-2, this.rank2_h_price.length) == '.0'){
				this.rank2_h_price = this.rank2_h_price.slice(0, -2);
			}
			if(this.rank2_c_price.substring(this.rank2_c_price.length-2, this.rank2_c_price.length) == '.0'){
				this.rank2_c_price = this.rank2_c_price.slice(0, -2);
			}

			this.rank3_coin_ticker = params.data.rank3_coin_ticker;
			this.rank3_coin_name = params.data.rank3_coin_name;
			this.rank3_coin_particle = params.data.rank3_coin_particle;
			this.rank3_o_price = params.data.rank3_o_price;
			this.rank3_l_price = params.data.rank3_l_price;
			this.rank3_h_price = params.data.rank3_h_price;
			this.rank3_c_price = params.data.rank3_c_price;
			this.rank3_o_c_price = params.data.rank3_o_c_price;
			this.rank3_o_c_price_rate = params.data.rank3_o_c_price_rate;
			this.rank3_l_h_price = params.data.rank3_l_h_price;
			this.rank3_l_h_price_rate = params.data.rank3_l_h_price_rate;

			if(this.rank3_o_price.substring(this.rank3_o_price.length-2, this.rank3_o_price.length) == '.0'){
				this.rank3_o_price = this.rank3_o_price.slice(0, -2);
			}
			if(this.rank3_l_price.substring(this.rank3_l_price.length-2, this.rank3_l_price.length) == '.0'){
				this.rank3_l_price = this.rank3_l_price.slice(0, -2);
			}
			if(this.rank3_h_price.substring(this.rank3_h_price.length-2, this.rank3_h_price.length) == '.0'){
				this.rank3_h_price = this.rank3_h_price.slice(0, -2);
			}
			if(this.rank3_c_price.substring(this.rank3_c_price.length-2, this.rank3_c_price.length) == '.0'){
				this.rank3_c_price = this.rank3_c_price.slice(0, -2);
			}

			this.rank4_coin_ticker = params.data.rank4_coin_ticker;
			this.rank4_coin_name = params.data.rank4_coin_name;
			this.rank4_coin_particle = params.data.rank4_coin_particle;
			this.rank4_o_price = params.data.rank4_o_price;
			this.rank4_l_price = params.data.rank4_l_price;
			this.rank4_h_price = params.data.rank4_h_price;
			this.rank4_c_price = params.data.rank4_c_price;
			this.rank4_o_c_price = params.data.rank4_o_c_price;
			this.rank4_o_c_price_rate = params.data.rank4_o_c_price_rate;
			this.rank4_l_h_price = params.data.rank4_l_h_price;
			this.rank4_l_h_price_rate = params.data.rank4_l_h_price_rate;
			
			this.rank5_coin_ticker = params.data.rank5_coin_ticker;
			this.rank5_coin_name = params.data.rank5_coin_name;
			this.rank5_coin_particle = params.data.rank5_coin_particle;
			this.rank5_o_price = params.data.rank5_o_price;
			this.rank5_l_price = params.data.rank5_l_price;
			this.rank5_h_price = params.data.rank5_h_price;
			this.rank5_c_price = params.data.rank5_c_price;
			this.rank5_o_c_price = params.data.rank5_o_c_price;
			this.rank5_o_c_price_rate = params.data.rank5_o_c_price_rate;
			this.rank5_l_h_price = params.data.rank5_l_h_price;
			this.rank5_l_h_price_rate = params.data.rank5_l_h_price_rate;

			this.getYoutubeShortMake();
		},
		getYoutubeShortMake(){
			if(this.search_date != '' && this.search_market != ''){
				var korea_date = ''
				var short_title = ''
				//short_title = this.search_date + ' ';
				short_title = (this.search_date).substring(2, 4) + '년 ';

				if((this.search_date).substring(5, 6) == '0'){
					short_title += (this.search_date).substring(6, 7) + '월 '
				}else{
					short_title += (this.search_date).substring(5, 7) + '월 '
				}

				if((this.search_date).substring(8, 9) == '0'){
					short_title += (this.search_date).substring(9, 10) +'일';
				}else{
					short_title += (this.search_date).substring(8, 10) +'일';
				}

				korea_date = short_title

				if(this.search_market == 'upbit'){
					short_title += '<br/>업비트 '
				}else if(this.search_market == 'bithumb'){
					short_title += '<br/>빗썸 '
				}
				short_title += '코인 순위'

				this.btc_o_c_price_rate = this.zeroCut(this.btc_o_c_price_rate)
				this.btc_l_h_price_rate = this.zeroCut(this.btc_l_h_price_rate)

				this.rank5_o_c_price_rate = this.zeroCut(this.rank5_o_c_price_rate)
				this.rank5_l_h_price_rate = this.zeroCut(this.rank5_l_h_price_rate)

				this.rank4_o_c_price_rate = this.zeroCut(this.rank4_o_c_price_rate)
				this.rank4_l_h_price_rate = this.zeroCut(this.rank4_l_h_price_rate)

				this.rank3_o_c_price_rate = this.zeroCut(this.rank3_o_c_price_rate)
				this.rank3_l_h_price_rate = this.zeroCut(this.rank3_l_h_price_rate)

				this.rank2_o_c_price_rate = this.zeroCut(this.rank2_o_c_price_rate)
				this.rank2_l_h_price_rate = this.zeroCut(this.rank2_l_h_price_rate)

				this.rank1_o_c_price_rate = this.zeroCut(this.rank1_o_c_price_rate)
				this.rank1_l_h_price_rate = this.zeroCut(this.rank1_l_h_price_rate)

				this.short_html = '<div id="short_title" style="color:black; width:500px; padding-top:50px; padding-bottom:50px;">'
				this.short_html += '<h1 style="text-align:center; vertical-align:middle; line-height:1.5; font-size:36px;">'+short_title+'</h1>';
				this.short_html += '</div>'
				//this.short_html += '<br/><br/>';
				this.short_html += '<div style="color:black;">';
				this.short_html += short_title.replaceAll('<br/><br/>', ' ').replaceAll('<br/>', ' ')+'를 알아보겠습니다.';
				//this.short_html += '<br/><br/>';
				//this.short_html += '기준이 되는 '+this.btc_coin_name+'는 '+this.btc_o_price+'원으로 시작해 젓가 '+this.btc_l_price+'원, 곳가 '+this.btc_h_price+'원을 기록하고 '+this.btc_c_price+'원으로 마감하였고, 시까대비종까는 '+this.btc_o_c_price_rate+'% 상승하였고, 젓가대비곳가는 '+this.btc_l_h_price_rate+'% 상승 하였습니다.';
				//this.short_html += '<br/><br/>';
				//this.short_html += '상승율 5위는 '+this.rank5_coin_name+this.rank5_coin_particle+' '+this.rank5_o_price+'원으로 시작해 젓가 '+this.rank5_l_price+'원, 곳가 '+this.rank5_h_price+'원을 기록하고 '+this.rank5_c_price+'원으로 마감하였고, 시까대비종까는 '+this.rank5_o_c_price_rate+'% 상승하였고, 젓가대비곳가는 '+this.rank5_l_h_price_rate+'% 상승 하였습니다.';
				//this.short_html += '<br/><br/>';
				//this.short_html += '상승율 4위는 '+this.rank4_coin_name+this.rank4_coin_particle+' '+this.rank4_o_price+'원으로 시작해 젓가 '+this.rank4_l_price+'원, 곳가 '+this.rank4_h_price+'원을 기록하고 '+this.rank4_c_price+'원으로 마감하였고, 시까대비종까는 '+this.rank4_o_c_price_rate+'% 상승하였고, 젓가대비곳가는 '+this.rank4_l_h_price_rate+'% 상승 하였습니다.';
				this.short_html += '<br/><br/>';
				if(Number(this.rank3_o_c_price_rate) > 0){
					this.short_html += '상승율 '
				}else if(Number(this.rank3_o_c_price_rate) < 0){
					this.short_html += '최저 하락율 '
				}
				this.short_html += ' 3위는 '+this.rank3_coin_name+this.rank3_coin_particle+' '+this.rank3_o_price+'원으로 시작해 젓가 '+this.rank3_l_price+'원, 곳가 '+this.rank3_h_price+'원을 기록하고 '+this.rank3_c_price+'원으로 마감하였고, 시까대비종까는 '+this.rank3_o_c_price_rate+'% '
				if(Number(this.rank3_o_c_price_rate) == 0){
					this.short_html += '보합이였고, '
				}else if(Number(this.rank3_o_c_price_rate) > 0){
					this.short_html += '상승하였고, '
				}else if(Number(this.rank3_o_c_price_rate) < 0){
					this.short_html += '하락하였고, '
				}
				this.short_html += '젓가대비곳가는 '+this.rank3_l_h_price_rate+'% 상승 하였습니다.';
				this.short_html += '<br/><br/>';
				if(Number(this.rank2_o_c_price_rate) > 0){
					this.short_html += '상승율 '
				}else if(Number(this.rank2_o_c_price_rate) < 0){
					this.short_html += '최저 하락율 '
				}
				this.short_html += ' 2위는 '+this.rank2_coin_name+this.rank2_coin_particle+' '+this.rank2_o_price+'원으로 시작해 젓가 '+this.rank2_l_price+'원, 곳가 '+this.rank2_h_price+'원을 기록하고 '+this.rank2_c_price+'원으로 마감하였고, 시까대비종까는 '+this.rank2_o_c_price_rate+'% '
				if(Number(this.rank2_o_c_price_rate) == 0){
					this.short_html += '보합이였고, '
				}else if(Number(this.rank2_o_c_price_rate) > 0){
					this.short_html += '상승하였고, '
				}else if(Number(this.rank2_o_c_price_rate) < 0){
					this.short_html += '하락하였고, '
				}
				this.short_html += '젓가대비곳가는 '+this.rank2_l_h_price_rate+'% 상승 하였습니다.';
				this.short_html += '<br/><br/>';
				if(Number(this.rank1_o_c_price_rate) > 0){
					this.short_html += '상승율 '
				}else if(Number(this.rank1_o_c_price_rate) < 0){
					this.short_html += '최저 하락율 '
				}
				this.short_html += ' 1위는 '+this.rank1_coin_name+this.rank1_coin_particle+' '+this.rank1_o_price+'원으로 시작해 젓가 '+this.rank1_l_price+'원, 곳가 '+this.rank1_h_price+'원을 기록하고 '+this.rank1_c_price+'원으로 마감하였고, 시까대비종까는 '+this.rank1_o_c_price_rate+'% '
				if(Number(this.rank1_o_c_price_rate) == 0){
					this.short_html += '보합이였고, '
				}else if(Number(this.rank1_o_c_price_rate) > 0){
					this.short_html += '상승하였고, '
				}else if(Number(this.rank1_o_c_price_rate) < 0){
					this.short_html += '하락하였고, '
				}
				this.short_html += '젓가대비곳가는 '+this.rank1_l_h_price_rate+'% 상승 하였습니다.';
				this.short_html += '<br/><br/>';
				//this.short_html += '영상 시청 감사드리며, 좋아요와 구독 부탁 드립니다.';
				//this.short_html += '<br/><br/>';
				this.short_html += '영상 시청 감사드리며, 유튜브 코인분석기 채널 방문 부탁드립니다.';
				this.short_html += '<br/><br/>';

				this.short_html += korea_date+' 코인 순위를 알아보겠습니다.';
				this.short_html += '<br/>'
				if(this.search_market == 'upbit'){
					this.short_html += '업비트 '
				}else if(this.search_market == 'bithumb'){
					this.short_html += '빗썸 '
				}
				if(Number(this.rank3_o_c_price_rate) > 0){
					this.short_html += '상승율 '
				}else if(Number(this.rank3_o_c_price_rate) < 0){
					this.short_html += '최저 하락율 '
				}
				this.short_html += ' 3위는 '+this.rank3_coin_name+this.rank3_coin_particle+' 시까대비종까는 '+this.rank3_o_c_price_rate+'% '
				if(Number(this.rank3_o_c_price_rate) == 0){
					this.short_html += '보합이였고, '
				}else if(Number(this.rank3_o_c_price_rate) > 0){
					this.short_html += '상승하였고, '
				}else if(Number(this.rank3_o_c_price_rate) < 0){
					this.short_html += '하락하였고, '
				}
				this.short_html += '젓가대비곳가는 '+this.rank3_l_h_price_rate+'% 상승 하였습니다.';
				this.short_html += '<br/>';

				if(this.search_market == 'upbit'){
					this.short_html += '업비트 '
				}else if(this.search_market == 'bithumb'){
					this.short_html += '빗썸 '
				}
				if(Number(this.rank2_o_c_price_rate) > 0){
					this.short_html += '상승율 '
				}else if(Number(this.rank2_o_c_price_rate) < 0){
					this.short_html += '최저 하락율 '
				}
				this.short_html += ' 2위는 '+this.rank2_coin_name+this.rank2_coin_particle+' 시까대비종까는 '+this.rank2_o_c_price_rate+'% '
				if(Number(this.rank2_o_c_price_rate) == 0){
					this.short_html += '보합이였고, '
				}else if(Number(this.rank2_o_c_price_rate) > 0){
					this.short_html += '상승하였고, '
				}else if(Number(this.rank2_o_c_price_rate) < 0){
					this.short_html += '하락하였고, '
				}
				this.short_html += '젓가대비곳가는 '+this.rank2_l_h_price_rate+'% 상승 하였습니다.';
				this.short_html += '<br/>';

				if(this.search_market == 'upbit'){
					this.short_html += '업비트 '
				}else if(this.search_market == 'bithumb'){
					this.short_html += '빗썸 '
				}
				if(Number(this.rank1_o_c_price_rate) > 0){
					this.short_html += '상승율 '
				}else if(Number(this.rank1_o_c_price_rate) < 0){
					this.short_html += '최저 하락율 '
				}
				this.short_html += ' 1위는 '+this.rank1_coin_name+this.rank1_coin_particle+' 시까대비종까는 '+this.rank1_o_c_price_rate+'% '
				if(Number(this.rank1_o_c_price_rate) == 0){
					this.short_html += '보합이였고, '
				}else if(Number(this.rank1_o_c_price_rate) > 0){
					this.short_html += '상승하였고, '
				}else if(Number(this.rank1_o_c_price_rate) < 0){
					this.short_html += '하락하였고, '
				}
				this.short_html += '젓가대비곳가는 '+this.rank1_l_h_price_rate+'% 상승 하였습니다.';
				this.short_html += '<br/>';
				this.short_html += '영상 시청 감사드리며, 좋아요와 구독 부탁 드립니다.';
				this.short_html += '<br/><br/>';
				this.short_html += '</div>';

				/* 아래에서부터는 돈표시가 되어야함, 근데 소수점 이하는 돈표시하면 소수점이 이상하게 나오는 경우가 발생하여 999원 기준으로 미리 수정함. */
				if(Number(this.btc_o_price) > 999){
					this.btc_o_price = Number(this.btc_o_price).toLocaleString('ko-KR')
				}else{
					this.btc_o_price = this.btc_o_price
				}

				if(Number(this.btc_l_price) > 999){
					this.btc_l_price = Number(this.btc_l_price).toLocaleString('ko-KR')
				}else{
					this.btc_l_price = this.btc_l_price
				}

				if(Number(this.btc_h_price) > 999){
					this.btc_h_price = Number(this.btc_h_price).toLocaleString('ko-KR')
				}else{
					this.btc_h_price = this.btc_h_price
				}

				if(Number(this.btc_c_price) > 999){
					this.btc_c_price = Number(this.btc_c_price).toLocaleString('ko-KR')
				}else{
					this.btc_c_price = this.btc_c_price
				}

				if(Math.abs(this.btc_o_c_price) > 999){
					this.btc_o_c_price = Number(this.btc_o_c_price).toLocaleString('ko-KR')
				}else{
					this.btc_o_c_price = this.btc_o_c_price
				}

				if(Math.abs(this.btc_l_h_price) > 999){
					this.btc_l_h_price = Number(this.btc_l_h_price).toLocaleString('ko-KR')
				}else{
					this.btc_l_h_price = this.btc_l_h_price
				}

				if(Number(this.rank5_o_price) > 999){
					this.rank5_o_price = Number(this.rank5_o_price).toLocaleString('ko-KR')
				}else{
					this.rank5_o_price = this.rank5_o_price
				}

				if(Number(this.rank5_l_price) > 999){
					this.rank5_l_price = Number(this.rank5_l_price).toLocaleString('ko-KR')
				}else{
					this.rank5_l_price = this.rank5_l_price
				}

				if(Number(this.rank5_h_price) > 999){
					this.rank5_h_price = Number(this.rank5_h_price).toLocaleString('ko-KR')
				}else{
					this.rank5_h_price = this.rank5_h_price
				}

				if(Number(this.rank5_c_price) > 999){
					this.rank5_c_price = Number(this.rank5_c_price).toLocaleString('ko-KR')
				}else{
					this.rank5_c_price = this.rank5_c_price
				}

				if(Math.abs(this.rank5_o_c_price) > 999){
					this.rank5_o_c_price = Number(this.rank5_o_c_price).toLocaleString('ko-KR')
				}else{
					this.rank5_o_c_price = this.rank5_o_c_price
				}

				if(Math.abs(this.rank5_l_h_price) > 999){
					this.rank5_l_h_price = Number(this.rank5_l_h_price).toLocaleString('ko-KR')
				}else{
					this.rank5_l_h_price = this.rank5_l_h_price
				}

				if(Number(this.rank5_o_price) > 999){
					this.rank5_o_price = Number(this.rank5_o_price).toLocaleString('ko-KR')
				}else{
					this.rank5_o_price = this.rank5_o_price
				}

				if(Number(this.rank5_l_price) > 999){
					this.rank5_l_price = Number(this.rank5_l_price).toLocaleString('ko-KR')
				}else{
					this.rank5_l_price = this.rank5_l_price
				}

				if(Number(this.rank5_h_price) > 999){
					this.rank5_h_price = Number(this.rank5_h_price).toLocaleString('ko-KR')
				}else{
					this.rank5_h_price = this.rank5_h_price
				}

				if(Number(this.rank5_c_price) > 999){
					this.rank5_c_price = Number(this.rank5_c_price).toLocaleString('ko-KR')
				}else{
					this.rank5_c_price = this.rank5_c_price
				}

				if(Math.abs(this.rank5_o_c_price) > 999){
					this.rank5_o_c_price = Number(this.rank5_o_c_price).toLocaleString('ko-KR')
				}else{
					this.rank5_o_c_price = this.rank5_o_c_price
				}

				if(Math.abs(this.rank5_l_h_price) > 999){
					this.rank5_l_h_price = Number(this.rank5_l_h_price).toLocaleString('ko-KR')
				}else{
					this.rank5_l_h_price = this.rank5_l_h_price
				}

				if(Number(this.rank4_o_price) > 999){
					this.rank4_o_price = Number(this.rank4_o_price).toLocaleString('ko-KR')
				}else{
					this.rank4_o_price = this.rank4_o_price
				}

				if(Number(this.rank4_l_price) > 999){
					this.rank4_l_price = Number(this.rank4_l_price).toLocaleString('ko-KR')
				}else{
					this.rank4_l_price = this.rank4_l_price
				}

				if(Number(this.rank4_h_price) > 999){
					this.rank4_h_price = Number(this.rank4_h_price).toLocaleString('ko-KR')
				}else{
					this.rank4_h_price = this.rank4_h_price
				}

				if(Number(this.rank4_c_price) > 999){
					this.rank4_c_price = Number(this.rank4_c_price).toLocaleString('ko-KR')
				}else{
					this.rank4_c_price = this.rank4_c_price
				}

				if(Math.abs(this.rank4_o_c_price) > 999){
					this.rank4_o_c_price = Number(this.rank4_o_c_price).toLocaleString('ko-KR')
				}else{
					this.rank4_o_c_price = this.rank4_o_c_price
				}

				if(Math.abs(this.rank4_l_h_price) > 999){
					this.rank4_l_h_price = Number(this.rank4_l_h_price).toLocaleString('ko-KR')
				}else{
					this.rank4_l_h_price = this.rank4_l_h_price
				}

				if(Number(this.rank3_o_price) > 999){
					this.rank3_o_price = Number(this.rank3_o_price).toLocaleString('ko-KR')
				}else{
					this.rank3_o_price = this.rank3_o_price
				}

				if(Number(this.rank3_l_price) > 999){
					this.rank3_l_price = Number(this.rank3_l_price).toLocaleString('ko-KR')
				}else{
					this.rank3_l_price = this.rank3_l_price
				}

				if(Number(this.rank3_h_price) > 999){
					this.rank3_h_price = Number(this.rank3_h_price).toLocaleString('ko-KR')
				}else{
					this.rank3_h_price = this.rank3_h_price
				}

				if(Number(this.rank3_c_price) > 999){
					this.rank3_c_price = Number(this.rank3_c_price).toLocaleString('ko-KR')
				}else{
					this.rank3_c_price = this.rank3_c_price
				}

				if(Math.abs(this.rank3_o_c_price) > 999){
					this.rank3_o_c_price = Number(this.rank3_o_c_price).toLocaleString('ko-KR')
				}else{
					this.rank3_o_c_price = this.rank3_o_c_price
				}

				if(Math.abs(this.rank3_l_h_price) > 999){
					this.rank3_l_h_price = Number(this.rank3_l_h_price).toLocaleString('ko-KR')
				}else{
					this.rank3_l_h_price = this.rank3_l_h_price
				}

				if(Number(this.rank2_o_price) > 999){
					this.rank2_o_price = Number(this.rank2_o_price).toLocaleString('ko-KR')
				}else{
					this.rank2_o_price = this.rank2_o_price
				}

				if(Number(this.rank2_l_price) > 999){
					this.rank2_l_price = Number(this.rank2_l_price).toLocaleString('ko-KR')
				}else{
					this.rank2_l_price = this.rank2_l_price
				}

				if(Number(this.rank2_h_price) > 999){
					this.rank2_h_price = Number(this.rank2_h_price).toLocaleString('ko-KR')
				}else{
					this.rank2_h_price = this.rank2_h_price
				}

				if(Number(this.rank2_c_price) > 999){
					this.rank2_c_price = Number(this.rank2_c_price).toLocaleString('ko-KR')
				}else{
					this.rank2_c_price = this.rank2_c_price
				}

				if(Math.abs(this.rank2_o_c_price) > 999){
					this.rank2_o_c_price = Number(this.rank2_o_c_price).toLocaleString('ko-KR')
				}else{
					this.rank2_o_c_price = this.rank2_o_c_price
				}

				if(Math.abs(this.rank2_l_h_price) > 999){
					this.rank2_l_h_price = Number(this.rank2_l_h_price).toLocaleString('ko-KR')
				}else{
					this.rank2_l_h_price = this.rank2_l_h_price
				}

				if(Number(this.rank1_o_price) > 999){
					this.rank1_o_price = Number(this.rank1_o_price).toLocaleString('ko-KR')
				}else{
					this.rank1_o_price = this.rank1_o_price
				}

				if(Number(this.rank1_l_price) > 999){
					this.rank1_l_price = Number(this.rank1_l_price).toLocaleString('ko-KR')
				}else{
					this.rank1_l_price = this.rank1_l_price
				}

				if(Number(this.rank1_h_price) > 999){
					this.rank1_h_price = Number(this.rank1_h_price).toLocaleString('ko-KR')
				}else{
					this.rank1_h_price = this.rank1_h_price
				}

				if(Number(this.rank1_c_price) > 999){
					this.rank1_c_price = Number(this.rank1_c_price).toLocaleString('ko-KR')
				}else{
					this.rank1_c_price = this.rank1_c_price
				}

				if(Math.abs(this.rank1_o_c_price) > 999){
					this.rank1_o_c_price = Number(this.rank1_o_c_price).toLocaleString('ko-KR')
				}else{
					this.rank1_o_c_price = this.rank1_o_c_price
				}

				if(Math.abs(this.rank1_l_h_price) > 999){
					this.rank1_l_h_price = Number(this.rank1_l_h_price).toLocaleString('ko-KR')
				}else{
					this.rank1_l_h_price = this.rank1_l_h_price
				}

				/* BTC */
				this.short_html += '<table id="btc_short" style="color:black; border-spacing:0px; border-color:black; font-size:16px; padding: 20px;">';
				this.short_html += '<colgroup>';
				this.short_html += '<col style="width: 50px">';
				this.short_html += '<col style="width: 50px">';
				this.short_html += '<col style="width: 140px">';
				this.short_html += '</colgroup>';
				this.short_html += '<tbody>';
				this.short_html += '<tr style="border: 1px solid black;">';
				this.short_html += '<td style="height:60px; border: 1px solid black; border-left:3px solid black; border-right: 3px solid black; border-top:3px solid black; font-weight:bold; font-size: 22px; text-align:center;" colspan="3">'+this.btc_coin_name+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">시가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.btc_o_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">저가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.btc_l_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">고가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.btc_h_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">종가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.btc_c_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:25px; border: 1px solid black; border-left: 3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2" rowspan="2">시가대비종가</td>';
				if(Number(this.btc_o_c_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; padding-right:3px; font-size:18px; color:red;">'+this.btc_o_c_price+'</td>';
				}else if(Number(this.btc_o_c_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; padding-right:3px; font-size:18px; color:blue;">'+this.btc_o_c_price+'</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; padding-right:3px; font-size:18px; color:black;">'+this.btc_o_c_price+'</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				if(Number(this.btc_o_c_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:red;">'+this.btc_o_c_price_rate+'%</td>';
				}else if(Number(this.btc_o_c_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:blue;">'+this.btc_o_c_price_rate+'%</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:black;">'+this.btc_o_c_price_rate+'%</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:25px; border: 1px solid black; border-left: 3px solid black; border-bottom: 3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2" rowspan="2">저가대비고가</td>';
				if(Number(this.btc_l_h_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:red;">'+this.btc_l_h_price+'</td>';
				}else if(Number(this.btc_l_h_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:blue;">'+this.btc_l_h_price+'</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:black;">'+this.btc_l_h_price+'</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				if(Number(this.btc_l_h_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-bottom: 3px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:red;">'+this.btc_l_h_price_rate+'%</td>';
				}else if(Number(this.btc_l_h_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-bottom: 3px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:blue;">'+this.btc_l_h_price_rate+'%</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-bottom: 3px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:black;">'+this.btc_l_h_price_rate+'%</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '</table>';

				this.short_html += '<br/><br/>';
				/* RANK5 */
				this.short_html += '<table id="rank5_short" style="color:black; border-spacing:0px; border-color:black; font-size:16px; padding: 20px;">';
				this.short_html += '<colgroup>';
				this.short_html += '<col style="width: 50px">';
				this.short_html += '<col style="width: 50px">';
				this.short_html += '<col style="width: 140px">';
				this.short_html += '</colgroup>';
				this.short_html += '<tbody>';
				this.short_html += '<tr style="border: 1px solid black;">';
				this.short_html += '<td style="height:60px; border: 1px solid black; border-left:3px solid black; border-right: 3px solid black; border-top:3px solid black; font-weight:bold; font-size: 22px; text-align:center;" colspan="3">'+this.rank5_coin_name+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">시가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-top:0px solid black; border-right: 3px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank5_o_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">저가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-top:0px solid black; border-right: 3px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank5_l_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">고가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-top:0px solid black; border-right: 3px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank5_h_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">종가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-top:0px solid black; border-right: 3px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank5_c_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:25px; border: 1px solid black; border-left: 3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2" rowspan="2">시가대비종가</td>';
				if(Number(this.rank5_o_c_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; padding-right:3px; font-size:18px; color:red;">'+this.rank5_o_c_price+'</td>';
				}else if(Number(this.rank5_o_c_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; padding-right:3px; font-size:18px; color:blue;">'+this.rank5_o_c_price+'</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; padding-right:3px; font-size:18px; color:black;">'+this.rank5_o_c_price+'</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				if(Number(this.rank5_o_c_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:red;">'+this.rank5_o_c_price_rate+'%</td>';
				}else if(Number(this.rank5_o_c_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:blue;">'+this.rank5_o_c_price_rate+'%</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:black;">'+this.rank5_o_c_price_rate+'%</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:25px; border: 1px solid black; border-left: 3px solid black; border-bottom: 3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2" rowspan="2">저가대비고가</td>';
				if(Number(this.rank5_l_h_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-top:0px solid black; border-right: 3px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:red;">'+this.rank5_l_h_price+'</td>';
				}else if(Number(this.rank5_l_h_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-top:0px solid black; border-right: 3px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:blue;">'+this.rank5_l_h_price+'</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-top:0px solid black; border-right: 3px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:black;">'+this.rank5_l_h_price+'</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				if(Number(this.rank5_l_h_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-bottom: 3px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:red;">'+this.rank5_l_h_price_rate+'%</td>';
				}else if(Number(this.rank5_l_h_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-bottom: 3px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:blue;">'+this.rank5_l_h_price_rate+'%</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-bottom: 3px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:black;">'+this.rank5_l_h_price_rate+'%</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '</table>';
				
				this.short_html += '<br/><br/>';
				/* RANK4 */
				this.short_html += '<table id="rank4_short" style="color:black; border-spacing:0px; border-color:black; font-size:16px; padding: 20px;">';
				this.short_html += '<colgroup>';
				this.short_html += '<col style="width: 50px">';
				this.short_html += '<col style="width: 50px">';
				this.short_html += '<col style="width: 140px">';
				this.short_html += '</colgroup>';
				this.short_html += '<tbody>';
				this.short_html += '<tr style="border: 1px solid black;">';
				this.short_html += '<td style="height:60px; border: 1px solid black; border-right: 3px solid black; border-left:3px solid black; border-top:3px solid black; font-weight:bold; font-size: 22px; text-align:center;" colspan="3">'+this.rank4_coin_name+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">시가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank4_o_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">저가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank4_l_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">고가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank4_h_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">종가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank4_c_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:25px; border: 1px solid black; border-left: 3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2" rowspan="2">시가대비종가</td>';
				if(Number(this.rank4_o_c_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; font-size:18px; padding-right:3px; color:red;">'+this.rank4_o_c_price+'</td>';
				}else if(Number(this.rank4_o_c_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; font-size:18px; padding-right:3px; color:blue;">'+this.rank4_o_c_price+'</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; font-size:18px; padding-right:3px; color:black;">'+this.rank4_o_c_price+'</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				if(Number(this.rank4_o_c_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:red;">'+this.rank4_o_c_price_rate+'%</td>';
				}else if(Number(this.rank4_o_c_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:blue;">'+this.rank4_o_c_price_rate+'%</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:black;">'+this.rank4_o_c_price_rate+'%</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:25px; border: 1px solid black; border-left: 3px solid black; border-bottom: 3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2" rowspan="2">저가대비고가</td>';
				if(Number(this.rank4_l_h_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:red;">'+this.rank4_l_h_price+'</td>';
				}else if(Number(this.rank4_l_h_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:blue;">'+this.rank4_l_h_price+'</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:black;">'+this.rank4_l_h_price+'</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				if(Number(this.rank4_l_h_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom: 3px solid black; text-align:right; padding-right:3px; font-size:15px; color:red;">'+this.rank4_l_h_price_rate+'%</td>';
				}else if(Number(this.rank4_l_h_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom: 3px solid black; text-align:right; padding-right:3px; font-size:15px; color:blue;">'+this.rank4_l_h_price_rate+'%</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom: 3px solid black; text-align:right; padding-right:3px; font-size:15px; color:black;">'+this.rank4_l_h_price_rate+'%</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '</table>';
				
				this.short_html += '<br/><br/>';
				/* RANK3 */
				this.short_html += '<table id="rank3_short" style="color:black; border-spacing:0px; border-color:black; font-size:16px; padding: 20px;">';
				this.short_html += '<colgroup>';
				this.short_html += '<col style="width: 50px">';
				this.short_html += '<col style="width: 50px">';
				this.short_html += '<col style="width: 140px">';
				this.short_html += '</colgroup>';
				this.short_html += '<tbody>';
				this.short_html += '<tr style="border: 1px solid black;">';
				this.short_html += '<td style="height:60px; border: 1px solid black; border-left:3px solid black; border-right: 3px solid black; border-top:3px solid black; font-weight:bold; font-size: 22px; text-align:center;" colspan="3">'+this.rank3_coin_name+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">시가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank3_o_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">저가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank3_l_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">고가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank3_h_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">종가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank3_c_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:25px; border: 1px solid black; border-left: 3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2" rowspan="2">시가대비종가</td>';
				if(Number(this.rank3_o_c_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; font-size:18px; padding-right:3px; color:red;">'+this.rank3_o_c_price+'</td>';
				}else if(Number(this.rank3_o_c_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; font-size:18px; padding-right:3px; color:blue;">'+this.rank3_o_c_price+'</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; font-size:18px; padding-right:3px; color:black;">'+this.rank3_o_c_price+'</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				if(Number(this.rank3_o_c_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:red;">'+this.rank3_o_c_price_rate+'%</td>';
				}else if(Number(this.rank3_o_c_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:blue;">'+this.rank3_o_c_price_rate+'%</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:black;">'+this.rank3_o_c_price_rate+'%</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:25px; border: 1px solid black; border-left: 3px solid black; border-bottom: 3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2" rowspan="2">저가대비고가</td>';
				if(Number(this.rank3_l_h_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:red;">'+this.rank3_l_h_price+'</td>';
				}else if(Number(this.rank3_l_h_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:blue;">'+this.rank3_l_h_price+'</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:black;">'+this.rank3_l_h_price+'</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				if(Number(this.rank3_l_h_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-bottom: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:red;">'+this.rank3_l_h_price_rate+'%</td>';
				}else if(Number(this.rank3_l_h_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-bottom: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:blue;">'+this.rank3_l_h_price_rate+'%</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-bottom: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:black;">'+this.rank3_l_h_price_rate+'%</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '</table>';

				this.short_html += '<br/><br/>';
				/* RANK2 */
				this.short_html += '<table id="rank2_short" style="color:black; border-spacing:0px; border-color:black; font-size:16px; padding: 20px;">';
				this.short_html += '<colgroup>';
				this.short_html += '<col style="width: 50px">';
				this.short_html += '<col style="width: 50px">';
				this.short_html += '<col style="width: 140px">';
				this.short_html += '</colgroup>';
				this.short_html += '<tbody>';
				this.short_html += '<tr style="border: 1px solid black;">';
				this.short_html += '<td style="height:60px; border: 1px solid black; border-right: 3px solid black; border-left:3px solid black; border-top:3px solid black; font-weight:bold; font-size: 22px; text-align:center;" colspan="3">'+this.rank2_coin_name+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">시가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank2_o_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">저가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank2_l_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">고가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank2_h_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">종가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank2_c_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:25px; border: 1px solid black; border-left: 3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2" rowspan="2">시가대비종가</td>';
				if(Number(this.rank2_o_c_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; padding-right:3px; font-size:18px; color:red;">'+this.rank2_o_c_price+'</td>';
				}else if(Number(this.rank2_o_c_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; padding-right:3px; font-size:18px; color:blue;">'+this.rank2_o_c_price+'</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; padding-right:3px; font-size:18px; color:black;">'+this.rank2_o_c_price+'</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				if(Number(this.rank2_o_c_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:red;">'+this.rank2_o_c_price_rate+'%</td>';
				}else if(Number(this.rank2_o_c_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:blue;">'+this.rank2_o_c_price_rate+'%</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:black;">'+this.rank2_o_c_price_rate+'%</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:25px; border: 1px solid black; border-left: 3px solid black; border-bottom: 3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2" rowspan="2">저가대비고가</td>';
				if(Number(this.rank2_l_h_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:red;">'+this.rank2_l_h_price+'</td>';
				}else if(Number(this.rank2_l_h_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:blue;">'+this.rank2_l_h_price+'</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:black;">'+this.rank2_l_h_price+'</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				if(Number(this.rank2_l_h_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-bottom: 3px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:red;">'+this.rank2_l_h_price_rate+'%</td>';
				}else if(Number(this.rank2_l_h_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-bottom: 3px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:blue;">'+this.rank2_l_h_price_rate+'%</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-bottom: 3px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:black;">'+this.rank2_l_h_price_rate+'%</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '</table>';

				this.short_html += '<br/><br/>';
				/* RANK1 */
				this.short_html += '<table id="rank1_short" style="color:black; border-spacing:0px; border-color:black; font-size:16px; padding: 20px;">';
				this.short_html += '<colgroup>';
				this.short_html += '<col style="width: 50px">';
				this.short_html += '<col style="width: 50px">';
				this.short_html += '<col style="width: 140px">';
				this.short_html += '</colgroup>';
				this.short_html += '<tbody>';
				this.short_html += '<tr style="border: 1px solid black;">';
				this.short_html += '<td style="height:60px; border: 1px solid black; border-left:3px solid black; border-right: 3px solid black; border-top:3px solid black; font-weight:bold; font-size: 22px; text-align:center;" colspan="3">'+this.rank1_coin_name+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">시가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank1_o_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">저가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank1_l_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">고가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank1_h_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-left:3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2">종가</td>';
				this.short_html += '<td style="height:45px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; font-size:20px; padding-right:3px;">'+this.rank1_c_price+'</td>';
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:25px; border: 1px solid black; border-left: 3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2" rowspan="2">시가대비종가</td>';
				if(Number(this.rank1_o_c_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; padding-right:3px; font-size:18px; color:red;">'+this.rank1_o_c_price+'</td>';
				}else if(Number(this.rank1_o_c_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; padding-right:3px; font-size:18px; color:blue;">'+this.rank1_o_c_price+'</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; solid black; text-align:right; padding-right:3px; font-size:18px; color:black;">'+this.rank1_o_c_price+'</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				if(Number(this.rank1_o_c_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:red;">'+this.rank1_o_c_price_rate+'%</td>';
				}else if(Number(this.rank1_o_c_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:blue;">'+this.rank1_o_c_price_rate+'%</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:black;">'+this.rank1_o_c_price_rate+'%</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				this.short_html += '<td style="height:25px; border: 1px solid black; border-left: 3px solid black; border-bottom: 3px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center;" colspan="2" rowspan="2">저가대비고가</td>';
				if(Number(this.rank1_l_h_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:red;">'+this.rank1_l_h_price+'</td>';
				}else if(Number(this.rank1_l_h_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:blue;">'+this.rank1_l_h_price+'</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-top:0px solid black; border-bottom:0px; text-align:right; padding-right:3px; font-size:18px; color:black;">'+this.rank1_l_h_price+'</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '<tr>';
				if(Number(this.rank1_l_h_price_rate) > 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-bottom: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:red;">'+this.rank1_l_h_price_rate+'%</td>';
				}else if(Number(this.rank1_l_h_price_rate) < 0){
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-bottom: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:blue;">'+this.rank1_l_h_price_rate+'%</td>';
				}else{
					this.short_html += '<td style="height:25px; border: 1px solid black; border-right: 3px solid black; border-bottom: 3px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:15px; color:black;">'+this.rank1_l_h_price_rate+'%</td>';
				}
				this.short_html += '</tr>';
				this.short_html += '</table>';
			}
		},
		zeroCut(nn) {
			if (nn == undefined || nn == null){
				return nn;
			}

			nn = nn + "";
			var num3Arr = nn.split(".");
			var arr1 = num3Arr[1];
			var isNotZero = false;
			var result = "";
			for (var i = arr1.length;i > 0; i--) {
				var n = i-1;
				var charA = arr1.charAt(n);
				if (charA == '0') {
					if (isNotZero == true) {
						result += "0";
					} else {
						result += "";
					}
				} else {
					isNotZero = true;
					result += arr1.charAt(n);
				}
			}

			var return_data = ''
			if((result.split("").reverse().join("")) == ''){
				return_data = num3Arr[0]
			}else{
				return_data = num3Arr[0] + "." + result.split("").reverse().join("")
			}
			return return_data;
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
