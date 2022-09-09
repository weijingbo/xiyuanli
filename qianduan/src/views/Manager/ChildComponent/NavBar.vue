<template>
    <div class="nav-bar">
        <el-row class="tac">
            <el-col :span="12" class="col">
                <el-menu
                        class="vertical"
                        background-color="#545c64"
                        text-color="#ffffff"
                        router>
                    <el-menu-item index="userManage">
                        <span slot="title">用户管理</span>
                    </el-menu-item>
                    <el-menu-item index="houseManage">
                        <span slot="title">房屋管理</span>
                    </el-menu-item>
                    <el-menu-item index="carManage">
                        <span slot="title">车辆管理</span>
                    </el-menu-item>
                    <el-menu-item index="employee">
                        <span slot="title">员工管理</span>
                    </el-menu-item>
                    <el-menu-item index="wrongManage">
                        <span slot="title">报障管理</span>
                    </el-menu-item>
                    <el-menu-item index="patrol">
                        <span slot="title">巡逻安排</span>
                    </el-menu-item>
                    <el-submenu index="water">
                        <template slot="title">
                            <span>缴费管理</span>
                        </template>
                        <el-menu-item index="water">
                            <template slot="title">水费</template>
                        </el-menu-item>
                        <el-menu-item index="property">
                            <template slot="title">物业费</template>
                        </el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-col>
        </el-row>
    </div>
</template>

<script>
  export default {
    name: "NavBar",
  }
</script>

<style scoped>
  .nav-bar{
      position: absolute;
      float: left;
      width: 300px;
      height: 650px;
      background-color: #545c64;
  }
  .col {
      width: 100%;
      text-align: center;
      height: 100%;
  }
    .vertical{
        width: 300px;
    }
</style>
