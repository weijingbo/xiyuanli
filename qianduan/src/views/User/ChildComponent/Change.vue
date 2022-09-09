<template>
    <div class="change">
        <div class="house">
            <el-card >
                <div slot="header" class="clearfix">
                    <span><b>我的房屋信息</b></span>
                </div>
                <el-form :inline="true" :model="house" label-position="left">
                    <el-form-item label="单元号" label-width="80px">
                        <el-tag class="sp">{{house.belong}}</el-tag>
                    </el-form-item>
                    <el-form-item label="门牌号" label-width="80px">
                        <el-tag class="sp">{{house.number}}</el-tag>
                    </el-form-item>
                    <el-form-item label="房屋大小" label-width="80px">
                        <el-tag class="sp">{{house.size}}㎡</el-tag>
                    </el-form-item>
                    <el-form-item label="房屋性质" label-width="80px">
                        <el-tag class="sp">{{house.nature}}</el-tag>
                    </el-form-item>
                </el-form>
            </el-card>
       <el-card style="margin-top:50px">
           <div slot="header" class="clearfix">
               <span><b>我的车位信息</b></span>
           </div>
           <el-form :inline="true" :model="car" label-position="left">
               <el-form-item label="车位信息" label-width="80px">
                   <el-tag class="sp">{{car.detail}}</el-tag>
               </el-form-item>
               <el-form-item label="车牌号" label-width="80px">
                   <el-tag class="sp">{{car.number}}</el-tag>
               </el-form-item>
           </el-form>
       </el-card>
        </div>
        <div class="user">
            <el-card>
                <div slot="header">
                    <span><b>我的个人信息</b></span>
                    <el-button type="text" @click="Update()" size="mini" class="bt">修改</el-button>
                </div>
                <el-form :model="user" :rules="rules" label-position="left">
                    <el-form-item label="用户姓名" label-width="80px">
                        <el-input v-model="user.name" class="input" :disabled="dis"/>
                    </el-form-item>
                    <el-form-item label="用户密码" label-width="80px" prop="password">
                        <el-input v-model="user.password" class="input"/>
                    </el-form-item>
                    <el-form-item label="用户电话" label-width="80px" prop="phone">
                        <el-input v-model="user.phone" class="input" />
                    </el-form-item>
                    <el-form-item label="用户工作" label-width="80px" prop="work">
                        <el-input v-model="user.work" class="input" />
                    </el-form-item>
                    <el-form-item label="年龄" label-width="80px" prop="age">
                        <el-input v-model="user.age" class="input" />
                    </el-form-item>
                    <el-form-item label="入住时间" label-width="80px">
                        <el-input v-model="user.stime" class="input" :disabled="dis"/>
                    </el-form-item>
                </el-form>
            </el-card>
        </div>
    </div>
</template>

<script>
    import {getHouseByName} from "network/house";
    import {getUser} from "network/manager";
    import {getParking} from "network/manager";
    import {updateUser} from"network/user";

    export default {
    name: "Change",
    data(){
      var checkPhone = (rule, value, callback) => {
        const phoneReg = /^1[3|4|5|7|8][0-9]{9}$/
        if (!value) {
          return callback(new Error('电话号码不能为空'))
        }
        setTimeout(() => {
          // Number.isInteger是es6验证数字是否为整数的方法,但是我实际用的时候输入的数字总是识别成字符串
          // 所以我就在前面加了一个+实现隐式转换

          if (!Number.isInteger(+value)) {
            callback(new Error('请输入数字值'))
          } else {
            if (phoneReg.test(value)) {
              callback()
            } else {
              callback(new Error('电话号码格式不正确'))
            }
          }
        }, 100)
      };
      return{
        user:{
          name: '请稍等',
          password: '请稍等',
          work: '请稍等',
          stime: '请稍等',
          phone: '请稍等',
          age:'请稍等',
          sex: '请稍等'

        },
        car:{
          detail:'您未购买车位',
          number:'暂未查询到',
        },
        house:{
          belong: '请稍等',
          size: '请稍等',
          uname: '请稍等',
          nature: '请稍等',
          number: '请稍等'
        },
        dis:true,
        rules:{
          password:[
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 0, max: 20, message: '密码太长了吧', trigger: 'blur' }
          ],
          phone:[
            { required: true, message: '请输入业主电话', trigger: 'blur' },
            { validator:checkPhone,trigger: 'blur'}
          ],
          work:[
            { required: true, message: '请输入业主工作', trigger: 'blur' },
          ],
          age:[
            { required: true, message: '请输入业主年龄', trigger: 'blur' },
            { type:'number',min:18, max:120, message: '年龄请在18-120之间',trigger: 'blur'}
          ],
          sex:[
            {required:true, message:'请选择用户性别',trigger:'change'}
          ]
        }
      }
    },
      created() {
      this.Init();
      },
      methods:{
      Init(){
        let name = sessionStorage.getItem("uname");
        getHouseByName(name).then(res=>{
          this.house = res[0]
        });
        getUser(name).then(res=>{
          this.user = res[0]
        });
        getParking(name).then(res=>{
          if(res[0].id){
            this.car=res[0]
          }
        });
      },
        Update(){
        this.$confirm('确认要更改信息吗？','提示',{
          type:'warning'
        }).then(()=>{
          updateUser(this.user).then(res=>{
            if(res ===1 ){
              this.$alert("更新成功！")
            } else(
              this.$alert("更新失败！")
            )
          })
        })
        },
      }
  }
</script>

<style scoped>
    .input{
        width: 200px;
    }
    .tit{
        padding-left:100px;
    }
    .sp{
        width: 100px;
        text-align: center;
    }
    .house{
        width: 450px;
        position: absolute;
        right: 50px;
        padding-right: 50px;
    }
    .user{
        width: 400px;
    }
    .bt{
        float: right;
    }
    .change{
        height:540px;
        padding-top: 20px;
    }
</style>
