<template>
  <div>
    <v-dialog v-model="dialog" persistent max-width="500">
      <v-card>
        <v-card-title style="font-weight:bold; font-size:28px; padding-bottom:20px;">
          DIFF
        </v-card-title>
        <v-card-text style="padding-bottom:20px; padding-left:45px; padding-right:45px;">
        	<v-text-field label="ID" height="50px" style="font-size:26px;" v-model="username"/>
    		<v-text-field label="Password" height="50px" style="font-size:26px;" type="password" v-model="password" v-on:keyup.enter="LoginConfirm" />
		</v-card-text>
        <v-card-actions style="padding-top:0px;">
          <v-spacer></v-spacer>
          <v-btn color="black darken-1" style="font-weight:bold; font-size:24px;" text v-on:click="LoginConfirm">
            로그인
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
	<v-flex xs12 sm12 md12>
		<v-row>
			<v-col xl="5" md="5" sm="5" style="text-align:center; padding-top:40px;">
				<Upbit5M :key="Upbit5MKey" />
			</v-col>
			<v-col xl="2" md="2" sm="2" style="text-align:center; padding-top:40px;">
				<img width="320px" height="240px" src="@/assets/advertise/TOP_AD.jpg" />
				<img width="320px" height="240px" src="@/assets/advertise/TOP_AD.jpg" />
			</v-col>
			<v-col xl="5" md="5" sm="5" style="text-align:center; padding-top:40px;">
				<Upbit15M :key="Upbit15MKey" />
			</v-col>
		</v-row>
		<v-row>
			<v-col xl="5" md="5" sm="5" style="text-align:center; padding-top:40px;">
				<Upbit60M :key="Upbit60MKey" />
			</v-col>
			<v-col xl="2" md="2" sm="2" style="text-align:center; padding-top:40px;">
				<img width="320px" height="240px" src="@/assets/advertise/TOP_AD.jpg" />
				<img width="320px" height="240px" src="@/assets/advertise/TOP_AD.jpg" />
			</v-col>
			<v-col xl="5" md="5" sm="5" style="text-align:center; padding-top:40px;">
				<Upbit240M  :key="Upbit240MKey" />
			</v-col>
		</v-row>
	</v-flex>
  </div>
</template>
<script src="https://unpkg.com/vue"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script>
import axios from 'axios';
import Upbit5M from '../components/upbit_5m.vue';
import Upbit15M from '../components/upbit_15m.vue';
import Upbit60M from '../components/upbit_60m.vue';
import Upbit240M from '../components/upbit_240m.vue';

export default {
	data () {
      	return {
			username: '',
			password: '',
			dialog: false,
			Upbit5MKey: 0,
			Upbit15MKey: 0,
			Upbit60MKey: 0,
			Upbit240MKey: 0,
      	}
    },
	components: {
		Upbit5M,
		Upbit15M,
		Upbit60M,
		Upbit240M
	},
    mounted() {
		this.CurrentDataTime();
		setInterval(this.CurrentDataTime.bind(this) , 1000);
	},
	methods: {
		CurrentDataTime() {
			let hh = new Date().getHours() < 10? "0" + new Date().getHours(): new Date().getHours();
			let mm = new Date().getMinutes() < 10? "0" + new Date().getMinutes(): new Date().getMinutes();
			let ss = new Date().getSeconds() < 10? "0" + new Date().getSeconds(): new Date().getSeconds();

			if(Number(ss) == 5){
				Upbit5MRerender();
				Upbit15MRerender();
			}
		},
		Upbit5MRerender() {
			this.Upbit5MKey += 1;  
		},
		Upbit15MRerender() {
			this.Upbit15MKey += 1;  
		},
		Upbit60MRerender() {
			this.Upbit60MKey += 1;  
		},
		Upbit240MRerender() {
			this.Upbit240MKey += 1;  
		},
		LoginConfirm() {
		}
		/*
		LoginConfirm() {
			if(this.username == '' || this.password == ''){
				alert('ID와 비밀번호를 입력해주시기 바랍니다.');
				return;
			}
		
			axios.post('/back/Login/Login', null, { params: {
				clm_user_id: this.username,
				clm_user_pw: this.password
			}})
			.then(response => {
				if (response.status == '200' && response.data.clm_user_id != '') {
					this.$session.start();
					this.$session.set('user_id', response.data.clm_user_id);
					this.$session.set('user_name', response.data.clm_user_name);
					location.reload();
				}else{
					alert('ID와 패스워드를 확인해주시기 바랍니다.');
					this.$session.destroy();
				}
			})
			.catch(error => {
				console.log(error)
			})
		}
		*/
	}
}
</script>