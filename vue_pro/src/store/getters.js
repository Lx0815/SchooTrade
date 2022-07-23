const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  // avatar: state => state.user.avatar,
  // name: state => state.user.name
  avatar: state => state.user.userObj.avatar,
  userId: state => state.user.userObj.userId,
  gender: state => state.user.userObj.gender,
  nickName: state => state.user.userObj.nickName,
  role: state => state.user.userObj.role
}
export default getters
