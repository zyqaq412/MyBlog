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
              toc:[],    //目录节点数据
              defaultProps: {
                children: 'children',
                label: 'content'
              },
              containerTop:'',
            }
        },

        methods: {
          handleNodeClick(data) {
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
/*            handleEdit(){
              this.toc = this.$store.state.tree
              console.log('toc',this.toc)
              console.log('tree',this.$store.state.tree)
            }*/
        },
        components: { //定义组件
            'sg-nav':header,
            'sg-articleDetail':articleDetail,
            'sg-message':message,
            'sg-rightlist':rightlist,
        },
        created() { //生命周期函数
          // 当this.$store.state.tree的值发生变化 就执行该函数
          this.$store.watch(
            () => this.$store.state.tree,
            (newTree) => {
              this.toc = newTree;
            }
          );
        },
        mounted(){
/*          setTimeout(()=>{
            this.toc = this.$refs['articleDetail'].tete();
            console.log('toc',this.toc)
            console.log('this.$store.state.tree',this.$store.state.tree)
            this.toc=this.$store.state.tree
          },1500)*/
          // 跳转到文章标题位置
          const anchor = document.querySelector("#detail");
          const top = anchor.offsetTop - 60;
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
