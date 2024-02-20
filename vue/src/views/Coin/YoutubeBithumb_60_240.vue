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
			axios({
				url: '/YoutubeBithumb/MIN_60_240',
				method: 'get',
				timeout: 60000
			})
			.then(response => {
				if(response.status == 200){

					var list240 = response.data;
					this.min60_api_datetime_kst = (response.data[0].API_DATETIME_KST_60).substring(0, 16);
					this.min60_gijun_datetime_kst = response.data[0].CURRENT_DATETIME_KST;
					
					if(this.min60_rank1_coin_name != response.data[0].Coin_Kor_Name){
						this.min60_rank1_coin = response.data[0].API_Coin_Ticker;
						this.min60_rank1_coin_name = response.data[0].Coin_Kor_Name;

						if(this.min60_rank1_coin_name != ''){
							if(response.data[0].gubun_60 == '상승'){
								this.min60_rank1_border_color = 'red';
							}else if(response.data[0].gubun_60 == '하락'){
								this.min60_rank1_border_color = 'blue';
							}else if(response.data[0].gubun_60 == '보합'){
								this.min60_rank1_border_color = 'black';
							}

							setTimeout(() => 
								this.min60_rank1_border_color = 'white'
							, 500);
						}
					}
					this.min60_rank1_gubun = response.data[0].gubun_60;
					this.min60_rank1_open = response.data[0].o_price_60;
					this.min60_rank1_low = response.data[0].l_price_60;
					this.min60_rank1_high = response.data[0].h_price_60;
					this.min60_rank1_close = response.data[0].format_c_price_60;
					this.min60_rank1_o_c_rate = response.data[0].o_c_rate_60;
					this.min60_rank1_o_c_subtract = response.data[0].o_c_subtract_60;
					this.min60_rank1_price_volume = response.data[0].format_volume_price_60;

					if(this.min60_rank2_coin_name != response.data[1].Coin_Kor_Name){
						this.min60_rank2_coin = response.data[1].API_Coin_Ticker;
						this.min60_rank2_coin_name = response.data[1].Coin_Kor_Name;

						if(this.min60_rank2_coin_name != ''){
							if(response.data[1].gubun_60 == '상승'){
								this.min60_rank2_border_color = 'red';
							}else if(response.data[1].gubun_60 == '하락'){
								this.min60_rank2_border_color = 'blue';
							}else if(response.data[1].gubun_60 == '보합'){
								this.min60_rank2_border_color = 'black';
							}

							setTimeout(() => 
								this.min60_rank2_border_color = 'white'
							, 500);
						}
					}
					this.min60_rank2_gubun = response.data[1].gubun_60;
					this.min60_rank2_open = response.data[1].o_price_60;
					this.min60_rank2_low = response.data[1].l_price_60;
					this.min60_rank2_high = response.data[1].h_price_60;
					this.min60_rank2_close = response.data[1].format_c_price_60;
					this.min60_rank2_o_c_rate = response.data[1].o_c_rate_60;
					this.min60_rank2_o_c_subtract = response.data[1].o_c_subtract_60;
					this.min60_rank2_price_volume = response.data[1].format_volume_price_60;

					if(this.min60_rank3_coin_name != response.data[2].Coin_Kor_Name){
						this.min60_rank3_coin = response.data[2].API_Coin_Ticker;
						this.min60_rank3_coin_name = response.data[2].Coin_Kor_Name;

						if(this.min60_rank3_coin_name != ''){
							if(response.data[2].gubun_60 == '상승'){
								this.min60_rank3_border_color = 'red';
							}else if(response.data[2].gubun_60 == '하락'){
								this.min60_rank3_border_color = 'blue';
							}else if(response.data[2].gubun_60 == '보합'){
								this.min60_rank3_border_color = 'black';
							}

							setTimeout(() => 
								this.min60_rank3_border_color = 'white'
							, 500);
						}
					}
					this.min60_rank3_gubun = response.data[2].gubun_60;
					this.min60_rank3_open = response.data[2].o_price_60;
					this.min60_rank3_low = response.data[2].l_price_60;
					this.min60_rank3_high = response.data[2].h_price_60;
					this.min60_rank3_close = response.data[2].format_c_price_60;
					this.min60_rank3_o_c_rate = response.data[2].o_c_rate_60;
					this.min60_rank3_o_c_subtract = response.data[2].o_c_subtract_60;
					this.min60_rank3_price_volume = response.data[2].format_volume_price_60;
					
					if(this.min60_rank4_coin_name != response.data[3].Coin_Kor_Name){
						this.min60_rank4_coin = response.data[3].API_Coin_Ticker;
						this.min60_rank4_coin_name = response.data[3].Coin_Kor_Name;

						if(this.min60_rank4_coin_name != ''){
							if(response.data[3].gubun_60 == '상승'){
								this.min60_rank4_border_color = 'red';
							}else if(response.data[3].gubun_60 == '하락'){
								this.min60_rank4_border_color = 'blue';
							}else if(response.data[3].gubun_60 == '보합'){
								this.min60_rank4_border_color = 'black';
							}

							setTimeout(() => 
								this.min60_rank4_border_color = 'white'
							, 500);
						}
					}
					this.min60_rank4_gubun = response.data[3].gubun_60;
					this.min60_rank4_open = response.data[3].o_price_60;
					this.min60_rank4_low = response.data[3].l_price_60;
					this.min60_rank4_high = response.data[3].h_price_60;
					this.min60_rank4_close = response.data[3].format_c_price_60;
					this.min60_rank4_o_c_rate = response.data[3].o_c_rate_60;
					this.min60_rank4_o_c_subtract = response.data[3].o_c_subtract_60;
					this.min60_rank4_price_volume = response.data[3].format_volume_price_60;
					
					if(this.min60_rank5_coin_name != response.data[4].Coin_Kor_Name){
						this.min60_rank5_coin = response.data[4].API_Coin_Ticker;
						this.min60_rank5_coin_name = response.data[4].Coin_Kor_Name;

						if(this.min60_rank5_coin_name != ''){
							if(response.data[4].gubun_60 == '상승'){
								this.min60_rank5_border_color = 'red';
							}else if(response.data[4].gubun_60 == '하락'){
								this.min60_rank5_border_color = 'blue';
							}else if(response.data[4].gubun_60 == '보합'){
								this.min60_rank5_border_color = 'black';
							}

							setTimeout(() => 
								this.min60_rank5_border_color = 'white'
							, 500);
						}
					}
					this.min60_rank5_gubun = response.data[4].gubun_60;
					this.min60_rank5_open = response.data[4].o_price_60;
					this.min60_rank5_low = response.data[4].l_price_60;
					this.min60_rank5_high = response.data[4].h_price_60;
					this.min60_rank5_close = response.data[4].format_c_price_60;
					this.min60_rank5_o_c_rate = response.data[4].o_c_rate_60;
					this.min60_rank5_o_c_subtract = response.data[4].o_c_subtract_60;
					this.min60_rank5_price_volume = response.data[4].format_volume_price_60;

					if(this.min60_rank6_coin_name != response.data[5].Coin_Kor_Name){
						this.min60_rank6_coin = response.data[5].API_Coin_Ticker;
						this.min60_rank6_coin_name = response.data[5].Coin_Kor_Name;

						if(this.min60_rank6_coin_name != ''){
							if(response.data[5].gubun_60 == '상승'){
								this.min60_rank6_border_color = 'red';
							}else if(response.data[5].gubun_60 == '하락'){
								this.min60_rank6_border_color = 'blue';
							}else if(response.data[5].gubun_60 == '보합'){
								this.min60_rank6_border_color = 'black';
							}

							setTimeout(() => 
								this.min60_rank6_border_color = 'white'
							, 500);
						}
					}
					this.min60_rank6_gubun = response.data[5].gubun_60;
					this.min60_rank6_open = response.data[5].o_price_60;
					this.min60_rank6_low = response.data[5].l_price_60;
					this.min60_rank6_high = response.data[5].h_price_60;
					this.min60_rank6_close = response.data[5].format_c_price_60;
					this.min60_rank6_o_c_rate = response.data[5].o_c_rate_60;
					this.min60_rank6_o_c_subtract = response.data[5].o_c_subtract_60;
					this.min60_rank6_price_volume = response.data[5].format_volume_price_60;
					
					if(this.min60_rank7_coin_name != response.data[6].Coin_Kor_Name){
						this.min60_rank7_coin = response.data[6].API_Coin_Ticker;
						this.min60_rank7_coin_name = response.data[6].Coin_Kor_Name;

						if(this.min60_rank7_coin_name != ''){
							if(response.data[6].gubun_60 == '상승'){
								this.min60_rank7_border_color = 'red';
							}else if(response.data[6].gubun_60 == '하락'){
								this.min60_rank7_border_color = 'blue';
							}else if(response.data[6].gubun_60 == '보합'){
								this.min60_rank7_border_color = 'black';
							}

							setTimeout(() => 
								this.min60_rank7_border_color = 'white'
							, 500);
						}
					}
					this.min60_rank7_gubun = response.data[6].gubun_60;
					this.min60_rank7_open = response.data[6].o_price_60;
					this.min60_rank7_low = response.data[6].l_price_60;
					this.min60_rank7_high = response.data[6].h_price_60;
					this.min60_rank7_close = response.data[6].format_c_price_60;
					this.min60_rank7_o_c_rate = response.data[6].o_c_rate_60;
					this.min60_rank7_o_c_subtract = response.data[6].o_c_subtract_60;
					this.min60_rank7_price_volume = response.data[6].format_volume_price_60;

					if(this.min60_rank8_coin_name != response.data[7].Coin_Kor_Name){
						this.min60_rank8_coin = response.data[7].API_Coin_Ticker;
						this.min60_rank8_coin_name = response.data[7].Coin_Kor_Name;

						if(this.min60_rank8_coin_name != ''){
							if(response.data[7].gubun_60 == '상승'){
								this.min60_rank8_border_color = 'red';
							}else if(response.data[7].gubun_60 == '하락'){
								this.min60_rank8_border_color = 'blue';
							}else if(response.data[7].gubun_60 == '보합'){
								this.min60_rank8_border_color = 'black';
							}

							setTimeout(() => 
								this.min60_rank8_border_color = 'white'
							, 500);
						}
					}
					this.min60_rank8_gubun = response.data[7].gubun_60;
					this.min60_rank8_open = response.data[7].o_price_60;
					this.min60_rank8_low = response.data[7].l_price_60;
					this.min60_rank8_high = response.data[7].h_price_60;
					this.min60_rank8_close = response.data[7].format_c_price_60;
					this.min60_rank8_o_c_rate = response.data[7].o_c_rate_60;
					this.min60_rank8_o_c_subtract = response.data[7].o_c_subtract_60;
					this.min60_rank8_price_volume = response.data[7].format_volume_price_60;

					if(this.min60_rank9_coin_name != response.data[8].Coin_Kor_Name){
						this.min60_rank9_coin = response.data[8].API_Coin_Ticker;
						this.min60_rank9_coin_name = response.data[8].Coin_Kor_Name;

						if(this.min60_rank9_coin_name != ''){
							if(response.data[8].gubun_60 == '상승'){
								this.min60_rank9_border_color = 'red';
							}else if(response.data[8].gubun_60 == '하락'){
								this.min60_rank9_border_color = 'blue';
							}else if(response.data[8].gubun_60 == '보합'){
								this.min60_rank9_border_color = 'black';
							}

							setTimeout(() => 
								this.min60_rank9_border_color = 'white'
							, 500);
						}
					}
					this.min60_rank9_gubun = response.data[8].gubun_60;
					this.min60_rank9_open = response.data[8].o_price_60;
					this.min60_rank9_low = response.data[8].l_price_60;
					this.min60_rank9_high = response.data[8].h_price_60;
					this.min60_rank9_close = response.data[8].format_c_price_60;
					this.min60_rank9_o_c_rate = response.data[8].o_c_rate_60;
					this.min60_rank9_o_c_subtract = response.data[8].o_c_subtract_60;
					this.min60_rank9_price_volume = response.data[8].format_volume_price_60;
					
					if(this.min60_rank10_coin_name != response.data[9].Coin_Kor_Name){
						this.min60_rank10_coin = response.data[9].API_Coin_Ticker;
						this.min60_rank10_coin_name = response.data[9].Coin_Kor_Name;

						if(this.min60_rank10_coin_name != ''){
							if(response.data[9].gubun_60 == '상승'){
								this.min60_rank10_border_color = 'red';
							}else if(response.data[9].gubun_60 == '하락'){
								this.min60_rank10_border_color = 'blue';
							}else if(response.data[9].gubun_60 == '보합'){
								this.min60_rank10_border_color = 'black';
							}

							setTimeout(() => 
								this.min60_rank10_border_color = 'white'
							, 500);
						}
					}
					this.min60_rank10_gubun = response.data[9].gubun_60;
					this.min60_rank10_open = response.data[9].o_price_60;
					this.min60_rank10_low = response.data[9].l_price_60;
					this.min60_rank10_high = response.data[9].h_price_60;
					this.min60_rank10_close = response.data[9].format_c_price_60;
					this.min60_rank10_o_c_rate = response.data[9].o_c_rate_60;
					this.min60_rank10_o_c_subtract = response.data[9].o_c_subtract_60;
					this.min60_rank10_price_volume = response.data[9].format_volume_price_60;

					list240.sort(function (a, b) {
						return Number(b.volume_price_240) - Number(a.volume_price_240);
					});
					
					this.min240_api_datetime_kst = (list240[0].API_DATETIME_KST_240).substring(0, 16);
					this.min240_gijun_datetime_kst = list240[0].CURRENT_DATETIME_KST;
					
					if(this.min240_rank1_coin_name != list240[0].Coin_Kor_Name){
						this.min240_rank1_coin = list240[0].API_Coin_Ticker;
						this.min240_rank1_coin_name = list240[0].Coin_Kor_Name;

						if(this.min60_rank1_coin_name != ''){
							if(list240[0].gubun_240 == '상승'){
								this.min240_rank1_border_color = 'red';
							}else if(list240[0].gubun_240 == '하락'){
								this.min240_rank1_border_color = 'blue';
							}else if(list240[0].gubun_240 == '보합'){
								this.min240_rank1_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_rank1_border_color = 'white'
							, 500);
						}
					}
					this.min240_rank1_gubun = list240[0].gubun_240;
					this.min240_rank1_open = list240[0].o_price_240;
					this.min240_rank1_low = list240[0].l_price_240;
					this.min240_rank1_high = list240[0].h_price_240;
					this.min240_rank1_close = list240[0].format_c_price_240;
					this.min240_rank1_o_c_rate = list240[0].o_c_rate_240;
					this.min240_rank1_o_c_subtract = list240[0].o_c_subtract_240;
					this.min240_rank1_price_volume = list240[0].format_volume_price_240;

					if(this.min240_rank2_coin_name != list240[1].Coin_Kor_Name){
						this.min240_rank2_coin = list240[1].API_Coin_Ticker;
						this.min240_rank2_coin_name = list240[1].Coin_Kor_Name;

						if(this.min240_rank2_coin_name != ''){
							if(list240[1].gubun_240 == '상승'){
								this.min240_rank2_border_color = 'red';
							}else if(list240[1].gubun_240 == '하락'){
								this.min240_rank2_border_color = 'blue';
							}else if(list240[1].gubun_240 == '보합'){
								this.min240_rank2_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_rank2_border_color = 'white'
							, 500);
						}
					}
					this.min240_rank2_gubun = list240[1].gubun_240;
					this.min240_rank2_open = list240[1].o_price_240;
					this.min240_rank2_low = list240[1].l_price_240;
					this.min240_rank2_high = list240[1].h_price_240;
					this.min240_rank2_close = list240[1].format_c_price_240;
					this.min240_rank2_o_c_rate = list240[1].o_c_rate_240;
					this.min240_rank2_o_c_subtract = list240[1].o_c_subtract_240;
					this.min240_rank2_price_volume = list240[1].format_volume_price_240;

					if(this.min240_rank3_coin_name != list240[2].Coin_Kor_Name){
						this.min240_rank3_coin = list240[2].API_Coin_Ticker;
						this.min240_rank3_coin_name = list240[2].Coin_Kor_Name;

						if(this.min240_rank3_coin_name != ''){
							if(list240[2].gubun_240 == '상승'){
								this.min240_rank3_border_color = 'red';
							}else if(list240[2].gubun_240 == '하락'){
								this.min240_rank3_border_color = 'blue';
							}else if(list240[2].gubun_240 == '보합'){
								this.min240_rank3_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_rank3_border_color = 'white'
							, 500);
						}
					}
					this.min240_rank3_gubun = list240[2].gubun_240;
					this.min240_rank3_open = list240[2].o_price_240;
					this.min240_rank3_low = list240[2].l_price_240;
					this.min240_rank3_high = list240[2].h_price_240;
					this.min240_rank3_close = list240[2].format_c_price_240;
					this.min240_rank3_o_c_rate = list240[2].o_c_rate_240;
					this.min240_rank3_o_c_subtract = list240[2].o_c_subtract_240;
					this.min240_rank3_price_volume = list240[2].format_volume_price_240;
					
					if(this.min240_rank4_coin_name != list240[3].Coin_Kor_Name){
						this.min240_rank4_coin = list240[3].API_Coin_Ticker;
						this.min240_rank4_coin_name = list240[3].Coin_Kor_Name;

						if(this.min240_rank4_coin_name != ''){
							if(list240[3].gubun_240 == '상승'){
								this.min240_rank4_border_color = 'red';
							}else if(list240[3].gubun_240 == '하락'){
								this.min240_rank4_border_color = 'blue';
							}else if(list240[3].gubun_240 == '보합'){
								this.min240_rank4_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_rank4_border_color = 'white'
							, 500);
						}
					}
					this.min240_rank4_gubun = list240[3].gubun_240;
					this.min240_rank4_open = list240[3].o_price_240;
					this.min240_rank4_low = list240[3].l_price_240;
					this.min240_rank4_high = list240[3].h_price_240;
					this.min240_rank4_close = list240[3].format_c_price_240;
					this.min240_rank4_o_c_rate = list240[3].o_c_rate_240;
					this.min240_rank4_o_c_subtract = list240[3].o_c_subtract_240;
					this.min240_rank4_price_volume = list240[3].format_volume_price_240;
					
					if(this.min240_rank5_coin_name != list240[4].Coin_Kor_Name){
						this.min240_rank5_coin = list240[4].API_Coin_Ticker;
						this.min240_rank5_coin_name = list240[4].Coin_Kor_Name;

						if(this.min240_rank5_coin_name != ''){
							if(list240[4].gubun_240 == '상승'){
								this.min240_rank5_border_color = 'red';
							}else if(list240[4].gubun_240 == '하락'){
								this.min240_rank5_border_color = 'blue';
							}else if(list240[4].gubun_240 == '보합'){
								this.min240_rank5_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_rank5_border_color = 'white'
							, 500);
						}
					}
					this.min240_rank5_gubun = list240[4].gubun_240;
					this.min240_rank5_open = list240[4].o_price_240;
					this.min240_rank5_low = list240[4].l_price_240;
					this.min240_rank5_high = list240[4].h_price_240;
					this.min240_rank5_close = list240[4].format_c_price_240;
					this.min240_rank5_o_c_rate = list240[4].o_c_rate_240;
					this.min240_rank5_o_c_subtract = list240[4].o_c_subtract_240;
					this.min240_rank5_price_volume = list240[4].format_volume_price_240;
				}

				if(this.min240_rank6_coin_name != list240[5].Coin_Kor_Name){
					this.min240_rank6_coin = list240[5].API_Coin_Ticker;
					this.min240_rank6_coin_name = list240[5].Coin_Kor_Name;

					if(this.min240_rank6_coin_name != ''){
						if(list240[5].gubun_240 == '상승'){
							this.min240_rank6_border_color = 'red';
						}else if(list240[5].gubun_240 == '하락'){
							this.min240_rank6_border_color = 'blue';
						}else if(list240[5].gubun_240 == '보합'){
							this.min240_rank6_border_color = 'black';
						}

						setTimeout(() => 
							this.min240_rank6_border_color = 'white'
						, 500);
					}
				}
				this.min240_rank6_gubun = list240[5].gubun_240;
				this.min240_rank6_open = list240[5].o_price_240;
				this.min240_rank6_low = list240[5].l_price_240;
				this.min240_rank6_high = list240[5].h_price_240;
				this.min240_rank6_close = list240[5].format_c_price_240;
				this.min240_rank6_o_c_rate = list240[5].o_c_rate_240;
				this.min240_rank6_o_c_subtract = list240[5].o_c_subtract_240;
				this.min240_rank6_price_volume = list240[5].format_volume_price_240;
				
				if(this.min240_rank7_coin_name != list240[6].Coin_Kor_Name){
					this.min240_rank7_coin = list240[6].API_Coin_Ticker;
					this.min240_rank7_coin_name = list240[6].Coin_Kor_Name;

					if(this.min240_rank7_coin_name != ''){
						if(list240[6].gubun_240 == '상승'){
							this.min240_rank7_border_color = 'red';
						}else if(list240[6].gubun_240 == '하락'){
							this.min240_rank7_border_color = 'blue';
						}else if(list240[6].gubun_240 == '보합'){
							this.min240_rank7_border_color = 'black';
						}

						setTimeout(() => 
							this.min240_rank7_border_color = 'white'
						, 500);
					}
				}
				this.min240_rank7_gubun = list240[6].gubun_240;
				this.min240_rank7_open = list240[6].o_price_240;
				this.min240_rank7_low = list240[6].l_price_240;
				this.min240_rank7_high = list240[6].h_price_240;
				this.min240_rank7_close = list240[6].format_c_price_240;
				this.min240_rank7_o_c_rate = list240[6].o_c_rate_240;
				this.min240_rank7_o_c_subtract = list240[6].o_c_subtract_240;
				this.min240_rank7_price_volume = list240[6].format_volume_price_240;

				if(this.min240_rank8_coin_name != list240[7].Coin_Kor_Name){
					this.min240_rank8_coin = list240[7].API_Coin_Ticker;
					this.min240_rank8_coin_name = list240[7].Coin_Kor_Name;

					if(this.min240_rank8_coin_name != ''){
						if(list240[7].gubun_240 == '상승'){
							this.min240_rank8_border_color = 'red';
						}else if(list240[7].gubun_240 == '하락'){
							this.min240_rank8_border_color = 'blue';
						}else if(list240[7].gubun_240 == '보합'){
							this.min240_rank8_border_color = 'black';
						}

						setTimeout(() => 
							this.min240_rank8_border_color = 'white'
						, 500);
					}
				}
				this.min240_rank8_gubun = list240[7].gubun_240;
				this.min240_rank8_open = list240[7].o_price_240;
				this.min240_rank8_low = list240[7].l_price_240;
				this.min240_rank8_high = list240[7].h_price_240;
				this.min240_rank8_close = list240[7].format_c_price_240;
				this.min240_rank8_o_c_rate = list240[7].o_c_rate_240;
				this.min240_rank8_o_c_subtract = list240[7].o_c_subtract_240;
				this.min240_rank8_price_volume = list240[7].format_volume_price_240;

				if(this.min240_rank9_coin_name != list240[8].Coin_Kor_Name){
					this.min240_rank9_coin = list240[8].API_Coin_Ticker;
					this.min240_rank9_coin_name = list240[8].Coin_Kor_Name;

					if(this.min240_rank9_coin_name != ''){
						if(list240[8].gubun_240 == '상승'){
							this.min240_rank9_border_color = 'red';
						}else if(list240[8].gubun_240 == '하락'){
							this.min240_rank9_border_color = 'blue';
						}else if(list240[8].gubun_240 == '보합'){
							this.min240_rank9_border_color = 'black';
						}

						setTimeout(() => 
							this.min240_rank9_border_color = 'white'
						, 500);
					}
				}
				this.min240_rank9_gubun = list240[8].gubun_240;
				this.min240_rank9_open = list240[8].o_price_240;
				this.min240_rank9_low = list240[8].l_price_240;
				this.min240_rank9_high = list240[8].h_price_240;
				this.min240_rank9_close = list240[8].format_c_price_240;
				this.min240_rank9_o_c_rate = list240[8].o_c_rate_240;
				this.min240_rank9_o_c_subtract = list240[8].o_c_subtract_240;
				this.min240_rank9_price_volume = list240[8].format_volume_price_240;
				
				if(this.min240_rank10_coin_name != list240[9].Coin_Kor_Name){
					this.min240_rank10_coin = list240[9].API_Coin_Ticker;
					this.min240_rank10_coin_name = list240[9].Coin_Kor_Name;

					if(this.min240_rank10_coin_name != ''){
						if(list240[9].gubun_240 == '상승'){
							this.min240_rank10_border_color = 'red';
						}else if(list240[9].gubun_240 == '하락'){
							this.min240_rank10_border_color = 'blue';
						}else if(list240[9].gubun_240 == '보합'){
							this.min240_rank10_border_color = 'black';
						}

						setTimeout(() => 
							this.min240_rank10_border_color = 'white'
						, 500);
					}
				}
				this.min240_rank10_gubun = list240[9].gubun_240;
				this.min240_rank10_open = list240[9].o_price_240;
				this.min240_rank10_low = list240[9].l_price_240;
				this.min240_rank10_high = list240[9].h_price_240;
				this.min240_rank10_close = list240[9].format_c_price_240;
				this.min240_rank10_o_c_rate = list240[9].o_c_rate_240;
				this.min240_rank10_o_c_subtract = list240[9].o_c_subtract_240;
				this.min240_rank10_price_volume = list240[9].format_volume_price_240;

				this.Data_MIN_60_240_Make();
			})
		},
		CurrentDataTime() {
			var mi = new Date().getMinutes() < 10? "0" + new Date().getMinutes(): new Date().getMinutes();
			var ss = new Date().getSeconds() < 10? "0" + new Date().getSeconds(): new Date().getSeconds();
			
			if(Number(ss) == 0){
				//this.Data_Notice();
			}
			
			if(this.min60_gijun_datetime_kst != ''){
				var min60_mi = (this.min60_gijun_datetime_kst).substr(14, 2)
				if(Number(min60_mi) == 0 && Number(mi) == 2){
					this.Data_MIN_60_240_Make();
				}else if((Number(min60_mi) < Number(mi)-1) || (Number(min60_mi) > Number(mi)+1)){
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