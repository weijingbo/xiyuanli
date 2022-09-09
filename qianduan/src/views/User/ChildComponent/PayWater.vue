<template>
<div>
    <el-table
            :data="water.slice((page-1)*pageSize,(page)*pageSize)"
            :row-style="{height:'62px'}"
            stripe>
        <el-table-column
                width="120">
        </el-table-column>
        <el-table-column
                prop="date"
                label="时间"
                width="200"
                sortable>
        </el-table-column>
        <el-table-column
                prop="uname"
                label="业主姓名"
                width="120">
        </el-table-column>
        <el-table-column
                label="用水量"
                width="120">
            <template slot-scope="scope">
                {{scope.row.number}}m³
            </template>
        </el-table-column>
        <el-table-column
                label="水费"
                width="120">
            <template slot-scope="scope">
                {{scope.row.price}}元
            </template>
        </el-table-column>
        <el-table-column
                prop="flag"
                label="状态"
                width="120">
        </el-table-column>
        <el-table-column
                label="操作">
            <template slot-scope="scope">
                <el-button type="primary" round @click="Pay(scope.row.id)" v-if="isshow(scope.row.flag)">
                    去缴费
                </el-button>
            </template>
        </el-table-column>
    </el-table>

    <el-col :span="24" class="toolbar">
        <el-pagination layout="prev, pager, next"
                       @current-change="handleCurrentChange"
                       @prev-click ="page--"
                       @next-click ="page++"
                       :page-size=pageSize
                       :total="total"
                       :hide-on-single-page = true
                       style="float:left;">
        </el-pagination>
    </el-col>


</div>
</template>

<script>
    import {getWaters,payWater} from 'network/user'
  export default {
    name: "PayWater",
    data(){
      return{
        water:[],
        page:1,
        pageSize:7,
        total:0,
      }
    },
    methods:{
      isshow(flag){
        if (flag !== "已缴费"){
          return 1
        }else{
          return 0
        }
      },
      PayWater(){
        let uid = sessionStorage.getItem("uid");
        getWaters(uid).then(res =>{
            this.water = [];
            this.water.push(...res);
            this.total = res.length
        })
      },
      Pay(id){
        let src = require("assets/zhifubao.png")
        this.$alert('<img src='+src+'>','支付',{
          dangerouslyUseHTMLString: true,
          'type': 'primary'
        }) .then( _ =>{
          payWater(id).then(res=>{
            if(res === 1){
              this.$alert('支付成功');
              this.PayWater()
            }else{
              this.$alert('支付失败');
            }
          })
        })

      },
      handleCurrentChange(val) {
        this.page = val;
      },
    },
    created() {
      this.PayWater()
    }
  }
</script>

<style scoped>

</style>
