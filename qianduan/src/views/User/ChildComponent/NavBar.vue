<template>
    <div class="nav-bar">
        <el-row class="tac">
            <el-col :span="24">
                <el-menu
                        background-color="#545c64"
                        text-color="#ffffff"
                        router>
                    <el-submenu index="1" style="text-align: center" >
                        <template slot="title">
                            <span>缴费</span>
                        </template>
                        <el-menu-item index="payProperty">
                            <template slot="title">物业费</template>
                        </el-menu-item>
                        <el-menu-item index="payWater">
                            <template slot="title">水费</template>
                        </el-menu-item>
                    </el-submenu>
                    <el-submenu index="2" style="text-align: center" >
                        <template slot="title">
                            <span>报修</span>
                        </template>
                        <el-menu-item index="wrong">
                            <template slot="title">我要报修</template>
                        </el-menu-item>
                        <el-menu-item index="myWrong">
                            <template slot="title">我的报修</template>
                        </el-menu-item>
                    </el-submenu>
                    <el-menu-item index="change" style="text-align: center">
                        <span slot="title">修改个人信息</span>
                    </el-menu-item>

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
        background-color: #545c64;
        height: 650px;
    }


</style>
