<template>
    <div class="article">
        <Header></Header>
        <div class="content">
           
               <div class="menu">
                    <div class="menu_info">
                        <div class="owner">
                            <img src="../src/assets/logo2.jpg" alt="">
                            <div>
                                <h3>辰 </h3>
                                <h5>开发者</h5>
                            </div>
                             
                        </div>
                       <h5>Vue、React、JavaScript</h5>
                       <h5>Java、SpringBoot、Mysql</h5>
                       <h5>人工智能、Python、C++</h5>
                       <br>
                       <h5>邮箱: hbaiqiao@163.com</h5>
                       <br>
                    </div>
                    
                    
                     <div class="menu_item">
                        <h4>博客栏目</h4>
                        <h6>建站指南</h6>
                        <h6>建站指南</h6>
                        <h6>建站指南</h6>
                        <h6>建站指南</h6>
                        <h6>建站指南</h6>
                        <h6>建站指南</h6>
                        <h6>建站指南</h6>
                        <h6>建站指南</h6>
                     </div>
               </div>
            

       
             <div class="main">
                 <slot></slot>
             </div>
          
        </div>
        
    </div>
</template>

<script>
import Header from "../src/components/Header.vue"
export default{
  name:'Article',
  components:{
    Header:Header,
  }
}
</script>

<style lang="scss" scoped>
.header{
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.content{
    width:91vw;
    margin: 0 auto;
    margin: 0 4.5vw;
    display: flex;
    // box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.menu{
    width: 20vw;
    height: 100vh;
    text-align: center;
    // margin: 1vw 1vw 1vw 0;
    
    .menu_info{
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        margin: 1vw 0.5vw 0.5vw 0;
    }
    .menu_item{
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        margin: 0 0.5vw 0 0;
        min-height: 55vh;
    }
    .owner{
        display: flex;
        text-align: left;
        // margin: 2vw;
        padding: 2vw;
    }
    img{
        width: 6vw;
        height: 6vw;
        // margin: 2vw 6vw 0vw 6vw;
        border-radius: 3vw;
        margin: 0 1.5vw 0 0;
    }
    h3{
       font-size: 1.5vw; 
       font-weight: 400;
       line-height: 4vw;
    }
    h4{
        font-size: 1.5vw;
        color: deepskyblue;
        font-weight: 400;
        // margin: 2vw 0 1vw 0;
        line-height: 4vw;
      
    }
    h5{
        font-size: 1vw;
        color: #333;
        font-weight: 300;
    }
    h6{
        font-size: 1vw;
        color: #333;
        font-weight: 300;
        line-height: 2vw;
    }
    
}

.main{
   width: 70vw;
   height: 100vh;
   margin: 1vw 0 1vw 0;
   box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style>