import Vue from 'vue'
import Router from 'vue-router'
import axios from 'axios'
import VueSession from 'vue-session'

Vue.use(Router)
var sessionOptions = {
  persist: true
}

Vue.use(VueSession, sessionOptions)

const Main = () => {
	return import("./views/Main.vue")
}
const CoinMainList = () => {
	return import("./views/Coin/CoinMainList.vue")	
}
const BithumbCoinMainList = () => {
	return import("./views/Coin/BithumbCoinMainList.vue")	
}

export const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes : [
    {
      path: '/',
      name: 'main',
      component: Main,
    },
    {
      path: '/CoinMainList',
      name: 'coinMainList',
      component: CoinMainList
    },
    {
      path: '/BithumbCoinMainList',
      name: 'bithumbcoinMainList',
      component: BithumbCoinMainList
    }
  ]
});

router.beforeEach(function (to, from, next) {
  console.log('변경하려는 경로 : ' + to.name);
  console.log('이전 경로 : ' + from.name);
  
  var authority_yn = 'N';
  var select_yn = 'N';
  var update_yn = 'N';
  var delete_yn = 'N';
  var screen_name = '';
  var from_location = from.name;
  var to_location = to.name;
  
  next()
});