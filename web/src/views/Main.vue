<template>
  <div>
    <el-carousel :interval="4000" type="card" height="240px">
      <el-carousel-item v-for="(item, i) in courses.slice(0, 5)">
        <el-skeleton animated :loading="loading">
          <template slot="template">
            <el-skeleton-item variant="image" style="width: 100%; height: 240px" />
          </template>
          <template>
            <div class="background" variant="image" :style="`background:${extractColorByName(item.courseName)}`">
             
              <h1 style="font-size: xx-large;">{{ item.courseName }}</h1>
            </div>
          </template>
        </el-skeleton>
      </el-carousel-item>
    </el-carousel>

    <span>
      <h2>所有课程</h2>
      <el-divider></el-divider>
    </span>
    <div>
      <el-skeleton animated :loading="loading">
        <template slot="template">
          <div class="card-layout">
            <el-card class="card" v-for="x in 8"  :body-style="{ padding: '0px' }">
              <el-skeleton-item variant="image" style="height: 180px" />
            </el-card>
          </div>
        </template>
        <template class="card-layout">
          <div class="card-layout">
            <el-card class="card" v-for="x in courses" :body-style="{ padding: '0px' }">
              <div class="background" style="width: 100%; height: 180px"
                :style="`background:${extractColorByName(x.courseName)}`">
                <div style="font-size: x-large;"> {{ x.courseName }} </div>
              </div>
            </el-card>
          </div>
        </template>
      </el-skeleton>
    </div>
  </div>
</template>

<script>
export default {
  name: "Main",
  created() {
    setTimeout(this.queryCourses, 50);
    setTimeout(()=>{this.loading = true}, 25);
  },
  data() {
    return {
      loading: false,
      courses: [
        { courseName: "Test1" },
        { courseName: "Test2" },
        { courseName: "Test3" },
        { courseName: "Test4" },
        { courseName: "Test5" },
      ]
    };
  },
  methods: {
    extractColorByName(name) {
      var temp = [];
      temp.push("#");
      for (let index = 0; index < name.length; index++) {
        temp.push(parseInt(name[index].charCodeAt(0), 10).toString(16));
      }
      return temp.slice(0, 5).join("").slice(0, 4);
    },
    queryCourses(){
      this.$axios
      .post("/queryCourse", { pageNum: 1, pageSize: 999 })
      .then((res) => {
        this.loading = true;
        this.courses = res.data.courses;
        console.log(this.courses);
      })
      .then(() => {
        this.loading = false;
      });
    }
  },
};
</script>

<style scoped>
.card-layout {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;

  margin-left: -0.25rem;
  margin-right: -0.25rem;
}

.card {
  flex-basis: 24%;
  margin-bottom: 15px;
}

.background {
  width: 100%;
  height: 240px;
  justify-content: center;
  align-items: center;
  display: flex;
  justify-content: center;
}
</style>