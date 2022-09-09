<template>
    <div class="context">
            <div class="carousel">
                <el-carousel :interval="5000" arrow="always" type="card">
                    <el-carousel-item v-for="item in imgs" :key="item.number">
                        <img :src="item.src">
                    </el-carousel-item>
                </el-carousel>
            </div>
            <h2>小区简介</h2>
            <p>西苑里总占地约1000亩，总建筑面积约200万方，以新亚洲国风建筑群落鼎立洛北。
                16条立体交通，揽尽一城繁华；4所社区幼儿园、4所知名学府环伺，助力孩子优越起步；
                5园环绕，开启全新鲜氧生活；丰富医疗配套，为健康保驾护航。
                建业100%控股，给你更完美的定鼎府！西苑里，三期建面约97-138㎡升级新品，倾献洛城。</p>
    </div>
</template>


<script>
  export default {
    name: "Detail",
    data(){
      return{
        imgs:[
          {
            number:1,
            src:require("assets/img/1.jpg")
          },
          {
            number:2,
            src:require("assets/img/2.jpg")
          },
          {
            number:3,
            src:require("assets/img/3.jpg")
          }
        ]


      }
    }
  }
</script>

<style scoped>
    .context {
        position: absolute;
        right: 0px;
        width: 1100px;
        padding-right: 20px;
    }
   .carousel {
       width: 1100px;
       padding-top: 10px;
   }
    .carousel img{
        width: 100%;
    }
</style>
