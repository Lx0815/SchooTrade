import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    // name: '',
    // avatar: ''
    userObj:{
      userId: -1,
      nickName: '',
      avatar: '',
      gender: -1,
      role: -1
    }
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  // SET_NAME: (state, name) => {
  //   state.name = name
  // },
  // SET_AVATAR: (state, avatar) => {
  //   state.avatar = avatar
  // }
  SET_USER_OBJ: (state, userObj) => {
    state.userObj.userId = userObj.id
    state.userObj.nickName = userObj.nickName
    state.userObj.avatar = userObj.avatar
    state.userObj.gender = userObj.gender
    state.userObj.role = userObj.role
  }
}

const actions = {
  // user login
  // 第一个参数是 context.commit , context 中含有一些 actions 操作可能需要的对象
  login({ commit }, userInfo) {
    const { schoolCardId, password } = userInfo
    // 构造一个 Promise 对象执行异步请求
    return new Promise((resolve, reject) => {
      // 调用登录函数
      login({ schoolCardId: schoolCardId.trim(), password: password }).then(response => {
        const { data } = response
        const { obj } = data
        commit('SET_TOKEN', data.token)
        console.log('login/obj : ' + obj)
        commit('SET_USER_OBJ', obj)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.userObj.userId).then(response => {
        const { data } = response
        const { obj } = data

        if (!data) {
          return reject('Verification failed, please Login again.')
        }

        console.log('gerInfo/obj : ' + obj)
        commit('SET_USER_OBJ', obj)

        // const { name, avatar } = data

        // commit('SET_NAME', name)
        // commit('SET_AVATAR', avatar)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

