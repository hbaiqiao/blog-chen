<template>
    <Main>
        <template v-slot:manager>
             <Manager></Manager>
        </template>
        <div class="vlog">
             <div class="vlog_item" v-for="item in vlogList" :key="item.id" @click="toVlog(item.id)">
                  <div class="vlog_up">
                      <img :src="item.cover" alt="">
                  </div>
                  <div class="vlog_down">
                       <div class="title">{{ item.title }}</div>
                       <div class="info">
                            <span>{{ item.author }} </span>
                            <span class="time">{{ item.time }}</span>
                       </div>
                  </div>
             </div>
           
        </div>
        <div v-if="pageshow">
              <Page :sizeNum="9" :pageIndex="pageIndex" :totalPages="totalPages" @changePage="getPage"></Page>
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
        this.getdataPage(9,1)
      },
      data(){
          return{
              src:'',
              title:'',
              music:'',
              vlogList:[{}],
              pageIndex:1,
              totalPages:1,
              pageshow:false,
          }
      },
      methods:{
        toVlog(id){
            this.$router.push({path:'/vlogDetail', query:{id:id,size:9,page:this.pageIndex} })
        },
          getdata(){
             this.axios.get("/vlogs")
             .then(res=>{
                 console.log(res);
                 this.vlogList = res.data
                 this.vlogList.forEach(item => {
                    item.time = datetime.format(item.time, 'yyyy-MM-dd')
                });
             })
          },
          getdataPage(size,page){
             this.axios.get("/vlogPage/"+ size +'/' + page)
             .then(res=>{
                 console.log(res);
                 this.totalPages = res.data.totalPages
                 this.pageshow = true
                 this.vlogList = res.data.content
                 this.vlogList.forEach(item => {
                    item.time = datetime.format(item.time, 'yyyy-MM-dd')
                });
             })
          },
          getPage(size,page,total){
             this.getdataPage(size,page)
             this.pageIndex = page
          }
      }
      
  }
  </script>

  <style lang="scss">
  .vlog{
    display: flex;
    // justify-content: space-around;
    flex-wrap: wrap;
    .vlog_item{
        width: 22.6vw;
        height: 17vw;
        margin: 1vw 0.3vw 0 0.3vw;
        // background-color: pink;
        border-radius: 0.5vw;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        .vlog_up{
           width: 22vw;
           height: 12vw;
           background-color:rgba(0,0,0,0.9);
           position: relative;
           border-radius: 0.5vw 0.5vw 0 0;
           img{
             max-width: 22vw;
             max-height: 12vw;
             position: absolute;
             top:50%;
             left: 50%;
             transform: translate(-50%,-50%);
           }
        }

        .vlog_down{
            text-indent: 1em;
           .title{
               font-size: 1.2vw;
               line-height: 3vw;
           }
           .info{
               font-size: 1vw;
               color: #666;
               line-height: 1vw;
               display: flex;
               justify-content: space-between;
               .time{
                 margin-right: 1vw;
               }
           }
        }
    }

    .vlog_item:hover{
        box-shadow: 5px 5px 7px rgba(217, 82, 9, 0.24), 0 0 6px rgba(236, 51, 9, 0.08)
    }
  }

  @media screen and (max-width: 900px){
    .vlog{
    display: flex;
    // justify-content: space-around;
    flex-wrap: wrap;
    .vlog_item{
        width: 32vw;
        height: 22vw;
        margin: 1vw 0.3vw 0 0.3vw;
        // background-color: pink;
        border-radius: 0.5vw;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        .vlog_up{
           width: 32vw;
           height: 16vw;
           background-color:rgba(0,0,0,0.9);
           position: relative;
           border-radius: 0.5vw 0.5vw 0 0;
           img{
             max-width: 32vw;
             max-height: 16vw;
             position: absolute;
             top:50%;
             left: 50%;
             transform: translate(-50%,-50%);
           }
        }

        .vlog_down{
            text-indent: 1em;
           .title{
               font-size: 2.5vw;
               line-height: 4vw;
           }
           .info{
               font-size: 1.6vw;
               color: #666;
               line-height: 2vw;
               display: flex;
               justify-content: space-between;
               .time{
                 margin-right: 1vw;
               }
           }
        }
    }

    .vlog_item:hover{
        box-shadow: 5px 5px 7px rgba(217, 82, 9, 0.24), 0 0 6px rgba(236, 51, 9, 0.08)
    }
  }
  }
  </style>