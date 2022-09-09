<template>
    <div>
        <!--工具条-->
        <el-col :span="24" class="toolbar" >
            <el-form :inline="true" :model="filter">
                <el-form-item>
                    <el-input v-model="filter.uname" placeholder="姓名"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getParking()">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="insert()">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>


        <el-table
                :data="Parkings.slice((page-1)*pageSize,(page)*pageSize)"
                stripe
                class="table">
            <el-table-column width="160px">

            </el-table-column>
            <el-table-column
                prop="uname"
                label="业主姓名"
                width="160"
                align="center">
            </el-table-column>
            <el-table-column
                    prop="number"
                    label="车牌号"
                    width="160"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="detail"
                    label="说明"
                    width="160"
                    align="center">
            </el-table-column>
            <el-table-column
                    label="操作"
                    align="center"
                    width="280">
                <template slot-scope="scope">
                    <el-button  @click="update(scope.row)" >
                        修改
                    </el-button>
                    <el-button type="danger" @click="deleteParking(scope.row.id)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
            <el-table-column width="120px">
            </el-table-column>
        </el-table>

        <el-col :span="24" class="toolbar">
            <el-pagination layout="prev, pager, next"
                           @current-change="handleCurrentChange"
                           :hide-on-single-page = true
                           @prev-click ="page--"
                           @next-click ="page++"
                           :page-size=pageSize
                           :total="total"
                           style="float:left;">
            </el-pagination>
        </el-col>


        <el-dialog title="更新车位信息" :visible.symc="isUpdate">
        <el-form :model="Parking" class="form">
            <el-form-item label="业主姓名" label-width="80px">
                <el-autocomplete
                        v-model="Parking.uname"
                        :fetch-suggestions="querySearch"
                        placeholder="请输入业主姓名"
                        :trigger-on-focus="false"/>
            </el-form-item>
            <el-form-item label="车牌号" label-width="80px">
                <el-input v-model="Parking.number" style="width: 200px"/>
            </el-form-item>
            <el-form-item label="描述" label-width="80px">
                <el-input v-model="Parking.detail" style="width: 200px"/>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="isUpdate = false">取 消</el-button>
            <el-button type="primary" @click="updateParking()">确 定</el-button>
        </div>
    </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增车位信息" :visible.symc="isInsert">
            <el-form :model="Parking" class="form">
                <el-form-item label="业主姓名" label-width="80px">
                    <el-autocomplete
                            v-model="Parking.uname"
                            :fetch-suggestions="querySearch"
                            placeholder="请输入业主姓名"
                            :trigger-on-focus="false"/>
                </el-form-item>
                <el-form-item label="车牌号" label-width="80px">
                    <el-input v-model="Parking.number" style="width: 200px" clearable/>
                </el-form-item>
                <el-form-item label="描述" label-width="80px">
                    <el-input v-model="Parking.detail" style="width: 200px" clearable/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="isInsert = false">取 消</el-button>
                <el-button type="primary" @click="insertParking()">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {getParkings,updateParking,insertParking,getParking,deleteParking} from "network/manager"
    import {getNames} from "network/house"
  export default {
    name: "CarManage",
    data(){
      return{
        filter:{
          uname:''
        },
        Parkings:[],
        isInsert:false,
        isUpdate:false,
        Parking:{
          uname:'',
          number:'',
          detail:''
        },
        page:1,
        pageSize:7,
        total: 0,
        names:[],
      }
    },
    created() {
      this.getParkings();
      this.GetNames();
    },
    methods: {
      getParkings() {
        getParkings().then(res => {
          this.Parkings = [];
          this.Parkings.push(...res);
          this.total=res.length;
          this.page = 1;
          }
        )
      },
      update(row) {
        this.Parking = row;
        this.isUpdate = true
      },
      insert() {
        this.isInsert = true
      },
      updateParking() {
        updateParking(this.Parking).then(res => {
          this.isUpdate = false;
          if (res === 1) {
            this.$alert("更新成功")
          } else {
            this.$alert("更新失败")
          }
        })
      },
      getParking() {
        getParking(this.filter.uname).then(res => {
          this.Parkings = [];
          this.Parkings.push(...res);
          this.total=res.length;
          this.page = 1;
        })
      },
      insertParking() {
        insertParking(this.Parking).then(res => {
          if (res === 1) {
            this.isInsert = false;
            this.$alert("插入成功");
            this.getParkings();
          } else {
            this.$alert("插入失败")
          }
        })
      },
      deleteParking(id) {
        this.$confirm('确认删除该记录吗?', '提示', {
          'type': 'warning'
        }).then(() => {
          deleteParking(id).then(res => {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.getParkings();
          })
        })
      },
      GetNames(){
        getNames().then(res=>{
          for (let i = 0; i <res.length ; i++) {
            let o = {};
            o.value = res[i];
            this.names.push(o);
          }
        });
      },
      querySearch(queryString, cb) {
        let names = this.names;
        var results = queryString ? names.filter(this.createFilter(queryString)) : names;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      createFilter(queryString) {
        return (name) => {
          return (name.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      handleCurrentChange(val) {
        this.page = val;
      },
    }
  }
</script>

<style scoped>
  .table{
      text-align: center;
  }
   .form{
       padding-left: 200px;
   }
</style>
