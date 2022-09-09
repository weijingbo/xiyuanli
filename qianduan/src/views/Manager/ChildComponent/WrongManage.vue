<template>
    <div>
        <el-table
                :data="wrongs.slice((page-1)*pageSize,(page)*pageSize)"
                stripe
                style="width: 100%">
            <el-table-column
                    label="楼栋"
                    width="150"
                    align="center">
                <template slot-scope="scope">
                    {{scope.row.bname}}{{scope.row.number}}
                </template>
            </el-table-column>
            <el-table-column
                    prop="uname"
                    label="业主姓名"
                    width="100"
                    align="center">
            </el-table-column>
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
                    label="安排员工"
                    width="120"
                    align="center">
                <template slot-scope="scope">
                    <el-select v-model="scope.row.ename" placeholder="请选择" size="mini">
                        <el-option
                                v-for="item in names"
                                :value="item"
                                :key="item">
                        </el-option>
                    </el-select>
                </template>
            </el-table-column>
            <el-table-column
                    align="center"
                    prop="state"
                    label="状态"
                    width="90"
                    :filters="[{ text: '已解决', value: 1 }, { text: '未解决', value: 0}]"
                    :filter-method="filterTag"
                    filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.state" @click="change1(scope.$index)">已解决</el-tag>
                    <el-tag v-else @click="change2(scope.$index)" type="danger">未解决</el-tag>
                </template>
            </el-table-column>
            <el-table-column
                    :show-overflow-tooltip='true'
                    align="center"
                    prop="evaluate"
                    label="评价"
                    width="150">
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="130"
                    align="center">
                <template slot-scope="scope">
                    <el-button @click="updateWrong(scope.row)" icon="el-icon-edit" size="mini"/>
                    <el-button type="danger" @click="deleteWrong(scope.row.id)" icon="el-icon-delete" size="mini"/>
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
  import {getNames} from "network/manager";
  import {deleteWrong, getWrong, updateWrong,} from "network/wrong"

  export default {
    name: "WrongManage",
    data() {
      return {
        wrongs: [],
        page: 1,
        pageSize: 7,
        total: 0,
        names: [],
      }
    },
    created() {
      this.GetWrong();
      this.GetNames()
    },
    methods: {
      updateWrong(wrong) {
        this.$confirm('确认要更新信息吗？', '提示', {
          'type': 'warning'
        }).then(() => {
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
      GetWrong() {
        getWrong().then(res => {
          this.wrongs = [];
          this.wrongs.push(...res);
          this.total = res.length;
          this.page = 1;
        })
      },
      handleCurrentChange(val) {
        this.page = val
      },
      GetNames() {
        getNames().then(res => {
          this.names = [];
          this.names.push(...res);
          this.total = res.length;
          this.page = 1;
        })
      },
      change1(index) {
        this.wrongs[index].state = 0
      },
      change2(index) {
        this.wrongs[index].state = 1
      },
      filterTag(value, row) {
        return row.state === value;
      },
    }
  }
</script>

<style scoped>

</style>
