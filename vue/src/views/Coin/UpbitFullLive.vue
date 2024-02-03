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
			<v-col xl="12" md="12" sm="12" style="height:1px; padding-top:0px;"></v-col>
		</v-row>
		<v-row>
			<v-col xl="6" md="6" sm="6" style="text-align:center; padding-bottom:0px; padding-right:40px; padding-left:25px;">
				<v-row>
					<!--
					<v-col xl="7" md="7" sm="7" style="padding-left:20px; text-align:left; font-size:12px;">
						거래대금은 거래량*현재가로 대략적으로 계산 되었습니다.
					</v-col>
					-->
					<v-col xl="7" md="7" sm="7" style="padding-left:20px; text-align:left; font-weight:bold; font-size:40px;">
						업비트 5분 [{{ min5_api_datetime_kst }}]
					</v-col>
					<v-col xl="5" md="5" sm="5" style="padding-right:0px; text-align:right; font-weight:bold; font-size:40px;">
						{{ min5_gijun_datetime_kst }}
					</v-col>
				</v-row>
				<v-row>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						코인
					</v-col>
					<v-col xl="2" md="2" sm="2" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						현재가
					</v-col>
					<v-col xl="2" md="2" sm="2" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						변동률(5분)
					</v-col>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						추정 거래대금(5분)
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min5_rank1_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank1_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.min5_rank1_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ min5_rank1_o_c_rate }}</td>
								</template>
								<template v-else-if="this.min5_rank1_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ min5_rank1_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank1_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min5_rank2_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank2_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min5_rank3_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank3_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min5_rank4_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank4_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min5_rank5_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min5_rank5_price_volume }}
					</v-col>
				</v-row>

			</v-col>
			<v-col xl="6" md="6" sm="6" style="text-align:center; padding-bottom:0px; padding-left:40px; padding-right:25px;">
				<v-row>
					<v-col xl="7" md="7" sm="7" style="padding-left:20px; text-align:left; font-weight:bold; font-size:40px;">
						업비트 15분 [{{ min15_api_datetime_kst }}]
					</v-col>
					<v-col xl="5" md="5" sm="5" style="padding-right:25px; text-align:right; font-weight:bold; font-size:40px;">
						{{ min15_gijun_datetime_kst }}
					</v-col>
				</v-row>
				<v-row>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						코인
					</v-col>
					<v-col xl="2" md="2" sm="2" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						현재가
					</v-col>
					<v-col xl="2" md="2" sm="2" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						변동률(15분)
					</v-col>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						추정 거래대금(15분)
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min15_rank1_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank1_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:36px;">
						{{ min15_rank2_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank2_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min15_rank3_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank3_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min15_rank4_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank3_close }}
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank4_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min15_rank5_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min15_rank5_price_volume }}
					</v-col>
				</v-row>

			</v-col>
		</v-row>
		<v-row style="height:190px; display:flex; align-items:center;">
			<template v-if="notice_msg != ''">
				<v-col xl="12" md="12" sm="12" style="font-size:50px; font-weight:bold; text-align:center;">
					{{ notice_msg }}
				</v-col>
			</template>
			<template v-else>
				<v-col xl="6" md="6" sm="6" style="text-align:center; padding: 0px;">
					<img src="@/assets/advertise/MID_AD.jpg" />
				</v-col>
				<v-col xl="6" md="6" sm="6" style="text-align:center; padding: 0px;">
					<img src="@/assets/advertise/MID_AD.jpg" />
				</v-col>
			</template>
		</v-row>
		<v-row>
			<v-col xl="6" md="6" sm="6" style="text-align:center; padding-top:0px; padding-right:40px; padding-left:25px;">
				<v-row>
					<v-col xl="7" md="7" sm="7" style="padding-left:20px; text-align:left; font-weight:bold; font-size:40px;">
						업비트 60분 [{{ min60_api_datetime_kst }}]
					</v-col>
					<v-col xl="5" md="5" sm="5" style="padding-right:0px; text-align:right; font-weight:bold; font-size:40px;">
						{{ min60_gijun_datetime_kst }}
					</v-col>
				</v-row>
				<v-row>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						코인
					</v-col>
					<v-col xl="2" md="2" sm="2" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						현재가
					</v-col>
					<v-col xl="2" md="2" sm="2" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						변동률(60분)
					</v-col>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						추정 거래대금(60분)
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min60_rank1_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank1_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min60_rank2_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank2_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min60_rank3_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank3_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min60_rank4_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank4_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min60_rank5_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ min60_rank5_price_volume }}
					</v-col>
				</v-row>

			</v-col>
			<v-col xl="6" md="6" sm="6" style="text-align:center; padding-top:0px; padding-left:40px; padding-right:25px;">
				
				<v-row>
					<v-col xl="7" md="7" sm="7" style="padding-left:0px; text-align:left; font-weight:bold; font-size:40px;">
						업비트 240분 [{{ min240_api_datetime_kst }}]
					</v-col>
					<v-col xl="5" md="5" sm="5" style="padding-right:25px; text-align:right; font-weight:bold; font-size:40px;">
						{{ min240_gijun_datetime_kst }}
					</v-col>
				</v-row>
				<v-row>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						코인
					</v-col>
					<v-col xl="2" md="2" sm="2" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						현재가
					</v-col>
					<v-col xl="2" md="2" sm="2" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						변동률(240분)
					</v-col>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 30px; font-weight:bold; padding: 0px; padding-top: 5px;">
						추정 거래대금(240분)
					</v-col>
				</v-row>
				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min240_rank1_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>

					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank1_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min240_rank2_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank2_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min240_rank3_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank3_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min240_rank4_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank4_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;">
						{{ min240_rank5_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ min240_rank5_price_volume }}
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
			/* MIN5 */
			min5_api_datetime_kst : '',
			min5_gijun_datetime_kst : '',

			min5_rank1_coin : '',
			min5_rank1_coin_name : '',
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
			min5_rank5_gubun : '',
			min5_rank5_open : '',
			min5_rank5_low : '',
			min5_rank5_high : '',
			min5_rank5_close : '',
			min5_rank5_o_c_rate : '',
			min5_rank5_o_c_subtract : '',
			min5_rank5_price_volume : '',

			/* MIN15 */
			min15_api_datetime_kst : '',
			min15_gijun_datetime_kst : '',

			min15_rank1_coin : '',
			min15_rank1_coin_name : '',
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
			min15_rank5_gubun : '',
			min15_rank5_open : '',
			min15_rank5_low : '',
			min15_rank5_high : '',
			min15_rank5_close : '',
			min15_rank5_o_c_rate : '',
			min15_rank5_o_c_subtract : '',
			min15_rank5_price_volume : '',

			/* MIN60 */
			min60_api_datetime_kst : '',
			min60_gijun_datetime_kst : '',

			min60_rank1_coin : '',
			min60_rank1_coin_name : '',
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
			min60_rank5_gubun : '',
			min60_rank5_open : '',
			min60_rank5_low : '',
			min60_rank5_high : '',
			min60_rank5_close : '',
			min60_rank5_o_c_rate : '',
			min60_rank5_o_c_subtract : '',
			min60_rank5_price_volume : '',

			/* MIN240 */
			min240_api_datetime_kst : '',
			min240_gijun_datetime_kst : '',

			min240_rank1_coin : '',
			min240_rank1_coin_name : '',
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
			min240_rank5_gubun : '',
			min240_rank5_open : '',
			min240_rank5_low : '',
			min240_rank5_high : '',
			min240_rank5_close : '',
			min240_rank5_o_c_rate : '',
			min240_rank5_o_c_subtract : '',
			min240_rank5_price_volume : '',
      	}
    },
	components: {
	},
    mounted() {
		this.Data_MIN5_Make();
		this.Data_MIN15_Make();
		this.Data_MIN60_Make();
		this.Data_MIN240_Make();
		this.CurrentDataTime();
		this.Data_Notice();
		setInterval(this.CurrentDataTime.bind(this) , 1000);
	},
	methods: {
		Data_MIN5_Make(){
			axios.get('/UpbitFullLive/Main_5MIN')
			.then(response => {
				if(response.status == 200){
					console.log('Main_5MIN')
					console.log(response)

					this.min5_api_datetime_kst = response.data[0].API_DATETIME_KST;
					this.min5_gijun_datetime_kst = response.data[0].CURRENT_DATETIME_KST;

					this.min5_rank1_coin = response.data[0].API_Coin_Ticker;
					this.min5_rank1_coin_name = response.data[0].Coin_Kor_Name;
					this.min5_rank1_gubun = response.data[0].gubun;
					this.min5_rank1_open = response.data[0].o_price;
					this.min5_rank1_low = response.data[0].l_price;
					this.min5_rank1_high = response.data[0].h_price;
					this.min5_rank1_close = response.data[0].format_c_price;
					this.min5_rank1_o_c_rate = response.data[0].o_c_rate;
					this.min5_rank1_o_c_subtract = response.data[0].o_c_subtract;
					this.min5_rank1_price_volume = response.data[0].format_volume_price;

					this.min5_rank2_coin = response.data[1].API_Coin_Ticker;
					this.min5_rank2_coin_name = response.data[1].Coin_Kor_Name;
					this.min5_rank2_gubun = response.data[1].gubun;
					this.min5_rank2_open = response.data[1].o_price;
					this.min5_rank2_low = response.data[1].l_price;
					this.min5_rank2_high = response.data[1].h_price;
					this.min5_rank2_close = response.data[1].format_c_price;
					this.min5_rank2_o_c_rate = response.data[1].o_c_rate;
					this.min5_rank2_o_c_subtract = response.data[1].o_c_subtract;
					this.min5_rank2_price_volume = response.data[1].format_volume_price;

					this.min5_rank3_coin = response.data[2].API_Coin_Ticker;
					this.min5_rank3_coin_name = response.data[2].Coin_Kor_Name;
					this.min5_rank3_gubun = response.data[2].gubun;
					this.min5_rank3_open = response.data[2].o_price;
					this.min5_rank3_low = response.data[2].l_price;
					this.min5_rank3_high = response.data[2].h_price;
					this.min5_rank3_close = response.data[2].format_c_price;
					this.min5_rank3_o_c_rate = response.data[2].o_c_rate;
					this.min5_rank3_o_c_subtract = response.data[2].o_c_subtract;
					this.min5_rank3_price_volume = response.data[2].format_volume_price;
					
					this.min5_rank4_coin = response.data[3].API_Coin_Ticker;
					this.min5_rank4_coin_name = response.data[3].Coin_Kor_Name;
					this.min5_rank4_gubun = response.data[3].gubun;
					this.min5_rank4_open = response.data[3].o_price;
					this.min5_rank4_low = response.data[3].l_price;
					this.min5_rank4_high = response.data[3].h_price;
					this.min5_rank4_close = response.data[3].format_c_price;
					this.min5_rank4_o_c_rate = response.data[3].o_c_rate;
					this.min5_rank4_o_c_subtract = response.data[3].o_c_subtract;
					this.min5_rank4_price_volume = response.data[3].format_volume_price;
					
					this.min5_rank5_coin = response.data[4].API_Coin_Ticker;
					this.min5_rank5_coin_name = response.data[4].Coin_Kor_Name;
					this.min5_rank5_gubun = response.data[4].gubun;
					this.min5_rank5_open = response.data[4].o_price;
					this.min5_rank5_low = response.data[4].l_price;
					this.min5_rank5_high = response.data[4].h_price;
					this.min5_rank5_close = response.data[4].format_c_price;
					this.min5_rank5_o_c_rate = response.data[4].o_c_rate;
					this.min5_rank5_o_c_subtract = response.data[4].o_c_subtract;
					this.min5_rank5_price_volume = response.data[4].format_volume_price;
				}
			})
		},
		Data_MIN15_Make(){
			axios.get('/UpbitFullLive/Main_15MIN')
			.then(response => {
				if(response.status == 200){
					console.log('Main_15MIN')
					console.log(response)

					this.min15_api_datetime_kst = response.data[0].API_DATETIME_KST;
					this.min15_gijun_datetime_kst = response.data[0].CURRENT_DATETIME_KST;

					this.min15_rank1_coin = response.data[0].API_Coin_Ticker;
					this.min15_rank1_coin_name = response.data[0].Coin_Kor_Name;
					this.min15_rank1_gubun = response.data[0].gubun;
					this.min15_rank1_open = response.data[0].o_price;
					this.min15_rank1_low = response.data[0].l_price;
					this.min15_rank1_high = response.data[0].h_price;
					this.min15_rank1_close = response.data[0].format_c_price;
					this.min15_rank1_o_c_rate = response.data[0].o_c_rate;
					this.min15_rank1_o_c_subtract = response.data[0].o_c_subtract;
					this.min15_rank1_price_volume = response.data[0].format_volume_price;

					this.min15_rank2_coin = response.data[1].API_Coin_Ticker;
					this.min15_rank2_coin_name = response.data[1].Coin_Kor_Name;
					this.min15_rank2_gubun = response.data[1].gubun;
					this.min15_rank2_open = response.data[1].o_price;
					this.min15_rank2_low = response.data[1].l_price;
					this.min15_rank2_high = response.data[1].h_price;
					this.min15_rank2_close = response.data[1].format_c_price;
					this.min15_rank2_o_c_rate = response.data[1].o_c_rate;
					this.min15_rank2_o_c_subtract = response.data[1].o_c_subtract;
					this.min15_rank2_price_volume = response.data[1].format_volume_price;

					this.min15_rank3_coin = response.data[2].API_Coin_Ticker;
					this.min15_rank3_coin_name = response.data[2].Coin_Kor_Name;
					this.min15_rank3_gubun = response.data[2].gubun;
					this.min15_rank3_open = response.data[2].o_price;
					this.min15_rank3_low = response.data[2].l_price;
					this.min15_rank3_high = response.data[2].h_price;
					this.min15_rank3_close = response.data[2].format_c_price;
					this.min15_rank3_o_c_rate = response.data[2].o_c_rate;
					this.min15_rank3_o_c_subtract = response.data[2].o_c_subtract;
					this.min15_rank3_price_volume = response.data[2].format_volume_price;
					
					this.min15_rank4_coin = response.data[3].API_Coin_Ticker;
					this.min15_rank4_coin_name = response.data[3].Coin_Kor_Name;
					this.min15_rank4_gubun = response.data[3].gubun;
					this.min15_rank4_open = response.data[3].o_price;
					this.min15_rank4_low = response.data[3].l_price;
					this.min15_rank4_high = response.data[3].h_price;
					this.min15_rank4_close = response.data[3].format_c_price;
					this.min15_rank4_o_c_rate = response.data[3].o_c_rate;
					this.min15_rank4_o_c_subtract = response.data[3].o_c_subtract;
					this.min15_rank4_price_volume = response.data[3].format_volume_price;
					
					this.min15_rank5_coin = response.data[4].API_Coin_Ticker;
					this.min15_rank5_coin_name = response.data[4].Coin_Kor_Name;
					this.min15_rank5_gubun = response.data[4].gubun;
					this.min15_rank5_open = response.data[4].o_price;
					this.min15_rank5_low = response.data[4].l_price;
					this.min15_rank5_high = response.data[4].h_price;
					this.min15_rank5_close = response.data[4].format_c_price;
					this.min15_rank5_o_c_rate = response.data[4].o_c_rate;
					this.min15_rank5_o_c_subtract = response.data[4].o_c_subtract;
					this.min15_rank5_price_volume = response.data[4].format_volume_price;
				}
			})
		},
		Data_MIN60_Make(){
			axios.get('/UpbitFullLive/Main_60MIN')
			.then(response => {
				if(response.status == 200){
					console.log('Main_60MIN')
					console.log(response)

					this.min60_api_datetime_kst = response.data[0].API_DATETIME_KST;
					this.min60_gijun_datetime_kst = response.data[0].CURRENT_DATETIME_KST;

					this.min60_rank1_coin = response.data[0].API_Coin_Ticker;
					this.min60_rank1_coin_name = response.data[0].Coin_Kor_Name;
					this.min60_rank1_gubun = response.data[0].gubun;
					this.min60_rank1_open = response.data[0].o_price;
					this.min60_rank1_low = response.data[0].l_price;
					this.min60_rank1_high = response.data[0].h_price;
					this.min60_rank1_close = response.data[0].format_c_price;
					this.min60_rank1_o_c_rate = response.data[0].o_c_rate;
					this.min60_rank1_o_c_subtract = response.data[0].o_c_subtract;
					this.min60_rank1_price_volume = response.data[0].format_volume_price;

					this.min60_rank2_coin = response.data[1].API_Coin_Ticker;
					this.min60_rank2_coin_name = response.data[1].Coin_Kor_Name;
					this.min60_rank2_gubun = response.data[1].gubun;
					this.min60_rank2_open = response.data[1].o_price;
					this.min60_rank2_low = response.data[1].l_price;
					this.min60_rank2_high = response.data[1].h_price;
					this.min60_rank2_close = response.data[1].format_c_price;
					this.min60_rank2_o_c_rate = response.data[1].o_c_rate;
					this.min60_rank2_o_c_subtract = response.data[1].o_c_subtract;
					this.min60_rank2_price_volume = response.data[1].format_volume_price;

					this.min60_rank3_coin = response.data[2].API_Coin_Ticker;
					this.min60_rank3_coin_name = response.data[2].Coin_Kor_Name;
					this.min60_rank3_gubun = response.data[2].gubun;
					this.min60_rank3_open = response.data[2].o_price;
					this.min60_rank3_low = response.data[2].l_price;
					this.min60_rank3_high = response.data[2].h_price;
					this.min60_rank3_close = response.data[2].format_c_price;
					this.min60_rank3_o_c_rate = response.data[2].o_c_rate;
					this.min60_rank3_o_c_subtract = response.data[2].o_c_subtract;
					this.min60_rank3_price_volume = response.data[2].format_volume_price;
					
					this.min60_rank4_coin = response.data[3].API_Coin_Ticker;
					this.min60_rank4_coin_name = response.data[3].Coin_Kor_Name;
					this.min60_rank4_gubun = response.data[3].gubun;
					this.min60_rank4_open = response.data[3].o_price;
					this.min60_rank4_low = response.data[3].l_price;
					this.min60_rank4_high = response.data[3].h_price;
					this.min60_rank4_close = response.data[3].format_c_price;
					this.min60_rank4_o_c_rate = response.data[3].o_c_rate;
					this.min60_rank4_o_c_subtract = response.data[3].o_c_subtract;
					this.min60_rank4_price_volume = response.data[3].format_volume_price;
					
					this.min60_rank5_coin = response.data[4].API_Coin_Ticker;
					this.min60_rank5_coin_name = response.data[4].Coin_Kor_Name;
					this.min60_rank5_gubun = response.data[4].gubun;
					this.min60_rank5_open = response.data[4].o_price;
					this.min60_rank5_low = response.data[4].l_price;
					this.min60_rank5_high = response.data[4].h_price;
					this.min60_rank5_close = response.data[4].format_c_price;
					this.min60_rank5_o_c_rate = response.data[4].o_c_rate;
					this.min60_rank5_o_c_subtract = response.data[4].o_c_subtract;
					this.min60_rank5_price_volume = response.data[4].format_volume_price;
				}
			})
		},
		Data_MIN240_Make(){
			axios.get('/UpbitFullLive/Main_240MIN')
			.then(response => {
				if(response.status == 200){
					console.log('Main_240MIN')
					console.log(response)

					this.min240_api_datetime_kst = response.data[0].API_DATETIME_KST;
					this.min240_gijun_datetime_kst = response.data[0].CURRENT_DATETIME_KST;

					this.min240_rank1_coin = response.data[0].API_Coin_Ticker;
					this.min240_rank1_coin_name = response.data[0].Coin_Kor_Name;
					this.min240_rank1_gubun = response.data[0].gubun;
					this.min240_rank1_open = response.data[0].o_price;
					this.min240_rank1_low = response.data[0].l_price;
					this.min240_rank1_high = response.data[0].h_price;
					this.min240_rank1_close = response.data[0].format_c_price;
					this.min240_rank1_o_c_rate = response.data[0].o_c_rate;
					this.min240_rank1_o_c_subtract = response.data[0].o_c_subtract;
					this.min240_rank1_price_volume = response.data[0].format_volume_price;

					this.min240_rank2_coin = response.data[1].API_Coin_Ticker;
					this.min240_rank2_coin_name = response.data[1].Coin_Kor_Name;
					this.min240_rank2_gubun = response.data[1].gubun;
					this.min240_rank2_open = response.data[1].o_price;
					this.min240_rank2_low = response.data[1].l_price;
					this.min240_rank2_high = response.data[1].h_price;
					this.min240_rank2_close = response.data[1].format_c_price;
					this.min240_rank2_o_c_rate = response.data[1].o_c_rate;
					this.min240_rank2_o_c_subtract = response.data[1].o_c_subtract;
					this.min240_rank2_price_volume = response.data[1].format_volume_price;

					this.min240_rank3_coin = response.data[2].API_Coin_Ticker;
					this.min240_rank3_coin_name = response.data[2].Coin_Kor_Name;
					this.min240_rank3_gubun = response.data[2].gubun;
					this.min240_rank3_open = response.data[2].o_price;
					this.min240_rank3_low = response.data[2].l_price;
					this.min240_rank3_high = response.data[2].h_price;
					this.min240_rank3_close = response.data[2].format_c_price;
					this.min240_rank3_o_c_rate = response.data[2].o_c_rate;
					this.min240_rank3_o_c_subtract = response.data[2].o_c_subtract;
					this.min240_rank3_price_volume = response.data[2].format_volume_price;
					
					this.min240_rank4_coin = response.data[3].API_Coin_Ticker;
					this.min240_rank4_coin_name = response.data[3].Coin_Kor_Name;
					this.min240_rank4_gubun = response.data[3].gubun;
					this.min240_rank4_open = response.data[3].o_price;
					this.min240_rank4_low = response.data[3].l_price;
					this.min240_rank4_high = response.data[3].h_price;
					this.min240_rank4_close = response.data[3].format_c_price;
					this.min240_rank4_o_c_rate = response.data[3].o_c_rate;
					this.min240_rank4_o_c_subtract = response.data[3].o_c_subtract;
					this.min240_rank4_price_volume = response.data[3].format_volume_price;
					
					this.min240_rank5_coin = response.data[4].API_Coin_Ticker;
					this.min240_rank5_coin_name = response.data[4].Coin_Kor_Name;
					this.min240_rank5_gubun = response.data[4].gubun;
					this.min240_rank5_open = response.data[4].o_price;
					this.min240_rank5_low = response.data[4].l_price;
					this.min240_rank5_high = response.data[4].h_price;
					this.min240_rank5_close = response.data[4].format_c_price;
					this.min240_rank5_o_c_rate = response.data[4].o_c_rate;
					this.min240_rank5_o_c_subtract = response.data[4].o_c_subtract;
					this.min240_rank5_price_volume = response.data[4].format_volume_price;
				}
			})
		},
		CurrentDataTime() {
			let mi = new Date().getMinutes() < 10? "0" + new Date().getMinutes(): new Date().getMinutes();
			let ss = new Date().getSeconds() < 10? "0" + new Date().getSeconds(): new Date().getSeconds();
			
			if(Number(ss) == 0){
				this.Data_Notice();
			}

			if(Number(ss) == 0 || Number(ss) == 5 || Number(ss) == 10 || Number(ss) == 15 || Number(ss) == 20 || Number(ss) == 25 || Number(ss) == 30 || Number(ss) == 35 || Number(ss) == 40 || Number(ss) == 45 || Number(ss) == 50 || Number(ss) == 55){
				this.Data_MIN5_Make();
				this.Data_MIN15_Make();
				this.Data_MIN60_Make();
				this.Data_MIN240_Make();
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