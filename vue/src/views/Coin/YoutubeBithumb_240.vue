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
					<v-col xl="8" md="8" sm="8" style="padding-left:20px; text-align:left; font-weight:bold; font-size:35px;">
						빗썸 4시간 거래대금 [{{ min240_api_datetime_kst }}]
					</v-col>
					<v-col xl="4" md="4" sm="4" style="padding-right:0px; text-align:right; font-weight:bold; font-size:35px;">
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
						변동율(4시간)
					</v-col>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						추정 거래대금(4시간)
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_volume_rank1_border_color }">
						{{ min240_volume_rank1_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank1_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_volume_rank1_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_volume_rank1_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank1_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_volume_rank1_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_volume_rank1_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_volume_rank1_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank1_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_volume_rank1_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank1_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_volume_rank2_border_color }">
						{{ min240_volume_rank2_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank2_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_volume_rank2_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_volume_rank2_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank2_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_volume_rank2_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_volume_rank2_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_volume_rank2_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank2_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_volume_rank2_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank2_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_volume_rank3_border_color }">
						{{ min240_volume_rank3_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank3_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_volume_rank3_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_volume_rank3_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank3_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_volume_rank3_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_volume_rank3_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_volume_rank3_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank3_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_volume_rank3_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank3_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_volume_rank4_border_color }">
						{{ min240_volume_rank4_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank4_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_volume_rank4_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_volume_rank4_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank4_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_volume_rank4_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_volume_rank4_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_volume_rank4_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank4_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_volume_rank4_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank4_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_volume_rank5_border_color }">
						{{ min240_volume_rank5_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank5_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_volume_rank5_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_volume_rank5_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank5_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_volume_rank5_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_volume_rank5_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_volume_rank5_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank5_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_volume_rank5_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank5_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_volume_rank6_border_color }">
						{{ min240_volume_rank6_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank6_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_volume_rank6_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_volume_rank6_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank6_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_volume_rank6_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_volume_rank6_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_volume_rank6_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank6_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_volume_rank6_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank6_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_volume_rank7_border_color }">
						{{ min240_volume_rank7_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank7_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_volume_rank7_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_volume_rank7_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank7_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_volume_rank7_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_volume_rank7_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_volume_rank7_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank7_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_volume_rank7_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank7_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_volume_rank8_border_color }">
						{{ min240_volume_rank8_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank8_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_volume_rank8_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_volume_rank8_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank8_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_volume_rank8_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_volume_rank8_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_volume_rank8_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank8_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_volume_rank8_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank8_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_volume_rank9_border_color }">
						{{ min240_volume_rank9_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank9_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_volume_rank9_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_volume_rank9_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank9_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_volume_rank9_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_volume_rank9_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_volume_rank9_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank9_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_volume_rank9_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank9_price_volume }}
					</v-col>
				</v-row>
				
				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_volume_rank10_border_color }">
						{{ min240_volume_rank10_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank10_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_volume_rank10_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_volume_rank10_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank10_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_volume_rank10_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_volume_rank10_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_volume_rank10_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_volume_rank10_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_volume_rank10_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_volume_rank10_price_volume }}
					</v-col>
				</v-row>
			</v-col>
			<v-col xl="6" md="6" sm="6" style="text-align:center; padding-top:0px; padding-left:40px; padding-right:25px;">
				<v-row>
					<v-col xl="8" md="8" sm="8" style="padding-left:20px; text-align:left; font-weight:bold; font-size:35px;">
						빗썸 4시간 변동율 [{{ min240_api_datetime_kst }}]
					</v-col>
					<v-col xl="4" md="4" sm="4" style="padding-right:25px; text-align:right; font-weight:bold; font-size:35px;">
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
						변동율(4시간)
					</v-col>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						추정 거래대금(4시간)
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_oc_rank1_border_color }">
						{{ min240_oc_rank1_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank1_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_oc_rank1_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_oc_rank1_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank1_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_oc_rank1_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_oc_rank1_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_oc_rank1_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank1_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_oc_rank1_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank1_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_oc_rank2_border_color }">
						{{ min240_oc_rank2_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank2_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_oc_rank2_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_oc_rank2_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank2_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_oc_rank2_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_oc_rank2_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_oc_rank2_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank2_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_oc_rank2_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank2_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_oc_rank3_border_color }">
						{{ min240_oc_rank3_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank3_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_oc_rank3_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_oc_rank3_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank3_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_oc_rank3_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_oc_rank3_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_oc_rank3_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank3_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_oc_rank3_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank3_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_oc_rank4_border_color }">
						{{ min240_oc_rank4_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank4_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_oc_rank4_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_oc_rank4_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank4_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_oc_rank4_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_oc_rank4_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_oc_rank4_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank4_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_oc_rank4_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank4_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_oc_rank5_border_color }">
						{{ min240_oc_rank5_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank5_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_oc_rank5_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_oc_rank5_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank5_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_oc_rank5_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_oc_rank5_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_oc_rank5_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank5_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_oc_rank5_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank5_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_oc_rank6_border_color }">
						{{ min240_oc_rank6_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank6_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_oc_rank6_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_oc_rank6_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank6_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_oc_rank6_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_oc_rank6_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_oc_rank6_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank6_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_oc_rank6_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank6_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_oc_rank7_border_color }">
						{{ min240_oc_rank7_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank7_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_oc_rank7_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_oc_rank7_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank7_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_oc_rank7_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_oc_rank7_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_oc_rank7_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank7_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_oc_rank7_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank7_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_oc_rank8_border_color }">
						{{ min240_oc_rank8_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank8_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_oc_rank8_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_oc_rank8_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank8_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_oc_rank8_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_oc_rank8_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_oc_rank8_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank8_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_oc_rank8_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank8_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_oc_rank9_border_color }">
						{{ min240_oc_rank9_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank9_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_oc_rank9_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_oc_rank9_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank9_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_oc_rank9_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_oc_rank9_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_oc_rank9_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank9_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_oc_rank9_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank9_price_volume }}
					</v-col>
				</v-row>
				
				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min240_oc_rank10_border_color }">
						{{ min240_oc_rank10_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank10_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min240_oc_rank10_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min240_oc_rank10_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank10_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min240_oc_rank10_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.min240_oc_rank10_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ min240_oc_rank10_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.min240_oc_rank10_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ min240_oc_rank10_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_oc_rank10_price_volume }}
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
			
			min240_api_datetime_kst : '',
			min240_gijun_datetime_kst : '',

			min240_volume_rank1_coin : '',
			min240_volume_rank1_coin_name : '',
			min240_volume_rank1_border_color : 'white',
			min240_volume_rank1_gubun : '',
			min240_volume_rank1_open : '',
			min240_volume_rank1_low : '',
			min240_volume_rank1_high : '',
			min240_volume_rank1_close : '',
			min240_volume_rank1_o_c_rate : '',
			min240_volume_rank1_o_c_subtract : '',
			min240_volume_rank1_price_volume : '',

			min240_volume_rank2_coin : '',
			min240_volume_rank2_coin_name : '',
			min240_volume_rank2_border_color : 'white',
			min240_volume_rank2_gubun : '',
			min240_volume_rank2_open : '',
			min240_volume_rank2_low : '',
			min240_volume_rank2_high : '',
			min240_volume_rank2_close : '',
			min240_volume_rank2_o_c_rate : '',
			min240_volume_rank2_o_c_subtract : '',
			min240_volume_rank2_price_volume : '',

			min240_volume_rank3_coin : '',
			min240_volume_rank3_coin_name : '',
			min240_volume_rank3_border_color : 'white',
			min240_volume_rank3_gubun : '',
			min240_volume_rank3_open : '',
			min240_volume_rank3_low : '',
			min240_volume_rank3_high : '',
			min240_volume_rank3_close : '',
			min240_volume_rank3_o_c_rate : '',
			min240_volume_rank3_o_c_subtract : '',
			min240_volume_rank3_price_volume : '',

			min240_volume_rank4_coin : '',
			min240_volume_rank4_coin_name : '',
			min240_volume_rank4_border_color : 'white',
			min240_volume_rank4_gubun : '',
			min240_volume_rank4_open : '',
			min240_volume_rank4_low : '',
			min240_volume_rank4_high : '',
			min240_volume_rank4_close : '',
			min240_volume_rank4_o_c_rate : '',
			min240_volume_rank4_o_c_subtract : '',
			min240_volume_rank4_price_volume : '',

			min240_volume_rank5_coin : '',
			min240_volume_rank5_coin_name : '',
			min240_volume_rank5_border_color : 'white',
			min240_volume_rank5_gubun : '',
			min240_volume_rank5_open : '',
			min240_volume_rank5_low : '',
			min240_volume_rank5_high : '',
			min240_volume_rank5_close : '',
			min240_volume_rank5_o_c_rate : '',
			min240_volume_rank5_o_c_subtract : '',
			min240_volume_rank5_price_volume : '',

			min240_volume_rank6_coin : '',
			min240_volume_rank6_coin_name : '',
			min240_volume_rank6_border_color : 'white',
			min240_volume_rank6_gubun : '',
			min240_volume_rank6_open : '',
			min240_volume_rank6_low : '',
			min240_volume_rank6_high : '',
			min240_volume_rank6_close : '',
			min240_volume_rank6_o_c_rate : '',
			min240_volume_rank6_o_c_subtract : '',
			min240_volume_rank6_price_volume : '',

			min240_volume_rank7_coin : '',
			min240_volume_rank7_coin_name : '',
			min240_volume_rank7_border_color : 'white',
			min240_volume_rank7_gubun : '',
			min240_volume_rank7_open : '',
			min240_volume_rank7_low : '',
			min240_volume_rank7_high : '',
			min240_volume_rank7_close : '',
			min240_volume_rank7_o_c_rate : '',
			min240_volume_rank7_o_c_subtract : '',
			min240_volume_rank7_price_volume : '',

			min240_volume_rank8_coin : '',
			min240_volume_rank8_coin_name : '',
			min240_volume_rank8_border_color : 'white',
			min240_volume_rank8_gubun : '',
			min240_volume_rank8_open : '',
			min240_volume_rank8_low : '',
			min240_volume_rank8_high : '',
			min240_volume_rank8_close : '',
			min240_volume_rank8_o_c_rate : '',
			min240_volume_rank8_o_c_subtract : '',
			min240_volume_rank8_price_volume : '',

			min240_volume_rank9_coin : '',
			min240_volume_rank9_coin_name : '',
			min240_volume_rank9_border_color : 'white',
			min240_volume_rank9_gubun : '',
			min240_volume_rank9_open : '',
			min240_volume_rank9_low : '',
			min240_volume_rank9_high : '',
			min240_volume_rank9_close : '',
			min240_volume_rank9_o_c_rate : '',
			min240_volume_rank9_o_c_subtract : '',
			min240_volume_rank9_price_volume : '',

			min240_volume_rank10_coin : '',
			min240_volume_rank10_coin_name : '',
			min240_volume_rank10_border_color : 'white',
			min240_volume_rank10_gubun : '',
			min240_volume_rank10_open : '',
			min240_volume_rank10_low : '',
			min240_volume_rank10_high : '',
			min240_volume_rank10_close : '',
			min240_volume_rank10_o_c_rate : '',
			min240_volume_rank10_o_c_subtract : '',
			min240_volume_rank10_price_volume : '',

			min240_oc_rank1_coin : '',
			min240_oc_rank1_coin_name : '',
			min240_oc_rank1_border_color : 'white',
			min240_oc_rank1_gubun : '',
			min240_oc_rank1_open : '',
			min240_oc_rank1_low : '',
			min240_oc_rank1_high : '',
			min240_oc_rank1_close : '',
			min240_oc_rank1_o_c_rate : '',
			min240_oc_rank1_o_c_subtract : '',
			min240_oc_rank1_price_volume : '',

			min240_oc_rank2_coin : '',
			min240_oc_rank2_coin_name : '',
			min240_oc_rank2_border_color : 'white',
			min240_oc_rank2_gubun : '',
			min240_oc_rank2_open : '',
			min240_oc_rank2_low : '',
			min240_oc_rank2_high : '',
			min240_oc_rank2_close : '',
			min240_oc_rank2_o_c_rate : '',
			min240_oc_rank2_o_c_subtract : '',
			min240_oc_rank2_price_volume : '',

			min240_oc_rank3_coin : '',
			min240_oc_rank3_coin_name : '',
			min240_oc_rank3_border_color : 'white',
			min240_oc_rank3_gubun : '',
			min240_oc_rank3_open : '',
			min240_oc_rank3_low : '',
			min240_oc_rank3_high : '',
			min240_oc_rank3_close : '',
			min240_oc_rank3_o_c_rate : '',
			min240_oc_rank3_o_c_subtract : '',
			min240_oc_rank3_price_volume : '',

			min240_oc_rank4_coin : '',
			min240_oc_rank4_coin_name : '',
			min240_oc_rank4_border_color : 'white',
			min240_oc_rank4_gubun : '',
			min240_oc_rank4_open : '',
			min240_oc_rank4_low : '',
			min240_oc_rank4_high : '',
			min240_oc_rank4_close : '',
			min240_oc_rank4_o_c_rate : '',
			min240_oc_rank4_o_c_subtract : '',
			min240_oc_rank4_price_volume : '',

			min240_oc_rank5_coin : '',
			min240_oc_rank5_coin_name : '',
			min240_oc_rank5_border_color : 'white',
			min240_oc_rank5_gubun : '',
			min240_oc_rank5_open : '',
			min240_oc_rank5_low : '',
			min240_oc_rank5_high : '',
			min240_oc_rank5_close : '',
			min240_oc_rank5_o_c_rate : '',
			min240_oc_rank5_o_c_subtract : '',
			min240_oc_rank5_price_volume : '',

			min240_oc_rank6_coin : '',
			min240_oc_rank6_coin_name : '',
			min240_oc_rank6_border_color : 'white',
			min240_oc_rank6_gubun : '',
			min240_oc_rank6_open : '',
			min240_oc_rank6_low : '',
			min240_oc_rank6_high : '',
			min240_oc_rank6_close : '',
			min240_oc_rank6_o_c_rate : '',
			min240_oc_rank6_o_c_subtract : '',
			min240_oc_rank6_price_volume : '',

			min240_oc_rank7_coin : '',
			min240_oc_rank7_coin_name : '',
			min240_oc_rank7_border_color : 'white',
			min240_oc_rank7_gubun : '',
			min240_oc_rank7_open : '',
			min240_oc_rank7_low : '',
			min240_oc_rank7_high : '',
			min240_oc_rank7_close : '',
			min240_oc_rank7_o_c_rate : '',
			min240_oc_rank7_o_c_subtract : '',
			min240_oc_rank7_price_volume : '',

			min240_oc_rank8_coin : '',
			min240_oc_rank8_coin_name : '',
			min240_oc_rank8_border_color : 'white',
			min240_oc_rank8_gubun : '',
			min240_oc_rank8_open : '',
			min240_oc_rank8_low : '',
			min240_oc_rank8_high : '',
			min240_oc_rank8_close : '',
			min240_oc_rank8_o_c_rate : '',
			min240_oc_rank8_o_c_subtract : '',
			min240_oc_rank8_price_volume : '',

			min240_oc_rank9_coin : '',
			min240_oc_rank9_coin_name : '',
			min240_oc_rank9_border_color : 'white',
			min240_oc_rank9_gubun : '',
			min240_oc_rank9_open : '',
			min240_oc_rank9_low : '',
			min240_oc_rank9_high : '',
			min240_oc_rank9_close : '',
			min240_oc_rank9_o_c_rate : '',
			min240_oc_rank9_o_c_subtract : '',
			min240_oc_rank9_price_volume : '',

			min240_oc_rank10_coin : '',
			min240_oc_rank10_coin_name : '',
			min240_oc_rank10_border_color : 'white',
			min240_oc_rank10_gubun : '',
			min240_oc_rank10_open : '',
			min240_oc_rank10_low : '',
			min240_oc_rank10_high : '',
			min240_oc_rank10_close : '',
			min240_oc_rank10_o_c_rate : '',
			min240_oc_rank10_o_c_subtract : '',
			min240_oc_rank10_price_volume : '',
      	}
    },
	components: {
	},
    mounted() {
		this.Data_MIN_240_Make();
		this.CurrentDataTime();
		//this.Data_Notice();
		setInterval(this.CurrentDataTime.bind(this) , 1000);
	},
	methods: {
		Data_MIN_240_Make(){
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
			var list240_volume = [];
			var list240_oc = [];

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
					var timestamp240 = BithumbList[i].timestamp240;
					var gijun_datetime_240 = BithumbList[i].gijun_datetime_240;
					
					tmpBithumbList.push('https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/'+String(number)+'_C0100/1H');
					axios.get('https://gw.bithumb.com/observer/chart/public/candlesticknew_trview/'+String(number)+'_C0100/1H', {
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

						var YN240 = 'N'

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
							
							if(BigInt(timestamp240) <= BigInt(tmp_timestamp)){
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
								o_c_subtract_240 = (Math.round((parseFloat(c_price_240) - parseFloat(o_price_240)) * 10000000000) / 10000000000)
								o_c_rate_240 = ((parseFloat(o_c_subtract_240)/parseFloat(o_price_240))*100).toFixed(2)
							}
						}

						o_c_rate_240 = o_c_rate_240.replace('-0.00','0.00')
						this.min240_api_datetime_kst = (gijun_datetime_240.substr(0, 16)).replace('20','')

						if(YN240 == 'Y'){
							list240_volume.push({
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
							list240_volume.push({
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

						if(list240_volume.length == tmpBithumbList.length){
							list240_volume = list240_volume.sort((a,b) => Number(b.price_volume) - Number(a.price_volume));
							
							if(this.min240_volume_rank1_coin_name != list240_volume[0].coin_kor_name){
								this.min240_volume_rank1_coin = list240_volume[0].coin_ticker;
								this.min240_volume_rank1_coin_name = list240_volume[0].coin_kor_name;

								if(this.min240_volume_rank1_coin_name != ''){
									if(list240_volume[0].gubun == '상승'){
										this.min240_volume_rank1_border_color = 'red';
									}else if(list240_volume[0].gubun == '하락'){
										this.min240_volume_rank1_border_color = 'blue';
									}else if(list240_volume[0].gubun == '보합'){
										this.min240_volume_rank1_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_volume_rank1_border_color = 'white'
									, 500);
								}
							}
							this.min240_volume_rank1_gubun = list240_volume[0].gubun;
							this.min240_volume_rank1_open = list240_volume[0].o_price;
							this.min240_volume_rank1_low = list240_volume[0].l_price;
							this.min240_volume_rank1_high = list240_volume[0].h_price;
							this.min240_volume_rank1_close = '';
							this.min240_volume_rank1_o_c_rate = list240_volume[0].o_c_rate + '%';
							this.min240_volume_rank1_o_c_subtract = '';
							this.min240_volume_rank1_price_volume = Number(list240_volume[0].price_volume).toLocaleString('ko-KR');

							if(Number(list240_volume[0].c_price) > 999){
								this.min240_volume_rank1_close = Number(list240_volume[0].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank1_close = list240_volume[0].c_price
							}

							if(Math.abs(list240_volume[0].o_c_subtract) > 999){
								this.min240_volume_rank1_o_c_subtract = Number(list240_volume[0].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank1_o_c_subtract = list240_volume[0].o_c_subtract
							}

							if(this.min240_volume_rank2_coin_name != list240_volume[1].coin_kor_name){
								this.min240_volume_rank2_coin = list240_volume[1].coin_ticker;
								this.min240_volume_rank2_coin_name = list240_volume[1].coin_kor_name;

								if(this.min240_volume_rank2_coin_name != ''){
									if(list240_volume[1].gubun == '상승'){
										this.min240_volume_rank2_border_color = 'red';
									}else if(list240_volume[1].gubun == '하락'){
										this.min240_volume_rank2_border_color = 'blue';
									}else if(list240_volume[1].gubun == '보합'){
										this.min240_volume_rank2_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_volume_rank2_border_color = 'white'
									, 500);
								}
							}
							this.min240_volume_rank2_gubun = list240_volume[1].gubun;
							this.min240_volume_rank2_open = list240_volume[1].o_price;
							this.min240_volume_rank2_low = list240_volume[1].l_price;
							this.min240_volume_rank2_high = list240_volume[1].h_price;
							this.min240_volume_rank2_close = '';
							this.min240_volume_rank2_o_c_rate = list240_volume[1].o_c_rate + '%';
							this.min240_volume_rank2_o_c_subtract = '';
							this.min240_volume_rank2_price_volume = Number(list240_volume[1].price_volume).toLocaleString('ko-KR');

							if(Number(list240_volume[1].c_price) > 999){
								this.min240_volume_rank2_close = Number(list240_volume[1].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank2_close = list240_volume[1].c_price
							}

							if(Math.abs(list240_volume[1].o_c_subtract) > 999){
								this.min240_volume_rank2_o_c_subtract = Number(list240_volume[1].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank2_o_c_subtract = list240_volume[1].o_c_subtract
							}

							if(this.min240_volume_rank3_coin_name != list240_volume[2].coin_kor_name){
								this.min240_volume_rank3_coin = list240_volume[2].coin_ticker;
								this.min240_volume_rank3_coin_name = list240_volume[2].coin_kor_name;

								if(this.min240_volume_rank3_coin_name != ''){
									if(list240_volume[2].gubun == '상승'){
										this.min240_volume_rank3_border_color = 'red';
									}else if(list240_volume[2].gubun == '하락'){
										this.min240_volume_rank3_border_color = 'blue';
									}else if(list240_volume[2].gubun == '보합'){
										this.min240_volume_rank3_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_volume_rank3_border_color = 'white'
									, 500);
								}
							}
							this.min240_volume_rank3_gubun = list240_volume[2].gubun;
							this.min240_volume_rank3_open = list240_volume[2].o_price;
							this.min240_volume_rank3_low = list240_volume[2].l_price;
							this.min240_volume_rank3_high = list240_volume[2].h_price;
							this.min240_volume_rank3_close = '';
							this.min240_volume_rank3_o_c_rate = list240_volume[2].o_c_rate + '%';
							this.min240_volume_rank3_o_c_subtract = '';
							this.min240_volume_rank3_price_volume = Number(list240_volume[2].price_volume).toLocaleString('ko-KR');

							if(Number(list240_volume[2].c_price) > 999){
								this.min240_volume_rank3_close = Number(list240_volume[2].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank3_close = list240_volume[2].c_price
							}

							if(Math.abs(list240_volume[2].o_c_subtract) > 999){
								this.min240_volume_rank3_o_c_subtract = Number(list240_volume[2].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank3_o_c_subtract = list240_volume[2].o_c_subtract
							}

							if(this.min240_volume_rank4_coin_name != list240_volume[3].coin_kor_name){
								this.min240_volume_rank4_coin = list240_volume[3].coin_ticker;
								this.min240_volume_rank4_coin_name = list240_volume[3].coin_kor_name;

								if(this.min240_volume_rank4_coin_name != ''){
									if(list240_volume[3].gubun == '상승'){
										this.min240_volume_rank4_border_color = 'red';
									}else if(list240_volume[3].gubun == '하락'){
										this.min240_volume_rank4_border_color = 'blue';
									}else if(list240_volume[3].gubun == '보합'){
										this.min240_volume_rank4_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_volume_rank4_border_color = 'white'
									, 500);
								}
							}
							this.min240_volume_rank4_gubun = list240_volume[3].gubun;
							this.min240_volume_rank4_open = list240_volume[3].o_price;
							this.min240_volume_rank4_low = list240_volume[3].l_price;
							this.min240_volume_rank4_high = list240_volume[3].h_price;
							this.min240_volume_rank4_close = '';
							this.min240_volume_rank4_o_c_rate = list240_volume[3].o_c_rate + '%';
							this.min240_volume_rank4_o_c_subtract = '';
							this.min240_volume_rank4_price_volume = Number(list240_volume[3].price_volume).toLocaleString('ko-KR');

							if(Number(list240_volume[3].c_price) > 999){
								this.min240_volume_rank4_close = Number(list240_volume[3].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank4_close = list240_volume[3].c_price
							}

							if(Math.abs(list240_volume[3].o_c_subtract) > 999){
								this.min240_volume_rank4_o_c_subtract = Number(list240_volume[3].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank4_o_c_subtract = list240_volume[3].o_c_subtract
							}

							if(this.min240_volume_rank5_coin_name != list240_volume[4].coin_kor_name){
								this.min240_volume_rank5_coin = list240_volume[4].coin_ticker;
								this.min240_volume_rank5_coin_name = list240_volume[4].coin_kor_name;

								if(this.min240_volume_rank5_coin_name != ''){
									if(list240_volume[4].gubun == '상승'){
										this.min240_volume_rank5_border_color = 'red';
									}else if(list240_volume[4].gubun == '하락'){
										this.min240_volume_rank5_border_color = 'blue';
									}else if(list240_volume[4].gubun == '보합'){
										this.min240_volume_rank5_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_volume_rank5_border_color = 'white'
									, 500);
								}
							}
							this.min240_volume_rank5_gubun = list240_volume[4].gubun;
							this.min240_volume_rank5_open = list240_volume[4].o_price;
							this.min240_volume_rank5_low = list240_volume[4].l_price;
							this.min240_volume_rank5_high = list240_volume[4].h_price;
							this.min240_volume_rank5_close = '';
							this.min240_volume_rank5_o_c_rate = list240_volume[4].o_c_rate + '%';
							this.min240_volume_rank5_o_c_subtract = '';
							this.min240_volume_rank5_price_volume = Number(list240_volume[4].price_volume).toLocaleString('ko-KR');

							if(Number(list240_volume[4].c_price) > 999){
								this.min240_volume_rank5_close = Number(list240_volume[4].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank5_close = list240_volume[4].c_price
							}

							if(Math.abs(list240_volume[4].o_c_subtract) > 999){
								this.min240_volume_rank5_o_c_subtract = Number(list240_volume[4].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank5_o_c_subtract = list240_volume[4].o_c_subtract
							}

							if(this.min240_volume_rank6_coin_name != list240_volume[5].coin_kor_name){
								this.min240_volume_rank6_coin = list240_volume[5].coin_ticker;
								this.min240_volume_rank6_coin_name = list240_volume[5].coin_kor_name;

								if(this.min240_volume_rank6_coin_name != ''){
									if(list240_volume[5].gubun == '상승'){
										this.min240_volume_rank6_border_color = 'red';
									}else if(list240_volume[5].gubun == '하락'){
										this.min240_volume_rank6_border_color = 'blue';
									}else if(list240_volume[5].gubun == '보합'){
										this.min240_volume_rank6_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_volume_rank6_border_color = 'white'
									, 500);
								}
							}
							this.min240_volume_rank6_gubun = list240_volume[5].gubun;
							this.min240_volume_rank6_open = list240_volume[5].o_price;
							this.min240_volume_rank6_low = list240_volume[5].l_price;
							this.min240_volume_rank6_high = list240_volume[5].h_price;
							this.min240_volume_rank6_close = '';
							this.min240_volume_rank6_o_c_rate = list240_volume[5].o_c_rate + '%';
							this.min240_volume_rank6_o_c_subtract = '';
							this.min240_volume_rank6_price_volume = Number(list240_volume[5].price_volume).toLocaleString('ko-KR');

							if(Number(list240_volume[5].c_price) > 999){
								this.min240_volume_rank6_close = Number(list240_volume[5].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank6_close = list240_volume[5].c_price
							}

							if(Math.abs(list240_volume[5].o_c_subtract) > 999){
								this.min240_volume_rank6_o_c_subtract = Number(list240_volume[5].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank6_o_c_subtract = list240_volume[5].o_c_subtract
							}

							if(this.min240_volume_rank7_coin_name != list240_volume[6].coin_kor_name){
								this.min240_volume_rank7_coin = list240_volume[6].coin_ticker;
								this.min240_volume_rank7_coin_name = list240_volume[6].coin_kor_name;

								if(this.min240_volume_rank7_coin_name != ''){
									if(list240_volume[6].gubun == '상승'){
										this.min240_volume_rank7_border_color = 'red';
									}else if(list240_volume[6].gubun == '하락'){
										this.min240_volume_rank7_border_color = 'blue';
									}else if(list240_volume[6].gubun == '보합'){
										this.min240_volume_rank7_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_volume_rank7_border_color = 'white'
									, 500);
								}
							}
							this.min240_volume_rank7_gubun = list240_volume[6].gubun;
							this.min240_volume_rank7_open = list240_volume[6].o_price;
							this.min240_volume_rank7_low = list240_volume[6].l_price;
							this.min240_volume_rank7_high = list240_volume[6].h_price;
							this.min240_volume_rank7_close = '';
							this.min240_volume_rank7_o_c_rate = list240_volume[6].o_c_rate + '%';
							this.min240_volume_rank7_o_c_subtract = '';
							this.min240_volume_rank7_price_volume = Number(list240_volume[6].price_volume).toLocaleString('ko-KR');

							if(Number(list240_volume[6].c_price) > 999){
								this.min240_volume_rank7_close = Number(list240_volume[6].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank7_close = list240_volume[6].c_price
							}

							if(Math.abs(list240_volume[6].o_c_subtract) > 999){
								this.min240_volume_rank7_o_c_subtract = Number(list240_volume[6].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank7_o_c_subtract = list240_volume[6].o_c_subtract
							}

							if(this.min240_volume_rank8_coin_name != list240_volume[7].coin_kor_name){
								this.min240_volume_rank8_coin = list240_volume[7].coin_ticker;
								this.min240_volume_rank8_coin_name = list240_volume[7].coin_kor_name;

								if(this.min240_volume_rank8_coin_name != ''){
									if(list240_volume[7].gubun == '상승'){
										this.min240_volume_rank8_border_color = 'red';
									}else if(list240_volume[7].gubun == '하락'){
										this.min240_volume_rank8_border_color = 'blue';
									}else if(list240_volume[7].gubun == '보합'){
										this.min240_volume_rank8_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_volume_rank8_border_color = 'white'
									, 500);
								}
							}
							this.min240_volume_rank8_gubun = list240_volume[7].gubun;
							this.min240_volume_rank8_open = list240_volume[7].o_price;
							this.min240_volume_rank8_low = list240_volume[7].l_price;
							this.min240_volume_rank8_high = list240_volume[7].h_price;
							this.min240_volume_rank8_close = '';
							this.min240_volume_rank8_o_c_rate = list240_volume[7].o_c_rate + '%';
							this.min240_volume_rank8_o_c_subtract = '';
							this.min240_volume_rank8_price_volume = Number(list240_volume[7].price_volume).toLocaleString('ko-KR');

							if(Number(list240_volume[7].c_price) > 999){
								this.min240_volume_rank8_close = Number(list240_volume[7].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank8_close = list240_volume[7].c_price
							}

							if(Math.abs(list240_volume[7].o_c_subtract) > 999){
								this.min240_volume_rank8_o_c_subtract = Number(list240_volume[7].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank8_o_c_subtract = list240_volume[7].o_c_subtract
							}

							if(this.min240_volume_rank9_coin_name != list240_volume[8].coin_kor_name){
								this.min240_volume_rank9_coin = list240_volume[8].coin_ticker;
								this.min240_volume_rank9_coin_name = list240_volume[8].coin_kor_name;

								if(this.min240_volume_rank9_coin_name != ''){
									if(list240_volume[8].gubun == '상승'){
										this.min240_volume_rank9_border_color = 'red';
									}else if(list240_volume[8].gubun == '하락'){
										this.min240_volume_rank9_border_color = 'blue';
									}else if(list240_volume[8].gubun == '보합'){
										this.min240_volume_rank9_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_volume_rank9_border_color = 'white'
									, 500);
								}
							}
							this.min240_volume_rank9_gubun = list240_volume[8].gubun;
							this.min240_volume_rank9_open = list240_volume[8].o_price;
							this.min240_volume_rank9_low = list240_volume[8].l_price;
							this.min240_volume_rank9_high = list240_volume[8].h_price;
							this.min240_volume_rank9_close = '';
							this.min240_volume_rank9_o_c_rate = list240_volume[8].o_c_rate + '%';
							this.min240_volume_rank9_o_c_subtract = '';
							this.min240_volume_rank9_price_volume = Number(list240_volume[8].price_volume).toLocaleString('ko-KR');

							if(Number(list240_volume[8].c_price) > 999){
								this.min240_volume_rank9_close = Number(list240_volume[8].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank9_close = list240_volume[8].c_price
							}

							if(Math.abs(list240_volume[8].o_c_subtract) > 999){
								this.min240_volume_rank9_o_c_subtract = Number(list240_volume[8].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank9_o_c_subtract = list240_volume[8].o_c_subtract
							}

							if(this.min240_volume_rank10_coin_name != list240_volume[9].coin_kor_name){
								this.min240_volume_rank10_coin = list240_volume[9].coin_ticker;
								this.min240_volume_rank10_coin_name = list240_volume[9].coin_kor_name;

								if(this.min240_volume_rank10_coin_name != ''){
									if(list240_volume[9].gubun == '상승'){
										this.min240_volume_rank10_border_color = 'red';
									}else if(list240_volume[9].gubun == '하락'){
										this.min240_volume_rank10_border_color = 'blue';
									}else if(list240_volume[9].gubun == '보합'){
										this.min240_volume_rank10_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_volume_rank10_border_color = 'white'
									, 500);
								}
							}
							this.min240_volume_rank10_gubun = list240_volume[9].gubun;
							this.min240_volume_rank10_open = list240_volume[9].o_price;
							this.min240_volume_rank10_low = list240_volume[9].l_price;
							this.min240_volume_rank10_high = list240_volume[9].h_price;
							this.min240_volume_rank10_close = '';
							this.min240_volume_rank10_o_c_rate = list240_volume[9].o_c_rate + '%';
							this.min240_volume_rank10_o_c_subtract = '';
							this.min240_volume_rank10_price_volume = Number(list240_volume[9].price_volume).toLocaleString('ko-KR');

							if(Number(list240_volume[9].c_price) > 999){
								this.min240_volume_rank10_close = Number(list240_volume[9].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank10_close = list240_volume[9].c_price
							}

							if(Math.abs(list240_volume[9].o_c_subtract) > 999){
								this.min240_volume_rank10_o_c_subtract = Number(list240_volume[9].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_volume_rank10_o_c_subtract = list240_volume[9].o_c_subtract
							}

							list240_oc = list240_volume.sort((a,b) => Number(b.o_c_rate) - Number(a.o_c_rate));
							
							if(this.min240_oc_rank1_coin_name != list240_oc[0].coin_kor_name){
								this.min240_oc_rank1_coin = list240_oc[0].coin_ticker;
								this.min240_oc_rank1_coin_name = list240_oc[0].coin_kor_name;

								if(this.min240_oc_rank1_coin_name != ''){
									if(list240_oc[0].gubun == '상승'){
										this.min240_oc_rank1_border_color = 'red';
									}else if(list240_oc[0].gubun == '하락'){
										this.min240_oc_rank1_border_color = 'blue';
									}else if(list240_oc[0].gubun == '보합'){
										this.min240_oc_rank1_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_oc_rank1_border_color = 'white'
									, 500);
								}
							}
							this.min240_oc_rank1_gubun = list240_oc[0].gubun;
							this.min240_oc_rank1_open = list240_oc[0].o_price;
							this.min240_oc_rank1_low = list240_oc[0].l_price;
							this.min240_oc_rank1_high = list240_oc[0].h_price;
							this.min240_oc_rank1_close = '';
							this.min240_oc_rank1_o_c_rate = list240_oc[0].o_c_rate + '%';
							this.min240_oc_rank1_o_c_subtract = '';
							this.min240_oc_rank1_price_volume = Number(list240_oc[0].price_volume).toLocaleString('ko-KR');
							
							if(Number(list240_oc[0].c_price) > 999){
								this.min240_oc_rank1_close = Number(list240_oc[0].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank1_close = list240_oc[0].c_price
							}

							if(Math.abs(list240_oc[0].o_c_subtract) > 999){
								this.min240_oc_rank1_o_c_subtract = Number(list240_oc[0].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank1_o_c_subtract = list240_oc[0].o_c_subtract
							}

							if(this.min240_oc_rank2_coin_name != list240_oc[1].coin_kor_name){
								this.min240_oc_rank2_coin = list240_oc[1].coin_ticker;
								this.min240_oc_rank2_coin_name = list240_oc[1].coin_kor_name;

								if(this.min240_oc_rank2_coin_name != ''){
									if(list240_oc[1].gubun == '상승'){
										this.min240_oc_rank2_border_color = 'red';
									}else if(list240_oc[1].gubun == '하락'){
										this.min240_oc_rank2_border_color = 'blue';
									}else if(list240_oc[1].gubun == '보합'){
										this.min240_oc_rank2_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_oc_rank2_border_color = 'white'
									, 500);
								}
							}
							this.min240_oc_rank2_gubun = list240_oc[1].gubun;
							this.min240_oc_rank2_open = list240_oc[1].o_price;
							this.min240_oc_rank2_low = list240_oc[1].l_price;
							this.min240_oc_rank2_high = list240_oc[1].h_price;
							this.min240_oc_rank2_close = '';
							this.min240_oc_rank2_o_c_rate = list240_oc[1].o_c_rate + '%';
							this.min240_oc_rank2_o_c_subtract = '';
							this.min240_oc_rank2_price_volume = Number(list240_oc[1].price_volume).toLocaleString('ko-KR');
							
							if(Number(list240_oc[1].c_price) > 999){
								this.min240_oc_rank2_close = Number(list240_oc[1].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank2_close = list240_oc[1].c_price
							}

							if(Math.abs(list240_oc[1].o_c_subtract) > 999){
								this.min240_oc_rank2_o_c_subtract = Number(list240_oc[1].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank2_o_c_subtract = list240_oc[1].o_c_subtract
							}

							if(this.min240_oc_rank3_coin_name != list240_oc[2].coin_kor_name){
								this.min240_oc_rank3_coin = list240_oc[2].coin_ticker;
								this.min240_oc_rank3_coin_name = list240_oc[2].coin_kor_name;

								if(this.min240_oc_rank3_coin_name != ''){
									if(list240_oc[2].gubun == '상승'){
										this.min240_oc_rank3_border_color = 'red';
									}else if(list240_oc[2].gubun == '하락'){
										this.min240_oc_rank3_border_color = 'blue';
									}else if(list240_oc[2].gubun == '보합'){
										this.min240_oc_rank3_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_oc_rank3_border_color = 'white'
									, 500);
								}
							}
							this.min240_oc_rank3_gubun = list240_oc[2].gubun;
							this.min240_oc_rank3_open = list240_oc[2].o_price;
							this.min240_oc_rank3_low = list240_oc[2].l_price;
							this.min240_oc_rank3_high = list240_oc[2].h_price;
							this.min240_oc_rank3_close = '';
							this.min240_oc_rank3_o_c_rate = list240_oc[2].o_c_rate + '%';
							this.min240_oc_rank3_o_c_subtract = '';
							this.min240_oc_rank3_price_volume = Number(list240_oc[2].price_volume).toLocaleString('ko-KR');
							
							if(Number(list240_oc[2].c_price) > 999){
								this.min240_oc_rank3_close = Number(list240_oc[2].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank3_close = list240_oc[2].c_price
							}

							if(Math.abs(list240_oc[2].o_c_subtract) > 999){
								this.min240_oc_rank3_o_c_subtract = Number(list240_oc[2].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank3_o_c_subtract = list240_oc[2].o_c_subtract
							}

							if(this.min240_oc_rank4_coin_name != list240_oc[3].coin_kor_name){
								this.min240_oc_rank4_coin = list240_oc[3].coin_ticker;
								this.min240_oc_rank4_coin_name = list240_oc[3].coin_kor_name;

								if(this.min240_oc_rank4_coin_name != ''){
									if(list240_oc[3].gubun == '상승'){
										this.min240_oc_rank4_border_color = 'red';
									}else if(list240_oc[3].gubun == '하락'){
										this.min240_oc_rank4_border_color = 'blue';
									}else if(list240_oc[3].gubun == '보합'){
										this.min240_oc_rank4_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_oc_rank4_border_color = 'white'
									, 500);
								}
							}
							this.min240_oc_rank4_gubun = list240_oc[3].gubun;
							this.min240_oc_rank4_open = list240_oc[3].o_price;
							this.min240_oc_rank4_low = list240_oc[3].l_price;
							this.min240_oc_rank4_high = list240_oc[3].h_price;
							this.min240_oc_rank4_close = '';
							this.min240_oc_rank4_o_c_rate = list240_oc[3].o_c_rate + '%';
							this.min240_oc_rank4_o_c_subtract = '';
							this.min240_oc_rank4_price_volume = Number(list240_oc[3].price_volume).toLocaleString('ko-KR');
							
							if(Number(list240_oc[3].c_price) > 999){
								this.min240_oc_rank4_close = Number(list240_oc[3].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank4_close = list240_oc[3].c_price
							}

							if(Math.abs(list240_oc[3].o_c_subtract) > 999){
								this.min240_oc_rank4_o_c_subtract = Number(list240_oc[3].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank4_o_c_subtract = list240_oc[3].o_c_subtract
							}

							if(this.min240_oc_rank5_coin_name != list240_oc[4].coin_kor_name){
								this.min240_oc_rank5_coin = list240_oc[4].coin_ticker;
								this.min240_oc_rank5_coin_name = list240_oc[4].coin_kor_name;

								if(this.min240_oc_rank5_coin_name != ''){
									if(list240_oc[4].gubun == '상승'){
										this.min240_oc_rank5_border_color = 'red';
									}else if(list240_oc[4].gubun == '하락'){
										this.min240_oc_rank5_border_color = 'blue';
									}else if(list240_oc[4].gubun == '보합'){
										this.min240_oc_rank5_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_oc_rank5_border_color = 'white'
									, 500);
								}
							}
							this.min240_oc_rank5_gubun = list240_oc[4].gubun;
							this.min240_oc_rank5_open = list240_oc[4].o_price;
							this.min240_oc_rank5_low = list240_oc[4].l_price;
							this.min240_oc_rank5_high = list240_oc[4].h_price;
							this.min240_oc_rank5_close = '';
							this.min240_oc_rank5_o_c_rate = list240_oc[4].o_c_rate + '%';
							this.min240_oc_rank5_o_c_subtract = '';
							this.min240_oc_rank5_price_volume = Number(list240_oc[4].price_volume).toLocaleString('ko-KR');
							
							if(Number(list240_oc[4].c_price) > 999){
								this.min240_oc_rank5_close = Number(list240_oc[4].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank5_close = list240_oc[4].c_price
							}

							if(Math.abs(list240_oc[4].o_c_subtract) > 999){
								this.min240_oc_rank5_o_c_subtract = Number(list240_oc[4].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank5_o_c_subtract = list240_oc[4].o_c_subtract
							}

							if(this.min240_oc_rank6_coin_name != list240_oc[5].coin_kor_name){
								this.min240_oc_rank6_coin = list240_oc[5].coin_ticker;
								this.min240_oc_rank6_coin_name = list240_oc[5].coin_kor_name;

								if(this.min240_oc_rank6_coin_name != ''){
									if(list240_oc[5].gubun == '상승'){
										this.min240_oc_rank6_border_color = 'red';
									}else if(list240_oc[5].gubun == '하락'){
										this.min240_oc_rank6_border_color = 'blue';
									}else if(list240_oc[5].gubun == '보합'){
										this.min240_oc_rank6_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_oc_rank6_border_color = 'white'
									, 500);
								}
							}
							this.min240_oc_rank6_gubun = list240_oc[5].gubun;
							this.min240_oc_rank6_open = list240_oc[5].o_price;
							this.min240_oc_rank6_low = list240_oc[5].l_price;
							this.min240_oc_rank6_high = list240_oc[5].h_price;
							this.min240_oc_rank6_close = '';
							this.min240_oc_rank6_o_c_rate = list240_oc[5].o_c_rate + '%';
							this.min240_oc_rank6_o_c_subtract = '';
							this.min240_oc_rank6_price_volume = Number(list240_oc[5].price_volume).toLocaleString('ko-KR');
							
							if(Number(list240_oc[5].c_price) > 999){
								this.min240_oc_rank6_close = Number(list240_oc[5].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank6_close = list240_oc[5].c_price
							}

							if(Math.abs(list240_oc[5].o_c_subtract) > 999){
								this.min240_oc_rank6_o_c_subtract = Number(list240_oc[5].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank6_o_c_subtract = list240_oc[5].o_c_subtract
							}

							if(this.min240_oc_rank7_coin_name != list240_oc[6].coin_kor_name){
								this.min240_oc_rank7_coin = list240_oc[6].coin_ticker;
								this.min240_oc_rank7_coin_name = list240_oc[6].coin_kor_name;

								if(this.min240_oc_rank7_coin_name != ''){
									if(list240_oc[6].gubun == '상승'){
										this.min240_oc_rank7_border_color = 'red';
									}else if(list240_oc[6].gubun == '하락'){
										this.min240_oc_rank7_border_color = 'blue';
									}else if(list240_oc[6].gubun == '보합'){
										this.min240_oc_rank7_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_oc_rank7_border_color = 'white'
									, 500);
								}
							}
							this.min240_oc_rank7_gubun = list240_oc[6].gubun;
							this.min240_oc_rank7_open = list240_oc[6].o_price;
							this.min240_oc_rank7_low = list240_oc[6].l_price;
							this.min240_oc_rank7_high = list240_oc[6].h_price;
							this.min240_oc_rank7_close = '';
							this.min240_oc_rank7_o_c_rate = list240_oc[6].o_c_rate + '%';
							this.min240_oc_rank7_o_c_subtract = '';
							this.min240_oc_rank7_price_volume = Number(list240_oc[6].price_volume).toLocaleString('ko-KR');
							
							if(Number(list240_oc[6].c_price) > 999){
								this.min240_oc_rank7_close = Number(list240_oc[6].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank7_close = list240_oc[6].c_price
							}

							if(Math.abs(list240_oc[6].o_c_subtract) > 999){
								this.min240_oc_rank7_o_c_subtract = Number(list240_oc[6].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank7_o_c_subtract = list240_oc[6].o_c_subtract
							}

							if(this.min240_oc_rank8_coin_name != list240_oc[7].coin_kor_name){
								this.min240_oc_rank8_coin = list240_oc[7].coin_ticker;
								this.min240_oc_rank8_coin_name = list240_oc[7].coin_kor_name;

								if(this.min240_oc_rank8_coin_name != ''){
									if(list240_oc[7].gubun == '상승'){
										this.min240_oc_rank8_border_color = 'red';
									}else if(list240_oc[7].gubun == '하락'){
										this.min240_oc_rank8_border_color = 'blue';
									}else if(list240_oc[7].gubun == '보합'){
										this.min240_oc_rank8_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_oc_rank8_border_color = 'white'
									, 500);
								}
							}
							this.min240_oc_rank8_gubun = list240_oc[7].gubun;
							this.min240_oc_rank8_open = list240_oc[7].o_price;
							this.min240_oc_rank8_low = list240_oc[7].l_price;
							this.min240_oc_rank8_high = list240_oc[7].h_price;
							this.min240_oc_rank8_close = '';
							this.min240_oc_rank8_o_c_rate = list240_oc[7].o_c_rate + '%';
							this.min240_oc_rank8_o_c_subtract = '';
							this.min240_oc_rank8_price_volume = Number(list240_oc[7].price_volume).toLocaleString('ko-KR');
							
							if(Number(list240_oc[7].c_price) > 999){
								this.min240_oc_rank8_close = Number(list240_oc[7].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank8_close = list240_oc[7].c_price
							}

							if(Math.abs(list240_oc[7].o_c_subtract) > 999){
								this.min240_oc_rank8_o_c_subtract = Number(list240_oc[7].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank8_o_c_subtract = list240_oc[7].o_c_subtract
							}

							if(this.min240_oc_rank9_coin_name != list240_oc[8].coin_kor_name){
								this.min240_oc_rank9_coin = list240_oc[8].coin_ticker;
								this.min240_oc_rank9_coin_name = list240_oc[8].coin_kor_name;

								if(this.min240_oc_rank9_coin_name != ''){
									if(list240_oc[8].gubun == '상승'){
										this.min240_oc_rank9_border_color = 'red';
									}else if(list240_oc[8].gubun == '하락'){
										this.min240_oc_rank9_border_color = 'blue';
									}else if(list240_oc[8].gubun == '보합'){
										this.min240_oc_rank9_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_oc_rank9_border_color = 'white'
									, 500);
								}
							}
							this.min240_oc_rank9_gubun = list240_oc[8].gubun;
							this.min240_oc_rank9_open = list240_oc[8].o_price;
							this.min240_oc_rank9_low = list240_oc[8].l_price;
							this.min240_oc_rank9_high = list240_oc[8].h_price;
							this.min240_oc_rank9_close = '';
							this.min240_oc_rank9_o_c_rate = list240_oc[8].o_c_rate + '%';
							this.min240_oc_rank9_o_c_subtract = '';
							this.min240_oc_rank9_price_volume = Number(list240_oc[8].price_volume).toLocaleString('ko-KR');
							
							if(Number(list240_oc[8].c_price) > 999){
								this.min240_oc_rank9_close = Number(list240_oc[8].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank9_close = list240_oc[8].c_price
							}

							if(Math.abs(list240_oc[8].o_c_subtract) > 999){
								this.min240_oc_rank9_o_c_subtract = Number(list240_oc[8].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank9_o_c_subtract = list240_oc[8].o_c_subtract
							}

							if(this.min240_oc_rank10_coin_name != list240_oc[9].coin_kor_name){
								this.min240_oc_rank10_coin = list240_oc[9].coin_ticker;
								this.min240_oc_rank10_coin_name = list240_oc[9].coin_kor_name;

								if(this.min240_oc_rank10_coin_name != ''){
									if(list240_oc[9].gubun == '상승'){
										this.min240_oc_rank10_border_color = 'red';
									}else if(list240_oc[9].gubun == '하락'){
										this.min240_oc_rank10_border_color = 'blue';
									}else if(list240_oc[9].gubun == '보합'){
										this.min240_oc_rank10_border_color = 'black';
									}

									setTimeout(() => 
										this.min240_oc_rank10_border_color = 'white'
									, 500);
								}
							}
							this.min240_oc_rank10_gubun = list240_oc[9].gubun;
							this.min240_oc_rank10_open = list240_oc[9].o_price;
							this.min240_oc_rank10_low = list240_oc[9].l_price;
							this.min240_oc_rank10_high = list240_oc[9].h_price;
							this.min240_oc_rank10_close = '';
							this.min240_oc_rank10_o_c_rate = list240_oc[9].o_c_rate + '%';
							this.min240_oc_rank10_o_c_subtract = '';
							this.min240_oc_rank10_price_volume = Number(list240_oc[9].price_volume).toLocaleString('ko-KR');
							
							if(Number(list240_oc[9].c_price) > 999){
								this.min240_oc_rank10_close = Number(list240_oc[9].c_price).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank10_close = list240_oc[9].c_price
							}

							if(Math.abs(list240_oc[9].o_c_subtract) > 999){
								this.min240_oc_rank10_o_c_subtract = Number(list240_oc[9].o_c_subtract).toLocaleString('ko-KR')
							}else{
								this.min240_oc_rank10_o_c_subtract = list240_oc[9].o_c_subtract
							}

							console.log('종료 ' + (new Date()))
						}

						if(list240_volume.length == tmpBithumbList.length){
							var today = new Date();
							var year = today.getFullYear();
							var month = ('0' + (today.getMonth() + 1)).slice(-2);
							var day = ('0' + today.getDate()).slice(-2);
							var hours = ('0' + today.getHours()).slice(-2); 
							var minutes = ('0' + today.getMinutes()).slice(-2);
							var seconds = ('0' + today.getSeconds()).slice(-2); 
							
							this.min240_gijun_datetime_kst = year + '-' + month  + '-' + day + ' ' + hours + ':' + minutes  + ':' + seconds;
							this.min240_gijun_datetime_kst = (this.min240_gijun_datetime_kst).replace('20','')

							this.RequestCompleteYN = 'Y'
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
				//this.Data_MIN_240_Make();
			}

			if(this.RequestCompleteYN == 'Y' && (Number(ss) == 0 || Number(ss) == 10 || Number(ss) == 20 || Number(ss) == 30 || Number(ss) == 40 || Number(ss) == 50)){
				this.Data_MIN_240_Make();
			}

			if(this.min240_gijun_datetime_kst != ''){
				var min240_mi = (this.min240_gijun_datetime_kst).substr(12, 2)
				if((Number(min240_mi) == 0 && Number(mi) == 2) || (Number(min240_mi) == 0 && Number(mi) == 2)){
					this.Data_MIN_240_Make();
				}else if((Number(min240_mi) < Number(mi)-1) || (Number(min240_mi) > Number(mi)+1) || (Number(min240_mi) < Number(mi)-1) || (Number(min240_mi) > Number(mi)+1)){
					this.Data_MIN_240_Make();
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