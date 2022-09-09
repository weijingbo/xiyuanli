<template>
    <div>
        <el-table
                :data="wrongs.slice((page-1)*pageSize,(page)*pageSize)"
                stripe
                style="width: 100%">
            <el-table-column
                    prop="sdate"
                    label="报修时间"
                    width="120"
                    sortable
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="detail"
                    label="详情"
                    width="180"
                    :show-overflow-tooltip='true'
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="ename"
                    label="安排员工"
                    width="180"
                    align="center">
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="state"
                    label="状态"
                    width="100"
                    :filters="[{ text: '已解决', value: 1 }, { text: '未解决', value: 0}]"
                    :filter-method="filterTag"
                    filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.state">已解决</el-tag>
                    <el-tag v-else type="danger">未解决</el-tag>
                </template>
            </el-table-column>
            <el-table-column
                    prop="evaluate"
                    label="评价"
                    width="180"
                    :show-overflow-tooltip='true'
                    align="center">
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="300"
                    align="center">
                <template slot-scope="scope">
                    <el-button  @click="AddEvaluate(scope.row)" v-if="scope.row.state">
                        添加评价
                    </el-button>
                    <el-button @click="updateWrong(scope.row)" v-else>
                        已经解决
                    </el-button>
                    <el-button type="danger" @click="deleteWrong(scope.row.id)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-col :span="24" class="toolbar">
            <el-pagination layout="prev, pager, next"
                           @current-change="handleCurrentChange"
                           @prev-click="page--"
                           :hide-on-single-page=true
                           @next-click="page++"
                           :page-size=pageSize
                           :total=total
                           style="float:left;">
            </el-pagination>
        </el-col>
    </div>
</template>

<script>
  import {getWrongByName, updateWrong, deleteWrong} from "network/wrong";

  export default {
    name: "MyWrong",
    data() {
      return {
        wrongs: [],
        page: 1,
        pageSize: 7,
        total: 0,
      }
    },
    created() {
      this.GetWrongByName()
    },
    methods: {
      AddEvaluate(wrong){
        this.$prompt('请填写评价','填写评价',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({value}) =>{
          wrong.evaluate = value;
          updateWrong(wrong).then(res=>{
            if(res===1){
              this.$confirm("感谢您的评价");
              this.GetWrongByName();
            }else{
              this.$confirm("评论失败");
            }
          })

        })
      },
      updateWrong(wrong) {
        this.$confirm('问题已经解决了吗？', '提示', {
          'type': 'warning'
        }).then(() => {
          wrong.state = 1;
          updateWrong(wrong).then(res => {
            if (res === 1) {
              this.$alert('更新成功 ')
            } else {
              this.$alert('更新失败！')
            }
          })
        })
      },
      deleteWrong(id) {
        this.$confirm('确认删除该记录吗?', '提示', {
          'type': 'warning'
        }).then(() => {
          deleteWrong(id).then(res => {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.GetWrong();
          })
        })
      },
      GetWrongByName() {
        let name = sessionStorage.getItem("uname");
        getWrongByName(name).then(res => {
          this.wrongs = [];
          this.wrongs.push(...res);
          this.total = res.length;
          this.page = 1;
        })
      },
      handleCurrentChange(val) {
        this.page = val
      },
      filterTag(value, row) {
        return row.state === value;
      },
    }
  }
</script>

<style scoped>

</style>
