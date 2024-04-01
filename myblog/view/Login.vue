<template>
    <div class="login">
         <Header></Header>
         <img src="../src/assets/backgroud.jpg" alt="" class="backgroud">
         <div class="loginform">
             <h3>博客登录</h3>
             <div class="num">账号 &emsp;<br><input type="text" v-model="num"> </div>
             <div class="num">密码  &emsp;<br><input type="text" v-model="password" placeholder="密码为手机号码"> </div>
             <div class="btn" @click="login">登&emsp;录</div>
         </div>
       
    </div>
</template>

<script>
import Header from "../src/components/Header.vue"
export default{
    components:{
        Header:Header,
    },
    data(){
        return{
            num:'hbaiqiao@163.com',
            password:''
        }
    },
    methods:{
        login () {
            //  var _this = this
             console.log(this.$store.state)
             let manager = {
                  num:this.num,
                  password:this.password,
             }
             this.axios
               .post('/login', {
                  num:this.num,
                  password:this.password,
               })
               .then(successResponse => {
                 if (successResponse.data.code === 200) {
                   // var data = this.loginForm
                   console.log(this.$store.state)
                   this.$store.commit('login', manager)
                   let path = this.$route.query.redirect
               
                   this.$router.replace({path: path === '/' || path === undefined ? '/' : path})
                    // this.$router.replace({name:'articleAdd'})
                 }
                 if (successResponse.data.code === 400) {
                    alert("密码错误")
                 }
               })
               .catch(failResponse => {
                    alert("网络错误")
               }) 
        }

    }
}
</script>

<style lang="scss">

.login{
    .header{
      position: absolute;
      width: 100%;
      top: 0;
      left: 0;
      background-color: rgba(255,255,255,0);
      box-shadow: 0 0 0 0;
      color: #fff;
      .hd_left{
         color: #fff;
      }
   }
   .header:hover{
      background-color: rgba(0,0,0,0.6);
   }

    width: 100vw;
    height: 99.5vh;
    .backgroud{
        width: 100vw;
        height: 99.5vh;
    }
    .loginform{
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
        width: 36vw;
        height: 25vw;
        font-size: 1.2vw;
        line-height: 3vw;
        background-color: rgba(255,255,255,0.2);
        border-radius: 1vw;
        h3{
            text-align: center;
            font-size: 1.5vw;
            color: deepskyblue;
            line-height: 4vw;
            font-weight: 300;
        }
        .num{
        //    text-indent: 2em;
           width: 25vw;
           height: 6vw;
           margin: 0 auto;
        }
        input{
            font-family: Avenir, Helvetica, Arial, sans-serif;
            background-color: rgba(255,255,255,0.1);
            width: 25vw;
            height: 3vw;
            line-height: 3vw;
            text-indent: 1em;
            font-size: 1.2vw;
            outline: 0;
            // margin: 1vw 0vw;
            border: 0;
            border-bottom: 0.2vw solid  deepskyblue;
        }
        .btn{
            width: 12vw;
            height: 3vw;
            font-size: 1.2vw;
            text-align: center;
            line-height: 3vw;
            border-radius: 1vw;
            background-color: deepskyblue;
            color: #fff;
            margin: 2vw auto;
        }
    }
}
</style>