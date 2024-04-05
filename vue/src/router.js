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
const YoutubeUpbit_5 = () => {
  return import("./views/Coin/YoutubeUpbit_5.vue")
}
const YoutubeUpbit_15 = () => {
  return import("./views/Coin/YoutubeUpbit_15.vue")
}
const YoutubeUpbit_60 = () => {
  return import("./views/Coin/YoutubeUpbit_60.vue")
}
const YoutubeUpbit_240 = () => {
  return import("./views/Coin/YoutubeUpbit_240.vue")
}
const YoutubeBithumb_5 = () => {
  return import("./views/Coin/YoutubeBithumb_5.vue")
}
const YoutubeBithumb_15 = () => {
  return import("./views/Coin/YoutubeBithumb_15.vue")
}
const YoutubeBithumb_60 = () => {
  return import("./views/Coin/YoutubeBithumb_60.vue")
}
const YoutubeBithumb_240 = () => {
  return import("./views/Coin/YoutubeBithumb_240.vue")
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
const BlogReportMake = () => {
	return import("./views/Coin/BlogReportMake.vue")	
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
      path: '/YoutubeUpbit_5',
      name: 'youtubeupbit_5',
      component: YoutubeUpbit_5
    },
    {
      path: '/YoutubeUpbit_15',
      name: 'youtubeupbit_15',
      component: YoutubeUpbit_15
    },
    {
      path: '/YoutubeUpbit_60',
      name: 'youtubeupbit_60',
      component: YoutubeUpbit_60
    },
    {
      path: '/YoutubeUpbit_240',
      name: 'youtubeupbit_240',
      component: YoutubeUpbit_240
    },
    {
      path: '/YoutubeBithumb_5',
      name: 'youtubebithumb_5',
      component: YoutubeBithumb_5
    },
    {
      path: '/YoutubeBithumb_15',
      name: 'youtubebithumb_15',
      component: YoutubeBithumb_15
    },
    {
      path: '/YoutubeBithumb_60',
      name: 'youtubebithumb_60',
      component: YoutubeBithumb_60
    },
    {
      path: '/YoutubeBithumb_240',
      name: 'youtubebithumb_240',
      component: YoutubeBithumb_240
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
      name: 'coinMainList2',
      component: CoinMainList
    },
    {
      path: '/BithumbCoinMainList',
      name: 'bithumbcoinMainList',
      component: BithumbCoinMainList
    },
    {
      path: '/BithumbCoinMainList/:date',
      name: 'bithumbcoinMainList2',
      component: BithumbCoinMainList
    },
    {
      path: '/BinanceCoinMainList',
      name: 'binancecoinMainList',
      component: BinanceCoinMainList
    },
    {
      path: '/BinanceCoinMainList/:date',
      name: 'binancecoinMainList2',
      component: BinanceCoinMainList
    },
    {
      path: '/BinanceFuturesCoinMainList',
      name: 'binancefuturescoinMainList',
      component: BinanceFuturesCoinMainList
    },
    {
      path: '/BinanceFuturesCoinMainList/:date',
      name: 'binancefuturescoinMainList2',
      component: BinanceFuturesCoinMainList
    },
    {
      path: '/BybitCoinMainList',
      name: 'bybitcoinMainList',
      component: BybitCoinMainList
    },
    {
      path: '/BybitCoinMainList/:date',
      name: 'bybitcoinMainList2',
      component: BybitCoinMainList
    },
    {
      path: '/BlogReportMake/:date',
      name: 'blogreportmake',
      component: BlogReportMake
    },
    {
      path: '/Report',
      name: 'Report',
      component: Report
    },
    {
      path: '/Report/:date/:market/:blog',
      name: 'Report2',
      component: Report
    },
    {
      path: '/Short',
      name: 'Short',
      component: Short
    },
    {
      path: '/Short/:date/:market',
      name: 'Short2',
      component: Short
    },
    {
      path: '/Video',
      name: 'Video',
      component: Video
    },
    {
      path: '/Video/:date/:market',
      name: 'Video2',
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