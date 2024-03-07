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
	.animated-title .notice_content {-webkit-transform: translateY(calc(100% - 8rem)); transform: translateY(calc(100% - 8rem));}
}
</style>
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
			<v-col xl="12" md="12" sm="12" style="height:1px; padding-top:0px;"></v-col>
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
			
      	}
    },
	components: {
	},
    mounted() {
		this.CurrentDataTime();
	},
	methods: {
		CurrentDataTime() {
			let mi = new Date().getMinutes() < 10? "0" + new Date().getMinutes(): new Date().getMinutes();
			let ss = new Date().getSeconds() < 10? "0" + new Date().getSeconds(): new Date().getSeconds();
			
			if(Number(mi) == 0 && Number(ss) == 0){
				this.Data_Notice();
			}

			if(ss == 3 || ss == 8 || ss == 13 || ss == 18 || ss == 23 || ss == 28 || ss == 33 || ss == 38 || ss == 43 || ss == 48 || ss == 53 || ss == 58){
				//this.Data_MIN5_Make();
				//this.Data_MIN15_Make();
				//this.Data_MIN60_Make();
				//this.Data_MIN240_Make();
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
		},
		LoginConfirm() {
		},
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