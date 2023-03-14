<template>
  <div class="dashboard-container">
    <div class="dashboard-text">
      zy'Blog后台管理
    </div>
    <div>
      欢迎关注三更的B站账号：<a href="https://space.bilibili.com/663528522">https://space.bilibili.com/663528522</a>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
