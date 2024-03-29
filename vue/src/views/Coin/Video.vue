<style>
.swal2-title{
	font-size:22px !important;
}
.ag-header-cell-label {
  justify-content: center;
}
.googleChartTitle {
    text-align: center;
}
</style>
<template>
	<v-layout wrap row>
		<v-flex xs12 sm12 md12 style="margin-bottom:20px;">
			<v-card xs12 sm12 md12>
				<v-card-title xs12 sm12 md12 class="fontBold" style="font-size:18px; font-weight:bold; padding-left: 60px; padding-top: 20px; padding-bottom: 10px;">
					유튜브 Video
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
							<v-col id="daily_video" lg="8" md="8" sm="8" cols="8" style="padding-top:0px;">
								<div v-html="video_title_html"></div>
								<div v-html="video_html"></div>
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
			video_title_html: '',
			video_html: '',
			search_date: '',
			search_date_kor: '',
			search_market: '',
			rankList: [],

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
			btc_o_c_price_color: '',
			btc_l_h_price: '',
			btc_l_h_price_rate: '',
			btc_l_h_price_color: '',
			btc_lower_price_time: '',
			btc_higher_price_time: '',
			btc_lower_volume_time: '',
			btc_higher_volume_time: '',
			btc_o_0_4: '',
			btc_l_0_4: '',
			btc_h_0_4: '',
			btc_c_0_4: '',
			btc_v_0_4: '',
			btc_o_4_8: '',
			btc_l_4_8: '',
			btc_h_4_8: '',
			btc_c_4_8: '',
			btc_v_4_8: '',
			btc_o_8_12: '',
			btc_l_8_12: '',
			btc_h_8_12: '',
			btc_c_8_12: '',
			btc_v_8_12: '',
			btc_o_12_16: '',
			btc_l_12_16: '',
			btc_h_12_16: '',
			btc_c_12_16: '',
			btc_v_12_16: '',
			btc_o_16_20: '',
			btc_l_16_20: '',
			btc_h_16_20: '',
			btc_c_16_20: '',
			btc_v_16_20: '',
			btc_o_20_24: '',
			btc_l_20_24: '',
			btc_h_20_24: '',
			btc_c_20_24: '',
			btc_v_20_24: '',

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
			rank1_lower_price_time: '',
			rank1_higher_price_time: '',
			rank1_lower_volume_time: '',
			rank1_higher_volume_time: '',
			rank1_o_0_4: '',
			rank1_l_0_4: '',
			rank1_h_0_4: '',
			rank1_c_0_4: '',
			rank1_v_0_4: '',
			rank1_o_4_8: '',
			rank1_l_4_8: '',
			rank1_h_4_8: '',
			rank1_c_4_8: '',
			rank1_v_4_8: '',
			rank1_o_8_12: '',
			rank1_l_8_12: '',
			rank1_h_8_12: '',
			rank1_c_8_12: '',
			rank1_v_8_12: '',
			rank1_o_12_16: '',
			rank1_l_12_16: '',
			rank1_h_12_16: '',
			rank1_c_12_16: '',
			rank1_v_12_16: '',
			rank1_o_16_20: '',
			rank1_l_16_20: '',
			rank1_h_16_20: '',
			rank1_c_16_20: '',
			rank1_v_16_20: '',
			rank1_o_20_24: '',
			rank1_l_20_24: '',
			rank1_h_20_24: '',
			rank1_c_20_24: '',
			rank1_v_20_24: '',

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
			rank2_lower_price_time: '',
			rank2_higher_price_time: '',
			rank2_lower_volume_time: '',
			rank2_higher_volume_time: '',
			rank2_o_0_4: '',
			rank2_l_0_4: '',
			rank2_h_0_4: '',
			rank2_c_0_4: '',
			rank2_v_0_4: '',
			rank2_o_4_8: '',
			rank2_l_4_8: '',
			rank2_h_4_8: '',
			rank2_c_4_8: '',
			rank2_v_4_8: '',
			rank2_o_8_12: '',
			rank2_l_8_12: '',
			rank2_h_8_12: '',
			rank2_c_8_12: '',
			rank2_v_8_12: '',
			rank2_o_12_16: '',
			rank2_l_12_16: '',
			rank2_h_12_16: '',
			rank2_c_12_16: '',
			rank2_v_12_16: '',
			rank2_o_16_20: '',
			rank2_l_16_20: '',
			rank2_h_16_20: '',
			rank2_c_16_20: '',
			rank2_v_16_20: '',
			rank2_o_20_24: '',
			rank2_l_20_24: '',
			rank2_h_20_24: '',
			rank2_c_20_24: '',
			rank2_v_20_24: '',

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
			rank3_lower_price_time: '',
			rank3_higher_price_time: '',
			rank3_lower_volume_time: '',
			rank3_higher_volume_time: '',
			rank3_o_0_4: '',
			rank3_l_0_4: '',
			rank3_h_0_4: '',
			rank3_c_0_4: '',
			rank3_v_0_4: '',
			rank3_o_4_8: '',
			rank3_l_4_8: '',
			rank3_h_4_8: '',
			rank3_c_4_8: '',
			rank3_v_4_8: '',
			rank3_o_8_12: '',
			rank3_l_8_12: '',
			rank3_h_8_12: '',
			rank3_c_8_12: '',
			rank3_v_8_12: '',
			rank3_o_12_16: '',
			rank3_l_12_16: '',
			rank3_h_12_16: '',
			rank3_c_12_16: '',
			rank3_v_12_16: '',
			rank3_o_16_20: '',
			rank3_l_16_20: '',
			rank3_h_16_20: '',
			rank3_c_16_20: '',
			rank3_v_16_20: '',
			rank3_o_20_24: '',
			rank3_l_20_24: '',
			rank3_h_20_24: '',
			rank3_c_20_24: '',
			rank3_v_20_24: '',

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
			rank4_lower_price_time: '',
			rank4_higher_price_time: '',
			rank4_lower_volume_time: '',
			rank4_higher_volume_time: '',
			rank4_o_0_4: '',
			rank4_l_0_4: '',
			rank4_h_0_4: '',
			rank4_c_0_4: '',
			rank4_v_0_4: '',
			rank4_o_4_8: '',
			rank4_l_4_8: '',
			rank4_h_4_8: '',
			rank4_c_4_8: '',
			rank4_v_4_8: '',
			rank4_o_8_12: '',
			rank4_l_8_12: '',
			rank4_h_8_12: '',
			rank4_c_8_12: '',
			rank4_v_8_12: '',
			rank4_o_12_16: '',
			rank4_l_12_16: '',
			rank4_h_12_16: '',
			rank4_c_12_16: '',
			rank4_v_12_16: '',
			rank4_o_16_20: '',
			rank4_l_16_20: '',
			rank4_h_16_20: '',
			rank4_c_16_20: '',
			rank4_v_16_20: '',
			rank4_o_20_24: '',
			rank4_l_20_24: '',
			rank4_h_20_24: '',
			rank4_c_20_24: '',
			rank4_v_20_24: '',

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
			rank5_lower_price_time: '',
			rank5_higher_price_time: '',
			rank5_lower_volume_time: '',
			rank5_higher_volume_time: '',
			rank5_o_0_4: '',
			rank5_l_0_4: '',
			rank5_h_0_4: '',
			rank5_c_0_4: '',
			rank5_v_0_4: '',
			rank5_o_4_8: '',
			rank5_l_4_8: '',
			rank5_h_4_8: '',
			rank5_c_4_8: '',
			rank5_v_4_8: '',
			rank5_o_8_12: '',
			rank5_l_8_12: '',
			rank5_h_8_12: '',
			rank5_c_8_12: '',
			rank5_v_8_12: '',
			rank5_o_12_16: '',
			rank5_l_12_16: '',
			rank5_h_12_16: '',
			rank5_c_12_16: '',
			rank5_v_12_16: '',
			rank5_o_16_20: '',
			rank5_l_16_20: '',
			rank5_h_16_20: '',
			rank5_c_16_20: '',
			rank5_v_16_20: '',
			rank5_o_20_24: '',
			rank5_l_20_24: '',
			rank5_h_20_24: '',
			rank5_c_20_24: '',
			rank5_v_20_24: '',
		}
	},
	components: {
		AgGridVue
	},
	beforeMount() {
       this.columnDefs = [
           {headerName: 'Video', field:"id", width:220, cellStyle: {textAlign: "left"}, sortable: false, filter: true, resizable:true},
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
		   {headerName: '비트코인 시가 대비 종가 색상', field:"btc_o_c_price_color", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 고가-저가', field:"btc_l_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 저가 대비 고가', field:"btc_l_h_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 저가 대비 고가 색상', field:"btc_l_h_price_color", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 최저가 시간', field:"btc_lower_price_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 최고가 시간', field:"btc_higher_price_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 최저 거래량 시간', field:"btc_lower_volume_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 최고 거래량 시간', field:"btc_higher_volume_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 0-4 시가', field:"btc_o_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 0-4 저가', field:"btc_l_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 0-4 고가', field:"btc_h_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 0-4 종가', field:"btc_c_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 0-4 거래량', field:"btc_v_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 4-8 시가', field:"btc_o_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 4-8 저가', field:"btc_l_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 4-8 고가', field:"btc_h_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 4-8 종가', field:"btc_c_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 4-8 거래량', field:"btc_v_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 8-12 시가', field:"btc_o_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 8-12 저가', field:"btc_l_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 8-12 고가', field:"btc_h_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 8-12 종가', field:"btc_c_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 8-12 거래량', field:"btc_v_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 12-16 시가', field:"btc_o_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 12-16 저가', field:"btc_l_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 12-16 고가', field:"btc_h_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 12-16 종가', field:"btc_c_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 12-16 거래량', field:"btc_v_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 16-20 시가', field:"btc_o_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 16-20 저가', field:"btc_l_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 16-20 고가', field:"btc_h_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 16-20 종가', field:"btc_c_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 16-20 거래량', field:"btc_v_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 20-24 시가', field:"btc_o_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 20-24 저가', field:"btc_l_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 20-24 고가', field:"btc_h_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 20-24 종가', field:"btc_c_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '비트코인 20-24 거래량', field:"btc_v_20_24", hide:true, sortable: false, filter: true, resizable:true},

		   {headerName: '랭킹1 코인티커', field:"rank1_coin_ticker", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 코인명', field:"rank1_coin_name", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 조사', field:"rank1_coin_particle", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 시가', field:"rank1_o_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 저가', field:"rank1_l_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 고가', field:"rank1_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 종가', field:"rank1_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 종가-시가', field:"rank1_o_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 시가 대비 종가', field:"rank1_o_c_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 시가 대비 종가 색상', field:"rank1_o_c_price_color", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 고가-저가', field:"rank1_l_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 저가 대비 고가', field:"rank1_l_h_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 저가 대비 고가 색상', field:"rank1_l_h_price_color", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 최저가 시간', field:"rank1_lower_price_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 최고가 시간', field:"rank1_higher_price_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 최저 거래량 시간', field:"rank1_lower_volume_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 최고 거래량 시간', field:"rank1_higher_volume_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 0-4 시가', field:"rank1_o_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 0-4 저가', field:"rank1_l_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 0-4 고가', field:"rank1_h_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 0-4 종가', field:"rank1_c_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 0-4 거래량', field:"rank1_v_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 4-8 시가', field:"rank1_o_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 4-8 저가', field:"rank1_l_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 4-8 고가', field:"rank1_h_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 4-8 종가', field:"rank1_c_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 4-8 거래량', field:"rank1_v_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 8-12 시가', field:"rank1_o_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 8-12 저가', field:"rank1_l_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 8-12 고가', field:"rank1_h_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 8-12 종가', field:"rank1_c_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 8-12 거래량', field:"rank1_v_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 12-16 시가', field:"rank1_o_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 12-16 저가', field:"rank1_l_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 12-16 고가', field:"rank1_h_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 12-16 종가', field:"rank1_c_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 12-16 거래량', field:"rank1_v_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 16-20 시가', field:"rank1_o_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 16-20 저가', field:"rank1_l_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 16-20 고가', field:"rank1_h_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 16-20 종가', field:"rank1_c_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 16-20 거래량', field:"rank1_v_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 20-24 시가', field:"rank1_o_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 20-24 저가', field:"rank1_l_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 20-24 고가', field:"rank1_h_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 20-24 종가', field:"rank1_c_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹1 20-24 거래량', field:"rank1_v_20_24", hide:true, sortable: false, filter: true, resizable:true},

		   {headerName: '랭킹2 코인티커', field:"rank2_coin_ticker", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 코인명', field:"rank2_coin_name", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 조사', field:"rank2_coin_particle", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 시가', field:"rank2_o_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 저가', field:"rank2_l_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 고가', field:"rank2_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 종가', field:"rank2_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 종가-시가', field:"rank2_o_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 시가 대비 종가', field:"rank2_o_c_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 시가 대비 종가 색상', field:"rank2_o_c_price_color", hide:true, sortable: false, filter: true, resizable:true},  
		   {headerName: '랭킹2 고가-저가', field:"rank2_l_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 저가 대비 고가', field:"rank2_l_h_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 저가 대비 고가 색상', field:"rank2_l_h_price_color", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 최저가 시간', field:"rank2_lower_price_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 최고가 시간', field:"rank2_higher_price_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 최저 거래량 시간', field:"rank2_lower_volume_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 최고 거래량 시간', field:"rank2_higher_volume_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 0-4 시가', field:"rank2_o_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 0-4 저가', field:"rank2_l_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 0-4 고가', field:"rank2_h_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 0-4 종가', field:"rank2_c_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 0-4 거래량', field:"rank2_v_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 4-8 시가', field:"rank2_o_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 4-8 저가', field:"rank2_l_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 4-8 고가', field:"rank2_h_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 4-8 종가', field:"rank2_c_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 4-8 거래량', field:"rank2_v_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 8-12 시가', field:"rank2_o_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 8-12 저가', field:"rank2_l_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 8-12 고가', field:"rank2_h_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 8-12 종가', field:"rank2_c_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 8-12 거래량', field:"rank2_v_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 12-16 시가', field:"rank2_o_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 12-16 저가', field:"rank2_l_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 12-16 고가', field:"rank2_h_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 12-16 종가', field:"rank2_c_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 12-16 거래량', field:"rank2_v_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 16-20 시가', field:"rank2_o_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 16-20 저가', field:"rank2_l_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 16-20 고가', field:"rank2_h_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 16-20 종가', field:"rank2_c_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 16-20 거래량', field:"rank2_v_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 20-24 시가', field:"rank2_o_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 20-24 저가', field:"rank2_l_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 20-24 고가', field:"rank2_h_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 20-24 종가', field:"rank2_c_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹2 20-24 거래량', field:"rank2_v_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   
		   {headerName: '랭킹3 코인티커', field:"rank3_coin_ticker", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 코인명', field:"rank3_coin_name", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 조사', field:"rank3_coin_particle", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 시가', field:"rank3_o_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 저가', field:"rank3_l_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 고가', field:"rank3_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 종가', field:"rank3_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 종가-시가', field:"rank3_o_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 시가 대비 종가', field:"rank3_o_c_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 시가 대비 종가 색상', field:"rank3_o_c_price_color", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 고가-저가', field:"rank3_l_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 저가 대비 고가', field:"rank3_l_h_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 저가 대비 고가 색상', field:"rank3_l_h_price_color", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 최저가 시간', field:"rank3_lower_price_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 최고가 시간', field:"rank3_higher_price_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 최저 거래량 시간', field:"rank3_lower_volume_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 최고 거래량 시간', field:"rank3_higher_volume_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 0-4 시가', field:"rank3_o_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 0-4 저가', field:"rank3_l_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 0-4 고가', field:"rank3_h_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 0-4 종가', field:"rank3_c_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 0-4 거래량', field:"rank3_v_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 4-8 시가', field:"rank3_o_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 4-8 저가', field:"rank3_l_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 4-8 고가', field:"rank3_h_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 4-8 종가', field:"rank3_c_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 4-8 거래량', field:"rank3_v_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 8-12 시가', field:"rank3_o_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 8-12 저가', field:"rank3_l_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 8-12 고가', field:"rank3_h_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 8-12 종가', field:"rank3_c_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 8-12 거래량', field:"rank3_v_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 12-16 시가', field:"rank3_o_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 12-16 저가', field:"rank3_l_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 12-16 고가', field:"rank3_h_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 12-16 종가', field:"rank3_c_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 12-16 거래량', field:"rank3_v_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 16-20 시가', field:"rank3_o_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 16-20 저가', field:"rank3_l_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 16-20 고가', field:"rank3_h_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 16-20 종가', field:"rank3_c_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 16-20 거래량', field:"rank3_v_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 20-24 시가', field:"rank3_o_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 20-24 저가', field:"rank3_l_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 20-24 고가', field:"rank3_h_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 20-24 종가', field:"rank3_c_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹3 20-24 거래량', field:"rank3_v_20_24", hide:true, sortable: false, filter: true, resizable:true},

		   {headerName: '랭킹4 코인티커', field:"rank4_coin_ticker", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 코인명', field:"rank4_coin_name", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 조사', field:"rank4_coin_particle", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 시가', field:"rank4_o_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 저가', field:"rank4_l_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 고가', field:"rank4_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 종가', field:"rank4_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 종가-시가', field:"rank4_o_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 시가 대비 종가', field:"rank4_o_c_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 시가 대비 종가 색상', field:"rank4_o_c_price_color", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 고가-저가', field:"rank4_l_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 저가 대비 고가', field:"rank4_l_h_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 저가 대비 고가 색상', field:"rank4_l_h_price_color", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 최저가 시간', field:"rank4_lower_price_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 최고가 시간', field:"rank4_higher_price_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 최저 거래량 시간', field:"rank4_lower_volume_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 최고 거래량 시간', field:"rank4_higher_volume_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 0-4 시가', field:"rank4_o_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 0-4 저가', field:"rank4_l_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 0-4 고가', field:"rank4_h_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 0-4 종가', field:"rank4_c_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 0-4 거래량', field:"rank4_v_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 4-8 시가', field:"rank4_o_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 4-8 저가', field:"rank4_l_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 4-8 고가', field:"rank4_h_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 4-8 종가', field:"rank4_c_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 4-8 거래량', field:"rank4_v_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 8-12 시가', field:"rank4_o_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 8-12 저가', field:"rank4_l_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 8-12 고가', field:"rank4_h_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 8-12 종가', field:"rank4_c_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 8-12 거래량', field:"rank4_v_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 12-16 시가', field:"rank4_o_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 12-16 저가', field:"rank4_l_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 12-16 고가', field:"rank4_h_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 12-16 종가', field:"rank4_c_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 12-16 거래량', field:"rank4_v_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 16-20 시가', field:"rank4_o_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 16-20 저가', field:"rank4_l_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 16-20 고가', field:"rank4_h_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 16-20 종가', field:"rank4_c_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 16-20 거래량', field:"rank4_v_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 20-24 시가', field:"rank4_o_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 20-24 저가', field:"rank4_l_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 20-24 고가', field:"rank4_h_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 20-24 종가', field:"rank4_c_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹4 20-24 거래량', field:"rank4_v_20_24", hide:true, sortable: false, filter: true, resizable:true},

		   {headerName: '랭킹5 코인티커', field:"rank5_coin_ticker", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 코인명', field:"rank5_coin_name", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 조사', field:"rank5_coin_particle", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 시가', field:"rank5_o_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 저가', field:"rank5_l_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 고가', field:"rank5_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 종가', field:"rank5_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 종가-시가', field:"rank5_o_c_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 시가 대비 종가', field:"rank5_o_c_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 시가 대비 종가 색상', field:"rank5_o_c_price_color", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 고가-저가', field:"rank5_l_h_price", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 저가 대비 고가', field:"rank5_l_h_price_rate", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 저가 대비 고가 색상', field:"rank5_l_h_price_color", hide:true, sortable: false, filter: true, resizable:true},	
		   {headerName: '랭킹5 최저가 시간', field:"rank5_lower_price_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 최고가 시간', field:"rank5_higher_price_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 최저 거래량 시간', field:"rank5_lower_volume_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 최고 거래량 시간', field:"rank5_higher_volume_time", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 0-4 시가', field:"rank5_o_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 0-4 저가', field:"rank5_l_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 0-4 고가', field:"rank5_h_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 0-4 종가', field:"rank5_c_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 0-4 거래량', field:"rank5_v_0_4", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 4-8 시가', field:"rank5_o_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 4-8 저가', field:"rank5_l_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 4-8 고가', field:"rank5_h_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 4-8 종가', field:"rank5_c_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 4-8 거래량', field:"rank5_v_4_8", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 8-12 시가', field:"rank5_o_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 8-12 저가', field:"rank5_l_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 8-12 고가', field:"rank5_h_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 8-12 종가', field:"rank5_c_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 8-12 거래량', field:"rank5_v_8_12", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 12-16 시가', field:"rank5_o_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 12-16 저가', field:"rank5_l_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 12-16 고가', field:"rank5_h_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 12-16 종가', field:"rank5_c_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 12-16 거래량', field:"rank5_v_12_16", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 16-20 시가', field:"rank5_o_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 16-20 저가', field:"rank5_l_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 16-20 고가', field:"rank5_h_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 16-20 종가', field:"rank5_c_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 16-20 거래량', field:"rank5_v_16_20", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 20-24 시가', field:"rank5_o_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 20-24 저가', field:"rank5_l_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 20-24 고가', field:"rank5_h_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 20-24 종가', field:"rank5_c_20_24", hide:true, sortable: false, filter: true, resizable:true},
		   {headerName: '랭킹5 20-24 거래량', field:"rank5_v_20_24", hide:true, sortable: false, filter: true, resizable:true},
       ];
    },
	created () {
		if(this.$route.params.date !== undefined && this.$route.params.market !== undefined){
			this.search_date = this.$route.params.date
			this.search_market = this.$route.params.market

			this.search_date_kor = (this.search_date).substring(2, 4) + '년 ';
			if((this.search_date).substring(5, 6) == '0'){
				this.search_date_kor += (this.search_date).substring(6, 7) + '월 '
			}else{
				this.search_date_kor += (this.search_date).substring(5, 7) + '월 '
			}

			if((this.search_date).substring(8, 9) == '0'){
				this.search_date_kor += (this.search_date).substring(9, 10) +'일';
			}else{
				this.search_date_kor += (this.search_date).substring(8, 10) +'일';
			}
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
			this.search_date_kor = (this.search_date).substring(2, 4) + '년 ';
			if((this.search_date).substring(5, 6) == '0'){
				this.search_date_kor += (this.search_date).substring(6, 7) + '월 '
			}else{
				this.search_date_kor += (this.search_date).substring(5, 7) + '월 '
			}

			if((this.search_date).substring(8, 9) == '0'){
				this.search_date_kor += (this.search_date).substring(9, 10) +'일';
			}else{
				this.search_date_kor += (this.search_date).substring(8, 10) +'일';
			}
		}
	},
	mounted (){
		this.makeDay();
	},
	methods: {
		makeDay () {
			this.rowData = [];
			this.isLoading = true;
			axios.get('/Main/VideoList')
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
						btc_o_c_price_color: response.data[x].btc_o_c_price_color,
						btc_l_h_price: response.data[x].btc_l_h_price,
						btc_l_h_price_rate: response.data[x].btc_l_h_price_rate,
						btc_l_h_price_color: response.data[x].btc_l_h_price_color,
						btc_lower_price_time: response.data[x].btc_lower_price_time,
						btc_higher_price_time: response.data[x].btc_higher_price_time,
						btc_lower_volume_time: response.data[x].btc_lower_volume_time,
						btc_higher_volume_time: response.data[x].btc_higher_volume_time,
						btc_o_0_4: response.data[x].btc_o_0_4,
						btc_l_0_4: response.data[x].btc_l_0_4,
						btc_h_0_4: response.data[x].btc_h_0_4,
						btc_c_0_4: response.data[x].btc_c_0_4,
						btc_v_0_4: response.data[x].btc_v_0_4,
						btc_o_4_8: response.data[x].btc_o_4_8,
						btc_l_4_8: response.data[x].btc_l_4_8,
						btc_h_4_8: response.data[x].btc_h_4_8,
						btc_c_4_8: response.data[x].btc_c_4_8,
						btc_v_4_8: response.data[x].btc_v_4_8,
						btc_o_8_12: response.data[x].btc_o_8_12,
						btc_l_8_12: response.data[x].btc_l_8_12,
						btc_h_8_12: response.data[x].btc_h_8_12,
						btc_c_8_12: response.data[x].btc_c_8_12,
						btc_v_8_12: response.data[x].btc_v_8_12,
						btc_o_12_16: response.data[x].btc_o_12_16,
						btc_l_12_16: response.data[x].btc_l_12_16,
						btc_h_12_16: response.data[x].btc_h_12_16,
						btc_c_12_16: response.data[x].btc_c_12_16,
						btc_v_12_16: response.data[x].btc_v_12_16,
						btc_o_16_20: response.data[x].btc_o_16_20,
						btc_l_16_20: response.data[x].btc_l_16_20,
						btc_h_16_20: response.data[x].btc_h_16_20,
						btc_c_16_20: response.data[x].btc_c_16_20,
						btc_v_16_20: response.data[x].btc_v_16_20,
						btc_o_20_24: response.data[x].btc_o_20_24,
						btc_l_20_24: response.data[x].btc_l_20_24,
						btc_h_20_24: response.data[x].btc_h_20_24,
						btc_c_20_24: response.data[x].btc_c_20_24,
						btc_v_20_24: response.data[x].btc_v_20_24,

						rank1_coin_ticker: response.data[x].rank1_coin_ticker,
						rank1_coin_name: response.data[x].rank1_coin_name,
						rank1_coin_particle: response.data[x].rank1_coin_particle,
						rank1_o_price: response.data[x].rank1_o_price,
						rank1_l_price: response.data[x].rank1_l_price,
						rank1_h_price: response.data[x].rank1_h_price,
						rank1_c_price: response.data[x].rank1_c_price,
						rank1_o_c_price: response.data[x].rank1_o_c_price,
						rank1_o_c_price_rate: response.data[x].rank1_o_c_price_rate,
						rank1_o_c_price_color: response.data[x].rank1_o_c_price_color,
						rank1_l_h_price: response.data[x].rank1_l_h_price,
						rank1_l_h_price_rate: response.data[x].rank1_l_h_price_rate,
						rank1_l_h_price_color: response.data[x].rank1_l_h_price_color,
						rank1_lower_price_time: response.data[x].rank1_lower_price_time,
						rank1_higher_price_time: response.data[x].rank1_higher_price_time,
						rank1_lower_volume_time: response.data[x].rank1_lower_volume_time,
						rank1_higher_volume_time: response.data[x].rank1_higher_volume_time,
						rank1_o_0_4: response.data[x].rank1_o_0_4,
						rank1_l_0_4: response.data[x].rank1_l_0_4,
						rank1_h_0_4: response.data[x].rank1_h_0_4,
						rank1_c_0_4: response.data[x].rank1_c_0_4,
						rank1_v_0_4: response.data[x].rank1_v_0_4,
						rank1_o_4_8: response.data[x].rank1_o_4_8,
						rank1_l_4_8: response.data[x].rank1_l_4_8,
						rank1_h_4_8: response.data[x].rank1_h_4_8,
						rank1_c_4_8: response.data[x].rank1_c_4_8,
						rank1_v_4_8: response.data[x].rank1_v_4_8,
						rank1_o_8_12: response.data[x].rank1_o_8_12,
						rank1_l_8_12: response.data[x].rank1_l_8_12,
						rank1_h_8_12: response.data[x].rank1_h_8_12,
						rank1_c_8_12: response.data[x].rank1_c_8_12,
						rank1_v_8_12: response.data[x].rank1_v_8_12,
						rank1_o_12_16: response.data[x].rank1_o_12_16,
						rank1_l_12_16: response.data[x].rank1_l_12_16,
						rank1_h_12_16: response.data[x].rank1_h_12_16,
						rank1_c_12_16: response.data[x].rank1_c_12_16,
						rank1_v_12_16: response.data[x].rank1_v_12_16,
						rank1_o_16_20: response.data[x].rank1_o_16_20,
						rank1_l_16_20: response.data[x].rank1_l_16_20,
						rank1_h_16_20: response.data[x].rank1_h_16_20,
						rank1_c_16_20: response.data[x].rank1_c_16_20,
						rank1_v_16_20: response.data[x].rank1_v_16_20,
						rank1_o_20_24: response.data[x].rank1_o_20_24,
						rank1_l_20_24: response.data[x].rank1_l_20_24,
						rank1_h_20_24: response.data[x].rank1_h_20_24,
						rank1_c_20_24: response.data[x].rank1_c_20_24,
						rank1_v_20_24: response.data[x].rank1_v_20_24,

						rank2_coin_ticker: response.data[x].rank2_coin_ticker,
						rank2_coin_name: response.data[x].rank2_coin_name,
						rank2_coin_particle: response.data[x].rank2_coin_particle,
						rank2_o_price: response.data[x].rank2_o_price,
						rank2_l_price: response.data[x].rank2_l_price,
						rank2_h_price: response.data[x].rank2_h_price,
						rank2_c_price: response.data[x].rank2_c_price,
						rank2_o_c_price: response.data[x].rank2_o_c_price,
						rank2_o_c_price_rate: response.data[x].rank2_o_c_price_rate,
						rank2_o_c_price_color: response.data[x].rank2_o_c_price_color,
						rank2_l_h_price: response.data[x].rank2_l_h_price,
						rank2_l_h_price_rate: response.data[x].rank2_l_h_price_rate,
						rank2_l_h_price_color: response.data[x].rank2_l_h_price_color,
						rank2_lower_price_time: response.data[x].rank2_lower_price_time,
						rank2_higher_price_time: response.data[x].rank2_higher_price_time,
						rank2_lower_volume_time: response.data[x].rank2_lower_volume_time,
						rank2_higher_volume_time: response.data[x].rank2_higher_volume_time,
						rank2_o_0_4: response.data[x].rank2_o_0_4,
						rank2_l_0_4: response.data[x].rank2_l_0_4,
						rank2_h_0_4: response.data[x].rank2_h_0_4,
						rank2_c_0_4: response.data[x].rank2_c_0_4,
						rank2_v_0_4: response.data[x].rank2_v_0_4,
						rank2_o_4_8: response.data[x].rank2_o_4_8,
						rank2_l_4_8: response.data[x].rank2_l_4_8,
						rank2_h_4_8: response.data[x].rank2_h_4_8,
						rank2_c_4_8: response.data[x].rank2_c_4_8,
						rank2_v_4_8: response.data[x].rank2_v_4_8,
						rank2_o_8_12: response.data[x].rank2_o_8_12,
						rank2_l_8_12: response.data[x].rank2_l_8_12,
						rank2_h_8_12: response.data[x].rank2_h_8_12,
						rank2_c_8_12: response.data[x].rank2_c_8_12,
						rank2_v_8_12: response.data[x].rank2_v_8_12,
						rank2_o_12_16: response.data[x].rank2_o_12_16,
						rank2_l_12_16: response.data[x].rank2_l_12_16,
						rank2_h_12_16: response.data[x].rank2_h_12_16,
						rank2_c_12_16: response.data[x].rank2_c_12_16,
						rank2_v_12_16: response.data[x].rank2_v_12_16,
						rank2_o_16_20: response.data[x].rank2_o_16_20,
						rank2_l_16_20: response.data[x].rank2_l_16_20,
						rank2_h_16_20: response.data[x].rank2_h_16_20,
						rank2_c_16_20: response.data[x].rank2_c_16_20,
						rank2_v_16_20: response.data[x].rank2_v_16_20,
						rank2_o_20_24: response.data[x].rank2_o_20_24,
						rank2_l_20_24: response.data[x].rank2_l_20_24,
						rank2_h_20_24: response.data[x].rank2_h_20_24,
						rank2_c_20_24: response.data[x].rank2_c_20_24,
						rank2_v_20_24: response.data[x].rank2_v_20_24,

						rank3_coin_ticker: response.data[x].rank3_coin_ticker,
						rank3_coin_name: response.data[x].rank3_coin_name,
						rank3_coin_particle: response.data[x].rank3_coin_particle,
						rank3_o_price: response.data[x].rank3_o_price,
						rank3_l_price: response.data[x].rank3_l_price,
						rank3_h_price: response.data[x].rank3_h_price,
						rank3_c_price: response.data[x].rank3_c_price,
						rank3_o_c_price: response.data[x].rank3_o_c_price,
						rank3_o_c_price_rate: response.data[x].rank3_o_c_price_rate,
						rank3_o_c_price_color: response.data[x].rank3_o_c_price_color,
						rank3_l_h_price: response.data[x].rank3_l_h_price,
						rank3_l_h_price_rate: response.data[x].rank3_l_h_price_rate,
						rank3_l_h_price_color: response.data[x].rank3_l_h_price_color,
						rank3_lower_price_time: response.data[x].rank3_lower_price_time,
						rank3_higher_price_time: response.data[x].rank3_higher_price_time,
						rank3_lower_volume_time: response.data[x].rank3_lower_volume_time,
						rank3_higher_volume_time: response.data[x].rank3_higher_volume_time,
						rank3_o_0_4: response.data[x].rank3_o_0_4,
						rank3_l_0_4: response.data[x].rank3_l_0_4,
						rank3_h_0_4: response.data[x].rank3_h_0_4,
						rank3_c_0_4: response.data[x].rank3_c_0_4,
						rank3_v_0_4: response.data[x].rank3_v_0_4,
						rank3_o_4_8: response.data[x].rank3_o_4_8,
						rank3_l_4_8: response.data[x].rank3_l_4_8,
						rank3_h_4_8: response.data[x].rank3_h_4_8,
						rank3_c_4_8: response.data[x].rank3_c_4_8,
						rank3_v_4_8: response.data[x].rank3_v_4_8,
						rank3_o_8_12: response.data[x].rank3_o_8_12,
						rank3_l_8_12: response.data[x].rank3_l_8_12,
						rank3_h_8_12: response.data[x].rank3_h_8_12,
						rank3_c_8_12: response.data[x].rank3_c_8_12,
						rank3_v_8_12: response.data[x].rank3_v_8_12,
						rank3_o_12_16: response.data[x].rank3_o_12_16,
						rank3_l_12_16: response.data[x].rank3_l_12_16,
						rank3_h_12_16: response.data[x].rank3_h_12_16,
						rank3_c_12_16: response.data[x].rank3_c_12_16,
						rank3_v_12_16: response.data[x].rank3_v_12_16,
						rank3_o_16_20: response.data[x].rank3_o_16_20,
						rank3_l_16_20: response.data[x].rank3_l_16_20,
						rank3_h_16_20: response.data[x].rank3_h_16_20,
						rank3_c_16_20: response.data[x].rank3_c_16_20,
						rank3_v_16_20: response.data[x].rank3_v_16_20,
						rank3_o_20_24: response.data[x].rank3_o_20_24,
						rank3_l_20_24: response.data[x].rank3_l_20_24,
						rank3_h_20_24: response.data[x].rank3_h_20_24,
						rank3_c_20_24: response.data[x].rank3_c_20_24,
						rank3_v_20_24: response.data[x].rank3_v_20_24,

						rank4_coin_ticker: response.data[x].rank4_coin_ticker,
						rank4_coin_name: response.data[x].rank4_coin_name,
						rank4_coin_particle: response.data[x].rank4_coin_particle,
						rank4_o_price: response.data[x].rank4_o_price,
						rank4_l_price: response.data[x].rank4_l_price,
						rank4_h_price: response.data[x].rank4_h_price,
						rank4_c_price: response.data[x].rank4_c_price,
						rank4_o_c_price: response.data[x].rank4_o_c_price,
						rank4_o_c_price_rate: response.data[x].rank4_o_c_price_rate,
						rank4_o_c_price_color: response.data[x].rank4_o_c_price_color,
						rank4_l_h_price: response.data[x].rank4_l_h_price,
						rank4_l_h_price_rate: response.data[x].rank4_l_h_price_rate,
						rank4_l_h_price_color: response.data[x].rank4_l_h_price_color,
						rank4_lower_price_time: response.data[x].rank4_lower_price_time,
						rank4_higher_price_time: response.data[x].rank4_higher_price_time,
						rank4_lower_volume_time: response.data[x].rank4_lower_volume_time,
						rank4_higher_volume_time: response.data[x].rank4_higher_volume_time,
						rank4_o_0_4: response.data[x].rank4_o_0_4,
						rank4_l_0_4: response.data[x].rank4_l_0_4,
						rank4_h_0_4: response.data[x].rank4_h_0_4,
						rank4_c_0_4: response.data[x].rank4_c_0_4,
						rank4_v_0_4: response.data[x].rank4_v_0_4,
						rank4_o_4_8: response.data[x].rank4_o_4_8,
						rank4_l_4_8: response.data[x].rank4_l_4_8,
						rank4_h_4_8: response.data[x].rank4_h_4_8,
						rank4_c_4_8: response.data[x].rank4_c_4_8,
						rank4_v_4_8: response.data[x].rank4_v_4_8,
						rank4_o_8_12: response.data[x].rank4_o_8_12,
						rank4_l_8_12: response.data[x].rank4_l_8_12,
						rank4_h_8_12: response.data[x].rank4_h_8_12,
						rank4_c_8_12: response.data[x].rank4_c_8_12,
						rank4_v_8_12: response.data[x].rank4_v_8_12,
						rank4_o_12_16: response.data[x].rank4_o_12_16,
						rank4_l_12_16: response.data[x].rank4_l_12_16,
						rank4_h_12_16: response.data[x].rank4_h_12_16,
						rank4_c_12_16: response.data[x].rank4_c_12_16,
						rank4_v_12_16: response.data[x].rank4_v_12_16,
						rank4_o_16_20: response.data[x].rank4_o_16_20,
						rank4_l_16_20: response.data[x].rank4_l_16_20,
						rank4_h_16_20: response.data[x].rank4_h_16_20,
						rank4_c_16_20: response.data[x].rank4_c_16_20,
						rank4_v_16_20: response.data[x].rank4_v_16_20,
						rank4_o_20_24: response.data[x].rank4_o_20_24,
						rank4_l_20_24: response.data[x].rank4_l_20_24,
						rank4_h_20_24: response.data[x].rank4_h_20_24,
						rank4_c_20_24: response.data[x].rank4_c_20_24,
						rank4_v_20_24: response.data[x].rank4_v_20_24,

						rank5_coin_ticker: response.data[x].rank5_coin_ticker,
						rank5_coin_name: response.data[x].rank5_coin_name,
						rank5_coin_particle: response.data[x].rank5_coin_particle,
						rank5_o_price: response.data[x].rank5_o_price,
						rank5_l_price: response.data[x].rank5_l_price,
						rank5_h_price: response.data[x].rank5_h_price,
						rank5_c_price: response.data[x].rank5_c_price,
						rank5_o_c_price: response.data[x].rank5_o_c_price,
						rank5_o_c_price_rate: response.data[x].rank5_o_c_price_rate,
						rank5_o_c_price_color: response.data[x].rank5_o_c_price_color,
						rank5_l_h_price: response.data[x].rank5_l_h_price,
						rank5_l_h_price_rate: response.data[x].rank5_l_h_price_rate,
						rank5_l_h_price_color: response.data[x].rank5_l_h_price_color,
						rank5_lower_price_time: response.data[x].rank5_lower_price_time,
						rank5_higher_price_time: response.data[x].rank5_higher_price_time,
						rank5_lower_volume_time: response.data[x].rank5_lower_volume_time,
						rank5_higher_volume_time: response.data[x].rank5_higher_volume_time,
						rank5_o_0_4: response.data[x].rank5_o_0_4,
						rank5_l_0_4: response.data[x].rank5_l_0_4,
						rank5_h_0_4: response.data[x].rank5_h_0_4,
						rank5_c_0_4: response.data[x].rank5_c_0_4,
						rank5_v_0_4: response.data[x].rank5_v_0_4,
						rank5_o_4_8: response.data[x].rank5_o_4_8,
						rank5_l_4_8: response.data[x].rank5_l_4_8,
						rank5_h_4_8: response.data[x].rank5_h_4_8,
						rank5_c_4_8: response.data[x].rank5_c_4_8,
						rank5_v_4_8: response.data[x].rank5_v_4_8,
						rank5_o_8_12: response.data[x].rank5_o_8_12,
						rank5_l_8_12: response.data[x].rank5_l_8_12,
						rank5_h_8_12: response.data[x].rank5_h_8_12,
						rank5_c_8_12: response.data[x].rank5_c_8_12,
						rank5_v_8_12: response.data[x].rank5_v_8_12,
						rank5_o_12_16: response.data[x].rank5_o_12_16,
						rank5_l_12_16: response.data[x].rank5_l_12_16,
						rank5_h_12_16: response.data[x].rank5_h_12_16,
						rank5_c_12_16: response.data[x].rank5_c_12_16,
						rank5_v_12_16: response.data[x].rank5_v_12_16,
						rank5_o_16_20: response.data[x].rank5_o_16_20,
						rank5_l_16_20: response.data[x].rank5_l_16_20,
						rank5_h_16_20: response.data[x].rank5_h_16_20,
						rank5_c_16_20: response.data[x].rank5_c_16_20,
						rank5_v_16_20: response.data[x].rank5_v_16_20,
						rank5_o_20_24: response.data[x].rank5_o_20_24,
						rank5_l_20_24: response.data[x].rank5_l_20_24,
						rank5_h_20_24: response.data[x].rank5_h_20_24,
						rank5_c_20_24: response.data[x].rank5_c_20_24,
						rank5_v_20_24: response.data[x].rank5_v_20_24,
					})
					
					if(this.search_date == response.data[x].yyyymmdd && this.search_market == response.data[x].market){
						this.makeRankList();

						this.btc_coin_ticker = response.data[x].btc_coin_ticker;
						this.btc_coin_name = response.data[x].btc_coin_name;
						this.btc_coin_particle = response.data[x].btc_coin_particle;
						this.btc_o_price = response.data[x].btc_o_price;
						this.btc_l_price = response.data[x].btc_l_price;
						this.btc_h_price = response.data[x].btc_h_price;
						this.btc_c_price = response.data[x].btc_c_price;
						this.btc_o_c_price = response.data[x].btc_o_c_price;
						this.btc_o_c_price_rate = response.data[x].btc_o_c_price_rate;
						this.btc_o_c_price_color = response.data[x].btc_o_c_price_color;
						this.btc_l_h_price = response.data[x].btc_l_h_price;
						this.btc_l_h_price_rate = response.data[x].btc_l_h_price_rate;
						this.btc_l_h_price_color = response.data[x].btc_l_h_price_color;
						this.btc_lower_price_time = response.data[x].btc_lower_price_time;
						this.btc_higher_price_time = response.data[x].btc_higher_price_time;
						this.btc_lower_volume_time = response.data[x].btc_lower_volume_time;
						this.btc_higher_volume_time = response.data[x].btc_higher_volume_time;
						this.btc_o_0_4 = response.data[x].btc_o_0_4;
						this.btc_l_0_4 = response.data[x].btc_l_0_4;
						this.btc_h_0_4 = response.data[x].btc_h_0_4;
						this.btc_c_0_4 = response.data[x].btc_c_0_4;
						this.btc_v_0_4 = response.data[x].btc_v_0_4;
						this.btc_o_4_8 = response.data[x].btc_o_4_8;
						this.btc_l_4_8 = response.data[x].btc_l_4_8;
						this.btc_h_4_8 = response.data[x].btc_h_4_8;
						this.btc_c_4_8 = response.data[x].btc_c_4_8;
						this.btc_v_4_8 = response.data[x].btc_v_4_8;
						this.btc_o_8_12 = response.data[x].btc_o_8_12;
						this.btc_l_8_12 = response.data[x].btc_l_8_12;
						this.btc_h_8_12 = response.data[x].btc_h_8_12;
						this.btc_c_8_12 = response.data[x].btc_c_8_12;
						this.btc_v_8_12 = response.data[x].btc_v_8_12;
						this.btc_o_12_16 = response.data[x].btc_o_12_16;
						this.btc_l_12_16 = response.data[x].btc_l_12_16;
						this.btc_h_12_16 = response.data[x].btc_h_12_16;
						this.btc_c_12_16 = response.data[x].btc_c_12_16;
						this.btc_v_12_16 = response.data[x].btc_v_12_16;
						this.btc_o_16_20 = response.data[x].btc_o_16_20;
						this.btc_l_16_20 = response.data[x].btc_l_16_20;
						this.btc_h_16_20 = response.data[x].btc_h_16_20;
						this.btc_c_16_20 = response.data[x].btc_c_16_20;
						this.btc_v_16_20 = response.data[x].btc_v_16_20;
						this.btc_o_20_24 = response.data[x].btc_o_20_24;
						this.btc_l_20_24 = response.data[x].btc_l_20_24;
						this.btc_h_20_24 = response.data[x].btc_h_20_24;
						this.btc_c_20_24 = response.data[x].btc_c_20_24;
						this.btc_v_20_24 = response.data[x].btc_v_20_24;

						this.rank1_coin_ticker = response.data[x].rank1_coin_ticker;
						this.rank1_coin_name = response.data[x].rank1_coin_name;
						this.rank1_coin_particle = response.data[x].rank1_coin_particle;
						this.rank1_o_price = response.data[x].rank1_o_price;
						this.rank1_l_price = response.data[x].rank1_l_price;
						this.rank1_h_price = response.data[x].rank1_h_price;
						this.rank1_c_price = response.data[x].rank1_c_price;
						this.rank1_o_c_price = response.data[x].rank1_o_c_price;
						this.rank1_o_c_price_rate = response.data[x].rank1_o_c_price_rate;
						this.rank1_o_c_price_color = response.data[x].rank1_o_c_price_color;
						this.rank1_l_h_price = response.data[x].rank1_l_h_price;
						this.rank1_l_h_price_rate = response.data[x].rank1_l_h_price_rate;
						this.rank1_l_h_price_color = response.data[x].rank1_l_h_price_color;
						this.rank1_lower_price_time = response.data[x].rank1_lower_price_time;
						this.rank1_higher_price_time = response.data[x].rank1_higher_price_time;
						this.rank1_lower_volume_time = response.data[x].rank1_lower_volume_time;
						this.rank1_higher_volume_time = response.data[x].rank1_higher_volume_time;
						this.rank1_o_0_4 = response.data[x].rank1_o_0_4;
						this.rank1_l_0_4 = response.data[x].rank1_l_0_4;
						this.rank1_h_0_4 = response.data[x].rank1_h_0_4;
						this.rank1_c_0_4 = response.data[x].rank1_c_0_4;
						this.rank1_v_0_4 = response.data[x].rank1_v_0_4;
						this.rank1_o_4_8 = response.data[x].rank1_o_4_8;
						this.rank1_l_4_8 = response.data[x].rank1_l_4_8;
						this.rank1_h_4_8 = response.data[x].rank1_h_4_8;
						this.rank1_c_4_8 = response.data[x].rank1_c_4_8;
						this.rank1_v_4_8 = response.data[x].rank1_v_4_8;
						this.rank1_o_8_12 = response.data[x].rank1_o_8_12;
						this.rank1_l_8_12 = response.data[x].rank1_l_8_12;
						this.rank1_h_8_12 = response.data[x].rank1_h_8_12;
						this.rank1_c_8_12 = response.data[x].rank1_c_8_12;
						this.rank1_v_8_12 = response.data[x].rank1_v_8_12;
						this.rank1_o_12_16 = response.data[x].rank1_o_12_16;
						this.rank1_l_12_16 = response.data[x].rank1_l_12_16;
						this.rank1_h_12_16 = response.data[x].rank1_h_12_16;
						this.rank1_c_12_16 = response.data[x].rank1_c_12_16;
						this.rank1_v_12_16 = response.data[x].rank1_v_12_16;
						this.rank1_o_16_20 = response.data[x].rank1_o_16_20;
						this.rank1_l_16_20 = response.data[x].rank1_l_16_20;
						this.rank1_h_16_20 = response.data[x].rank1_h_16_20;
						this.rank1_c_16_20 = response.data[x].rank1_c_16_20;
						this.rank1_v_16_20 = response.data[x].rank1_v_16_20;
						this.rank1_o_20_24 = response.data[x].rank1_o_20_24;
						this.rank1_l_20_24 = response.data[x].rank1_l_20_24;
						this.rank1_h_20_24 = response.data[x].rank1_h_20_24;
						this.rank1_c_20_24 = response.data[x].rank1_c_20_24;
						this.rank1_v_20_24 = response.data[x].rank1_v_20_24;

						this.rank2_coin_ticker = response.data[x].rank2_coin_ticker;
						this.rank2_coin_name = response.data[x].rank2_coin_name;
						this.rank2_coin_particle = response.data[x].rank2_coin_particle;
						this.rank2_o_price = response.data[x].rank2_o_price;
						this.rank2_l_price = response.data[x].rank2_l_price;
						this.rank2_h_price = response.data[x].rank2_h_price;
						this.rank2_c_price = response.data[x].rank2_c_price;
						this.rank2_o_c_price = response.data[x].rank2_o_c_price;
						this.rank2_o_c_price_rate = response.data[x].rank2_o_c_price_rate;
						this.rank2_o_c_price_color = response.data[x].rank2_o_c_price_color;
						this.rank2_l_h_price = response.data[x].rank2_l_h_price;
						this.rank2_l_h_price_rate = response.data[x].rank2_l_h_price_rate;
						this.rank2_l_h_price_color = response.data[x].rank2_l_h_price_color;
						this.rank2_lower_price_time = response.data[x].rank2_lower_price_time;
						this.rank2_higher_price_time = response.data[x].rank2_higher_price_time;
						this.rank2_lower_volume_time = response.data[x].rank2_lower_volume_time;
						this.rank2_higher_volume_time = response.data[x].rank2_higher_volume_time;
						this.rank2_o_0_4 = response.data[x].rank2_o_0_4;
						this.rank2_l_0_4 = response.data[x].rank2_l_0_4;
						this.rank2_h_0_4 = response.data[x].rank2_h_0_4;
						this.rank2_c_0_4 = response.data[x].rank2_c_0_4;
						this.rank2_v_0_4 = response.data[x].rank2_v_0_4;
						this.rank2_o_4_8 = response.data[x].rank2_o_4_8;
						this.rank2_l_4_8 = response.data[x].rank2_l_4_8;
						this.rank2_h_4_8 = response.data[x].rank2_h_4_8;
						this.rank2_c_4_8 = response.data[x].rank2_c_4_8;
						this.rank2_v_4_8 = response.data[x].rank2_v_4_8;
						this.rank2_o_8_12 = response.data[x].rank2_o_8_12;
						this.rank2_l_8_12 = response.data[x].rank2_l_8_12;
						this.rank2_h_8_12 = response.data[x].rank2_h_8_12;
						this.rank2_c_8_12 = response.data[x].rank2_c_8_12;
						this.rank2_v_8_12 = response.data[x].rank2_v_8_12;
						this.rank2_o_12_16 = response.data[x].rank2_o_12_16;
						this.rank2_l_12_16 = response.data[x].rank2_l_12_16;
						this.rank2_h_12_16 = response.data[x].rank2_h_12_16;
						this.rank2_c_12_16 = response.data[x].rank2_c_12_16;
						this.rank2_v_12_16 = response.data[x].rank2_v_12_16;
						this.rank2_o_16_20 = response.data[x].rank2_o_16_20;
						this.rank2_l_16_20 = response.data[x].rank2_l_16_20;
						this.rank2_h_16_20 = response.data[x].rank2_h_16_20;
						this.rank2_c_16_20 = response.data[x].rank2_c_16_20;
						this.rank2_v_16_20 = response.data[x].rank2_v_16_20;
						this.rank2_o_20_24 = response.data[x].rank2_o_20_24;
						this.rank2_l_20_24 = response.data[x].rank2_l_20_24;
						this.rank2_h_20_24 = response.data[x].rank2_h_20_24;
						this.rank2_c_20_24 = response.data[x].rank2_c_20_24;
						this.rank2_v_20_24 = response.data[x].rank2_v_20_24;
						
						this.rank3_coin_ticker = response.data[x].rank3_coin_ticker;
						this.rank3_coin_name = response.data[x].rank3_coin_name;
						this.rank3_coin_particle = response.data[x].rank3_coin_particle;
						this.rank3_o_price = response.data[x].rank3_o_price;
						this.rank3_l_price = response.data[x].rank3_l_price;
						this.rank3_h_price = response.data[x].rank3_h_price;
						this.rank3_c_price = response.data[x].rank3_c_price;
						this.rank3_o_c_price = response.data[x].rank3_o_c_price;
						this.rank3_o_c_price_rate = response.data[x].rank3_o_c_price_rate;
						this.rank3_o_c_price_color = response.data[x].rank3_o_c_price_color;
						this.rank3_l_h_price = response.data[x].rank3_l_h_price;
						this.rank3_l_h_price_rate = response.data[x].rank3_l_h_price_rate;
						this.rank3_l_h_price_color = response.data[x].rank3_l_h_price_color;
						this.rank3_lower_price_time = response.data[x].rank3_lower_price_time;
						this.rank3_higher_price_time = response.data[x].rank3_higher_price_time;
						this.rank3_lower_volume_time = response.data[x].rank3_lower_volume_time;
						this.rank3_higher_volume_time = response.data[x].rank3_higher_volume_time;
						this.rank3_o_0_4 = response.data[x].rank3_o_0_4;
						this.rank3_l_0_4 = response.data[x].rank3_l_0_4;
						this.rank3_h_0_4 = response.data[x].rank3_h_0_4;
						this.rank3_c_0_4 = response.data[x].rank3_c_0_4;
						this.rank3_v_0_4 = response.data[x].rank3_v_0_4;
						this.rank3_o_4_8 = response.data[x].rank3_o_4_8;
						this.rank3_l_4_8 = response.data[x].rank3_l_4_8;
						this.rank3_h_4_8 = response.data[x].rank3_h_4_8;
						this.rank3_c_4_8 = response.data[x].rank3_c_4_8;
						this.rank3_v_4_8 = response.data[x].rank3_v_4_8;
						this.rank3_o_8_12 = response.data[x].rank3_o_8_12;
						this.rank3_l_8_12 = response.data[x].rank3_l_8_12;
						this.rank3_h_8_12 = response.data[x].rank3_h_8_12;
						this.rank3_c_8_12 = response.data[x].rank3_c_8_12;
						this.rank3_v_8_12 = response.data[x].rank3_v_8_12;
						this.rank3_o_12_16 = response.data[x].rank3_o_12_16;
						this.rank3_l_12_16 = response.data[x].rank3_l_12_16;
						this.rank3_h_12_16 = response.data[x].rank3_h_12_16;
						this.rank3_c_12_16 = response.data[x].rank3_c_12_16;
						this.rank3_v_12_16 = response.data[x].rank3_v_12_16;
						this.rank3_o_16_20 = response.data[x].rank3_o_16_20;
						this.rank3_l_16_20 = response.data[x].rank3_l_16_20;
						this.rank3_h_16_20 = response.data[x].rank3_h_16_20;
						this.rank3_c_16_20 = response.data[x].rank3_c_16_20;
						this.rank3_v_16_20 = response.data[x].rank3_v_16_20;
						this.rank3_o_20_24 = response.data[x].rank3_o_20_24;
						this.rank3_l_20_24 = response.data[x].rank3_l_20_24;
						this.rank3_h_20_24 = response.data[x].rank3_h_20_24;
						this.rank3_c_20_24 = response.data[x].rank3_c_20_24;
						this.rank3_v_20_24 = response.data[x].rank3_v_20_24;

						this.rank4_coin_ticker = response.data[x].rank4_coin_ticker;
						this.rank4_coin_name = response.data[x].rank4_coin_name;
						this.rank4_coin_particle = response.data[x].rank4_coin_particle;
						this.rank4_o_price = response.data[x].rank4_o_price;
						this.rank4_l_price = response.data[x].rank4_l_price;
						this.rank4_h_price = response.data[x].rank4_h_price;
						this.rank4_c_price = response.data[x].rank4_c_price;
						this.rank4_o_c_price = response.data[x].rank4_o_c_price;
						this.rank4_o_c_price_rate = response.data[x].rank4_o_c_price_rate;
						this.rank4_o_c_price_color = response.data[x].rank4_o_c_price_color;
						this.rank4_l_h_price = response.data[x].rank4_l_h_price;
						this.rank4_l_h_price_rate = response.data[x].rank4_l_h_price_rate;
						this.rank4_l_h_price_color = response.data[x].rank4_l_h_price_color;
						this.rank4_lower_price_time = response.data[x].rank4_lower_price_time;
						this.rank4_higher_price_time = response.data[x].rank4_higher_price_time;
						this.rank4_lower_volume_time = response.data[x].rank4_lower_volume_time;
						this.rank4_higher_volume_time = response.data[x].rank4_higher_volume_time;
						this.rank4_o_0_4 = response.data[x].rank4_o_0_4;
						this.rank4_l_0_4 = response.data[x].rank4_l_0_4;
						this.rank4_h_0_4 = response.data[x].rank4_h_0_4;
						this.rank4_c_0_4 = response.data[x].rank4_c_0_4;
						this.rank4_v_0_4 = response.data[x].rank4_v_0_4;
						this.rank4_o_4_8 = response.data[x].rank4_o_4_8;
						this.rank4_l_4_8 = response.data[x].rank4_l_4_8;
						this.rank4_h_4_8 = response.data[x].rank4_h_4_8;
						this.rank4_c_4_8 = response.data[x].rank4_c_4_8;
						this.rank4_v_4_8 = response.data[x].rank4_v_4_8;
						this.rank4_o_8_12 = response.data[x].rank4_o_8_12;
						this.rank4_l_8_12 = response.data[x].rank4_l_8_12;
						this.rank4_h_8_12 = response.data[x].rank4_h_8_12;
						this.rank4_c_8_12 = response.data[x].rank4_c_8_12;
						this.rank4_v_8_12 = response.data[x].rank4_v_8_12;
						this.rank4_o_12_16 = response.data[x].rank4_o_12_16;
						this.rank4_l_12_16 = response.data[x].rank4_l_12_16;
						this.rank4_h_12_16 = response.data[x].rank4_h_12_16;
						this.rank4_c_12_16 = response.data[x].rank4_c_12_16;
						this.rank4_v_12_16 = response.data[x].rank4_v_12_16;
						this.rank4_o_16_20 = response.data[x].rank4_o_16_20;
						this.rank4_l_16_20 = response.data[x].rank4_l_16_20;
						this.rank4_h_16_20 = response.data[x].rank4_h_16_20;
						this.rank4_c_16_20 = response.data[x].rank4_c_16_20;
						this.rank4_v_16_20 = response.data[x].rank4_v_16_20;
						this.rank4_o_20_24 = response.data[x].rank4_o_20_24;
						this.rank4_l_20_24 = response.data[x].rank4_l_20_24;
						this.rank4_h_20_24 = response.data[x].rank4_h_20_24;
						this.rank4_c_20_24 = response.data[x].rank4_c_20_24;
						this.rank4_v_20_24 = response.data[x].rank4_v_20_24;
						
						this.rank5_coin_ticker = response.data[x].rank5_coin_ticker;
						this.rank5_coin_name = response.data[x].rank5_coin_name;
						this.rank5_coin_particle = response.data[x].rank5_coin_particle;
						this.rank5_o_price = response.data[x].rank5_o_price;
						this.rank5_l_price = response.data[x].rank5_l_price;
						this.rank5_h_price = response.data[x].rank5_h_price;
						this.rank5_c_price = response.data[x].rank5_c_price;
						this.rank5_o_c_price = response.data[x].rank5_o_c_price;
						this.rank5_o_c_price_rate = response.data[x].rank5_o_c_price_rate;
						this.rank5_o_c_price_color = response.data[x].rank5_o_c_price_color;
						this.rank5_l_h_price = response.data[x].rank5_l_h_price;
						this.rank5_l_h_price_rate = response.data[x].rank5_l_h_price_rate;
						this.rank5_l_h_price_color = response.data[x].rank5_l_h_price_color;
						this.rank5_lower_price_time = response.data[x].rank5_lower_price_time;
						this.rank5_higher_price_time = response.data[x].rank5_higher_price_time;
						this.rank5_lower_volume_time = response.data[x].rank5_lower_volume_time;
						this.rank5_higher_volume_time = response.data[x].rank5_higher_volume_time;
						this.rank5_o_0_4 = response.data[x].rank5_o_0_4;
						this.rank5_l_0_4 = response.data[x].rank5_l_0_4;
						this.rank5_h_0_4 = response.data[x].rank5_h_0_4;
						this.rank5_c_0_4 = response.data[x].rank5_c_0_4;
						this.rank5_v_0_4 = response.data[x].rank5_v_0_4;
						this.rank5_o_4_8 = response.data[x].rank5_o_4_8;
						this.rank5_l_4_8 = response.data[x].rank5_l_4_8;
						this.rank5_h_4_8 = response.data[x].rank5_h_4_8;
						this.rank5_c_4_8 = response.data[x].rank5_c_4_8;
						this.rank5_v_4_8 = response.data[x].rank5_v_4_8;
						this.rank5_o_8_12 = response.data[x].rank5_o_8_12;
						this.rank5_l_8_12 = response.data[x].rank5_l_8_12;
						this.rank5_h_8_12 = response.data[x].rank5_h_8_12;
						this.rank5_c_8_12 = response.data[x].rank5_c_8_12;
						this.rank5_v_8_12 = response.data[x].rank5_v_8_12;
						this.rank5_o_12_16 = response.data[x].rank5_o_12_16;
						this.rank5_l_12_16 = response.data[x].rank5_l_12_16;
						this.rank5_h_12_16 = response.data[x].rank5_h_12_16;
						this.rank5_c_12_16 = response.data[x].rank5_c_12_16;
						this.rank5_v_12_16 = response.data[x].rank5_v_12_16;
						this.rank5_o_16_20 = response.data[x].rank5_o_16_20;
						this.rank5_l_16_20 = response.data[x].rank5_l_16_20;
						this.rank5_h_16_20 = response.data[x].rank5_h_16_20;
						this.rank5_c_16_20 = response.data[x].rank5_c_16_20;
						this.rank5_v_16_20 = response.data[x].rank5_v_16_20;
						this.rank5_o_20_24 = response.data[x].rank5_o_20_24;
						this.rank5_l_20_24 = response.data[x].rank5_l_20_24;
						this.rank5_h_20_24 = response.data[x].rank5_h_20_24;
						this.rank5_c_20_24 = response.data[x].rank5_c_20_24;
						this.rank5_v_20_24 = response.data[x].rank5_v_20_24;

						setTimeout(this.getYoutubeVideoMake(), 5000);
					}
				}
				this.isLoading = false;
			})
		},
		onRowClicked(params) {
			this.search_date = params.data.yyyymmdd;
			this.search_date_kor = (this.search_date).substring(2, 4) + '년 ';
			if((this.search_date).substring(5, 6) == '0'){
				this.search_date_kor += (this.search_date).substring(6, 7) + '월 '
			}else{
				this.search_date_kor += (this.search_date).substring(5, 7) + '월 '
			}

			if((this.search_date).substring(8, 9) == '0'){
				this.search_date_kor += (this.search_date).substring(9, 10) +'일';
			}else{
				this.search_date_kor += (this.search_date).substring(8, 10) +'일';
			}
			this.search_market = params.data.market;
			
			this.makeRankList();

			this.$router.push('/Video/'+this.search_date+'/'+this.search_market)

			this.btc_coin_ticker = params.data.btc_coin_ticker;
			this.btc_coin_name = params.data.btc_coin_name;
			this.btc_coin_particle = params.data.btc_coin_particle;
			this.btc_o_price = params.data.btc_o_price;
			this.btc_l_price = params.data.btc_l_price;
			this.btc_h_price = params.data.btc_h_price;
			this.btc_c_price = params.data.btc_c_price;
			this.btc_o_c_price = params.data.btc_o_c_price;
			this.btc_o_c_price_rate = params.data.btc_o_c_price_rate;
			this.btc_o_c_price_color = params.data.btc_o_c_price_color;
			this.btc_l_h_price = params.data.btc_l_h_price;
			this.btc_l_h_price_rate = params.data.btc_l_h_price_rate;
			this.btc_l_h_price_color = params.data.btc_l_h_price_color;
			this.btc_lower_price_time = params.data.btc_lower_price_time;
			this.btc_higher_price_time = params.data.btc_higher_price_time;
			this.btc_lower_volume_time = params.data.btc_lower_volume_time;
			this.btc_higher_volume_time = params.data.btc_higher_volume_time;
			this.btc_o_0_4 = params.data.btc_o_0_4;
			this.btc_l_0_4 = params.data.btc_l_0_4;
			this.btc_h_0_4 = params.data.btc_h_0_4;
			this.btc_c_0_4 = params.data.btc_c_0_4;
			this.btc_v_0_4 = params.data.btc_v_0_4;
			this.btc_o_4_8 = params.data.btc_o_4_8;
			this.btc_l_4_8 = params.data.btc_l_4_8;
			this.btc_h_4_8 = params.data.btc_h_4_8;
			this.btc_c_4_8 = params.data.btc_c_4_8;
			this.btc_v_4_8 = params.data.btc_v_4_8;
			this.btc_o_8_12 = params.data.btc_o_8_12;
			this.btc_l_8_12 = params.data.btc_l_8_12;
			this.btc_h_8_12 = params.data.btc_h_8_12;
			this.btc_c_8_12 = params.data.btc_c_8_12;
			this.btc_v_8_12 = params.data.btc_v_8_12;
			this.btc_o_12_16 = params.data.btc_o_12_16;
			this.btc_l_12_16 = params.data.btc_l_12_16;
			this.btc_h_12_16 = params.data.btc_h_12_16;
			this.btc_c_12_16 = params.data.btc_c_12_16;
			this.btc_v_12_16 = params.data.btc_v_12_16;
			this.btc_o_16_20 = params.data.btc_o_16_20;
			this.btc_l_16_20 = params.data.btc_l_16_20;
			this.btc_h_16_20 = params.data.btc_h_16_20;
			this.btc_c_16_20 = params.data.btc_c_16_20;
			this.btc_v_16_20 = params.data.btc_v_16_20;
			this.btc_o_20_24 = params.data.btc_o_20_24;
			this.btc_l_20_24 = params.data.btc_l_20_24;
			this.btc_h_20_24 = params.data.btc_h_20_24;
			this.btc_c_20_24 = params.data.btc_c_20_24;
			this.btc_v_20_24 = params.data.btc_v_20_24;
			
			this.rank1_coin_ticker = params.data.rank1_coin_ticker;
			this.rank1_coin_name = params.data.rank1_coin_name;
			this.rank1_coin_particle = params.data.rank1_coin_particle;
			this.rank1_o_price = params.data.rank1_o_price;
			this.rank1_l_price = params.data.rank1_l_price;
			this.rank1_h_price = params.data.rank1_h_price;
			this.rank1_c_price = params.data.rank1_c_price;
			this.rank1_o_c_price = params.data.rank1_o_c_price;
			this.rank1_o_c_price_rate = params.data.rank1_o_c_price_rate;
			this.rank1_o_c_price_color = params.data.rank1_o_c_price_color;
			this.rank1_l_h_price = params.data.rank1_l_h_price;
			this.rank1_l_h_price_rate = params.data.rank1_l_h_price_rate;
			this.rank1_l_h_price_color = params.data.rank1_l_h_price_color;
			this.rank1_lower_price_time = params.data.rank1_lower_price_time;
			this.rank1_higher_price_time = params.data.rank1_higher_price_time;
			this.rank1_lower_volume_time = params.data.rank1_lower_volume_time;
			this.rank1_higher_volume_time = params.data.rank1_higher_volume_time;
			this.rank1_o_0_4 = params.data.rank1_o_0_4;
			this.rank1_l_0_4 = params.data.rank1_l_0_4;
			this.rank1_h_0_4 = params.data.rank1_h_0_4;
			this.rank1_c_0_4 = params.data.rank1_c_0_4;
			this.rank1_v_0_4 = params.data.rank1_v_0_4;
			this.rank1_o_4_8 = params.data.rank1_o_4_8;
			this.rank1_l_4_8 = params.data.rank1_l_4_8;
			this.rank1_h_4_8 = params.data.rank1_h_4_8;
			this.rank1_c_4_8 = params.data.rank1_c_4_8;
			this.rank1_v_4_8 = params.data.rank1_v_4_8;
			this.rank1_o_8_12 = params.data.rank1_o_8_12;
			this.rank1_l_8_12 = params.data.rank1_l_8_12;
			this.rank1_h_8_12 = params.data.rank1_h_8_12;
			this.rank1_c_8_12 = params.data.rank1_c_8_12;
			this.rank1_v_8_12 = params.data.rank1_v_8_12;
			this.rank1_o_12_16 = params.data.rank1_o_12_16;
			this.rank1_l_12_16 = params.data.rank1_l_12_16;
			this.rank1_h_12_16 = params.data.rank1_h_12_16;
			this.rank1_c_12_16 = params.data.rank1_c_12_16;
			this.rank1_v_12_16 = params.data.rank1_v_12_16;
			this.rank1_o_16_20 = params.data.rank1_o_16_20;
			this.rank1_l_16_20 = params.data.rank1_l_16_20;
			this.rank1_h_16_20 = params.data.rank1_h_16_20;
			this.rank1_c_16_20 = params.data.rank1_c_16_20;
			this.rank1_v_16_20 = params.data.rank1_v_16_20;
			this.rank1_o_20_24 = params.data.rank1_o_20_24;
			this.rank1_l_20_24 = params.data.rank1_l_20_24;
			this.rank1_h_20_24 = params.data.rank1_h_20_24;
			this.rank1_c_20_24 = params.data.rank1_c_20_24;
			this.rank1_v_20_24 = params.data.rank1_v_20_24;

			this.rank2_coin_ticker = params.data.rank2_coin_ticker;
			this.rank2_coin_name = params.data.rank2_coin_name;
			this.rank2_coin_particle = params.data.rank2_coin_particle;
			this.rank2_o_price = params.data.rank2_o_price;
			this.rank2_l_price = params.data.rank2_l_price;
			this.rank2_h_price = params.data.rank2_h_price;
			this.rank2_c_price = params.data.rank2_c_price;
			this.rank2_o_c_price = params.data.rank2_o_c_price;
			this.rank2_o_c_price_rate = params.data.rank2_o_c_price_rate;
			this.rank2_o_c_price_color = params.data.rank2_o_c_price_color;
			this.rank2_l_h_price = params.data.rank2_l_h_price;
			this.rank2_l_h_price_rate = params.data.rank2_l_h_price_rate;
			this.rank2_l_h_price_color = params.data.rank2_l_h_price_color;
			this.rank2_lower_price_time = params.data.rank2_lower_price_time;
			this.rank2_higher_price_time = params.data.rank2_higher_price_time;
			this.rank2_lower_volume_time = params.data.rank2_lower_volume_time;
			this.rank2_higher_volume_time = params.data.rank2_higher_volume_time;
			this.rank2_o_0_4 = params.data.rank2_o_0_4;
			this.rank2_l_0_4 = params.data.rank2_l_0_4;
			this.rank2_h_0_4 = params.data.rank2_h_0_4;
			this.rank2_c_0_4 = params.data.rank2_c_0_4;
			this.rank2_v_0_4 = params.data.rank2_v_0_4;
			this.rank2_o_4_8 = params.data.rank2_o_4_8;
			this.rank2_l_4_8 = params.data.rank2_l_4_8;
			this.rank2_h_4_8 = params.data.rank2_h_4_8;
			this.rank2_c_4_8 = params.data.rank2_c_4_8;
			this.rank2_v_4_8 = params.data.rank2_v_4_8;
			this.rank2_o_8_12 = params.data.rank2_o_8_12;
			this.rank2_l_8_12 = params.data.rank2_l_8_12;
			this.rank2_h_8_12 = params.data.rank2_h_8_12;
			this.rank2_c_8_12 = params.data.rank2_c_8_12;
			this.rank2_v_8_12 = params.data.rank2_v_8_12;
			this.rank2_o_12_16 = params.data.rank2_o_12_16;
			this.rank2_l_12_16 = params.data.rank2_l_12_16;
			this.rank2_h_12_16 = params.data.rank2_h_12_16;
			this.rank2_c_12_16 = params.data.rank2_c_12_16;
			this.rank2_v_12_16 = params.data.rank2_v_12_16;
			this.rank2_o_16_20 = params.data.rank2_o_16_20;
			this.rank2_l_16_20 = params.data.rank2_l_16_20;
			this.rank2_h_16_20 = params.data.rank2_h_16_20;
			this.rank2_c_16_20 = params.data.rank2_c_16_20;
			this.rank2_v_16_20 = params.data.rank2_v_16_20;
			this.rank2_o_20_24 = params.data.rank2_o_20_24;
			this.rank2_l_20_24 = params.data.rank2_l_20_24;
			this.rank2_h_20_24 = params.data.rank2_h_20_24;
			this.rank2_c_20_24 = params.data.rank2_c_20_24;
			this.rank2_v_20_24 = params.data.rank2_v_20_24;
			
			this.rank3_coin_ticker = params.data.rank3_coin_ticker;
			this.rank3_coin_name = params.data.rank3_coin_name;
			this.rank3_coin_particle = params.data.rank3_coin_particle;
			this.rank3_o_price = params.data.rank3_o_price;
			this.rank3_l_price = params.data.rank3_l_price;
			this.rank3_h_price = params.data.rank3_h_price;
			this.rank3_c_price = params.data.rank3_c_price;
			this.rank3_o_c_price = params.data.rank3_o_c_price;
			this.rank3_o_c_price_rate = params.data.rank3_o_c_price_rate;
			this.rank3_o_c_price_color = params.data.rank3_o_c_price_color;
			this.rank3_l_h_price = params.data.rank3_l_h_price;
			this.rank3_l_h_price_rate = params.data.rank3_l_h_price_rate;
			this.rank3_l_h_price_color = params.data.rank3_l_h_price_color;
			this.rank3_lower_price_time = params.data.rank3_lower_price_time;
			this.rank3_higher_price_time = params.data.rank3_higher_price_time;
			this.rank3_lower_volume_time = params.data.rank3_lower_volume_time;
			this.rank3_higher_volume_time = params.data.rank3_higher_volume_time;
			this.rank3_o_0_4 = params.data.rank3_o_0_4;
			this.rank3_l_0_4 = params.data.rank3_l_0_4;
			this.rank3_h_0_4 = params.data.rank3_h_0_4;
			this.rank3_c_0_4 = params.data.rank3_c_0_4;
			this.rank3_v_0_4 = params.data.rank3_v_0_4;
			this.rank3_o_4_8 = params.data.rank3_o_4_8;
			this.rank3_l_4_8 = params.data.rank3_l_4_8;
			this.rank3_h_4_8 = params.data.rank3_h_4_8;
			this.rank3_c_4_8 = params.data.rank3_c_4_8;
			this.rank3_v_4_8 = params.data.rank3_v_4_8;
			this.rank3_o_8_12 = params.data.rank3_o_8_12;
			this.rank3_l_8_12 = params.data.rank3_l_8_12;
			this.rank3_h_8_12 = params.data.rank3_h_8_12;
			this.rank3_c_8_12 = params.data.rank3_c_8_12;
			this.rank3_v_8_12 = params.data.rank3_v_8_12;
			this.rank3_o_12_16 = params.data.rank3_o_12_16;
			this.rank3_l_12_16 = params.data.rank3_l_12_16;
			this.rank3_h_12_16 = params.data.rank3_h_12_16;
			this.rank3_c_12_16 = params.data.rank3_c_12_16;
			this.rank3_v_12_16 = params.data.rank3_v_12_16;
			this.rank3_o_16_20 = params.data.rank3_o_16_20;
			this.rank3_l_16_20 = params.data.rank3_l_16_20;
			this.rank3_h_16_20 = params.data.rank3_h_16_20;
			this.rank3_c_16_20 = params.data.rank3_c_16_20;
			this.rank3_v_16_20 = params.data.rank3_v_16_20;
			this.rank3_o_20_24 = params.data.rank3_o_20_24;
			this.rank3_l_20_24 = params.data.rank3_l_20_24;
			this.rank3_h_20_24 = params.data.rank3_h_20_24;
			this.rank3_c_20_24 = params.data.rank3_c_20_24;
			this.rank3_v_20_24 = params.data.rank3_v_20_24;

			this.rank4_coin_ticker = params.data.rank4_coin_ticker;
			this.rank4_coin_name = params.data.rank4_coin_name;
			this.rank4_coin_particle = params.data.rank4_coin_particle;
			this.rank4_o_price = params.data.rank4_o_price;
			this.rank4_l_price = params.data.rank4_l_price;
			this.rank4_h_price = params.data.rank4_h_price;
			this.rank4_c_price = params.data.rank4_c_price;
			this.rank4_o_c_price = params.data.rank4_o_c_price;
			this.rank4_o_c_price_rate = params.data.rank4_o_c_price_rate;
			this.rank4_o_c_price_color = params.data.rank4_o_c_price_color;
			this.rank4_l_h_price = params.data.rank4_l_h_price;
			this.rank4_l_h_price_rate = params.data.rank4_l_h_price_rate;
			this.rank4_l_h_price_color = params.data.rank4_l_h_price_color;
			this.rank4_lower_price_time = params.data.rank4_lower_price_time;
			this.rank4_higher_price_time = params.data.rank4_higher_price_time;
			this.rank4_lower_volume_time = params.data.rank4_lower_volume_time;
			this.rank4_higher_volume_time = params.data.rank4_higher_volume_time;
			this.rank4_o_0_4 = params.data.rank4_o_0_4;
			this.rank4_l_0_4 = params.data.rank4_l_0_4;
			this.rank4_h_0_4 = params.data.rank4_h_0_4;
			this.rank4_c_0_4 = params.data.rank4_c_0_4;
			this.rank4_v_0_4 = params.data.rank4_v_0_4;
			this.rank4_o_4_8 = params.data.rank4_o_4_8;
			this.rank4_l_4_8 = params.data.rank4_l_4_8;
			this.rank4_h_4_8 = params.data.rank4_h_4_8;
			this.rank4_c_4_8 = params.data.rank4_c_4_8;
			this.rank4_v_4_8 = params.data.rank4_v_4_8;
			this.rank4_o_8_12 = params.data.rank4_o_8_12;
			this.rank4_l_8_12 = params.data.rank4_l_8_12;
			this.rank4_h_8_12 = params.data.rank4_h_8_12;
			this.rank4_c_8_12 = params.data.rank4_c_8_12;
			this.rank4_v_8_12 = params.data.rank4_v_8_12;
			this.rank4_o_12_16 = params.data.rank4_o_12_16;
			this.rank4_l_12_16 = params.data.rank4_l_12_16;
			this.rank4_h_12_16 = params.data.rank4_h_12_16;
			this.rank4_c_12_16 = params.data.rank4_c_12_16;
			this.rank4_v_12_16 = params.data.rank4_v_12_16;
			this.rank4_o_16_20 = params.data.rank4_o_16_20;
			this.rank4_l_16_20 = params.data.rank4_l_16_20;
			this.rank4_h_16_20 = params.data.rank4_h_16_20;
			this.rank4_c_16_20 = params.data.rank4_c_16_20;
			this.rank4_v_16_20 = params.data.rank4_v_16_20;
			this.rank4_o_20_24 = params.data.rank4_o_20_24;
			this.rank4_l_20_24 = params.data.rank4_l_20_24;
			this.rank4_h_20_24 = params.data.rank4_h_20_24;
			this.rank4_c_20_24 = params.data.rank4_c_20_24;
			this.rank4_v_20_24 = params.data.rank4_v_20_24;
			
			this.rank5_coin_ticker = params.data.rank5_coin_ticker;
			this.rank5_coin_name = params.data.rank5_coin_name;
			this.rank5_coin_particle = params.data.rank5_coin_particle;
			this.rank5_o_price = params.data.rank5_o_price;
			this.rank5_l_price = params.data.rank5_l_price;
			this.rank5_h_price = params.data.rank5_h_price;
			this.rank5_c_price = params.data.rank5_c_price;
			this.rank5_o_c_price = params.data.rank5_o_c_price;
			this.rank5_o_c_price_rate = params.data.rank5_o_c_price_rate;
			this.rank5_o_c_price_color = params.data.rank5_o_c_price_color;
			this.rank5_l_h_price = params.data.rank5_l_h_price;
			this.rank5_l_h_price_rate = params.data.rank5_l_h_price_rate;
			this.rank5_l_h_price_color = params.data.rank5_l_h_price_color;
			this.rank5_lower_price_time = params.data.rank5_lower_price_time;
			this.rank5_higher_price_time = params.data.rank5_higher_price_time;
			this.rank5_lower_volume_time = params.data.rank5_lower_volume_time;
			this.rank5_higher_volume_time = params.data.rank5_higher_volume_time;
			this.rank5_o_0_4 = params.data.rank5_o_0_4;
			this.rank5_l_0_4 = params.data.rank5_l_0_4;
			this.rank5_h_0_4 = params.data.rank5_h_0_4;
			this.rank5_c_0_4 = params.data.rank5_c_0_4;
			this.rank5_v_0_4 = params.data.rank5_v_0_4;
			this.rank5_o_4_8 = params.data.rank5_o_4_8;
			this.rank5_l_4_8 = params.data.rank5_l_4_8;
			this.rank5_h_4_8 = params.data.rank5_h_4_8;
			this.rank5_c_4_8 = params.data.rank5_c_4_8;
			this.rank5_v_4_8 = params.data.rank5_v_4_8;
			this.rank5_o_8_12 = params.data.rank5_o_8_12;
			this.rank5_l_8_12 = params.data.rank5_l_8_12;
			this.rank5_h_8_12 = params.data.rank5_h_8_12;
			this.rank5_c_8_12 = params.data.rank5_c_8_12;
			this.rank5_v_8_12 = params.data.rank5_v_8_12;
			this.rank5_o_12_16 = params.data.rank5_o_12_16;
			this.rank5_l_12_16 = params.data.rank5_l_12_16;
			this.rank5_h_12_16 = params.data.rank5_h_12_16;
			this.rank5_c_12_16 = params.data.rank5_c_12_16;
			this.rank5_v_12_16 = params.data.rank5_v_12_16;
			this.rank5_o_16_20 = params.data.rank5_o_16_20;
			this.rank5_l_16_20 = params.data.rank5_l_16_20;
			this.rank5_h_16_20 = params.data.rank5_h_16_20;
			this.rank5_c_16_20 = params.data.rank5_c_16_20;
			this.rank5_v_16_20 = params.data.rank5_v_16_20;
			this.rank5_o_20_24 = params.data.rank5_o_20_24;
			this.rank5_l_20_24 = params.data.rank5_l_20_24;
			this.rank5_h_20_24 = params.data.rank5_h_20_24;
			this.rank5_c_20_24 = params.data.rank5_c_20_24;
			this.rank5_v_20_24 = params.data.rank5_v_20_24;

			setTimeout(this.getYoutubeVideoMake(), 5000);
		},
		getYoutubeVideoMake(){
			if(this.search_date != '' && this.search_market != ''){
				var video_title = ''
				//video_title = this.search_date + ' ';
				video_title = (this.search_date).substring(2, 4) + '년 ';

				if((this.search_date).substring(5, 6) == '0'){
					video_title += (this.search_date).substring(6, 7) + '월 '
				}else{
					video_title += (this.search_date).substring(5, 7) + '월 '
				}

				if((this.search_date).substring(8, 9) == '0'){
					video_title += (this.search_date).substring(9, 10) +'일';
				}else{
					video_title += (this.search_date).substring(8, 10) +'일';
				}

				if(this.search_market == 'upbit'){
					video_title += '<br/>업비트 '
				}else if(this.search_market == 'bithumb'){
					video_title += '<br/>빗썸 '
				}
				video_title += '코인 분석'

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

				this.video_title_html = '<div id="video_title" style="color:black; width:500px; padding-top:50px; padding-bottom:50px;">'
				this.video_title_html += '<h1 style="text-align:center; vertical-align:middle; line-height:1.5; font-size:36px;">'+video_title+'</h1>';
				this.video_title_html += '</div>'
				this.video_title_html += '<br/><br/>';
				this.video_title_html += '<div style="color:black;">';
				this.video_title_html += video_title.replaceAll('<br/><br/>', ' ').replaceAll('<br/>', ' ')+'을 시작하겠습니다.';
				this.video_title_html += '<br/><br/>';
				this.video_title_html += '먼저 기준이 되는 '+this.btc_coin_name+'는 '+this.btc_o_price+'원으로 시작해 젓가 '+this.btc_l_price+'원, 곳가 '+this.btc_h_price+'원을 기록하고 '+this.btc_c_price+'원으로 마감하였고, 시까대비종까는 '+this.btc_o_c_price_rate+'% 상승하였고, 젓가대비곳가는 '+this.btc_l_h_price_rate+'% 상승 하였습니다.';
				this.video_title_html += '<br/><br/>';
				this.video_title_html += '상승율 5위는 '+this.rank5_coin_name+this.rank5_coin_particle+' '+this.rank5_o_price+'원으로 시작해 젓가 '+this.rank5_l_price+'원, 곳가 '+this.rank5_h_price+'원을 기록하고 '+this.rank5_c_price+'원으로 마감하였고, 시까대비종까는 '+this.rank5_o_c_price_rate+'% 상승하였고, 젓가대비곳가는 '+this.rank5_l_h_price_rate+'% 상승 하였습니다.';
				this.video_title_html += '<br/><br/>';
				this.video_title_html += '상승율 4위는 '+this.rank4_coin_name+this.rank4_coin_particle+' '+this.rank4_o_price+'원으로 시작해 젓가 '+this.rank4_l_price+'원, 곳가 '+this.rank4_h_price+'원을 기록하고 '+this.rank4_c_price+'원으로 마감하였고, 시까대비종까는 '+this.rank4_o_c_price_rate+'% 상승하였고, 젓가대비곳가는 '+this.rank4_l_h_price_rate+'% 상승 하였습니다.';
				this.video_title_html += '<br/><br/>';
				this.video_title_html += '상승율 3위는 '+this.rank3_coin_name+this.rank3_coin_particle+' '+this.rank3_o_price+'원으로 시작해 젓가 '+this.rank3_l_price+'원, 곳가 '+this.rank3_h_price+'원을 기록하고 '+this.rank3_c_price+'원으로 마감하였고, 시까대비종까는 '+this.rank3_o_c_price_rate+'% 상승하였고, 젓가대비곳가는 '+this.rank3_l_h_price_rate+'% 상승 하였습니다.';
				this.video_title_html += '<br/><br/>';
				this.video_title_html += '상승율 2위는 '+this.rank2_coin_name+this.rank2_coin_particle+' '+this.rank2_o_price+'원으로 시작해 젓가 '+this.rank2_l_price+'원, 곳가 '+this.rank2_h_price+'원을 기록하고 '+this.rank2_c_price+'원으로 마감하였고, 시까대비종까는 '+this.rank2_o_c_price_rate+'% 상승하였고, 젓가대비곳가는 '+this.rank2_l_h_price_rate+'% 상승 하였습니다.';
				this.video_title_html += '<br/><br/>';
				this.video_title_html += '상승율 1위는 '+this.rank1_coin_name+this.rank1_coin_particle+' '+this.rank1_o_price+'원으로 시작해 젓가 '+this.rank1_l_price+'원, 곳가 '+this.rank1_h_price+'원을 기록하고 '+this.rank1_c_price+'원으로 마감하였고, 시까대비종까는 '+this.rank1_o_c_price_rate+'% 상승하였고, 젓가대비곳가는 '+this.rank1_l_h_price_rate+'% 상승 하였습니다.';
				this.video_title_html += '</div>';
				this.video_title_html += '<br/><br/>';
				
				this.video_html = '';
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

				if(Number(this.btc_o_0_4) > 999){
					this.btc_o_0_4 = Number(this.btc_o_0_4).toLocaleString('ko-KR')
				}else{
					this.btc_o_0_4 = this.btc_o_0_4
				}

				if(Number(this.btc_l_0_4) > 999){
					this.btc_l_0_4 = Number(this.btc_l_0_4).toLocaleString('ko-KR')
				}else{
					this.btc_l_0_4 = this.btc_l_0_4
				}

				if(Number(this.btc_h_0_4) > 999){
					this.btc_h_0_4 = Number(this.btc_h_0_4).toLocaleString('ko-KR')
				}else{
					this.btc_h_0_4 = this.btc_h_0_4
				}

				if(Number(this.btc_c_0_4) > 999){
					this.btc_c_0_4 = Number(this.btc_c_0_4).toLocaleString('ko-KR')
				}else{
					this.btc_c_0_4 = this.btc_c_0_4
				}

				if(Number(this.btc_v_0_4) > 999){
					this.btc_v_0_4 = Number(this.btc_v_0_4).toLocaleString('ko-KR')
				}else{
					this.btc_v_0_4 = this.btc_v_0_4
				}

				if(Number(this.btc_o_4_8) > 999){
					this.btc_o_4_8 = Number(this.btc_o_4_8).toLocaleString('ko-KR')
				}else{
					this.btc_o_4_8 = this.btc_o_4_8
				}

				if(Number(this.btc_l_4_8) > 999){
					this.btc_l_4_8 = Number(this.btc_l_4_8).toLocaleString('ko-KR')
				}else{
					this.btc_l_4_8 = this.btc_l_4_8
				}

				if(Number(this.btc_h_4_8) > 999){
					this.btc_h_4_8 = Number(this.btc_h_4_8).toLocaleString('ko-KR')
				}else{
					this.btc_h_4_8 = this.btc_h_4_8
				}

				if(Number(this.btc_c_4_8) > 999){
					this.btc_c_4_8 = Number(this.btc_c_4_8).toLocaleString('ko-KR')
				}else{
					this.btc_c_4_8 = this.btc_c_4_8
				}

				if(Number(this.btc_v_4_8) > 999){
					this.btc_v_4_8 = Number(this.btc_v_4_8).toLocaleString('ko-KR')
				}else{
					this.btc_v_4_8 = this.btc_v_4_8
				}

				if(Number(this.btc_o_8_12) > 999){
					this.btc_o_8_12 = Number(this.btc_o_8_12).toLocaleString('ko-KR')
				}else{
					this.btc_o_8_12 = this.btc_o_8_12
				}

				if(Number(this.btc_l_8_12) > 999){
					this.btc_l_8_12 = Number(this.btc_l_8_12).toLocaleString('ko-KR')
				}else{
					this.btc_l_8_12 = this.btc_l_8_12
				}

				if(Number(this.btc_h_8_12) > 999){
					this.btc_h_8_12 = Number(this.btc_h_8_12).toLocaleString('ko-KR')
				}else{
					this.btc_h_8_12 = this.btc_h_8_12
				}

				if(Number(this.btc_c_8_12) > 999){
					this.btc_c_8_12 = Number(this.btc_c_8_12).toLocaleString('ko-KR')
				}else{
					this.btc_c_8_12 = this.btc_c_8_12
				}

				if(Number(this.btc_v_8_12) > 999){
					this.btc_v_8_12 = Number(this.btc_v_8_12).toLocaleString('ko-KR')
				}else{
					this.btc_v_8_12 = this.btc_v_8_12
				}

				if(Number(this.btc_o_12_16) > 999){
					this.btc_o_12_16 = Number(this.btc_o_12_16).toLocaleString('ko-KR')
				}else{
					this.btc_o_12_16 = this.btc_o_12_16
				}

				if(Number(this.btc_l_12_16) > 999){
					this.btc_l_12_16 = Number(this.btc_l_12_16).toLocaleString('ko-KR')
				}else{
					this.btc_l_12_16 = this.btc_l_12_16
				}

				if(Number(this.btc_h_12_16) > 999){
					this.btc_h_12_16 = Number(this.btc_h_12_16).toLocaleString('ko-KR')
				}else{
					this.btc_h_12_16 = this.btc_h_12_16
				}

				if(Number(this.btc_c_12_16) > 999){
					this.btc_c_12_16 = Number(this.btc_c_12_16).toLocaleString('ko-KR')
				}else{
					this.btc_c_12_16 = this.btc_c_12_16
				}

				if(Number(this.btc_v_12_16) > 999){
					this.btc_v_12_16 = Number(this.btc_v_12_16).toLocaleString('ko-KR')
				}else{
					this.btc_v_12_16 = this.btc_v_12_16
				}

				if(Number(this.btc_o_16_20) > 999){
					this.btc_o_16_20 = Number(this.btc_o_16_20).toLocaleString('ko-KR')
				}else{
					this.btc_o_16_20 = this.btc_o_16_20
				}

				if(Number(this.btc_l_16_20) > 999){
					this.btc_l_16_20 = Number(this.btc_l_16_20).toLocaleString('ko-KR')
				}else{
					this.btc_l_16_20 = this.btc_l_16_20
				}

				if(Number(this.btc_h_16_20) > 999){
					this.btc_h_16_20 = Number(this.btc_h_16_20).toLocaleString('ko-KR')
				}else{
					this.btc_h_16_20 = this.btc_h_16_20
				}

				if(Number(this.btc_c_16_20) > 999){
					this.btc_c_16_20 = Number(this.btc_c_16_20).toLocaleString('ko-KR')
				}else{
					this.btc_c_16_20 = this.btc_c_16_20
				}

				if(Number(this.btc_v_16_20) > 999){
					this.btc_v_16_20 = Number(this.btc_v_16_20).toLocaleString('ko-KR')
				}else{
					this.btc_v_16_20 = this.btc_v_16_20
				}

				if(Number(this.btc_o_20_24) > 999){
					this.btc_o_20_24 = Number(this.btc_o_20_24).toLocaleString('ko-KR')
				}else{
					this.btc_o_20_24 = this.btc_o_20_24
				}

				if(Number(this.btc_l_20_24) > 999){
					this.btc_l_20_24 = Number(this.btc_l_20_24).toLocaleString('ko-KR')
				}else{
					this.btc_l_20_24 = this.btc_l_20_24
				}

				if(Number(this.btc_h_20_24) > 999){
					this.btc_h_20_24 = Number(this.btc_h_20_24).toLocaleString('ko-KR')
				}else{
					this.btc_h_20_24 = this.btc_h_20_24
				}

				if(Number(this.btc_c_20_24) > 999){
					this.btc_c_20_24 = Number(this.btc_c_20_24).toLocaleString('ko-KR')
				}else{
					this.btc_c_20_24 = this.btc_c_20_24
				}

				if(Number(this.btc_v_20_24) > 999){
					this.btc_v_20_24 = Number(this.btc_v_20_24).toLocaleString('ko-KR')
				}else{
					this.btc_v_20_24 = this.btc_v_20_24
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

				if(Number(this.rank5_o_0_4) > 999){
					this.rank5_o_0_4 = Number(this.rank5_o_0_4).toLocaleString('ko-KR')
				}else{
					this.rank5_o_0_4 = this.rank5_o_0_4
				}

				if(Number(this.rank5_l_0_4) > 999){
					this.rank5_l_0_4 = Number(this.rank5_l_0_4).toLocaleString('ko-KR')
				}else{
					this.rank5_l_0_4 = this.rank5_l_0_4
				}

				if(Number(this.rank5_h_0_4) > 999){
					this.rank5_h_0_4 = Number(this.rank5_h_0_4).toLocaleString('ko-KR')
				}else{
					this.rank5_h_0_4 = this.rank5_h_0_4
				}

				if(Number(this.rank5_c_0_4) > 999){
					this.rank5_c_0_4 = Number(this.rank5_c_0_4).toLocaleString('ko-KR')
				}else{
					this.rank5_c_0_4 = this.rank5_c_0_4
				}

				if(Number(this.rank5_v_0_4) > 999){
					this.rank5_v_0_4 = Number(this.rank5_v_0_4).toLocaleString('ko-KR')
				}else{
					this.rank5_v_0_4 = this.rank5_v_0_4
				}

				if(Number(this.rank5_o_4_8) > 999){
					this.rank5_o_4_8 = Number(this.rank5_o_4_8).toLocaleString('ko-KR')
				}else{
					this.rank5_o_4_8 = this.rank5_o_4_8
				}

				if(Number(this.rank5_l_4_8) > 999){
					this.rank5_l_4_8 = Number(this.rank5_l_4_8).toLocaleString('ko-KR')
				}else{
					this.rank5_l_4_8 = this.rank5_l_4_8
				}

				if(Number(this.rank5_h_4_8) > 999){
					this.rank5_h_4_8 = Number(this.rank5_h_4_8).toLocaleString('ko-KR')
				}else{
					this.rank5_h_4_8 = this.rank5_h_4_8
				}

				if(Number(this.rank5_c_4_8) > 999){
					this.rank5_c_4_8 = Number(this.rank5_c_4_8).toLocaleString('ko-KR')
				}else{
					this.rank5_c_4_8 = this.rank5_c_4_8
				}

				if(Number(this.rank5_v_4_8) > 999){
					this.rank5_v_4_8 = Number(this.rank5_v_4_8).toLocaleString('ko-KR')
				}else{
					this.rank5_v_4_8 = this.rank5_v_4_8
				}

				if(Number(this.rank5_o_8_12) > 999){
					this.rank5_o_8_12 = Number(this.rank5_o_8_12).toLocaleString('ko-KR')
				}else{
					this.rank5_o_8_12 = this.rank5_o_8_12
				}

				if(Number(this.rank5_l_8_12) > 999){
					this.rank5_l_8_12 = Number(this.rank5_l_8_12).toLocaleString('ko-KR')
				}else{
					this.rank5_l_8_12 = this.rank5_l_8_12
				}

				if(Number(this.rank5_h_8_12) > 999){
					this.rank5_h_8_12 = Number(this.rank5_h_8_12).toLocaleString('ko-KR')
				}else{
					this.rank5_h_8_12 = this.rank5_h_8_12
				}

				if(Number(this.rank5_c_8_12) > 999){
					this.rank5_c_8_12 = Number(this.rank5_c_8_12).toLocaleString('ko-KR')
				}else{
					this.rank5_c_8_12 = this.rank5_c_8_12
				}

				if(Number(this.rank5_v_8_12) > 999){
					this.rank5_v_8_12 = Number(this.rank5_v_8_12).toLocaleString('ko-KR')
				}else{
					this.rank5_v_8_12 = this.rank5_v_8_12
				}

				if(Number(this.rank5_o_12_16) > 999){
					this.rank5_o_12_16 = Number(this.rank5_o_12_16).toLocaleString('ko-KR')
				}else{
					this.rank5_o_12_16 = this.rank5_o_12_16
				}

				if(Number(this.rank5_l_12_16) > 999){
					this.rank5_l_12_16 = Number(this.rank5_l_12_16).toLocaleString('ko-KR')
				}else{
					this.rank5_l_12_16 = this.rank5_l_12_16
				}

				if(Number(this.rank5_h_12_16) > 999){
					this.rank5_h_12_16 = Number(this.rank5_h_12_16).toLocaleString('ko-KR')
				}else{
					this.rank5_h_12_16 = this.rank5_h_12_16
				}

				if(Number(this.rank5_c_12_16) > 999){
					this.rank5_c_12_16 = Number(this.rank5_c_12_16).toLocaleString('ko-KR')
				}else{
					this.rank5_c_12_16 = this.rank5_c_12_16
				}

				if(Number(this.rank5_v_12_16) > 999){
					this.rank5_v_12_16 = Number(this.rank5_v_12_16).toLocaleString('ko-KR')
				}else{
					this.rank5_v_12_16 = this.rank5_v_12_16
				}

				if(Number(this.rank5_o_16_20) > 999){
					this.rank5_o_16_20 = Number(this.rank5_o_16_20).toLocaleString('ko-KR')
				}else{
					this.rank5_o_16_20 = this.rank5_o_16_20
				}

				if(Number(this.rank5_l_16_20) > 999){
					this.rank5_l_16_20 = Number(this.rank5_l_16_20).toLocaleString('ko-KR')
				}else{
					this.rank5_l_16_20 = this.rank5_l_16_20
				}

				if(Number(this.rank5_h_16_20) > 999){
					this.rank5_h_16_20 = Number(this.rank5_h_16_20).toLocaleString('ko-KR')
				}else{
					this.rank5_h_16_20 = this.rank5_h_16_20
				}

				if(Number(this.rank5_c_16_20) > 999){
					this.rank5_c_16_20 = Number(this.rank5_c_16_20).toLocaleString('ko-KR')
				}else{
					this.rank5_c_16_20 = this.rank5_c_16_20
				}

				if(Number(this.rank5_v_16_20) > 999){
					this.rank5_v_16_20 = Number(this.rank5_v_16_20).toLocaleString('ko-KR')
				}else{
					this.rank5_v_16_20 = this.rank5_v_16_20
				}

				if(Number(this.rank5_o_20_24) > 999){
					this.rank5_o_20_24 = Number(this.rank5_o_20_24).toLocaleString('ko-KR')
				}else{
					this.rank5_o_20_24 = this.rank5_o_20_24
				}

				if(Number(this.rank5_l_20_24) > 999){
					this.rank5_l_20_24 = Number(this.rank5_l_20_24).toLocaleString('ko-KR')
				}else{
					this.rank5_l_20_24 = this.rank5_l_20_24
				}

				if(Number(this.rank5_h_20_24) > 999){
					this.rank5_h_20_24 = Number(this.rank5_h_20_24).toLocaleString('ko-KR')
				}else{
					this.rank5_h_20_24 = this.rank5_h_20_24
				}

				if(Number(this.rank5_c_20_24) > 999){
					this.rank5_c_20_24 = Number(this.rank5_c_20_24).toLocaleString('ko-KR')
				}else{
					this.rank5_c_20_24 = this.rank5_c_20_24
				}

				if(Number(this.rank5_v_20_24) > 999){
					this.rank5_v_20_24 = Number(this.rank5_v_20_24).toLocaleString('ko-KR')
				}else{
					this.rank5_v_20_24 = this.rank5_v_20_24
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

				if(Number(this.rank4_o_0_4) > 999){
					this.rank4_o_0_4 = Number(this.rank4_o_0_4).toLocaleString('ko-KR')
				}else{
					this.rank4_o_0_4 = this.rank4_o_0_4
				}

				if(Number(this.rank4_l_0_4) > 999){
					this.rank4_l_0_4 = Number(this.rank4_l_0_4).toLocaleString('ko-KR')
				}else{
					this.rank4_l_0_4 = this.rank4_l_0_4
				}

				if(Number(this.rank4_h_0_4) > 999){
					this.rank4_h_0_4 = Number(this.rank4_h_0_4).toLocaleString('ko-KR')
				}else{
					this.rank4_h_0_4 = this.rank4_h_0_4
				}

				if(Number(this.rank4_c_0_4) > 999){
					this.rank4_c_0_4 = Number(this.rank4_c_0_4).toLocaleString('ko-KR')
				}else{
					this.rank4_c_0_4 = this.rank4_c_0_4
				}

				if(Number(this.rank4_v_0_4) > 999){
					this.rank4_v_0_4 = Number(this.rank4_v_0_4).toLocaleString('ko-KR')
				}else{
					this.rank4_v_0_4 = this.rank4_v_0_4
				}

				if(Number(this.rank4_o_4_8) > 999){
					this.rank4_o_4_8 = Number(this.rank4_o_4_8).toLocaleString('ko-KR')
				}else{
					this.rank4_o_4_8 = this.rank4_o_4_8
				}

				if(Number(this.rank4_l_4_8) > 999){
					this.rank4_l_4_8 = Number(this.rank4_l_4_8).toLocaleString('ko-KR')
				}else{
					this.rank4_l_4_8 = this.rank4_l_4_8
				}

				if(Number(this.rank4_h_4_8) > 999){
					this.rank4_h_4_8 = Number(this.rank4_h_4_8).toLocaleString('ko-KR')
				}else{
					this.rank4_h_4_8 = this.rank4_h_4_8
				}

				if(Number(this.rank4_c_4_8) > 999){
					this.rank4_c_4_8 = Number(this.rank4_c_4_8).toLocaleString('ko-KR')
				}else{
					this.rank4_c_4_8 = this.rank4_c_4_8
				}

				if(Number(this.rank4_v_4_8) > 999){
					this.rank4_v_4_8 = Number(this.rank4_v_4_8).toLocaleString('ko-KR')
				}else{
					this.rank4_v_4_8 = this.rank4_v_4_8
				}

				if(Number(this.rank4_o_8_12) > 999){
					this.rank4_o_8_12 = Number(this.rank4_o_8_12).toLocaleString('ko-KR')
				}else{
					this.rank4_o_8_12 = this.rank4_o_8_12
				}

				if(Number(this.rank4_l_8_12) > 999){
					this.rank4_l_8_12 = Number(this.rank4_l_8_12).toLocaleString('ko-KR')
				}else{
					this.rank4_l_8_12 = this.rank4_l_8_12
				}

				if(Number(this.rank4_h_8_12) > 999){
					this.rank4_h_8_12 = Number(this.rank4_h_8_12).toLocaleString('ko-KR')
				}else{
					this.rank4_h_8_12 = this.rank4_h_8_12
				}

				if(Number(this.rank4_c_8_12) > 999){
					this.rank4_c_8_12 = Number(this.rank4_c_8_12).toLocaleString('ko-KR')
				}else{
					this.rank4_c_8_12 = this.rank4_c_8_12
				}

				if(Number(this.rank4_v_8_12) > 999){
					this.rank4_v_8_12 = Number(this.rank4_v_8_12).toLocaleString('ko-KR')
				}else{
					this.rank4_v_8_12 = this.rank4_v_8_12
				}

				if(Number(this.rank4_o_12_16) > 999){
					this.rank4_o_12_16 = Number(this.rank4_o_12_16).toLocaleString('ko-KR')
				}else{
					this.rank4_o_12_16 = this.rank4_o_12_16
				}

				if(Number(this.rank4_l_12_16) > 999){
					this.rank4_l_12_16 = Number(this.rank4_l_12_16).toLocaleString('ko-KR')
				}else{
					this.rank4_l_12_16 = this.rank4_l_12_16
				}

				if(Number(this.rank4_h_12_16) > 999){
					this.rank4_h_12_16 = Number(this.rank4_h_12_16).toLocaleString('ko-KR')
				}else{
					this.rank4_h_12_16 = this.rank4_h_12_16
				}

				if(Number(this.rank4_c_12_16) > 999){
					this.rank4_c_12_16 = Number(this.rank4_c_12_16).toLocaleString('ko-KR')
				}else{
					this.rank4_c_12_16 = this.rank4_c_12_16
				}

				if(Number(this.rank4_v_12_16) > 999){
					this.rank4_v_12_16 = Number(this.rank4_v_12_16).toLocaleString('ko-KR')
				}else{
					this.rank4_v_12_16 = this.rank4_v_12_16
				}

				if(Number(this.rank4_o_16_20) > 999){
					this.rank4_o_16_20 = Number(this.rank4_o_16_20).toLocaleString('ko-KR')
				}else{
					this.rank4_o_16_20 = this.rank4_o_16_20
				}

				if(Number(this.rank4_l_16_20) > 999){
					this.rank4_l_16_20 = Number(this.rank4_l_16_20).toLocaleString('ko-KR')
				}else{
					this.rank4_l_16_20 = this.rank4_l_16_20
				}

				if(Number(this.rank4_h_16_20) > 999){
					this.rank4_h_16_20 = Number(this.rank4_h_16_20).toLocaleString('ko-KR')
				}else{
					this.rank4_h_16_20 = this.rank4_h_16_20
				}

				if(Number(this.rank4_c_16_20) > 999){
					this.rank4_c_16_20 = Number(this.rank4_c_16_20).toLocaleString('ko-KR')
				}else{
					this.rank4_c_16_20 = this.rank4_c_16_20
				}

				if(Number(this.rank4_v_16_20) > 999){
					this.rank4_v_16_20 = Number(this.rank4_v_16_20).toLocaleString('ko-KR')
				}else{
					this.rank4_v_16_20 = this.rank4_v_16_20
				}

				if(Number(this.rank4_o_20_24) > 999){
					this.rank4_o_20_24 = Number(this.rank4_o_20_24).toLocaleString('ko-KR')
				}else{
					this.rank4_o_20_24 = this.rank4_o_20_24
				}

				if(Number(this.rank4_l_20_24) > 999){
					this.rank4_l_20_24 = Number(this.rank4_l_20_24).toLocaleString('ko-KR')
				}else{
					this.rank4_l_20_24 = this.rank4_l_20_24
				}

				if(Number(this.rank4_h_20_24) > 999){
					this.rank4_h_20_24 = Number(this.rank4_h_20_24).toLocaleString('ko-KR')
				}else{
					this.rank4_h_20_24 = this.rank4_h_20_24
				}

				if(Number(this.rank4_c_20_24) > 999){
					this.rank4_c_20_24 = Number(this.rank4_c_20_24).toLocaleString('ko-KR')
				}else{
					this.rank4_c_20_24 = this.rank4_c_20_24
				}

				if(Number(this.rank4_v_20_24) > 999){
					this.rank4_v_20_24 = Number(this.rank4_v_20_24).toLocaleString('ko-KR')
				}else{
					this.rank4_v_20_24 = this.rank4_v_20_24
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

				if(Number(this.rank3_o_0_4) > 999){
					this.rank3_o_0_4 = Number(this.rank3_o_0_4).toLocaleString('ko-KR')
				}else{
					this.rank3_o_0_4 = this.rank3_o_0_4
				}

				if(Number(this.rank3_l_0_4) > 999){
					this.rank3_l_0_4 = Number(this.rank3_l_0_4).toLocaleString('ko-KR')
				}else{
					this.rank3_l_0_4 = this.rank3_l_0_4
				}

				if(Number(this.rank3_h_0_4) > 999){
					this.rank3_h_0_4 = Number(this.rank3_h_0_4).toLocaleString('ko-KR')
				}else{
					this.rank3_h_0_4 = this.rank3_h_0_4
				}

				if(Number(this.rank3_c_0_4) > 999){
					this.rank3_c_0_4 = Number(this.rank3_c_0_4).toLocaleString('ko-KR')
				}else{
					this.rank3_c_0_4 = this.rank3_c_0_4
				}

				if(Number(this.rank3_v_0_4) > 999){
					this.rank3_v_0_4 = Number(this.rank3_v_0_4).toLocaleString('ko-KR')
				}else{
					this.rank3_v_0_4 = this.rank3_v_0_4
				}

				if(Number(this.rank3_o_4_8) > 999){
					this.rank3_o_4_8 = Number(this.rank3_o_4_8).toLocaleString('ko-KR')
				}else{
					this.rank3_o_4_8 = this.rank3_o_4_8
				}

				if(Number(this.rank3_l_4_8) > 999){
					this.rank3_l_4_8 = Number(this.rank3_l_4_8).toLocaleString('ko-KR')
				}else{
					this.rank3_l_4_8 = this.rank3_l_4_8
				}

				if(Number(this.rank3_h_4_8) > 999){
					this.rank3_h_4_8 = Number(this.rank3_h_4_8).toLocaleString('ko-KR')
				}else{
					this.rank3_h_4_8 = this.rank3_h_4_8
				}

				if(Number(this.rank3_c_4_8) > 999){
					this.rank3_c_4_8 = Number(this.rank3_c_4_8).toLocaleString('ko-KR')
				}else{
					this.rank3_c_4_8 = this.rank3_c_4_8
				}

				if(Number(this.rank3_v_4_8) > 999){
					this.rank3_v_4_8 = Number(this.rank3_v_4_8).toLocaleString('ko-KR')
				}else{
					this.rank3_v_4_8 = this.rank3_v_4_8
				}

				if(Number(this.rank3_o_8_12) > 999){
					this.rank3_o_8_12 = Number(this.rank3_o_8_12).toLocaleString('ko-KR')
				}else{
					this.rank3_o_8_12 = this.rank3_o_8_12
				}

				if(Number(this.rank3_l_8_12) > 999){
					this.rank3_l_8_12 = Number(this.rank3_l_8_12).toLocaleString('ko-KR')
				}else{
					this.rank3_l_8_12 = this.rank3_l_8_12
				}

				if(Number(this.rank3_h_8_12) > 999){
					this.rank3_h_8_12 = Number(this.rank3_h_8_12).toLocaleString('ko-KR')
				}else{
					this.rank3_h_8_12 = this.rank3_h_8_12
				}

				if(Number(this.rank3_c_8_12) > 999){
					this.rank3_c_8_12 = Number(this.rank3_c_8_12).toLocaleString('ko-KR')
				}else{
					this.rank3_c_8_12 = this.rank3_c_8_12
				}

				if(Number(this.rank3_v_8_12) > 999){
					this.rank3_v_8_12 = Number(this.rank3_v_8_12).toLocaleString('ko-KR')
				}else{
					this.rank3_v_8_12 = this.rank3_v_8_12
				}

				if(Number(this.rank3_o_12_16) > 999){
					this.rank3_o_12_16 = Number(this.rank3_o_12_16).toLocaleString('ko-KR')
				}else{
					this.rank3_o_12_16 = this.rank3_o_12_16
				}

				if(Number(this.rank3_l_12_16) > 999){
					this.rank3_l_12_16 = Number(this.rank3_l_12_16).toLocaleString('ko-KR')
				}else{
					this.rank3_l_12_16 = this.rank3_l_12_16
				}

				if(Number(this.rank3_h_12_16) > 999){
					this.rank3_h_12_16 = Number(this.rank3_h_12_16).toLocaleString('ko-KR')
				}else{
					this.rank3_h_12_16 = this.rank3_h_12_16
				}

				if(Number(this.rank3_c_12_16) > 999){
					this.rank3_c_12_16 = Number(this.rank3_c_12_16).toLocaleString('ko-KR')
				}else{
					this.rank3_c_12_16 = this.rank3_c_12_16
				}

				if(Number(this.rank3_v_12_16) > 999){
					this.rank3_v_12_16 = Number(this.rank3_v_12_16).toLocaleString('ko-KR')
				}else{
					this.rank3_v_12_16 = this.rank3_v_12_16
				}

				if(Number(this.rank3_o_16_20) > 999){
					this.rank3_o_16_20 = Number(this.rank3_o_16_20).toLocaleString('ko-KR')
				}else{
					this.rank3_o_16_20 = this.rank3_o_16_20
				}

				if(Number(this.rank3_l_16_20) > 999){
					this.rank3_l_16_20 = Number(this.rank3_l_16_20).toLocaleString('ko-KR')
				}else{
					this.rank3_l_16_20 = this.rank3_l_16_20
				}

				if(Number(this.rank3_h_16_20) > 999){
					this.rank3_h_16_20 = Number(this.rank3_h_16_20).toLocaleString('ko-KR')
				}else{
					this.rank3_h_16_20 = this.rank3_h_16_20
				}

				if(Number(this.rank3_c_16_20) > 999){
					this.rank3_c_16_20 = Number(this.rank3_c_16_20).toLocaleString('ko-KR')
				}else{
					this.rank3_c_16_20 = this.rank3_c_16_20
				}

				if(Number(this.rank3_v_16_20) > 999){
					this.rank3_v_16_20 = Number(this.rank3_v_16_20).toLocaleString('ko-KR')
				}else{
					this.rank3_v_16_20 = this.rank3_v_16_20
				}

				if(Number(this.rank3_o_20_24) > 999){
					this.rank3_o_20_24 = Number(this.rank3_o_20_24).toLocaleString('ko-KR')
				}else{
					this.rank3_o_20_24 = this.rank3_o_20_24
				}

				if(Number(this.rank3_l_20_24) > 999){
					this.rank3_l_20_24 = Number(this.rank3_l_20_24).toLocaleString('ko-KR')
				}else{
					this.rank3_l_20_24 = this.rank3_l_20_24
				}

				if(Number(this.rank3_h_20_24) > 999){
					this.rank3_h_20_24 = Number(this.rank3_h_20_24).toLocaleString('ko-KR')
				}else{
					this.rank3_h_20_24 = this.rank3_h_20_24
				}

				if(Number(this.rank3_c_20_24) > 999){
					this.rank3_c_20_24 = Number(this.rank3_c_20_24).toLocaleString('ko-KR')
				}else{
					this.rank3_c_20_24 = this.rank3_c_20_24
				}

				if(Number(this.rank3_v_20_24) > 999){
					this.rank3_v_20_24 = Number(this.rank3_v_20_24).toLocaleString('ko-KR')
				}else{
					this.rank3_v_20_24 = this.rank3_v_20_24
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

				if(Number(this.rank2_o_0_4) > 999){
					this.rank2_o_0_4 = Number(this.rank2_o_0_4).toLocaleString('ko-KR')
				}else{
					this.rank2_o_0_4 = this.rank2_o_0_4
				}

				if(Number(this.rank2_l_0_4) > 999){
					this.rank2_l_0_4 = Number(this.rank2_l_0_4).toLocaleString('ko-KR')
				}else{
					this.rank2_l_0_4 = this.rank2_l_0_4
				}

				if(Number(this.rank2_h_0_4) > 999){
					this.rank2_h_0_4 = Number(this.rank2_h_0_4).toLocaleString('ko-KR')
				}else{
					this.rank2_h_0_4 = this.rank2_h_0_4
				}

				if(Number(this.rank2_c_0_4) > 999){
					this.rank2_c_0_4 = Number(this.rank2_c_0_4).toLocaleString('ko-KR')
				}else{
					this.rank2_c_0_4 = this.rank2_c_0_4
				}

				if(Number(this.rank2_v_0_4) > 999){
					this.rank2_v_0_4 = Number(this.rank2_v_0_4).toLocaleString('ko-KR')
				}else{
					this.rank2_v_0_4 = this.rank2_v_0_4
				}

				if(Number(this.rank2_o_4_8) > 999){
					this.rank2_o_4_8 = Number(this.rank2_o_4_8).toLocaleString('ko-KR')
				}else{
					this.rank2_o_4_8 = this.rank2_o_4_8
				}

				if(Number(this.rank2_l_4_8) > 999){
					this.rank2_l_4_8 = Number(this.rank2_l_4_8).toLocaleString('ko-KR')
				}else{
					this.rank2_l_4_8 = this.rank2_l_4_8
				}

				if(Number(this.rank2_h_4_8) > 999){
					this.rank2_h_4_8 = Number(this.rank2_h_4_8).toLocaleString('ko-KR')
				}else{
					this.rank2_h_4_8 = this.rank2_h_4_8
				}

				if(Number(this.rank2_c_4_8) > 999){
					this.rank2_c_4_8 = Number(this.rank2_c_4_8).toLocaleString('ko-KR')
				}else{
					this.rank2_c_4_8 = this.rank2_c_4_8
				}

				if(Number(this.rank2_v_4_8) > 999){
					this.rank2_v_4_8 = Number(this.rank2_v_4_8).toLocaleString('ko-KR')
				}else{
					this.rank2_v_4_8 = this.rank2_v_4_8
				}

				if(Number(this.rank2_o_8_12) > 999){
					this.rank2_o_8_12 = Number(this.rank2_o_8_12).toLocaleString('ko-KR')
				}else{
					this.rank2_o_8_12 = this.rank2_o_8_12
				}

				if(Number(this.rank2_l_8_12) > 999){
					this.rank2_l_8_12 = Number(this.rank2_l_8_12).toLocaleString('ko-KR')
				}else{
					this.rank2_l_8_12 = this.rank2_l_8_12
				}

				if(Number(this.rank2_h_8_12) > 999){
					this.rank2_h_8_12 = Number(this.rank2_h_8_12).toLocaleString('ko-KR')
				}else{
					this.rank2_h_8_12 = this.rank2_h_8_12
				}

				if(Number(this.rank2_c_8_12) > 999){
					this.rank2_c_8_12 = Number(this.rank2_c_8_12).toLocaleString('ko-KR')
				}else{
					this.rank2_c_8_12 = this.rank2_c_8_12
				}

				if(Number(this.rank2_v_8_12) > 999){
					this.rank2_v_8_12 = Number(this.rank2_v_8_12).toLocaleString('ko-KR')
				}else{
					this.rank2_v_8_12 = this.rank2_v_8_12
				}

				if(Number(this.rank2_o_12_16) > 999){
					this.rank2_o_12_16 = Number(this.rank2_o_12_16).toLocaleString('ko-KR')
				}else{
					this.rank2_o_12_16 = this.rank2_o_12_16
				}

				if(Number(this.rank2_l_12_16) > 999){
					this.rank2_l_12_16 = Number(this.rank2_l_12_16).toLocaleString('ko-KR')
				}else{
					this.rank2_l_12_16 = this.rank2_l_12_16
				}

				if(Number(this.rank2_h_12_16) > 999){
					this.rank2_h_12_16 = Number(this.rank2_h_12_16).toLocaleString('ko-KR')
				}else{
					this.rank2_h_12_16 = this.rank2_h_12_16
				}

				if(Number(this.rank2_c_12_16) > 999){
					this.rank2_c_12_16 = Number(this.rank2_c_12_16).toLocaleString('ko-KR')
				}else{
					this.rank2_c_12_16 = this.rank2_c_12_16
				}

				if(Number(this.rank2_v_12_16) > 999){
					this.rank2_v_12_16 = Number(this.rank2_v_12_16).toLocaleString('ko-KR')
				}else{
					this.rank2_v_12_16 = this.rank2_v_12_16
				}

				if(Number(this.rank2_o_16_20) > 999){
					this.rank2_o_16_20 = Number(this.rank2_o_16_20).toLocaleString('ko-KR')
				}else{
					this.rank2_o_16_20 = this.rank2_o_16_20
				}

				if(Number(this.rank2_l_16_20) > 999){
					this.rank2_l_16_20 = Number(this.rank2_l_16_20).toLocaleString('ko-KR')
				}else{
					this.rank2_l_16_20 = this.rank2_l_16_20
				}

				if(Number(this.rank2_h_16_20) > 999){
					this.rank2_h_16_20 = Number(this.rank2_h_16_20).toLocaleString('ko-KR')
				}else{
					this.rank2_h_16_20 = this.rank2_h_16_20
				}

				if(Number(this.rank2_c_16_20) > 999){
					this.rank2_c_16_20 = Number(this.rank2_c_16_20).toLocaleString('ko-KR')
				}else{
					this.rank2_c_16_20 = this.rank2_c_16_20
				}

				if(Number(this.rank2_v_16_20) > 999){
					this.rank2_v_16_20 = Number(this.rank2_v_16_20).toLocaleString('ko-KR')
				}else{
					this.rank2_v_16_20 = this.rank2_v_16_20
				}

				if(Number(this.rank2_o_20_24) > 999){
					this.rank2_o_20_24 = Number(this.rank2_o_20_24).toLocaleString('ko-KR')
				}else{
					this.rank2_o_20_24 = this.rank2_o_20_24
				}

				if(Number(this.rank2_l_20_24) > 999){
					this.rank2_l_20_24 = Number(this.rank2_l_20_24).toLocaleString('ko-KR')
				}else{
					this.rank2_l_20_24 = this.rank2_l_20_24
				}

				if(Number(this.rank2_h_20_24) > 999){
					this.rank2_h_20_24 = Number(this.rank2_h_20_24).toLocaleString('ko-KR')
				}else{
					this.rank2_h_20_24 = this.rank2_h_20_24
				}

				if(Number(this.rank2_c_20_24) > 999){
					this.rank2_c_20_24 = Number(this.rank2_c_20_24).toLocaleString('ko-KR')
				}else{
					this.rank2_c_20_24 = this.rank2_c_20_24
				}

				if(Number(this.rank2_v_20_24) > 999){
					this.rank2_v_20_24 = Number(this.rank2_v_20_24).toLocaleString('ko-KR')
				}else{
					this.rank2_v_20_24 = this.rank2_v_20_24
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

				if(Number(this.rank1_o_0_4) > 999){
					this.rank1_o_0_4 = Number(this.rank1_o_0_4).toLocaleString('ko-KR')
				}else{
					this.rank1_o_0_4 = this.rank1_o_0_4
				}

				if(Number(this.rank1_l_0_4) > 999){
					this.rank1_l_0_4 = Number(this.rank1_l_0_4).toLocaleString('ko-KR')
				}else{
					this.rank1_l_0_4 = this.rank1_l_0_4
				}

				if(Number(this.rank1_h_0_4) > 999){
					this.rank1_h_0_4 = Number(this.rank1_h_0_4).toLocaleString('ko-KR')
				}else{
					this.rank1_h_0_4 = this.rank1_h_0_4
				}

				if(Number(this.rank1_c_0_4) > 999){
					this.rank1_c_0_4 = Number(this.rank1_c_0_4).toLocaleString('ko-KR')
				}else{
					this.rank1_c_0_4 = this.rank1_c_0_4
				}

				if(Number(this.rank1_v_0_4) > 999){
					this.rank1_v_0_4 = Number(this.rank1_v_0_4).toLocaleString('ko-KR')
				}else{
					this.rank1_v_0_4 = this.rank1_v_0_4
				}

				if(Number(this.rank1_o_4_8) > 999){
					this.rank1_o_4_8 = Number(this.rank1_o_4_8).toLocaleString('ko-KR')
				}else{
					this.rank1_o_4_8 = this.rank1_o_4_8
				}

				if(Number(this.rank1_l_4_8) > 999){
					this.rank1_l_4_8 = Number(this.rank1_l_4_8).toLocaleString('ko-KR')
				}else{
					this.rank1_l_4_8 = this.rank1_l_4_8
				}

				if(Number(this.rank1_h_4_8) > 999){
					this.rank1_h_4_8 = Number(this.rank1_h_4_8).toLocaleString('ko-KR')
				}else{
					this.rank1_h_4_8 = this.rank1_h_4_8
				}

				if(Number(this.rank1_c_4_8) > 999){
					this.rank1_c_4_8 = Number(this.rank1_c_4_8).toLocaleString('ko-KR')
				}else{
					this.rank1_c_4_8 = this.rank1_c_4_8
				}

				if(Number(this.rank1_v_4_8) > 999){
					this.rank1_v_4_8 = Number(this.rank1_v_4_8).toLocaleString('ko-KR')
				}else{
					this.rank1_v_4_8 = this.rank1_v_4_8
				}

				if(Number(this.rank1_o_8_12) > 999){
					this.rank1_o_8_12 = Number(this.rank1_o_8_12).toLocaleString('ko-KR')
				}else{
					this.rank1_o_8_12 = this.rank1_o_8_12
				}

				if(Number(this.rank1_l_8_12) > 999){
					this.rank1_l_8_12 = Number(this.rank1_l_8_12).toLocaleString('ko-KR')
				}else{
					this.rank1_l_8_12 = this.rank1_l_8_12
				}

				if(Number(this.rank1_h_8_12) > 999){
					this.rank1_h_8_12 = Number(this.rank1_h_8_12).toLocaleString('ko-KR')
				}else{
					this.rank1_h_8_12 = this.rank1_h_8_12
				}

				if(Number(this.rank1_c_8_12) > 999){
					this.rank1_c_8_12 = Number(this.rank1_c_8_12).toLocaleString('ko-KR')
				}else{
					this.rank1_c_8_12 = this.rank1_c_8_12
				}

				if(Number(this.rank1_v_8_12) > 999){
					this.rank1_v_8_12 = Number(this.rank1_v_8_12).toLocaleString('ko-KR')
				}else{
					this.rank1_v_8_12 = this.rank1_v_8_12
				}

				if(Number(this.rank1_o_12_16) > 999){
					this.rank1_o_12_16 = Number(this.rank1_o_12_16).toLocaleString('ko-KR')
				}else{
					this.rank1_o_12_16 = this.rank1_o_12_16
				}

				if(Number(this.rank1_l_12_16) > 999){
					this.rank1_l_12_16 = Number(this.rank1_l_12_16).toLocaleString('ko-KR')
				}else{
					this.rank1_l_12_16 = this.rank1_l_12_16
				}

				if(Number(this.rank1_h_12_16) > 999){
					this.rank1_h_12_16 = Number(this.rank1_h_12_16).toLocaleString('ko-KR')
				}else{
					this.rank1_h_12_16 = this.rank1_h_12_16
				}

				if(Number(this.rank1_c_12_16) > 999){
					this.rank1_c_12_16 = Number(this.rank1_c_12_16).toLocaleString('ko-KR')
				}else{
					this.rank1_c_12_16 = this.rank1_c_12_16
				}

				if(Number(this.rank1_v_12_16) > 999){
					this.rank1_v_12_16 = Number(this.rank1_v_12_16).toLocaleString('ko-KR')
				}else{
					this.rank1_v_12_16 = this.rank1_v_12_16
				}

				if(Number(this.rank1_o_16_20) > 999){
					this.rank1_o_16_20 = Number(this.rank1_o_16_20).toLocaleString('ko-KR')
				}else{
					this.rank1_o_16_20 = this.rank1_o_16_20
				}

				if(Number(this.rank1_l_16_20) > 999){
					this.rank1_l_16_20 = Number(this.rank1_l_16_20).toLocaleString('ko-KR')
				}else{
					this.rank1_l_16_20 = this.rank1_l_16_20
				}

				if(Number(this.rank1_h_16_20) > 999){
					this.rank1_h_16_20 = Number(this.rank1_h_16_20).toLocaleString('ko-KR')
				}else{
					this.rank1_h_16_20 = this.rank1_h_16_20
				}

				if(Number(this.rank1_c_16_20) > 999){
					this.rank1_c_16_20 = Number(this.rank1_c_16_20).toLocaleString('ko-KR')
				}else{
					this.rank1_c_16_20 = this.rank1_c_16_20
				}

				if(Number(this.rank1_v_16_20) > 999){
					this.rank1_v_16_20 = Number(this.rank1_v_16_20).toLocaleString('ko-KR')
				}else{
					this.rank1_v_16_20 = this.rank1_v_16_20
				}

				if(Number(this.rank1_o_20_24) > 999){
					this.rank1_o_20_24 = Number(this.rank1_o_20_24).toLocaleString('ko-KR')
				}else{
					this.rank1_o_20_24 = this.rank1_o_20_24
				}

				if(Number(this.rank1_l_20_24) > 999){
					this.rank1_l_20_24 = Number(this.rank1_l_20_24).toLocaleString('ko-KR')
				}else{
					this.rank1_l_20_24 = this.rank1_l_20_24
				}

				if(Number(this.rank1_h_20_24) > 999){
					this.rank1_h_20_24 = Number(this.rank1_h_20_24).toLocaleString('ko-KR')
				}else{
					this.rank1_h_20_24 = this.rank1_h_20_24
				}

				if(Number(this.rank1_c_20_24) > 999){
					this.rank1_c_20_24 = Number(this.rank1_c_20_24).toLocaleString('ko-KR')
				}else{
					this.rank1_c_20_24 = this.rank1_c_20_24
				}

				if(Number(this.rank1_v_20_24) > 999){
					this.rank1_v_20_24 = Number(this.rank1_v_20_24).toLocaleString('ko-KR')
				}else{
					this.rank1_v_20_24 = this.rank1_v_20_24
				}
				
				if(this.search_market == 'bithumb'){
					this.video_html += '<div id="bithumb_btc_open_summary_graph_script"></div>'
					this.video_html += '<div id="bithumb_btc_open_day_graph_script"></div>'
					this.video_html += '<div id="bithumb_btc_open_hour_graph_script"></div>'
					this.video_html += '<div id="bithumb_btc_open_4hour_graph_script"></div>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<div id="upbit_btc_open_summary_graph_script"></div>'
					this.video_html += '<div id="upbit_btc_open_day_graph_script"></div>'
					this.video_html += '<div id="upbit_btc_open_hour_graph_script"></div>'
					this.video_html += '<div id="upbit_btc_open_4hour_graph_script"></div>'
				}
				/* BTC */
				this.video_html += '<table id="btc_video" style="color:black; border-spacing:0px; border-color:black; font-size:14px; padding: 10px;">'
				this.video_html += '<colgroup>'
				this.video_html += '<col style="width: 60px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 100px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 100px">'
				this.video_html += '</colgroup>'
				this.video_html += '<tbody>'
				this.video_html += '<tr style="border: 1px solid black; height:35px;">'
				this.video_html += '<td rowspan="2" style="height:50px; border: 1px solid black; border-right:0px solid black; font-weight:bold; font-size: 24px; text-align:center;">기준</td>'
				this.video_html += '<td rowspan="2" style="height:50px; border: 1px solid black; font-weight:bold; font-size: 24px; text-align:center;" colspan="7">'+this.btc_coin_name+' ['+this.btc_coin_ticker+']</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">기준시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">시가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">저가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">거래량</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">00:00 ~ 03:59</td>'	
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">09:00 ~ 12:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_o_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.btc_h_price == this.btc_h_0_4) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.btc_h_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.btc_l_price == this.btc_l_0_4) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.btc_l_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_c_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_v_0_4+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">시가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold;" colspan="2">'+this.btc_o_price+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold;" colspan="2">'+this.btc_c_price+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">04:00 ~ 07:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">13:00 ~ 16:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_o_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.btc_h_price == this.btc_h_4_8) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.btc_h_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.btc_l_price == this.btc_l_4_8) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.btc_l_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_c_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_v_4_8+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">저가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold; color:blue;" colspan="2">'+this.btc_l_price+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold; color:red;" colspan="2">'+this.btc_h_price+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">08:00 ~ 11:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">17:00 ~ 20:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_o_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.btc_h_price == this.btc_h_8_12) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.btc_h_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.btc_l_price == this.btc_l_8_12) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.btc_l_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_c_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_v_8_12+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">시가 대비 종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px" colspan="2">'+'<span style="color:'+this.btc_o_c_price_color+';">'+this.btc_o_c_price+'</span> <span style="font-size:15px;">['+'<span style="color:'+this.btc_o_c_price_color+';">'+this.btc_o_c_price_rate+'%'+'</span>]</span></td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">저가 대비 고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px" colspan="2">'+'<span style="color:'+this.btc_l_h_price_color+';">'+this.btc_l_h_price+'</span> <span style="font-size:15px;">['+'<span style="color:'+this.btc_l_h_price_color+';">'+this.btc_l_h_price_rate+'%'+'</span>]</span></td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">12:00 ~ 15:59</td>'	
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">21:00 ~ 00:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_o_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.btc_h_price == this.btc_h_12_16) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.btc_h_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.btc_l_price == this.btc_l_12_16) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.btc_l_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_c_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_v_12_16+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최저가 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.btc_lower_price_time+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최고가 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.btc_higher_price_time+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">16:00 ~ 19:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">01:00 ~ 04:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_o_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.btc_h_price == this.btc_h_16_20) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.btc_h_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.btc_l_price == this.btc_l_16_20) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.btc_l_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_c_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_v_16_20+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최저 거래량 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.btc_lower_volume_time+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최고 거래량 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.btc_higher_volume_time+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">20:00 ~ 23:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">05:00 ~ 08:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_o_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.btc_h_price == this.btc_h_20_24) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.btc_h_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.btc_l_price == this.btc_l_20_24) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.btc_l_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_c_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.btc_v_20_24+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td colspan="14" style="border: 1px solid black; border-top:0px solid black;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_open_hour_btc_graph"></div>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_open_hour_btc_graph"></div>'
				}
				this.video_html += '</td>'
				this.video_html += '</tr>'
				this.video_html += '</tbody>'
				this.video_html += '</table>'
				this.video_html += '<br/><br/>'

				this.video_html += '<table id="rank5_video" style="color:black; border-spacing:0px; border-color:black; font-size:14px; padding: 10px;">'
				this.video_html += '<colgroup>'
				this.video_html += '<col style="width: 60px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 100px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 100px">'
				this.video_html += '</colgroup>'
				this.video_html += '<tbody>'
				this.video_html += '<tr style="border: 1px solid black; height:35px;">'
				this.video_html += '<td rowspan="2" style="height:50px; border: 1px solid black; border-right:0px solid black; font-weight:bold; font-size: 24px; text-align:center;">5위</td>'
				this.video_html += '<td rowspan="2" style="height:50px; border: 1px solid black; font-weight:bold; font-size: 24px; text-align:center;" colspan="7">'+this.rank5_coin_name+' ['+this.rank5_coin_ticker+']</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">기준시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">시가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">저가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">거래량</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">00:00 ~ 03:59</td>'	
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">09:00 ~ 12:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_o_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank5_h_price == this.rank5_h_0_4) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank5_h_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank5_l_price == this.rank5_l_0_4) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank5_l_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_c_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_v_0_4+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">시가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold;" colspan="2">'+this.rank5_o_price+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold;" colspan="2">'+this.rank5_c_price+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">04:00 ~ 07:59</td>'	
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">13:00 ~ 16:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_o_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank5_h_price == this.rank5_h_4_8) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank5_h_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank5_l_price == this.rank5_l_4_8) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank5_l_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_c_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_v_4_8+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">저가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold; color:blue;" colspan="2">'+this.rank5_l_price+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold; color:red;" colspan="2">'+this.rank5_h_price+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">08:00 ~ 11:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">17:00 ~ 20:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_o_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank5_h_price == this.rank5_h_8_12) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank5_h_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank5_l_price == this.rank5_l_8_12) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank5_l_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_c_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_v_8_12+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">시가 대비 종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px" colspan="2">'+'<span style="color:'+this.rank5_o_c_price_color+';">'+this.rank5_o_c_price+'</span> <span style="font-size:15px;">['+'<span style="color:'+this.rank5_o_c_price_color+';">'+this.rank5_o_c_price_rate+'%'+'</span>]</span></td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">저가 대비 고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px" colspan="2">'+'<span style="color:'+this.rank5_l_h_price_color+';">'+this.rank5_l_h_price+'</span> <span style="font-size:15px;">['+'<span style="color:'+this.rank5_l_h_price_color+';">'+this.rank5_l_h_price_rate+'%'+'</span>]</span></td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">12:00 ~ 15:59</td>'	
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">21:00 ~ 00:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_o_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank5_h_price == this.rank5_h_12_16) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank5_h_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank5_l_price == this.rank5_l_12_16) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank5_l_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_c_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_v_12_16+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최저가 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank5_lower_price_time+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최고가 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank5_higher_price_time+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">16:00 ~ 19:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">01:00 ~ 04:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_o_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank5_h_price == this.rank5_h_16_20) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank5_h_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank5_l_price == this.rank5_l_16_20) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank5_l_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_c_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_v_16_20+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최저 거래량 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank5_lower_volume_time+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최고 거래량 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank5_higher_volume_time+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">20:00 ~ 23:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">05:00 ~ 08:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_o_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank5_h_price == this.rank5_h_20_24) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank5_h_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank5_l_price == this.rank5_l_20_24) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank5_l_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_c_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank5_v_20_24+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td colspan="8" style="border: 1px solid black; border-top:0px solid black;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_open_4hour_rank5_btc_graph"></div>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_open_4hour_rank5_btc_graph"></div>'
				}
				this.video_html += '</td>'
				this.video_html += '<td colspan="6" style="border: 1px solid black; border-top:0px solid black; border-left:0px solid black;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_open_4hour_rank5_graph"></div>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_open_4hour_rank5_graph"></div>'
				}
				this.video_html += '</td>'

				this.video_html += '</tr>'
				this.video_html += '</tbody>'
				this.video_html += '</table>'
				this.video_html += '<br/><br/>'

				this.video_html += '<table id="rank4_video" style="color:black; border-spacing:0px; border-color:black; font-size:14px; padding: 10px;">'
				this.video_html += '<colgroup>'
				this.video_html += '<col style="width: 60px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 100px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 100px">'
				this.video_html += '</colgroup>'
				this.video_html += '<tbody>'
				this.video_html += '<tr style="border: 1px solid black; height:35px;">'
				this.video_html += '<td rowspan="2" style="height:50px; border: 1px solid black; border-right:0px solid black; font-weight:bold; font-size: 24px; text-align:center;">4위</td>'
				this.video_html += '<td rowspan="2" style="height:50px; border: 1px solid black; font-weight:bold; font-size: 24px; text-align:center;" colspan="7">'+this.rank4_coin_name+' ['+this.rank4_coin_ticker+']</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">기준시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">시가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">저가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">거래량</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">00:00 ~ 03:59</td>'	
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">09:00 ~ 12:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_o_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank4_h_price == this.rank4_h_0_4) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank4_h_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank4_l_price == this.rank4_l_0_4) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank4_l_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_c_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_v_0_4+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">시가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold;" colspan="2">'+this.rank4_o_price+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold;" colspan="2">'+this.rank4_c_price+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">04:00 ~ 07:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">13:00 ~ 16:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_o_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank4_h_price == this.rank4_h_4_8) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank4_h_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank4_l_price == this.rank4_l_4_8) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank4_l_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_c_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_v_4_8+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">저가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; color: blue; font-weight:bold;" colspan="2">'+this.rank4_l_price+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; color: red; font-weight:bold;" colspan="2">'+this.rank4_h_price+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">08:00 ~ 11:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">17:00 ~ 20:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_o_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank4_h_price == this.rank4_h_8_12) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank4_h_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank4_l_price == this.rank4_l_8_12) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank4_l_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_c_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_v_8_12+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">시가 대비 종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px" colspan="2">'+'<span style="color:'+this.rank4_o_c_price_color+';">'+this.rank4_o_c_price+'</span> <span style="font-size:15px;">['+'<span style="color:'+this.rank4_o_c_price_color+';">'+this.rank4_o_c_price_rate+'%'+'</span>]</span></td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">저가 대비 고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px" colspan="2">'+'<span style="color:'+this.rank4_l_h_price_color+';">'+this.rank4_l_h_price+'</span> <span style="font-size:15px;">['+'<span style="color:'+this.rank4_l_h_price_color+';">'+this.rank4_l_h_price_rate+'%'+'</span>]</span></td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">12:00 ~ 15:59</td>'	
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">21:00 ~ 00:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_o_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank4_h_price == this.rank4_h_12_16) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank4_h_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank4_l_price == this.rank4_l_12_16) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank4_l_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_c_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_v_12_16+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최저가 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank4_lower_price_time+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최고가 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank4_higher_price_time+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">16:00 ~ 19:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">01:00 ~ 04:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_o_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank4_h_price == this.rank4_h_16_20) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank4_h_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank4_l_price == this.rank4_l_16_20) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank4_l_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_c_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_v_16_20+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최저 거래량 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank4_lower_volume_time+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최고 거래량 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank4_higher_volume_time+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">20:00 ~ 23:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">05:00 ~ 08:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_o_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank4_h_price == this.rank4_h_20_24) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank4_h_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank4_l_price == this.rank4_l_20_24) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank4_l_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_c_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank4_v_20_24+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td colspan="8" style="border: 1px solid black; border-top:0px solid black;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_open_4hour_rank4_btc_graph"></div>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_open_4hour_rank4_btc_graph"></div>'
				}
				this.video_html += '</td>'
				this.video_html += '<td colspan="6" style="border: 1px solid black; border-top:0px solid black; border-left:0px solid black;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_open_4hour_rank4_graph"></div>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_open_4hour_rank4_graph"></div>'
				}
				this.video_html += '</td>'
				this.video_html += '</tr>'
				this.video_html += '</tbody>'
				this.video_html += '</table>'
				this.video_html += '<br/><br/>'

				this.video_html += '<table id="rank3_video" style="color:black; border-spacing:0px; border-color:black; font-size:14px; padding: 10px;">'
				this.video_html += '<colgroup>'
				this.video_html += '<col style="width: 60px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 100px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 100px">'
				this.video_html += '</colgroup>'
				this.video_html += '<tbody>'
				this.video_html += '<tr style="border: 1px solid black; height:35px;">'
				this.video_html += '<td rowspan="2" style="height:50px; border: 1px solid black; border-right:0px solid black; font-weight:bold; font-size: 24px; text-align:center;">3위</td>'
				this.video_html += '<td rowspan="2" style="height:50px; border: 1px solid black; font-weight:bold; font-size: 24px; text-align:center;" colspan="7">'+this.rank3_coin_name+' ['+this.rank3_coin_ticker+']</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">기준시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">시가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">저가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">거래량</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">00:00 ~ 03:59</td>'	
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">09:00 ~ 12:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_o_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank3_h_price == this.rank3_h_0_4) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank3_h_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank3_l_price == this.rank3_l_0_4) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank3_l_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_c_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_v_0_4+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">시가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold;" colspan="2">'+this.rank3_o_price+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold;" colspan="2">'+this.rank3_c_price+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">04:00 ~ 07:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">13:00 ~ 16:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_o_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank3_h_price == this.rank3_h_4_8) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank3_h_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank3_l_price == this.rank3_l_4_8) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank3_l_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_c_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_v_4_8+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">저가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; color: blue; font-weight:bold;" colspan="2">'+this.rank3_l_price+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; color: red; font-weight:bold;" colspan="2">'+this.rank3_h_price+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">08:00 ~ 11:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">17:00 ~ 20:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_o_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank3_h_price == this.rank3_h_8_12) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank3_h_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank3_l_price == this.rank3_l_8_12) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank3_l_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_c_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_v_8_12+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">시가 대비 종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px" colspan="2">'+'<span style="color:'+this.rank3_o_c_price_color+';">'+this.rank3_o_c_price+'</span> <span style="font-size:15px;">['+'<span style="color:'+this.rank3_o_c_price_color+';">'+this.rank3_o_c_price_rate+'%'+'</span>]</span></td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">저가 대비 고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px" colspan="2">'+'<span style="color:'+this.rank3_l_h_price_color+';">'+this.rank3_l_h_price+'</span> <span style="font-size:15px;">['+'<span style="color:'+this.rank3_l_h_price_color+';">'+this.rank3_l_h_price_rate+'%'+'</span>]</span></td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">12:00 ~ 15:59</td>'	
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">21:00 ~ 00:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_o_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank3_h_price == this.rank3_h_12_16) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank3_h_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank3_l_price == this.rank3_l_12_16) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank3_l_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_c_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_v_12_16+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최저가 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank3_lower_price_time+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최고가 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank3_higher_price_time+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">16:00 ~ 19:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">01:00 ~ 04:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_o_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank3_h_price == this.rank3_h_16_20) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank3_h_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank3_l_price == this.rank3_l_16_20) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank3_l_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_c_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_v_16_20+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최저 거래량 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank3_lower_volume_time+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최고 거래량 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank3_higher_volume_time+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">20:00 ~ 23:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">05:00 ~ 08:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_o_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank3_h_price == this.rank3_h_20_24) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank3_h_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank3_l_price == this.rank3_l_20_24) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank3_l_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_c_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank3_v_20_24+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td colspan="8" style="border: 1px solid black; border-top:0px solid black;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_open_4hour_rank3_btc_graph"></div>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_open_4hour_rank3_btc_graph"></div>'
				}
				this.video_html += '</td>'
				this.video_html += '<td colspan="6" style="border: 1px solid black; border-top:0px solid black; border-left:0px solid black;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_open_4hour_rank3_graph"></div>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_open_4hour_rank3_graph"></div>'
				}
				this.video_html += '</td>'
				this.video_html += '</tr>'
				this.video_html += '</tbody>'
				this.video_html += '</table>'
				this.video_html += '<br/><br/>'

				this.video_html += '<table id="rank2_video" style="color:black; border-spacing:0px; border-color:black; font-size:14px; padding: 10px;">'
				this.video_html += '<colgroup>'
				this.video_html += '<col style="width: 60px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 100px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 100px">'
				this.video_html += '</colgroup>'
				this.video_html += '<tbody>'
				this.video_html += '<tr style="border: 1px solid black; height:35px;">'
				this.video_html += '<td rowspan="2" style="height:50px; border: 1px solid black; border-right:0px solid black; font-weight:bold; font-size: 24px; text-align:center;">2위</td>'
				this.video_html += '<td rowspan="2" style="height:50px; border: 1px solid black; font-weight:bold; font-size: 24px; text-align:center;" colspan="7">'+this.rank2_coin_name+' ['+this.rank2_coin_ticker+']</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">기준시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">시가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">저가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">거래량</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">00:00 ~ 03:59</td>'	
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">09:00 ~ 12:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_o_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank2_h_price == this.rank2_h_0_4) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank2_h_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank2_l_price == this.rank2_l_0_4) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank2_l_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_c_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_v_0_4+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">시가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold;" colspan="2">'+this.rank2_o_price+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold;" colspan="2">'+this.rank2_c_price+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">04:00 ~ 07:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">13:00 ~ 16:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_o_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank2_h_price == this.rank2_h_4_8) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank2_h_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank2_l_price == this.rank2_l_4_8) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank2_l_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_c_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_v_4_8+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">저가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; color: blue; font-weight:bold;" colspan="2">'+this.rank2_l_price+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; color: red; font-weight:bold;" colspan="2">'+this.rank2_h_price+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">08:00 ~ 11:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">17:00 ~ 20:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_o_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank2_h_price == this.rank2_h_8_12) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank2_h_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank2_l_price == this.rank2_l_8_12) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank2_l_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_c_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_v_8_12+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">시가 대비 종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px" colspan="2">'+'<span style="color:'+this.rank2_o_c_price_color+';">'+this.rank2_o_c_price+'</span> <span style="font-size:15px;">['+'<span style="color:'+this.rank2_o_c_price_color+';">'+this.rank2_o_c_price_rate+'%'+'</span>]</span></td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">저가 대비 고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px" colspan="2">'+'<span style="color:'+this.rank2_l_h_price_color+';">'+this.rank2_l_h_price+'</span> <span style="font-size:15px;">['+'<span style="color:'+this.rank2_l_h_price_color+';">'+this.rank2_l_h_price_rate+'%'+'</span>]</span></td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">12:00 ~ 15:59</td>'	
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">21:00 ~ 00:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_o_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank2_h_price == this.rank2_h_12_16) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank2_h_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank2_l_price == this.rank2_l_12_16) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank2_l_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_c_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_v_12_16+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최저가 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank2_lower_price_time+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최고가 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank2_higher_price_time+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">16:00 ~ 19:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">01:00 ~ 04:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_o_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank2_h_price == this.rank2_h_16_20) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank2_h_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank2_l_price == this.rank2_l_16_20) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank2_l_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_c_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_v_16_20+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최저 거래량 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank2_lower_volume_time+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최고 거래량 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank2_higher_volume_time+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">20:00 ~ 23:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">05:00 ~ 08:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_o_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank2_h_price == this.rank2_h_20_24) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank2_h_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank2_l_price == this.rank2_l_20_24) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank2_l_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_c_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank2_v_20_24+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td colspan="8" style="border: 1px solid black; border-top:0px solid black;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_open_4hour_rank2_btc_graph"></div>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_open_4hour_rank2_btc_graph"></div>'
				}
				this.video_html += '</td>'
				this.video_html += '<td colspan="6" style="border: 1px solid black; border-top:0px solid black; border-left:0px solid black;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_open_4hour_rank2_graph"></div>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_open_4hour_rank2_graph"></div>'
				}
				this.video_html += '</td>'
				this.video_html += '</tr>'
				this.video_html += '</tbody>'
				this.video_html += '</table>'
				this.video_html += '<br/><br/>'

				this.video_html += '<table id="rank1_video" style="color:black; border-spacing:0px; border-color:black; font-size:14px; padding: 10px;">'
				this.video_html += '<colgroup>'
				this.video_html += '<col style="width: 60px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 50px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 100px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 90px">'
				this.video_html += '<col style="width: 100px">'
				this.video_html += '</colgroup>'
				this.video_html += '<tbody>'
				this.video_html += '<tr style="border: 1px solid black; height:35px;">'
				this.video_html += '<td rowspan="2" style="height:50px; border: 1px solid black; border-right:0px solid black; font-weight:bold; font-size: 24px; text-align:center;">1위</td>'
				this.video_html += '<td rowspan="2" style="height:50px; border: 1px solid black; font-weight:bold; font-size: 24px; text-align:center;" colspan="7">'+this.rank1_coin_name+' ['+this.rank1_coin_ticker+']</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">기준시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">시가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">저가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; text-align:center; font-weight:bold;">거래량</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">00:00 ~ 03:59</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_o_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank1_h_price == this.rank1_h_0_4) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank1_h_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank1_l_price == this.rank1_l_0_4) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank1_l_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_c_0_4+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left: 0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_v_0_4+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">시가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold;" colspan="2">'+this.rank1_o_price+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; font-weight:bold;" colspan="2">'+this.rank1_c_price+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">04:00 ~ 07:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">13:00 ~ 16:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_o_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank1_h_price == this.rank1_h_4_8) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank1_h_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank1_l_price == this.rank1_l_4_8) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank1_l_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_c_4_8+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_v_4_8+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">저가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; color: blue; font-weight:bold;" colspan="2">'+this.rank1_l_price+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px; color: red; font-weight:bold;" colspan="2">'+this.rank1_h_price+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">08:00 ~ 11:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">17:00 ~ 20:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_o_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank1_h_price == this.rank1_h_8_12) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank1_h_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank1_l_price == this.rank1_l_8_12) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank1_l_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_c_8_12+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_v_8_12+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">시가 대비 종가</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px" colspan="2">'+'<span style="color:'+this.rank1_o_c_price_color+';">'+this.rank1_o_c_price+'</span> <span style="font-size:15px;">['+'<span style="color:'+this.rank1_o_c_price_color+';">'+this.rank1_o_c_price_rate+'%'+'</span>]</span></td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">저가 대비 고가</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:right; padding-right:3px; font-size:18px" colspan="2">'+'<span style="color:'+this.rank1_l_h_price_color+';">'+this.rank1_l_h_price+'</span> <span style="font-size:15px;">['+'<span style="color:'+this.rank1_l_h_price_color+';">'+this.rank1_l_h_price_rate+'%'+'</span>]</span></td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">12:00 ~ 15:59</td>'	
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">21:00 ~ 00:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_o_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank1_h_price == this.rank1_h_12_16) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank1_h_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank1_l_price == this.rank1_l_12_16) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank1_l_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_c_12_16+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_v_12_16+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최저가 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank1_lower_price_time+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최고가 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank1_higher_price_time+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">16:00 ~ 19:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">01:00 ~ 04:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_o_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank1_h_price == this.rank1_h_16_20) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank1_h_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank1_l_price == this.rank1_l_16_20) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank1_l_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_c_16_20+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_v_16_20+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최저 거래량 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank1_lower_volume_time+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;" colspan="2">최고 거래량 시간</td>'
				this.video_html += '<td style="border: 1px solid black; border-top:0px solid black; text-align:center; padding-right:3px; font-size:18px" colspan="2">'+this.rank1_higher_volume_time+'</td>'
				if(this.search_market == 'bithumb'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">20:00 ~ 23:59</td>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; text-align:center; font-weight:bold;">05:00 ~ 08:59</td>'
				}
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_o_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;'
				if(this.rank1_h_price == this.rank1_h_20_24) {
					this.video_html += '  font-weight:bold; color:#FF0000 '
				}
				this.video_html += '">'+this.rank1_h_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right; '
				if(this.rank1_l_price == this.rank1_l_20_24) {
					this.video_html += '  font-weight:bold; color:#0000FF '
				}
				this.video_html += '">'+this.rank1_l_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_c_20_24+'</td>'
				this.video_html += '<td style="border: 1px solid black; border-left:0px solid black; border-top:0px solid black; padding-right:3px; text-align:right;">'+this.rank1_v_20_24+'</td>'
				this.video_html += '</tr>'
				this.video_html += '<tr style="height:35px;">'
				this.video_html += '<td colspan="8" style="border: 1px solid black; border-top:0px solid black;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_open_4hour_rank1_btc_graph"></div>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_open_4hour_rank1_btc_graph"></div>'
				}
				this.video_html += '</td>'
				this.video_html += '<td colspan="6" style="border: 1px solid black; border-top:0px solid black; border-left:0px solid black;">'
				if(this.search_market == 'bithumb'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_open_4hour_rank1_graph"></div>'
				}else if(this.search_market == 'upbit'){
					this.video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_open_4hour_rank1_graph"></div>'
				}
				this.video_html += '</td>'
				this.video_html += '</tr>'
				this.video_html += '</tbody>'
				this.video_html += '</table>'
				this.video_html += '<br/><br/>'

				if(this.search_market == 'bithumb'){
					setTimeout(function() {
						var head_bithumb_summary = document.getElementById("bithumb_btc_open_summary_graph_script");
						var script_bithumb_summary = document.createElement('script');
						script_bithumb_summary.type = 'application/javascript';
						script_bithumb_summary.src = '../../../bithumb_btc_open_summary_graph_script.js';
						head_bithumb_summary.appendChild(script_bithumb_summary);

						var head_bithumb_day = document.getElementById("bithumb_btc_open_day_graph_script");
						var script_bithumb_day = document.createElement('script');
						script_bithumb_day.type = 'application/javascript';
						script_bithumb_day.src = '../../../bithumb_btc_open_day_graph_script.js';
						head_bithumb_day.appendChild(script_bithumb_day);

						var head_bithumb_hour = document.getElementById("bithumb_btc_open_hour_graph_script");
						var script_bithumb_hour = document.createElement('script');
						script_bithumb_hour.type = 'application/javascript';
						script_bithumb_hour.src = '../../../bithumb_btc_open_hour_graph_script.js';
						head_bithumb_hour.appendChild(script_bithumb_hour);

						var head_bithumb_4hour = document.getElementById("bithumb_btc_open_4hour_graph_script");
						var script_bithumb_4hour = document.createElement('script');
						script_bithumb_4hour.type = 'application/javascript';
						script_bithumb_4hour.src = '../../../bithumb_btc_open_4hour_graph_script.js';
						head_bithumb_4hour.appendChild(script_bithumb_4hour);
					}, 2500);
				}else if(this.search_market == 'upbit'){
					setTimeout(function() {
						var head_upbit_summary = document.getElementById("upbit_btc_open_summary_graph_script");
						var script_upbit_summary = document.createElement('script');
						script_upbit_summary.type = 'application/javascript';
						script_upbit_summary.src = '../../../upbit_btc_open_summary_graph_script.js';
						head_upbit_summary.appendChild(script_upbit_summary);

						var head_upbit_day = document.getElementById("upbit_btc_open_day_graph_script");
						var script_upbit_day = document.createElement('script');
						script_upbit_day.type = 'application/javascript';
						script_upbit_day.src = '../../../upbit_btc_open_day_graph_script.js';
						head_upbit_day.appendChild(script_upbit_day);

						var head_upbit_hour = document.getElementById("upbit_btc_open_hour_graph_script");
						var script_upbit_hour = document.createElement('script');
						script_upbit_hour.type = 'application/javascript';
						script_upbit_hour.src = '../../../upbit_btc_open_hour_graph_script.js';
						head_upbit_hour.appendChild(script_upbit_hour);

						var head_upbit_4hour = document.getElementById("upbit_btc_open_4hour_graph_script");
						var script_upbit_4hour = document.createElement('script');
						script_upbit_4hour.type = 'application/javascript';
						script_upbit_4hour.src = '../../../upbit_btc_open_4hour_graph_script.js';
						head_upbit_4hour.appendChild(script_upbit_4hour);
					}, 2500);
				}
			}
		},
		makeRankList(){
			if(this.search_market == 'upbit'){
				axios.get('/Main/UPBIT_DAY_RANKING?date='+this.search_date)
				.then(response2 => {
					this.rankList = response2.data;
					
					var temp_video_title_html = '<div style="color:black;">'
					var temp_video_html = ''
					var last_rank = ''
					var total_ranking = String((Number(this.rankList.length)-1));
					var rise_ranking = '0';
					var rise_coin = '';
					var drop_ranking = '0';
					var drop_coin = '';
					var station_ranking = '0';
					var station_coin = '';

					for(var i=1; i<this.rankList.length; i++){
						if(String(this.rankList[i].status) == '상승'){
							rise_ranking = String(Number(rise_ranking)+1)

							if(rise_coin != ''){
								rise_coin += ', '
							}
							rise_coin += this.rankList[i].coin_ticker
						}else if(String(this.rankList[i].status) == '하락'){
							drop_ranking = String(Number(drop_ranking)+1)
							
							if(drop_coin != ''){
								drop_coin += ', '
							}
							drop_coin += this.rankList[i].coin_ticker
						}else{
							station_ranking = String(Number(station_ranking)+1)

							if(station_coin != ''){
								station_coin += ', '
							}
							station_coin += this.rankList[i].coin_ticker
						}
					}

					this.video_html += '<table id="tbl_rank_summary" width="1000px" style="color:black; border-spacing:0px; border-color:black; font-size:13px; padding:10px;">'
					this.video_html += '<colgroup>'
					this.video_html += '<col style="width: 400px">'
					this.video_html += '<col style="width: 500px">'
					this.video_html += '</colgroup>'
					this.video_html += '<tbody>'
					this.video_html += '<tr>'
					this.video_html += '<td colspan="3" style="border: 1px solid black; border-bottom:0px solid black; text-align:center; font-size:32px; font-weight:bold; padding:5px; padding-top:20px; padding-bottom:20px;">'
					this.video_html += this.search_date_kor +' 업비트 코인 분석'
					this.video_html += '</td>'
					this.video_html += '</tr>'
					this.video_html += '<tr>'
					this.video_html += '<td rowspan="3" style="border: 1px solid black; border-right:0px solid black; text-align:center; font-size:20px; font-weight:bold; padding:5px;">'
					this.video_html += '<div style="text-align:center; padding:0px; width:100%;" id="upbit_open_summary_graph"></div>'
					this.video_html += '</td>'
					this.video_html += '<td style="border: 1px solid black; font-size:15px; padding:5px; color:red; font-weight:bold;"'
					if(station_ranking == '0'){
						this.video_html += ' rowspan="2" '
					}
					this.video_html += '>'
					this.video_html += rise_coin
					this.video_html += '</td>'
					this.video_html += '</tr>'
					if(station_ranking == '0'){
						this.video_html += '<tr>'
						this.video_html += '</tr>'
					}else{
						this.video_html += '<tr>'
						this.video_html += '<td style="border: 1px solid black; border-top: 0px solid black; font-size:15px; padding:5px; color:black; font-weight:bold;">'
						this.video_html += station_coin
						this.video_html += '</td>'
						this.video_html += '</tr>'
					}
					this.video_html += '<tr>'
					this.video_html += '<td style="border: 1px solid black; border-top: 0px solid black; font-size:15px; padding:5px; color:blue; font-weight:bold;">'
					this.video_html += drop_coin
					this.video_html += '</td>'
					this.video_html += '</tr>'
					this.video_html += '</tbody>'
					this.video_html += '</table>'
					this.video_html += '</br></br>'

					for(var i=1; i<this.rankList.length; i++){
						var rank_ranking = this.rankList[i].ranking
						var rank_coin_ticker = this.rankList[i].coin_ticker
						var rank_coin_kor_name = this.rankList[i].coin_kor_name
						var rank_status = this.rankList[i].status
						var rank_o_c_rate = this.rankList[i].o_c_price_rate
						var rank_l_h_rate = this.rankList[i].l_h_price_rate
						var rank_open_price = this.rankList[i].o_price
						var rank_low_price = this.rankList[i].l_price
						var rank_high_price = this.rankList[i].h_price
						var rank_close_price = this.rankList[i].c_price

						if(Number(rank_open_price) > 999){
							rank_open_price = Number(rank_open_price).toLocaleString('ko-KR')
						}else{
							rank_open_price = rank_open_price
						}

						if(Number(rank_low_price) > 999){
							rank_low_price = Number(rank_low_price).toLocaleString('ko-KR')
						}else{
							rank_low_price = rank_low_price
						}

						if(Number(rank_high_price) > 999){
							rank_high_price = Number(rank_high_price).toLocaleString('ko-KR')
						}else{
							rank_high_price = rank_high_price
						}

						if(Number(rank_close_price) > 999){
							rank_close_price = Number(rank_close_price).toLocaleString('ko-KR')
						}else{
							rank_close_price = rank_close_price
						}

						rank_o_c_rate = this.zeroCut(rank_o_c_rate)
						rank_l_h_rate = this.zeroCut(rank_l_h_rate)

						if(i%10 == 1){
							if(temp_video_title_html != '<div style="color:black;">'){
								temp_video_title_html += '<br/><br/>'

								temp_video_html += '<tr>'
								temp_video_html += '<td colspan="8" style="border: 1px solid black; border-top: 0px solid black; padding-left: 25px;">'
								temp_video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_btc_open_day_rank'+String(last_rank)+'_graph"></div>'
								temp_video_html += '</td>'
								temp_video_html += '</tr>'
								temp_video_html += '</tbody>'
								temp_video_html += '</table><br/><br/>'
							}else{
								temp_video_title_html += this.search_date_kor + ' 비트코인을 제외한 업비트 코인 총 '+ String(total_ranking) + '개 중'
								
								if(String(rise_ranking) != '0'){
									temp_video_title_html += ' 상승 ' + String(rise_ranking) + '개'
								}
								if(String(station_ranking) != '0'){
									if(String(rise_ranking) != '0'){
										temp_video_title_html += ', '
									}
									temp_video_title_html += ' 보합 ' + String(station_ranking) + '개'
								}
								if(String(drop_ranking) != '0'){
									if(String(rise_ranking) != '0' || String(station_ranking) != '0'){
										temp_video_title_html += ', '
									}
									temp_video_title_html += ' 하락 ' + String(drop_ranking) + '개'
								}
								temp_video_title_html += '를 기록하였습니다. 상세한 변동율 순위를 알아보겠습니다.<br/><br/>'
							}

							last_rank = String(i)
							temp_video_html += '<table id="tbl_rank'+String(i)+'" style="color:black; border-spacing:0px; border-color:black; font-size:13px; padding:10px;">'
							temp_video_html += '<colgroup>'
							temp_video_html += '<col style="width: 70px">'
							temp_video_html += '<col style="width: 320px">'
							temp_video_html += '<col style="width: 170px">'
							temp_video_html += '<col style="width: 170px">'
							temp_video_html += '<col style="width: 160px">'
							temp_video_html += '<col style="width: 160px">'
							temp_video_html += '<col style="width: 160px">'
							temp_video_html += '<col style="width: 160px">'
							temp_video_html += '</colgroup>'
							temp_video_html += '<tbody>'
							temp_video_html += '<tr>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; text-align:center; font-size:20px; font-weight:bold; padding:5px;">순위</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; padding-left:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">코인</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">시가대비종가</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">저가대비고가</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">시가</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">고가</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">저가</td>'
							temp_video_html += '<td style="border: 1px solid black; padding-right:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">종가</td>'
							temp_video_html += '</tr>'
							temp_video_html += '<tr>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-size:18px; font-weight:bold;">기준</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:left; font-size:18px; padding:5px;">비트코인 [BTC]</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;'
							if(String(this.btc_o_price) == String(this.btc_c_price)){
								temp_video_html += ' color:black;'
							}else if(String(this.btc_o_c_price_rate).includes('-')){
								temp_video_html += ' color:blue;'
							}else{
								temp_video_html += ' color:red;'
							}
							temp_video_html += '">'+String(this.btc_o_c_price_rate)+'%</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;'
							if(String(this.btc_l_price) == String(this.btc_h_price)){
								temp_video_html += ' color:black;'
							}else if(String(this.btc_l_h_price_rate).includes('-')){
								temp_video_html += ' color:blue;'
							}else{
								temp_video_html += ' color:red;'
							}
							temp_video_html += '">'+String(this.btc_l_h_price_rate)+'%</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;">'+this.btc_o_price+'</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;">'+this.btc_h_price+'</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;">'+this.btc_l_price+'</td>'
							temp_video_html += '<td style="border: 1px solid black; border-top:0px solid black; padding:5px; text-align:right; font-size:18px;">'+this.btc_c_price+'</td>'
							temp_video_html += '</tr>'
							
							var last_ranking = String(Number(i)+9);
							if(Number(last_ranking) > (Number(this.rankList.length)-1)){
								last_ranking = (Number(this.rankList.length)-1)
							}
							temp_video_title_html += '순위 '+String(i)+'위에서 '+String(last_ranking)+'위는 '
						}else{
							temp_video_title_html += ', '
						}

						temp_video_html += '<tr>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; font-weight:bold; padding-right:5px;">'+rank_ranking+'</td>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:left; font-size:18px; padding:5px;">'+rank_coin_kor_name+' ['+rank_coin_ticker+']</td>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;'
						if(String(rank_open_price) == String(rank_close_price)){
							temp_video_html += ' color:black;'
						}else if(String(rank_o_c_rate).includes('-')){
							temp_video_html += ' color:blue;'
						}else{
							temp_video_html += ' color:red;'
						}
						temp_video_html += '">'+rank_o_c_rate+'%</td>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;'
						if(String(rank_low_price) == String(rank_high_price)){
							temp_video_html += ' color:black;'
						}else if(String(rank_l_h_rate).includes('-')){
							temp_video_html += ' color:blue;'
						}else{
							temp_video_html += ' color:red;'
						}
						temp_video_html += '">'+rank_l_h_rate+'%</td>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;">'+rank_open_price+'</td>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;">'+rank_high_price+'</td>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;">'+rank_low_price+'</td>'
						temp_video_html += '<td style="border: 1px solid black; border-top:0px solid black; padding:5px; text-align:right; font-size:18px;">'+rank_close_price+'</td>'
						temp_video_html += '</tr>'

						temp_video_title_html += rank_coin_kor_name + ' ' +  rank_o_c_rate+ '%'
					}
					temp_video_title_html += '를 기록하였습니다.<br/><br/>'
					temp_video_title_html += '영상 시청 감사드리며, 더 많은 정보가 필요하시면 코인분석기 채널 방문하셔서 좋아요와 구독 부탁 드립니다.'
					temp_video_title_html += '<br/><br/>'
					temp_video_title_html += '<div>'
					
					temp_video_html += '<tr>'
					temp_video_html += '<td colspan="8" style="border: 1px solid black; border-top: 0px solid black; padding-left: 25px;">'
					temp_video_html += '<div style="text-align:left; padding:0px; width:100%;" id="upbit_btc_open_day_rank'+String(last_rank)+'_graph"></div>'
					temp_video_html += '</td>'
					temp_video_html += '</tr>'
					temp_video_html += '</tbody>'
					temp_video_html += '</table>'
					
					this.video_title_html += temp_video_title_html
					this.video_html += temp_video_html
				});
			}else if(this.search_market == 'bithumb'){
				var temp_video_title_html = ''
				axios.get('/Main/BITHUMB_DAY_RANKING?date='+this.search_date)
				.then(response2 => {
					this.rankList = response2.data;
					
					var temp_video_title_html = '<div style="color:black;">'
					var temp_video_html = ''
					var last_rank = ''
					var total_ranking = String((Number(this.rankList.length)-1));
					var rise_ranking = '0';
					var rise_coin = '';
					var drop_ranking = '0';
					var drop_coin = '';
					var station_ranking = '0';
					var station_coin = '';

					for(var i=1; i<this.rankList.length; i++){
						if(String(this.rankList[i].status) == '상승'){
							rise_ranking = String(Number(rise_ranking)+1)

							if(rise_coin != ''){
								rise_coin += ', '
							}
							rise_coin += this.rankList[i].coin_ticker
						}else if(String(this.rankList[i].status) == '하락'){
							drop_ranking = String(Number(drop_ranking)+1)
							
							if(drop_coin != ''){
								drop_coin += ', '
							}
							drop_coin += this.rankList[i].coin_ticker
						}else{
							station_ranking = String(Number(station_ranking)+1)

							if(station_coin != ''){
								station_coin += ', '
							}
							station_coin += this.rankList[i].coin_ticker
						}
					}

					this.video_html += '<table id="tbl_rank_summary" width="1200px" style="color:black; border-spacing:0px; border-color:black; font-size:13px; padding:10px;">'
					this.video_html += '<colgroup>'
					this.video_html += '<col style="width: 400px">'
					this.video_html += '<col style="width: 1200px">'
					this.video_html += '</colgroup>'
					this.video_html += '<tbody>'
					this.video_html += '<tr>'
					this.video_html += '<td colspan="3" style="border: 1px solid black; border-bottom:0px solid black; text-align:center; font-size:32px; font-weight:bold; padding:5px; padding-top:20px; padding-bottom:20px;">'
					this.video_html += this.search_date_kor +' 빗썸 코인 분석'
					this.video_html += '</td>'
					this.video_html += '</tr>'
					this.video_html += '<tr>'
					this.video_html += '<td rowspan="3" style="border: 1px solid black; border-right:0px solid black; text-align:center; font-size:20px; font-weight:bold; padding:5px;">'
					this.video_html += '<div style="text-align:center; padding:0px; width:100%;" id="bithumb_open_summary_graph"></div>'
					this.video_html += '</td>'
					this.video_html += '<td style="border: 1px solid black; font-size:15px; padding:5px; color:red; font-weight:bold;"'
					if(station_ranking == '0'){
						this.video_html += ' rowspan="2" '
					}
					this.video_html += '>'
					this.video_html += rise_coin
					this.video_html += '</td>'
					this.video_html += '</tr>'
					if(station_ranking == '0'){
						this.video_html += '<tr>'
						this.video_html += '</tr>'
					}else{
						this.video_html += '<tr>'
						this.video_html += '<td style="border: 1px solid black; border-top: 0px solid black; font-size:15px; padding:5px; color:black; font-weight:bold;">'
						this.video_html += station_coin
						this.video_html += '</td>'
						this.video_html += '</tr>'
					}
					this.video_html += '<tr>'
					this.video_html += '<td style="border: 1px solid black; border-top: 0px solid black; font-size:15px; padding:5px; color:blue; font-weight:bold;">'
					this.video_html += drop_coin
					this.video_html += '</td>'
					this.video_html += '</tr>'
					this.video_html += '</tbody>'
					this.video_html += '</table>'
					this.video_html += '</br></br>'

					for(var i=1; i<this.rankList.length; i++){
						var rank_ranking = this.rankList[i].ranking
						var rank_coin_ticker = this.rankList[i].coin_ticker
						var rank_coin_kor_name = this.rankList[i].coin_kor_name
						var rank_status = this.rankList[i].status
						var rank_o_c_rate = this.rankList[i].o_c_price_rate
						var rank_l_h_rate = this.rankList[i].l_h_price_rate
						var rank_open_price = this.rankList[i].o_price
						var rank_low_price = this.rankList[i].l_price
						var rank_high_price = this.rankList[i].h_price
						var rank_close_price = this.rankList[i].c_price

						if(Number(rank_open_price) > 999){
							rank_open_price = Number(rank_open_price).toLocaleString('ko-KR')
						}else{
							rank_open_price = rank_open_price
						}

						if(Number(rank_low_price) > 999){
							rank_low_price = Number(rank_low_price).toLocaleString('ko-KR')
						}else{
							rank_low_price = rank_low_price
						}

						if(Number(rank_high_price) > 999){
							rank_high_price = Number(rank_high_price).toLocaleString('ko-KR')
						}else{
							rank_high_price = rank_high_price
						}

						if(Number(rank_close_price) > 999){
							rank_close_price = Number(rank_close_price).toLocaleString('ko-KR')
						}else{
							rank_close_price = rank_close_price
						}

						rank_o_c_rate = this.zeroCut(rank_o_c_rate)
						rank_l_h_rate = this.zeroCut(rank_l_h_rate)

						if(i%10 == 1){
							if(temp_video_title_html != '<div style="color:black;">'){
								temp_video_title_html += '<br/><br/>'

								temp_video_html += '<tr>'
								temp_video_html += '<td colspan="8" style="border: 1px solid black; border-top: 0px solid black; padding-left: 25px;">'
								temp_video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_btc_open_day_rank'+String(last_rank)+'_graph"></div>'
								temp_video_html += '</td>'
								temp_video_html += '</tr>'
								temp_video_html += '</tbody>'
								temp_video_html += '</table><br/><br/>'
							}else{
								temp_video_title_html += this.search_date_kor + ' 비트코인을 제외한 빗썸 코인 총 '+ String(total_ranking) + '개 중'
								
								if(String(rise_ranking) != '0'){
									temp_video_title_html += ' 상승 ' + String(rise_ranking) + '개'
								}
								if(String(station_ranking) != '0'){
									if(String(rise_ranking) != '0'){
										temp_video_title_html += ', '
									}
									temp_video_title_html += ' 보합 ' + String(station_ranking) + '개'
								}
								if(String(drop_ranking) != '0'){
									if(String(rise_ranking) != '0' || String(station_ranking) != '0'){
										temp_video_title_html += ', '
									}
									temp_video_title_html += ' 하락 ' + String(drop_ranking) + '개'
								}
								temp_video_title_html += '를 기록하였습니다. 상세한 변동율 순위를 알아보겠습니다.<br/><br/>'
							}

							last_rank = String(i)
							temp_video_html += '<table id="tbl_rank'+String(i)+'" style="color:black; border-spacing:0px; border-color:black; font-size:13px; padding:10px;">'
							temp_video_html += '<colgroup>'
							temp_video_html += '<col style="width: 70px">'
							temp_video_html += '<col style="width: 320px">'
							temp_video_html += '<col style="width: 170px">'
							temp_video_html += '<col style="width: 170px">'
							temp_video_html += '<col style="width: 160px">'
							temp_video_html += '<col style="width: 160px">'
							temp_video_html += '<col style="width: 160px">'
							temp_video_html += '<col style="width: 160px">'
							temp_video_html += '</colgroup>'
							temp_video_html += '<tbody>'
							temp_video_html += '<tr>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; text-align:center; font-size:20px; font-weight:bold; padding:5px;">순위</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; padding-left:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">코인</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">시가대비종가</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">저가대비고가</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">시가</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">고가</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; padding-right:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">저가</td>'
							temp_video_html += '<td style="border: 1px solid black; padding-right:3px; text-align:center; font-size:20px; font-weight:bold; padding:5px;">종가</td>'
							temp_video_html += '</tr>'
							temp_video_html += '<tr>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:center; font-size:18px; font-weight:bold;">기준</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:left; font-size:18px; padding:5px;">비트코인 [BTC]</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;'
							if(String(this.btc_o_price) == String(this.btc_c_price)){
								temp_video_html += ' color:black;'
							}else if(String(this.btc_o_c_price_rate).includes('-')){
								temp_video_html += ' color:blue;'
							}else{
								temp_video_html += ' color:red;'
							}
							temp_video_html += '">'+String(this.btc_o_c_price_rate)+'%</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;'
							if(String(this.btc_l_price) == String(this.btc_h_price)){
								temp_video_html += ' color:black;'
							}else if(String(this.btc_l_h_price_rate).includes('-')){
								temp_video_html += ' color:blue;'
							}else{
								temp_video_html += ' color:red;'
							}
							temp_video_html += '">'+String(this.btc_l_h_price_rate)+'%</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;">'+this.btc_o_price+'</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;">'+this.btc_h_price+'</td>'
							temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;">'+this.btc_l_price+'</td>'
							temp_video_html += '<td style="border: 1px solid black; border-top:0px solid black; padding:5px; text-align:right; font-size:18px;">'+this.btc_c_price+'</td>'
							temp_video_html += '</tr>'
							
							var last_ranking = String(Number(i)+9);
							if(Number(last_ranking) > (Number(this.rankList.length)-1)){
								last_ranking = (Number(this.rankList.length)-1)
							}
							temp_video_title_html += '순위 '+String(i)+'위에서 '+String(last_ranking)+'위는 '
						}else{
							temp_video_title_html += ', '
						}

						temp_video_html += '<tr>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; font-weight:bold; padding-right:5px;">'+rank_ranking+'</td>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:left; font-size:18px; padding:5px;">'+rank_coin_kor_name+' ['+rank_coin_ticker+']</td>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;'
						if(String(rank_open_price) == String(rank_close_price)){
							temp_video_html += ' color:black;'
						}else if(String(rank_o_c_rate).includes('-')){
							temp_video_html += ' color:blue;'
						}else{
							temp_video_html += ' color:red;'
						}
						temp_video_html += '">'+rank_o_c_rate+'%</td>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;'
						if(String(rank_low_price) == String(rank_high_price)){
							temp_video_html += ' color:black;'
						}else if(String(rank_l_h_rate).includes('-')){
							temp_video_html += ' color:blue;'
						}else{
							temp_video_html += ' color:red;'
						}
						temp_video_html += '">'+rank_l_h_rate+'%</td>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;">'+rank_open_price+'</td>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;">'+rank_high_price+'</td>'
						temp_video_html += '<td style="border: 1px solid black; border-right:0px solid black; border-top:0px solid black; text-align:right; font-size:18px; padding:5px;">'+rank_low_price+'</td>'
						temp_video_html += '<td style="border: 1px solid black; border-top:0px solid black; padding:5px; text-align:right; font-size:18px;">'+rank_close_price+'</td>'
						temp_video_html += '</tr>'

						temp_video_title_html += rank_coin_kor_name + ' ' +  rank_o_c_rate+ '%'
					}
					temp_video_title_html += '를 기록하였습니다.<br/><br/>'
					temp_video_title_html += '영상 시청 감사드리며, 더 많은 정보가 필요하시면 코인분석기 채널 방문하셔서 좋아요와 구독 부탁 드립니다.'
					temp_video_title_html += '<br/><br/>'
					temp_video_title_html += '<div>'
					
					temp_video_html += '<tr>'
					temp_video_html += '<td colspan="8" style="border: 1px solid black; border-top: 0px solid black; padding-left: 25px;">'
					temp_video_html += '<div style="text-align:left; padding:0px; width:100%;" id="bithumb_btc_open_day_rank'+String(last_rank)+'_graph"></div>'
					temp_video_html += '</td>'
					temp_video_html += '</tr>'
					temp_video_html += '</tbody>'
					temp_video_html += '</table>'
					
					this.video_title_html += temp_video_title_html
					this.video_html += temp_video_html
				});
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
