<template>
   <h2>这是Home页面</h2>
   <Children msg='这是子组件' count=40  @getItem="getItem" @getNum="getNum">
       <h1>这是默认插槽片段</h1>
       <template v-slot:one>
          <h5>插槽一的内容</h5>
      </template>
      <template #two>
          <h5>插槽二的内容</h5>
      </template>
   </Children>
   <ul v-for="(item,index) in items" :key=index>
      <li>{{item}}</li>
   </ul>
   <h3>你的账号是{{num}}</h3>
   <div>
      <span>用户名：{{$store.state.user.name}} 账号：{{$store.state.user.num}}</span>
   </div>
   <button @click="changeName">更改名字</button>
   <button @click="getInput">ref获取</button>
</template>

<script>

import Children from '../src/components/Children.vue'
import { useStore } from 'vuex' // 引入useStore 方法


export default {
   name:'homePage',
   components:{
      Children,
   },
   data(){
      return{
         items:[],
         num:'',
      }
      
   },
   setup(){
      // const store = useStore()  // 该方法用于返回store 实例
      // console.log(store)  // store 实例对象
   },
   methods:{
      getItem(items){
         this.items =items
      },
      getNum(num){
         this.num=num
      },
      changeName(){
         console.log(this.$store.state);
         console.log(this.$store.state.user.name);
         this.$store.state.user.name = '小明'
         console.log(this.$store.state.user.name);
         console.log(this.$store.state);
      },
      getInput(){
        
      },
   }
}
</script>

<style>

</style>