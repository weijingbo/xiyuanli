<template>
    <div class="table">
        <el-table
                :data="property.slice((page-1)*pageSize,(page)*pageSize)"
                :row-style="{height:'62px'}"
                stripe>
            <el-table-column
                    width="200">

            </el-table-column>
            <el-table-column
                    label="年份"
                    width="120">
                <template slot-scope="scope">
                    {{scope.row.year}}年
                </template>
            </el-table-column>
            <el-table-column
                    prop="uname"
                    label="业主姓名"
                    width="120">
            </el-table-column>
            <el-table-column
                    label="物业费"
                    width="120">
                <template slot-scope="scope">
                    {{scope.row.price}}元
                </template>
            </el-table-column>
            <el-table-column
                    prop="state"
                    label="状态"
                    width="120">
            </el-table-column>
            <el-table-column
                    label="操作">
                <template slot-scope="scope">
                    <el-button type="primary" round @click="Pay(scope.row.id)" v-if="isshow(scope.row.state)">
                        去缴费
                    </el-button>
                </template>
            </el-table-column>
        </el-table>


        <el-col :span="24" class="toolbar">
            <el-pagination layout="prev, pager, next"
                           @current-change="handleCurrentChange"
                           @prev-click="page--"
                           @next-click="page++"
                           :page-size=pageSize
                           :total="total"
                           :hide-on-single-page = true
                           style="float:left;">
            </el-pagination>
        </el-col>
    </div>
</template>

<script>
  import {getProperty, payProperty} from "network/user"

  export default {
    name: "PayProperty",
    data() {
      return {
        property: [],
        page: 1,
        pageSize: 7,
        total: 0
      }
    },
    created() {
      this.GetProperty()
    },
    methods: {
      isshow(state) {
        if (state !== "已缴费") {
          return 1
        } else {
          return 0
        }
      },
      GetProperty() {
        let uid = sessionStorage.getItem("uid")
        getProperty(uid).then(res => {
            this.property = []
            this.property.push(...res)
            this.total = res.length
          }
        )
      },
      Pay(id) {
        let src = require("assets/zhifubao.png")
        this.$alert('<img src='+src+'>','支付',{
          dangerouslyUseHTMLString: true,
          'type': 'primary'
        }).then(_ => {
          payProperty(id).then(res => {
            if (res === 1) {
              this.$alert("缴费成功");
              this.GetProperty()
            } else {
              this.$alert("缴费失败");
            }

          })
        })
      },
      handleCurrentChange(val) {
        this.page = val;
      }
    }
  }
</script>

<style scoped>
    .table {
        padding-right: 50px;
    }
</style>
