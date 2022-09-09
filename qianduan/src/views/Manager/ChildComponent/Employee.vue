<template>
    <div>
        <!--工具条-->
        <el-col :span="24" class="toolbar" >
            <el-form :inline="true" :model="filter">
                <el-form-item>
                    <el-input v-model="filter.name" placeholder="姓名"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getEmployee()">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="insert()">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>


        <el-table
                :data="employees.slice((page-1)*7,(page)*7)"
                stripe
                class="table">
            <el-table-column
                    prop="name"
                    label="员工姓名"
                    width="160"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="age"
                    label="年龄"
                    width="120"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="sex"
                    label="性别"
                    width="120"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="phone"
                    label="电话"
                    width="160"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="work"
                    label="工作"
                    width="140"
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
                    <el-button type="danger" @click="deleteEmployee(scope.row.id)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-col :span="24" class="toolbar">
            <el-pagination layout="prev, pager, next"
                           @current-change="handleCurrentChange"
                           @prev-click ="page--"
                           @next-click ="page++"
                           :hide-on-single-page = true
                           :page-size="7"
                           :total="total"
                           style="float:left;">
            </el-pagination>
        </el-col>


        <el-dialog :visible.symc="isUpdate">
            <el-form :model="employee" class="form" label-position="left" :rules="rules">
                <el-form-item label="员工姓名" label-width="80px" prop="name">
                    <el-input v-model="employee.name" class="input"  />
                </el-form-item>
                <el-form-item label="员工密码" label-width="80px" prop="password">
                    <el-input v-model="employee.password" class="input"/>
                </el-form-item>
                <el-form-item label="年龄" label-width="80px" prop="age">
                    <el-input v-model="employee.age" class="input" />
                </el-form-item>
                <el-form-item label="电话" label-width="80px" prop="phone">
                    <el-input v-model="employee.phone" class="input" />
                </el-form-item>
                <el-form-item label="工作" label-width="80px" prop="work">
                    <el-input v-model="employee.work" class="input"/>
                </el-form-item>
                <el-form-item label="性别" label-width="80px">
                    <el-radio v-model="employee.sex" label="男">男</el-radio>
                    <el-radio v-model="employee.sex" label="女">女</el-radio>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="isUpdate = false">取 消</el-button>
                <el-button type="primary" @click="updateEmployee()">确 定</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog :visible.symc="isInsert">
            <el-form :model="employee" class="form" label-position="left" :rules="rules">
                <el-form-item label="员工姓名" label-width="80px" prop="name">
                    <el-input v-model="employee.name" class="input" clearable/>
                </el-form-item>
                <el-form-item label="员工密码" label-width="80px" prop="password">
                    <el-input v-model="employee.password" class="input" clearable/>
                </el-form-item>
                <el-form-item label="年龄" label-width="80px" prop="age">
                    <el-input v-model="employee.age" class="input" clearable/>
                </el-form-item>
                <el-form-item label="电话" label-width="80px" prop="phone">
                    <el-input v-model="employee.phone" class="input" clearable/>
                </el-form-item>
                <el-form-item label="工作" label-width="80px" prop="work">
                    <el-input v-model="employee.work"class="input" clearable/>
                </el-form-item>
                <el-form-item label="性别" label-width="80px">
                    <el-radio v-model="employee.sex" label="男">男</el-radio>
                    <el-radio v-model="employee.sex" label="女">女</el-radio>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="isInsert = false">取 消</el-button>
                <el-button type="primary" @click="insertEmployee()">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
  import {getEmployees,updateEmployee,insertEmployee,getEmployee,deleteEmployee} from "network/manager"
  import rules from "element-ui-validation";
  export default {
    name: "Employee",
    data(){
      return{
        filter:{
          name:''
        },
        employees:[],
        isInsert:false,
        isUpdate:false,
        employee:{
          name:'',
          password:'',
          sex:'',
          age:'',
          phone:'',
          work:''
        },
        page:1,
        pageSize:7,
        total: 0,
        rules:{
            name:rules.string("请输入名字",5),
            password:rules.string("请输入密码",20),
            phone:rules.phone("电话号码"),
            work:rules.string("请输入工作"),
            age:rules.integer("请输入员工年龄",99,18),
        }
      }
    },
    created() {
      this.getEmployees()
    },
    methods: {
      getEmployees() {
        this.employees = [];
        getEmployees().then(res => {
            this.employees.push(...res);
            this.total=res.length;
            this.page = 1;
          }
        )
      },
      update(row) {
        this.employee = row;
        this.isUpdate = true;
      },
      insert() {
        this.isInsert = true;
      },
      updateEmployee() {
        updateEmployee(this.employee).then(res => {
          this.isUpdate = false;
          if (res === 1) {
            this.$alert("更新成功");
            this.getEmployees();
          } else {
            this.$alert("更新失败")
          }
        })
      },
      getEmployee() {
        getEmployee(this.filter.name).then(res => {
          this.employees = [];
          this.employees.push(...res);
          this.page = 1;
          this.total = res.length;
        })
      },
      insertEmployee() {
        insertEmployee(this.employee).then(res => {
          if (res === 1) {
            this.isInsert = false;
            this.$alert("插入成功");
            this.getEmployees();
          } else {
            this.$alert("插入失败");
          }
        })
      },
      deleteEmployee(id) {
        this.$confirm('确认删除该记录吗?', '提示', {
          'type': 'warning'
        }).then(() => {
          deleteEmployee(id).then(res => {
            this.getEmployees();
            this.$message({
              message: '删除成功',
              type: 'success'
            });

          })
        })
      },

      handleCurrentChange(val) {
        this.page = val;
      },
    }
  }
</script>

<style scoped>
    .form{
        padding-left: 200px;
    }
    .input{
        width: 200px;
    }
</style>
