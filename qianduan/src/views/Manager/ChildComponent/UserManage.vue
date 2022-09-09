<template>
    <div>
        <!--头部-->
        <el-col :span="24" class="toolbar">
            <el-form :inline="true" :model="filter">
                <el-form-item>
                    <el-input v-model="filter.name" placeholder="姓名"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getUser()">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="isInsert = true">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>
        <!--数据展示-->
        <el-table
                :data="users.slice((page-1)*pageSize,(page)*pageSize)"
                stripe
                style="width: 100%">
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="80"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="sex"
                    label="性别"
                    width="80"
            align="center">
            </el-table-column>
            <el-table-column
                    prop="age"
                    label="年龄"
                    width="80"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="phone"
                    label="电话"
                    width="180"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="work"
                    label="工作"
                    width="180"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="stime"
                    label="入住时间"
                    width="180"
                    align="center">
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="180"
                    align="center">
                <template slot-scope="scope">
                    <el-button @click="changeUser(scope.row)">
                        编辑
                    </el-button>
                    <el-button type="danger" @click="deleteUser(scope.row.id)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--table分页-->
        <el-col :span="24" class="toolbar">
            <el-pagination layout="prev, pager, next"
                           @current-change="handleCurrentChange"
                           @prev-click ="page--"
                           :hide-on-single-page = true
                           @next-click ="page++"
                           :page-size=pageSize
                           :total=total
                           style="float:left;">
            </el-pagination>
        </el-col>


        <el-dialog title="更新用户" :visible.sync="isUpdate" >
            <el-form ref="user"  :model="user" style="padding-left:200px" label-position="left" :rules="rules">
                <el-form-item label="用户姓名" label-width="80px" prop="name">
                    <el-input v-model="user.name" style="width: 200px"/>
                </el-form-item>
                <el-form-item label="用户密码" label-width="80px" prop="password">
                    <el-input v-model="user.password" style="width: 200px"/>
                </el-form-item>
                <el-form-item label="用户电话" label-width="80px" prop="phone">
                    <el-input v-model="user.phone" style="width: 200px"/>
                </el-form-item>
                <el-form-item label="用户工作" label-width="80px" prop="work">
                    <el-input v-model="user.work" style="width: 200px"/>
                </el-form-item>
                <el-form-item label="年龄  " label-width="80px" prop="age">
                    <el-input v-model="user.age" style="width: 100px"/>
                </el-form-item>
                <el-form-item label="入住时间" label-width="80px" prop="time">
                    <div class="block">
                        <el-date-picker
                                v-model="user.stime"
                                type="date"
                                placeholder="选择日期时间"
                                style="width:200px">
                        </el-date-picker>
                    </div>
                </el-form-item>
                <el-form-item label="用户性别" label-width="80px" prop="sex">
                    <el-radio v-model="user.sex" label="男">男</el-radio>
                    <el-radio v-model="user.sex" label="女">女</el-radio>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="isUpdate = false">取 消</el-button>
                <el-button type="primary" @click="updateUser()">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="新增用户信息"
                   :visible.sync="isInsert"
                   label-position="left">
            <el-form :model="user" style="padding-left:200px" :rules="rules">
                <el-form-item label="用户姓名" label-width="80px" prop="name">
                    <el-input v-model="user.name" style="width: 200px"/>
                </el-form-item>
                <el-form-item label="用户密码" label-width="80px" prop="password">
                    <el-input v-model="user.password" style="width: 200px"/>
                </el-form-item>
                <el-form-item label="用户电话" label-width="80px" prop="phone">
                    <el-input v-model="user.phone" style="width: 200px" />
                </el-form-item>
                <el-form-item label="用户工作" label-width="80px" prop="work">
                    <el-input v-model="user.work" style="width: 200px" />
                </el-form-item>
                <el-form-item label="年龄" label-width="80px" prop="age">
                    <el-input v-model="user.age" style="width: 100px" />
                </el-form-item>
                <el-form-item label="入住时间" label-width="80px" prop="date">
                    <div class="block">
                        <el-date-picker
                                v-model="user.stime"
                                type="date"
                                placeholder="选择日期时间"
                                style="width: 200px">
                        </el-date-picker>
                    </div>
                </el-form-item>
                <el-form-item label="用户性别" label-width="80px" prop="sex">
                    <el-radio v-model="user.sex" label="男">男</el-radio>
                    <el-radio v-model="user.sex" label="女">女</el-radio>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="isInsert = false">取 消</el-button>
                <el-button type="primary" @click="InsertUser()">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
  import {getUsers, getUser, insertUser, deleteUser} from "network/manager";
  import {updateUser} from "network/user";
  import rules from 'element-ui-validation';
  export default {
    name: "UserManage",
    data() {
      return {
        filter: {
          name: ''
        },
        users: [],
        user: {
          id: '',
          name: '',
          password: '',
          work: '',
          stime: '',
          phone: '',
          age:'',
          sex: ''
        },
        isUpdate: false,
        isInsert: false,
        page: 1,
        pageSize: 7,
        total: 0,
        rules: {
          name:rules.string("请输入名字",5),
          password:rules.string("请输入密码",20),
          phone:rules.phone("电话号码"),
          work:rules.string("请输入工作"),
          age:rules.integer("请输入业主年龄",99,18),
        }
      }
    },
    created() {
      this.getUsers()
    },
    methods: {
      getUsers() {

        getUsers().then(res => {
          this.users = [];
          this.users.push(...res);
          this.total = res.length;
          this.page = 1
        })
      },
      changeUser(row) {
        this.user = row;
        this.isUpdate = true;
      },
      updateUser() {
        this.isUpdate = false;
        updateUser(this.user).then(res => {
          if (res === 1) {
            this.$alert("更新成功！")
          } else {
            this.$alert("更新失败！")
          }
        })
      },
      deleteUser(id) {
        this.$confirm('确认删除该记录吗?', '提示', {
          'type': 'warning'
        }).then(() => {
          deleteUser(id).then(res => {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.getUsers();
            this.page = 1
          })
        })
      },
      getUser() {
        getUser(this.filter.name).then(res => {
          this.users = [];
          this.users.push(...res);
          this.total = res.length;
          this.page = 1;
        })
      },
      InsertUser() {
        this.isInsert = false;
        insertUser(this.user).then(res => {
          if (res === 1) {
            this.$alert("插入成功！");
            this.getUsers();
          } else {
            this.$alert("插入失败！");
          }
        })
      },
      handleCurrentChange(val){
        this.page = val;
      }
    }
  }
</script>

<style scoped>
</style>
