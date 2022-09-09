<template>
    <div>
        <el-col :span="24" class="toolbar" >
            <el-form :inline="true">
                <el-form-item label="按时间查看" label-width="120px">
                    <el-date-picker
                            v-model="times"
                            type="daterange"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="GetPatrol()">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="Insert()">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>




        <el-table
                :data="patrols.slice((page-1)*7,(page)*7)"
                style="width: 100%"
                :default-sort = "{prop: 'date', order: 'descending'}">
            <el-table-column
                width="180">
            </el-table-column>
            <el-table-column
                    prop="date"
                    label="日期"
                    sortable
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="ename1"
                    label="员工1"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="ename2"
                    label="员工2"
                    width="180">
            </el-table-column>
            <el-table-column
                    label="操作"
                    align="center">
                <template slot-scope="scope">
                    <el-button  @click="Update(scope.row)" >
                        修改
                    </el-button>
                    <el-button type="danger" @click="DeletePatrol(scope.row.id)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-col :span="24" class="toolbar">
            <el-pagination layout="prev, pager, next"
                           @current-change="handleCurrentChange"
                           :hide-on-single-page = true
                           @prev-click ="page--"
                           @next-click ="page++"
                           :page-size="7"
                           :total="total"
                           style="float:left;">
            </el-pagination>
        </el-col>

        <el-dialog :visible.symc="isUpdate">
            <el-form :model="patrol" class="form" label-position="left">
                <el-form-item label="时间" label-width="80px">
                    <el-date-picker
                            v-model="patrol.date"
                            type="date"
                            placeholder="选择日期"
                            class="input">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="员工1" label-width="80px">
                    <el-select v-model="patrol.ename1" placeholder="请选择" class="input">
                        <el-option
                                v-for="item in names"
                                :value="item">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="员工2" label-width="80px" >
                    <el-select v-model="patrol.ename2" placeholder="请选择" class="input">
                        <el-option
                                v-for="item in names"
                                :value="item">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="isUpdate = false">取 消</el-button>
                <el-button type="primary" @click="UpdatePatrol()">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog :visible.symc="isInsert" >
            <el-form :model="patrol" class="form" label-position="left">
                <el-form-item label="时间" label-width="80px">
                    <el-date-picker
                            v-model="patrol.date"
                            type="date"
                            placeholder="选择日期"
                            clearable
                            class="input">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="员工1" label-width="80px">
                    <el-select v-model="patrol.ename1" placeholder="请选择" class="input" clearable>
                        <el-option
                                v-for="item in names"
                                :value="item">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="员工2" label-width="80px">
                    <el-select v-model="patrol.ename2" placeholder="请选择" class="input" clearable>
                        <el-option
                                v-for="item in names"
                                :value="item">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="isInsert = false">取 消</el-button>
                <el-button type="primary" @click="InsertPatrol()">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {getNames,getPatrol,updatePatrol,deletePatrol,insertPatrol,getPatrols} from 'network/manager'
  export default {
    name: "Patrol",
    data(){
      return{
        times:null,
        patrols:[],
        names:[],
        page:1,
        total:0,
        isInsert:false,
        isUpdate:false,
        patrol:{
          date:'',
          ename1:'',
          ename2:''
        }
      }
    },
    created() {
      this.GetNames();
      this.GetPatrols();
    },
    methods:{
      GetNames(){
        getNames().then(res=>{
          this.names.push(...res)
        })
      },
      GetPatrols(){
        getPatrols().then(res=>{
          this.patrols = [];
          this.patrols.push(...res);
          this.total = res.length;
          this.page = 1;
        })
      },
      DeletePatrol(id){
        this.$confirm('确认删除该记录吗?', '提示', {
          'type': 'warning'
        }).then(() => {
          deletePatrol(id).then(res => {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.getPatrols()
          })
        })
      },
      GetPatrol(){
        getPatrol(this.times).then(res=>{
          this.patrols = [];
          this.patrols.push(...res);
          this.total = res.length;
          this.page = 1;
        })
      },
      Insert(){
        this.isInsert = true
      },
      InsertPatrol(){
        insertPatrol(this.patrol).then(res=>{
          if (res ===1 ){
            this.$alert("新的巡逻安排已添加！")
          }else{
            this.$alert("添加失败！")
          }
          this.isInsert = false;
          this.GetPatrols();
          })
      },
      Update(row){
        this.patrol = row;
        this.isUpdate = true;
      },
      UpdatePatrol(){
        updatePatrol(this.patrol).then(res=>{
          if (res ===1 ){
            this.$alert("更新成功！")
          }else{
            this.$alert("更新失败！")
          }
          this.isUpdate = false
        })
      },
      handleCurrentChange(val) {
        this.page = val;
      }
    }
  }
</script>

<style scoped>
    .input{
        width: 200px;
    }
    .form{
        padding-left: 200px;
    }
</style>
