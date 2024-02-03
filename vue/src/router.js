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
const Test = () => {
	return import("./views/Test.vue")
}
const UpbitFullLive = () => {
  return import("./views/Coin/UpbitFullLive.vue")
}
const Report = () => {
	return import("./views/Coin/Report.vue")	
}
const CoinMainList = () => {
	return import("./views/Coin/CoinMainList.vue")	
}
const CoinLiveList = () => {
	return import("./views/Coin/CoinLiveList.vue")	
}
const BithumbCoinMainList = () => {
	return import("./views/Coin/BithumbCoinMainList.vue")	
}
const BithumbCoinLiveList = () => {
	return import("./views/Coin/BithumbCoinLiveList.vue")	
}
const BinanceCoinMainList = () => {
	return import("./views/Coin/BinanceCoinMainList.vue")	
}
const BinanceCoinLiveList = () => {
	return import("./views/Coin/BinanceCoinLiveList.vue")	
}
const BinanceFuturesCoinMainList = () => {
	return import("./views/Coin/BinanceFuturesCoinMainList.vue")	
}
const BinanceFuturesCoinLiveList = () => {
	return import("./views/Coin/BinanceFuturesCoinLiveList.vue")	
}
const BybitCoinMainList = () => {
	return import("./views/Coin/BybitCoinMainList.vue")	
}
const BybitCoinLiveList = () => {
	return import("./views/Coin/BybitCoinLiveList.vue")	
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
      path: '/UpbitFullLive',
      name: 'upbitfulllive',
      component: UpbitFullLive
    },
    {
      path: '/test',
      name: 'test',
      component: Test,
    },
    {
      path: '/CoinMainList',
      name: 'coinMainList',
      component: CoinMainList
    },
    {
      path: '/CoinMainList/:date',
      name: 'coinMainList',
      component: CoinMainList
    },
    {
      path: '/BithumbCoinMainList',
      name: 'bithumbcoinMainList',
      component: BithumbCoinMainList
    },
    {
      path: '/BithumbCoinMainList/:date',
      name: 'bithumbcoinMainList',
      component: BithumbCoinMainList
    },
    {
      path: '/BinanceCoinMainList',
      name: 'binancecoinMainList',
      component: BinanceCoinMainList
    },
    {
      path: '/BinanceCoinMainList/:date',
      name: 'binancecoinMainList',
      component: BinanceCoinMainList
    },
    {
      path: '/BinanceFuturesCoinMainList',
      name: 'binancefuturescoinMainList',
      component: BinanceFuturesCoinMainList
    },
    {
      path: '/BinanceFuturesCoinMainList/:date',
      name: 'binancefuturescoinMainList',
      component: BinanceFuturesCoinMainList
    },
    {
      path: '/BybitCoinMainList',
      name: 'bybitcoinMainList',
      component: BybitCoinMainList
    },
    {
      path: '/BybitCoinMainList/:date',
      name: 'bybitcoinMainList',
      component: BybitCoinMainList
    },
    {
      path: '/Report',
      name: 'Report',
      component: Report
    },
    {
      path: '/Report/:date/:market/:blog',
      name: 'Report',
      component: Report
    },
    {
      path: '/CoinLiveList',
      name: 'coinLiveList',
      component: CoinLiveList
    },
    {
      path: '/BithumbCoinLiveList',
      name: 'bithumbcoinLiveList',
      component: BithumbCoinLiveList
    },
    {
      path: '/BinanceCoinLiveList',
      name: 'binancecoinLiveList',
      component: BinanceCoinLiveList
    },
    {
      path: '/BinanceFuturesCoinLiveList',
      name: 'binancefuturescoinLiveList',
      component: BinanceFuturesCoinLiveList
    },
    {
      path: '/BybitCoinLiveList',
      name: 'bybitcoinLiveList',
      component: BybitCoinLiveList
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