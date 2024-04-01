<template>
    <Main>
        <template v-slot:manager>
             <Manager></Manager>
        </template>
        <div class="photo">
                <div class="photo_tab">
                    <li @click="showPhoto(1)" :class="tabIndex==1? 'current':''">电脑壁纸</li>
                    <li @click="showPhoto(2)" :class="tabIndex==2? 'current':''">手机壁纸</li>
                </div>
                <div class="photo_width" v-if="this.tabIndex==1" ref="photo_width">
                    <div class="photo_item" v-for="item in imgWidthList" :key="item.id" @click="toPhoto(item.id,1)">
                        <div class="photo_up">
                            <img :src="item.src" alt="">
                        </div>
                        <div class="photo_down">
                             <div class="info">
                                  <!-- <span>{{ item.author }} </span>
                                  <span class="time">{{ item.time }}</span> -->
                                  {{ item.author }}&emsp;&emsp;{{ item.time }}
                             </div>
                        </div>
                    </div>

                     <div class="pagebox" v-if="pageShowWidth">
                        <Page :sizeNum="10" :pageIndex="pageIndexWidth" :totalPages="totalPagesWidth" @changePage="getPageWidth"></Page> 
                     </div>
                   
                </div>

                <div class="photo_height" v-if="this.tabIndex==2" ref="photo_height">
                    <div class="photo_item" v-for="item in imgHeightList" :key="item.id" @click="toPhoto(item.id,2)">
                        <div class="photo_up">
                            <img :src="item.src" alt="">
                        </div>
                        <div class="photo_down">
                             <div class="info">
                                  <!-- <span>{{ item.author }} </span>
                                  <span class="time">{{ item.time }}</span> -->
                                  {{ item.author }}&emsp;&emsp;{{ item.time }}
                             </div>
                        </div>
                    </div>
                    <div class="pagebox" v-if="pageShowHeight">
                        <Page :sizeNum="9" :pageIndex="pageIndexHeight" :totalPages="totalPagesHeight" @changePage="getPageHeight"></Page>
                    </div>
                   
                </div>
                

        </div>
    </Main>
  </template>
  
  <script>
  import Main from "../src/components/Main.vue"
  import Page from "../src/components/Page.vue"
  import Manager from "../src/components/Manager.vue"
  import datetime from '../js/datetime.js'
  export default{
      components:{
          Main:Main,
          Page:Page,
          Manager:Manager,
      },
      created(){
        //  this.getdata()
         this.getdataPageWidth(10,this.pageIndexWidth)
         this.getdataPageHeight(9,this.pageIndexHeight)
      },

      data(){
          return{
              tabIndex:1,
              src:'',
              title:'',
              music:'',
              imgWidthList:[{}],
              imgHeightList:[{}],
              totalPagesWidth:1,
              totalPagesHeight:1,
              pageIndexWidth:1,
              pageIndexHeight:1,
              pageShowWidth:false,
              pageShowHeight:false,

          }
      },
      methods:{
          toPhoto(id,type){
              this.$router.push({path:'/photoDetail', query:{id:id,type:type} })
          },
          showPhoto(index){
              this.tabIndex = index
          },
          getdata(){
             this.axios.get("/photoWidths")
             .then(res=>{
                 console.log(res);
                 this.imgWidthList = res.data
                 this.imgWidthList.forEach(item => {
                    item.time = datetime.format(item.time, 'yyyy-MM-dd')
                });
             })

             this.axios.get("/photoHeights")
             .then(res=>{
                 console.log(res);
                 this.imgHeightList = res.data
                 this.imgHeightList.forEach(item => {
                    item.time = datetime.format(item.time, 'yyyy-MM-dd')
                });
             })

          },
        getdataPageWidth(size,pageIndex){
            this.axios.get("/photoWidthPage/"+ size + '/' + pageIndex,)
            .then(res=>{
                console.log(res);
                this.totalPagesWidth = res.data.totalPages
                this.pageShowWidth = true
                this.imgWidthList = res.data.content
                this.imgWidthList.forEach(item => {
                      item.time = datetime.format(item.time, 'yyyy-MM-dd')
                });  
            })

             
         },
         getdataPageHeight(size,pageIndex){
              this.axios.get("/photoHeightPage/"+ size + '/' + pageIndex,)
             .then(res=>{
                 console.log(res);
                 this.totalPagesHeight = res.data.totalPages
                 this.pageShowHeight = true
                 this.imgHeightList = res.data.content
                 this.imgHeightList.forEach(item => {
                  item.time = datetime.format(item.time, 'yyyy-MM-dd')
                 });
              })       
         },

         getPageWidth(size,page,total){
            this.getdataPageWidth(size,page)
            // console.log(this.$refs.photo_width);
            // console.log(this.$refs.photo_width.style.offSetHeight)
            // console.log(this.$refs.photo_width.scrollTop);
            // this.$refs.photo_width.scrollTop = 0 + 'px'
         },
         getPageHeight(size,page,total){
            this.getdataPageHeight(size,page)
         }
         
      
         
      }
      
  }
  </script>

  <style lang="scss">
 .photo{
    .photo_tab{
        width: 15vw;
        margin-left: 25vw;
        li{ 
            width: 6vw;
            height: 2vw;
            // padding: 0 1vw;
            line-height: 2vw;
            font-size: 1.2vw;
            text-align: center;
            display: inline-block;
            list-style: none;
            // background-color: pink;
            margin-right: 1vw;
            color: #666;
        }
        .current{
            border-bottom: 0.2vw solid deepskyblue;
            color: deepskyblue;
        }
    }
    .photo_width{
        display: flex;
        flex-wrap: wrap;
        .photo_item{
            width: 34vw;
            height: 19vw;
            margin: 1vw 0.5vw 0 0.5vw;
            
            // background-color: pink;
            .photo_up{
                width: 34vw;
                height: 17vw;
                position: relative;
            }
            img{
                max-width: 34vw;
                max-height: 17vw;
                background-color: rgba(0,0,0,0.9);
                position: absolute;
                top:50%;
                left: 50%;
                transform: translate(-50%,-50%);
            }
            img:hover{
                max-width: 34.5vw;
                max-height: 17.5vw;
            }
            .info{
                // display: flex;
                // justify-content:space-between;
                font-size: 1vw;
                line-height: 2vw;
                text-align: center;
                color: #666;
                // border-bottom: 0.05vw solid rgba(0,0,0,0.2);
                // .time{
                //     margin-right: 1vw;
                // }
            }
        }
    }

    .photo_height{
        display: flex;
        flex-wrap: wrap;
        width: 70vw;
        .photo_item{
            width: 21vw;
            height: 41vw;
            margin: 1vw 0.5vw 0 0.5vw;
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.2);
            border-radius: 0.5vw;
            margin-right: 1.2vw;
            margin-left: 1vw;
            // background-color: pink;
            .photo_up{
                width: 21vw;
                height: 40vw;
                position: relative;
            }
            img{
                max-width: 21vw;
                max-height: 40vw;
                background-color: rgba(0,0,0,0.9);
                position: absolute;
                top:50%;
                left: 50%;
                transform: translate(-50%,-50%);
            }
            .info{
                // display: flex;
                // justify-content:space-between;
                font-size: 0.8vw;
                line-height: 1vw;
                text-align: center;
                color: #666;
                // border-bottom: 0.05vw solid rgba(0,0,0,0.2);
                // .time{
                //     margin-right: 1vw;
                // }
            }
        }
    }

    .pagebox{
       width: 100%;
    }

 
 }

 @media screen and (max-width: 900px){
 .photo{
    .photo_tab{
        width: 30vw;
        height: 8vw;
        margin-left: 35vw;
        margin-bottom: 3vw;
        li{ 
            width: 14vw;
            height: 8vw;
            // padding: 0 1vw;
            line-height: 8vw;
            font-size: 3vw;
            text-align: center;
            display: inline-block;
            list-style: none;
            // background-color: pink;
            margin-right: 1vw;
            color: #666;
        }
        .current{
            border-bottom: 0.6vw solid deepskyblue;
            color: deepskyblue;
        }
    }
    .photo_width{
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
        .photo_item{
            width: 48vw;
            height: 28vw;
            margin: 1vw 0;
            position: relative;
            
            // background-color: pink;
            .photo_up{
                width: 48vw;
                height: 25vw;
                position: relative;
                // background-color: deeppink;
            }
            img{
                max-width: 48vw;
                max-height: 25vw;
                background-color: rgba(0,0,0,0.9);
                position: absolute;
                top:50%;
                left: 50%;
                transform: translate(-50%,-50%);
            }
            img:hover{
                max-width: 48vw;
                max-height: 26vw;
            }
            .info{
                // display: flex;
                // justify-content:space-between;
                font-size: 2vw;
                line-height: 3vw;
                text-align: center;
                color: #666;
                // border-bottom: 0.05vw solid rgba(0,0,0,0.2);
                // .time{
                //     margin-right: 1vw;
                // }
            }
        }
    }

    .photo_height{
        display: flex;
        flex-wrap: wrap;
        width: 100vw;
        justify-content: space-around;
        .photo_item{
            width: 32vw;
            height: 64vw;
            margin: 1vw 0;
            box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.2);
            border-radius: 0.5vw;
            // margin-right: 1.2vw;
            // margin-left: 1vw;
            // background-color: pink;
            .photo_up{
                width: 32vw;
                height: 60vw;
                position: relative;
            }
            img{
                max-width: 32vw;
                max-height: 60vw;
                background-color: rgba(0,0,0,0.9);
                position: absolute;
                top:50%;
                left: 50%;
                transform: translate(-50%,-50%);
            }
            .info{
                // display: flex;
                // justify-content:space-between;
                font-size: 2vw;
                line-height: 3vw;
                text-align: center;
                color: #666;
                // border-bottom: 0.05vw solid rgba(0,0,0,0.2);
                // .time{
                //     margin-right: 1vw;
                // }
            }
        }
    }
 }
}
  </style>