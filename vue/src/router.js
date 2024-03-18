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
const YoutubeUpbit_5_15 = () => {
  return import("./views/Coin/YoutubeUpbit_5_15.vue")
}
const YoutubeUpbit_60_240 = () => {
  return import("./views/Coin/YoutubeUpbit_60_240.vue")
}
const YoutubeBithumb_5_15 = () => {
  return import("./views/Coin/YoutubeBithumb_5_15.vue")
}
const YoutubeBithumb_60_240 = () => {
  return import("./views/Coin/YoutubeBithumb_60_240.vue")
}
const Report = () => {
	return import("./views/Coin/Report.vue")	
}
const Short = () => {
	return import("./views/Coin/Short.vue")	
}
const Video = () => {
	return import("./views/Coin/Video.vue")	
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
      path: '/YoutubeUpbit_5_15',
      name: 'youtubeupbit_5_15',
      component: YoutubeUpbit_5_15
    },
    {
      path: '/YoutubeUpbit_60_240',
      name: 'youtubeupbit_60_240',
      component: YoutubeUpbit_60_240
    },
    {
      path: '/YoutubeBithumb_5_15',
      name: 'youtubebithumb_5_15',
      component: YoutubeBithumb_5_15
    },
    {
      path: '/YoutubeBithumb_60_240',
      name: 'youtubebithumb_60_240',
      component: YoutubeBithumb_60_240
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
      path: '/Short',
      name: 'Short',
      component: Short
    },
    {
      path: '/Short/:date/:market',
      name: 'Short',
      component: Short
    },
    {
      path: '/Video',
      name: 'Video',
      component: Video
    },
    {
      path: '/Video/:date/:market',
      name: 'Video',
      component: Video
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
