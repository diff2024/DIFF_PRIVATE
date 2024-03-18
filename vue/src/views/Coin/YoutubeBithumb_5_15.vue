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
						빗썸 5분 [{{ min5_api_datetime_kst }}]
					</v-col>
					<v-col xl="5" md="5" sm="5" style="padding-right:0px; text-align:right; font-weight:bold; font-size:35px;">
						{{ min5_gijun_datetime_kst }}
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
						변동률(5분)
					</v-col>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						추정 거래대금(5분)
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank1_border_color }">
						{{ min5_rank1_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank1_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min5_rank1_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min5_rank1_o_c_rate }}%</td>
								</template>
								<template v-else-if="this.min5_rank1_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min5_rank1_o_c_rate }}%</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min5_rank1_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min5_rank1_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min5_rank1_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min5_rank1_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank1_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank2_border_color }">
						{{ min5_rank2_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank2_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min5_rank2_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min5_rank2_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min5_rank2_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min5_rank2_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min5_rank2_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min5_rank2_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min5_rank2_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min5_rank2_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank2_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank3_border_color }">
						{{ min5_rank3_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank3_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min5_rank3_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min5_rank3_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min5_rank3_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min5_rank3_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min5_rank3_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min5_rank3_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min5_rank3_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min5_rank3_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank3_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank4_border_color }">
						{{ min5_rank4_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank4_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min5_rank4_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min5_rank4_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min5_rank4_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min5_rank4_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min5_rank4_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min5_rank4_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min5_rank4_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min5_rank4_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank4_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank5_border_color }">
						{{ min5_rank5_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank5_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min5_rank5_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min5_rank5_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min5_rank5_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min5_rank5_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min5_rank5_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min5_rank5_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min5_rank5_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min5_rank5_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank5_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank6_border_color }">
						{{ min5_rank6_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank6_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min5_rank6_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min5_rank6_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min5_rank6_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min5_rank6_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min5_rank6_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min5_rank6_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min5_rank6_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min5_rank6_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank6_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank7_border_color }">
						{{ min5_rank7_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank7_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min5_rank7_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min5_rank7_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min5_rank7_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min5_rank7_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min5_rank7_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min5_rank7_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min5_rank7_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min5_rank7_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank7_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank8_border_color }">
						{{ min5_rank8_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank8_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min5_rank8_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min5_rank8_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min5_rank8_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min5_rank8_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min5_rank8_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min5_rank8_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min5_rank8_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min5_rank8_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank8_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank9_border_color }">
						{{ min5_rank9_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank9_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min5_rank9_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min5_rank9_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min5_rank9_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min5_rank9_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min5_rank9_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min5_rank9_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min5_rank9_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min5_rank9_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank9_price_volume }}
					</v-col>
				</v-row>
				
				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank10_border_color }">
						{{ min5_rank10_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank10_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min5_rank10_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min5_rank10_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min5_rank10_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min5_rank10_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min5_rank10_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min5_rank10_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min5_rank10_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min5_rank10_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank10_price_volume }}
					</v-col>
				</v-row>
			</v-col>
			<v-col xl="6" md="6" sm="6" style="text-align:center; padding-top:0px; padding-left:40px; padding-right:25px;">
				<v-row>
					<v-col xl="7" md="7" sm="7" style="padding-left:20px; text-align:left; font-weight:bold; font-size:35px;">
						빗썸 15분 [{{ min15_api_datetime_kst }}]
					</v-col>
					<v-col xl="5" md="5" sm="5" style="padding-right:25px; text-align:right; font-weight:bold; font-size:35px;">
						{{ min15_gijun_datetime_kst }}
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
						변동률(15분)
					</v-col>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						추정 거래대금(15분)
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank1_border_color }">
						{{ min15_rank1_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank1_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min15_rank1_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min15_rank1_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min15_rank1_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min15_rank1_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min15_rank1_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min15_rank1_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min15_rank1_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min15_rank1_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank1_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank2_border_color }">
						{{ min15_rank2_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank2_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min15_rank2_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min15_rank2_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min15_rank2_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min15_rank2_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min15_rank2_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min15_rank2_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min15_rank2_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min15_rank2_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank2_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank3_border_color }">
						{{ min15_rank3_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank3_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min15_rank3_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min15_rank3_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min15_rank3_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min15_rank3_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min15_rank3_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min15_rank3_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min15_rank3_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min15_rank3_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank3_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank4_border_color }">
						{{ min15_rank4_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank4_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min15_rank4_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min15_rank4_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min15_rank4_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min15_rank4_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min15_rank4_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min15_rank4_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min15_rank4_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min15_rank4_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank4_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank5_border_color }">
						{{ min15_rank5_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank5_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min15_rank5_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min15_rank5_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min15_rank5_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min15_rank5_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min15_rank5_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min15_rank5_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min15_rank5_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min15_rank5_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank5_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank6_border_color }">
						{{ min15_rank6_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank6_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min15_rank6_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min15_rank6_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min15_rank6_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min15_rank6_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min15_rank6_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min15_rank6_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min15_rank6_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min15_rank6_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank6_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank7_border_color }">
						{{ min15_rank7_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank7_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min15_rank7_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min15_rank7_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min15_rank7_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min15_rank7_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min15_rank7_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min15_rank7_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min15_rank7_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min15_rank7_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank7_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank8_border_color }">
						{{ min15_rank8_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank8_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min15_rank8_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min15_rank8_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min15_rank8_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min15_rank8_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min15_rank8_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min15_rank8_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min15_rank8_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min15_rank8_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank8_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank9_border_color }">
						{{ min15_rank9_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank9_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min15_rank9_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min15_rank9_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min15_rank9_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min15_rank9_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min15_rank9_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min15_rank9_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min15_rank9_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min15_rank9_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank9_price_volume }}
					</v-col>
				</v-row>
				
				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank10_border_color }">
						{{ min15_rank10_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank10_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min15_rank10_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min15_rank10_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min15_rank10_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min15_rank10_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min15_rank10_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min15_rank10_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min15_rank10_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min15_rank10_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank10_price_volume }}
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
import * as axios from 'axios' 

export default {
	data () {
      	return {
			username: '',
			password: '',
			dialog: false,
			notice_msg: '',
			RequestCompleteYN: 'N',
			/* MIN5 */
			min5_api_datetime_kst : '',
			min5_gijun_datetime_kst : '',

			min5_rank1_coin : '',
			min5_rank1_coin_name : '',
			min5_rank1_border_color : 'white',
			min5_rank1_gubun : '',
			min5_rank1_open : '',
			min5_rank1_low : '',
			min5_rank1_high : '',
			min5_rank1_close : '',
			min5_rank1_o_c_rate : '',
			min5_rank1_o_c_subtract : '',
			min5_rank1_price_volume : '',

			min5_rank2_coin : '',
			min5_rank2_coin_name : '',
			min5_rank2_border_color : 'white',
			min5_rank2_gubun : '',
			min5_rank2_open : '',
			min5_rank2_low : '',
			min5_rank2_high : '',
			min5_rank2_close : '',
			min5_rank2_o_c_rate : '',
			min5_rank2_o_c_subtract : '',
			min5_rank2_price_volume : '',

			min5_rank3_coin : '',
			min5_rank3_coin_name : '',
			min5_rank3_border_color : 'white',
			min5_rank3_gubun : '',
			min5_rank3_open : '',
			min5_rank3_low : '',
			min5_rank3_high : '',
			min5_rank3_close : '',
			min5_rank3_o_c_rate : '',
			min5_rank3_o_c_subtract : '',
			min5_rank3_price_volume : '',

			min5_rank4_coin : '',
			min5_rank4_coin_name : '',
			min5_rank4_border_color : 'white',
			min5_rank4_gubun : '',
			min5_rank4_open : '',
			min5_rank4_low : '',
			min5_rank4_high : '',
			min5_rank4_close : '',
			min5_rank4_o_c_rate : '',
			min5_rank4_o_c_subtract : '',
			min5_rank4_price_volume : '',

			min5_rank5_coin : '',
			min5_rank5_coin_name : '',
			min5_rank5_border_color : 'white',
			min5_rank5_gubun : '',
			min5_rank5_open : '',
			min5_rank5_low : '',
			min5_rank5_high : '',
			min5_rank5_close : '',
			min5_rank5_o_c_rate : '',
			min5_rank5_o_c_subtract : '',
			min5_rank5_price_volume : '',

			min5_rank6_coin : '',
			min5_rank6_coin_name : '',
			min5_rank6_border_color : 'white',
			min5_rank6_gubun : '',
			min5_rank6_open : '',
			min5_rank6_low : '',
			min5_rank6_high : '',
			min5_rank6_close : '',
			min5_rank6_o_c_rate : '',
			min5_rank6_o_c_subtract : '',
			min5_rank6_price_volume : '',

			min5_rank7_coin : '',
			min5_rank7_coin_name : '',
			min5_rank7_border_color : 'white',
			min5_rank7_gubun : '',
			min5_rank7_open : '',
			min5_rank7_low : '',
			min5_rank7_high : '',
			min5_rank7_close : '',
			min5_rank7_o_c_rate : '',
			min5_rank7_o_c_subtract : '',
			min5_rank7_price_volume : '',

			min5_rank8_coin : '',
			min5_rank8_coin_name : '',
			min5_rank8_border_color : 'white',
			min5_rank8_gubun : '',
			min5_rank8_open : '',
			min5_rank8_low : '',
			min5_rank8_high : '',
			min5_rank8_close : '',
			min5_rank8_o_c_rate : '',
			min5_rank8_o_c_subtract : '',
			min5_rank8_price_volume : '',

			min5_rank9_coin : '',
			min5_rank9_coin_name : '',
			min5_rank9_border_color : 'white',
			min5_rank9_gubun : '',
			min5_rank9_open : '',
			min5_rank9_low : '',
			min5_rank9_high : '',
			min5_rank9_close : '',
			min5_rank9_o_c_rate : '',
			min5_rank9_o_c_subtract : '',
			min5_rank9_price_volume : '',

			min5_rank10_coin : '',
			min5_rank10_coin_name : '',
			min5_rank10_border_color : 'white',
			min5_rank10_gubun : '',
			min5_rank10_open : '',
			min5_rank10_low : '',
			min5_rank10_high : '',
			min5_rank10_close : '',
			min5_rank10_o_c_rate : '',
			min5_rank10_o_c_subtract : '',
			min5_rank10_price_volume : '',

			/* MIN15 */
			min15_api_datetime_kst : '',
			min15_gijun_datetime_kst : '',

			min15_rank1_coin : '',
			min15_rank1_coin_name : '',
			min15_rank1_border_color : 'white',
			min15_rank1_gubun : '',
			min15_rank1_open : '',
			min15_rank1_low : '',
			min15_rank1_high : '',
			min15_rank1_close : '',
			min15_rank1_o_c_rate : '',
			min15_rank1_o_c_subtract : '',
			min15_rank1_price_volume : '',

			min15_rank2_coin : '',
			min15_rank2_coin_name : '',
			min15_rank2_border_color : 'white',
			min15_rank2_gubun : '',
			min15_rank2_open : '',
			min15_rank2_low : '',
			min15_rank2_high : '',
			min15_rank2_close : '',
			min15_rank2_o_c_rate : '',
			min15_rank2_o_c_subtract : '',
			min15_rank2_price_volume : '',

			min15_rank3_coin : '',
			min15_rank3_coin_name : '',
			min15_rank3_border_color : 'white',
			min15_rank3_gubun : '',
			min15_rank3_open : '',
			min15_rank3_low : '',
			min15_rank3_high : '',
			min15_rank3_close : '',
			min15_rank3_o_c_rate : '',
			min15_rank3_o_c_subtract : '',
			min15_rank3_price_volume : '',

			min15_rank4_coin : '',
			min15_rank4_coin_name : '',
			min15_rank4_border_color : 'white',
			min15_rank4_gubun : '',
			min15_rank4_open : '',
			min15_rank4_low : '',
			min15_rank4_high : '',
			min15_rank4_close : '',
			min15_rank4_o_c_rate : '',
			min15_rank4_o_c_subtract : '',
			min15_rank4_price_volume : '',

			min15_rank5_coin : '',
			min15_rank5_coin_name : '',
			min15_rank5_border_color : 'white',
			min15_rank5_gubun : '',
			min15_rank5_open : '',
			min15_rank5_low : '',
			min15_rank5_high : '',
			min15_rank5_close : '',
			min15_rank5_o_c_rate : '',
			min15_rank5_o_c_subtract : '',
			min15_rank5_price_volume : '',

			min15_rank6_coin : '',
			min15_rank6_coin_name : '',
			min15_rank6_border_color : 'white',
			min15_rank6_gubun : '',
			min15_rank6_open : '',
			min15_rank6_low : '',
			min15_rank6_high : '',
			min15_rank6_close : '',
			min15_rank6_o_c_rate : '',
			min15_rank6_o_c_subtract : '',
			min15_rank6_price_volume : '',

			min15_rank7_coin : '',
			min15_rank7_coin_name : '',
			min15_rank7_border_color : 'white',
			min15_rank7_gubun : '',
			min15_rank7_open : '',
			min15_rank7_low : '',
			min15_rank7_high : '',
			min15_rank7_close : '',
			min15_rank7_o_c_rate : '',
			min15_rank7_o_c_subtract : '',
			min15_rank7_price_volume : '',

			min15_rank8_coin : '',
			min15_rank8_coin_name : '',
			min15_rank8_border_color : 'white',
			min15_rank8_gubun : '',
			min15_rank8_open : '',
			min15_rank8_low : '',
			min15_rank8_high : '',
			min15_rank8_close : '',
			min15_rank8_o_c_rate : '',
			min15_rank8_o_c_subtract : '',
			min15_rank8_price_volume : '',

			min15_rank9_coin : '',
			min15_rank9_coin_name : '',
			min15_rank9_border_color : 'white',
			min15_rank9_gubun : '',
			min15_rank9_open : '',
			min15_rank9_low : '',
			min15_rank9_high : '',
			min15_rank9_close : '',
			min15_rank9_o_c_rate : '',
			min15_rank9_o_c_subtract : '',
			min15_rank9_price_volume : '',

			min15_rank10_coin : '',
			min15_rank10_coin_name : '',
			min15_rank10_border_color : 'white',
			min15_rank10_gubun : '',
			min15_rank10_open : '',
			min15_rank10_low : '',
			min15_rank10_high : '',
			min15_rank10_close : '',
			min15_rank10_o_c_rate : '',
			min15_rank10_o_c_subtract : '',
			min15_rank10_price_volume : '',
      	}
    },
	components: {
	},
    mounted() {
		this.Data_MIN_5_15_Make();
		this.CurrentDataTime();
		//this.Data_Notice();
		setInterval(this.CurrentDataTime.bind(this) , 1000);
	},
	methods: {
		Data_MIN_5_15_Make(){
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
			var list5 = [];
			var list15 = [];

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
					var gijun_datetime_5 = BithumbList[i].gijun_datetime_5;
					var gijun_datetime_15 = BithumbList[i].gijun_datetime_15;
					var gijun_datetime_60 = BithumbList[i].gijun_datetime_60;
					var gijun_datetime_240 = BithumbList[i].gijun_datetime_240;
					
					tmpBithumbList.push('https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/'+String(number)+'_C0100/1M');
					axios.get('https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/'+String(number)+'_C0100/1M', {
						headers: {
							'Cache-Control': 'no-cache',
							'Pragma': 'no-cache',
							'Expires': '0',
						},
					})
					//tmpBithumbList.push('https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/'+String(number)+'_C0100/1M');
					//this.$http.get("https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/"+String(number)+"_C0100/1M", requestOptions)
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

						var YN5 = 'N'
						var YN15 = 'N'

						var o_price_5 = ''
						var l_price_5 = ''
						var h_price_5 = ''
						var c_price_5 = ''
						var o_c_subtract_5 = ''
						var o_c_rate_5 = ''
						var volume_5 = '0'
						var gubun_5 = ''
						var price_volume_5 = ''

						var o_price_15 = ''
						var l_price_15 = ''
						var h_price_15 = ''
						var c_price_15 = ''
						var o_c_subtract_15 = ''
						var o_c_rate_15 = ''
						var volume_15 = '0'
						var gubun_15 = ''
						var price_volume_15 = ''

						for(var a=0; a<bithumb_list_timestamp.length; a++){
							var tmp_timestamp = bithumb_list_timestamp[a]
							var tmp_o_price = bithumb_list_open[a]
							var tmp_l_price = bithumb_list_low[a]
							var tmp_h_price = bithumb_list_high[a]
							var tmp_c_price = bithumb_list_close[a]
							var tmp_volume = bithumb_list_volume[a]
							
							if(BigInt(timestamp5) <= BigInt(tmp_timestamp)){
								YN5 = 'Y'

								if(o_price_5 == '') {
									o_price_5 = tmp_o_price;
								}
								
								if(l_price_5 == '' && h_price_5 == '') {
									l_price_5 = tmp_l_price;
									h_price_5 = tmp_h_price;
								}else{
									if(parseFloat(l_price_5) > parseFloat(tmp_l_price)){
										l_price_5 = tmp_l_price
									}

									if(parseFloat(h_price_5) < parseFloat(tmp_h_price)){
										h_price_5 = tmp_h_price
									}
								}

								c_price_5 = tmp_c_price;

								if(parseFloat(c_price_5) > parseFloat(o_price_5)){
									gubun_5 = '상승'
								}else if(parseFloat(c_price_5) < parseFloat(o_price_5)){
									gubun_5 = '하락'
								}else{
									gubun_5 = '보합'
								}
								volume_5 = (parseFloat(volume_5) + parseFloat(tmp_volume)).toFixed(8)
								price_volume_5 = (parseFloat(volume_5) * parseFloat(c_price_5)).toFixed(0)
								o_c_subtract_5 = (parseFloat(c_price_5) - parseFloat(o_price_5))
								o_c_rate_5 = ((parseFloat(o_c_subtract_5)/parseFloat(o_price_5))*100).toFixed(2)

								//console.log('['+String(tmp_timestamp)+']['+String(o_price_5)+']['+String(l_price_5)+']['+String(h_price_5)+']['+String(c_price_5)+']['+String(tmp_volume)+']+['+String(volume_5)+']')
							}

							if(BigInt(timestamp15) <= BigInt(bithumb_list_timestamp[a])){
								YN15 = 'Y'

								if(o_price_15 == '') {
									o_price_15 = tmp_o_price;
								}
								
								if(l_price_15 == '' && h_price_15 == '') {
									l_price_15 = tmp_l_price;
									h_price_15 = tmp_h_price;
								}else{
									if(parseFloat(l_price_15) > parseFloat(tmp_l_price)){
										l_price_15 = tmp_l_price
									}

									if(parseFloat(h_price_15) < parseFloat(tmp_h_price)){
										h_price_15 = tmp_h_price
									}
								}

								c_price_15 = tmp_c_price;

								if(parseFloat(c_price_15) > parseFloat(o_price_15)){
									gubun_15 = '상승'
								}else if(parseFloat(c_price_15) < parseFloat(o_price_15)){
									gubun_15 = '하락'
								}else{
									gubun_15 = '보합'
								}
								volume_15 = (parseFloat(volume_15) + parseFloat(tmp_volume)).toFixed(8)
								price_volume_15 = (parseFloat(volume_15) * parseFloat(c_price_15)).toFixed(0)
								o_c_subtract_15 = (parseFloat(c_price_15) - parseFloat(o_price_15))
								o_c_rate_15 = ((parseFloat(o_c_subtract_15)/parseFloat(o_price_15))*100).toFixed(2)
							}
						}
						o_c_rate_5 = o_c_rate_5.replace('-0.00','0.00')
						this.min5_api_datetime_kst = gijun_datetime_5.substr(0, 16)

						o_c_rate_15 = o_c_rate_15.replace('-0.00','0.00')
						this.min15_api_datetime_kst = gijun_datetime_15.substr(0, 16)

						if(YN5 == 'Y'){
							list5.push({
								coin_ticker: String(BithumbList[FindIndex].coin_ticker),
								coin_kor_name: String(BithumbList[FindIndex].coin_kor_name),
								gubun: gubun_5,
								o_price: o_price_5,
								l_price: l_price_5,
								h_price: h_price_5,
								c_price: c_price_5,
								volume: volume_5,
								price_volume: price_volume_5,
								o_c_subtract: o_c_subtract_5,
								o_c_rate: o_c_rate_5,
							})
						}else{
							list5.push({
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

						if(YN15 == 'Y'){
							list15.push({
								coin_ticker: String(BithumbList[FindIndex].coin_ticker),
								coin_kor_name: String(BithumbList[FindIndex].coin_kor_name),
								gubun: gubun_15,
								o_price: o_price_15,
								l_price: l_price_15,
								h_price: h_price_15,
								c_price: c_price_15,
								volume: volume_15,
								price_volume: price_volume_15,
								o_c_subtract: o_c_subtract_15,
								o_c_rate: o_c_rate_15,
							})
						}else{
							list15.push({
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
						
						if(list5.length == tmpBithumbList.length){
							list5 = list5.sort((a,b) => Number(b.price_volume) - Number(a.price_volume));
							
							if(this.min5_rank1_coin_name != list5[0].coin_kor_name){
								this.min5_rank1_coin = list5[0].coin_ticker;
								this.min5_rank1_coin_name = list5[0].coin_kor_name;

								if(this.min5_rank1_coin_name != ''){
									if(list5[0].gubun == '상승'){
										this.min5_rank1_border_color = 'red';
									}else if(list5[0].gubun == '하락'){
										this.min5_rank1_border_color = 'blue';
									}else if(list5[0].gubun == '보합'){
										this.min5_rank1_border_color = 'black';
									}

									setTimeout(() => 
										this.min5_rank1_border_color = 'white'
									, 500);
								}
							}
							this.min5_rank1_gubun = list5[0].gubun;
							this.min5_rank1_open = list5[0].o_price;
							this.min5_rank1_low = list5[0].l_price;
							this.min5_rank1_high = list5[0].h_price;
							this.min5_rank1_close = Number(list5[0].c_price).toLocaleString('ko-KR');
							this.min5_rank1_o_c_rate = list5[0].o_c_rate;
							this.min5_rank1_o_c_subtract = Number(list5[0].o_c_subtract).toLocaleString('ko-KR');
							this.min5_rank1_price_volume = Number(list5[0].price_volume).toLocaleString('ko-KR');

							if(this.min5_rank2_coin_name != list5[1].coin_kor_name){
								this.min5_rank2_coin = list5[1].coin_ticker;
								this.min5_rank2_coin_name = list5[1].coin_kor_name;

								if(this.min5_rank2_coin_name != ''){
									if(list5[1].gubun == '상승'){
										this.min5_rank2_border_color = 'red';
									}else if(list5[1].gubun == '하락'){
										this.min5_rank2_border_color = 'blue';
									}else if(list5[1].gubun == '보합'){
										this.min5_rank2_border_color = 'black';
									}

									setTimeout(() => 
										this.min5_rank2_border_color = 'white'
									, 500);
								}
							}
							this.min5_rank2_gubun = list5[1].gubun;
							this.min5_rank2_open = list5[1].o_price;
							this.min5_rank2_low = list5[1].l_price;
							this.min5_rank2_high = list5[1].h_price;
							this.min5_rank2_close = Number(list5[1].c_price).toLocaleString('ko-KR');
							this.min5_rank2_o_c_rate = list5[1].o_c_rate;
							this.min5_rank2_o_c_subtract = Number(list5[1].o_c_subtract).toLocaleString('ko-KR');
							this.min5_rank2_price_volume = Number(list5[1].price_volume).toLocaleString('ko-KR');

							if(this.min5_rank3_coin_name != list5[2].coin_kor_name){
								this.min5_rank3_coin = list5[2].coin_ticker;
								this.min5_rank3_coin_name = list5[2].coin_kor_name;

								if(this.min5_rank3_coin_name != ''){
									if(list5[2].gubun == '상승'){
										this.min5_rank3_border_color = 'red';
									}else if(list5[2].gubun == '하락'){
										this.min5_rank3_border_color = 'blue';
									}else if(list5[2].gubun == '보합'){
										this.min5_rank3_border_color = 'black';
									}

									setTimeout(() => 
										this.min5_rank3_border_color = 'white'
									, 500);
								}
							}
							this.min5_rank3_gubun = list5[2].gubun;
							this.min5_rank3_open = list5[2].o_price;
							this.min5_rank3_low = list5[2].l_price;
							this.min5_rank3_high = list5[2].h_price;
							this.min5_rank3_close = Number(list5[2].c_price).toLocaleString('ko-KR');
							this.min5_rank3_o_c_rate = list5[2].o_c_rate;
							this.min5_rank3_o_c_subtract = Number(list5[2].o_c_subtract).toLocaleString('ko-KR');
							this.min5_rank3_price_volume = Number(list5[2].price_volume).toLocaleString('ko-KR');

							if(this.min5_rank4_coin_name != list5[3].coin_kor_name){
								this.min5_rank4_coin = list5[3].coin_ticker;
								this.min5_rank4_coin_name = list5[3].coin_kor_name;

								if(this.min5_rank4_coin_name != ''){
									if(list5[3].gubun == '상승'){
										this.min5_rank4_border_color = 'red';
									}else if(list5[3].gubun == '하락'){
										this.min5_rank4_border_color = 'blue';
									}else if(list5[3].gubun == '보합'){
										this.min5_rank4_border_color = 'black';
									}

									setTimeout(() => 
										this.min5_rank4_border_color = 'white'
									, 500);
								}
							}
							this.min5_rank4_gubun = list5[3].gubun;
							this.min5_rank4_open = list5[3].o_price;
							this.min5_rank4_low = list5[3].l_price;
							this.min5_rank4_high = list5[3].h_price;
							this.min5_rank4_close = Number(list5[3].c_price).toLocaleString('ko-KR');
							this.min5_rank4_o_c_rate = list5[3].o_c_rate;
							this.min5_rank4_o_c_subtract = Number(list5[3].o_c_subtract).toLocaleString('ko-KR');
							this.min5_rank4_price_volume = Number(list5[3].price_volume).toLocaleString('ko-KR');

							if(this.min5_rank5_coin_name != list5[4].coin_kor_name){
								this.min5_rank5_coin = list5[4].coin_ticker;
								this.min5_rank5_coin_name = list5[4].coin_kor_name;

								if(this.min5_rank5_coin_name != ''){
									if(list5[4].gubun == '상승'){
										this.min5_rank5_border_color = 'red';
									}else if(list5[4].gubun == '하락'){
										this.min5_rank5_border_color = 'blue';
									}else if(list5[4].gubun == '보합'){
										this.min5_rank5_border_color = 'black';
									}

									setTimeout(() => 
										this.min5_rank5_border_color = 'white'
									, 500);
								}
							}
							this.min5_rank5_gubun = list5[4].gubun;
							this.min5_rank5_open = list5[4].o_price;
							this.min5_rank5_low = list5[4].l_price;
							this.min5_rank5_high = list5[4].h_price;
							this.min5_rank5_close = Number(list5[4].c_price).toLocaleString('ko-KR');
							this.min5_rank5_o_c_rate = list5[4].o_c_rate;
							this.min5_rank5_o_c_subtract = Number(list5[4].o_c_subtract).toLocaleString('ko-KR');
							this.min5_rank5_price_volume = Number(list5[4].price_volume).toLocaleString('ko-KR');

							if(this.min5_rank6_coin_name != list5[5].coin_kor_name){
								this.min5_rank6_coin = list5[5].coin_ticker;
								this.min5_rank6_coin_name = list5[5].coin_kor_name;

								if(this.min5_rank6_coin_name != ''){
									if(list5[5].gubun == '상승'){
										this.min5_rank6_border_color = 'red';
									}else if(list5[5].gubun == '하락'){
										this.min5_rank6_border_color = 'blue';
									}else if(list5[5].gubun == '보합'){
										this.min5_rank6_border_color = 'black';
									}

									setTimeout(() => 
										this.min5_rank6_border_color = 'white'
									, 500);
								}
							}
							this.min5_rank6_gubun = list5[5].gubun;
							this.min5_rank6_open = list5[5].o_price;
							this.min5_rank6_low = list5[5].l_price;
							this.min5_rank6_high = list5[5].h_price;
							this.min5_rank6_close = Number(list5[5].c_price).toLocaleString('ko-KR');
							this.min5_rank6_o_c_rate = list5[5].o_c_rate;
							this.min5_rank6_o_c_subtract = Number(list5[5].o_c_subtract).toLocaleString('ko-KR');
							this.min5_rank6_price_volume = Number(list5[5].price_volume).toLocaleString('ko-KR');

							if(this.min5_rank7_coin_name != list5[6].coin_kor_name){
								this.min5_rank7_coin = list5[6].coin_ticker;
								this.min5_rank7_coin_name = list5[6].coin_kor_name;

								if(this.min5_rank7_coin_name != ''){
									if(list5[6].gubun == '상승'){
										this.min5_rank7_border_color = 'red';
									}else if(list5[6].gubun == '하락'){
										this.min5_rank7_border_color = 'blue';
									}else if(list5[6].gubun == '보합'){
										this.min5_rank7_border_color = 'black';
									}

									setTimeout(() => 
										this.min5_rank7_border_color = 'white'
									, 500);
								}
							}
							this.min5_rank7_gubun = list5[6].gubun;
							this.min5_rank7_open = list5[6].o_price;
							this.min5_rank7_low = list5[6].l_price;
							this.min5_rank7_high = list5[6].h_price;
							this.min5_rank7_close = Number(list5[6].c_price).toLocaleString('ko-KR');
							this.min5_rank7_o_c_rate = list5[6].o_c_rate;
							this.min5_rank7_o_c_subtract = Number(list5[6].o_c_subtract).toLocaleString('ko-KR');
							this.min5_rank7_price_volume = Number(list5[6].price_volume).toLocaleString('ko-KR');

							if(this.min5_rank8_coin_name != list5[7].coin_kor_name){
								this.min5_rank8_coin = list5[7].coin_ticker;
								this.min5_rank8_coin_name = list5[7].coin_kor_name;

								if(this.min5_rank8_coin_name != ''){
									if(list5[7].gubun == '상승'){
										this.min5_rank8_border_color = 'red';
									}else if(list5[7].gubun == '하락'){
										this.min5_rank8_border_color = 'blue';
									}else if(list5[7].gubun == '보합'){
										this.min5_rank8_border_color = 'black';
									}

									setTimeout(() => 
										this.min5_rank8_border_color = 'white'
									, 500);
								}
							}
							this.min5_rank8_gubun = list5[7].gubun;
							this.min5_rank8_open = list5[7].o_price;
							this.min5_rank8_low = list5[7].l_price;
							this.min5_rank8_high = list5[7].h_price;
							this.min5_rank8_close = Number(list5[7].c_price).toLocaleString('ko-KR');
							this.min5_rank8_o_c_rate = list5[7].o_c_rate;
							this.min5_rank8_o_c_subtract = Number(list5[7].o_c_subtract).toLocaleString('ko-KR');
							this.min5_rank8_price_volume = Number(list5[7].price_volume).toLocaleString('ko-KR');

							if(this.min5_rank9_coin_name != list5[8].coin_kor_name){
								this.min5_rank9_coin = list5[8].coin_ticker;
								this.min5_rank9_coin_name = list5[8].coin_kor_name;

								if(this.min5_rank9_coin_name != ''){
									if(list5[8].gubun == '상승'){
										this.min5_rank9_border_color = 'red';
									}else if(list5[8].gubun == '하락'){
										this.min5_rank9_border_color = 'blue';
									}else if(list5[8].gubun == '보합'){
										this.min5_rank9_border_color = 'black';
									}

									setTimeout(() => 
										this.min5_rank9_border_color = 'white'
									, 500);
								}
							}
							this.min5_rank9_gubun = list5[8].gubun;
							this.min5_rank9_open = list5[8].o_price;
							this.min5_rank9_low = list5[8].l_price;
							this.min5_rank9_high = list5[8].h_price;
							this.min5_rank9_close = Number(list5[8].c_price).toLocaleString('ko-KR');
							this.min5_rank9_o_c_rate = list5[8].o_c_rate;
							this.min5_rank9_o_c_subtract = Number(list5[8].o_c_subtract).toLocaleString('ko-KR');
							this.min5_rank9_price_volume = Number(list5[8].price_volume).toLocaleString('ko-KR');

							if(this.min5_rank10_coin_name != list5[9].coin_kor_name){
								this.min5_rank10_coin = list5[9].coin_ticker;
								this.min5_rank10_coin_name = list5[9].coin_kor_name;

								if(this.min5_rank10_coin_name != ''){
									if(list5[9].gubun == '상승'){
										this.min5_rank10_border_color = 'red';
									}else if(list5[9].gubun == '하락'){
										this.min5_rank10_border_color = 'blue';
									}else if(list5[9].gubun == '보합'){
										this.min5_rank10_border_color = 'black';
									}

									setTimeout(() => 
										this.min5_rank10_border_color = 'white'
									, 500);
								}
							}
							this.min5_rank10_gubun = list5[9].gubun;
							this.min5_rank10_open = list5[9].o_price;
							this.min5_rank10_low = list5[9].l_price;
							this.min5_rank10_high = list5[9].h_price;
							this.min5_rank10_close = Number(list5[9].c_price).toLocaleString('ko-KR');
							this.min5_rank10_o_c_rate = list5[9].o_c_rate;
							this.min5_rank10_o_c_subtract = Number(list5[9].o_c_subtract).toLocaleString('ko-KR');
							this.min5_rank10_price_volume = Number(list5[9].price_volume).toLocaleString('ko-KR');

						}

						if(list15.length == tmpBithumbList.length){
							list15 = list15.sort((a,b) => Number(b.price_volume) - Number(a.price_volume));
							
							if(this.min15_rank1_coin_name != list15[0].coin_kor_name){
								this.min15_rank1_coin = list15[0].coin_ticker;
								this.min15_rank1_coin_name = list15[0].coin_kor_name;

								if(this.min15_rank1_coin_name != ''){
									if(list15[0].gubun == '상승'){
										this.min15_rank1_border_color = 'red';
									}else if(list15[0].gubun == '하락'){
										this.min15_rank1_border_color = 'blue';
									}else if(list15[0].gubun == '보합'){
										this.min15_rank1_border_color = 'black';
									}

									setTimeout(() => 
										this.min15_rank1_border_color = 'white'
									, 500);
								}
							}
							this.min15_rank1_gubun = list15[0].gubun;
							this.min15_rank1_open = list15[0].o_price;
							this.min15_rank1_low = list15[0].l_price;
							this.min15_rank1_high = list15[0].h_price;
							this.min15_rank1_close = Number(list15[0].c_price).toLocaleString('ko-KR');
							this.min15_rank1_o_c_rate = list15[0].o_c_rate;
							this.min15_rank1_o_c_subtract = Number(list15[0].o_c_subtract).toLocaleString('ko-KR');
							this.min15_rank1_price_volume = Number(list15[0].price_volume).toLocaleString('ko-KR');
							
							if(this.min15_rank2_coin_name != list15[1].coin_kor_name){
								this.min15_rank2_coin = list15[1].coin_ticker;
								this.min15_rank2_coin_name = list15[1].coin_kor_name;

								if(this.min15_rank2_coin_name != ''){
									if(list15[1].gubun == '상승'){
										this.min15_rank2_border_color = 'red';
									}else if(list15[1].gubun == '하락'){
										this.min15_rank2_border_color = 'blue';
									}else if(list15[1].gubun == '보합'){
										this.min15_rank2_border_color = 'black';
									}

									setTimeout(() => 
										this.min15_rank2_border_color = 'white'
									, 500);
								}
							}
							this.min15_rank2_gubun = list15[1].gubun;
							this.min15_rank2_open = list15[1].o_price;
							this.min15_rank2_low = list15[1].l_price;
							this.min15_rank2_high = list15[1].h_price;
							this.min15_rank2_close = Number(list15[1].c_price).toLocaleString('ko-KR');
							this.min15_rank2_o_c_rate = list15[1].o_c_rate;
							this.min15_rank2_o_c_subtract = Number(list15[1].o_c_subtract).toLocaleString('ko-KR');
							this.min15_rank2_price_volume = Number(list15[1].price_volume).toLocaleString('ko-KR');
							
							if(this.min15_rank3_coin_name != list15[2].coin_kor_name){
								this.min15_rank3_coin = list15[2].coin_ticker;
								this.min15_rank3_coin_name = list15[2].coin_kor_name;

								if(this.min15_rank3_coin_name != ''){
									if(list15[2].gubun == '상승'){
										this.min15_rank3_border_color = 'red';
									}else if(list15[2].gubun == '하락'){
										this.min15_rank3_border_color = 'blue';
									}else if(list15[2].gubun == '보합'){
										this.min15_rank3_border_color = 'black';
									}

									setTimeout(() => 
										this.min15_rank3_border_color = 'white'
									, 500);
								}
							}
							this.min15_rank3_gubun = list15[2].gubun;
							this.min15_rank3_open = list15[2].o_price;
							this.min15_rank3_low = list15[2].l_price;
							this.min15_rank3_high = list15[2].h_price;
							this.min15_rank3_close = Number(list15[2].c_price).toLocaleString('ko-KR');
							this.min15_rank3_o_c_rate = list15[2].o_c_rate;
							this.min15_rank3_o_c_subtract = Number(list15[2].o_c_subtract).toLocaleString('ko-KR');
							this.min15_rank3_price_volume = Number(list15[2].price_volume).toLocaleString('ko-KR');
							
							if(this.min15_rank4_coin_name != list15[3].coin_kor_name){
								this.min15_rank4_coin = list15[3].coin_ticker;
								this.min15_rank4_coin_name = list15[3].coin_kor_name;

								if(this.min15_rank4_coin_name != ''){
									if(list15[3].gubun == '상승'){
										this.min15_rank4_border_color = 'red';
									}else if(list15[3].gubun == '하락'){
										this.min15_rank4_border_color = 'blue';
									}else if(list15[3].gubun == '보합'){
										this.min15_rank4_border_color = 'black';
									}

									setTimeout(() => 
										this.min15_rank4_border_color = 'white'
									, 500);
								}
							}
							this.min15_rank4_gubun = list15[3].gubun;
							this.min15_rank4_open = list15[3].o_price;
							this.min15_rank4_low = list15[3].l_price;
							this.min15_rank4_high = list15[3].h_price;
							this.min15_rank4_close = Number(list15[3].c_price).toLocaleString('ko-KR');
							this.min15_rank4_o_c_rate = list15[3].o_c_rate;
							this.min15_rank4_o_c_subtract = Number(list15[3].o_c_subtract).toLocaleString('ko-KR');
							this.min15_rank4_price_volume = Number(list15[3].price_volume).toLocaleString('ko-KR');
							
							if(this.min15_rank5_coin_name != list15[4].coin_kor_name){
								this.min15_rank5_coin = list15[4].coin_ticker;
								this.min15_rank5_coin_name = list15[4].coin_kor_name;

								if(this.min15_rank5_coin_name != ''){
									if(list15[4].gubun == '상승'){
										this.min15_rank5_border_color = 'red';
									}else if(list15[4].gubun == '하락'){
										this.min15_rank5_border_color = 'blue';
									}else if(list15[4].gubun == '보합'){
										this.min15_rank5_border_color = 'black';
									}

									setTimeout(() => 
										this.min15_rank5_border_color = 'white'
									, 500);
								}
							}
							this.min15_rank5_gubun = list15[4].gubun;
							this.min15_rank5_open = list15[4].o_price;
							this.min15_rank5_low = list15[4].l_price;
							this.min15_rank5_high = list15[4].h_price;
							this.min15_rank5_close = Number(list15[4].c_price).toLocaleString('ko-KR');
							this.min15_rank5_o_c_rate = list15[4].o_c_rate;
							this.min15_rank5_o_c_subtract = Number(list15[4].o_c_subtract).toLocaleString('ko-KR');
							this.min15_rank5_price_volume = Number(list15[4].price_volume).toLocaleString('ko-KR');
							
							if(this.min15_rank6_coin_name != list15[5].coin_kor_name){
								this.min15_rank6_coin = list15[5].coin_ticker;
								this.min15_rank6_coin_name = list15[5].coin_kor_name;

								if(this.min15_rank6_coin_name != ''){
									if(list15[5].gubun == '상승'){
										this.min15_rank6_border_color = 'red';
									}else if(list15[5].gubun == '하락'){
										this.min15_rank6_border_color = 'blue';
									}else if(list15[5].gubun == '보합'){
										this.min15_rank6_border_color = 'black';
									}

									setTimeout(() => 
										this.min15_rank6_border_color = 'white'
									, 500);
								}
							}
							this.min15_rank6_gubun = list15[5].gubun;
							this.min15_rank6_open = list15[5].o_price;
							this.min15_rank6_low = list15[5].l_price;
							this.min15_rank6_high = list15[5].h_price;
							this.min15_rank6_close = Number(list15[5].c_price).toLocaleString('ko-KR');
							this.min15_rank6_o_c_rate = list15[5].o_c_rate;
							this.min15_rank6_o_c_subtract = Number(list15[5].o_c_subtract).toLocaleString('ko-KR');
							this.min15_rank6_price_volume = Number(list15[5].price_volume).toLocaleString('ko-KR');
							
							if(this.min15_rank7_coin_name != list15[6].coin_kor_name){
								this.min15_rank7_coin = list15[6].coin_ticker;
								this.min15_rank7_coin_name = list15[6].coin_kor_name;

								if(this.min15_rank7_coin_name != ''){
									if(list15[6].gubun == '상승'){
										this.min15_rank7_border_color = 'red';
									}else if(list15[6].gubun == '하락'){
										this.min15_rank7_border_color = 'blue';
									}else if(list15[6].gubun == '보합'){
										this.min15_rank7_border_color = 'black';
									}

									setTimeout(() => 
										this.min15_rank7_border_color = 'white'
									, 500);
								}
							}
							this.min15_rank7_gubun = list15[6].gubun;
							this.min15_rank7_open = list15[6].o_price;
							this.min15_rank7_low = list15[6].l_price;
							this.min15_rank7_high = list15[6].h_price;
							this.min15_rank7_close = Number(list15[6].c_price).toLocaleString('ko-KR');
							this.min15_rank7_o_c_rate = list15[6].o_c_rate;
							this.min15_rank7_o_c_subtract = Number(list15[6].o_c_subtract).toLocaleString('ko-KR');
							this.min15_rank7_price_volume = Number(list15[6].price_volume).toLocaleString('ko-KR');
							
							if(this.min15_rank8_coin_name != list15[7].coin_kor_name){
								this.min15_rank8_coin = list15[7].coin_ticker;
								this.min15_rank8_coin_name = list15[7].coin_kor_name;

								if(this.min15_rank8_coin_name != ''){
									if(list15[7].gubun == '상승'){
										this.min15_rank8_border_color = 'red';
									}else if(list15[7].gubun == '하락'){
										this.min15_rank8_border_color = 'blue';
									}else if(list15[7].gubun == '보합'){
										this.min15_rank8_border_color = 'black';
									}

									setTimeout(() => 
										this.min15_rank8_border_color = 'white'
									, 500);
								}
							}
							this.min15_rank8_gubun = list15[7].gubun;
							this.min15_rank8_open = list15[7].o_price;
							this.min15_rank8_low = list15[7].l_price;
							this.min15_rank8_high = list15[7].h_price;
							this.min15_rank8_close = Number(list15[7].c_price).toLocaleString('ko-KR');
							this.min15_rank8_o_c_rate = list15[7].o_c_rate;
							this.min15_rank8_o_c_subtract = Number(list15[7].o_c_subtract).toLocaleString('ko-KR');
							this.min15_rank8_price_volume = Number(list15[7].price_volume).toLocaleString('ko-KR');
							
							if(this.min15_rank9_coin_name != list15[8].coin_kor_name){
								this.min15_rank9_coin = list15[8].coin_ticker;
								this.min15_rank9_coin_name = list15[8].coin_kor_name;

								if(this.min15_rank9_coin_name != ''){
									if(list15[8].gubun == '상승'){
										this.min15_rank9_border_color = 'red';
									}else if(list15[8].gubun == '하락'){
										this.min15_rank9_border_color = 'blue';
									}else if(list15[8].gubun == '보합'){
										this.min15_rank9_border_color = 'black';
									}

									setTimeout(() => 
										this.min15_rank9_border_color = 'white'
									, 500);
								}
							}
							this.min15_rank9_gubun = list15[8].gubun;
							this.min15_rank9_open = list15[8].o_price;
							this.min15_rank9_low = list15[8].l_price;
							this.min15_rank9_high = list15[8].h_price;
							this.min15_rank9_close = Number(list15[8].c_price).toLocaleString('ko-KR');
							this.min15_rank9_o_c_rate = list15[8].o_c_rate;
							this.min15_rank9_o_c_subtract = Number(list15[8].o_c_subtract).toLocaleString('ko-KR');
							this.min15_rank9_price_volume = Number(list15[8].price_volume).toLocaleString('ko-KR');
							
							if(this.min15_rank10_coin_name != list15[9].coin_kor_name){
								this.min15_rank10_coin = list15[9].coin_ticker;
								this.min15_rank10_coin_name = list15[9].coin_kor_name;

								if(this.min15_rank10_coin_name != ''){
									if(list15[9].gubun == '상승'){
										this.min15_rank10_border_color = 'red';
									}else if(list15[9].gubun == '하락'){
										this.min15_rank10_border_color = 'blue';
									}else if(list15[9].gubun == '보합'){
										this.min15_rank10_border_color = 'black';
									}

									setTimeout(() => 
										this.min15_rank10_border_color = 'white'
									, 500);
								}
							}
							this.min15_rank10_gubun = list15[9].gubun;
							this.min15_rank10_open = list15[9].o_price;
							this.min15_rank10_low = list15[9].l_price;
							this.min15_rank10_high = list15[9].h_price;
							this.min15_rank10_close = Number(list15[9].c_price).toLocaleString('ko-KR');
							this.min15_rank10_o_c_rate = list15[9].o_c_rate;
							this.min15_rank10_o_c_subtract = Number(list15[9].o_c_subtract).toLocaleString('ko-KR');
							this.min15_rank10_price_volume = Number(list15[9].price_volume).toLocaleString('ko-KR');
							
							console.log('종료 ' + (new Date()))

							if(list5.length == tmpBithumbList.length && list15.length == tmpBithumbList.length){
								var today = new Date();
								var year = today.getFullYear();
								var month = ('0' + (today.getMonth() + 1)).slice(-2);
								var day = ('0' + today.getDate()).slice(-2);
								var hours = ('0' + today.getHours()).slice(-2); 
								var minutes = ('0' + today.getMinutes()).slice(-2);
								var seconds = ('0' + today.getSeconds()).slice(-2); 
								
								this.min5_gijun_datetime_kst = year + '-' + month  + '-' + day + ' ' + hours + ':' + minutes  + ':' + seconds;
								this.min15_gijun_datetime_kst = year + '-' + month  + '-' + day + ' ' + hours + ':' + minutes  + ':' + seconds;

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
				this.Data_MIN_5_15_Make();
			}

			if(this.min5_gijun_datetime_kst != '' || this.min15_gijun_datetime_kst != ''){
				var min5_mi = (this.min5_gijun_datetime_kst).substr(14, 2)
				var min15_mi = (this.min15_gijun_datetime_kst).substr(14, 2)
				if((Number(min5_mi) == 0 && Number(mi) == 2) || (Number(min15_mi) == 0 && Number(mi) == 2)){
					this.Data_MIN_5_15_Make();
				}else if((Number(min5_mi) < Number(mi)-1) || (Number(min5_mi) > Number(mi)+1) || (Number(min15_mi) < Number(mi)-1) || (Number(min15_mi) > Number(mi)+1)){
					this.Data_MIN_5_15_Make();
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