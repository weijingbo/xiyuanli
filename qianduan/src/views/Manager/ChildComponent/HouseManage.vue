<template>
    <div>
        <!--工具条-->
        <el-col :span="24" class="toolbar">
            <el-form :inline="true" :model="filter">
                <el-form-item lable="按姓名查找" label-width="80px">
                    <el-input v-model="filter.name" placeholder="姓名"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="GetHouseByName()">查询</el-button>
                </el-form-item>
                <el-form-item label-width="100px" label="按楼宇查找">
                    <el-select v-model="filter.bid" placeholder="请选择" clearable>
                        <el-option
                                v-for="item in buildings"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="GetHouse()">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="insert()">新增</el-button>
                </el-form-item>
                <el-form-item label="入住情况" v-if="isShow">
                        {{total}}/{{rate}}
                </el-form-item>
            </el-form>
        </el-col>


        <el-table
                :data="houses.slice((page-1)*7,(page)*7)"
                stripe
                class="table">
            <el-table-column
                    prop="belong"
                    label="楼栋"
                    width="140"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="number"
                    label="门牌号"
                    width="100"
                    align="center"
                    sortable>
            </el-table-column>
            <el-table-column
                    prop="uname"
                    label="房主姓名"
                    width="160"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="size"
                    label="房屋大小"
                    width="160"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="nature"
                    label="房屋性质"
                    width="160"
                    align="center">
            </el-table-column>
            <el-table-column
                    label="操作"
                    align="center"
                    width="320">
                <template slot-scope="scope">
                    <el-button @click="update(scope.row)">
                        修改
                    </el-button>
                    <el-button type="danger" @click="deleteHouse(scope.row.id)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-col :span="24" class="toolbar">
            <el-pagination layout="prev, pager, next"
                           @current-change="handleCurrentChange"
                           :hide-on-single-page = true
                           @prev-click="page--"
                           @next-click="page++"
                           :page-size="7"
                           :total="total"
                           style="float:left;">
            </el-pagination>
        </el-col>


        <el-dialog title="更新楼房信息" :visible.symc="isUpdate">
            <el-form :model="uhouse" style="padding-left: 200px" label-position="left" :rules="rules">
                <el-form-item label="楼栋" label-width="80px">
                    <el-select v-model="uhouse.belong" placeholder="请选择" style="width: 200px" clearable>
                        <el-option
                                v-for="item in buildings"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="门牌号" label-width="80px" prop="number">
                    <el-input v-model="uhouse.number" style="width: 200px"/>
                </el-form-item>
                <el-form-item label="业主" label-width="80px" prop="name">
                    <el-autocomplete
                            v-model="uhouse.uname"
                            :fetch-suggestions="querySearch"
                            placeholder="请输入内容"
                            :trigger-on-focus="false"/>
                </el-form-item>
                <el-form-item label="房间大小" label-width="80px" prop="size">
                    <el-input v-model="uhouse.size" style="width: 200px"/>
                </el-form-item>
                <el-form-item label="房屋性质" label-width="80px">
                    <el-radio-group v-model="uhouse.nature">
                        <el-radio label="住宅" />
                        <el-radio label="商铺"/>
                    </el-radio-group>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="isUpdate = false">取 消</el-button>
                <el-button type="primary" @click="updateHouse()">确 定</el-button>
            </div>
        </el-dialog>

        <!--新增界面-->
        <el-dialog title="新增楼房信息" :visible.symc="isInsert" label-position="left">
            <el-form :model="house" style="padding-left: 200px" label-position="left" :rules="rules">
                <el-form-item label="楼栋" label-width="80px">
                    <el-select v-model="house.belong" placeholder="请选择" style="width: 200px" clearable>
                        <el-option
                                v-for="item in buildings"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="门牌号" label-width="80px" prop="number">
                    <el-input v-model="house.number" style="width: 200px"/>
                </el-form-item>
                <el-form-item label="业主" label-width="80px" prop="name">
                    <el-autocomplete
                            class="inline-input"
                            v-model="house.uname"
                            :fetch-suggestions="querySearch"
                            placeholder="请输入业主姓名"
                            :trigger-on-focus="false"/>
                </el-form-item>
                <el-form-item label="房间大小" label-width="80px" prop="size">
                    <el-input v-model="house.size" style="width: 200px"/>
                </el-form-item>
                <el-form-item label="房屋性质" label-width="80px">
                    <el-radio-group v-model="house.nature">
                        <el-radio label="住宅" />
                        <el-radio label="商铺"/>
                    </el-radio-group>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="isInsert = false">取 消</el-button>
                <el-button type="primary" @click="insertHouse()">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
  import {
    getNames,
    getHouse,
    getHouseByName,
    getBuildings,
    getHouses,
    updateHouse,
    insertHouse,
    deleteHouse,
    rates,rate
  } from "network/house"
import rules from 'element-ui-validation'
  export default {
    name: "House",
    data() {
      return {
        filter: {
          name: null,
          bid: null,
        },
        names:[],
        buildings: [],
        houses: [],
        isInsert: false,
        isUpdate: false,
        isShow:true,
        house: {
          belong: '',
          size: '',
          uname: '',
          nature: '',
          number: ''
        },
        uhouse: {
          belong: '',
          size: '',
          uname: '',
          nature: '',
          number: ''
        },
        page: 1,
        pageSize: 7,
        total: 0,
        rate:0,
        rules:{
            number:rules.integer("门牌号",3205),
            size:rules.money("房屋面积",1000)
        }
      }
    },
    created() {
      this.getHouses();
      this.getBuildings();
      this.GetNames();
    },
    methods: {

      getBuildings() {
        getBuildings().then(res => {
          this.buildings.push(...res);
        })
      },

      getHouses() {
        this.isShow = true;
        this.Rates();
        getHouses().then(res => {
          this.houses = [];
            this.houses.push(...res);
            this.total = res.length;
            this.page = 1;
          }
        )
      },

      update(row) {
        this.uhouse = row;
        this.isUpdate = true;
      },

      insert() {
        this.isInsert = true;
      },

      updateHouse() {
        for (let i = 0; i < this.buildings.length; i++) {
          if (this.buildings[i].name === this.uhouse.belong) {
            this.uhouse.belong = this.buildings[i].id
          }
        }
        updateHouse(this.uhouse).then(res => {
          this.isUpdate = false;
          if (res === 1) {
            this.$alert("更新成功");
            this.getHouses();
            this.page = 1;
          } else {
            this.$alert("更新失败")
          }
        })
      },

      GetHouse() {
        this.isShow = true;
        this.Rate();
        getHouse(this.filter.bid).then(res => {
          this.houses = [];
          this.houses.push(...res);
          this.total = res.length;
          this.page = 1;
        })
      },

      insertHouse() {
        insertHouse(this.house).then(res => {
          if (res === 1) {
            this.isInsert = false;
            this.$alert("插入成功");
            this.getHouses();
            this.page = 1;
          } else {
            this.$alert("插入失败");
          }
        })
      },
      deleteHouse(id) {
        this.$confirm('确认删除该记录吗?', '提示', {
          'type': 'warning'
        }).then(() => {
          deleteHouse(id).then(res => {
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.getHouses();
          })
        })
      },
      GetHouseByName() {
        this.isShow = false;
        getHouseByName(this.filter.name).then(res => {
          this.houses = [];
          this.houses.push(...res);
          this.total = res.length;
          this.page = 1;
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
      handleCurrentChange(val) {
        this.page = val;
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
      Rates(){
        rates().then(res=>{
          this.rate = res;
          }
        )
      },
      Rate(){
        rate(this.filter.bid).then(res=>{
            this.rate = res;
          }
        )
      }

    }
  }
</script>

<style scoped>
    .table {
        text-align: center;
    }
</style>
