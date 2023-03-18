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
                    <div><el-tree :data="toc" :props="defaultProps" ></el-tree></div>
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
              }
            }
        },
        methods: { //事件处理器
            handleEdit(){

              this.toc = this.$refs['articleDetail'].getVal();

              console.log(this.toc)
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
            var anchor = document.querySelector("#detail");
            // console.log(anchor,anchor.offsetTop);
            var top = anchor.offsetTop-60;
            document.body.scrollTop = top;
             // Firefox
             document.documentElement.scrollTop = top;
             // Safari
             window.pageYOffset = top;
        }
    }
</script>

<style>
</style>
