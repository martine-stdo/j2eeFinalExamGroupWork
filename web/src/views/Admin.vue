<template>
  <div style="width: 100%;height: 100%;display: flex;flex-direction: column;">

    <span style="font-size: 26px; height: 40px; font-weight: 500;">课程管理</span>

    <el-row type="flex" align="center" justify="space-between" style="width: 100%;margin-top: 20px;">
      <el-form :inline="true">
        <el-form-item style="margin-left: 20px;">
          <el-button type="primary">查询</el-button>
        </el-form-item>
      </el-form>
      <div>
        <el-button type="primary" @click="createCourse">创建课程</el-button>
        <el-button type="danger" @click="deletes">删除课程</el-button>
      </div>
    </el-row>

    <el-table ref="studentTable" :data="tableData" border style="width: 100%">
      <el-table-column type="selection" width="55" />
      <el-table-column prop="courseId" label="课程编号" width="155" />
      <el-table-column prop="courseName" label="课程名名" width="250" />
      <el-table-column prop="courseDescription" label="描述" width="250" />
      <el-table-column prop="courseStartTime" label="开始时间" width="250" />
      <el-table-column prop="courseEndTime" label="结束时间" width="250" />
    </el-table>

    <el-dialog title="创建课程" :visible.sync="showDialog">
      <el-form :model="newCourse">
        <!-- Add your form items here... -->
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="newCourse.courseName"></el-input>
        </el-form-item>
        <el-form-item label="课程描述" prop="courseDescription">
          <el-input v-model="newCourse.courseDescription"></el-input>
        </el-form-item>
        <el-form-item label="开始时间" prop="courseStartTime">
          <el-date-picker type="date" placeholder="选择日期" v-model="newCourse.courseStartTime"
            style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="courseEndTime">
          <el-date-picker type="date" placeholder="选择日期" v-model="newCourse.courseEndTime"
            style="width: 100%;"></el-date-picker>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showDialog = false">取消</el-button>
        <el-button type="primary" @click="submitCourse">确定</el-button>
      </span>
    </el-dialog>
    <div style="width: 100%; display: flex;justify-content: center; align-items: center;">
      <div>
        <span style="padding-right: 7px; color: gray;font-size: small;">共 {{totalPage}} 页</span>
      </div>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
        :page-sizes="[5, 10, 15, 20]" :page-size="pageSize"
        layout=" sizes, prev, pager, next, jumper" />
    </div>

  </div>
</template>

<script>
export default {
  name: "Admin",
  created() {
    this.queryCourses(this.currentPage, this.pageSize);
  },
  data() {
    return {
      currentPage: 1,
      pageSize: 5,
      totalPage: 0,
      keyword: '',
      tableData: [],
      showDialog: false,
      newCourse: {
        courseName: '',
        courseDescription: '',
        courseStartTime: '',
        courseEndTime: ''
      }
    }
  },
  methods: {
    handleSizeChange(pageSize) {
      console.log("pageSize:", pageSize)
      this.queryCourses(this.currentPage, pageSize)
    },
    handleCurrentChange(pageNum) {
      console.log("pageNum:", pageNum)
      this.queryCourses(pageNum, this.pageSize)
    },
    queryCourses(currentPage, pageSize) {
      this.$axios
        .post("/queryCourse", { pageNum: currentPage, pageSize: pageSize })
        .then((res) => {
          this.tableData = res.data.courses;
          this.totalPage = res.data.totalPage;
        })
    },
    deletes() {
      let rows = this.$refs['studentTable'].selection;
      let ids = '';
      for (let i = 0; i < rows.length; i++) {
        ids += ',' + rows[i].courseId;
      }
      ids = ids.substring(1);   // 1,2,3,4,5,6....
      this.$axios.post('/delEduInfo?CourseID=' + ids).then(result => {
        this.$message(result.data);
        this.queryCourses(1, this.pageSize);
      });
    },
    createCourse() {
      this.showDialog = true;
    },
    submitCourse() {
      // Call the API here...
      // this.newCourse.courseStartTime = this.newCourse.courseStartTime.toLocaleDateString();
      // this.newCourse.courseStartTime = this.newCourse.courseStartTime.replace(/\//g, '-');
      // this.newCourse.courseEndTime = this.newCourse.courseEndTime.toLocaleDateString();
      // this.newCourse.courseEndTime = this.newCourse.courseEndTime.replace(/\//g, '-');
      console.log(this.newCourse);
      this.$axios.post('/createEduInfo', this.newCourse)
        .then(response => {
          this.$message({
            message: '课程创建成功！',
            type: 'success'
          });
          this.newCourse = {
            courseName: '',
            courseDescription: '',
            courseStartTime: '',
            courseEndTime: ''
          };
          this.queryCourses(this.currentPage, this.pageSize);
          this.showDialog = false;
        })
        .catch(error => {
          this.$message({
            message: '课程创建失败',
            type: 'error'
          });
          console.log(error);
        });
    },
  }
}
</script>

<style scoped></style>