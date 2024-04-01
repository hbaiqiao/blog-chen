<template>
    <Main>
        <template v-slot:manager>
          <div class="vlogList">
               <h3>更多视频</h3>
               <div class="item" v-for="item in vlogList" :key="item.id" @click="toVlog(item.id)">
                    <div class="item_left">
                        <img :src="item.cover" alt="">
                    </div>
                    <div class="item_right">
                        <div class="item_hd">{{ item.title }}</div>
                        <div class="item_bd">{{ item.info }}</div>
                    </div>
                   
               </div>
          </div>
              
      </template>
         <div class="vlogDetail">
             <div class="title">{{ title }}</div>
             <div class="msg">{{ author }} &emsp;&emsp; {{ time }}</div>
             <div class="vediobox">
                  <video :src="src" controls class="vedio" ref="vedio"></video>
             </div>
             <div class="info">简介:&emsp;{{ info }}</div>
         </div>
    </Main>
</template>

<script>
import Main from "../src/components/Main.vue"
import datetime from "../js/datetime"
export default{
    components:{
       Main:Main,
    },
    data(){
        return{
            title:'',
            author:'',
            time:'',
            src:'',
            info:'',
            vlogList:[],
        }
    },
    created(){
        this.getData()
        this.getdataPage(this.$route.query.size,this.$route.query.page)
    },
    methods:{
        toVlog(id){
            this.$router.replace({path:'/vlogDetail', query:{id:id,size:this.$route.query.size,page:this.$route.query.page} })
            this.getData()
        },
        getdataPage(size,index){
            this.axios.get("/vlogPage/" + size +'/'+ index)
           .then(res=>{
               console.log(res);
             
               this.vlogList = res.data.content
           })
        },
        getData(){ 
            this.axios.get(`/vlog/${this.$route.query.id}`)
            .then(res=>{
                 this.title = res.data.title
                 this.author = res.data.author
                 this.time  = datetime.format(res.data.time, 'yyyy-MM-dd HH:mm:ss')
                 this.src = res.data.src
                 this.info  = res.data.info
                 setTimeout(()=>{
                    this.$refs.vedio.play()
                 },2000)
                 
            })

      },
    },
}
</script>


<style lang="scss">
.vlogDetail{
    .title{
        font-size: 1.5vw;
        line-height: 3vw;
    }
    .msg{
        font-size: 1vw;
        line-height: 2vw;
        color: #666;
    }
    .vediobox{
        width: 70vw;
        height: 37vw;
        background-color:rgba(0, 0, 0, 1);
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        // background-color: pink;
        position: relative;
        .vedio{
            // width: 68vw;
            max-width: 70vw;
            max-height: 37vw;
            position: absolute;
            top:50%;
            left: 50%;
            transform: translate(-50%,-50%);
            margin: 0 auto;
        }
    }
    .info{
        margin-top: 1vw;
        font-size: 1vw;
        line-height: 1.5vw;
    }
}
.vlogList{
    h3{
        line-height: 3vw;
        background-color: skyblue;
        color: #fff;
        font-size: 1.2vw;
        line-height: 3vw;
    }
    .item{
       width: 17.5vw;
       height: 6vw;
       margin: 0.5vw 0;
      
       box-sizing: border-box;
    //    background-color: pink;
       border-bottom: 0.1vw solid #eee;
       display: flex;
       .item_left{
          width: 6vw;
          height: 6vw;    
          img{
            width: 4vw;
            height: 4vw;
            margin: 1vw;
          }  
        }
        .item_right{
            width: 12vw;
            margin: 1vw 0;
            line-height: 2vw;
            font-size: 1vw;
            text-align: left;
            .item_hd{
               width: 11vw;
               height: 2vw;
               overflow: hidden;
            }
            .item_bd{
                width: 11vw;
               height: 2vw;
               overflow: hidden;
            }
        }
    }
    .item:hover{
          color: skyblue;
    }
}

@media screen and (max-width: 900px){
    .vlogDetail{
    .title{
        font-size: 6vw;
        line-height: 10vw;
    }
    .msg{
        font-size: 3vw;
        line-height: 5vw;
        color: #666;
    }
    .vediobox{
        width: 100vw;
        height:90vh;
        background-color:rgba(0, 0, 0, 1);
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        // background-color: pink;
        position: relative;
        .vedio{
            // width: 68vw;
            max-width: 100vw;
            max-height: 90vh;
            position: absolute;
            top:50%;
            left: 50%;
            transform: translate(-50%,-50%);
            margin: 0 auto;
        }
    }
    .info{
        margin-top: 1vw;
        font-size: 3vw;
        line-height: 6vw;
    }
}



}
</style>