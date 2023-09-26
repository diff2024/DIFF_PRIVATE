import Vue from 'vue'
import App from './App.vue'
import { router } from './router'
import vuetify from './plugins/vuetify'
import VuetifyDialog from 'vuetify-dialog'
import VueMobileDetection from 'vue-mobile-detection' 
import VueSession from 'vue-session'
import Print from 'vue-print-nb'
import message from './message.js';
import numkeyboard from 'vue-numkeyboard';
import 'vue-numkeyboard/style.css';

Vue.config.productionTip = false

// Native 로 메시지를 보내는 함수를 플러그인으로 등록한다.
Vue.use(message);
//모바일 숫자 키패드
Vue.use(numkeyboard);

// window 객체에 해당 함수를 추가한다.
window.sendMessage = message;

var sessionOptions = {
  persist: true
}

Vue.use(VueSession, sessionOptions)
Vue.use(VueMobileDetection)
Vue.use(VuetifyDialog, {
  context: {
    vuetify
  }
})
Vue.use(Print)

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')
