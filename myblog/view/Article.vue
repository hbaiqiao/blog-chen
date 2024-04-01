<template>
    <Main>
         <template v-slot:manager>
             <Manager></Manager>
              
         </template>
          
        <div class="article">
            <div class="article_item" v-for="blog in blogs" :key="blog.id" @click="toArticle(blog.id)">
                <div class="item_left">
                     <div class="title">
                         {{ blog.title }}
                     </div>
                     <div class="info">
                         {{ blog.info }}
                     </div>
                     <div class="info_box">
                        <span> {{ blog.time }} &ensp;&emsp;{{ blog.author }}</span>
                        <span> {{ blog.type }}</span>
                     </div>
                </div>
                <div class="item_right">
                     <div class="picture">
                          <img :src="blog.cover" alt="" mode="aspectFill">
                     </div>
                </div>
                
            </div>
            
            <div v-if="pageshow">
                <Page :sizeNum="10" :pageIndex="pageIndex" :totalPages="totalPages" @changePage="getPage"></Page>
            </div>
          
        </div>
    
    </Main>
</template>

<script>
import Main from "../src/components/Main.vue"
import Manager from "../src/components/Manager.vue"
import datetime from '../js/datetime.js'
import Page from "../src/components/Page.vue"
export default{
    components:{
        Main:Main,
        Page:Page,
        Manager:Manager,
    },
    data(){
        return{
            articleImg:require("../src/assets/logo2.jpg"),
            blogs:null,
            pageIndex:1,
            totalPages:1,
            pageshow:false,
        }
    },
    created(){
        //  this.getdata()
        this.getdataPage(10,this.pageIndex)
    },
    methods:{
      getdata(){
            this.axios.get("/blogs")
            .then(res=>{
                console.log(res);
                this.blogs = res.data;
                this.blogs.forEach(item => {
                    item.time = datetime.format(item.time, 'yyyy-MM-dd HH:mm:ss')
                });
            })
      },
      getdataPage(size,index){
         this.axios.get("/blogsPage/"+ size + '/' + index,)
            .then(res=>{
                console.log(res);
                this.totalPages = res.data.totalPages; 
                console.log(this.totalPages);
                this.pageshow = true;
                this.blogs = res.data.content;
                this.blogs.forEach(item => {
                    item.time = datetime.format(item.time, 'yyyy-MM-dd HH:mm:ss')
                });
            })
      },
      toArticle(id){
           this.$router.push({path:'/articleDetail', query:{id:id,size:10,page:this.pageIndex} })
      },
     getPage(size,page,index){
         this.getdataPage(size,page)
     }
    },
}
</script>

<style scoped lang="scss">
.article{
    // width: 100%;
    // padding: 0 1vw;
    min-height: 50vw;
    box-sizing: border-box;
}
.article_item{
    width: 70vw;
    height: 10vw;
    // background-color: skyblue;
    display: flex;
    border-radius: 1vw;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    // box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.item_left{
    width: 57vw;
    height: 100%;
    box-sizing: border-box;
    padding: 0 1vw;
    .title{
        width: 100%;
        height: 4vw;
        overflow: hidden;
        line-height: 4vw;
        font-size: 1.2vw;
        box-sizing: border-box;
        border-bottom: 2px solid #eee;
    }     
    .info{
       line-height: 2vw; 
       overflow: hidden;
       height:4vw ;
       color: #666;
    }
    .info_box{
        display: flex;
        justify-content: space-between;
        line-height: 2vw;
        height: 2vw;
        color: #666;
    
    }
}
.item_right{
    width: 13vw;
    height: 10vw;
    .picture{
        position: relative;
        width: 9vw;
        height: 9vw;   
        margin: 0.5vw 2.5vw; 
        // margin: auto;   
        text-align: center;
        line-height: 8vw;
        img{
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
            max-width: 8vw;
            max-height: 8vw;
            margin: auto;
        }
    }
    
}
.article_item:hover{
    // width: 69vw;
    // height: 10.5vw;
    box-shadow: 5px 5px 7px rgba(15, 164, 209, 0.24), 0 0 6px rgba(10, 141, 218, 0.08)
}

@media screen and (max-width: 900px){
    .article{
    // width: 100%;
    // padding: 0 1vw;
    min-height: 50vw;
    box-sizing: border-box;
}
.article_item{
    width: 100vw;
    height: 30vw;
    // background-color: skyblue;
    box-sizing: border-box;
    display: flex;
    border-radius: 1vw;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    // box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
}
.item_left{
    width: 66vw;
    height: 30vw;
    box-sizing: border-box;
    padding: 0 4vw;
    .title{
        width: 100%;
        height: 12vw;
        overflow: hidden;
        line-height: 12vw;
        font-size: 4vw;
        box-sizing: border-box;
        border-bottom: 2px solid #eee;
    }     
    .info{
        width: 100%;
       line-height: 6vw; 
       overflow: hidden;
       height:12vw ;
       font-size: 3vw;
       color: #666;
    }
    .info_box{
        display: flex;
        justify-content: space-between;
        line-height: 6vw;
        height: 6vw;
        font-size: 3vw;
        color: #666;
    
    }
}
.item_right{
    width: 30vw;
    height: 30vw;
    .picture{
        position: relative;
        width: 30vw;
        height: 30vw;   
        // margin: 0.5vw 2.5vw; 
        // margin: auto;   
        text-align: center;
        line-height: 8vw;
        img{
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
            max-width: 28vw;
            max-height: 28vw;
            margin: auto;
        }
    }
    
}    
}
</style>