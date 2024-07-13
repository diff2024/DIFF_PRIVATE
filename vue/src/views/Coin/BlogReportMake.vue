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
					Blog Report Make
				</v-card-title>
				<v-card-text>
					<v-row>
						<v-col lg="8" md="8" sm="8" cols="8" style="padding-top:0px; padding-bottom:0px; text-align:center;">
							<v-btn depressed dark small color="info" style="float:center; margin-top:17px; font-size:25px; height:45px;">
								<v-icon small>menu</v-icon>&nbsp;<span id="btn_bithumb_make" style="padding-bottom:2px;" @click="BithumbMakeReport">빗썸 일일 리포트 생성</span>
							</v-btn>
						</v-col>
						<v-col lg="4" md="4" sm="4" cols="4" style="padding-top:30px; padding-bottom:0px; text-align:left;">
							<span style="font-size:24px; font-weight:bold; color:black;">{{ bithumb_yn }}</span>
						</v-col>
					</v-row>
					<v-row>
						<v-col lg="8" md="8" sm="8" cols="8" style="padding-top:0px; padding-bottom:0px; text-align:center;">
							<v-btn depressed dark small color="info" style="float:center; margin-top:17px; font-size:25px; height:45px;">
								<v-icon small>menu</v-icon>&nbsp;<span id="btn_upbit_make" style="padding-bottom:2px;" @click="UpbitMakeReport">업비트 일일 리포트 생성</span>
							</v-btn>
						</v-col>
						<v-col lg="4" md="4" sm="4" cols="4" style="padding-top:30px; padding-bottom:0px; text-align:left;">
							<span style="font-size:24px; font-weight:bold; color:black;">{{ upbit_yn }}</span>
						</v-col>
					</v-row>
					<v-row>
						<v-col lg="8" md="8" sm="8" cols="8" style="padding-top:0px; padding-bottom:0px; text-align:center;">
							<v-btn depressed dark small color="info" style="float:center; margin-top:17px; font-size:25px; height:45px;">
								<v-icon small>menu</v-icon>&nbsp;<span id="btn_binance_make" style="padding-bottom:2px;" @click="BinanceMakeReport">바이낸스 일일 리포트 생성</span>
							</v-btn>
						</v-col>
						<v-col lg="4" md="4" sm="4" cols="4" style="padding-top:30px; padding-bottom:0px; text-align:left;">
							<span style="font-size:24px; font-weight:bold; color:black;">{{ binance_yn }}</span>
						</v-col>
					</v-row>
					<v-row>
						<v-col lg="8" md="8" sm="8" cols="8" style="padding-top:0px; padding-bottom:0px; text-align:center;">
							<v-btn depressed dark small color="info" style="float:center; margin-top:17px; font-size:25px; height:45px;">
									<v-icon small>menu</v-icon>&nbsp;<span id="btn_binance_futures_make" style="padding-bottom:2px;" @click="BinanceFuturesMakeReport">바이낸스 선물 일일 리포트 생성</span>
								</v-btn>
						</v-col>
						<v-col lg="4" md="4" sm="4" cols="4" style="padding-top:30px; padding-bottom:0px; text-align:left;">
							<span style="font-size:24px; font-weight:bold; color:black;">{{ binance_futures_yn }}</span>
						</v-col>
					</v-row>
					<v-row>
						<v-col lg="8" md="8" sm="8" cols="8" style="padding-top:0px; padding-bottom:0px; text-align:center;">
							<v-btn depressed dark small color="info" style="float:center; margin-top:17px; font-size:25px; height:45px;">
								<v-icon small>menu</v-icon>&nbsp;<span id="btn_bybit_make" style="padding-bottom:2px;" @click="BybitMakeReport">바이비트 일일 리포트 생성</span>
							</v-btn>
						</v-col>
						<v-col lg="4" md="4" sm="4" cols="4" style="padding-top:30px; padding-bottom:0px; text-align:left;">
							<span style="font-size:24px; font-weight:bold; color:black;">{{ bybit_yn }}</span>
						</v-col>
					</v-row>
				</v-card-text>
			</v-card>
		</v-flex>
	</v-layout>
</template>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
import axios from 'axios';
import Swal from 'sweetalert2'
import LoadingVue from '../../components/LoadingSpinner.vue';

export default {
	data () {
		return {
			isLoading: false,
			timeout: 1500000,
			today_date: '',
			yesterday: '',
			std_date: '',
			end_date: '',
			bithumb_yn: 'N',
			upbit_yn: 'N',
			binance_yn: 'N',
			binance_futures_yn: 'N',
			bybit_yn: 'N',
		}
	},
	components: {
		LoadingVue
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

		Swal.fire({
			icon: 'question',
			text: "빗썸, 업비트에서 데이터를 가져 오시겠습니까?",
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '실행',
			cancelButtonText: '취소',
			allowOutsideClick: false,
		}).then(function (result) {
			if (result.isConfirmed) {
				
				axios.post('/Main/SeverSetting', null,{
					params: {
							option_number: '1',
							option_data: 'Y'
					}
				})
				.then(response => {
					Swal.fire({
						title:'데이터 요청이 정상적으로 완료되었습니다.',
						icon: 'success'
					});
				})
			}
		});
	},
	methods: {
		BithumbMakeReport(){
			this.bithumb_yn = 'Y';

			axios.post('/Bithumb/CoinAnalysisCreate', null,{
				params: {
					date: this.today_date,
					yesterday: this.yesterday
				},
				timeout: this.timeout
			})
			.then(response => {
				this.isLoading = false;
				this.bithumb_yn = 'N';

				Swal.fire({
					title:'일일 리포트 생성이 완료되었습니다.',
					icon: 'success'
				});
			})
			.catch(error => {
				this.bithumb_yn = 'N';

				if (error.code === 'ECONNABORTED') {
					console.log('요청이 타임아웃되었습니다.');
				} else {
					console.error('요청 실패:', error);
				}
			});
		},
		UpbitMakeReport(){
			this.upbit_yn = 'Y';

			axios.post('/Upbit/CoinAnalysisCreate', null,{
				params: {
					date: this.today_date,
					yesterday: this.yesterday
				},
				timeout: this.timeout
			})
			.then(response => {
				this.isLoading = false;
				this.upbit_yn = 'N';

				Swal.fire({
					title:'일일 리포트 생성이 완료되었습니다.',
					icon: 'success'
				});
			})
			.catch(error => {
				this.upbit_yn = 'N';

				if (error.code === 'ECONNABORTED') {
					console.log('요청이 타임아웃되었습니다.');
				} else {
					console.error('요청 실패:', error);
				}
			});
		},
		BinanceMakeReport(){
			this.binance_yn = 'Y';

			axios.post('/Binance/CoinAnalysisCreate', null,{
				params: {
					date: this.today_date,
					yesterday: this.yesterday
				},
				timeout: this.timeout
			})
			.then(response => {
				this.isLoading = false;
				this.binance_yn = 'N';

				Swal.fire({
					title:'일일 리포트 생성이 완료되었습니다.',
					icon: 'success'
				});
			})
			.catch(error => {
				this.binance_yn = 'N';

				if (error.code === 'ECONNABORTED') {
					console.log('요청이 타임아웃되었습니다.');
				} else {
					console.error('요청 실패:', error);
				}
			});
		},
		BinanceFuturesMakeReport(){
			this.binance_futures_yn = 'Y';

			axios.post('/BinanceFutures/CoinAnalysisCreate', null,{
				params: {
					date: this.today_date,
					yesterday: this.yesterday
				},
				timeout: this.timeout
			})
			.then(response => {
				this.isLoading = false;
				this.binance_futures_yn = 'N';

				Swal.fire({
					title:'일일 리포트 생성이 완료되었습니다.',
					icon: 'success'
				});
			})
			.catch(error => {
				this.binance_futures_yn = 'N';

				if (error.code === 'ECONNABORTED') {
					console.log('요청이 타임아웃되었습니다.');
				} else {
					console.error('요청 실패:', error);
				}
			});
		},
		BybitMakeReport(){
			this.bybit_yn = 'Y';

			axios.post('/Bybit/CoinAnalysisCreate', null,{
				params: {
					date: this.today_date,
					yesterday: this.yesterday
				},
				timeout: this.timeout
			})
			.then(response => {
				this.isLoading = false;
				this.bybit_yn = 'N';

				Swal.fire({
					title:'일일 리포트 생성이 완료되었습니다.',
					icon: 'success'
				});
			})
			.catch(error => {
				this.bybit_yn = 'N';

				if (error.code === 'ECONNABORTED') {
					console.log('요청이 타임아웃되었습니다.');
				} else {
					console.error('요청 실패:', error);
				}
			});
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