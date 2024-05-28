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
								<v-select 
									v-model="search_day" 
									:items="days_items" 
									item-text="yymmdd_text"
									item-value="yymmdd_val"
									open-on-clear 
									persistent-hint
									style="width:200px;" 
									label="조회일자">
								</v-select>
							</v-col>
							<v-col lg="6" md="6" sm="12" cols="12" style="padding: 20px 50px;">
							</v-col>
						</v-row>
						<v-row>
							<v-col lg="6" md="6" sm="12" cols="12" style="padding-top:0px;">
								<div id="binance_futures_day_analysis_script"></div>
								<div style="text-align:left; padding:0px; width:900px;" id="binance_futures_day_analysis_rank1_graph"></div>
							</v-col>
							<v-col lg="6" md="6" sm="12" cols="12" style="padding-top:0px;">
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
import router from '../../router'

export default {
	data () {
		return {
			search_day : '',
			days_items: []
		}
	},
	components: {
		
	},
	watch: {
		search_day(newDay) {
			if(newDay != ''){
				var data_list = null;
				var dataArray = [];
				var date = newDay
				dataArray.push(['X', 'PERCENT', { role: 'style' }, { role: 'annotation' } ]);
				fetch('/Main/BINANCE_FUTURES_DAY_ANALYSIS?date='+date)
				.then((response) => response.json())
				.then((list) => {
					console.log(list)
					data_list = list;

					if(list.length > 0){

					var btc_coin_ticker = data_list[0]['coin_ticker'];
					var btc_coin_kor_name = data_list[0]['coin_kor_name'];
					var btc_color = data_list[0]['color'];
					var btc_o_c_percent = data_list[0]['o_c_price_rate'];
					var rank_x = ''
					var ranking = ''
					var coin_ticker = ''
					var coin_kor_name = ''
					var color = ''
					var o_c_percent = ''
					var all_color = ''
					
					for(var x=1; x<data_list.length; x++){
						coin_ticker = data_list[x]['coin_ticker'];
						coin_kor_name = data_list[x]['coin_kor_name'];
						color = data_list[x]['color'];
						o_c_percent = data_list[x]['o_c_price_rate'];
						if(x%15==1){
							rank_x = String(x)
							dataArray = [];
							dataArray.push(['X', 'PERCENT', { role: 'style' }, { role: 'annotation' } ]);
							dataArray.push([btc_coin_ticker, Number(btc_o_c_percent), '#F8941C', String(btc_o_c_percent)]);
							dataArray.push([coin_ticker, Number(o_c_percent), color, String(o_c_percent)]);
							
							if(Number(btc_o_c_percent) > 0 && Number(o_c_percent) > 0){
								all_color = color
							}else if(Number(btc_o_c_percent) < 0 && Number(o_c_percent) < 0){
								all_color = color
							}else{
								all_color = ''
							}

							}else if(x%15==0){
								if(all_color != '' && all_color != color){
									all_color = ''
								}
								dataArray.push([coin_ticker, Number(o_c_percent), color, String(o_c_percent)]);
								
								var data = new google.visualization.arrayToDataTable(dataArray);
								var options = {
									bar: {groupWidth: "45%"},
									chartArea: { width: '90%' },
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
									width: 900,
									height: 400
								};

								if(all_color == 'red'){
									options = {
										bar: {groupWidth: "45%"},
										chartArea: { width: '90%' },
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
											viewWindow: { min: 0 }, viewWindowMode: "explicit"
										},
										width: 900,
										height: 400
									};
								}else if(all_color == 'blue'){
									options = {
										bar: {groupWidth: "45%"},
										chartArea: { width: '90%' },
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
											viewWindow: { max: 0 }, viewWindowMode: "explicit"
										},
										width: 900,
										height: 400
									};
								}
								
								var chart = new google.visualization.ColumnChart(document.getElementById('binance_futures_day_analysis_rank'+String(rank_x)+'_graph'));
								chart.draw(data, options);
							}else{
								if(all_color != '' && all_color != color){
									all_color = ''
								}
								
								dataArray.push([coin_ticker, Number(o_c_percent), color, String(o_c_percent)]);
							}
						}
						var data = new google.visualization.arrayToDataTable(dataArray);
						var options = {
							bar: {groupWidth: "45%"},
							chartArea: { width: '90%' },
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
							width: 900,
							height: 400
						};

						if(all_color == 'red'){
							options = {
								bar: {groupWidth: "45%"},
								chartArea: { width: '90%' },
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
									viewWindow: { min: 0 }, viewWindowMode: "explicit"
								},
								width: 900,
								height: 400
							};
						}else if(all_color == 'blue'){
							options = {
								bar: {groupWidth: "45%"},
								chartArea: { width: '90%' },
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
									viewWindow: { max: 0 }, viewWindowMode: "explicit"
								},
								width: 900,
								height: 400
							};
						}
						var chart = new google.visualization.ColumnChart(document.getElementById('binance_futures_day_analysis_rank'+String(rank_x)+'_graph'));
						chart.draw(data, options);
					}
				})
				






				/*
				axios.get('/Main/BINANCE_FUTURES_DAY_ANALYSIS',{
				params: {
						date: newDay
					}
				})
				.then(response => {
					console.log(response.data)
					?
					setTimeout(function() {
						var head_day = document.getElementById("binance_futures_day_analysis_script");
						var script_day = document.createElement('script');
						script_day.type = 'application/javascript';
						script_day.src = '../../../binance_futures_day_analysis_script.js';
						head_day.appendChild(script_day);
					}, 1500);
				})
				*/
			}
		}
	},
	beforeMount() {
       
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
	},
	mounted (){
		
	},
	methods: {
		
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
