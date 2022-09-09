import {request} from "./request";
//用户登陆
export function ulogin(name,password) {
    return request({
      url:'user/login',
      params :{
        name,
        password
      }
    })
}
//获取该用户的物业费
export function getProperty(uid) {
  return request({
    url:'/getProperty',
    params :{
      uid
    }
  })
}
//缴纳物业费
export  function payProperty(id) {
  return request({
    url:'/payProperty',
    params :{
      id
    }
  })
}

export  function getWaters(uid) {
  return request({
    url:'/getwaters',
    params :{
      uid
    }
  })
}
export  function payWater(id) {
  return request({
    url:'/paywater',
    params :{
      id
    }
  })
}

export  function updateUser(user) {
  return request({
    url:'/user/updateUser',
    method:'POST',
    data:user
  })
}
