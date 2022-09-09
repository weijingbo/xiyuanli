<template>
    <div class="wrong">
        <h2>我要报修</h2>
        <el-form label-position="left" style="padding-left: 150px">
            <el-form-item label="请选择单元楼" label-width="120px">
                <el-select v-model="wrong.bname" placeholder="请选择" clearable>
                    <el-option
                            v-for="item in buildings"
                            :key="item.id"
                            :label="item.name"
                            :value="item.name">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="请输入门牌号" label-width="120px">
                <el-input v-model="wrong.number" style="width:200px;"/>
            </el-form-item>
            <el-form-item label="故障描述" label-width="120px">
                <el-input :rows="11" type="textarea" v-model="wrong.detail" class="text"></el-input>
            </el-form-item>
            <el-form-item style="padding-left: 200px">
                <el-button type="primary" @click="submit" >
                    提交
                </el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
  import {insertWrong} from "network/wrong"
  import {getBuildings} from "network/house";
  export default {
    name: "Wrong",
    data() {
      return {
        wrong:{
          uname:'',
          detail:'',
          bname:'',
          number:''
        },
        buildings:[]
      }
    },
    created() {
      this.getBuildings()
    },
    methods: {
      submit() {
        this.wrong.uname = sessionStorage.getItem("uname")
        insertWrong(this.wrong).then(res => {
          if(res==1){
          this.$alert('提交成功')
          }else{
            this.$alert('提交失败')
          }
          }
        )
      },
      getBuildings() {
        getBuildings().then(res => {
            this.buildings.push(...res);
          }
        )
      },
    }
  }
</script>

<style scoped>
    .text {
        height: 250px;
        width: 400px;

    }

    .wrong {
        padding-top: 20px;
        padding-left: 50px
    }

    h2 {
        padding-left: 40%;
    }
</style>
