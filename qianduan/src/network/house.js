import {request} from "./request";

export function getNames() {
  return request({
    url:'/getUserName'
  })
}


export  function getHouse(belong) {
  return request({
    url:'/getHouse',
    params:{
      belong
    }
  })
}

export  function insertHouse(house) {
  return request({
    url:'/insertHouse',
    method:'POST',
    data:house
  })
}

export  function updateHouse(house) {
  return request({
    url:'/updateHouse',
    method:'POST',
    data:house
  })
}
export  function deleteHouse(id) {
  return request({
    url:'/deleteHouse',
    params:{id}
  })
}

export function getHouseByName(name) {
  return request({
    url:'/getHouseByName',
    params:{name}
  })
}

export  function getBuildings() {
  return request({
    url:'/getBuildings'
  })
}
export  function getHouses() {
  return request({
    url:'/getHouses',
  })
}

export function rates() {
  return request({
    url:'/rates',
  })
}

export function rate(bid) {
  return request({
    url:'/rate',
    params:{bid}
  })
}
