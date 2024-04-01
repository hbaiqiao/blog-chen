<template>
  <Main>
       <template v-slot:manager>
             <Manager></Manager>
      </template>
      <div class="music">
           <div class="music_item" v-for="item in musicList" :key="item.id" @click="toMusic(item.id)">
                <div class="music_left">
                    <img :src="item.cover" alt="">
                </div>
                <div class="music_right">
                     <div class="title">{{ item.title }}</div>
                     <div class="musician">{{ item.musician }}</div>
                </div>
           </div>
           
      </div>
      <div v-if="pageshow">
        <Page :sizeNum="10" :pageIndex="pageIndex" :totalPages="totalPages" @changePage="getPage"></Page>
      </div>
  </Main>
</template>

<script>
import Main from "../src/components/Main.vue"
import Page from "../src/components/Page.vue"
import Manager from "../src/components/Manager.vue"
export default{
    components:{
        Main:Main,
        Page:Page,
        Manager:Manager,
    },
    created(){
    //    this.getdata()
       this.getdataPage(10,1)
    },
    data(){
        return{
            src:'',
            title:'',
            music:'',
            musicList:[{}],
            pageIndex:1,
            totalPages:1,
            pageshow:false,
        }
    },
    methods:{
        toMusic(id){
            this.$router.push({path:'/musicDetail', query:{id:id,size:10,page:this.pageIndex} })
        },
        getdata(){
           this.axios.get("/musics")
           .then(res=>{
               console.log(res);
               this.musicList = res.data
           })
        },
        getdataPage(size,index){
            this.axios.get("/musicPage/" + size +'/'+ index)
           .then(res=>{
               console.log(res);
               this.totalPages = res.data.totalPages
               this.pageshow = true
               this.musicList = res.data.content
           })
        },
        getPage(size,page,total){
           this.getdataPage(size,page)
           this.pageIndex = page
        },
    }
    
}
</script>

<style lang="scss">
  .music{
      width: 70vw;
    //   min-height: 50vw;
      display: flex;
      flex-wrap: wrap;
      .music_item{
         margin-top: 1vw;
         margin-right: 0.5vw;
         margin-left: 0.5vw;
         width: 34vw;
         height: 8vw;
        //  background-color: deeppink;
        //  margin-right: 1vw;
         border-radius: 0.5vw;
         display: flex;
         box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
         .music_left{
            position: relative;
            width: 8vw;
            height: 8vw;
            // background-color: pink;
            margin-left: 1vw;
            img{
                max-width: 8vw;
                max-height: 8vw;
                position: absolute;
                top:50%;
                left: 50%;
                transform: translate(-50%,-50%);
            }
         }
         .music_right{
            // background-color: palegoldenrod;;
            width: 23vw;
            height: 8vw;
            text-indent: 2em;
            margin-right: 1vw;
             .title{
                width: 100%;
                line-height: 4vw;
                font-size: 1.3vw;
                // background-color: palevioletred;
             }
             .musician{
                width: 100%;
                line-height: 4vw;
                font-size: 1.2vw;
                color: #666;
             }
         }
      }
      .music_item:hover{
        box-shadow: 5px 5px 7px rgba(209, 141, 15, 0.24), 0 0 6px rgba(236, 202, 9, 0.08)
      }
  }

  @media screen and (max-width: 900px){
    .music{
      width: 100vw;
    //   min-height: 100vh;
      display: flex;
      flex-wrap: wrap;
      .music_item{
         margin-top: 3vw;
        //  margin-right: 1vw;
        //  margin-left: 1vw;
         width: 48vw;
         height: 20vw;
        //  background-color: deeppink;
        //  margin-right: 1vw;
         border-radius: 0.5vw;
         display: flex;
         justify-content: space-around;
         box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
         .music_left{
            position: relative;
            width: 20vw;
            height: 20vw;
            // background-color: pink;
            margin-left: 1vw;
            img{
                max-width: 18vw;
                max-height: 18vw;
                position: absolute;
                top:50%;
                left: 50%;
                transform: translate(-50%,-50%);
            }
         }
         .music_right{
            // background-color: palegoldenrod;;
            width: 28vw;
            height: 20vw;
            text-indent: 2em;
            margin-right: 1vw;
             .title{
                width: 100%;
                line-height: 10vw;
                font-size: 3vw;
                height: 10vw;
                overflow: hidden;
                // background-color: palevioletred;
             }
             .musician{
                width: 100%;
                line-height: 10vw;
                font-size: 3vw;
                color: #666;
                height: 10vw;
                overflow: hidden;
             }
         }
      }
      .music_item:hover{
        box-shadow: 5px 5px 7px rgba(209, 141, 15, 0.24), 0 0 6px rgba(236, 202, 9, 0.08)
      }
  }
  }
</style>