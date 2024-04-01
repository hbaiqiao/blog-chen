<template>
     <div class="page">
          <span class="btn" @click="pageGo(-1)">&lt;&lt;上一页</span>
          <span>当前&ensp;{{page}}&ensp;页</span>
          <div class="pagebox" ref="pagebox">
              <ul ref="ul">
                  <li v-for="(item,index) in total" :key="index" :class="page==index+1?'btn':''" @click="pageTo(index+1)">{{ index+1}}</li>   
              </ul>
          </div>                        
          <li v-if="total>6 && page< total -2 ">...</li>
          <span >总数&ensp;{{total}}&ensp;页</span>
          <span class="btn" @click="pageGo(1)">下一页&gt;&gt;</span>
     </div>        
</template>

<script>
export default{
    props:{
       sizeNum:Number,
       pageIndex:Number,
       totalPages:Number,
    },
    data(){
        return{
            page:1,
            total:1,
            size:10,
        }
    },
    created(){ 
        this.size = this.sizeNum
        this.page = this.pageIndex
        this.total = this.totalPages
    },
    methods:{
        pageGo(index){
            if(index==-1){
                this.page= this.page-1
            }
            if(index==1){
                this.page = this.page+1
            }
            if(this.page<=0){
                this.page= this.total
            }
            if(this.page>this.total){
                this.page = 1
            }
            this.movePage()
            this.$emit('changePage',this.size,this.page,this.total)
         },
         pageTo(index){
            this.page = index
            this.movePage()
            this.$emit('changePage',this.size,this.page,this.total)
         },


         movePage(){
            let width =  this.$refs.ul.children[0].offsetWidth
            console.log(width);
             if(this.page<5){
                return
             }
             if(this.page>3&&this.page<this.total-1){
                
                 this.$refs.ul.style.left = -((this.page-3)*width) + 'px'
                 console.log(this.$refs.ul.style.left);
             }
             if(this.page==1){
                this.$refs.ul.style.left = 0 +'px'
             }
             if(this.page >=this.total-2){
                 this.$refs.ul.style.left = -((this.total/2)*width) +'px'
             }
         },        
    }

}
</script>

<style lang="scss">
    .page{
        margin: 3vw auto;
        width: 40vw;
        height: 2vw;
        text-align: center;
        line-height: 2vw;
        color:#666;
        font-size: 1.2vw;
        // background-color: pink;
        display: flex;
        .btn{
            color: deepskyblue;
            text-decoration: underline;
        }
        span{
           width: 7vw;
        }
        .pagebox{
            width: 100%;
            max-width: 12vw;
            overflow: hidden;
            // background-color: pink;
            position: relative;
        }
        ul{ 
            list-style: none;
            position: absolute;
            left: 0;
            top:0;
            height: 2vw;
            
        }
        li{ 
            list-style: none;
            display: inline-block;
            width: 2vw;
            text-align: center;
            // background-color: #fff;
            // padding: 0 0.5vw;
        }
    }

    @media screen and (max-width: 900px){
        .page{
        margin: 5vw auto;
        width: 80vw;
        height: 5vw;
        text-align: center;
        line-height: 5vw;
        color:#666;
        font-size: 3vw;
        // background-color: pink;
        display: flex;
        .btn{
            color: deepskyblue;
            text-decoration: underline;
        }
        span{
           width: 14vw;
        }
        .pagebox{
            width: 100%;
            max-width: 22vw;
            overflow: hidden;
            // background-color: pink;
            position: relative;
        }
        ul{ 
            list-style: none;
            position: absolute;
            left: 0;
            top:0;
            height: 5vw;
            
        }
        li{ 
            list-style: none;
            display: inline-block;
            width: 4vw;
            text-align: center;
            // background-color: #fff;
            // padding: 0 0.5vw;
        }
    }
    }
</style>