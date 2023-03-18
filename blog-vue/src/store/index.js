import Vue from 'vue'
import Vuex from 'vuex'
// import * as getters from './getters.js'

Vue.use(Vuex)

/** 状态定义 */
export const state = {
  loading: false,
  themeObj: 0,//主题
  keywords:'',//关键词
  //errorImg: 'this.onerror=null;this.src="' + require('../../static/img/tou.jpg') + '"',
  errorImg: 'static/img/morentouxiang.png',
 // baseURL:'http://localhost:7777/'
  baseURL:'http://47.109.90.13:7777/'
}

export default new Vuex.Store({
    state,
})
