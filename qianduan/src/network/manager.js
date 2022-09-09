import {request} from "./request";

export function mlogin(name,password) {
  return request({
    url:'manage/login',
    params :{name, password}
  })
}

export function getUsers() {
  return request({
    url:'/user/getUsers',
  })
}

export function getUser(name) {
  return request({
    url:'/user/getUser',
    params:{name}
  })
}

export function insertUser(user) {
  return request({
    url:'/user/insertUser',
    method:'POST',
    data:user
  })
}
export function deleteUser(id) {
  return request({
    url:'/user/deleteUser',
    params:{id}
  })
}


export function getParkings() {
  return request({
    url:'/getParkings',
  })
}

export  function updateParking(Parking) {
  return request({
    url:'/updateParking',
    method:'POST',
    data:Parking
  })
}

export  function getParking(uname) {
  return request({
    url:'/getParking',
    params:{uname}
  })
}

export  function deleteParking(id) {
  return request({
    url:'/deleteParking',
    params:{
      id
    }
  })
}

export  function insertParking(Parking) {
  return request({
    url:'/insertParking',
    method:'POST',
    data:Parking
  })
}

export  function getEmployees() {
    return request({
      url:'/manage/getEmployees',
    })
}

export  function getEmployee(name) {
  return request({
    url:'/manage/getEmployee',
    params:{name}
  })
}

export  function insertEmployee(employee) {
  return request({
    url:'/manage/insertEmployee',
    method:'POST',
    data:employee
  })
}

export  function updateEmployee(employee) {
  return request({
    url:'/manage/updateEmployee',
    method:'POST',
    data:employee
  })
}
export  function deleteEmployee(id) {
  return request({
    url:'/manage/deleteEmployee',
    params:{id}
  })
}

export  function getNames() {
  return request({
    url:'/manage/getNames',
  })
}


export  function getPatrols() {
  return request({
    url:'/getPatrols',
  })
}

export  function getPatrol(times) {
  return request({
    url:'/getPatrol',
    method:'POST',
    data:times
  })
}

export  function insertPatrol(patrol) {
  return request({
    url:'/insertPatrol',
    method:'POST',
    data:patrol
  })
}

export  function updatePatrol(patrol) {
  return request({
    url:'/updatePatrol',
    method:'POST',
    data:patrol
  })
}
export  function deletePatrol(id) {
  return request({
    url:'/deletePatrol',
    params:{id}
  })
}

export function insertWaters(waters) {
  return request({
    url:'/insertWaters',
    method:'POST',
    data:waters
  })
}

export function deleteWater(id) {
  return request({
    url:'/deleteWater',
    params:{
      id
    }
  })
}

export function selectWaters(date) {
  return request({
    url:'/selectWaters',
    params:{
      date
    }
  })
}

export function getWaterByname(name) {
  return request({
    url:'/getWaterByName',
    params:{
      name
    }
  })
}

export function deleteWaters(waters) {
  return request({
    url:'/deleteWaters',
    method:'POST',
    data:waters
  })
}

export function insertProperties(properties) {
  return request({
    url:'/insertProperties',
    method:'POST',
    data:properties
  })
}
export function deleteProperties(properties) {
  return request({
    url:'/deleteProperties',
    method:'POST',
    data:properties
  })
}

export function deleteProperty(id) {
  return request({
    url:'/deleteProperty',
    params:{
      id
    }
  })
}

export function getByName(name) {
  return request({
    url:'/getByName',
    params:{
      name
    }
  })
}

export function getByYear(year) {
  return request({
    url:'/getByYear',
    params:{
      year
    }
  })
}


export function getNoPay() {
  return request({
    url:'/getNoPay'
  })
}

export function sendMessage() {
  return request({
    url:'/urgeMessage'
  })
}

export function getNo() {
  return request({
    url:'/PNoPay'
  })
}

export function Message() {
  return request({
    url:'/message'
  })
}
