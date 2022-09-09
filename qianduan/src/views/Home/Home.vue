<template>
    <div id="Home">
            <Header/>
        <el-row>
            <el-col :span="8">
                <Login/>
            </el-col>
            <el-col :span="16">
                <Detail/>
            </el-col>
        </el-row>
    </div>
</template>

<script>
  import Header from "components/Header";
  import Login from "./ChildComponent/Login";
  import Detail from "./ChildComponent/Detail";
  export default {
    name: 'Home',
    components: {
      Header,
      Login,
      Detail,
    }
  }
</script>

<style>

</style>
