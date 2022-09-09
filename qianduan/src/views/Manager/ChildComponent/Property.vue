<template>
    <div>
        <el-col :span="24" class="toolbar" >
            <el-form :inline="true">
                <el-form-item>
                    <el-input placeholder="请输入年份" v-model="year"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="GetProByYear()">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-input placeholder="请输入用户名" v-model="name" style="width: 150px"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="GetProByName()">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-upload
                            class="upload-demo"
                            action=""
                            :on-change="handleChange"
                            :on-remove="handleRemove"
                            :on-exceed="handleExceed"
                            :limit="limitUpload"
                            accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel"
                            :auto-upload="false">
                        <el-button type="primary">导入物业费</el-button>
                    </el-upload>
                </el-form-item>
                <el-form-item>
                    <el-button> <el-link :underline="false" href="http://localhost:8080/getPropertyExcel">获取表格</el-link></el-button>
                </el-form-item>
                <el-form-item>
                    <el-button @click="GetNoPay()" >查看未缴费用户</el-button>
                </el-form-item>
                <el-form-item v-if="urge">
                    <el-button @click="SendMessage()">通知缴费</el-button>
                </el-form-item>
            </el-form>
        </el-col>


        <el-table
                border
                height="450"
                :data="properties"
                @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="uname" label="业主姓名" width="160" align="center">
            </el-table-column>
            <el-table-column prop="year" label="年份" width="160" align="center">
            </el-table-column>
            <el-table-column label="物业费" width="160" align="center">
                <template slot-scope="scope">
                    {{scope.row.price}}元
                </template>
            </el-table-column>
            <el-table-column prop="state" label="状态" width="160" align="center">
            </el-table-column>
            <el-table-column label="操作" v-if="isshow" align="center">
                <template slot-scope="scope">
                    <el-button type="danger" @click="DeletePro(this.row.id)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-button @click="InsertProperties">批量上传</el-button>
        <el-button @click="DeleteProperties" type="danger" v-if="isshow">批量删除</el-button>
    </div>
</template>

<script>
  import {getByYear,getByName,deleteProperty,deleteProperties,insertProperties,getNo,Message} from 'network/manager'

  export default {
    name: "Property",
    data() {
      return {
        name:'',
        year:null,
        limitUpload: 1,
        fileTemp: null,
        file: null,
        properties: [],
        Selection: [],
        isshow: false,
        urge:false
      }
    },
    methods: {
      //上传文件时处理方法
      handleChange(file, fileList) {
        this.fileTemp = file.raw;
        if (this.fileTemp) {
          if ((this.fileTemp.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet')
            || (this.fileTemp.type === 'application/vnd.ms-excel')) {
            this.importProperty(this.fileTemp);
          } else {
            this.$message({
              type: 'warning',
              message: '附件格式错误，请删除后重新上传！'
            })
          }
        } else {
          this.$message({
            type: 'warning',
            message: '请上传附件！'
          })
        }
      },
      //超出最大上传文件数量时的处理方法
      handleExceed() {
        this.$message({
          type: 'warning',
          message: '超出最大上传文件数量的限制！'
        });
        return;
      },
      //移除文件的操作方法
      handleRemove(file, fileList) {
        this.fileTemp = null
      },
      //
      importProperty(obj) {
        this.isshow = false;
        let _this = this;
        this.file = event.currentTarget.files[0];
        var f = this.file;
        var reader = new FileReader();
        FileReader.prototype.readAsBinaryString = function (f) {
          var binary = "";
          var wb; //读取完成的数据
          var outdata;
          var reader = new FileReader();
          reader.onload = function (e) {
            var bytes = new Uint8Array(reader.result);
            var length = bytes.byteLength;
            for (var i = 0; i < length; i++) {
              binary += String.fromCharCode(bytes[i]);
            }
            //此处引入，用于解析excel
            var XLSX = require("xlsx");
            wb = XLSX.read(binary, {type: "binary"});
            outdata = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]);
            console.log(outdata);
            let arr = [];
            outdata.map(v => {
              let obj = {};
              obj.uname = v['业主姓名'];
              obj.uid = v['业主编号'];
              obj.year = v['年份'];
              obj.price = v['物业费'];
              obj.state = v['状态'];
              arr.push(obj)
            });
            _this.properties = arr;
            return arr;
          };
          reader.readAsArrayBuffer(f);
        };
        reader.readAsBinaryString(f);
      },
      handleSelectionChange(val) {
        this.Selection = val;
      },
      InsertProperties() {
        insertProperties(this.Selection).then(res => {
          if (res === 1) {
            this.$alert("物业费已添加!")
          } else {
            this.$alert("请检查上传内容！")
          }
        })
      },
      DeleteProperties() {
        deleteProperties(this.Selection).then(res => {
          if (res === 1) {
            this.$alert("已删除！");
          } else {
            this.$alert("未删除！");
          }
        })
      },
      DeletePro(id) {
        deleteProperty(id).then(res => {
          if (res === 1) {
            this.$alert("删除成功！");
            this.GetWater();
          } else {
            this.$alert("出了点故障哦！");
          }
        })
      },
      GetProByYear(){
        getByYear(this.year).then(res => {
          this.properties = [];
          this.properties.push(...res);
        });
        this.isshow = true;
      },
      GetProByName(){
        getByName(this.name).then(res=>{
          this.properties = [];
          this.properties.push(...res)
        });
        this.isshow = true;
      },
      GetNoPay(){
        getNo().then(res=>{
          this.properties = [];
          this.properties.push(...res);
          this.urge=true
        })
      },
      SendMessage(){
        Message().then(
          this.$alert("消息已发送")
        )
      }
    }
  }
</script>

<style scoped>

</style>
