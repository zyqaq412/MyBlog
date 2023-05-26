<!-- 文章详情 -->
<template>
    <div>
        <sg-nav></sg-nav>
      <div>


      </div>
        <div  class="container" id="detail">
            <el-row  :gutter="30">
                <el-col :sm="24" :md="16" style="transition:all .5s ease-out;margin-bottom:30px;">
                    <sg-articleDetail ref="articleDetail"></sg-articleDetail>
                    <sg-message></sg-message>
                </el-col>
                <el-col :sm="24"  :md="8" >
                    <sg-rightlist></sg-rightlist>
<!--                    <div><el-tree :data="toc" :props="defaultProps"  @node-click="handleNodeClick"></el-tree></div>-->
                  <div class="sticky-tree-container" >
                    <el-tree :data="toc" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
                  </div>

                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
import header from '../components/header.vue'
import rightlist from '../components/rightlist.vue'
import articleDetail from '../components/articleDetail.vue'
import message from '../components/message.vue'
    export default {
        name:'DetailShare',
        data() { //选项 / 数据
            return {
              toc: [
               /* {
                  content:"<a id=\"1Mybatis_0\"></a>1.什么是Mybatis？",
                  children: [{
                    content:"<a id=\"1Mybatis_0\"></a>1.什么是Mybatis？",
                    children: [{
                      content:"<a id=\"1Mybatis_0\"></a>1.什么是Mybatis？",
                    }]
                  }]
                },*/
              ],    //目录节点数据
              defaultProps: {
                children: 'children',
                label: 'content'
              },
              containerTop:'',
            }
        },
        methods: {
          handleNodeClick(data) {
/*            const position = data.position; // 获取节点的位置信息
            window.scrollTo({
              top: position+100,
              behavior: 'smooth' // 平滑滚动
            })*/
            // 获取锚点ID
            console.log('id',data.id)
            const anchorId = data.id;

            // 滚动到锚点位置
            const anchorElement = document.getElementById(anchorId);
            console.log('anchorElement', anchorElement);
            if (anchorElement) {
              anchorElement.scrollIntoView({ behavior: 'smooth' });
            }
          },
          checkTreePosition() {
            const treeContainer = document.querySelector('.sticky-tree-container');
            const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
            console.log('scrollTop',scrollTop)
            console.log('this.containerTop',this.containerTop)
            if (scrollTop > this.containerTop) {
              treeContainer.classList.add('fixed');
            } else {
              treeContainer.classList.remove('fixed');
            }
          },

          //事件处理器
            handleEdit(){

              this.toc = this.$refs['articleDetail'].getVal();

              console.log('toc',this.toc)
            }
        },
        components: { //定义组件
            'sg-nav':header,
            'sg-articleDetail':articleDetail,
            'sg-message':message,
            'sg-rightlist':rightlist,
        },
        created() { //生命周期函数

        },
        mounted(){
          this.$nextTick(()=>{
            setTimeout(()=>{
              this.handleEdit()
            },1000)
          })
/*            var anchor = document.querySelector("#detail");
            // console.log(anchor,anchor.offsetTop);
            var top = anchor.offsetTop-60;
            document.body.scrollTop = top;
             // Firefox
             document.documentElement.scrollTop = top;
             // Safari
             window.pageYOffset = top;*/
          // 跳转到文章标题位置
          var anchor = document.querySelector("#detail");
          var top = anchor.offsetTop - 60;
          window.scrollTo({
            top: top,
            behavior: "smooth"
          });
          //TODO 目录固定显示
            /*this.containerTop =document.querySelector('.sticky-tree-container').offsetTop;
            window.addEventListener('scroll', this.checkTreePosition);*/
        },
/*        beforeDestroy() {
        window.removeEventListener('scroll', this.checkTreePosition);
      }*/
    }
</script>

<style>
.sticky-tree-container {
  position: sticky;
  top: 0;
}
.fixed {
  position: fixed !important;
  top: 0;
  z-index: 999;
  width: 100%;
}
</style>
