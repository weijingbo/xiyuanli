<template>
    <div class="login">
        <el-form :rules="rules" :model="login">
            <el-form-item prop="name" class="name">
                <el-input type="text" v-model="login.name" placeholder="用户名" clearable />
            </el-form-item>
            <el-form-item prop="password"  class="password">
                <el-input type="password" v-model="login.password" placeholder="密码"  clearable/>
            </el-form-item>
            <el-form-item class="radio">
                <el-radio-group v-model="login.radio">
                    <el-radio label="用户" />
                    <el-radio label="管理员"/>
                </el-radio-group>
            </el-form-item>
            <el-form-item class="button">
                <el-button @click="tologin()" id="login" >登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
   import {ulogin} from "network/user";
   import {mlogin} from "network/manager";
   import rules from 'element-ui-validation'
   export default {
    data () {
      return {
       login:{
         name:'',
         password:'',
         radio:'用户',
       },
        rules:{
          name:rules.string("用户名",11),
          password:rules.string("密码",20),
        }
      };
    },

    methods: {
      tologin(){
        if(this.login.radio === "用户"){
        ulogin(this.login.name,this.login.password).then( res=>{
          if(res.isuser){
            sessionStorage.setItem("uid",res.user.id);
            sessionStorage.setItem("uname",res.user.name);
            this.$router.push("/user")
          }else{
            this.$alert('用户名或密码错误，请重新输入','error')
          }
        })
        }else if(this.login.radio === "管理员"){
          mlogin(this.login.name,this.login.password).then( res=>{
             if(res.ismanage){
               sessionStorage.setItem("eid",res.employee.id)
               sessionStorage.setItem("ename",res.employee.name)
               this.$router.push("/manager")
             }else{
               this.$alert('用户名或密码错误，请重新输入','error')
             }
          })
        }

      }
    }
  };
</script>

<style scoped>
    .login {
        padding-top: 100px;
        position: absolute;
        left: 0px;
        width: 350px;
        height: 570px;
        background-color:#E4E7ED;
    }
    .name ,.password{
        width: 200px;
        padding-left: 75px;
    }
    .radio{
        width: 80%;
        padding-top: 20px;
        padding-left: 85px;
    }
    .button{
        padding-left: 140px;
        padding-top: 20px;
    }
</style>
