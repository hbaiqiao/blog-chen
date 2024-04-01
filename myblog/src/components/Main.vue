<template>
    <div class="blog">
        <Header></Header>
        <div class="content">
           
               <div class="menu">
                    <div class="menu_info">
                        <div class="owner">
                            <img src="../assets/logo2.jpg" alt="">
                            <div>
                                <h3>辰 </h3>
                                <h5>开发者</h5>
                            </div>
                             
                        </div>
                       <h5>Vue、React、JavaScript</h5>
                       <h5>Java、SpringBoot、Mysql</h5>
                       <h5>Linux、Python、C++</h5>
                       <br>
                       <h5>邮箱: hbaiqiao@163.com</h5>
                       <br>
                    </div>
                    
                    
                     <div class="menu_item">
                         <slot name ="manager"></slot>
                     </div>
               </div>
            

       
             <div class="main">
                 <slot></slot>
             </div>
          
        </div>
        
    </div>
</template>

<script>
import Header from "./Header.vue"
import Manager from "./Manager.vue"
export default{
  name:'Article',
  components:{
    Header:Header,
    Manager:Manager,
  }
}
</script>

<style lang="scss" scoped>

//博客背景颜色
.blog{
    // background-color: #68cde1;
    background-color:rgba(135,206,235,0.2);
}
.header{
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.content{
    width:91vw;
    margin: 0.5vw auto;
    // margin: 0 4.5vw;
    display: flex;
    // box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    // background-color:rgba(135,206,235,1);
}
.menu{
    width: 18vw;
    height: 100vh;
    text-align: center;
    // margin: 1vw 1vw 1vw 0;
    // background-color: #fff;
    position: sticky;
    top: 3vw;
    .menu_info{
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        margin: 0 0.5vw 0.5vw 0;
        background-color: #fff;
    }
    .menu_item{
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        margin: 0 0.5vw 0 0;
        min-height: 55vh;
        background-color: #fff;
        // overflow-y: scroll;
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

.main{
   width: 72vw;
//    height: 100vh;
//    padding: 1vw 0 1vw 0;
   padding: 1vw;
   box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
   box-sizing: border-box;
   background-color: #fff;
}

@media screen and (max-width: 900px){
    .menu{
        display: none;
    }
    .content{
        width: 100vw;
        margin:  0 auto;
        padding: 0;
    }
    .main{
       width: 100vw;
       margin: 0;
       padding: 0;
    }
   
}
</style>