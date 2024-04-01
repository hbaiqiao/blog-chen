<template>
     <div>
         <h3>{{msg}}</h3> 
         <h4>计数为{{count}}</h4>
        <input type="text"  ref="password" v-model="pwd" @keyup="pwdchange">
        <button @click="addItem">添加数据</button>  
        <slot>
            默认插槽内容
        </slot> 
        
        <slot name = "one"></slot>
        <slot name = "two"></slot>
    </div>
</template>

<script>
export default{
    name:"Children",
    props:{
       msg:String,
       count:Number,
    },
    setup(){
    //    console.log($parent);
    },

    data(){
        return{
            pwd:'',
            items:[],
            index:0,
        }
    },
    methods:{
       addItem(){
           this.items.push('这是第+'+this.index+' 条数据')
           this.index++
           this.$emit('getItem',this.items)
       },
       pwdchange(){
        console.log(this.pwd);
          this.$emit('getNum',this.pwd)
       } 
    }
    
}
</script>