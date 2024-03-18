<style scoped>
/* 움직이는 텍스트 */
.animated-title {
	font-size:30px; font-family:'Raleway',Sans-serif; font-weight:500; position: relative; width: 100%; max-width:100%; height: auto; padding:73px 0; overflow-x: hidden; overflow-y: hidden; 
}
.animated-title .track {position: absolute; white-space: nowrap;will-change: transform;animation: marquee 60s linear infinite; }
@keyframes marquee {
  from { transform: translateX(0); }
  to { transform: translateX(50%); }
}
@media (hover: hover) and (min-width: 700px){
	.animated-title .notice_content {
			-webkit-transform: translateY(calc(100% - 8rem));
			transform: translateY(calc(100% - 8rem));
			font-weight: 600;
		}
}
</style>
<template>
  <div>
	<v-flex xs12 sm12 md12>
		<v-row>
			<v-col xl="12" md="12" sm="12" style="height:70px; padding-top:0px;"></v-col>
		</v-row>
		<v-row>
			<v-col xl="6" md="6" sm="6" style="text-align:center; padding-top:0px; padding-right:40px; padding-left:25px;">
				<v-row>
					<v-col xl="7" md="7" sm="7" style="padding-left:20px; text-align:left; font-weight:bold; font-size:35px;">
						빗썸 60분 [{{ min60_api_datetime_kst }}]
					</v-col>
					<v-col xl="5" md="5" sm="5" style="padding-right:0px; text-align:right; font-weight:bold; font-size:35px;">
						{{ min60_gijun_datetime_kst }}
					</v-col>
				</v-row>
				<v-row>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						코인
					</v-col>
					<v-col xl="2" md="2" sm="2" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						현재가
					</v-col>
					<v-col xl="2" md="2" sm="2" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						변동률(60분)
					</v-col>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						추정 거래대금(60분)
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min60_rank1_border_color }">
						{{ min60_rank1_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank1_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min60_rank1_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min60_rank1_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min60_rank1_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min60_rank1_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min60_rank1_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min60_rank1_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min60_rank1_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min60_rank1_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank1_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min60_rank2_border_color }">
						{{ min60_rank2_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank2_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min60_rank2_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min60_rank2_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min60_rank2_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min60_rank2_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min60_rank2_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min60_rank2_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min60_rank2_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min60_rank2_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank2_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min60_rank3_border_color }">
						{{ min60_rank3_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank3_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min60_rank3_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min60_rank3_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min60_rank3_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min60_rank3_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min60_rank3_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min60_rank3_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min60_rank3_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min60_rank3_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank3_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min60_rank4_border_color }">
						{{ min60_rank4_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank4_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min60_rank4_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min60_rank4_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min60_rank4_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min60_rank4_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min60_rank4_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min60_rank4_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min60_rank4_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min60_rank4_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank4_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min60_rank5_border_color }">
						{{ min60_rank5_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank5_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min60_rank5_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min60_rank5_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min60_rank5_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min60_rank5_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min60_rank5_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min60_rank5_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min60_rank5_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min60_rank5_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank5_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min60_rank6_border_color }">
						{{ min60_rank6_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank6_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min60_rank6_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min60_rank6_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min60_rank6_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min60_rank6_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min60_rank6_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min60_rank6_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min60_rank6_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min60_rank6_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank6_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min60_rank7_border_color }">
						{{ min60_rank7_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank7_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min60_rank7_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min60_rank7_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min60_rank7_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min60_rank7_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min60_rank7_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min60_rank7_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min60_rank7_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min60_rank7_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank7_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min60_rank8_border_color }">
						{{ min60_rank8_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank8_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min60_rank8_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min60_rank8_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min60_rank8_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min60_rank8_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min60_rank8_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min60_rank8_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min60_rank8_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min60_rank8_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank8_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min60_rank9_border_color }">
						{{ min60_rank9_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank9_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min60_rank9_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min60_rank9_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min60_rank9_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min60_rank9_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min60_rank9_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min60_rank9_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min60_rank9_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min60_rank9_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank9_price_volume }}
					</v-col>
				</v-row>
				
				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min60_rank10_border_color }">
						{{ min60_rank10_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank10_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min60_rank10_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min60_rank10_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min60_rank10_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min60_rank10_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min60_rank10_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min60_rank10_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min60_rank10_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min60_rank10_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank10_price_volume }}
					</v-col>
				</v-row>
			</v-col>
			<v-col xl="6" md="6" sm="6" style="text-align:center; padding-top:0px; padding-left:40px; padding-right:25px;">
				<v-row>
					<v-col xl="7" md="7" sm="7" style="padding-left:20px; text-align:left; font-weight:bold; font-size:35px;">
						빗썸 240분 [{{ min240_api_datetime_kst }}]
					</v-col>
					<v-col xl="5" md="5" sm="5" style="padding-right:25px; text-align:right; font-weight:bold; font-size:35px;">
						{{ min240_gijun_datetime_kst }}
					</v-col>
				</v-row>
				<v-row>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						코인
					</v-col>
					<v-col xl="2" md="2" sm="2" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						현재가
					</v-col>
					<v-col xl="2" md="2" sm="2" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						변동률(240분)
					</v-col>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						추정 거래대금(240분)
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_rank1_border_color }">
						{{ min240_rank1_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank1_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_rank1_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_rank1_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_rank1_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_rank1_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_rank1_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_rank1_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_rank1_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_rank1_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank1_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_rank2_border_color }">
						{{ min240_rank2_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank2_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_rank2_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_rank2_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_rank2_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_rank2_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_rank2_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_rank2_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_rank2_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_rank2_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank2_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_rank3_border_color }">
						{{ min240_rank3_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank3_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_rank3_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_rank3_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_rank3_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_rank3_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_rank3_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_rank3_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_rank3_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_rank3_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank3_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_rank4_border_color }">
						{{ min240_rank4_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank4_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_rank4_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_rank4_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_rank4_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_rank4_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_rank4_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_rank4_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_rank4_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_rank4_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank4_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_rank5_border_color }">
						{{ min240_rank5_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank5_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_rank5_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_rank5_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_rank5_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_rank5_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_rank5_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_rank5_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_rank5_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_rank5_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank5_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_rank6_border_color }">
						{{ min240_rank6_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank6_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_rank6_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_rank6_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_rank6_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_rank6_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_rank6_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_rank6_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_rank6_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_rank6_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank6_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_rank7_border_color }">
						{{ min240_rank7_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank7_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_rank7_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_rank7_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_rank7_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_rank7_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_rank7_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_rank7_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_rank7_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_rank7_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank7_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_rank8_border_color }">
						{{ min240_rank8_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank8_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_rank8_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_rank8_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_rank8_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_rank8_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_rank8_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_rank8_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_rank8_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_rank8_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank8_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_rank9_border_color }">
						{{ min240_rank9_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank9_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_rank9_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_rank9_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_rank9_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_rank9_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_rank9_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_rank9_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_rank9_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_rank9_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank9_price_volume }}
					</v-col>
				</v-row>
				
				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_rank10_border_color }">
						{{ min240_rank10_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank10_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_rank10_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_rank10_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_rank10_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_rank10_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_rank10_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_rank10_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_rank10_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_rank10_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank10_price_volume }}
					</v-col>
				</v-row>
			</v-col>
		</v-row>
	</v-flex>
  </div>
</template>
<script src="https://unpkg.com/vue"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script>
import axios from 'axios';

export default {
	data () {
      	return {
			username: '',
			password: '',
			dialog: false,
			notice_msg: '',
			RequestCompleteYN: 'N',
			/* MIN60 */
			min60_api_datetime_kst : '',
			min60_gijun_datetime_kst : '',

			min60_rank1_coin : '',
			min60_rank1_coin_name : '',
			min60_rank1_border_color : 'white',
			min60_rank1_gubun : '',
			min60_rank1_open : '',
			min60_rank1_low : '',
			min60_rank1_high : '',
			min60_rank1_close : '',
			min60_rank1_o_c_rate : '',
			min60_rank1_o_c_subtract : '',
			min60_rank1_price_volume : '',

			min60_rank2_coin : '',
			min60_rank2_coin_name : '',
			min60_rank2_border_color : 'white',
			min60_rank2_gubun : '',
			min60_rank2_open : '',
			min60_rank2_low : '',
			min60_rank2_high : '',
			min60_rank2_close : '',
			min60_rank2_o_c_rate : '',
			min60_rank2_o_c_subtract : '',
			min60_rank2_price_volume : '',

			min60_rank3_coin : '',
			min60_rank3_coin_name : '',
			min60_rank3_border_color : 'white',
			min60_rank3_gubun : '',
			min60_rank3_open : '',
			min60_rank3_low : '',
			min60_rank3_high : '',
			min60_rank3_close : '',
			min60_rank3_o_c_rate : '',
			min60_rank3_o_c_subtract : '',
			min60_rank3_price_volume : '',

			min60_rank4_coin : '',
			min60_rank4_coin_name : '',
			min60_rank4_border_color : 'white',
			min60_rank4_gubun : '',
			min60_rank4_open : '',
			min60_rank4_low : '',
			min60_rank4_high : '',
			min60_rank4_close : '',
			min60_rank4_o_c_rate : '',
			min60_rank4_o_c_subtract : '',
			min60_rank4_price_volume : '',

			min60_rank5_coin : '',
			min60_rank5_coin_name : '',
			min60_rank5_border_color : 'white',
			min60_rank5_gubun : '',
			min60_rank5_open : '',
			min60_rank5_low : '',
			min60_rank5_high : '',
			min60_rank5_close : '',
			min60_rank5_o_c_rate : '',
			min60_rank5_o_c_subtract : '',
			min60_rank5_price_volume : '',

			min60_rank6_coin : '',
			min60_rank6_coin_name : '',
			min60_rank6_border_color : 'white',
			min60_rank6_gubun : '',
			min60_rank6_open : '',
			min60_rank6_low : '',
			min60_rank6_high : '',
			min60_rank6_close : '',
			min60_rank6_o_c_rate : '',
			min60_rank6_o_c_subtract : '',
			min60_rank6_price_volume : '',

			min60_rank7_coin : '',
			min60_rank7_coin_name : '',
			min60_rank7_border_color : 'white',
			min60_rank7_gubun : '',
			min60_rank7_open : '',
			min60_rank7_low : '',
			min60_rank7_high : '',
			min60_rank7_close : '',
			min60_rank7_o_c_rate : '',
			min60_rank7_o_c_subtract : '',
			min60_rank7_price_volume : '',

			min60_rank8_coin : '',
			min60_rank8_coin_name : '',
			min60_rank8_border_color : 'white',
			min60_rank8_gubun : '',
			min60_rank8_open : '',
			min60_rank8_low : '',
			min60_rank8_high : '',
			min60_rank8_close : '',
			min60_rank8_o_c_rate : '',
			min60_rank8_o_c_subtract : '',
			min60_rank8_price_volume : '',

			min60_rank9_coin : '',
			min60_rank9_coin_name : '',
			min60_rank9_border_color : 'white',
			min60_rank9_gubun : '',
			min60_rank9_open : '',
			min60_rank9_low : '',
			min60_rank9_high : '',
			min60_rank9_close : '',
			min60_rank9_o_c_rate : '',
			min60_rank9_o_c_subtract : '',
			min60_rank9_price_volume : '',

			min60_rank10_coin : '',
			min60_rank10_coin_name : '',
			min60_rank10_border_color : 'white',
			min60_rank10_gubun : '',
			min60_rank10_open : '',
			min60_rank10_low : '',
			min60_rank10_high : '',
			min60_rank10_close : '',
			min60_rank10_o_c_rate : '',
			min60_rank10_o_c_subtract : '',
			min60_rank10_price_volume : '',

			/* MIN240 */
			min240_api_datetime_kst : '',
			min240_gijun_datetime_kst : '',

			min240_rank1_coin : '',
			min240_rank1_coin_name : '',
			min240_rank1_border_color : 'white',
			min240_rank1_gubun : '',
			min240_rank1_open : '',
			min240_rank1_low : '',
			min240_rank1_high : '',
			min240_rank1_close : '',
			min240_rank1_o_c_rate : '',
			min240_rank1_o_c_subtract : '',
			min240_rank1_price_volume : '',

			min240_rank2_coin : '',
			min240_rank2_coin_name : '',
			min240_rank2_border_color : 'white',
			min240_rank2_gubun : '',
			min240_rank2_open : '',
			min240_rank2_low : '',
			min240_rank2_high : '',
			min240_rank2_close : '',
			min240_rank2_o_c_rate : '',
			min240_rank2_o_c_subtract : '',
			min240_rank2_price_volume : '',

			min240_rank3_coin : '',
			min240_rank3_coin_name : '',
			min240_rank3_border_color : 'white',
			min240_rank3_gubun : '',
			min240_rank3_open : '',
			min240_rank3_low : '',
			min240_rank3_high : '',
			min240_rank3_close : '',
			min240_rank3_o_c_rate : '',
			min240_rank3_o_c_subtract : '',
			min240_rank3_price_volume : '',

			min240_rank4_coin : '',
			min240_rank4_coin_name : '',
			min240_rank4_border_color : 'white',
			min240_rank4_gubun : '',
			min240_rank4_open : '',
			min240_rank4_low : '',
			min240_rank4_high : '',
			min240_rank4_close : '',
			min240_rank4_o_c_rate : '',
			min240_rank4_o_c_subtract : '',
			min240_rank4_price_volume : '',

			min240_rank5_coin : '',
			min240_rank5_coin_name : '',
			min240_rank5_border_color : 'white',
			min240_rank5_gubun : '',
			min240_rank5_open : '',
			min240_rank5_low : '',
			min240_rank5_high : '',
			min240_rank5_close : '',
			min240_rank5_o_c_rate : '',
			min240_rank5_o_c_subtract : '',
			min240_rank5_price_volume : '',

			min240_rank6_coin : '',
			min240_rank6_coin_name : '',
			min240_rank6_border_color : 'white',
			min240_rank6_gubun : '',
			min240_rank6_open : '',
			min240_rank6_low : '',
			min240_rank6_high : '',
			min240_rank6_close : '',
			min240_rank6_o_c_rate : '',
			min240_rank6_o_c_subtract : '',
			min240_rank6_price_volume : '',

			min240_rank7_coin : '',
			min240_rank7_coin_name : '',
			min240_rank7_border_color : 'white',
			min240_rank7_gubun : '',
			min240_rank7_open : '',
			min240_rank7_low : '',
			min240_rank7_high : '',
			min240_rank7_close : '',
			min240_rank7_o_c_rate : '',
			min240_rank7_o_c_subtract : '',
			min240_rank7_price_volume : '',

			min240_rank8_coin : '',
			min240_rank8_coin_name : '',
			min240_rank8_border_color : 'white',
			min240_rank8_gubun : '',
			min240_rank8_open : '',
			min240_rank8_low : '',
			min240_rank8_high : '',
			min240_rank8_close : '',
			min240_rank8_o_c_rate : '',
			min240_rank8_o_c_subtract : '',
			min240_rank8_price_volume : '',

			min240_rank9_coin : '',
			min240_rank9_coin_name : '',
			min240_rank9_border_color : 'white',
			min240_rank9_gubun : '',
			min240_rank9_open : '',
			min240_rank9_low : '',
			min240_rank9_high : '',
			min240_rank9_close : '',
			min240_rank9_o_c_rate : '',
			min240_rank9_o_c_subtract : '',
			min240_rank9_price_volume : '',

			min240_rank10_coin : '',
			min240_rank10_coin_name : '',
			min240_rank10_border_color : 'white',
			min240_rank10_gubun : '',
			min240_rank10_open : '',
			min240_rank10_low : '',
			min240_rank10_high : '',
			min240_rank10_close : '',
			min240_rank10_o_c_rate : '',
			min240_rank10_o_c_subtract : '',
			min240_rank10_price_volume : '',
      	}
    },
	components: {
	},
    mounted() {
		this.Data_MIN_60_240_Make();
		this.CurrentDataTime();
		//this.Data_Notice();
		setInterval(this.CurrentDataTime.bind(this) , 1000);
	},
	methods: {
		Data_MIN_60_240_Make(){
			const headerDict = {
				'Cache-Control': 'no-cache',
				'Pragma': 'no-cache',
				'Expires': '0',
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Headers': 'X-Requested-With'
			}

			const requestOptions = {                                                                                                                                                                                 
				headers: new Headers(headerDict), 
			};
			
			this.RequestCompleteYN = 'N'
			var BithumbList = [];
			var tmpBithumbList = [];
			var list60 = [];
			var list240 = [];

			console.log('시작 ' + (new Date()))

			axios.get('/YoutubeBithumb/BithumbCoinInfo')
			.then(response => {
				let start = new Date();
				let end = new Date();
				BithumbList = response.data;
				for(var i=0; i<BithumbList.length; i++){
					var ticker = BithumbList[i].coin_ticker;
					var kor_name = BithumbList[i].coin_kor_name;
					var number = BithumbList[i].coin_number;
					var timestamp5 = BithumbList[i].timestamp5;
					var timestamp15 = BithumbList[i].timestamp15;
					var timestamp60 = BithumbList[i].timestamp60;
					var timestamp240 = BithumbList[i].timestamp240;
					var gijun_datetime_60 = BithumbList[i].gijun_datetime_60;
					var gijun_datetime_240 = BithumbList[i].gijun_datetime_240;
					var gijun_datetime_60 = BithumbList[i].gijun_datetime_60;
					var gijun_datetime_240 = BithumbList[i].gijun_datetime_240;
					
					tmpBithumbList.push('https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/'+String(number)+'_C0100/1H');
					axios.get('https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/'+String(number)+'_C0100/1H',{
						headers: {
							'Cache-Control': 'no-cache',
							'Pragma': 'no-cache',
							'Expires': '0',
						},
					})
					//tmpBithumbList.push('https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/'+String(number)+'_C0100/1H');
					//this.$http.get("https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/"+String(number)+"_C0100/1H", requestOptions)
					.then(response => {
						var FindIndex = tmpBithumbList.findIndex(function(data, index){ 
							//return data === response.url
							return data === response.config.url
						});
						
						var bithumb_list = response.data.data;
						var bithumb_list_timestamp = bithumb_list['t'];
						var bithumb_list_open = bithumb_list['o'];
						var bithumb_list_low = bithumb_list['l'];
						var bithumb_list_high = bithumb_list['h'];
						var bithumb_list_close = bithumb_list['c'];
						var bithumb_list_volume = bithumb_list['v'];

						var YN60 = 'N'
						var YN240 = 'N'

						var o_price_60 = ''
						var l_price_60 = ''
						var h_price_60 = ''
						var c_price_60 = ''
						var o_c_subtract_60 = ''
						var o_c_rate_60 = ''
						var volume_60 = '0'
						var gubun_60 = ''
						var price_volume_60 = ''

						var o_price_240 = ''
						var l_price_240 = ''
						var h_price_240 = ''
						var c_price_240 = ''
						var o_c_subtract_240 = ''
						var o_c_rate_240 = ''
						var volume_240 = '0'
						var gubun_240 = ''
						var price_volume_240 = ''

						for(var a=0; a<bithumb_list_timestamp.length; a++){
							var tmp_timestamp = bithumb_list_timestamp[a]
							var tmp_o_price = bithumb_list_open[a]
							var tmp_l_price = bithumb_list_low[a]
							var tmp_h_price = bithumb_list_high[a]
							var tmp_c_price = bithumb_list_close[a]
							var tmp_volume = bithumb_list_volume[a]
							
							if(BigInt(timestamp60) <= BigInt(tmp_timestamp)){
								YN60 = 'Y'

								if(o_price_60 == '') {
									o_price_60 = tmp_o_price;
								}
								
								if(l_price_60 == '' && h_price_60 == '') {
									l_price_60 = tmp_l_price;
									h_price_60 = tmp_h_price;
								}else{
									if(parseFloat(l_price_60) > parseFloat(tmp_l_price)){
										l_price_60 = tmp_l_price
									}

									if(parseFloat(h_price_60) < parseFloat(tmp_h_price)){
										h_price_60 = tmp_h_price
									}
								}

								c_price_60 = tmp_c_price;

								if(parseFloat(c_price_60) > parseFloat(o_price_60)){
									gubun_60 = '상승'
								}else if(parseFloat(c_price_60) < parseFloat(o_price_60)){
									gubun_60 = '하락'
								}else{
									gubun_60 = '보합'
								}
								volume_60 = (parseFloat(volume_60) + parseFloat(tmp_volume)).toFixed(8)
								price_volume_60 = (parseFloat(volume_60) * parseFloat(c_price_60)).toFixed(0)
								o_c_subtract_60 = (parseFloat(c_price_60) - parseFloat(o_price_60))
								o_c_rate_60 = ((parseFloat(o_c_subtract_60)/parseFloat(o_price_60))*100).toFixed(2)

								//console.log('['+String(tmp_timestamp)+']['+String(o_price_60)+']['+String(l_price_60)+']['+String(h_price_60)+']['+String(c_price_60)+']['+String(tmp_volume)+']+['+String(volume_60)+']')
							}

							if(BigInt(timestamp240) <= BigInt(bithumb_list_timestamp[a])){
								YN240 = 'Y'

								if(o_price_240 == '') {
									o_price_240 = tmp_o_price;
								}
								
								if(l_price_240 == '' && h_price_240 == '') {
									l_price_240 = tmp_l_price;
									h_price_240 = tmp_h_price;
								}else{
									if(parseFloat(l_price_240) > parseFloat(tmp_l_price)){
										l_price_240 = tmp_l_price
									}

									if(parseFloat(h_price_240) < parseFloat(tmp_h_price)){
										h_price_240 = tmp_h_price
									}
								}

								c_price_240 = tmp_c_price;

								if(parseFloat(c_price_240) > parseFloat(o_price_240)){
									gubun_240 = '상승'
								}else if(parseFloat(c_price_240) < parseFloat(o_price_240)){
									gubun_240 = '하락'
								}else{
									gubun_240 = '보합'
								}
								volume_240 = (parseFloat(volume_240) + parseFloat(tmp_volume)).toFixed(8)
								price_volume_240 = (parseFloat(volume_240) * parseFloat(c_price_240)).toFixed(0)
								o_c_subtract_240 = (parseFloat(c_price_240) - parseFloat(o_price_240))
								o_c_rate_240 = ((parseFloat(o_c_subtract_240)/parseFloat(o_price_240))*100).toFixed(2)
							}
						}
						o_c_rate_60 = o_c_rate_60.replace('-0.00','0.00')
						this.min60_api_datetime_kst = gijun_datetime_60.substr(0, 16)

						o_c_rate_240 = o_c_rate_240.replace('-0.00','0.00')
						this.min240_api_datetime_kst = gijun_datetime_240.substr(0, 16)

						if(YN60 == 'Y'){
							list60.push({
								coin_ticker: String(BithumbList[FindIndex].coin_ticker),
								coin_kor_name: String(BithumbList[FindIndex].coin_kor_name),
								gubun: gubun_60,
								o_price: o_price_60,
								l_price: l_price_60,
								h_price: h_price_60,
								c_price: c_price_60,
								volume: volume_60,
								price_volume: price_volume_60,
								o_c_subtract: o_c_subtract_60,
								o_c_rate: o_c_rate_60,
							})
						}else{
							list60.push({
								coin_ticker: String(BithumbList[FindIndex].coin_ticker),
								coin_kor_name: String(BithumbList[FindIndex].coin_kor_name),
								gubun: '',
								o_price: '0',
								l_price: '0',
								h_price: '0',
								c_price: '0',
								volume: '0',
								price_volume: '0',
								o_c_subtract: '0',
								o_c_rate: '0',
							})
						}

						if(YN240 == 'Y'){
							list240.push({
								coin_ticker: String(BithumbList[FindIndex].coin_ticker),
								coin_kor_name: String(BithumbList[FindIndex].coin_kor_name),
								gubun: gubun_240,
								o_price: o_price_240,
								l_price: l_price_240,
								h_price: h_price_240,
								c_price: c_price_240,
								volume: volume_240,
								price_volume: price_volume_240,
								o_c_subtract: o_c_subtract_240,
								o_c_rate: o_c_rate_240,
							})
						}else{
							list240.push({
								coin_ticker: String(BithumbList[FindIndex].coin_ticker),
								coin_kor_name: String(BithumbList[FindIndex].coin_kor_name),
								gubun: '',
								o_price: '0',
								l_price: '0',
								h_price: '0',
								c_price: '0',
								volume: '0',
								price_volume: '0',
								o_c_subtract: '0',
								o_c_rate: '0',
							})
						}
						
						if(list60.length == tmpBithumbList.length){
							list60 = list60.sort((a,b) => Number(b.price_volume) - Number(a.price_volume));
							
							if(this.min60_rank1_coin_name != list60[0].coin_kor_name){
								this.min60_rank1_coin = list60[0].coin_ticker;
								this.min60_rank1_coin_name = list60[0].coin_kor_name;

								if(this.min60_rank1_coin_name != ''){
									if(list60[0].gubun == '상승'){
										this.min60_rank1_border_color = 'red';
									}else if(list60[0].gubun == '하락'){
										this.min60_rank1_border_color = 'blue';
									}else if(list60[0].gubun == '보합'){
										this.min60_rank1_border_color = 'black';
									}

									setTimeout(() => 
										this.min60_rank1_border_color = 'white'
									, 500);
								}
							}
							this.min60_rank1_gubun = list60[0].gubun;
							this.min60_rank1_open = list60[0].o_price;
							this.min60_rank1_low = list60[0].l_price;
							this.min60_rank1_high = list60[0].h_price;
							this.min60_rank1_close = Number(list60[0].c_price).toLocaleString('ko-KR');
							this.min60_rank1_o_c_rate = list60[0].o_c_rate;
							this.min60_rank1_o_c_subtract = Number(list60[0].o_c_subtract).toLocaleString('ko-KR');
							this.min60_rank1_price_volume = Number(list60[0].price_volume).toLocaleString('ko-KR');

							if(this.min60_rank2_coin_name != list60[1].coin_kor_name){
								this.min60_rank2_coin = list60[1].coin_ticker;
								this.min60_rank2_coin_name = list60[1].coin_kor_name;

								if(this.min60_rank2_coin_name != ''){
									if(list60[1].gubun == '상승'){
										this.min60_rank2_border_color = 'red';
									}else if(list60[1].gubun == '하락'){
										this.min60_rank2_border_color = 'blue';
									}else if(list60[1].gubun == '보합'){
										this.min60_rank2_border_color = 'black';
									}

									setTimeout(() => 
										this.min60_rank2_border_color = 'white'
									, 500);
								}
							}
							this.min60_rank2_gubun = list60[1].gubun;
							this.min60_rank2_open = list60[1].o_price;
							this.min60_rank2_low = list60[1].l_price;
							this.min60_rank2_high = list60[1].h_price;
							this.min60_rank2_close = Number(list60[1].c_price).toLocaleString('ko-KR');
							this.min60_rank2_o_c_rate = list60[1].o_c_rate;
							this.min60_rank2_o_c_subtract = Number(list60[1].o_c_subtract).toLocaleString('ko-KR');
							this.min60_rank2_price_volume = Number(list60[1].price_volume).toLocaleString('ko-KR');

							if(this.min60_rank3_coin_name != list60[2].coin_kor_name){
								this.min60_rank3_coin = list60[2].coin_ticker;
								this.min60_rank3_coin_name = list60[2].coin_kor_name;

								if(this.min60_rank3_coin_name != ''){
									if(list60[2].gubun == '상승'){
										this.min60_rank3_border_color = 'red';
									}else if(list60[2].gubun == '하락'){
										this.min60_rank3_border_color = 'blue';
									}else if(list60[2].gubun == '보합'){
										this.min60_rank3_border_color = 'black';
									}

									setTimeout(() => 
										this.min60_rank3_border_color = 'white'
									, 500);
								}
							}
							this.min60_rank3_gubun = list60[2].gubun;
							this.min60_rank3_open = list60[2].o_price;
							this.min60_rank3_low = list60[2].l_price;
							this.min60_rank3_high = list60[2].h_price;
							this.min60_rank3_close = Number(list60[2].c_price).toLocaleString('ko-KR');
							this.min60_rank3_o_c_rate = list60[2].o_c_rate;
							this.min60_rank3_o_c_subtract = Number(list60[2].o_c_subtract).toLocaleString('ko-KR');
							this.min60_rank3_price_volume = Number(list60[2].price_volume).toLocaleString('ko-KR');

							if(this.min60_rank4_coin_name != list60[3].coin_kor_name){
								this.min60_rank4_coin = list60[3].coin_ticker;
								this.min60_rank4_coin_name = list60[3].coin_kor_name;

								if(this.min60_rank4_coin_name != ''){
									if(list60[3].gubun == '상승'){
										this.min60_rank4_border_color = 'red';
									}else if(list60[3].gubun == '하락'){
										this.min60_rank4_border_color = 'blue';
									}else if(list60[3].gubun == '보합'){
										this.min60_rank4_border_color = 'black';
									}

									setTimeout(() => 
										this.min60_rank4_border_color = 'white'
									, 500);
								}
							}
							this.min60_rank4_gubun = list60[3].gubun;
							this.min60_rank4_open = list60[3].o_price;
							this.min60_rank4_low = list60[3].l_price;
							this.min60_rank4_high = list60[3].h_price;
							this.min60_rank4_close = Number(list60[3].c_price).toLocaleString('ko-KR');
							this.min60_rank4_o_c_rate = list60[3].o_c_rate;
							this.min60_rank4_o_c_subtract = Number(list60[3].o_c_subtract).toLocaleString('ko-KR');
							this.min60_rank4_price_volume = Number(list60[3].price_volume).toLocaleString('ko-KR');

							if(this.min60_rank5_coin_name != list60[4].coin_kor_name){
								this.min60_rank5_coin = list60[4].coin_ticker;
								this.min60_rank5_coin_name = list60[4].coin_kor_name;

								if(this.min60_rank5_coin_name != ''){
									if(list60[4].gubun == '상승'){
										this.min60_rank5_border_color = 'red';
									}else if(list60[4].gubun == '하락'){
										this.min60_rank5_border_color = 'blue';
									}else if(list60[4].gubun == '보합'){
										this.min60_rank5_border_color = 'black';
									}

									setTimeout(() => 
										this.min60_rank5_border_color = 'white'
									, 500);
								}
							}
							this.min60_rank5_gubun = list60[4].gubun;
							this.min60_rank5_open = list60[4].o_price;
							this.min60_rank5_low = list60[4].l_price;
							this.min60_rank5_high = list60[4].h_price;
							this.min60_rank5_close = Number(list60[4].c_price).toLocaleString('ko-KR');
							this.min60_rank5_o_c_rate = list60[4].o_c_rate;
							this.min60_rank5_o_c_subtract = Number(list60[4].o_c_subtract).toLocaleString('ko-KR');
							this.min60_rank5_price_volume = Number(list60[4].price_volume).toLocaleString('ko-KR');

							if(this.min60_rank6_coin_name != list60[5].coin_kor_name){
								this.min60_rank6_coin = list60[5].coin_ticker;
								this.min60_rank6_coin_name = list60[5].coin_kor_name;

								if(this.min60_rank6_coin_name != ''){
									if(list60[5].gubun == '상승'){
										this.min60_rank6_border_color = 'red';
									}else if(list60[5].gubun == '하락'){
										this.min60_rank6_border_color = 'blue';
									}else if(list60[5].gubun == '보합'){
										this.min60_rank6_border_color = 'black';
									}

									setTimeout(() => 
										this.min60_rank6_border_color = 'white'
									, 500);
								}
							}
							this.min60_rank6_gubun = list60[5].gubun;
							this.min60_rank6_open = list60[5].o_price;
							this.min60_rank6_low = list60[5].l_price;
							this.min60_rank6_high = list60[5].h_price;
							this.min60_rank6_close = Number(list60[5].c_price).toLocaleString('ko-KR');
							this.min60_rank6_o_c_rate = list60[5].o_c_rate;
							this.min60_rank6_o_c_subtract = Number(list60[5].o_c_subtract).toLocaleString('ko-KR');
							this.min60_rank6_price_volume = Number(list60[5].price_volume).toLocaleString('ko-KR');

							if(this.min60_rank7_coin_name != list60[6].coin_kor_name){
								this.min60_rank7_coin = list60[6].coin_ticker;
								this.min60_rank7_coin_name = list60[6].coin_kor_name;

								if(this.min60_rank7_coin_name != ''){
									if(list60[6].gubun == '상승'){
										this.min60_rank7_border_color = 'red';
									}else if(list60[6].gubun == '하락'){
										this.min60_rank7_border_color = 'blue';
									}else if(list60[6].gubun == '보합'){
										this.min60_rank7_border_color = 'black';
									}

									setTimeout(() => 
										this.min60_rank7_border_color = 'white'
									, 500);
								}
							}
							this.min60_rank7_gubun = list60[6].gubun;
							this.min60_rank7_open = list60[6].o_price;
							this.min60_rank7_low = list60[6].l_price;
							this.min60_rank7_high = list60[6].h_price;
							this.min60_rank7_close = Number(list60[6].c_price).toLocaleString('ko-KR');
							this.min60_rank7_o_c_rate = list60[6].o_c_rate;
							this.min60_rank7_o_c_subtract = Number(list60[6].o_c_subtract).toLocaleString('ko-KR');
							this.min60_rank7_price_volume = Number(list60[6].price_volume).toLocaleString('ko-KR');

							if(this.min60_rank8_coin_name != list60[7].coin_kor_name){
								this.min60_rank8_coin = list60[7].coin_ticker;
								this.min60_rank8_coin_name = list60[7].coin_kor_name;

								if(this.min60_rank8_coin_name != ''){
									if(list60[7].gubun == '상승'){
										this.min60_rank8_border_color = 'red';
									}else if(list60[7].gubun == '하락'){
										this.min60_rank8_border_color = 'blue';
									}else if(list60[7].gubun == '보합'){
										this.min60_rank8_border_color = 'black';
									}

									setTimeout(() => 
										this.min60_rank8_border_color = 'white'
									, 500);
								}
							}
							this.min60_rank8_gubun = list60[7].gubun;
							this.min60_rank8_open = list60[7].o_price;
							this.min60_rank8_low = list60[7].l_price;
							this.min60_rank8_high = list60[7].h_price;
							this.min60_rank8_close = Number(list60[7].c_price).toLocaleString('ko-KR');
							this.min60_rank8_o_c_rate = list60[7].o_c_rate;
							this.min60_rank8_o_c_subtract = Number(list60[7].o_c_subtract).toLocaleString('ko-KR');
							this.min60_rank8_price_volume = Number(list60[7].price_volume).toLocaleString('ko-KR');

							if(this.min60_rank9_coin_name != list60[8].coin_kor_name){
								this.min60_rank9_coin = list60[8].coin_ticker;
								this.min60_rank9_coin_name = list60[8].coin_kor_name;

								if(this.min60_rank9_coin_name != ''){
									if(list60[8].gubun == '상승'){
										this.min60_rank9_border_color = 'red';
									}else if(list60[8].gubun == '하락'){
										this.min60_rank9_border_color = 'blue';
									}else if(list60[8].gubun == '보합'){
										this.min60_rank9_border_color = 'black';
									}

									setTimeout(() => 
										this.min60_rank9_border_color = 'white'
									, 500);
								}
							}
							this.min60_rank9_gubun = list60[8].gubun;
							this.min60_rank9_open = list60[8].o_price;
							this.min60_rank9_low = list60[8].l_price;
							this.min60_rank9_high = list60[8].h_price;
							this.min60_rank9_close = Number(list60[8].c_price).toLocaleString('ko-KR');
							this.min60_rank9_o_c_rate = list60[8].o_c_rate;
							this.min60_rank9_o_c_subtract = Number(list60[8].o_c_subtract).toLocaleString('ko-KR');
							this.min60_rank9_price_volume = Number(list60[8].price_volume).toLocaleString('ko-KR');

							if(this.min60_rank10_coin_name != list60[9].coin_kor_name){
								this.min60_rank10_coin = list60[9].coin_ticker;
								this.min60_rank10_coin_name = list60[9].coin_kor_name;

								if(this.min60_rank10_coin_name != ''){
									if(list60[9].gubun == '상승'){
										this.min60_rank10_border_color = 'red';
									}else if(list60[9].gubun == '하락'){
										this.min60_rank10_border_color = 'blue';
									}else if(list60[9].gubun == '보합'){
										this.min60_rank10_border_color = 'black';
									}

									setTimeout(() => 
										this.min60_rank10_border_color = 'white'
									, 500);
								}
							}
							this.min60_rank10_gubun = list60[9].gubun;
							this.min60_rank10_open = list60[9].o_price;
							this.min60_rank10_low = list60[9].l_price;
							this.min60_rank10_high = list60[9].h_price;
							this.min60_rank10_close = Number(list60[9].c_price).toLocaleString('ko-KR');
							this.min60_rank10_o_c_rate = list60[9].o_c_rate;
							this.min60_rank10_o_c_subtract = Number(list60[9].o_c_subtract).toLocaleString('ko-KR');
							this.min60_rank10_price_volume = Number(list60[9].price_volume).toLocaleString('ko-KR');

							end = new Date();
						}

						if(list240.length == tmpBithumbList.length){
							list240 = list240.sort((a,b) => Number(b.price_volume) - Number(a.price_volume));
							
							if(this.min240_rank1_coin_name != list240[0].coin_kor_name){
								this.min240_rank1_coin = list240[0].coin_ticker;
								this.min240_rank1_coin_name = list240[0].coin_kor_name;

								if(this.min240_rank1_coin_name != ''){
									if(list240[0].gubun == '상승'){
										this.min240_rank1_border_color = 'red';
									}else if(list240[0].gubun == '하락'){
										this.min240_rank1_border_color = 'blue';
									}else if(list240[0].gubun == '보합'){
										this.min240_rank1_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_rank1_border_color = 'white'
									, 500);
								}
							}
							this.min240_rank1_gubun = list240[0].gubun;
							this.min240_rank1_open = list240[0].o_price;
							this.min240_rank1_low = list240[0].l_price;
							this.min240_rank1_high = list240[0].h_price;
							this.min240_rank1_close = Number(list240[0].c_price).toLocaleString('ko-KR');
							this.min240_rank1_o_c_rate = list240[0].o_c_rate;
							this.min240_rank1_o_c_subtract = Number(list240[0].o_c_subtract).toLocaleString('ko-KR');
							this.min240_rank1_price_volume = Number(list240[0].price_volume).toLocaleString('ko-KR');
							
							if(this.min240_rank2_coin_name != list240[1].coin_kor_name){
								this.min240_rank2_coin = list240[1].coin_ticker;
								this.min240_rank2_coin_name = list240[1].coin_kor_name;

								if(this.min240_rank2_coin_name != ''){
									if(list240[1].gubun == '상승'){
										this.min240_rank2_border_color = 'red';
									}else if(list240[1].gubun == '하락'){
										this.min240_rank2_border_color = 'blue';
									}else if(list240[1].gubun == '보합'){
										this.min240_rank2_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_rank2_border_color = 'white'
									, 500);
								}
							}
							this.min240_rank2_gubun = list240[1].gubun;
							this.min240_rank2_open = list240[1].o_price;
							this.min240_rank2_low = list240[1].l_price;
							this.min240_rank2_high = list240[1].h_price;
							this.min240_rank2_close = Number(list240[1].c_price).toLocaleString('ko-KR');
							this.min240_rank2_o_c_rate = list240[1].o_c_rate;
							this.min240_rank2_o_c_subtract = Number(list240[1].o_c_subtract).toLocaleString('ko-KR');
							this.min240_rank2_price_volume = Number(list240[1].price_volume).toLocaleString('ko-KR');
							
							if(this.min240_rank3_coin_name != list240[2].coin_kor_name){
								this.min240_rank3_coin = list240[2].coin_ticker;
								this.min240_rank3_coin_name = list240[2].coin_kor_name;

								if(this.min240_rank3_coin_name != ''){
									if(list240[2].gubun == '상승'){
										this.min240_rank3_border_color = 'red';
									}else if(list240[2].gubun == '하락'){
										this.min240_rank3_border_color = 'blue';
									}else if(list240[2].gubun == '보합'){
										this.min240_rank3_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_rank3_border_color = 'white'
									, 500);
								}
							}
							this.min240_rank3_gubun = list240[2].gubun;
							this.min240_rank3_open = list240[2].o_price;
							this.min240_rank3_low = list240[2].l_price;
							this.min240_rank3_high = list240[2].h_price;
							this.min240_rank3_close = Number(list240[2].c_price).toLocaleString('ko-KR');
							this.min240_rank3_o_c_rate = list240[2].o_c_rate;
							this.min240_rank3_o_c_subtract = Number(list240[2].o_c_subtract).toLocaleString('ko-KR');
							this.min240_rank3_price_volume = Number(list240[2].price_volume).toLocaleString('ko-KR');
							
							if(this.min240_rank4_coin_name != list240[3].coin_kor_name){
								this.min240_rank4_coin = list240[3].coin_ticker;
								this.min240_rank4_coin_name = list240[3].coin_kor_name;

								if(this.min240_rank4_coin_name != ''){
									if(list240[3].gubun == '상승'){
										this.min240_rank4_border_color = 'red';
									}else if(list240[3].gubun == '하락'){
										this.min240_rank4_border_color = 'blue';
									}else if(list240[3].gubun == '보합'){
										this.min240_rank4_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_rank4_border_color = 'white'
									, 500);
								}
							}
							this.min240_rank4_gubun = list240[3].gubun;
							this.min240_rank4_open = list240[3].o_price;
							this.min240_rank4_low = list240[3].l_price;
							this.min240_rank4_high = list240[3].h_price;
							this.min240_rank4_close = Number(list240[3].c_price).toLocaleString('ko-KR');
							this.min240_rank4_o_c_rate = list240[3].o_c_rate;
							this.min240_rank4_o_c_subtract = Number(list240[3].o_c_subtract).toLocaleString('ko-KR');
							this.min240_rank4_price_volume = Number(list240[3].price_volume).toLocaleString('ko-KR');
							
							if(this.min240_rank5_coin_name != list240[4].coin_kor_name){
								this.min240_rank5_coin = list240[4].coin_ticker;
								this.min240_rank5_coin_name = list240[4].coin_kor_name;

								if(this.min240_rank5_coin_name != ''){
									if(list240[4].gubun == '상승'){
										this.min240_rank5_border_color = 'red';
									}else if(list240[4].gubun == '하락'){
										this.min240_rank5_border_color = 'blue';
									}else if(list240[4].gubun == '보합'){
										this.min240_rank5_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_rank5_border_color = 'white'
									, 500);
								}
							}
							this.min240_rank5_gubun = list240[4].gubun;
							this.min240_rank5_open = list240[4].o_price;
							this.min240_rank5_low = list240[4].l_price;
							this.min240_rank5_high = list240[4].h_price;
							this.min240_rank5_close = Number(list240[4].c_price).toLocaleString('ko-KR');
							this.min240_rank5_o_c_rate = list240[4].o_c_rate;
							this.min240_rank5_o_c_subtract = Number(list240[4].o_c_subtract).toLocaleString('ko-KR');
							this.min240_rank5_price_volume = Number(list240[4].price_volume).toLocaleString('ko-KR');
							
							if(this.min240_rank6_coin_name != list240[5].coin_kor_name){
								this.min240_rank6_coin = list240[5].coin_ticker;
								this.min240_rank6_coin_name = list240[5].coin_kor_name;

								if(this.min240_rank6_coin_name != ''){
									if(list240[5].gubun == '상승'){
										this.min240_rank6_border_color = 'red';
									}else if(list240[5].gubun == '하락'){
										this.min240_rank6_border_color = 'blue';
									}else if(list240[5].gubun == '보합'){
										this.min240_rank6_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_rank6_border_color = 'white'
									, 500);
								}
							}
							this.min240_rank6_gubun = list240[5].gubun;
							this.min240_rank6_open = list240[5].o_price;
							this.min240_rank6_low = list240[5].l_price;
							this.min240_rank6_high = list240[5].h_price;
							this.min240_rank6_close = Number(list240[5].c_price).toLocaleString('ko-KR');
							this.min240_rank6_o_c_rate = list240[5].o_c_rate;
							this.min240_rank6_o_c_subtract = Number(list240[5].o_c_subtract).toLocaleString('ko-KR');
							this.min240_rank6_price_volume = Number(list240[5].price_volume).toLocaleString('ko-KR');
							
							if(this.min240_rank7_coin_name != list240[6].coin_kor_name){
								this.min240_rank7_coin = list240[6].coin_ticker;
								this.min240_rank7_coin_name = list240[6].coin_kor_name;

								if(this.min240_rank7_coin_name != ''){
									if(list240[6].gubun == '상승'){
										this.min240_rank7_border_color = 'red';
									}else if(list240[6].gubun == '하락'){
										this.min240_rank7_border_color = 'blue';
									}else if(list240[6].gubun == '보합'){
										this.min240_rank7_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_rank7_border_color = 'white'
									, 500);
								}
							}
							this.min240_rank7_gubun = list240[6].gubun;
							this.min240_rank7_open = list240[6].o_price;
							this.min240_rank7_low = list240[6].l_price;
							this.min240_rank7_high = list240[6].h_price;
							this.min240_rank7_close = Number(list240[6].c_price).toLocaleString('ko-KR');
							this.min240_rank7_o_c_rate = list240[6].o_c_rate;
							this.min240_rank7_o_c_subtract = Number(list240[6].o_c_subtract).toLocaleString('ko-KR');
							this.min240_rank7_price_volume = Number(list240[6].price_volume).toLocaleString('ko-KR');
							
							if(this.min240_rank8_coin_name != list240[7].coin_kor_name){
								this.min240_rank8_coin = list240[7].coin_ticker;
								this.min240_rank8_coin_name = list240[7].coin_kor_name;

								if(this.min240_rank8_coin_name != ''){
									if(list240[7].gubun == '상승'){
										this.min240_rank8_border_color = 'red';
									}else if(list240[7].gubun == '하락'){
										this.min240_rank8_border_color = 'blue';
									}else if(list240[7].gubun == '보합'){
										this.min240_rank8_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_rank8_border_color = 'white'
									, 500);
								}
							}
							this.min240_rank8_gubun = list240[7].gubun;
							this.min240_rank8_open = list240[7].o_price;
							this.min240_rank8_low = list240[7].l_price;
							this.min240_rank8_high = list240[7].h_price;
							this.min240_rank8_close = Number(list240[7].c_price).toLocaleString('ko-KR');
							this.min240_rank8_o_c_rate = list240[7].o_c_rate;
							this.min240_rank8_o_c_subtract = Number(list240[7].o_c_subtract).toLocaleString('ko-KR');
							this.min240_rank8_price_volume = Number(list240[7].price_volume).toLocaleString('ko-KR');
							
							if(this.min240_rank9_coin_name != list240[8].coin_kor_name){
								this.min240_rank9_coin = list240[8].coin_ticker;
								this.min240_rank9_coin_name = list240[8].coin_kor_name;

								if(this.min240_rank9_coin_name != ''){
									if(list240[8].gubun == '상승'){
										this.min240_rank9_border_color = 'red';
									}else if(list240[8].gubun == '하락'){
										this.min240_rank9_border_color = 'blue';
									}else if(list240[8].gubun == '보합'){
										this.min240_rank9_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_rank9_border_color = 'white'
									, 500);
								}
							}
							this.min240_rank9_gubun = list240[8].gubun;
							this.min240_rank9_open = list240[8].o_price;
							this.min240_rank9_low = list240[8].l_price;
							this.min240_rank9_high = list240[8].h_price;
							this.min240_rank9_close = Number(list240[8].c_price).toLocaleString('ko-KR');
							this.min240_rank9_o_c_rate = list240[8].o_c_rate;
							this.min240_rank9_o_c_subtract = Number(list240[8].o_c_subtract).toLocaleString('ko-KR');
							this.min240_rank9_price_volume = Number(list240[8].price_volume).toLocaleString('ko-KR');
							
							if(this.min240_rank10_coin_name != list240[9].coin_kor_name){
								this.min240_rank10_coin = list240[9].coin_ticker;
								this.min240_rank10_coin_name = list240[9].coin_kor_name;

								if(this.min240_rank10_coin_name != ''){
									if(list240[9].gubun == '상승'){
										this.min240_rank10_border_color = 'red';
									}else if(list240[9].gubun == '하락'){
										this.min240_rank10_border_color = 'blue';
									}else if(list240[9].gubun == '보합'){
										this.min240_rank10_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_rank10_border_color = 'white'
									, 500);
								}
							}
							this.min240_rank10_gubun = list240[9].gubun;
							this.min240_rank10_open = list240[9].o_price;
							this.min240_rank10_low = list240[9].l_price;
							this.min240_rank10_high = list240[9].h_price;
							this.min240_rank10_close = Number(list240[9].c_price).toLocaleString('ko-KR');
							this.min240_rank10_o_c_rate = list240[9].o_c_rate;
							this.min240_rank10_o_c_subtract = Number(list240[9].o_c_subtract).toLocaleString('ko-KR');
							this.min240_rank10_price_volume = Number(list240[9].price_volume).toLocaleString('ko-KR');
							
							console.log('종료 ' + (new Date()))

							if(list60.length == tmpBithumbList.length && list240.length == tmpBithumbList.length){
								var today = new Date();
								var year = today.getFullYear();
								var month = ('0' + (today.getMonth() + 1)).slice(-2);
								var day = ('0' + today.getDate()).slice(-2);
								var hours = ('0' + today.getHours()).slice(-2); 
								var minutes = ('0' + today.getMinutes()).slice(-2);
								var seconds = ('0' + today.getSeconds()).slice(-2); 
								
								this.min60_gijun_datetime_kst = year + '-' + month  + '-' + day + ' ' + hours + ':' + minutes  + ':' + seconds;
								this.min240_gijun_datetime_kst = year + '-' + month  + '-' + day + ' ' + hours + ':' + minutes  + ':' + seconds;

								this.RequestCompleteYN = 'Y'
							}
						}
					})
				}
			})
		},
		CurrentDataTime() {
			var mi = new Date().getMinutes() < 10? "0" + new Date().getMinutes(): new Date().getMinutes();
			var ss = new Date().getSeconds() < 10? "0" + new Date().getSeconds(): new Date().getSeconds();
			if(Number(ss) == 0){
				//this.Data_Notice();
				//this.Data_MIN_5_15_Make();
			}

			if(this.RequestCompleteYN == 'Y' && (Number(ss) == 0 || Number(ss) == 8 || Number(ss) == 16 || Number(ss) == 24 || Number(ss) == 32 || Number(ss) == 40 || Number(ss) == 48 || Number(ss) == 54)){
				//this.Data_Notice();
				this.Data_MIN_60_240_Make();
			}
			
			if(this.min60_gijun_datetime_kst != '' || this.min240_gijun_datetime_kst != ''){
				var min60_mi = (this.min60_gijun_datetime_kst).substr(14, 2)
				var min240_mi = (this.min240_gijun_datetime_kst).substr(14, 2)
				if((Number(min60_mi) == 0 && Number(mi) == 2) || Number(min240_mi) == 0 && Number(mi) == 2){
					this.Data_MIN_60_240_Make();
				}else if((Number(min60_mi) < Number(mi)-1) || (Number(min60_mi) > Number(mi)+1) || (Number(min240_mi) < Number(mi)-1) || (Number(min240_mi) > Number(mi)+1)){
					this.Data_MIN_60_240_Make();
				}
			}
		},
		Data_Notice(){
			axios.get('/Main/SelectCodeData',{
			params: {
					code_id: '0001'
				}
			})
			.then(response => {
				this.notice_msg = response.data;
			});
		}
	}
}
</script>