import Vue from 'vue';
import Router from 'vue-router';
const Manager =() => import('../views/Manager/Manager');
const User =() => import('../views/User/User');
const Home =() => import('../views/Home/Home');
const CarManage =() => import('../views/Manager/ChildComponent/CarManage');
const UserManage =() => import('../views/Manager/ChildComponent/UserManage');
const Patrol =() => import('../views/Manager/ChildComponent/Patrol');
const Property =() => import('../views/Manager/ChildComponent/Property');
const Water =() => import('../views/Manager/ChildComponent/Water');
const WrongManage =() => import('../views/Manager/ChildComponent/WrongManage');
const HouseManage =() => import('../views/Manager/ChildComponent/HouseManage');
const Employee =() => import("../views/Manager/ChildComponent/Employee");
const PayWater = () => import("../views/User/ChildComponent/PayWater");
const PayProperty = () => import("../views/User/ChildComponent/PayProperty");
const Wrong = () => import("../views/User/ChildComponent/Wrong");
const Change =() => import("../views/User/ChildComponent/Change");
const MyWrong = () =>import("../views/User/ChildComponent/MyWrong");

Vue.use(Router);

const  routes= [
  {
    path: '/',
    redirect:'/home'
  },
  {
    path:'/home',
    component:Home
  },
  {
    path:'/user',
    component:User,
    children:[
      {
        path:'',
        redirect:'payWater'
      },
      {
        path:'payProperty',
        component:PayProperty
      },
      {
        path:'payWater',
        component:PayWater
      },
      {
        path:'wrong',
        component:Wrong
      },
      {
        path:'myWrong',
        component:MyWrong
      },
      {
        path:'change',
        component:Change
      }

    ]
  },
  {
    path:'/manager',
    component:Manager,
    children:[
      {
        path:'',
        redirect:'userManage'
      },
      {
        path:'carManage',
        component: CarManage
      },
      {
        path:'userManage',
        component: UserManage
      },
      {
        path:'houseManage',
        component: HouseManage
      },
      {
        path:'water',
        component: Water
      },
      {
        path:'property',
        component: Property
      },
      {
        path:'wrongManage',
        component: WrongManage
      },
      {
        path:'patrol',
        component: Patrol
      },
      {
        path:'employee',
        component:Employee
      },
    ]
  },
];


const router = new Router({
  //配置路由和组件之间的应用关系
  routes,
  mode:'history'
});


export default router


