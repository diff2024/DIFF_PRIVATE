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
			<v-col xl="12" md="12" sm="12" style="height:40px; padding-top:0px;"></v-col>
		</v-row>
		<v-row>
			<v-col xl="6" md="6" sm="6" style="text-align:center; padding-top:0px; padding-right:25px; padding-left:25px;">
				<v-row>
					<v-col xl="8" md="8" sm="8" style="padding-left:20px; text-align:left; font-weight:bold; font-size:32px;">
						빗썸 1시간 거래대금 [{{ api_datetime_kst }}]
					</v-col>
					<v-col xl="4" md="4" sm="4" style="padding-right:20px; text-align:right; font-weight:bold; font-size:32px;">
						{{ current_datetime_kst }}
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
						변동율(1시간)
					</v-col>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						추정 거래대금(1시간)
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + volume_rank1_border_color, 'font-size': volume_rank1_font_size }">
						{{ volume_rank1_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank1_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.volume_rank1_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ volume_rank1_o_c_rate }}</td>
								</template>
								<template v-else-if="this.volume_rank1_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ volume_rank1_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.volume_rank1_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ volume_rank1_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.volume_rank1_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ volume_rank1_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank1_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + volume_rank2_border_color, 'font-size': volume_rank2_font_size }">
						{{ volume_rank2_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank2_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.volume_rank2_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ volume_rank2_o_c_rate }}</td>
								</template>
								<template v-else-if="this.volume_rank2_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ volume_rank2_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.volume_rank2_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ volume_rank2_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.volume_rank2_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ volume_rank2_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank2_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + volume_rank3_border_color, 'font-size': volume_rank3_font_size }">
						{{ volume_rank3_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank3_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.volume_rank3_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ volume_rank3_o_c_rate }}</td>
								</template>
								<template v-else-if="this.volume_rank3_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ volume_rank3_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.volume_rank3_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ volume_rank3_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.volume_rank3_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ volume_rank3_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank3_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + volume_rank4_border_color, 'font-size': volume_rank4_font_size }">
						{{ volume_rank4_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank4_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.volume_rank4_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ volume_rank4_o_c_rate }}</td>
								</template>
								<template v-else-if="this.volume_rank4_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ volume_rank4_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.volume_rank4_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ volume_rank4_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.volume_rank4_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ volume_rank4_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank4_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + volume_rank5_border_color, 'font-size': volume_rank5_font_size }">
						{{ volume_rank5_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank5_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.volume_rank5_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ volume_rank5_o_c_rate }}</td>
								</template>
								<template v-else-if="this.volume_rank5_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ volume_rank5_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.volume_rank5_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ volume_rank5_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.volume_rank5_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ volume_rank5_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank5_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + volume_rank6_border_color, 'font-size': volume_rank6_font_size }">
						{{ volume_rank6_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank6_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.volume_rank6_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ volume_rank6_o_c_rate }}</td>
								</template>
								<template v-else-if="this.volume_rank6_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ volume_rank6_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.volume_rank6_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ volume_rank6_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.volume_rank6_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ volume_rank6_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank6_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + volume_rank7_border_color, 'font-size': volume_rank7_font_size }">
						{{ volume_rank7_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank7_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.volume_rank7_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ volume_rank7_o_c_rate }}</td>
								</template>
								<template v-else-if="this.volume_rank7_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ volume_rank7_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.volume_rank7_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ volume_rank7_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.volume_rank7_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ volume_rank7_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank7_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + volume_rank8_border_color, 'font-size': volume_rank8_font_size }">
						{{ volume_rank8_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank8_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.volume_rank8_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ volume_rank8_o_c_rate }}</td>
								</template>
								<template v-else-if="this.volume_rank8_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ volume_rank8_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.volume_rank8_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ volume_rank8_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.volume_rank8_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ volume_rank8_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank8_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + volume_rank9_border_color, 'font-size': volume_rank9_font_size }">
						{{ volume_rank9_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank9_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.volume_rank9_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ volume_rank9_o_c_rate }}</td>
								</template>
								<template v-else-if="this.volume_rank9_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ volume_rank9_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.volume_rank9_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ volume_rank9_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.volume_rank9_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ volume_rank9_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank9_price_volume }}
					</v-col>
				</v-row>
				
				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-left:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + volume_rank10_border_color, 'font-size': volume_rank10_font_size }">
						{{ volume_rank10_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank10_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.volume_rank10_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ volume_rank10_o_c_rate }}</td>
								</template>
								<template v-else-if="this.volume_rank10_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ volume_rank10_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.volume_rank10_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ volume_rank10_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.volume_rank10_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ volume_rank10_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:10px; padding-right:5px; padding-top:15px; color:black; font-size:36px;">
						{{ volume_rank10_price_volume }}
					</v-col>
				</v-row>
			</v-col>
			<v-col xl="6" md="6" sm="6" style="text-align:center; padding-top:0px; padding-left:25px; padding-right:25px;">
				<v-row>
					<v-col xl="8" md="8" sm="8" style="padding-left:20px; text-align:left; font-weight:bold; font-size:32px;">
						빗썸 1시간 변동율 [{{ api_datetime_kst }}]
					</v-col>
					<v-col xl="4" md="4" sm="4" style="padding-right:20px; text-align:right; font-weight:bold; font-size:32px;">
						{{ current_datetime_kst }}
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
						변동율(1시간)
					</v-col>
					<v-col xl="4" md="4" sm="4" style="height: 60px; background-color: rgb(249, 250, 252); color: rgb(102, 102, 102); font-family: 돋움; font-size: 25px; font-weight:bold; padding: 0px; padding-top: 10px;">
						추정 거래대금(1시간)
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + oc_rank1_border_color, 'font-size': oc_rank1_font_size }">
						{{ oc_rank1_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank1_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.oc_rank1_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ oc_rank1_o_c_rate }}</td>
								</template>
								<template v-else-if="this.oc_rank1_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ oc_rank1_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.oc_rank1_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ oc_rank1_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.oc_rank1_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ oc_rank1_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank1_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + oc_rank2_border_color, 'font-size': oc_rank2_font_size }">
						{{ oc_rank2_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank2_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.oc_rank2_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ oc_rank2_o_c_rate }}</td>
								</template>
								<template v-else-if="this.oc_rank2_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ oc_rank2_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.oc_rank2_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ oc_rank2_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.oc_rank2_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ oc_rank2_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank2_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + oc_rank3_border_color, 'font-size': oc_rank3_font_size }">
						{{ oc_rank3_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank3_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.oc_rank3_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ oc_rank3_o_c_rate }}</td>
								</template>
								<template v-else-if="this.oc_rank3_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ oc_rank3_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.oc_rank3_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ oc_rank3_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.oc_rank3_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ oc_rank3_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank3_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + oc_rank4_border_color, 'font-size': oc_rank4_font_size }">
						{{ oc_rank4_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank4_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.oc_rank4_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ oc_rank4_o_c_rate }}</td>
								</template>
								<template v-else-if="this.oc_rank4_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ oc_rank4_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.oc_rank4_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ oc_rank4_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.oc_rank4_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ oc_rank4_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank4_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + oc_rank5_border_color, 'font-size': oc_rank5_font_size }">
						{{ oc_rank5_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank5_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.oc_rank5_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ oc_rank5_o_c_rate }}</td>
								</template>
								<template v-else-if="this.oc_rank5_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ oc_rank5_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.oc_rank5_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ oc_rank5_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.oc_rank5_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ oc_rank5_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank5_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + oc_rank6_border_color, 'font-size': oc_rank6_font_size }">
						{{ oc_rank6_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank6_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.oc_rank6_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ oc_rank6_o_c_rate }}</td>
								</template>
								<template v-else-if="this.oc_rank6_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ oc_rank6_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.oc_rank6_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ oc_rank6_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.oc_rank6_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ oc_rank6_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank6_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + oc_rank7_border_color, 'font-size': oc_rank7_font_size }">
						{{ oc_rank7_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank7_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.oc_rank7_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ oc_rank7_o_c_rate }}</td>
								</template>
								<template v-else-if="this.oc_rank7_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ oc_rank7_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.oc_rank7_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ oc_rank7_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.oc_rank7_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ oc_rank7_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank7_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + oc_rank8_border_color, 'font-size': oc_rank8_font_size }">
						{{ oc_rank8_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank8_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.oc_rank8_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ oc_rank8_o_c_rate }}</td>
								</template>
								<template v-else-if="this.oc_rank8_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ oc_rank8_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.oc_rank8_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ oc_rank8_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.oc_rank8_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ oc_rank8_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank8_price_volume }}
					</v-col>
				</v-row>

				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + oc_rank9_border_color, 'font-size': oc_rank9_font_size }">
						{{ oc_rank9_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank9_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.oc_rank9_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ oc_rank9_o_c_rate }}</td>
								</template>
								<template v-else-if="this.oc_rank9_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ oc_rank9_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.oc_rank9_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ oc_rank9_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.oc_rank9_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ oc_rank9_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank9_price_volume }}
					</v-col>
				</v-row>
				
				<v-row>
					<v-col xl="4" md="4" sm="4" style="text-align: left; padding-right:20px; padding-right:0px; padding-top: 15px; color:black; font-weight:bold; font-size:40px;" v-bind:style="{ border: '3px solid ' + oc_rank10_border_color, 'font-size': oc_rank10_font_size }">
						{{ oc_rank10_coin_name }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="text-align: right; padding-left:0px; padding-right:0px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank10_close }}
					</v-col>
					<v-col xl="2" md="2" sm="2" style="display:flex; justify-content:right; padding-left:0px; padding-right:0px; padding-top: 5px;">
						<table>
							<tr>
								<template v-if="this.oc_rank10_gubun == '상승'">
									<td style="color:red; font-size:28px; text-align:right;">▲&nbsp;{{ oc_rank10_o_c_rate }}</td>
								</template>
								<template v-else-if="this.oc_rank10_gubun == '하락'">
									<td style="color:blue; font-size:28px; text-align:right;">▼&nbsp;{{ oc_rank10_o_c_rate }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:28px; text-align:right;">&nbsp;0.00%</td>
								</template>
								
							</tr>
							<tr>
								<template v-if="this.oc_rank10_gubun == '상승'">
									<td style="color:red; font-size:24px; text-align:right;">▲&nbsp;{{ oc_rank10_o_c_subtract }}</td>
								</template>
								<template v-else-if="this.oc_rank10_gubun == '하락'">
									<td style="color:blue; font-size:24px; text-align:right;">▼&nbsp;{{ oc_rank10_o_c_subtract }}</td>
								</template>
								<template v-else>
									<td style="color:black; font-size:24px; text-align:right;">&nbsp;0</td>
								</template>
							</tr>
						</table>
					</v-col>
					<v-col xl="4" md="4" sm="4" style="text-align: right; padding-left:5px; padding-right:20px; padding-top:15px; color:black; font-size:36px;">
						{{ oc_rank10_price_volume }}
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
			
			api_datetime_kst : '',
			current_datetime_kst : '',

			volume_rank1_coin : '',
			volume_rank1_coin_name : '',
			volume_rank1_border_color : 'white',
			volume_rank1_font_size: '',
			volume_rank1_gubun : '',
			volume_rank1_open : '',
			volume_rank1_low : '',
			volume_rank1_high : '',
			volume_rank1_close : '',
			volume_rank1_o_c_rate : '',
			volume_rank1_o_c_subtract : '',
			volume_rank1_price_volume : '',

			volume_rank2_coin : '',
			volume_rank2_coin_name : '',
			volume_rank2_border_color : 'white',
			volume_rank2_font_size: '',
			volume_rank2_gubun : '',
			volume_rank2_open : '',
			volume_rank2_low : '',
			volume_rank2_high : '',
			volume_rank2_close : '',
			volume_rank2_o_c_rate : '',
			volume_rank2_o_c_subtract : '',
			volume_rank2_price_volume : '',

			volume_rank3_coin : '',
			volume_rank3_coin_name : '',
			volume_rank3_border_color : 'white',
			volume_rank3_font_size: '',
			volume_rank3_gubun : '',
			volume_rank3_open : '',
			volume_rank3_low : '',
			volume_rank3_high : '',
			volume_rank3_close : '',
			volume_rank3_o_c_rate : '',
			volume_rank3_o_c_subtract : '',
			volume_rank3_price_volume : '',

			volume_rank4_coin : '',
			volume_rank4_coin_name : '',
			volume_rank4_border_color : 'white',
			volume_rank4_font_size: '',
			volume_rank4_gubun : '',
			volume_rank4_open : '',
			volume_rank4_low : '',
			volume_rank4_high : '',
			volume_rank4_close : '',
			volume_rank4_o_c_rate : '',
			volume_rank4_o_c_subtract : '',
			volume_rank4_price_volume : '',

			volume_rank5_coin : '',
			volume_rank5_coin_name : '',
			volume_rank5_border_color : 'white',
			volume_rank5_font_size: '',
			volume_rank5_gubun : '',
			volume_rank5_open : '',
			volume_rank5_low : '',
			volume_rank5_high : '',
			volume_rank5_close : '',
			volume_rank5_o_c_rate : '',
			volume_rank5_o_c_subtract : '',
			volume_rank5_price_volume : '',

			volume_rank6_coin : '',
			volume_rank6_coin_name : '',
			volume_rank6_border_color : 'white',
			volume_rank6_font_size: '',
			volume_rank6_gubun : '',
			volume_rank6_open : '',
			volume_rank6_low : '',
			volume_rank6_high : '',
			volume_rank6_close : '',
			volume_rank6_o_c_rate : '',
			volume_rank6_o_c_subtract : '',
			volume_rank6_price_volume : '',

			volume_rank7_coin : '',
			volume_rank7_coin_name : '',
			volume_rank7_border_color : 'white',
			volume_rank7_font_size: '',
			volume_rank7_gubun : '',
			volume_rank7_open : '',
			volume_rank7_low : '',
			volume_rank7_high : '',
			volume_rank7_close : '',
			volume_rank7_o_c_rate : '',
			volume_rank7_o_c_subtract : '',
			volume_rank7_price_volume : '',

			volume_rank8_coin : '',
			volume_rank8_coin_name : '',
			volume_rank8_border_color : 'white',
			volume_rank8_font_size: '',
			volume_rank8_gubun : '',
			volume_rank8_open : '',
			volume_rank8_low : '',
			volume_rank8_high : '',
			volume_rank8_close : '',
			volume_rank8_o_c_rate : '',
			volume_rank8_o_c_subtract : '',
			volume_rank8_price_volume : '',

			volume_rank9_coin : '',
			volume_rank9_coin_name : '',
			volume_rank9_border_color : 'white',
			volume_rank9_font_size: '',
			volume_rank9_gubun : '',
			volume_rank9_open : '',
			volume_rank9_low : '',
			volume_rank9_high : '',
			volume_rank9_close : '',
			volume_rank9_o_c_rate : '',
			volume_rank9_o_c_subtract : '',
			volume_rank9_price_volume : '',

			volume_rank10_coin : '',
			volume_rank10_coin_name : '',
			volume_rank10_border_color : 'white',
			volume_rank10_font_size: '',
			volume_rank10_gubun : '',
			volume_rank10_open : '',
			volume_rank10_low : '',
			volume_rank10_high : '',
			volume_rank10_close : '',
			volume_rank10_o_c_rate : '',
			volume_rank10_o_c_subtract : '',
			volume_rank10_price_volume : '',

			oc_rank1_coin : '',
			oc_rank1_coin_name : '',
			oc_rank1_border_color : 'white',
			oc_rank1_font_size: '',
			oc_rank1_gubun : '',
			oc_rank1_open : '',
			oc_rank1_low : '',
			oc_rank1_high : '',
			oc_rank1_close : '',
			oc_rank1_o_c_rate : '',
			oc_rank1_o_c_subtract : '',
			oc_rank1_price_volume : '',

			oc_rank2_coin : '',
			oc_rank2_coin_name : '',
			oc_rank2_border_color : 'white',
			oc_rank2_font_size: '',
			oc_rank2_gubun : '',
			oc_rank2_open : '',
			oc_rank2_low : '',
			oc_rank2_high : '',
			oc_rank2_close : '',
			oc_rank2_o_c_rate : '',
			oc_rank2_o_c_subtract : '',
			oc_rank2_price_volume : '',

			oc_rank3_coin : '',
			oc_rank3_coin_name : '',
			oc_rank3_border_color : 'white',
			oc_rank3_font_size: '',
			oc_rank3_gubun : '',
			oc_rank3_open : '',
			oc_rank3_low : '',
			oc_rank3_high : '',
			oc_rank3_close : '',
			oc_rank3_o_c_rate : '',
			oc_rank3_o_c_subtract : '',
			oc_rank3_price_volume : '',

			oc_rank4_coin : '',
			oc_rank4_coin_name : '',
			oc_rank4_border_color : 'white',
			oc_rank4_font_size: '',
			oc_rank4_gubun : '',
			oc_rank4_open : '',
			oc_rank4_low : '',
			oc_rank4_high : '',
			oc_rank4_close : '',
			oc_rank4_o_c_rate : '',
			oc_rank4_o_c_subtract : '',
			oc_rank4_price_volume : '',

			oc_rank5_coin : '',
			oc_rank5_coin_name : '',
			oc_rank5_border_color : 'white',
			oc_rank5_font_size: '',
			oc_rank5_gubun : '',
			oc_rank5_open : '',
			oc_rank5_low : '',
			oc_rank5_high : '',
			oc_rank5_close : '',
			oc_rank5_o_c_rate : '',
			oc_rank5_o_c_subtract : '',
			oc_rank5_price_volume : '',

			oc_rank6_coin : '',
			oc_rank6_coin_name : '',
			oc_rank6_border_color : 'white',
			oc_rank6_font_size: '',
			oc_rank6_gubun : '',
			oc_rank6_open : '',
			oc_rank6_low : '',
			oc_rank6_high : '',
			oc_rank6_close : '',
			oc_rank6_o_c_rate : '',
			oc_rank6_o_c_subtract : '',
			oc_rank6_price_volume : '',

			oc_rank7_coin : '',
			oc_rank7_coin_name : '',
			oc_rank7_border_color : 'white',
			oc_rank7_font_size: '',
			oc_rank7_gubun : '',
			oc_rank7_open : '',
			oc_rank7_low : '',
			oc_rank7_high : '',
			oc_rank7_close : '',
			oc_rank7_o_c_rate : '',
			oc_rank7_o_c_subtract : '',
			oc_rank7_price_volume : '',

			oc_rank8_coin : '',
			oc_rank8_coin_name : '',
			oc_rank8_border_color : 'white',
			oc_rank8_font_size: '',
			oc_rank8_gubun : '',
			oc_rank8_open : '',
			oc_rank8_low : '',
			oc_rank8_high : '',
			oc_rank8_close : '',
			oc_rank8_o_c_rate : '',
			oc_rank8_o_c_subtract : '',
			oc_rank8_price_volume : '',

			oc_rank9_coin : '',
			oc_rank9_coin_name : '',
			oc_rank9_border_color : 'white',
			oc_rank9_font_size: '',
			oc_rank9_gubun : '',
			oc_rank9_open : '',
			oc_rank9_low : '',
			oc_rank9_high : '',
			oc_rank9_close : '',
			oc_rank9_o_c_rate : '',
			oc_rank9_o_c_subtract : '',
			oc_rank9_price_volume : '',

			oc_rank10_coin : '',
			oc_rank10_coin_name : '',
			oc_rank10_border_color : 'white',
			oc_rank10_font_size: '',
			oc_rank10_gubun : '',
			oc_rank10_open : '',
			oc_rank10_low : '',
			oc_rank10_high : '',
			oc_rank10_close : '',
			oc_rank10_o_c_rate : '',
			oc_rank10_o_c_subtract : '',
			oc_rank10_price_volume : '',
      	}
    },
	components: {
	},
    mounted() {
		this.Data_MIN_60_Make();
		this.CurrentDataTime();
		//this.Data_Notice();
		setInterval(this.CurrentDataTime.bind(this) , 1000);
	},
	methods: {
		Data_MIN_60_Make(){
			this.RequestCompleteYN = 'N'
			axios({
				url: '/YoutubeBithumb/MIN60_T',
				method: 'get',
				timeout: 60000
			})
			.then(response => {
				if(response.status == 200){
					var list_volume = response.data;
					
					if(list_volume.length < 100 || ((list_volume[0].API_DATETIME_KST) == "undefined") || (list_volume[0].CURRENT_DATETIME_KST) == "undefined"){
						this.RequestCompleteYN = 'Y'
						return
					}

					this.api_datetime_kst = ((list_volume[0].API_DATETIME_KST).substr(0, 16)).replace("20", "");
					this.current_datetime_kst = (list_volume[0].CURRENT_DATETIME_KST).replace("20", "");

					if(this.volume_rank1_coin_name != list_volume[0].Coin_Kor_Name){
						this.volume_rank1_coin = list_volume[0].API_Coin_Ticker;
						this.volume_rank1_coin_name = list_volume[0].Coin_Kor_Name;
						
						if((this.volume_rank1_coin_name).trim().length > 10){
							this.volume_rank1_font_size = ' 32px '
						}else if((this.volume_rank1_coin_name).trim().length == 10){
							this.volume_rank1_font_size = ' 36px '
						}else{
							this.volume_rank1_font_size = ' 40px '
						}

						

						if(this.volume_rank1_coin_name != ''){
							if(list_volume[0].gubun == '상승'){
								this.volume_rank1_border_color = 'red';
							}else if(list_volume[0].gubun == '하락'){
								this.volume_rank1_border_color = 'blue';
							}else if(list_volume[0].gubun == '보합'){
								this.volume_rank1_border_color = 'black';
							}

							setTimeout(() => 
								this.volume_rank1_border_color = 'white'
							, 500);
						}
					}
					this.volume_rank1_gubun = list_volume[0].gubun;
					this.volume_rank1_open = list_volume[0].o_price;
					this.volume_rank1_low = list_volume[0].l_price;
					this.volume_rank1_high = list_volume[0].h_price;
					this.volume_rank1_close = list_volume[0].format_c_price;
					this.volume_rank1_o_c_rate = list_volume[0].o_c_rate;
					this.volume_rank1_o_c_subtract = list_volume[0].o_c_subtract;
					this.volume_rank1_price_volume = list_volume[0].format_volume_price;

					if(this.volume_rank2_coin_name != list_volume[1].Coin_Kor_Name){
						this.volume_rank2_coin = list_volume[1].API_Coin_Ticker;
						this.volume_rank2_coin_name = list_volume[1].Coin_Kor_Name;

						if((this.volume_rank2_coin_name).trim().length > 10){
							this.volume_rank2_font_size = ' 32px '
						}else if((this.volume_rank2_coin_name).trim().length == 10){
							this.volume_rank2_font_size = ' 36px '
						}else{
							this.volume_rank2_font_size = ' 40px '
						}

						if(this.volume_rank2_coin_name != ''){
							if(list_volume[1].gubun == '상승'){
								this.volume_rank2_border_color = 'red';
							}else if(list_volume[1].gubun == '하락'){
								this.volume_rank2_border_color = 'blue';
							}else if(list_volume[1].gubun == '보합'){
								this.volume_rank2_border_color = 'black';
							}

							setTimeout(() => 
								this.volume_rank2_border_color = 'white'
							, 500);
						}
					}
					this.volume_rank2_gubun = list_volume[1].gubun;
					this.volume_rank2_open = list_volume[1].o_price;
					this.volume_rank2_low = list_volume[1].l_price;
					this.volume_rank2_high = list_volume[1].h_price;
					this.volume_rank2_close = list_volume[1].format_c_price;
					this.volume_rank2_o_c_rate = list_volume[1].o_c_rate;
					this.volume_rank2_o_c_subtract = list_volume[1].o_c_subtract;
					this.volume_rank2_price_volume = list_volume[1].format_volume_price;

					if(this.volume_rank3_coin_name != list_volume[2].Coin_Kor_Name){
						this.volume_rank3_coin = list_volume[2].API_Coin_Ticker;
						this.volume_rank3_coin_name = list_volume[2].Coin_Kor_Name;

						if((this.volume_rank3_coin_name).trim().length > 10){
							this.volume_rank3_font_size = ' 32px '
						}else if((this.volume_rank3_coin_name).trim().length == 10){
							this.volume_rank3_font_size = ' 36px '
						}else{
							this.volume_rank3_font_size = ' 40px '
						}

						if(this.volume_rank3_coin_name != ''){
							if(list_volume[2].gubun == '상승'){
								this.volume_rank3_border_color = 'red';
							}else if(list_volume[2].gubun == '하락'){
								this.volume_rank3_border_color = 'blue';
							}else if(list_volume[2].gubun == '보합'){
								this.volume_rank3_border_color = 'black';
							}

							setTimeout(() => 
								this.volume_rank3_border_color = 'white'
							, 500);
						}
					}
					this.volume_rank3_gubun = list_volume[2].gubun;
					this.volume_rank3_open = list_volume[2].o_price;
					this.volume_rank3_low = list_volume[2].l_price;
					this.volume_rank3_high = list_volume[2].h_price;
					this.volume_rank3_close = list_volume[2].format_c_price;
					this.volume_rank3_o_c_rate = list_volume[2].o_c_rate;
					this.volume_rank3_o_c_subtract = list_volume[2].o_c_subtract;
					this.volume_rank3_price_volume = list_volume[2].format_volume_price;

					if(this.volume_rank4_coin_name != list_volume[3].Coin_Kor_Name){
						this.volume_rank4_coin = list_volume[3].API_Coin_Ticker;
						this.volume_rank4_coin_name = list_volume[3].Coin_Kor_Name;

						if((this.volume_rank4_coin_name).trim().length > 10){
							this.volume_rank4_font_size = ' 32px '
						}else if((this.volume_rank4_coin_name).trim().length == 10){
							this.volume_rank4_font_size = ' 36px '
						}else{
							this.volume_rank4_font_size = ' 40px '
						}

						if(this.volume_rank4_coin_name != ''){
							if(list_volume[3].gubun == '상승'){
								this.volume_rank4_border_color = 'red';
							}else if(list_volume[3].gubun == '하락'){
								this.volume_rank4_border_color = 'blue';
							}else if(list_volume[3].gubun == '보합'){
								this.volume_rank4_border_color = 'black';
							}

							setTimeout(() => 
								this.volume_rank4_border_color = 'white'
							, 500);
						}
					}
					this.volume_rank4_gubun = list_volume[3].gubun;
					this.volume_rank4_open = list_volume[3].o_price;
					this.volume_rank4_low = list_volume[3].l_price;
					this.volume_rank4_high = list_volume[3].h_price;
					this.volume_rank4_close = list_volume[3].format_c_price;
					this.volume_rank4_o_c_rate = list_volume[3].o_c_rate;
					this.volume_rank4_o_c_subtract = list_volume[3].o_c_subtract;
					this.volume_rank4_price_volume = list_volume[3].format_volume_price;

					if(this.volume_rank5_coin_name != list_volume[4].Coin_Kor_Name){
						this.volume_rank5_coin = list_volume[4].API_Coin_Ticker;
						this.volume_rank5_coin_name = list_volume[4].Coin_Kor_Name;

						if((this.volume_rank5_coin_name).trim().length > 10){
							this.volume_rank5_font_size = ' 32px '
						}else if((this.volume_rank5_coin_name).trim().length == 10){
							this.volume_rank5_font_size = ' 36px '
						}else{
							this.volume_rank5_font_size = ' 40px '
						}
						
						if(this.volume_rank5_coin_name != ''){
							if(list_volume[4].gubun == '상승'){
								this.volume_rank5_border_color = 'red';
							}else if(list_volume[4].gubun == '하락'){
								this.volume_rank5_border_color = 'blue';
							}else if(list_volume[4].gubun == '보합'){
								this.volume_rank5_border_color = 'black';
							}

							setTimeout(() => 
								this.volume_rank5_border_color = 'white'
							, 500);
						}
					}
					this.volume_rank5_gubun = list_volume[4].gubun;
					this.volume_rank5_open = list_volume[4].o_price;
					this.volume_rank5_low = list_volume[4].l_price;
					this.volume_rank5_high = list_volume[4].h_price;
					this.volume_rank5_close = list_volume[4].format_c_price;
					this.volume_rank5_o_c_rate = list_volume[4].o_c_rate;
					this.volume_rank5_o_c_subtract = list_volume[4].o_c_subtract;
					this.volume_rank5_price_volume = list_volume[4].format_volume_price;

					if(this.volume_rank6_coin_name != list_volume[5].Coin_Kor_Name){
						this.volume_rank6_coin = list_volume[5].API_Coin_Ticker;
						this.volume_rank6_coin_name = list_volume[5].Coin_Kor_Name;
						
						if((this.volume_rank6_coin_name).trim().length > 10){
							this.volume_rank6_font_size = ' 32px '
						}else if((this.volume_rank6_coin_name).trim().length == 10){
							this.volume_rank6_font_size = ' 36px '
						}else{
							this.volume_rank6_font_size = ' 40px '
						}

						if(this.volume_rank6_coin_name != ''){
							if(list_volume[5].gubun == '상승'){
								this.volume_rank6_border_color = 'red';
							}else if(list_volume[5].gubun == '하락'){
								this.volume_rank6_border_color = 'blue';
							}else if(list_volume[5].gubun == '보합'){
								this.volume_rank6_border_color = 'black';
							}

							setTimeout(() => 
								this.volume_rank6_border_color = 'white'
							, 500);
						}
					}
					this.volume_rank6_gubun = list_volume[5].gubun;
					this.volume_rank6_open = list_volume[5].o_price;
					this.volume_rank6_low = list_volume[5].l_price;
					this.volume_rank6_high = list_volume[5].h_price;
					this.volume_rank6_close = list_volume[5].format_c_price;
					this.volume_rank6_o_c_rate = list_volume[5].o_c_rate;
					this.volume_rank6_o_c_subtract = list_volume[5].o_c_subtract;
					this.volume_rank6_price_volume = list_volume[5].format_volume_price;

					if(this.volume_rank7_coin_name != list_volume[6].Coin_Kor_Name){
						this.volume_rank7_coin = list_volume[6].API_Coin_Ticker;
						this.volume_rank7_coin_name = list_volume[6].Coin_Kor_Name;

						if((this.volume_rank7_coin_name).trim().length > 10){
							this.volume_rank7_font_size = ' 32px '
						}else if((this.volume_rank7_coin_name).trim().length == 10){
							this.volume_rank7_font_size = ' 36px '
						}else{
							this.volume_rank7_font_size = ' 40px '
						}

						if(this.volume_rank7_coin_name != ''){
							if(list_volume[6].gubun == '상승'){
								this.volume_rank7_border_color = 'red';
							}else if(list_volume[6].gubun == '하락'){
								this.volume_rank7_border_color = 'blue';
							}else if(list_volume[6].gubun == '보합'){
								this.volume_rank7_border_color = 'black';
							}

							setTimeout(() => 
								this.volume_rank7_border_color = 'white'
							, 500);
						}
					}
					this.volume_rank7_gubun = list_volume[6].gubun;
					this.volume_rank7_open = list_volume[6].o_price;
					this.volume_rank7_low = list_volume[6].l_price;
					this.volume_rank7_high = list_volume[6].h_price;
					this.volume_rank7_close = list_volume[6].format_c_price;
					this.volume_rank7_o_c_rate = list_volume[6].o_c_rate;
					this.volume_rank7_o_c_subtract = list_volume[6].o_c_subtract;
					this.volume_rank7_price_volume = list_volume[6].format_volume_price;

					if(this.volume_rank8_coin_name != list_volume[7].Coin_Kor_Name){
						this.volume_rank8_coin = list_volume[7].API_Coin_Ticker;
						this.volume_rank8_coin_name = list_volume[7].Coin_Kor_Name;

						if((this.volume_rank8_coin_name).trim().length > 10){
							this.volume_rank8_font_size = ' 32px '
						}else if((this.volume_rank8_coin_name).trim().length == 10){
							this.volume_rank8_font_size = ' 36px '
						}else{
							this.volume_rank8_font_size = ' 40px '
						}

						if(this.volume_rank8_coin_name != ''){
							if(list_volume[7].gubun == '상승'){
								this.volume_rank8_border_color = 'red';
							}else if(list_volume[7].gubun == '하락'){
								this.volume_rank8_border_color = 'blue';
							}else if(list_volume[7].gubun == '보합'){
								this.volume_rank8_border_color = 'black';
							}

							setTimeout(() => 
								this.volume_rank8_border_color = 'white'
							, 500);
						}
					}
					this.volume_rank8_gubun = list_volume[7].gubun;
					this.volume_rank8_open = list_volume[7].o_price;
					this.volume_rank8_low = list_volume[7].l_price;
					this.volume_rank8_high = list_volume[7].h_price;
					this.volume_rank8_close = list_volume[7].format_c_price;
					this.volume_rank8_o_c_rate = list_volume[7].o_c_rate;
					this.volume_rank8_o_c_subtract = list_volume[7].o_c_subtract;
					this.volume_rank8_price_volume = list_volume[7].format_volume_price;

					if(this.volume_rank9_coin_name != list_volume[8].Coin_Kor_Name){
						this.volume_rank9_coin = list_volume[8].API_Coin_Ticker;
						this.volume_rank9_coin_name = list_volume[8].Coin_Kor_Name;

						if((this.volume_rank9_coin_name).trim().length > 10){
							this.volume_rank9_font_size = ' 32px '
						}else if((this.volume_rank9_coin_name).trim().length == 10){
							this.volume_rank9_font_size = ' 36px '
						}else{
							this.volume_rank9_font_size = ' 40px '
						}

						if(this.volume_rank9_coin_name != ''){
							if(list_volume[8].gubun == '상승'){
								this.volume_rank9_border_color = 'red';
							}else if(list_volume[8].gubun == '하락'){
								this.volume_rank9_border_color = 'blue';
							}else if(list_volume[8].gubun == '보합'){
								this.volume_rank9_border_color = 'black';
							}

							setTimeout(() => 
								this.volume_rank9_border_color = 'white'
							, 500);
						}
					}
					this.volume_rank9_gubun = list_volume[8].gubun;
					this.volume_rank9_open = list_volume[8].o_price;
					this.volume_rank9_low = list_volume[8].l_price;
					this.volume_rank9_high = list_volume[8].h_price;
					this.volume_rank9_close = list_volume[8].format_c_price;
					this.volume_rank9_o_c_rate = list_volume[8].o_c_rate;
					this.volume_rank9_o_c_subtract = list_volume[8].o_c_subtract;
					this.volume_rank9_price_volume = list_volume[8].format_volume_price;

					if(this.volume_rank10_coin_name != list_volume[9].Coin_Kor_Name){
						this.volume_rank10_coin = list_volume[9].API_Coin_Ticker;
						this.volume_rank10_coin_name = list_volume[9].Coin_Kor_Name;

						if((this.volume_rank10_coin_name).trim().length > 10){
							this.volume_rank10_font_size = ' 32px '
						}else if((this.volume_rank10_coin_name).trim().length == 10){
							this.volume_rank10_font_size = ' 36px '
						}else{
							this.volume_rank10_font_size = ' 40px '
						}

						if(this.volume_rank10_coin_name != ''){
							if(list_volume[9].gubun == '상승'){
								this.volume_rank10_border_color = 'red';
							}else if(list_volume[9].gubun == '하락'){
								this.volume_rank10_border_color = 'blue';
							}else if(list_volume[9].gubun == '보합'){
								this.volume_rank10_border_color = 'black';
							}

							setTimeout(() => 
								this.volume_rank10_border_color = 'white'
							, 500);
						}
					}
					this.volume_rank10_gubun = list_volume[9].gubun;
					this.volume_rank10_open = list_volume[9].o_price;
					this.volume_rank10_low = list_volume[9].l_price;
					this.volume_rank10_high = list_volume[9].h_price;
					this.volume_rank10_close = list_volume[9].format_c_price;
					this.volume_rank10_o_c_rate = list_volume[9].o_c_rate;
					this.volume_rank10_o_c_subtract = list_volume[9].o_c_subtract;
					this.volume_rank10_price_volume = list_volume[9].format_volume_price;

					var list_oc = list_volume.sort((a,b) => Number(b.o_c_rate.replace('%', '')) - Number(a.o_c_rate.replace('%', '')));
					
					if(this.oc_rank1_coin_name != list_oc[0].Coin_Kor_Name){
						this.oc_rank1_coin = list_oc[0].API_Coin_Ticker;
						this.oc_rank1_coin_name = list_oc[0].Coin_Kor_Name;

						if((this.oc_rank1_coin_name).trim().length > 10){
							this.oc_rank1_font_size = ' 32px '
						}else if((this.oc_rank1_coin_name).trim().length == 10){
							this.oc_rank1_font_size = ' 36px '
						}else{
							this.oc_rank1_font_size = ' 40px '
						}

						if(this.oc_rank1_coin_name != ''){
							if(list_oc[0].gubun == '상승'){
								this.oc_rank1_border_color = 'red';
							}else if(list_oc[0].gubun == '하락'){
								this.oc_rank1_border_color = 'blue';
							}else if(list_oc[0].gubun == '보합'){
								this.oc_rank1_border_color = 'black';
							}

							setTimeout(() => 
								this.oc_rank1_border_color = 'white'
							, 500);
						}
					}
					this.oc_rank1_gubun = list_oc[0].gubun;
					this.oc_rank1_open = list_oc[0].o_price;
					this.oc_rank1_low = list_oc[0].l_price;
					this.oc_rank1_high = list_oc[0].h_price;
					this.oc_rank1_close = list_oc[0].format_c_price;
					this.oc_rank1_o_c_rate = list_oc[0].o_c_rate;
					this.oc_rank1_o_c_subtract = list_oc[0].o_c_subtract;
					this.oc_rank1_price_volume = list_oc[0].format_volume_price;

					if(this.oc_rank2_coin_name != list_oc[1].Coin_Kor_Name){
						this.oc_rank2_coin = list_oc[1].API_Coin_Ticker;
						this.oc_rank2_coin_name = list_oc[1].Coin_Kor_Name;

						if((this.oc_rank2_coin_name).trim().length > 10){
							this.oc_rank2_font_size = ' 32px '
						}else if((this.oc_rank2_coin_name).trim().length == 10){
							this.oc_rank2_font_size = ' 36px '
						}else{
							this.oc_rank2_font_size = ' 40px '
						}

						if(this.oc_rank2_coin_name != ''){
							if(list_oc[1].gubun == '상승'){
								this.oc_rank2_border_color = 'red';
							}else if(list_oc[1].gubun == '하락'){
								this.oc_rank2_border_color = 'blue';
							}else if(list_oc[1].gubun == '보합'){
								this.oc_rank2_border_color = 'black';
							}

							setTimeout(() => 
								this.oc_rank2_border_color = 'white'
							, 500);
						}
					}
					this.oc_rank2_gubun = list_oc[1].gubun;
					this.oc_rank2_open = list_oc[1].o_price;
					this.oc_rank2_low = list_oc[1].l_price;
					this.oc_rank2_high = list_oc[1].h_price;
					this.oc_rank2_close = list_oc[1].format_c_price;
					this.oc_rank2_o_c_rate = list_oc[1].o_c_rate;
					this.oc_rank2_o_c_subtract = list_oc[1].o_c_subtract;
					this.oc_rank2_price_volume = list_oc[1].format_volume_price;

					if(this.oc_rank3_coin_name != list_oc[2].Coin_Kor_Name){
						this.oc_rank3_coin = list_oc[2].API_Coin_Ticker;
						this.oc_rank3_coin_name = list_oc[2].Coin_Kor_Name;

						if((this.oc_rank3_coin_name).trim().length > 10){
							this.oc_rank3_font_size = ' 32px '
						}else if((this.oc_rank3_coin_name).trim().length == 10){
							this.oc_rank3_font_size = ' 36px '
						}else{
							this.oc_rank3_font_size = ' 40px '
						}

						if(this.oc_rank3_coin_name != ''){
							if(list_oc[2].gubun == '상승'){
								this.oc_rank3_border_color = 'red';
							}else if(list_oc[2].gubun == '하락'){
								this.oc_rank3_border_color = 'blue';
							}else if(list_oc[2].gubun == '보합'){
								this.oc_rank3_border_color = 'black';
							}

							setTimeout(() => 
								this.oc_rank3_border_color = 'white'
							, 500);
						}
					}
					this.oc_rank3_gubun = list_oc[2].gubun;
					this.oc_rank3_open = list_oc[2].o_price;
					this.oc_rank3_low = list_oc[2].l_price;
					this.oc_rank3_high = list_oc[2].h_price;
					this.oc_rank3_close = list_oc[2].format_c_price;
					this.oc_rank3_o_c_rate = list_oc[2].o_c_rate;
					this.oc_rank3_o_c_subtract = list_oc[2].o_c_subtract;
					this.oc_rank3_price_volume = list_oc[2].format_volume_price;
					
					if(this.oc_rank4_coin_name != list_oc[3].Coin_Kor_Name){
						this.oc_rank4_coin = list_oc[3].API_Coin_Ticker;
						this.oc_rank4_coin_name = list_oc[3].Coin_Kor_Name;

						if((this.oc_rank4_coin_name).trim().length > 10){
							this.oc_rank4_font_size = ' 32px '
						}else if((this.oc_rank4_coin_name).trim().length == 10){
							this.oc_rank4_font_size = ' 36px '
						}else{
							this.oc_rank4_font_size = ' 40px '
						}
						
						if(this.oc_rank4_coin_name != ''){
							if(list_oc[3].gubun == '상승'){
								this.oc_rank4_border_color = 'red';
							}else if(list_oc[3].gubun == '하락'){
								this.oc_rank4_border_color = 'blue';
							}else if(list_oc[3].gubun == '보합'){
								this.oc_rank4_border_color = 'black';
							}

							setTimeout(() => 
								this.oc_rank4_border_color = 'white'
							, 500);
						}
					}
					this.oc_rank4_gubun = list_oc[3].gubun;
					this.oc_rank4_open = list_oc[3].o_price;
					this.oc_rank4_low = list_oc[3].l_price;
					this.oc_rank4_high = list_oc[3].h_price;
					this.oc_rank4_close = list_oc[3].format_c_price;
					this.oc_rank4_o_c_rate = list_oc[3].o_c_rate;
					this.oc_rank4_o_c_subtract = list_oc[3].o_c_subtract;
					this.oc_rank4_price_volume = list_oc[3].format_volume_price;

					if(this.oc_rank5_coin_name != list_oc[4].Coin_Kor_Name){
						this.oc_rank5_coin = list_oc[4].API_Coin_Ticker;
						this.oc_rank5_coin_name = list_oc[4].Coin_Kor_Name;

						if((this.oc_rank5_coin_name).trim().length > 10){
							this.oc_rank5_font_size = ' 32px '
						}else if((this.oc_rank5_coin_name).trim().length == 10){
							this.oc_rank5_font_size = ' 36px '
						}else{
							this.oc_rank5_font_size = ' 40px '
						}

						if(this.oc_rank5_coin_name != ''){
							if(list_oc[4].gubun == '상승'){
								this.oc_rank5_border_color = 'red';
							}else if(list_oc[4].gubun == '하락'){
								this.oc_rank5_border_color = 'blue';
							}else if(list_oc[4].gubun == '보합'){
								this.oc_rank5_border_color = 'black';
							}

							setTimeout(() => 
								this.oc_rank5_border_color = 'white'
							, 500);
						}
					}
					this.oc_rank5_gubun = list_oc[4].gubun;
					this.oc_rank5_open = list_oc[4].o_price;
					this.oc_rank5_low = list_oc[4].l_price;
					this.oc_rank5_high = list_oc[4].h_price;
					this.oc_rank5_close = list_oc[4].format_c_price;
					this.oc_rank5_o_c_rate = list_oc[4].o_c_rate;
					this.oc_rank5_o_c_subtract = list_oc[4].o_c_subtract;
					this.oc_rank5_price_volume = list_oc[4].format_volume_price;

					if(this.oc_rank6_coin_name != list_oc[5].Coin_Kor_Name){
						this.oc_rank6_coin = list_oc[5].API_Coin_Ticker;
						this.oc_rank6_coin_name = list_oc[5].Coin_Kor_Name;

						if((this.oc_rank6_coin_name).trim().length > 10){
							this.oc_rank6_font_size = ' 32px '
						}else if((this.oc_rank6_coin_name).trim().length == 10){
							this.oc_rank6_font_size = ' 36px '
						}else{
							this.oc_rank6_font_size = ' 40px '
						}

						if(this.oc_rank6_coin_name != ''){
							if(list_oc[5].gubun == '상승'){
								this.oc_rank6_border_color = 'red';
							}else if(list_oc[5].gubun == '하락'){
								this.oc_rank6_border_color = 'blue';
							}else if(list_oc[5].gubun == '보합'){
								this.oc_rank6_border_color = 'black';
							}

							setTimeout(() => 
								this.oc_rank6_border_color = 'white'
							, 500);
						}
					}
					this.oc_rank6_gubun = list_oc[5].gubun;
					this.oc_rank6_open = list_oc[5].o_price;
					this.oc_rank6_low = list_oc[5].l_price;
					this.oc_rank6_high = list_oc[5].h_price;
					this.oc_rank6_close = list_oc[5].format_c_price;
					this.oc_rank6_o_c_rate = list_oc[5].o_c_rate;
					this.oc_rank6_o_c_subtract = list_oc[5].o_c_subtract;
					this.oc_rank6_price_volume = list_oc[5].format_volume_price;

					if(this.oc_rank7_coin_name != list_oc[6].Coin_Kor_Name){
						this.oc_rank7_coin = list_oc[6].API_Coin_Ticker;
						this.oc_rank7_coin_name = list_oc[6].Coin_Kor_Name;

						if((this.oc_rank7_coin_name).trim().length > 10){
							this.oc_rank7_font_size = ' 32px '
						}else if((this.oc_rank7_coin_name).trim().length == 10){
							this.oc_rank7_font_size = ' 36px '
						}else{
							this.oc_rank7_font_size = ' 40px '
						}

						if(this.oc_rank7_coin_name != ''){
							if(list_oc[6].gubun == '상승'){
								this.oc_rank7_border_color = 'red';
							}else if(list_oc[6].gubun == '하락'){
								this.oc_rank7_border_color = 'blue';
							}else if(list_oc[6].gubun == '보합'){
								this.oc_rank7_border_color = 'black';
							}

							setTimeout(() => 
								this.oc_rank7_border_color = 'white'
							, 500);
						}
					}
					this.oc_rank7_gubun = list_oc[6].gubun;
					this.oc_rank7_open = list_oc[6].o_price;
					this.oc_rank7_low = list_oc[6].l_price;
					this.oc_rank7_high = list_oc[6].h_price;
					this.oc_rank7_close = list_oc[6].format_c_price;
					this.oc_rank7_o_c_rate = list_oc[6].o_c_rate;
					this.oc_rank7_o_c_subtract = list_oc[6].o_c_subtract;
					this.oc_rank7_price_volume = list_oc[6].format_volume_price;

					if(this.oc_rank8_coin_name != list_oc[7].Coin_Kor_Name){
						this.oc_rank8_coin = list_oc[7].API_Coin_Ticker;
						this.oc_rank8_coin_name = list_oc[7].Coin_Kor_Name;

						if((this.oc_rank8_coin_name).trim().length > 10){
							this.oc_rank8_font_size = ' 32px '
						}else if((this.oc_rank8_coin_name).trim().length == 10){
							this.oc_rank8_font_size = ' 36px '
						}else{
							this.oc_rank8_font_size = ' 40px '
						}

						if(this.oc_rank8_coin_name != ''){
							if(list_oc[7].gubun == '상승'){
								this.oc_rank8_border_color = 'red';
							}else if(list_oc[7].gubun == '하락'){
								this.oc_rank8_border_color = 'blue';
							}else if(list_oc[7].gubun == '보합'){
								this.oc_rank8_border_color = 'black';
							}

							setTimeout(() => 
								this.oc_rank8_border_color = 'white'
							, 500);
						}
					}
					this.oc_rank8_gubun = list_oc[7].gubun;
					this.oc_rank8_open = list_oc[7].o_price;
					this.oc_rank8_low = list_oc[7].l_price;
					this.oc_rank8_high = list_oc[7].h_price;
					this.oc_rank8_close = list_oc[7].format_c_price;
					this.oc_rank8_o_c_rate = list_oc[7].o_c_rate;
					this.oc_rank8_o_c_subtract = list_oc[7].o_c_subtract;
					this.oc_rank8_price_volume = list_oc[7].format_volume_price;

					if(this.oc_rank9_coin_name != list_oc[8].Coin_Kor_Name){
						this.oc_rank9_coin = list_oc[8].API_Coin_Ticker;
						this.oc_rank9_coin_name = list_oc[8].Coin_Kor_Name;

						if((this.oc_rank9_coin_name).trim().length > 10){
							this.oc_rank9_font_size = ' 32px '
						}else if((this.oc_rank9_coin_name).trim().length == 10){
							this.oc_rank9_font_size = ' 36px '
						}else{
							this.oc_rank9_font_size = ' 40px '
						}

						if(this.oc_rank9_coin_name != ''){
							if(list_oc[8].gubun == '상승'){
								this.oc_rank9_border_color = 'red';
							}else if(list_oc[8].gubun == '하락'){
								this.oc_rank9_border_color = 'blue';
							}else if(list_oc[8].gubun == '보합'){
								this.oc_rank9_border_color = 'black';
							}

							setTimeout(() => 
								this.oc_rank9_border_color = 'white'
							, 500);
						}
					}
					this.oc_rank9_gubun = list_oc[8].gubun;
					this.oc_rank9_open = list_oc[8].o_price;
					this.oc_rank9_low = list_oc[8].l_price;
					this.oc_rank9_high = list_oc[8].h_price;
					this.oc_rank9_close = list_oc[8].format_c_price;
					this.oc_rank9_o_c_rate = list_oc[8].o_c_rate;
					this.oc_rank9_o_c_subtract = list_oc[8].o_c_subtract;
					this.oc_rank9_price_volume = list_oc[8].format_volume_price;

					if(this.oc_rank10_coin_name != list_oc[9].Coin_Kor_Name){
						this.oc_rank10_coin = list_oc[9].API_Coin_Ticker;
						this.oc_rank10_coin_name = list_oc[9].Coin_Kor_Name;

						if((this.oc_rank10_coin_name).trim().length > 10){
							this.oc_rank10_font_size = ' 32px '
						}else if((this.oc_rank10_coin_name).trim().length == 10){
							this.oc_rank10_font_size = ' 36px '
						}else{
							this.oc_rank10_font_size = ' 40px '
						}

						if(this.oc_rank10_coin_name != ''){
							if(list_oc[9].gubun == '상승'){
								this.oc_rank10_border_color = 'red';
							}else if(list_oc[9].gubun == '하락'){
								this.oc_rank10_border_color = 'blue';
							}else if(list_oc[9].gubun == '보합'){
								this.oc_rank10_border_color = 'black';
							}

							setTimeout(() => 
								this.oc_rank10_border_color = 'white'
							, 500);
						}
					}
					this.oc_rank10_gubun = list_oc[9].gubun;
					this.oc_rank10_open = list_oc[9].o_price;
					this.oc_rank10_low = list_oc[9].l_price;
					this.oc_rank10_high = list_oc[9].h_price;
					this.oc_rank10_close = list_oc[9].format_c_price;
					this.oc_rank10_o_c_rate = list_oc[9].o_c_rate;
					this.oc_rank10_o_c_subtract = list_oc[9].o_c_subtract;
					this.oc_rank10_price_volume = list_oc[9].format_volume_price;

					this.RequestCompleteYN = 'Y'
				}else{
					this.RequestCompleteYN = 'Y'
				}
			})
			.catch(error =>{
				this.RequestCompleteYN = 'Y'
			})
		},
		CurrentDataTime() {
			var mi = new Date().getMinutes() < 10? "0" + new Date().getMinutes(): new Date().getMinutes();
			var ss = new Date().getSeconds() < 10? "0" + new Date().getSeconds(): new Date().getSeconds();
			if(Number(ss) == 0){
				//this.Data_Notice();
				//this.Data_MIN_60_Make();
			}

			if(this.RequestCompleteYN == 'Y' && (Number(ss) == 55 || Number(ss) == 25)){
				this.Data_MIN_60_Make();
			}

			if(this.current_datetime_kst != ''){
				var data_mi = (this.current_datetime_kst).substr(12, 2)
				
				if(Number(mi) == 58 || Number(mi) == 59 || Number(mi) == 0 || Number(mi) == 1){
					if((Number(mi) == 58 && Number(data_mi) >= 0) || (Number(mi) == 58 && Number(data_mi) <= 56) || 
						(Number(mi) == 59 && Number(data_mi) >= 1) || (Number(mi) == 59 && Number(data_mi) <= 57) || 
						(Number(mi) == 0 && Number(data_mi) >= 2) || (Number(mi) == 0 && Number(data_mi) <= 58) || 
						(Number(mi) == 1 && Number(data_mi) >= 3) || (Number(mi) == 1 && Number(data_mi) <= 59)){
						this.Data_MIN_60_Make();
					}
				}else if((Number(mi) < Number(data_mi)-1) || (Number(mi) > Number(data_mi)+1)){
					this.Data_MIN_60_Make();
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