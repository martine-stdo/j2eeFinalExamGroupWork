<template>
  <div style="height: 100%">
    <router-view/>
  </div>
</template>

<script>


export default {
  name: "app",
  created() {

  },
  data() {
    return {
      color: 'red',
      size: '26px'
    }
  },
  methods: {
    changeColor() {
      this.color = 'black'
    }
  }

}
</script>

<style>
html, body {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
  font-size: 14px;
  line-height: 1.2857142;
  display: block;
}
</style>
