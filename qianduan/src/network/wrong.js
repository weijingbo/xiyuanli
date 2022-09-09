import {request} from "./request";


export function getWrong() {
  return request({
    url:'/getWrong',
  })
}

export function deleteWrong(id) {
  return request({
    url:'/deleteWrong',
    params:{id}
  })
}

export function updateWrong(wrong) {
  return request({
    url:'/updateWrong',
    method:'POST',
    data:wrong
  })
}

//报障
export  function insertWrong(wrong) {
  return request({
    url:'/insertWrong',
    method:'POST',
    data: wrong
  })
}


export function getWrongByName(name) {
  return request({
    url:'/getWrongByName',
    params:{name}
  })

}
