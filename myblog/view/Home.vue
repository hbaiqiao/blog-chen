<template>
    
    <div id ="home">
        
        <div class="frist">
           <Header></Header>
           <Index></Index>
           <!-- <div class="start">
              <img src="../src/assets/icon/enter.png" alt="">
              <h3>博客首页</h3>
           </div> -->
        </div>
        <!-- <Index></Index> -->
        <div class="home_area">
           <Header></Header>
            <div class="main">
                  <h2>最近文章</h2>
                  <div class="articleList">
                       <div class="article_item" v-for="item in articleList" :key="item.id" @click="toArticle(item.id)">
                          <div class="title">
                               {{ item.title }}
                          </div>
                          <div class="info">
                               {{ item.info }}
                          </div>
                       </div>
                       <!-- <div class="article_item"></div>
                       <div class="article_item"></div> -->
                  </div>
                  <!-- <h3>查看所有文章</h3> -->
                  <div class="handle">
                      <div class="handle_item" @click="()=>articlePage--">上一页</div>
                      <div class="handle_item">当前 {{articlePage}} 页</div>
                      <div class="handle_item" @click="()=>this.$router.push('/article')">查看所有文章</div>
                      <div class="handle_item">总数 {{articleTotal}} 页</div>
                      <div class="handle_item" @click="()=>articlePage++">下一页</div>
                  </div>


                  <h2>最近音乐</h2>
                  <div class="musicList">
                       <div class="music_item" v-for="item in musicList" :key="item.id" @click="toMusic(item.id)">
                           <div class="img">
                                <img :src="item.cover" alt="">
                           </div>
                           <div class="title">{{ item.title }}</div>
                           <div class="musician">{{ item.musician }}</div>
                       </div>
                      
                  </div>
                  <div class="handle">
                      <div class="handle_item" @click="()=>musicPage--">上一页</div>
                      <div class="handle_item">当前 {{musicPage}} 页</div>
                      <div class="handle_item" @click="()=>this.$router.push('/music')">查看所有音乐</div>
                      <div class="handle_item">总数 {{musicTotal}} 页</div>
                      <div class="handle_item" @click="()=>musicPage++">下一页</div>
                  </div>

                 
                  <h2>最近视频</h2>
                  <div class="vlogList">
                       <div class="vlog_item" v-for="item in vlogList" :key="item.id" @click="toVlog(item.id)">
                            <div class="img">
                                <img :src="item.cover" alt="">
                            </div>
                            <div class="title">
                                  {{ item.title }}
                            </div>

                       </div>
                      
                  </div>
                  <div class="handle">
                      <div class="handle_item" @click="()=>vlogPage--">上一页</div>
                      <div class="handle_item">当前 {{vlogPage}} 页</div>
                      <div class="handle_item" @click="()=>this.$router.push('/vlog')">查看所有视频</div>
                      <div class="handle_item">总数 {{vlogTotal}} 页</div>
                      <div class="handle_item" @click="()=>vlogPage++">下一页</div>
                  </div>

                  
                  <h2>电脑壁纸</h2>
                  <div class="photoWidthList">
                       <div class="photoWidth_item" v-for="item in photoWidthList" :key="item.id" @click="toPhoto(item.id,1)">
                          <img :src="item.src" alt="">
                       </div>
                      
                  </div>
                  <div class="handle">
                      <div class="handle_item" @click="()=>photoWidthPage--">上一页</div>
                      <div class="handle_item">当前 {{photoWidthPage}} 页</div>
                      <div class="handle_item" @click="this.$router.push('/photo')">查看所有壁纸</div>
                      <div class="handle_item">总数 {{photoWidthTotal}} 页</div>
                      <div class="handle_item" @click="()=>photoWidthPage++">下一页</div>
                  </div>
                
                  <h2>手机壁纸</h2>
                  <div class="photoHeightList" >
                       <div class="photoHeight_item" v-for="item in photoHeightList" :key="item.id" @click="toPhoto(item.id,2)">
                           <img :src="item.src" alt="">
                       </div>
                       
                  </div>
                  <div class="handle">
                      <div class="handle_item" @click="()=>photoHeightPage--">上一页</div>
                      <div class="handle_item">当前 {{photoHeightPage}} 页</div>
                      <div class="handle_item" @click="this.$router.push('/photo')">查看所有壁纸</div>
                      <div class="handle_item">总数 {{photoHeightTotal}} 页</div>
                      <div class="handle_item" @click="()=>photoHeightPage++">下一页</div>
                  </div>
                 

            </div>
        </div>
        
    </div>
</template>

<script>
   import Header from "../src/components/Header.vue"
   import Index from "../view/Index.vue"
   import Carousel from "../view/Carousel.vue"
   import Main from "../src/components/Main.vue";
   import datetime from '../js/datetime.js'
   export default{
      components:{
         Index:Index,
         Carousel:Carousel,
         Header:Header,
         Main:Main
         // Header:()=> import('../src/components/Header.vue')
      },
      data(){
         return {
            articleList:[],
            articlePage:1,
            articleTotal:1,

            musicList:[],
            musicPage:1,
            musicTotal:1,

            vlogList:[],
            vlogPage:1,
            vlogTotal:1,

            photoWidthList:[],
            photoWidthPage:1,
            photoWidthTotal:1,

            photoHeightList:[],
            photoHeightPage:1,
            photoHeightTotal:1,

            size:3,
         }
      },
      watch:{
        articlePage(){
           if(this.articlePage>this.articleTotal){
               this.articlePage = 1
           }
           if(this.articlePage <= 0){
               this.articlePage = this.articleTotal
           }
           this.getArticlePage(this.size,this.articlePage)
        },
        musicPage(){
           if(this.musicPage>this.musicTotal){
               this.musicPage = 1
           }
           if(this.musicPage <= 0){
               this.musicPage = this.musicTotal
           }
           this.getMusicPage(this.size,this.musicPage)
        },
        vlogPage(){
           if(this.vlogPage>this.vlogTotal){
               this.vlogPage = 1
           }
           if(this.vlogPage <= 0){
               this.vlogPage = this.vlogTotal
           }
           this.getVlogPage(this.size,this.vlogPage)
        },
        photoWidthPage(){
           if(this.photoWidthPage>this.photoWidthTotal){
               this.photoWidthPage = 1
           }
           if(this.photoWidthPage <= 0){
               this.photoWidthPage = this.photoWidthTotal
           }
           this.getPhotoWidthPage(this.size,this.photoWidthPage)
        },
        photoHeightPage(){
           if(this. photoHeightPage>this. photoHeightTotal){
               this. photoHeightPage = 1
           }
           if(this. photoHeightPage <= 0){
               this. photoHeightPage = this. photoHeightTotal
           }
           this.getPhotoHeightPage(this.size,this. photoHeightPage)
        },

      },
      created(){
          this.getArticlePage(this.size,this.articlePage)
          this.getMusicPage(this.size,this.musicPage)
          this.getVlogPage(this.size,this.vlogPage)
          this.getPhotoWidthPage(this.size,this.photoWidthPage)
          this.getPhotoHeightPage(this.size,this.photoHeightPage)
      },
      methods:{
         toArticle(id){
            this.$router.push({path:'/articleDetail', query:{id:id,size:this.size,page:this.articlePage} })
         },
         toMusic(id){
            this.$router.push({path:'/musicDetail', query:{id:id,size:this.size,page:this.musicPage} })
         },
         toVlog(id){
            this.$router.push({path:'/vlogDetail', query:{id:id,size:this.size,page:this.vlogPage} })
         },
         toPhoto(id,type){
            this.$router.push({path:'/photoDetail', query:{id:id,type:type} })
         },
        
         getArticlePage(size,index){
             this.axios.get("/blogsPage/"+ size + '/' + index,)
                .then(res=>{
                
                    this.articleTotal = res.data.totalPages; 
                    this.articleList = res.data.content;
                    this.articleList.forEach(item => {
                        item.time = datetime.format(item.time, 'yyyy-MM-dd')
                    });
                    
                })
         }, 
         getMusicPage(size,index){
            this.axios.get("/musicPage/" + size +'/'+ index)
           .then(res=>{ 
               this.musicTotal= res.data.totalPages
               this.musicList = res.data.content
           })
         },
         getVlogPage(size,page){
             this.axios.get("/vlogPage/"+ size +'/' + page)
             .then(res=>{
                 this.vlogTotal = res.data.totalPages
                 this.vlogList = res.data.content
                 this.vlogList.forEach(item => {
                    item.time = datetime.format(item.time, 'yyyy-MM-dd')
                 });
             })
         },
         getPhotoWidthPage(size,pageIndex){
            this.axios.get("/photoWidthPage/"+ size + '/' + pageIndex,)
            .then(res=>{
              
                this.photoWidthTotal = res.data.totalPages
               
                this.photoWidthList = res.data.content
                this.photoWidthList.forEach(item => {
                      item.time = datetime.format(item.time, 'yyyy-MM-dd')
                });  
            })

             
         },
         getPhotoHeightPage(size,pageIndex){
              this.axios.get("/photoHeightPage/"+ size + '/' + pageIndex,)
             .then(res=>{
                
                 this.photoHeightTotal = res.data.totalPages
                 this.photoHeightList = res.data.content
                 this.photoHeightList.forEach(item => {
                  item.time = datetime.format(item.time, 'yyyy-MM-dd')
                 });
              })       
         },         
      }

   }
</script>

<style scoped lang="scss">
   #home{
      /* background: url('../src/assets/backgroud.jpg');
      background-repeat: no-repeat;  
      background-size: 100% 100%;   */
      /* position: fixed; 充满全屏 */
      /* width: 100vw;  */
      /* margin-top: 100vh; */
      width: 100%;
      width: 100vw;
      overflow-x: hidden;
   }
   .frist{
      width: 100%;
      // height: 100vh;
      position: absolute;
      top:0;
      left: 0;
      overflow: hidden;
      .header{
         position: absolute !important;
         width: 100%;
         top: 0;
         left: 0;
         background-color: rgba(255,255,255,0) !important;
         box-shadow: 0 0 0 0;
         color: #fff;
      .hd_left{
         color: #fff;
      }
     }
   .header:hover{
      background-color: rgba(0,0,0,0.6);
   }
   .header_mobile{
         position: absolute !important;
         width: 100%;
         top: 0;
         left: 0;
         background-color: rgba(255,255,255,0) !important;
         box-shadow: 0 0 0 0;
         color: #fff;
   }
   }
   .start{
       width: 10vw;
       height: 10vh;
       position: absolute;
      //  background-color: #fff;
       top:75%;
       left: 45%;
     
       color: #1296db;
       z-index: 5;
       h3{
         text-align: center;
         line-height: 5vh;
         font-weight: 300;
       }
       img{
         width: 3vw;
         height: 3vw;
         margin: 0 3.5vw;
       }
       h3:hover{
         font-weight: 500;
       }
      
   }
   .home_area{
      /* margin-top: 100vh; */
      width: 100%;
      min-height: 50vw;
      position: absolute;
      top:50vw; 
      background-color:rgba(135,206,235,0.8);
      
   }
   .main{
      width: 92vw;
      margin: 0 auto;
      padding: 5vw 1vw;
      box-sizing: border-box;
      min-height: 50vw;
      background-color: rgba(255,255,255,1);
      h2{
         // background-color: pink;
         text-align: center;
         line-height: 6vw;
         font-size: 1.5vw;
         font-weight: 500;
         color: deepskyblue;
      }
      .handle{
         width: 60vw;
         height: 3vw;
         line-height: 3vw;
         font-size: 1.2vw;
         display: flex;
         justify-content: space-around;
         box-sizing: border-box;
         // padding: 0 15vw;
         margin: 2vw 15vw;
         // border-bottom: 0.1vw solid deepskyblue;
         .handle_item{
             width: 10vw;
            //  background-color: pink;
             text-align: center;
             color: #666;
         }
         .handle_item:hover{
            color: deepskyblue;
         }
      }
      h3{
         text-align: center;
         line-height: 2.6vw;
         font-size: 1.2vw;
         font-weight: 500;
         color: #fff;
         background-color:deepskyblue;
         width: 10vw;
         height: 2.6vw;
         margin: 2vw auto;
         border-radius: 1vw;
      }
      .articleList{
         width: 90vw;
         height: 12vw;
         padding: 1vw 3vw;
         box-sizing: border-box;
         display: flex;
         justify-content: space-around;
         flex-wrap: wrap;
         overflow: hidden;
        
         .article_item{
            width: 26vw;
            height: 10vw;
            // background-color: pink;
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
            border-radius: 0.5vw;
            box-sizing: border-box;
            padding: 0 1vw;
            .title{
               width: 24vw;
               height: 4vw;
               line-height: 4vw;
               overflow: hidden;
               font-size: 1.2vw;
            }
            .info{
               width: 24vw;
               height: 6vw;
               line-height: 3vw;
               overflow: hidden;
               font-size: 1vw;
               color: #666;
            }
         }
      }

      .musicList{
         width: 90vw;
         padding: 1vw 6vw;
         height: 20vw;
         box-sizing: border-box;
         display: flex;
         justify-content: space-around;
         flex-wrap: wrap;
         overflow: hidden;
        
         .music_item{
             width: 20vw;
             height: 18vw;
             box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
             .img{
                width: 20vw;
                height: 12vw;
                padding: 0.5vw 4vw;
                img{
                  width: 12vw;
                  height: 12vw;
                }
             }
             .title{
               font-size: 1.2vw;
               line-height:3vw;
               text-align: center;
               width: 20vw;
               overflow: hidden;
             }
             .musician{
                 font-size: 1vw;
                 line-height: 2vw;
                 color: #666;
                 width: 20vw;
                 overflow: hidden;
                 text-align: center;
             }
         }

      }

      .vlogList{
         width: 90vw;
         height: 20vw;
         padding: 1vw 3vw;
         box-sizing: border-box;
         display: flex;
         justify-content: space-around;
         flex-wrap: wrap;
         overflow: hidden;
         .vlog_item{
            width: 26vw;
            height: 18vw;
            // background-color: pink;
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
            border-radius: 0.5vw;
            .img{
                width: 26vw;
                height: 15vw;
                position: relative;
                background-color: #000;
                img{
                  max-width: 26vw;
                  max-height: 15vw;
                  position: absolute;
                  top: 50%;
                  left: 50%;
                  transform: translate(-50%,-50%);
                }
            }
            .title{
               width: 24vw;
               padding: 0 1vw;
               line-height: 3vw;
               font-size: 1.2vw;
    
            }
         }
      }

      .photoWidthList{
         width: 90vw;
         height: 18vw;
         padding: 1vw 3vw;
         box-sizing: border-box;
         display: flex;
         justify-content: space-around;
         flex-wrap: wrap;
         overflow: hidden;
         .photoWidth_item{
            width: 26vw;
            height: 16vw;
            // background-color: pink;
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
            border-radius: 0.5vw;
            position: relative;
            img{
                  max-width: 26vw;
                  max-height: 16vw;
                  position: absolute;
                  top: 50%;
                  left: 50%;
                  transform: translate(-50%,-50%);
                }
         }
      }

      .photoHeightList{
         width: 90vw;
         height: 42vw;
         padding: 1vw 8vw;
         box-sizing: border-box;
         display: flex;
         justify-content: space-around;
         flex-wrap: wrap;
         overflow: hidden;
         .photoHeight_item{
            width: 20vw;
            height: 40vw;
            // background-color: pink;
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
            border-radius: 0.5vw;
            position: relative;
            img{
                  max-width: 20vw;
                  max-height: 40vw;
                  position: absolute;
                  top: 50%;
                  left: 50%;
                  transform: translate(-50%,-50%);
                }
         }
      }
   




      

   }
   @media screen and (min-width: 900px) {
   .frist{
      width: 100%;
      // height: 100vh;
      position: absolute;
      top:0;
      left: 0;
      overflow: hidden;
      .header_mobile{
         display: none;
      }
  
   }
   } 
   @media screen and (max-width: 900px) {
      .frist{
         display: none;
      }
      .home_area{
         top: 0 !important;
      }
   //    .frist{
   //    width: 100%;
   //    // height: 100vh;
   //    position: absolute;
   //    top:0;
   //    left: 0;
   //    overflow: hidden;
   //    .header{
   //       position: absolute !important;
   //       width: 100%;
   //       top: 0;
   //       left: 0;
   //       background-color: rgba(255,255,255,0) !important;
   //       box-shadow: 0 0 0 0;
   //       color: #fff;
   //    .hd_left{
   //       color: #fff;
   //    }
   //   }
   // .header:hover{
   //    background-color: rgba(0,0,0,0.6);
   // }
   // .header_mobile{
   //       position: absolute !important;
   //       width: 100%;
   //       top: 0;
   //       left: 0;
   //       background-color: rgba(255,255,255,0) !important;
   //       box-shadow: 0 0 0 0;
   //       color: #fff;
   // }
   // }
   .header{
         position: absolute !important;
         width: 100%;
         top: 0;
         left: 0;
         background-color: rgba(255,255,255,0) !important;
         box-shadow: 0 0 0 0;
         color: #fff;
      .hd_left{
         color: #fff;
      }
   }
   .header_mobile{
         position: absolute !important;
         width: 100%;
         top: 0;
         left: 0;
         background-color: rgba(255,255,255,0) !important;
         box-shadow: 0 0 0 0;
         color: #fff;
   }
   .home_area{
      /* margin-top: 100vh; */
      width: 100%;
      min-height: 50vw;
      position: absolute;
      top:50vw; 
      background-color:rgba(135,206,235,0.8);
      
   }
   .main{
      width: 100%;
      margin: 0 auto;
      padding: 5vw 0vw;
      box-sizing: border-box;
      min-height: 50vw;
      background-color: rgba(255,255,255,1);
      h2{
         // background-color: pink;
         text-align: center;
         line-height: 8vw;
         font-size: 4vw;
         font-weight: 500;
         color: deepskyblue;
      }
      .handle{
         width: 90vw;
         height: 6vw;
         line-height: 6vw;
         font-size: 3vw;
         display: flex;
         justify-content: space-around;
         box-sizing: border-box;
         // padding: 0 15vw;
         margin: 2vw 5vw;
         // border-bottom: 0.1vw solid deepskyblue;
         .handle_item{
             width: 18vw;
            //  background-color: pink;
             text-align: center;
             color: #666;
         }
         .handle_item:hover{
            color: deepskyblue;
         }
      }
      h3{
         text-align: center;
         line-height:6vw;
         font-size: 3vw;
         font-weight: 500;
         color: #fff;
         background-color:deepskyblue;
         width: 10vw;
         height: 6vw;
         margin: 2vw auto;
         border-radius: 1vw;
      }
      .articleList{
         width: 100%;
         height: 70vw;
         padding: 1vw 3vw;
         box-sizing: border-box;
         display: flex;
         justify-content: space-around;
         flex-wrap: wrap;
         overflow: hidden;
        
         .article_item{
            width: 90vw;
            margin: 1vw auto;
            height: 20vw;
            // background-color: pink;
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
            border-radius: 0.5vw;
            box-sizing: border-box;
            padding: 0 1vw;
            .title{
               width: 90vw;
               height: 8vw;
               line-height: 8vw;
               overflow: hidden;
               font-size: 3.5vw;
            }
            .info{
               width: 90vw;
               height: 12vw;
               line-height: 6vw;
               overflow: hidden;
               font-size: 3vw;
               color: #666;
            }
         }
      }

      .musicList{
         width: 100%;
         padding: 1vw 0vw;
         height: 45vw;
         box-sizing: border-box;
         display: flex;
         justify-content: space-around;
         flex-wrap: wrap;
         overflow: hidden;
        
         .music_item{
             width: 30vw;
             height: 42vw;
             box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
             .img{
                width: 30vw;
                height: 30vw;
               //  padding: 0.5vw 4vw;
               padding: 0;
                img{
                  width: 30vw;
                  height: 30vw;
                }
             }
             .title{
               font-size: 3.5vw;
               line-height:6vw;
               text-align: center;
               width: 30vw;
               overflow: hidden;
             }
             .musician{
                 font-size: 3vw;
                 line-height: 6vw;
                 color: #666;
                 width: 30vw;
                 overflow: hidden;
                 text-align: center;
             }
         }

      }

      .vlogList{
         width: 100%;
         height: 30vw;
         padding: 1vw 0vw;
         box-sizing: border-box;
         display: flex;
         justify-content: space-around;
         flex-wrap: wrap;
         overflow: hidden;
         .vlog_item{
            width: 30vw;
            height: 26vw;
            // background-color: pink;
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
            border-radius: 0.5vw;
            .img{
                width: 30vw;
                height: 20vw;
                position: relative;
                overflow: hidden;
                background-color: #000;
                img{
                  max-width: 30vw;
                  max-height: 20vw;
                  position: absolute;
                  top: 50%;
                  left: 50%;
                  transform: translate(-50%,-50%);
                }
            }
            .title{
               width: 30vw;
               padding: 0 1vw;
               line-height: 6vw;
               font-size: 3vw;
    
            }
         }
      }

      .photoWidthList{
         width: 100%;
         height: 30vw;
         padding: 1vw 0vw;
         box-sizing: border-box;
         display: flex;
         justify-content: space-around;
         flex-wrap: wrap;
         overflow: hidden;
         .photoWidth_item{
            width: 30vw;
            height: 26vw;
            // background-color: pink;
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
            border-radius: 0.5vw;
            position: relative;
            img{
                  max-width: 30vw;
                  max-height: 20vw;
                  position: absolute;
                  top: 50%;
                  left: 50%;
                  transform: translate(-50%,-50%);
                }
         }
      }

      .photoHeightList{
         width: 100%;
         height: 70vw;
         padding: 1vw 0vw;
         box-sizing: border-box;
         display: flex;
         justify-content: space-around;
         flex-wrap: wrap;
         overflow: hidden;
         .photoHeight_item{
            width: 30vw;
            height: 60vw;
            // background-color: pink;
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
            border-radius: 0.5vw;
            position: relative;
            img{
                  max-width: 30vw;
                  max-height: 60vw;
                  position: absolute;
                  top: 50%;
                  left: 50%;
                  transform: translate(-50%,-50%);
                }
         }
      }
   




      

   }



} 
</style>