<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import Home from "views/Home/Home";
import User from "views/User/User";
import Manager from "views/Manager/Manager";
export default {
  name: 'App',
}
</script>

<style>
  #app{
    width: 100%;
    background-color: #F2F6FC;
    height: 98vh;
  }
</style>
