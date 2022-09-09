<template>
    <div>
        <Header>
            <div class="right">
                <span >你好,{{name}}</span>
                <span @click="back" class="text">[<el-a>退出登陆</el-a>]</span>
            </div>
        </Header>
        <el-row>
            <el-col :span="8">
                <nav-bar/>
            </el-col>
            <el-col :span="17" :offset="6">
               <div class="main">
                    <router-view/>
               </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
  import Header from "components/Header";
  import NavBar from "./ChildComponent/NavBar";

  export default {
    name: "Manager",
    data(){
      return{
        name:null
      }
    },
    components: {
      NavBar,
      Header
    },
    created() {
      this.name = sessionStorage.getItem("ename")
    },
    methods:{
      back(){
        this.$confirm('是否要退出登陆？','退出登陆',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          sessionStorage.removeItem("ename");
          sessionStorage.removeItem("eid");
          this.$router.push("/home")
        })
      }
    }
  }
</script>

<style scoped>
    .right{
        text-align: center;
        font-size: 20px;
        white-space:nowrap;
        padding: 35px 70px;
    }
    .text{
        font-size: 16px;
    }
    .main{
        padding-top:10px;
    }
</style>
