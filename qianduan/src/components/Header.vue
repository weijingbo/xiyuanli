<template>
    <div class="header">
        <div class="left"><img src="../assets/logo.png"></div>
        <div class="center"><span><b>西苑里小区物业管理系统</b></span></div>
        <div class="right"><slot></slot></div>
    </div>
</template>

<script>
  export default {
    name: "Header",
  }
</script>

<style scoped>
    .header{
        display: flex;
        height: 80px;
        background-color: #5d7bd1;
    }
    .right,.left{
        width: 25%;
    }
    .center{
        width: 50%;
    }
 .left img {
     height: 80px;
 }
  .center{
    font-size: 38px;
    padding-top: 12px;
    text-align: center;
    white-space:nowrap;
  }
</style>
