<template>
   <Main>
      <template v-slot:manager>
          <div class="articleList">
               <h3>更多文章</h3>
               <div class="item" v-for="item in blogs" :key="item.id" @click="toArticle(item.id)">
                    <div class="item_hd">{{ item.title }}</div>
                    <div class="item_bd">{{ item.info }}</div>
               </div>
          </div>
              
      </template>
      <div class="articleDetail">
           <div class="title">
                <!-- 哈哈哈哈哈哈哈哈哈哈哈哈哈哈 -->
                {{ title }}
           </div>

           <div class="publish">
              <div class="tip">{{ time }}</div>
              <div class="tip">{{ type }}</div>
              <div class="tip" @click="test">{{ author }}</div>
           </div>
       
           <div class="content" ref="content">
               <!-- <h3>图片懒加载</h3>
               腾讯自研的“腾讯混元大模型”已经进入应用内测阶段，多位腾讯员工表示，近期收到了内部邮件邀请，可以通过网页或者小程序体验混元大模型。据了解，腾讯内部多个业务已经接入混元大模型进行测试。报道称，混元助手项目组的一号位张正友是腾讯史上最高职级拥有者，早年曾在微软研究院就职，带领视觉团队从事大量学术研究，贡献颇丰。张正友于 2018 年加入腾讯，创建了腾讯机器人实验室 Robotics X，致力推进人机协作的下一代机器人研究。2021 年 1 月 8 日，腾讯宣布张正友成为腾讯首位 17 级研究员 / 杰出科学家。（36氪）
               <img src="../src/assets/backgroud.jpg">
               <h3>路由懒加载</h3>
               <img src="../src/assets/backgroud.jpg">
               <img src="../src/assets/backgroud.jpg"> -->
           </div>
      </div>
  </Main>
</template>


<script>
import Main from '../src/components/Main.vue'
import datetime from '../js/datetime.js'
export default{
    components:{
        Main:Main,
    },
    data(){
       return{
        //   content:' <h3>图片懒加载</h3>腾讯自研的“腾讯混元大模型”已经进入应用内测阶段，多位腾讯员工表示，近期收到了内部邮件邀请，可以通过网页或者小程序体验混元大模型。据了解，腾讯内部多个业务已经接入混元大模型进行测试。报道称，混元助手项目组的一号位张正友是腾讯史上最高职级拥有者，早年曾在微软研究院就职，带领视觉团队从事大量学术研究，贡献颇丰。张正友于 2018 年加入腾讯，创建了腾讯机器人实验室 Robotics X，致力推进人机协作的下一代机器人研究。2021 年 1 月 8 日，腾讯宣布张正友成为腾讯首位 17 级研究员 / 杰出科学家。（36氪）<img src="../src/assets/backgroud.jpg"><h3>路由懒加载</h3><img src="../src/assets/backgroud.jpg"><img src="../src/assets/backgroud.jpg">',
        title:'',
        type:'',
        time:'',
        author:'',
        content:'',
        contentData:'',

        blogs:[],
       }
    },
    created(){
       this.axios.get(`/blog/${this.$route.query.id}`)
       .then(res=>{
            this.title = res.data.title,
            this.type = res.data.type,
            this.time = datetime.format(res.data.time, 'yyyy-MM-dd HH:mm:ss'),
            this.author = res.data.author,
            this.contentData = res.data.content
            this.content = res.data.content
            // console.log(this.content);
            // console.log(this.contentData);
            // console.log(this.$refs.content);
            this.$refs.content.innerHTML = this.content
       })

       this.getdataPage(this.$route.query.size,this.$route.query.page)
    },
    mounted(){
        // this.content = this.contentData
        // console.log(this.content);
        // console.log(this.contentData);
        // console.log(this.$refs.content);
        // this.$refs.content.innerHTML = this.content
        // console.log(this.$refs.content);
    },
    methods:{
       test(){
           this.$refs.content.innerHTML = this.content
       },
       getdataPage(size,index){
         this.axios.get("/blogsPage/"+ size + '/' + index,)
            .then(res=>{
                console.log(res);
                this.blogs = res.data.content;
            })
      },
      toArticle(id){
           this.$router.replace({path:'/articleDetail', query:{id:id,size:this.$route.query.size,page:this.$route.query.page} })
           this.axios.get(`/blog/${this.$route.query.id}`)
            .then(res=>{
                 this.title = res.data.title,
                 this.type = res.data.type,
                 this.time = datetime.format(res.data.time, 'yyyy-MM-dd HH:mm:ss'),
                 this.author = res.data.author,
                 this.contentData = res.data.content
                 this.content = res.data.content
              
                 this.$refs.content.innerHTML = this.content
            })
      },
    },
}
</script>

<style lang="scss" >
 .articleDetail{
    padding:0 0.5vw;
    .title{
        width: 100%;
        // height: 5vw;
        // background-color: skyblue;
        font-size: 1.5vw;
        // color: #fff;
        line-height: 3vw;
    }
    .publish{
        color: #666;
        display: flex;
        font-size: 1.2vw;
        .tip{
            // width: 15vw;
            // text-align: center;
            font-size: 1vw;
            margin-right: 2vw;
        }
        padding-bottom: 0.3vw;
        border-bottom: 0.1vw solid #eee;
        // justify-content: space-between;
    }
    .content{
        // margin-top: 1vw;
        // h3{
        //     line-height: 3vw;
        // }
        // img{
        //    max-width: 100%; 
        //    margin: 1vw auto;
        // }
        font-size: 1vw;
        line-height: 1.8vw;
        h3{
            line-height: 3vw;
            font-size: 1.1vw;
            input{
                line-height: 3vw;
                font-size: 1.1vw;
                font-weight:700;
            }
        }
        h4{
            line-height: 2.5vw;
            font-size: 1vw;
            input{
                line-height: 2.5vw;
                font-size: 1vw;
                font-weight:700;
            }
        }
        img{
           max-width: 100%; 
           margin: 1vw auto;
        }
        // .text{
        //     // white-space: ;
        // }
        p{
          display: block;
          // background-color: #044f7a;
          background-color:#282c34 ;
          color:#9fb2bf;
          padding: 0.5vw 1vw;
          margin: 1vw  0;
          line-height: 1.8vw;
        }
        div{
            line-height: 1.8vw;
            padding: 0.5vw 0;
        }
    }
 }
 .articleList{
    h3{
        line-height: 3vw;
        background-color: skyblue;
        color: #fff;
        font-size: 1.2vw;
        line-height: 3vw;
    }
    .item{
       width: 100%;
       height: 6vw;
       margin: 0.5vw 0;
       padding:0 0.5vw;
       box-sizing: border-box;
    //    background-color: pink;
       border-bottom: 0.1vw solid #eee;
       text-align: left;
       .item_hd{
           width: 100%;
           font-size: 1.1vw;
           height: 3vw;
           overflow: hidden;
           line-height: 3vw;
       }
       
       .item_bd{
        width:100%;
        font-size: 1vw;
        line-height: 1.5vw;
        height: 3vw;
        overflow: hidden;
       }
    }
    .item:hover{
          color: skyblue;
    }
 }


 @media screen and (max-width: 900px){
    .articleDetail{
    padding:0 1vw;
    width: 100vw;
    box-sizing: border-box;
    min-height: 100vh;
    .title{
        width: 100%;
        // height: 5vw;
        // background-color: skyblue;
        font-size: 6vw;
        // color: #fff;
        line-height: 12vw;
    }
    .publish{
        color: #666;
        display: flex;
        font-size: 3vw;
        line-height: 6vw;
        .tip{
            // width: 15vw;
            // text-align: center;
            font-size: 3vw;
            margin-right: 2vw;
        }
        padding-bottom: 0.3vw;
        border-bottom: 0.1vw solid #eee;
        // justify-content: space-between;
    }
    .content{
        // margin-top: 1vw;
        // h3{
        //     line-height: 3vw;
        // }
        // img{
        //    max-width: 100%; 
        //    margin: 1vw auto;
        // }
        font-size: 4vw;
        line-height: 8vw;
        h3{
            line-height:10vw;
            font-size: 6vw;
            input{
                line-height: 5vw;
                font-size: 2.5vw;
                font-weight:700;
            }
        }
        h4{
            line-height: 8vw;
            font-size: 5vw;
            input{
                line-height: 4vw;
                font-size: 2vw;
                font-weight:700;
            }
        }
        img{
           max-width: 100%; 
           margin: 1vw auto;
        }
        // .text{
        //     // white-space: ;
        // }
        p{
          display: block;
          // background-color: #044f7a;
          background-color:#282c34 ;
          color:#9fb2bf;
          padding: 0.5vw 1vw;
          margin: 1vw  0;
          line-height: 8vw;
          font-size: 4vw;
          width: 100%;
          overflow-x:scroll;
        }
        div{
            line-height: 8vw;
            font-size: 4vw;
            padding: 0.5vw 0;
        }
    }
 }  
 }
</style>