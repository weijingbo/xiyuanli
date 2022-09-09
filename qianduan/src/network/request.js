import axios from 'axios'

axios.defaults.withCredentials = true;

export function request(config) {
  const instance = axios.create({
     baseURL: "http://localhost:8080",
     timeout:10000,
     withCredentials:true
    });




  instance.interceptors.request.use(config => {
    return config
  }, err => {
    console.log(config);
    console.log(err);
  })

  //2.2响应拦截
  instance.interceptors.response.use(res => {
    return res.data
  }, err => {
    console.log(err);
  })


  return instance(config)
}
