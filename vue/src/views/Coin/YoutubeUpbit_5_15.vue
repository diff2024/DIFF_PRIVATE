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
						업비트 5분 [{{ min5_api_datetime_kst }}]
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
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank2_border_color }">
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
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank3_border_color }">
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
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank4_border_color }">
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
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank5_border_color }">
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

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min5_rank6_border_color }">
						{{ min5_rank6_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
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
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
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
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
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
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
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
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
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
						업비트 15분 [{{ min15_api_datetime_kst }}]
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
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank2_border_color }">
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
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank3_border_color }">
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
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank4_border_color }">
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
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank5_border_color }">
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

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + min15_rank6_border_color }">
						{{ min15_rank6_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
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
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
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
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
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
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
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
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:10px; padding-top:15px; color:black; font-size:36px;">
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
									<td style="color:black; font-size:28px; text-align:right;">-&nbsp;0.00%</td>
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
									<td style="color:black; font-size:24px; text-align:right;">-&nbsp;0</td>
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
		this.Data_MIN5_Make();
		this.Data_MIN15_Make();
		this.CurrentDataTime();
		setInterval(this.CurrentDataTime.bind(this) , 1000);
	},
	methods: {
		Data_MIN5_Make(){
			axios.get('/YoutubeUpbit/MIN5')
			.then(response => {
				if(response.status == 200){
					console.log('MIN5')
					console.log(response)

					this.min5_api_datetime_kst = response.data[0].API_DATETIME_KST;
					this.min5_gijun_datetime_kst = response.data[0].CURRENT_DATETIME_KST;
					
					if(this.min5_rank1_coin_name != response.data[0].Coin_Kor_Name){
						if(!(Number(new Date().getMinutes())%5==0 && Number(new Date().getSeconds()) < 5)){
							this.min5_rank1_coin = response.data[0].API_Coin_Ticker;
							this.min5_rank1_coin_name = response.data[0].Coin_Kor_Name;

							if(this.min5_rank1_coin_name != ''){
								if(response.data[0].gubun == '상승'){
									this.min5_rank1_border_color = 'red';
								}else if(response.data[0].gubun == '하락'){
									this.min5_rank1_border_color = 'blue';
								}else if(response.data[0].gubun == '보합'){
									this.min5_rank1_border_color = 'black';
								}

								setTimeout(() => 
									this.min5_rank1_border_color = 'white'
								, 500);
							}
						}
					}
					this.min5_rank1_gubun = response.data[0].gubun;
					this.min5_rank1_open = response.data[0].o_price;
					this.min5_rank1_low = response.data[0].l_price;
					this.min5_rank1_high = response.data[0].h_price;
					this.min5_rank1_close = response.data[0].format_c_price;
					this.min5_rank1_o_c_rate = response.data[0].o_c_rate;
					this.min5_rank1_o_c_subtract = response.data[0].o_c_subtract;
					this.min5_rank1_price_volume = response.data[0].format_volume_price;

					if(this.min5_rank2_coin_name != response.data[1].Coin_Kor_Name){
						this.min5_rank2_coin = response.data[1].API_Coin_Ticker;
						this.min5_rank2_coin_name = response.data[1].Coin_Kor_Name;

						if(this.min5_rank2_coin_name != ''){
							if(response.data[1].gubun == '상승'){
								this.min5_rank2_border_color = 'red';
							}else if(response.data[1].gubun == '하락'){
								this.min5_rank2_border_color = 'blue';
							}else if(response.data[1].gubun == '보합'){
								this.min5_rank2_border_color = 'black';
							}

							setTimeout(() => 
								this.min5_rank2_border_color = 'white'
							, 500);
						}
					}
					this.min5_rank2_gubun = response.data[1].gubun;
					this.min5_rank2_open = response.data[1].o_price;
					this.min5_rank2_low = response.data[1].l_price;
					this.min5_rank2_high = response.data[1].h_price;
					this.min5_rank2_close = response.data[1].format_c_price;
					this.min5_rank2_o_c_rate = response.data[1].o_c_rate;
					this.min5_rank2_o_c_subtract = response.data[1].o_c_subtract;
					this.min5_rank2_price_volume = response.data[1].format_volume_price;

					if(this.min5_rank3_coin_name != response.data[2].Coin_Kor_Name){
						this.min5_rank3_coin = response.data[2].API_Coin_Ticker;
						this.min5_rank3_coin_name = response.data[2].Coin_Kor_Name;

						if(this.min5_rank3_coin_name != ''){
							if(response.data[2].gubun == '상승'){
								this.min5_rank3_border_color = 'red';
							}else if(response.data[2].gubun == '하락'){
								this.min5_rank3_border_color = 'blue';
							}else if(response.data[2].gubun == '보합'){
								this.min5_rank3_border_color = 'black';
							}

							setTimeout(() => 
								this.min5_rank3_border_color = 'white'
							, 500);
						}
					}
					this.min5_rank3_gubun = response.data[2].gubun;
					this.min5_rank3_open = response.data[2].o_price;
					this.min5_rank3_low = response.data[2].l_price;
					this.min5_rank3_high = response.data[2].h_price;
					this.min5_rank3_close = response.data[2].format_c_price;
					this.min5_rank3_o_c_rate = response.data[2].o_c_rate;
					this.min5_rank3_o_c_subtract = response.data[2].o_c_subtract;
					this.min5_rank3_price_volume = response.data[2].format_volume_price;
					
					if(this.min5_rank4_coin_name != response.data[3].Coin_Kor_Name){
						this.min5_rank4_coin = response.data[3].API_Coin_Ticker;
						this.min5_rank4_coin_name = response.data[3].Coin_Kor_Name;

						if(this.min5_rank4_coin_name != ''){
							if(response.data[3].gubun == '상승'){
								this.min5_rank4_border_color = 'red';
							}else if(response.data[3].gubun == '하락'){
								this.min5_rank4_border_color = 'blue';
							}else if(response.data[3].gubun == '보합'){
								this.min5_rank4_border_color = 'black';
							}

							setTimeout(() => 
								this.min5_rank4_border_color = 'white'
							, 500);
						}
					}
					this.min5_rank4_gubun = response.data[3].gubun;
					this.min5_rank4_open = response.data[3].o_price;
					this.min5_rank4_low = response.data[3].l_price;
					this.min5_rank4_high = response.data[3].h_price;
					this.min5_rank4_close = response.data[3].format_c_price;
					this.min5_rank4_o_c_rate = response.data[3].o_c_rate;
					this.min5_rank4_o_c_subtract = response.data[3].o_c_subtract;
					this.min5_rank4_price_volume = response.data[3].format_volume_price;
					
					if(this.min5_rank5_coin_name != response.data[4].Coin_Kor_Name){
						this.min5_rank5_coin = response.data[4].API_Coin_Ticker;
						this.min5_rank5_coin_name = response.data[4].Coin_Kor_Name;

						if(this.min5_rank5_coin_name != ''){
							if(response.data[4].gubun == '상승'){
								this.min5_rank5_border_color = 'red';
							}else if(response.data[4].gubun == '하락'){
								this.min5_rank5_border_color = 'blue';
							}else if(response.data[4].gubun == '보합'){
								this.min5_rank5_border_color = 'black';
							}

							setTimeout(() => 
								this.min5_rank5_border_color = 'white'
							, 500);
						}
					}
					this.min5_rank5_gubun = response.data[4].gubun;
					this.min5_rank5_open = response.data[4].o_price;
					this.min5_rank5_low = response.data[4].l_price;
					this.min5_rank5_high = response.data[4].h_price;
					this.min5_rank5_close = response.data[4].format_c_price;
					this.min5_rank5_o_c_rate = response.data[4].o_c_rate;
					this.min5_rank5_o_c_subtract = response.data[4].o_c_subtract;
					this.min5_rank5_price_volume = response.data[4].format_volume_price;

					if(this.min5_rank6_coin_name != response.data[5].Coin_Kor_Name){
						this.min5_rank6_coin = response.data[5].API_Coin_Ticker;
						this.min5_rank6_coin_name = response.data[5].Coin_Kor_Name;

						if(this.min5_rank6_coin_name != ''){
							if(response.data[5].gubun == '상승'){
								this.min5_rank6_border_color = 'red';
							}else if(response.data[5].gubun == '하락'){
								this.min5_rank6_border_color = 'blue';
							}else if(response.data[5].gubun == '보합'){
								this.min5_rank6_border_color = 'black';
							}

							setTimeout(() => 
								this.min5_rank6_border_color = 'white'
							, 500);
						}
					}
					this.min5_rank6_gubun = response.data[5].gubun;
					this.min5_rank6_open = response.data[5].o_price;
					this.min5_rank6_low = response.data[5].l_price;
					this.min5_rank6_high = response.data[5].h_price;
					this.min5_rank6_close = response.data[5].format_c_price;
					this.min5_rank6_o_c_rate = response.data[5].o_c_rate;
					this.min5_rank6_o_c_subtract = response.data[5].o_c_subtract;
					this.min5_rank6_price_volume = response.data[5].format_volume_price;
					
					if(this.min5_rank7_coin_name != response.data[6].Coin_Kor_Name){
						this.min5_rank7_coin = response.data[6].API_Coin_Ticker;
						this.min5_rank7_coin_name = response.data[6].Coin_Kor_Name;

						if(this.min5_rank7_coin_name != ''){
							if(response.data[6].gubun == '상승'){
								this.min5_rank7_border_color = 'red';
							}else if(response.data[6].gubun == '하락'){
								this.min5_rank7_border_color = 'blue';
							}else if(response.data[6].gubun == '보합'){
								this.min5_rank7_border_color = 'black';
							}

							setTimeout(() => 
								this.min5_rank7_border_color = 'white'
							, 500);
						}
					}
					this.min5_rank7_gubun = response.data[6].gubun;
					this.min5_rank7_open = response.data[6].o_price;
					this.min5_rank7_low = response.data[6].l_price;
					this.min5_rank7_high = response.data[6].h_price;
					this.min5_rank7_close = response.data[6].format_c_price;
					this.min5_rank7_o_c_rate = response.data[6].o_c_rate;
					this.min5_rank7_o_c_subtract = response.data[6].o_c_subtract;
					this.min5_rank7_price_volume = response.data[6].format_volume_price;

					if(this.min5_rank8_coin_name != response.data[7].Coin_Kor_Name){
						this.min5_rank8_coin = response.data[7].API_Coin_Ticker;
						this.min5_rank8_coin_name = response.data[7].Coin_Kor_Name;

						if(this.min5_rank8_coin_name != ''){
							if(response.data[7].gubun == '상승'){
								this.min5_rank8_border_color = 'red';
							}else if(response.data[7].gubun == '하락'){
								this.min5_rank8_border_color = 'blue';
							}else if(response.data[7].gubun == '보합'){
								this.min5_rank8_border_color = 'black';
							}

							setTimeout(() => 
								this.min5_rank8_border_color = 'white'
							, 500);
						}
					}
					this.min5_rank8_gubun = response.data[7].gubun;
					this.min5_rank8_open = response.data[7].o_price;
					this.min5_rank8_low = response.data[7].l_price;
					this.min5_rank8_high = response.data[7].h_price;
					this.min5_rank8_close = response.data[7].format_c_price;
					this.min5_rank8_o_c_rate = response.data[7].o_c_rate;
					this.min5_rank8_o_c_subtract = response.data[7].o_c_subtract;
					this.min5_rank8_price_volume = response.data[7].format_volume_price;

					if(this.min5_rank9_coin_name != response.data[8].Coin_Kor_Name){
						this.min5_rank9_coin = response.data[8].API_Coin_Ticker;
						this.min5_rank9_coin_name = response.data[8].Coin_Kor_Name;

						if(this.min5_rank9_coin_name != ''){
							if(response.data[8].gubun == '상승'){
								this.min5_rank9_border_color = 'red';
							}else if(response.data[8].gubun == '하락'){
								this.min5_rank9_border_color = 'blue';
							}else if(response.data[8].gubun == '보합'){
								this.min5_rank9_border_color = 'black';
							}

							setTimeout(() => 
								this.min5_rank9_border_color = 'white'
							, 500);
						}
					}
					this.min5_rank9_gubun = response.data[8].gubun;
					this.min5_rank9_open = response.data[8].o_price;
					this.min5_rank9_low = response.data[8].l_price;
					this.min5_rank9_high = response.data[8].h_price;
					this.min5_rank9_close = response.data[8].format_c_price;
					this.min5_rank9_o_c_rate = response.data[8].o_c_rate;
					this.min5_rank9_o_c_subtract = response.data[8].o_c_subtract;
					this.min5_rank9_price_volume = response.data[8].format_volume_price;
					
					if(this.min5_rank10_coin_name != response.data[9].Coin_Kor_Name){
						this.min5_rank10_coin = response.data[9].API_Coin_Ticker;
						this.min5_rank10_coin_name = response.data[9].Coin_Kor_Name;

						if(this.min5_rank10_coin_name != ''){
							if(response.data[9].gubun == '상승'){
								this.min5_rank10_border_color = 'red';
							}else if(response.data[9].gubun == '하락'){
								this.min5_rank10_border_color = 'blue';
							}else if(response.data[9].gubun == '보합'){
								this.min5_rank10_border_color = 'black';
							}

							setTimeout(() => 
								this.min5_rank10_border_color = 'white'
							, 500);
						}
					}
					this.min5_rank10_gubun = response.data[9].gubun;
					this.min5_rank10_open = response.data[9].o_price;
					this.min5_rank10_low = response.data[9].l_price;
					this.min5_rank10_high = response.data[9].h_price;
					this.min5_rank10_close = response.data[9].format_c_price;
					this.min5_rank10_o_c_rate = response.data[9].o_c_rate;
					this.min5_rank10_o_c_subtract = response.data[9].o_c_subtract;
					this.min5_rank10_price_volume = response.data[9].format_volume_price;
				}

				this.Data_MIN5_Make();
			})
		},
		Data_MIN15_Make(){
			axios.get('/YoutubeUpbit/MIN15')
			.then(response => {
				if(response.status == 200){
					console.log('MIN15')
					console.log(response)

					this.min15_api_datetime_kst = response.data[0].API_DATETIME_KST;
					this.min15_gijun_datetime_kst = response.data[0].CURRENT_DATETIME_KST;

					if(this.min15_rank1_coin_name != response.data[0].Coin_Kor_Name){
						this.min15_rank1_coin = response.data[0].API_Coin_Ticker;
						this.min15_rank1_coin_name = response.data[0].Coin_Kor_Name;

						if(this.min15_rank1_coin_name != ''){
							if(response.data[0].gubun == '상승'){
								this.min15_rank1_border_color = 'red';
							}else if(response.data[0].gubun == '하락'){
								this.min15_rank1_border_color = 'blue';
							}else if(response.data[0].gubun == '보합'){
								this.min15_rank1_border_color = 'black';
							}

							setTimeout(() => 
								this.min15_rank1_border_color = 'white'
							, 500);
						}
					}
					this.min15_rank1_gubun = response.data[0].gubun;
					this.min15_rank1_open = response.data[0].o_price;
					this.min15_rank1_low = response.data[0].l_price;
					this.min15_rank1_high = response.data[0].h_price;
					this.min15_rank1_close = response.data[0].format_c_price;
					this.min15_rank1_o_c_rate = response.data[0].o_c_rate;
					this.min15_rank1_o_c_subtract = response.data[0].o_c_subtract;
					this.min15_rank1_price_volume = response.data[0].format_volume_price;

					if(this.min15_rank2_coin_name != response.data[1].Coin_Kor_Name){
						this.min15_rank2_coin = response.data[1].API_Coin_Ticker;
						this.min15_rank2_coin_name = response.data[1].Coin_Kor_Name;

						if(this.min15_rank2_coin_name != ''){
							if(response.data[1].gubun == '상승'){
								this.min15_rank2_border_color = 'red';
							}else if(response.data[1].gubun == '하락'){
								this.min15_rank2_border_color = 'blue';
							}else if(response.data[1].gubun == '보합'){
								this.min15_rank2_border_color = 'black';
							}

							setTimeout(() => 
								this.min15_rank2_border_color = 'white'
							, 500);
						}
					}
					this.min15_rank2_gubun = response.data[1].gubun;
					this.min15_rank2_open = response.data[1].o_price;
					this.min15_rank2_low = response.data[1].l_price;
					this.min15_rank2_high = response.data[1].h_price;
					this.min15_rank2_close = response.data[1].format_c_price;
					this.min15_rank2_o_c_rate = response.data[1].o_c_rate;
					this.min15_rank2_o_c_subtract = response.data[1].o_c_subtract;
					this.min15_rank2_price_volume = response.data[1].format_volume_price;

					if(this.min15_rank3_coin_name != response.data[2].Coin_Kor_Name){
						this.min15_rank3_coin = response.data[2].API_Coin_Ticker;
						this.min15_rank3_coin_name = response.data[2].Coin_Kor_Name;

						if(this.min15_rank3_coin_name != ''){
							if(response.data[2].gubun == '상승'){
								this.min15_rank3_border_color = 'red';
							}else if(response.data[2].gubun == '하락'){
								this.min15_rank3_border_color = 'blue';
							}else if(response.data[2].gubun == '보합'){
								this.min15_rank3_border_color = 'black';
							}

							setTimeout(() => 
								this.min15_rank3_border_color = 'white'
							, 500);
						}
					}
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
					
					if(this.min15_rank4_coin_name != response.data[3].Coin_Kor_Name){
						this.min15_rank4_coin = response.data[3].API_Coin_Ticker;
						this.min15_rank4_coin_name = response.data[3].Coin_Kor_Name;

						if(this.min15_rank4_coin_name != ''){
							if(response.data[3].gubun == '상승'){
								this.min15_rank4_border_color = 'red';
							}else if(response.data[3].gubun == '하락'){
								this.min15_rank4_border_color = 'blue';
							}else if(response.data[3].gubun == '보합'){
								this.min15_rank4_border_color = 'black';
							}

							setTimeout(() => 
								this.min15_rank4_border_color = 'white'
							, 500);
						}
					}
					this.min15_rank4_gubun = response.data[3].gubun;
					this.min15_rank4_open = response.data[3].o_price;
					this.min15_rank4_low = response.data[3].l_price;
					this.min15_rank4_high = response.data[3].h_price;
					this.min15_rank4_close = response.data[3].format_c_price;
					this.min15_rank4_o_c_rate = response.data[3].o_c_rate;
					this.min15_rank4_o_c_subtract = response.data[3].o_c_subtract;
					this.min15_rank4_price_volume = response.data[3].format_volume_price;
					
					if(this.min15_rank5_coin_name != response.data[4].Coin_Kor_Name){
						this.min15_rank5_coin = response.data[4].API_Coin_Ticker;
						this.min15_rank5_coin_name = response.data[4].Coin_Kor_Name;

						if(this.min15_rank5_coin_name != ''){
							if(response.data[4].gubun == '상승'){
								this.min15_rank5_border_color = 'red';
							}else if(response.data[4].gubun == '하락'){
								this.min15_rank5_border_color = 'blue';
							}else if(response.data[4].gubun == '보합'){
								this.min15_rank5_border_color = 'black';
							}

							setTimeout(() => 
								this.min15_rank5_border_color = 'white'
							, 500);
						}
					}
					this.min15_rank5_gubun = response.data[4].gubun;
					this.min15_rank5_open = response.data[4].o_price;
					this.min15_rank5_low = response.data[4].l_price;
					this.min15_rank5_high = response.data[4].h_price;
					this.min15_rank5_close = response.data[4].format_c_price;
					this.min15_rank5_o_c_rate = response.data[4].o_c_rate;
					this.min15_rank5_o_c_subtract = response.data[4].o_c_subtract;
					this.min15_rank5_price_volume = response.data[4].format_volume_price;

					if(this.min15_rank6_coin_name != response.data[5].Coin_Kor_Name){
						this.min15_rank6_coin = response.data[5].API_Coin_Ticker;
						this.min15_rank6_coin_name = response.data[5].Coin_Kor_Name;

						if(this.min15_rank6_coin_name != ''){
							if(response.data[5].gubun == '상승'){
								this.min15_rank6_border_color = 'red';
							}else if(response.data[5].gubun == '하락'){
								this.min15_rank6_border_color = 'blue';
							}else if(response.data[5].gubun == '보합'){
								this.min15_rank6_border_color = 'black';
							}

							setTimeout(() => 
								this.min15_rank6_border_color = 'white'
							, 500);
						}
					}
					this.min15_rank6_gubun = response.data[5].gubun;
					this.min15_rank6_open = response.data[5].o_price;
					this.min15_rank6_low = response.data[5].l_price;
					this.min15_rank6_high = response.data[5].h_price;
					this.min15_rank6_close = response.data[5].format_c_price;
					this.min15_rank6_o_c_rate = response.data[5].o_c_rate;
					this.min15_rank6_o_c_subtract = response.data[5].o_c_subtract;
					this.min15_rank6_price_volume = response.data[5].format_volume_price;
					
					if(this.min15_rank7_coin_name != response.data[6].Coin_Kor_Name){
						this.min15_rank7_coin = response.data[6].API_Coin_Ticker;
						this.min15_rank7_coin_name = response.data[6].Coin_Kor_Name;

						if(this.min15_rank7_coin_name != ''){
							if(response.data[6].gubun == '상승'){
								this.min15_rank7_border_color = 'red';
							}else if(response.data[6].gubun == '하락'){
								this.min15_rank7_border_color = 'blue';
							}else if(response.data[6].gubun == '보합'){
								this.min15_rank7_border_color = 'black';
							}

							setTimeout(() => 
								this.min15_rank7_border_color = 'white'
							, 500);
						}
					}
					this.min15_rank7_gubun = response.data[6].gubun;
					this.min15_rank7_open = response.data[6].o_price;
					this.min15_rank7_low = response.data[6].l_price;
					this.min15_rank7_high = response.data[6].h_price;
					this.min15_rank7_close = response.data[6].format_c_price;
					this.min15_rank7_o_c_rate = response.data[6].o_c_rate;
					this.min15_rank7_o_c_subtract = response.data[6].o_c_subtract;
					this.min15_rank7_price_volume = response.data[6].format_volume_price;

					if(this.min15_rank8_coin_name != response.data[7].Coin_Kor_Name){
						this.min15_rank8_coin = response.data[7].API_Coin_Ticker;
						this.min15_rank8_coin_name = response.data[7].Coin_Kor_Name;

						if(this.min15_rank8_coin_name != ''){
							if(response.data[7].gubun == '상승'){
								this.min15_rank8_border_color = 'red';
							}else if(response.data[7].gubun == '하락'){
								this.min15_rank8_border_color = 'blue';
							}else if(response.data[7].gubun == '보합'){
								this.min15_rank8_border_color = 'black';
							}

							setTimeout(() => 
								this.min15_rank8_border_color = 'white'
							, 500);
						}
					}
					this.min15_rank8_gubun = response.data[7].gubun;
					this.min15_rank8_open = response.data[7].o_price;
					this.min15_rank8_low = response.data[7].l_price;
					this.min15_rank8_high = response.data[7].h_price;
					this.min15_rank8_close = response.data[7].format_c_price;
					this.min15_rank8_o_c_rate = response.data[7].o_c_rate;
					this.min15_rank8_o_c_subtract = response.data[7].o_c_subtract;
					this.min15_rank8_price_volume = response.data[7].format_volume_price;

					if(this.min15_rank9_coin_name != response.data[8].Coin_Kor_Name){
						this.min15_rank9_coin = response.data[8].API_Coin_Ticker;
						this.min15_rank9_coin_name = response.data[8].Coin_Kor_Name;

						if(this.min15_rank9_coin_name != ''){
							if(response.data[8].gubun == '상승'){
								this.min15_rank9_border_color = 'red';
							}else if(response.data[8].gubun == '하락'){
								this.min15_rank9_border_color = 'blue';
							}else if(response.data[8].gubun == '보합'){
								this.min15_rank9_border_color = 'black';
							}

							setTimeout(() => 
								this.min15_rank9_border_color = 'white'
							, 500);
						}
					}
					this.min15_rank9_gubun = response.data[8].gubun;
					this.min15_rank9_open = response.data[8].o_price;
					this.min15_rank9_low = response.data[8].l_price;
					this.min15_rank9_high = response.data[8].h_price;
					this.min15_rank9_close = response.data[8].format_c_price;
					this.min15_rank9_o_c_rate = response.data[8].o_c_rate;
					this.min15_rank9_o_c_subtract = response.data[8].o_c_subtract;
					this.min15_rank9_price_volume = response.data[8].format_volume_price;
					
					if(this.min15_rank10_coin_name != response.data[9].Coin_Kor_Name){
						this.min15_rank10_coin = response.data[9].API_Coin_Ticker;
						this.min15_rank10_coin_name = response.data[9].Coin_Kor_Name;

						if(this.min15_rank10_coin_name != ''){
							if(response.data[9].gubun == '상승'){
								this.min15_rank10_border_color = 'red';
							}else if(response.data[9].gubun == '하락'){
								this.min15_rank10_border_color = 'blue';
							}else if(response.data[9].gubun == '보합'){
								this.min15_rank10_border_color = 'black';
							}

							setTimeout(() => 
								this.min15_rank10_border_color = 'white'
							, 500);
						}
					}
					this.min15_rank10_gubun = response.data[9].gubun;
					this.min15_rank10_open = response.data[9].o_price;
					this.min15_rank10_low = response.data[9].l_price;
					this.min15_rank10_high = response.data[9].h_price;
					this.min15_rank10_close = response.data[9].format_c_price;
					this.min15_rank10_o_c_rate = response.data[9].o_c_rate;
					this.min15_rank10_o_c_subtract = response.data[9].o_c_subtract;
					this.min15_rank10_price_volume = response.data[9].format_volume_price;
				}

				this.Data_MIN15_Make();
			})
		},
		CurrentDataTime() {
			var mi = new Date().getMinutes() < 10? "0" + new Date().getMinutes(): new Date().getMinutes();
			var ss = new Date().getSeconds() < 10? "0" + new Date().getSeconds(): new Date().getSeconds();
			
			var min5_mi = (this.min5_gijun_datetime_kst).substr(14, 2)
			if(Number(min5_mi) == 0 && Number(mi) == 2){
				this.Data_MIN5_Make();
			}else if((Number(min5_mi) < Number(mi)-1) || (Number(min5_mi) > Number(mi)+1)){
				this.Data_MIN5_Make();
			}

			var min15_mi = (this.min15_gijun_datetime_kst).substr(14, 2)
			if(Number(min15_mi) == 0 && Number(mi) == 2){
				this.Data_MIN15_Make();
			}else if((Number(min15_mi) < Number(mi)-1) || (Number(min15_mi) > Number(mi)+1)){
				this.Data_MIN15_Make();
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