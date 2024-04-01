<template>
    <div class="header">
            <div class="hd_left" @click="changeRouter('/')">
                 <img :src="ownerImg" alt="">
                 &ensp;
                 辰的博客
            </div>
            <div class="hd_right">
                 <ul ref="ul">
                    <li index="0" path="/" @click="changeRouter('/')">首页</li>
                    <li index="1" path="/article" @click="changeRouter('/article')">文章</li>
                    <!-- <li index="2" path="" @click="changeRouter('')">分类</li> -->
                    <li index="3" path="/music" @click="changeRouter('/music')">音乐</li>
                    <li index="4" path="/vlog" @click="changeRouter('/vlog')">Vlog</li>
                    <li index="5" path="/photo" @click="changeRouter('/photo')">壁纸</li>
                    <li index="6" path="/login" @click="changeRouter('/login')">登录</li>
                 </ul>
            </div>
    </div>

    <div class="header_mobile">
            <div class="hd_left" @click="changeRouter('/')">
                 <img :src="ownerImg" alt="">
                 &ensp;
                 辰的博客
            </div>
            <div class="hd_right" @click="getRouter">
               <img src="../assets/menu.png" alt=""  @click="()=>menuShow =!menuShow">
            </div>
            <div class="hd_down" v-if="menuShow" >
                 <ul ref="ul">
                    <li index="0" path="/" @click="changeRouter('/')">首页</li>
                    <li index="1" path="/article" @click="changeRouter('/article')">文章</li>
                    <!-- <li index="2" path="" @click="changeRouter('')">分类</li> -->
                    <li index="3" path="/music" @click="changeRouter('/music')">音乐</li>
                    <li index="4" path="/vlog" @click="changeRouter('/vlog')">Vlog</li>
                    <li index="5" path="/photo" @click="changeRouter('/photo')">壁纸</li>
                    <li index="6" path="/login" @click="changeRouter('/login')">登录</li>
                    <li @click="()=>{this.$router.push('/manager/article')}">发布文章</li>
                    <li @click="()=>{this.$router.push('/manager/music')}">发布音乐</li>
                    <li @click="()=>{this.$router.push('/manager/vlog')}">发布视频</li>
                    <li @click="()=>{this.$router.push('/manager/photo')}">发布壁纸</li> 
                 </ul>
            </div>
    </div>
</template>

<script>
export default{
   name:'Header',
   data(){
    return{
       ownerImg:require("../assets/chen.png"),
       menuShow:false,
    }
   },
   mounted(){
      
       this.getRouter()
   },
   methods:{
       getRouter(){
         let location = this.$route.path
         let ul =this.$refs.ul
         // console.log(ul.children);
         for(let i =0;i<ul.children.length;i++){
            ul.children[i].className = ''
              if(ul.children[i].getAttribute("path") == location){
                   ul.children[i].className = 'li_select'
              }
         }
         
      },
      changeRouter(path){
          this.$router.push(path)
      }
   },
}
</script>
<style lang="scss" scoped>
.header{
    width: 100%;
    height: 2.5vw;
    line-height: 2.5vw;
    font-size: 15px;
    // color: #fff;
    background-color: rgba(255,255,255,1);
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    display: flex;
	 position: sticky;/*兼容*/
	 top: 0;
 	 left: 0;
    z-index: 10;
    font-size: 1vw;

    // background-color: rgba(255,255,255,0.8);
    // box-shadow: 1px 1px 1px rgba(0,0,0,0.1);
   .hd_left{
      flex: 3;
      text-align: center;
      line-height: 2.5vw;
      height: 2.5vw;
      // color:skyblue;
      img{
        width: 2vw;
        height: 2vw;
        opacity: 0.8;
        vertical-align: middle;
      }
     

   }
   .hd_right{
    flex: 6;
    
   }
   ul{
      width: 100%;
      list-style: none;
      line-height: 2.5vw;
      li{
        display: inline-block;
        margin: 0 2vw;
        line-height: 2.5vw; 
        box-sizing: border-box;     
      }
      li:hover{
        // color: rgb(0, 132, 255);
        color: deepskyblue;
        // text-decoration: underline;
       
      }
      .li_select{
        color: deepskyblue;
        border-bottom: 0.1vw solid deepskyblue;
      //   font-weight:600;
      }
   }
}
// .header_mobile{
//    display: none;
//    width: 100vw;
//    height: 10vw;
//    background-color:#fff;
//    display: flex;
//    justify-content: space-between;
//    flex-wrap: wrap;
//    .hd_left{
//       width: 40vw;
//       height: 10vw;
//       text-align: center;
//       line-height: 10vw;
//       font-size: 4vw;
//       height: 10vw;
//       color:skyblue;
//       img{
//         width: 8vw;
//         height: 8vw;
//         margin: 1vw;
//         opacity: 0.8;
//         vertical-align: middle;
//       }
//    }
//    .hd_right{
//       width: 40vw;
//       height: 10vw;
//       img{
       
//         width: 8vw;
//         height: 8vw;
//         margin: 1vw;
//         margin-left: 25vw;
//         opacity: 0.8;
//         vertical-align: middle;
//       }
//    }

//    .hd_down{
//       z-index: 10;
//       // padding-bottom: 5vw;
//       ul{
//          list-style: none;
//       }
//       li{
//          width: 100vw;
//          height: 10vw;
//          line-height: 10vw;
//          text-indent: 2em;
//          font-size: 4vw;
//          background-color: #fff;
//          // color: deepskyblue;
//       }
//       .li_select{
//         color: deepskyblue;
//       }
//    }
// }
.frist{
      width: 100%;
      // height: 100vh;
      position: absolute;
      top:0;
      left: 0;
      overflow: hidden;
      .header{
         position: absolute;
         width: 100%;
         top: 0;
         left: 0;
         background-color: rgba(255,255,255,0) ;
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
         position: absolute;
         width: 100%;
         top: 0;
         left: 0;
         background-color: rgba(255,255,255,0) ;
         box-shadow: 0 0 0 0;
         color: #fff;
   }
}
@media screen and (min-width: 900px) {
   .header_mobile{
      display: none;
   
   }
   .header{
      display: flex;
      width: 100%;
   }
}
@media screen and (max-width: 900px){
   // .header{
   //  display: none;
   //  width: 100%;
   //  height: 5vw;
   //  line-height: 5vw;
   //  font-size: 15px;
   //  // color: #fff;
   //  background-color: rgba(255,255,255,1);
   //  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
   //  display: flex;
	//  position: sticky;/*兼容*/
	//  top: 0;
 	//  left: 0;
   //  z-index: 10;
   // font-size: 1vw;

   //  // background-color: rgba(255,255,255,0.8);
   //  // box-shadow: 1px 1px 1px rgba(0,0,0,0.1);
   // .hd_left{
   //    flex: 3;
   //    text-align: center;
   //    line-height: 5vw;
   //    height: 5vw;
   //    // color:skyblue;
   //    img{
   //      width: 2vw;
   //      height: 2vw;
   //      opacity: 0.8;
   //      vertical-align: middle;
   //    }
     

   // }
   // .hd_right{
   //  flex: 6;
    
   // }
   // ul{
   //    width: 100%;
   //    list-style: none;
   //    line-height: 5vw;
   //    li{
   //      display: inline-block;
   //      margin: 0 2vw;
   //      line-height: 5vw; 
   //      box-sizing: border-box;     
   //    }
   //    li:hover{
   //      // color: rgb(0, 132, 255);
   //      color: deepskyblue;
   //      // text-decoration: underline;
       
   //    }
   //    .li_select{
   //      color: deepskyblue;
   //      border-bottom: 0.1vw solid deepskyblue;
   //    //   font-weight:600;
   //    }
   // }
   // }  
   // .header_mobile{
      
   //    display: block;
   //    width: 100vw;
   // }
   .header{
      display: none;
   }
   .header_mobile{
   z-index: 10;
    display: flex;
    width: 100vw;
    margin: 0;
    padding: 0;
    position: sticky;/*兼容*/
	 top: 0;
 	 left: 0;
   width: 100%;
   height: 10vw;
   background-color:#fff;
   box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
   display: flex;
   justify-content: space-between;
   flex-wrap: wrap;
   .hd_left{
      width: 40%;
      height: 10vw;
      text-align: center;
      line-height: 10vw;
      font-size: 4vw;
      height: 10vw;
      color:skyblue;
      img{
        width: 8vw;
        height: 8vw;
        margin: 1vw;
        opacity: 0.8;
        vertical-align: middle;
      }
   }
   .hd_right{
      width: 60%;
      height: 10vw;
      img{
       
        width: 8vw;
        height: 8vw;
        margin: 1vw;
        margin-left: 45vw;
        opacity: 0.8;
        vertical-align: middle;
      }
   }

   .hd_down{
      z-index: 10;
      // padding-bottom: 5vw;
      ul{
         list-style: none;
         width: 100%;
         background-color:pink;
      }
      li{
         width: 96vw;
         height: 10vw;
         line-height: 10vw;
         text-indent: 2em;
         font-size: 4vw;
         background-color: #fff;
         // color: deepskyblue;
      }
      .li_select{
        color: deepskyblue;
      }
   }
}
.frist{
      
   .header_mobile{
        display: none;
      // position: sticky;
      // top: 0;
      // background-color: #fff;
      // z-index: 20;
   }
   }
}
// .header:hover{
//     background-color: rgba(0,0,0,0.6);
// }
</style>