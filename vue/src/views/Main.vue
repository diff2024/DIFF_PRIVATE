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
		  dialog: false
      }
    },
	components: {
	},
    mounted() {
	    //if (!this.$session.exists()) {
		//	this.dialog = true
		//}
	},
	methods: {
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