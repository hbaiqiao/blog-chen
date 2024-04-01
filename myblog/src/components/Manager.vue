<template>
    <div class="manager_menu">  
       <h4>博客栏目</h4>
       <h6 @click="()=>{this.$router.push('/')}">首页</h6>
       <h4 >博客管理</h4>
        <h6 @click="()=>{this.$router.push('/login')}" v-if="!this.$store.state.manager">博客登录</h6>
       <h6 v-if="this.$store.state.manager">账号:{{ this.$store.state.manager.num }}</h6>
       <h6 @click="()=>{this.$router.push('/manager/article')}">发布文章</h6>
       <h6 @click="()=>{this.$router.push('/manager/music')}">发布音乐</h6>
       <h6 @click="()=>{this.$router.push('/manager/vlog')}">发布视频</h6>
       <h6 @click="()=>{this.$router.push('/manager/photo')}">发布壁纸</h6> 
    </div>    
</template>

<style lang="scss" scoped>
.manager_menu{
    h3{
       font-size: 1.3vw; 
       font-weight: 400;
       line-height: 4vw;
    }
    h4{
        font-size: 1.2vw;
        // color: deepskyblue;
        color: #fff;
        background-color: skyblue;
        font-weight: 400;
        // margin: 2vw 0 1vw 0;
        line-height: 3vw;
      
    }
    h5{
        font-size: 1vw;
        line-height: 1.5vw;
        color: #333;
        font-weight: 300;
    }
    h6{
        font-size: 1.2vw;
        color: #333;
        font-weight: 300;
        line-height: 3vw;
       
    }
    h6:hover{
        text-decoration:underline;
    }
}
</style>