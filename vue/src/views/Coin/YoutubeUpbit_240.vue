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
						업비트 4시간 거래대금 [{{ min240_api_datetime_kst }}]
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
						업비트 4시간 변동율 [{{ min240_api_datetime_kst }}]
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
import axios from 'axios';

export default {
	data () {
      	return {
			username: '',
			password: '',
			dialog: false,
			notice_msg: '',
			/* min240 */
			min240_search_yn: 'Y',
			min240_api_datetime_kst : '',
			min240_gijun_datetime_kst : '',
			list240_volume: [],
			list240_oc: [],

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
		this.Data_MIN240_Make();
		this.CurrentDataTime();
		setInterval(this.CurrentDataTime.bind(this) , 1000);
	},
	methods: {
		Data_MIN240_Make(){
			var mi = new Date().getMinutes() < 10? "0" + new Date().getMinutes(): new Date().getMinutes();
			this.min240_search_yn = 'N'
			
			axios.get('/YoutubeUpbit/MIN240')
			.then(response => {
				if(response.status == 200){
					this.list240_volume = response.data;
					this.list240_oc = response.data;
					
					this.min240_api_datetime_kst = (response.data[0].API_DATETIME_KST).replace('20','');
					this.min240_gijun_datetime_kst = (response.data[0].CURRENT_DATETIME_KST).replace('20','');
					
					if(this.min240_volume_rank1_coin_name != this.list240_volume[0].Coin_Kor_Name){
						if(!(Number(new Date().getMinutes())%5==0 && Number(new Date().getSeconds()) < 5)){
							this.min240_volume_rank1_coin = this.list240_volume[0].API_Coin_Ticker;
							this.min240_volume_rank1_coin_name = this.list240_volume[0].Coin_Kor_Name;

							if(this.min240_volume_rank1_coin_name != ''){
								if(this.list240_volume[0].gubun == '상승'){
									this.min240_volume_rank1_border_color = 'red';
								}else if(this.list240_volume[0].gubun == '하락'){
									this.min240_volume_rank1_border_color = 'blue';
								}else if(this.list240_volume[0].gubun == '보합'){
									this.min240_volume_rank1_border_color = 'black';
								}

								setTimeout(() => 
									this.min240_volume_rank1_border_color = 'white'
								, 500);
							}
						}
					}
					this.min240_volume_rank1_gubun = this.list240_volume[0].gubun;
					this.min240_volume_rank1_open = this.list240_volume[0].o_price;
					this.min240_volume_rank1_low = this.list240_volume[0].l_price;
					this.min240_volume_rank1_high = this.list240_volume[0].h_price;
					this.min240_volume_rank1_close = this.list240_volume[0].format_c_price;
					this.min240_volume_rank1_o_c_rate = this.list240_volume[0].o_c_rate;
					this.min240_volume_rank1_o_c_subtract = this.list240_volume[0].o_c_subtract;
					this.min240_volume_rank1_price_volume = this.list240_volume[0].format_volume_price;

					if(this.min240_volume_rank2_coin_name != this.list240_volume[1].Coin_Kor_Name){
						this.min240_volume_rank2_coin = this.list240_volume[1].API_Coin_Ticker;
						this.min240_volume_rank2_coin_name = this.list240_volume[1].Coin_Kor_Name;

						if(this.min240_volume_rank2_coin_name != ''){
							if(this.list240_volume[1].gubun == '상승'){
								this.min240_volume_rank2_border_color = 'red';
							}else if(this.list240_volume[1].gubun == '하락'){
								this.min240_volume_rank2_border_color = 'blue';
							}else if(this.list240_volume[1].gubun == '보합'){
								this.min240_volume_rank2_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_volume_rank2_border_color = 'white'
							, 500);
						}
					}
					this.min240_volume_rank2_gubun = this.list240_volume[1].gubun;
					this.min240_volume_rank2_open = this.list240_volume[1].o_price;
					this.min240_volume_rank2_low = this.list240_volume[1].l_price;
					this.min240_volume_rank2_high = this.list240_volume[1].h_price;
					this.min240_volume_rank2_close = this.list240_volume[1].format_c_price;
					this.min240_volume_rank2_o_c_rate = this.list240_volume[1].o_c_rate;
					this.min240_volume_rank2_o_c_subtract = this.list240_volume[1].o_c_subtract;
					this.min240_volume_rank2_price_volume = this.list240_volume[1].format_volume_price;

					if(this.min240_volume_rank3_coin_name != this.list240_volume[2].Coin_Kor_Name){
						this.min240_volume_rank3_coin = this.list240_volume[2].API_Coin_Ticker;
						this.min240_volume_rank3_coin_name = this.list240_volume[2].Coin_Kor_Name;

						if(this.min240_volume_rank3_coin_name != ''){
							if(this.list240_volume[2].gubun == '상승'){
								this.min240_volume_rank3_border_color = 'red';
							}else if(this.list240_volume[2].gubun == '하락'){
								this.min240_volume_rank3_border_color = 'blue';
							}else if(this.list240_volume[2].gubun == '보합'){
								this.min240_volume_rank3_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_volume_rank3_border_color = 'white'
							, 500);
						}
					}
					this.min240_volume_rank3_gubun = this.list240_volume[2].gubun;
					this.min240_volume_rank3_open = this.list240_volume[2].o_price;
					this.min240_volume_rank3_low = this.list240_volume[2].l_price;
					this.min240_volume_rank3_high = this.list240_volume[2].h_price;
					this.min240_volume_rank3_close = this.list240_volume[2].format_c_price;
					this.min240_volume_rank3_o_c_rate = this.list240_volume[2].o_c_rate;
					this.min240_volume_rank3_o_c_subtract = this.list240_volume[2].o_c_subtract;
					this.min240_volume_rank3_price_volume = this.list240_volume[2].format_volume_price;
					
					if(this.min240_volume_rank4_coin_name != this.list240_volume[3].Coin_Kor_Name){
						this.min240_volume_rank4_coin = this.list240_volume[3].API_Coin_Ticker;
						this.min240_volume_rank4_coin_name = this.list240_volume[3].Coin_Kor_Name;

						if(this.min240_volume_rank4_coin_name != ''){
							if(this.list240_volume[3].gubun == '상승'){
								this.min240_volume_rank4_border_color = 'red';
							}else if(this.list240_volume[3].gubun == '하락'){
								this.min240_volume_rank4_border_color = 'blue';
							}else if(this.list240_volume[3].gubun == '보합'){
								this.min240_volume_rank4_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_volume_rank4_border_color = 'white'
							, 500);
						}
					}
					this.min240_volume_rank4_gubun = this.list240_volume[3].gubun;
					this.min240_volume_rank4_open = this.list240_volume[3].o_price;
					this.min240_volume_rank4_low = this.list240_volume[3].l_price;
					this.min240_volume_rank4_high = this.list240_volume[3].h_price;
					this.min240_volume_rank4_close = this.list240_volume[3].format_c_price;
					this.min240_volume_rank4_o_c_rate = this.list240_volume[3].o_c_rate;
					this.min240_volume_rank4_o_c_subtract = this.list240_volume[3].o_c_subtract;
					this.min240_volume_rank4_price_volume = this.list240_volume[3].format_volume_price;
					
					if(this.min240_volume_rank5_coin_name != this.list240_volume[4].Coin_Kor_Name){
						this.min240_volume_rank5_coin = this.list240_volume[4].API_Coin_Ticker;
						this.min240_volume_rank5_coin_name = this.list240_volume[4].Coin_Kor_Name;

						if(this.min240_volume_rank5_coin_name != ''){
							if(this.list240_volume[4].gubun == '상승'){
								this.min240_volume_rank5_border_color = 'red';
							}else if(this.list240_volume[4].gubun == '하락'){
								this.min240_volume_rank5_border_color = 'blue';
							}else if(this.list240_volume[4].gubun == '보합'){
								this.min240_volume_rank5_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_volume_rank5_border_color = 'white'
							, 500);
						}
					}
					this.min240_volume_rank5_gubun = this.list240_volume[4].gubun;
					this.min240_volume_rank5_open = this.list240_volume[4].o_price;
					this.min240_volume_rank5_low = this.list240_volume[4].l_price;
					this.min240_volume_rank5_high = this.list240_volume[4].h_price;
					this.min240_volume_rank5_close = this.list240_volume[4].format_c_price;
					this.min240_volume_rank5_o_c_rate = this.list240_volume[4].o_c_rate;
					this.min240_volume_rank5_o_c_subtract = this.list240_volume[4].o_c_subtract;
					this.min240_volume_rank5_price_volume = this.list240_volume[4].format_volume_price;

					if(this.min240_volume_rank6_coin_name != this.list240_volume[5].Coin_Kor_Name){
						this.min240_volume_rank6_coin = this.list240_volume[5].API_Coin_Ticker;
						this.min240_volume_rank6_coin_name = this.list240_volume[5].Coin_Kor_Name;

						if(this.min240_volume_rank6_coin_name != ''){
							if(this.list240_volume[5].gubun == '상승'){
								this.min240_volume_rank6_border_color = 'red';
							}else if(this.list240_volume[5].gubun == '하락'){
								this.min240_volume_rank6_border_color = 'blue';
							}else if(this.list240_volume[5].gubun == '보합'){
								this.min240_volume_rank6_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_volume_rank6_border_color = 'white'
							, 500);
						}
					}
					this.min240_volume_rank6_gubun = this.list240_volume[5].gubun;
					this.min240_volume_rank6_open = this.list240_volume[5].o_price;
					this.min240_volume_rank6_low = this.list240_volume[5].l_price;
					this.min240_volume_rank6_high = this.list240_volume[5].h_price;
					this.min240_volume_rank6_close = this.list240_volume[5].format_c_price;
					this.min240_volume_rank6_o_c_rate = this.list240_volume[5].o_c_rate;
					this.min240_volume_rank6_o_c_subtract = this.list240_volume[5].o_c_subtract;
					this.min240_volume_rank6_price_volume = this.list240_volume[5].format_volume_price;
					
					if(this.min240_volume_rank7_coin_name != this.list240_volume[6].Coin_Kor_Name){
						this.min240_volume_rank7_coin = this.list240_volume[6].API_Coin_Ticker;
						this.min240_volume_rank7_coin_name = this.list240_volume[6].Coin_Kor_Name;

						if(this.min240_volume_rank7_coin_name != ''){
							if(this.list240_volume[6].gubun == '상승'){
								this.min240_volume_rank7_border_color = 'red';
							}else if(this.list240_volume[6].gubun == '하락'){
								this.min240_volume_rank7_border_color = 'blue';
							}else if(this.list240_volume[6].gubun == '보합'){
								this.min240_volume_rank7_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_volume_rank7_border_color = 'white'
							, 500);
						}
					}
					this.min240_volume_rank7_gubun = this.list240_volume[6].gubun;
					this.min240_volume_rank7_open = this.list240_volume[6].o_price;
					this.min240_volume_rank7_low = this.list240_volume[6].l_price;
					this.min240_volume_rank7_high = this.list240_volume[6].h_price;
					this.min240_volume_rank7_close = this.list240_volume[6].format_c_price;
					this.min240_volume_rank7_o_c_rate = this.list240_volume[6].o_c_rate;
					this.min240_volume_rank7_o_c_subtract = this.list240_volume[6].o_c_subtract;
					this.min240_volume_rank7_price_volume = this.list240_volume[6].format_volume_price;

					if(this.min240_volume_rank8_coin_name != this.list240_volume[7].Coin_Kor_Name){
						this.min240_volume_rank8_coin = this.list240_volume[7].API_Coin_Ticker;
						this.min240_volume_rank8_coin_name = this.list240_volume[7].Coin_Kor_Name;

						if(this.min240_volume_rank8_coin_name != ''){
							if(this.list240_volume[7].gubun == '상승'){
								this.min240_volume_rank8_border_color = 'red';
							}else if(this.list240_volume[7].gubun == '하락'){
								this.min240_volume_rank8_border_color = 'blue';
							}else if(this.list240_volume[7].gubun == '보합'){
								this.min240_volume_rank8_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_volume_rank8_border_color = 'white'
							, 500);
						}
					}
					this.min240_volume_rank8_gubun = this.list240_volume[7].gubun;
					this.min240_volume_rank8_open = this.list240_volume[7].o_price;
					this.min240_volume_rank8_low = this.list240_volume[7].l_price;
					this.min240_volume_rank8_high = this.list240_volume[7].h_price;
					this.min240_volume_rank8_close = this.list240_volume[7].format_c_price;
					this.min240_volume_rank8_o_c_rate = this.list240_volume[7].o_c_rate;
					this.min240_volume_rank8_o_c_subtract = this.list240_volume[7].o_c_subtract;
					this.min240_volume_rank8_price_volume = this.list240_volume[7].format_volume_price;

					if(this.min240_volume_rank9_coin_name != this.list240_volume[8].Coin_Kor_Name){
						this.min240_volume_rank9_coin = this.list240_volume[8].API_Coin_Ticker;
						this.min240_volume_rank9_coin_name = this.list240_volume[8].Coin_Kor_Name;

						if(this.min240_volume_rank9_coin_name != ''){
							if(this.list240_volume[8].gubun == '상승'){
								this.min240_volume_rank9_border_color = 'red';
							}else if(this.list240_volume[8].gubun == '하락'){
								this.min240_volume_rank9_border_color = 'blue';
							}else if(this.list240_volume[8].gubun == '보합'){
								this.min240_volume_rank9_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_volume_rank9_border_color = 'white'
							, 500);
						}
					}
					this.min240_volume_rank9_gubun = this.list240_volume[8].gubun;
					this.min240_volume_rank9_open = this.list240_volume[8].o_price;
					this.min240_volume_rank9_low = this.list240_volume[8].l_price;
					this.min240_volume_rank9_high = this.list240_volume[8].h_price;
					this.min240_volume_rank9_close = this.list240_volume[8].format_c_price;
					this.min240_volume_rank9_o_c_rate = this.list240_volume[8].o_c_rate;
					this.min240_volume_rank9_o_c_subtract = this.list240_volume[8].o_c_subtract;
					this.min240_volume_rank9_price_volume = this.list240_volume[8].format_volume_price;
					
					if(this.min240_volume_rank10_coin_name != this.list240_volume[9].Coin_Kor_Name){
						this.min240_volume_rank10_coin = this.list240_volume[9].API_Coin_Ticker;
						this.min240_volume_rank10_coin_name = this.list240_volume[9].Coin_Kor_Name;

						if(this.min240_volume_rank10_coin_name != ''){
							if(this.list240_volume[9].gubun == '상승'){
								this.min240_volume_rank10_border_color = 'red';
							}else if(this.list240_volume[9].gubun == '하락'){
								this.min240_volume_rank10_border_color = 'blue';
							}else if(this.list240_volume[9].gubun == '보합'){
								this.min240_volume_rank10_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_volume_rank10_border_color = 'white'
							, 500);
						}
					}
					this.min240_volume_rank10_gubun = this.list240_volume[9].gubun;
					this.min240_volume_rank10_open = this.list240_volume[9].o_price;
					this.min240_volume_rank10_low = this.list240_volume[9].l_price;
					this.min240_volume_rank10_high = this.list240_volume[9].h_price;
					this.min240_volume_rank10_close = this.list240_volume[9].format_c_price;
					this.min240_volume_rank10_o_c_rate = this.list240_volume[9].o_c_rate;
					this.min240_volume_rank10_o_c_subtract = this.list240_volume[9].o_c_subtract;
					this.min240_volume_rank10_price_volume = this.list240_volume[9].format_volume_price;

					this.list240_oc = this.list240_oc.sort((a,b) => Number(String(b.o_c_rate).replace('%','')) - Number(String(a.o_c_rate).replace('%','')));

					if(this.min240_oc_rank1_coin_name != this.list240_oc[0].Coin_Kor_Name){
						if(!(Number(new Date().getMinutes())%5==0 && Number(new Date().getSeconds()) < 5)){
							this.min240_oc_rank1_coin = this.list240_oc[0].API_Coin_Ticker;
							this.min240_oc_rank1_coin_name = this.list240_oc[0].Coin_Kor_Name;

							if(this.min240_oc_rank1_coin_name != ''){
								if(this.list240_oc[0].gubun == '상승'){
									this.min240_oc_rank1_border_color = 'red';
								}else if(this.list240_oc[0].gubun == '하락'){
									this.min240_oc_rank1_border_color = 'blue';
								}else if(this.list240_oc[0].gubun == '보합'){
									this.min240_oc_rank1_border_color = 'black';
								}

								setTimeout(() => 
									this.min240_oc_rank1_border_color = 'white'
								, 500);
							}
						}
					}
					this.min240_oc_rank1_gubun = this.list240_oc[0].gubun;
					this.min240_oc_rank1_open = this.list240_oc[0].o_price;
					this.min240_oc_rank1_low = this.list240_oc[0].l_price;
					this.min240_oc_rank1_high = this.list240_oc[0].h_price;
					this.min240_oc_rank1_close = this.list240_oc[0].format_c_price;
					this.min240_oc_rank1_o_c_rate = this.list240_oc[0].o_c_rate;
					this.min240_oc_rank1_o_c_subtract = this.list240_oc[0].o_c_subtract;
					this.min240_oc_rank1_price_volume = this.list240_oc[0].format_volume_price;

					if(this.min240_oc_rank2_coin_name != this.list240_oc[1].Coin_Kor_Name){
						this.min240_oc_rank2_coin = this.list240_oc[1].API_Coin_Ticker;
						this.min240_oc_rank2_coin_name = this.list240_oc[1].Coin_Kor_Name;

						if(this.min240_oc_rank2_coin_name != ''){
							if(this.list240_oc[1].gubun == '상승'){
								this.min240_oc_rank2_border_color = 'red';
							}else if(this.list240_oc[1].gubun == '하락'){
								this.min240_oc_rank2_border_color = 'blue';
							}else if(this.list240_oc[1].gubun == '보합'){
								this.min240_oc_rank2_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_oc_rank2_border_color = 'white'
							, 500);
						}
					}
					this.min240_oc_rank2_gubun = this.list240_oc[1].gubun;
					this.min240_oc_rank2_open = this.list240_oc[1].o_price;
					this.min240_oc_rank2_low = this.list240_oc[1].l_price;
					this.min240_oc_rank2_high = this.list240_oc[1].h_price;
					this.min240_oc_rank2_close = this.list240_oc[1].format_c_price;
					this.min240_oc_rank2_o_c_rate = this.list240_oc[1].o_c_rate;
					this.min240_oc_rank2_o_c_subtract = this.list240_oc[1].o_c_subtract;
					this.min240_oc_rank2_price_volume = this.list240_oc[1].format_volume_price;

					if(this.min240_oc_rank3_coin_name != this.list240_oc[2].Coin_Kor_Name){
						this.min240_oc_rank3_coin = this.list240_oc[2].API_Coin_Ticker;
						this.min240_oc_rank3_coin_name = this.list240_oc[2].Coin_Kor_Name;

						if(this.min240_oc_rank3_coin_name != ''){
							if(this.list240_oc[2].gubun == '상승'){
								this.min240_oc_rank3_border_color = 'red';
							}else if(this.list240_oc[2].gubun == '하락'){
								this.min240_oc_rank3_border_color = 'blue';
							}else if(this.list240_oc[2].gubun == '보합'){
								this.min240_oc_rank3_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_oc_rank3_border_color = 'white'
							, 500);
						}
					}
					this.min240_oc_rank3_gubun = this.list240_oc[2].gubun;
					this.min240_oc_rank3_open = this.list240_oc[2].o_price;
					this.min240_oc_rank3_low = this.list240_oc[2].l_price;
					this.min240_oc_rank3_high = this.list240_oc[2].h_price;
					this.min240_oc_rank3_close = this.list240_oc[2].format_c_price;
					this.min240_oc_rank3_o_c_rate = this.list240_oc[2].o_c_rate;
					this.min240_oc_rank3_o_c_subtract = this.list240_oc[2].o_c_subtract;
					this.min240_oc_rank3_price_volume = this.list240_oc[2].format_volume_price;
					
					if(this.min240_oc_rank4_coin_name != this.list240_oc[3].Coin_Kor_Name){
						this.min240_oc_rank4_coin = this.list240_oc[3].API_Coin_Ticker;
						this.min240_oc_rank4_coin_name = this.list240_oc[3].Coin_Kor_Name;

						if(this.min240_oc_rank4_coin_name != ''){
							if(this.list240_oc[3].gubun == '상승'){
								this.min240_oc_rank4_border_color = 'red';
							}else if(this.list240_oc[3].gubun == '하락'){
								this.min240_oc_rank4_border_color = 'blue';
							}else if(this.list240_oc[3].gubun == '보합'){
								this.min240_oc_rank4_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_oc_rank4_border_color = 'white'
							, 500);
						}
					}
					this.min240_oc_rank4_gubun = this.list240_oc[3].gubun;
					this.min240_oc_rank4_open = this.list240_oc[3].o_price;
					this.min240_oc_rank4_low = this.list240_oc[3].l_price;
					this.min240_oc_rank4_high = this.list240_oc[3].h_price;
					this.min240_oc_rank4_close = this.list240_oc[3].format_c_price;
					this.min240_oc_rank4_o_c_rate = this.list240_oc[3].o_c_rate;
					this.min240_oc_rank4_o_c_subtract = this.list240_oc[3].o_c_subtract;
					this.min240_oc_rank4_price_volume = this.list240_oc[3].format_volume_price;
					
					if(this.min240_oc_rank5_coin_name != this.list240_oc[4].Coin_Kor_Name){
						this.min240_oc_rank5_coin = this.list240_oc[4].API_Coin_Ticker;
						this.min240_oc_rank5_coin_name = this.list240_oc[4].Coin_Kor_Name;

						if(this.min240_oc_rank5_coin_name != ''){
							if(this.list240_oc[4].gubun == '상승'){
								this.min240_oc_rank5_border_color = 'red';
							}else if(this.list240_oc[4].gubun == '하락'){
								this.min240_oc_rank5_border_color = 'blue';
							}else if(this.list240_oc[4].gubun == '보합'){
								this.min240_oc_rank5_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_oc_rank5_border_color = 'white'
							, 500);
						}
					}
					this.min240_oc_rank5_gubun = this.list240_oc[4].gubun;
					this.min240_oc_rank5_open = this.list240_oc[4].o_price;
					this.min240_oc_rank5_low = this.list240_oc[4].l_price;
					this.min240_oc_rank5_high = this.list240_oc[4].h_price;
					this.min240_oc_rank5_close = this.list240_oc[4].format_c_price;
					this.min240_oc_rank5_o_c_rate = this.list240_oc[4].o_c_rate;
					this.min240_oc_rank5_o_c_subtract = this.list240_oc[4].o_c_subtract;
					this.min240_oc_rank5_price_volume = this.list240_oc[4].format_volume_price;

					if(this.min240_oc_rank6_coin_name != this.list240_oc[5].Coin_Kor_Name){
						this.min240_oc_rank6_coin = this.list240_oc[5].API_Coin_Ticker;
						this.min240_oc_rank6_coin_name = this.list240_oc[5].Coin_Kor_Name;

						if(this.min240_oc_rank6_coin_name != ''){
							if(this.list240_oc[5].gubun == '상승'){
								this.min240_oc_rank6_border_color = 'red';
							}else if(this.list240_oc[5].gubun == '하락'){
								this.min240_oc_rank6_border_color = 'blue';
							}else if(this.list240_oc[5].gubun == '보합'){
								this.min240_oc_rank6_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_oc_rank6_border_color = 'white'
							, 500);
						}
					}
					this.min240_oc_rank6_gubun = this.list240_oc[5].gubun;
					this.min240_oc_rank6_open = this.list240_oc[5].o_price;
					this.min240_oc_rank6_low = this.list240_oc[5].l_price;
					this.min240_oc_rank6_high = this.list240_oc[5].h_price;
					this.min240_oc_rank6_close = this.list240_oc[5].format_c_price;
					this.min240_oc_rank6_o_c_rate = this.list240_oc[5].o_c_rate;
					this.min240_oc_rank6_o_c_subtract = this.list240_oc[5].o_c_subtract;
					this.min240_oc_rank6_price_volume = this.list240_oc[5].format_volume_price;
					
					if(this.min240_oc_rank7_coin_name != this.list240_oc[6].Coin_Kor_Name){
						this.min240_oc_rank7_coin = this.list240_oc[6].API_Coin_Ticker;
						this.min240_oc_rank7_coin_name = this.list240_oc[6].Coin_Kor_Name;

						if(this.min240_oc_rank7_coin_name != ''){
							if(this.list240_oc[6].gubun == '상승'){
								this.min240_oc_rank7_border_color = 'red';
							}else if(this.list240_oc[6].gubun == '하락'){
								this.min240_oc_rank7_border_color = 'blue';
							}else if(this.list240_oc[6].gubun == '보합'){
								this.min240_oc_rank7_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_oc_rank7_border_color = 'white'
							, 500);
						}
					}
					this.min240_oc_rank7_gubun = this.list240_oc[6].gubun;
					this.min240_oc_rank7_open = this.list240_oc[6].o_price;
					this.min240_oc_rank7_low = this.list240_oc[6].l_price;
					this.min240_oc_rank7_high = this.list240_oc[6].h_price;
					this.min240_oc_rank7_close = this.list240_oc[6].format_c_price;
					this.min240_oc_rank7_o_c_rate = this.list240_oc[6].o_c_rate;
					this.min240_oc_rank7_o_c_subtract = this.list240_oc[6].o_c_subtract;
					this.min240_oc_rank7_price_volume = this.list240_oc[6].format_volume_price;

					if(this.min240_oc_rank8_coin_name != this.list240_oc[7].Coin_Kor_Name){
						this.min240_oc_rank8_coin = this.list240_oc[7].API_Coin_Ticker;
						this.min240_oc_rank8_coin_name = this.list240_oc[7].Coin_Kor_Name;

						if(this.min240_oc_rank8_coin_name != ''){
							if(this.list240_oc[7].gubun == '상승'){
								this.min240_oc_rank8_border_color = 'red';
							}else if(this.list240_oc[7].gubun == '하락'){
								this.min240_oc_rank8_border_color = 'blue';
							}else if(this.list240_oc[7].gubun == '보합'){
								this.min240_oc_rank8_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_oc_rank8_border_color = 'white'
							, 500);
						}
					}
					this.min240_oc_rank8_gubun = this.list240_oc[7].gubun;
					this.min240_oc_rank8_open = this.list240_oc[7].o_price;
					this.min240_oc_rank8_low = this.list240_oc[7].l_price;
					this.min240_oc_rank8_high = this.list240_oc[7].h_price;
					this.min240_oc_rank8_close = this.list240_oc[7].format_c_price;
					this.min240_oc_rank8_o_c_rate = this.list240_oc[7].o_c_rate;
					this.min240_oc_rank8_o_c_subtract = this.list240_oc[7].o_c_subtract;
					this.min240_oc_rank8_price_volume = this.list240_oc[7].format_volume_price;

					if(this.min240_oc_rank9_coin_name != this.list240_oc[8].Coin_Kor_Name){
						this.min240_oc_rank9_coin = this.list240_oc[8].API_Coin_Ticker;
						this.min240_oc_rank9_coin_name = this.list240_oc[8].Coin_Kor_Name;

						if(this.min240_oc_rank9_coin_name != ''){
							if(this.list240_oc[8].gubun == '상승'){
								this.min240_oc_rank9_border_color = 'red';
							}else if(this.list240_oc[8].gubun == '하락'){
								this.min240_oc_rank9_border_color = 'blue';
							}else if(this.list240_oc[8].gubun == '보합'){
								this.min240_oc_rank9_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_oc_rank9_border_color = 'white'
							, 500);
						}
					}
					this.min240_oc_rank9_gubun = this.list240_oc[8].gubun;
					this.min240_oc_rank9_open = this.list240_oc[8].o_price;
					this.min240_oc_rank9_low = this.list240_oc[8].l_price;
					this.min240_oc_rank9_high = this.list240_oc[8].h_price;
					this.min240_oc_rank9_close = this.list240_oc[8].format_c_price;
					this.min240_oc_rank9_o_c_rate = this.list240_oc[8].o_c_rate;
					this.min240_oc_rank9_o_c_subtract = this.list240_oc[8].o_c_subtract;
					this.min240_oc_rank9_price_volume = this.list240_oc[8].format_volume_price;
					
					if(this.min240_oc_rank10_coin_name != this.list240_oc[9].Coin_Kor_Name){
						this.min240_oc_rank10_coin = this.list240_oc[9].API_Coin_Ticker;
						this.min240_oc_rank10_coin_name = this.list240_oc[9].Coin_Kor_Name;

						if(this.min240_oc_rank10_coin_name != ''){
							if(this.list240_oc[9].gubun == '상승'){
								this.min240_oc_rank10_border_color = 'red';
							}else if(this.list240_oc[9].gubun == '하락'){
								this.min240_oc_rank10_border_color = 'blue';
							}else if(this.list240_oc[9].gubun == '보합'){
								this.min240_oc_rank10_border_color = 'black';
							}

							setTimeout(() => 
								this.min240_oc_rank10_border_color = 'white'
							, 500);
						}
					}
					this.min240_oc_rank10_gubun = this.list240_oc[9].gubun;
					this.min240_oc_rank10_open = this.list240_oc[9].o_price;
					this.min240_oc_rank10_low = this.list240_oc[9].l_price;
					this.min240_oc_rank10_high = this.list240_oc[9].h_price;
					this.min240_oc_rank10_close = this.list240_oc[9].format_c_price;
					this.min240_oc_rank10_o_c_rate = this.list240_oc[9].o_c_rate;
					this.min240_oc_rank10_o_c_subtract = this.list240_oc[9].o_c_subtract;
					this.min240_oc_rank10_price_volume = this.list240_oc[9].format_volume_price;
				}
				this.min240_search_yn = 'Y'
			})
		},
		CurrentDataTime() {
			var mi = new Date().getMinutes() < 10? "0" + new Date().getMinutes(): new Date().getMinutes();
			var ss = new Date().getSeconds() < 10? "0" + new Date().getSeconds(): new Date().getSeconds();
			
			var min240_mi = (this.min240_gijun_datetime_kst).substr(12, 2)
			
			if(this.min240_gijun_datetime_kst != ''){
				if(this.min240_search_yn == 'Y' && (Number(ss) == 0 || Number(ss) == 30)){
					this.Data_MIN240_Make();
				}
			}

			if(this.min240_gijun_datetime_kst != ''){
				if(Number(min240_mi) == 0 && Number(mi) == 2){
					this.Data_MIN240_Make();
				}else if((Number(min240_mi) < Number(mi)-1) || (Number(min240_mi) > Number(mi)+1)){
					this.Data_MIN240_Make();
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