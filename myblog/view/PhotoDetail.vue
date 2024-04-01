<template>
    <Main>
         <div class="photoDetail">
            
            
             <div class="imgbox">
                 <img :src="src" alt="">
             </div>
             <div class="msg">{{ author }} &emsp;&emsp; {{ time }}</div>
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
            author:'',
            time:'',
            src:'',
        }
    },
    created(){
        this.getData()
    },
    methods:{

        getData(){
            
            if(this.$route.query.type==1){
                this.axios.get(`/photoWidth/${this.$route.query.id}`)
                .then(res=>{            
                     this.author = res.data.author
                     this.time  = datetime.format(res.data.time, 'yyyy-MM-dd HH:mm:ss')
                     this.src = res.data.src            
                })
            }
            else if(this.$route.query.type==2){
                this.axios.get(`/photoHeight/${this.$route.query.id}`)
                .then(res=>{            
                     this.author = res.data.author
                     this.time  = datetime.format(res.data.time, 'yyyy-MM-dd HH:mm:ss')
                     this.src = res.data.src            
                })                
            }

           

      },
    },
}
</script>


<style lang="scss">
.photoDetail{
    .imgbox{
        width: 70vw;
        height: 43vw;
        // background-color:pink;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        position: relative;
        img{
           max-width: 70vw;
           max-height: 43vw;
           position: absolute;
           top:50%;
           left: 50%;
           transform: translate(-50%,-50%);
        }
    }
    .msg{
        text-align: center;
        font-size: 1vw;
        line-height: 2vw;
    }
}

@media screen and (max-width: 900px){
    .photoDetail{
    .imgbox{
        width: 100vw;
        height: 100vh;
        // background-color:pink;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        position: relative;
        img{
           max-width: 100vw;
           max-height: 100vh;
           position: absolute;
           top:50%;
           left: 50%;
           transform: translate(-50%,-50%);
        }
    }
    .msg{
        text-align: center;
        font-size: 2vw;
        line-height: 4vw;
    }
}
}
</style>