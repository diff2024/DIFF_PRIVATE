<script>
	import axios from 'axios';
</script>
<script src="https://unpkg.com/vue"></script>
<template>
  <div>
	<v-flex xs12 sm12 md12>
		<v-row>
			<v-col xl="12" md="12" sm="12" style="text-align:center; padding-top:40px;">
				<div id="upbit_hour_graph_script"></div>
				<div style="text-align:left; padding:0px;" id="upbit_hour_rank1_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_hour_rank2_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_hour_rank3_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_hour_rank4_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_hour_rank5_graph"></div>
			</v-col>
		</v-row>
		<v-row>
			<v-col xl="12" md="12" sm="12" style="text-align:center; padding-top:40px;">
				<div id="upbit_4hour_graph_script"></div>
				<div style="text-align:left; padding:0px;" id="upbit_4hour_rank1_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_4hour_rank2_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_4hour_rank3_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_4hour_rank4_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_4hour_rank5_graph"></div>
			</v-col>
		</v-row>
		<v-row>
			<v-col xl="12" md="12" sm="12" style="text-align:center; padding-top:40px;">
				<div id="upbit_day_graph_script"></div>
				<div style="text-align:left; padding:0px;" id="upbit_day_rank_1_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_day_rank_21_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_day_rank_41_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_day_rank_61_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_day_rank_81_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_day_rank_101_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_day_rank_121_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_day_rank_141_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_day_rank_161_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_day_rank_181_graph"></div>
				<div style="text-align:left; padding:0px;" id="upbit_day_rank_201_graph"></div>
			</v-col>
		</v-row>
	</v-flex>
  </div>
</template>
<script>
export default {
	data () {
      	return {
			
      	}
    },
	components: {
		
	},
    mounted() {
		var head = document.getElementById("upbit_hour_graph_script");
		var script = document.createElement('script');
		script.type = 'application/javascript';
		script.src = './upbit_hour_graph_script.js';
		head.appendChild(script)

		var head2 = document.getElementById("upbit_4hour_graph_script");
		var script2 = document.createElement('script');
		script2.type = 'application/javascript';
		script2.src = './upbit_4hour_graph_script.js';
		head2.appendChild(script2)

		var head3 = document.getElementById("upbit_day_graph_script");
		var script3 = document.createElement('script');
		script3.type = 'application/javascript';
		script3.src = './upbit_day_graph_script.js';
		head3.appendChild(script3)
	},
	methods: {
		
	}
}


</script>