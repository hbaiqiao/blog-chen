<template>
  <Main>
    <div class="articleAdd">
        <div class="title">
             <input type="text" v-model="title" class="title" placeholder="输入文章标题">
            <span class="button" @click="publish">发布</span>
        </div>

           <div class="publish">
              
              <div class="tip"> <input type="text" v-model="type" class="tip" placeholder="输入文章标签"></div>
              <!-- <div class="tip">发布时间: 2023/8/1 20:20:20</div> -->
           </div>
           
           <div class="addcontent">     
                   <li>添加:</li>
                   <li @click="addText">段落</li>
                   <li @click="addCode">代码</li>
                   <li @click="addH3">一级标题</li>
                   <li @click="addH4">二级标题</li>
                   <li @click="addPic">图片文件</li>
                   
           </div>
           <div class="content" ref="content">
            
             
           </div>
    </div>
  </Main>
</template>

<script>
import Main from '../src/components/Main.vue'
export default{
    components:{
        Main:Main,
    },
    data(){
       return{
         title:'',
         type:'',
         filesData:[],
       }
    },
    mounted(){
       this.addText()
    },
    methods:{
       addH3(){
          //新建H3标题
          let H3 = document.createElement('h3')
          let input = document.createElement('input')
          input.placeholder = '添加一级标题'
          let h3 = document.createElement('h3')
          H3.appendChild(input)
        
          let content = this.$refs.content
          content.appendChild(H3)
          input.focus()
          input.addEventListener('blur',()=>{
             
            if(!input.value){
               content.removeChild(H3)
               return
             }
            console.log(input.value);
            h3.innerHTML = input.value
            content.removeChild(H3)
            content.appendChild(h3)
         
          })
          //修改H3标题
          h3.addEventListener("click",()=>{
            let H3 = document.createElement('h3')
            let input = document.createElement('input')
            input.value = h3.innerHTML
            // h3.innerHTML = ''
            console.log(h3.innerHTML);
            // let h3 = document.createElement('h3')
            H3.appendChild(input)
            // h3  = H3
            h3.innerHTML = null
            h3.appendChild(H3)
            input.focus()
            input.addEventListener('blur',()=>{
                 console.log(input.value);
                 h3.innerHTML = input.value
                //  h3.removeChild(H3)
            })
          })
        //   input.onClick(()=>{
        //      alert('okokokok')
        //   })
        //   input.blur(()=>{
        //       h3.innerHTML = input.value
        //       h3.removeChild(input)
        //   })
        //   content.appendChild(h3)
        //   let h3_title = this.$refs.h3_title
        //   content.appendChild(h3_title)
        
       },
       addH4(){
       //新建H4标题
          let H4 = document.createElement('h4')
          let input = document.createElement('input')
          input.placeholder = '添加二级标题'
          let h4 = document.createElement('h4')
          H4.appendChild(input)
        
          let content = this.$refs.content
          content.appendChild(H4)
          input.focus()
          input.addEventListener('blur',()=>{
            if(!input.value){
               content.removeChild(H4)
               return
             }
            console.log(input.value);
            h4.innerHTML = input.value
            content.removeChild(H4)
            content.appendChild(h4)
         
          })
          //修改H4标题
          h4.addEventListener("click",()=>{
            let H4 = document.createElement('h4')
            let input = document.createElement('input')
            input.value = h4.innerHTML
            // h3.innerHTML = ''
            console.log(h4.innerHTML);
            // let h3 = document.createElement('h3')
            H4.appendChild(input)
            // h3  = H3
            h4.innerHTML = null
            h4.appendChild(H4)
            input.focus()
            input.addEventListener('blur',()=>{
                 console.log(input.value);
                 h4.innerHTML = input.value
                //  h3.removeChild(H3)
            })
          })
        
       },
       addPic(){
          let fileSelect = document.createElement('input')
          fileSelect.type = 'file'
          let H3 = document.createElement('h3')
          let content = this.$refs.content
          H3.appendChild(fileSelect)
        //   content.appendChild(H3)
          let img = document.createElement('img')

          fileSelect.click()
          fileSelect.addEventListener('change',()=>{
            if(!fileSelect.files[0]&&img.src) {
                for(let i = 0;i<this.filesData.length;i++){
                     if(img.dataName == this.filesData[i].name){
                           this.filesData.splice(i,1)
                           console.log(this.filesData);
                     }
                }
                content.removeChild(img)
                return
            }
            console.log(fileSelect.files[0]);
           
            let file = fileSelect.files[0]
             if(file.type.search(/image/)==-1){
                  alert('选择文件非图片类型')
                  return
               }
            console.log(file.name);
            let date = new Date()
            //完成文件的随机命名
            img.dataName ='' + date.getFullYear() + date.getMonth() + date.getDay() + date.getHours()+date.getMinutes() +date.getSeconds()+Math.floor(Math.random()*1000)+Math.floor(Math.random()*1000) + file.name.substr(file.name.lastIndexOf("."));
            //复制文件重命名

            var newFile = new File([file],img.dataName,{type:file.type});
            console.log(newFile.name);
            //添加进文件组
            this.filesData.push(newFile)

            //完成路径src的封装
            // img.dataSrc = 'http://127.0.0.1:6660/api/file/blog/'+ img.dataName
            img.dataSrc = this.axios.defaults.baseURL +'/file/blog/'+ img.dataName
             if(window.FileReader){
	         	//创建读取文件的对象
	         	var fr = new FileReader();
	         	//以读取文件字符串的方式读取文件 但是不能直接读取file 
	         	//因为文件的内容是存在file对象下面的files数组中的
	         	//该方法结束后图片会以data:URL格式的字符串（base64编码）存储在fr对象的result中
	         	fr.readAsDataURL(file);
	         	fr.onloadend = ()=>{
	         		// document.getElementById("img").src = fr.result;
                    img.src = fr.result
	         	}
	         }
             content.appendChild(img)
          })
          //更改图片
          
          img.addEventListener('click',()=>{
               fileSelect.click()
                // img.addEventListener('keypress',(event)=>{
                //      console.log(event.key);
                //     //  img.removeEventListener('keypress')
                //     // if(event.key == 'enter'){
                //     //     content.removeChild(img)
                //     // }
                     
                // })
              
          })
         
       },
       addText(){
          let  clickAble = true
          let textarea = document.createElement('textarea')
          textarea.placeholder = "添加段落"
          let div = document.createElement('div')
        //   div.style.backgroundColor = "pink"
        //   textarea.innerHTML = "hhhhhhh"
        //   textarea.contenteditable = true
          let content = this.$refs.content
          content.appendChild(textarea)
          textarea.focus()
        
          textarea.addEventListener('blur',()=>{
             if(!textarea.value){
                content.removeChild(textarea)
                return
              }
              console.log(textarea.value);
              let text = textarea.value
              text = text.replace(/\r\n/g, '<br/>'); //IE9、FF、chrome
		          text = text.replace(/\n/g, '<br/>'); //IE7-8
		          text = text.replace(/\s/g, '&ensp;'); //空格处理
            //   div.innerHTML = textarea.value
              div.innerHTML =  text
              content.appendChild(div)
              content.removeChild(textarea)
             
          })
          div.addEventListener('click',()=>{
            if(!clickAble){
                return
            }
             clickAble = false;
           
              let textarea = document.createElement('textarea')
              textarea.style.minHeight = div.offsetHeight +'px'
              textarea.style.width = div.offsetWidth +'px'
                console.log(div.offsetHeight);
                console.log(textarea.offsetHeight);
            //   textarea.value = div.innerHTML
                  let text = div.innerHTML
               
                  text = text.replace(/<br>/g, '\r\n'); //IE9、FF、chrome
		              text = text.replace(/<br>/g, '\n'); //IE7-8
		              text = text.replace(/&ensp;/g, '\s'); //空格处理
                //   console.log(text);
              textarea.value = text
              console.log(textarea.value);
              div.innerHTML = null
            //   textarea.style.minHeight = div.offsetHeight +'px'
              div.appendChild(textarea)
            //   console.log(div.offsetHeight + 'px');
            //   textarea.style.minHeight = div.offsetHeight + 'px'
            //  textarea.style.minHeight =  '16px'
              textarea.focus()
              textarea.addEventListener('blur',()=>{
                  console.log(textarea.value);
                  let text = textarea.value
                  text = text.replace(/\r\n/g, '<br/>'); //IE9、FF、chrome
		          text = text.replace(/\n/g, '<br/>'); //IE7-8
		          text = text.replace(/\s/g, '&ensp;'); //空格处理
                  div.innerHTML = text
                  clickAble = true
                //   div.removeChild(textarea)
              })
          })
          
       },
       addCode(){
          let  clickAble = true
          let textarea = document.createElement('textarea')
          textarea.placeholder = "添加代码"
          let p = document.createElement('p')
        //   div.style.backgroundColor = "pink"
        //   textarea.innerHTML = "hhhhhhh"
        //   textarea.contenteditable = true
          let content = this.$refs.content
          content.appendChild(textarea)
          textarea.focus()
        
          textarea.addEventListener('blur',()=>{
             if(!textarea.value){
               content.removeChild(textarea)
               return
             }
              console.log(textarea.value);
              let text = textarea.value
              text = text.replace(/\r\n/g, '<br/>'); //IE9、FF、chrome
		          text = text.replace(/\n/g, '<br/>'); //IE7-8
		          text = text.replace(/\s/g, '&ensp;'); //空格处理
            //   div.innerHTML = textarea.value
              p.innerHTML = text
              content.appendChild(p)
              content.removeChild(textarea)
             
          })
          p.addEventListener('click',()=>{
            if(!clickAble){
                return
            }
             clickAble = false;
           
              let textarea = document.createElement('textarea')
              textarea.style.minHeight = p.offsetHeight +'px'
                console.log(p.offsetHeight);
                console.log(textarea.offsetHeight);
            //   textarea.value = div.innerHTML
                  let text = p.innerHTML
               console.log(text);
              //  text = text.replace('<br>', "\n"); //IE9、FF、chrome
		        
		          //  text = text.replace(' ', /\s/g); //空格处理
                  text = text.replace(/<br>/g, '\r\n'); //IE9、FF、chrome
		              text = text.replace(/<br>/g, '\n'); //IE7-8
		              text = text.replace(/&ensp;/g, '\s'); //空格处理              
                //   console.log(text);
              textarea.value = text
              console.log(textarea.value);
              p.innerHTML = null
            //   textarea.style.minHeight = div.offsetHeight +'px'
              p.appendChild(textarea)
            //   console.log(div.offsetHeight + 'px');
            //   textarea.style.minHeight = div.offsetHeight + 'px'
            //  textarea.style.minHeight =  '16px'
              textarea.focus()
              textarea.addEventListener('blur',()=>{
                  console.log(textarea.value);
                  let text = textarea.value
                  text = text.replace(/\r\n/g, '<br/>'); //IE9、FF、chrome
		          text = text.replace(/\n/g, '<br/>'); //IE7-8
		          text = text.replace(/\s/g, '&ensp;'); //空格处理
                  p.innerHTML = text
                  clickAble = true
                //   div.removeChild(textarea)
              })
          })        
       },
       publish(){
          console.log(this.$refs.content.innerHTML);
          console.log(this.$refs.content.children);
          if(!this.title){
               alert("标题为空！")
               return
          }
          console.log(this.filesData);
          
          //循环上传图片信息
           this.filesData.forEach((item)=>{

               const formData = new FormData();
               formData.append('file', item)

               this.axios.post('/imgBlog',
                 formData
               ).then(res=>{
                   console.log(res);
               })

          })

          //更换图片缓存路径为服务器图片路径  更换图片名字
          let img = this.$refs.content.querySelectorAll('img')
          for(let i = 0 ; i<img.length;i++){
      
             img[i].src = ''
             img[i].src = img[i].dataSrc
             console.log(img[i]);
          }

          //保存正文文字描述信息
          let info = ''
          let div = this.$refs.content.querySelector('div')
          if(div){
               info = div.innerHTML
          }
         
          // for(let i = 0 ; i<div.length;i++){
          //    info += div[i].innerHTML
          // }
          // console.log(info);

          let content  = this.$refs.content.innerHTML
           this.$refs.content.innerHTML = null

          //上传服务器
          this.axios.post('/addBlog',{
             author:this.$store.state.user.name,
             title:this.title,
             type:this.type,
             time:new Date(),
             content:content,
             cover:img[0].src? img[0].src : "",
             info:info,
          }).then(res=>{
              console.log(res);
              // this.$refs.content.innerHTML = null
          })


          // let files = this.filesData
          // 构建FormData对象,通过该对象存储要上传的文件
          console.log(this.filesData);
          // const formData = new FormData();
          // 遍历当前临时文件List,将上传文件添加到FormData对象中
          // this.filesData.forEach((item) => formData.append('file', item));
          // console.log(formData);
          // this.axios.post('/imgBlog',
          //     formData
          // ).then(res=>{
          //     console.log(res);
          // })


          //循环上传
          //  this.filesData.forEach((item)=>{

          //      const formData = new FormData();
          //      formData.append('file', item)

          //      this.axios.post('/imgBlog',
          //        formData
          //      ).then(res=>{
          //          console.log(res);
          //      })

          // })
          this.filesData = []
       }
    },
}
</script>

<style lang="scss">
.articleAdd{
    width: 100%;
    // padding:1vw 5vw;
    // box-sizing: border-box;
    input {
        box-sizing: border-box;
        border: 0;
        border-bottom: 0.05vw solid skyblue;
        // border: 0;
        // text-indent: 1em;
        font-family: Avenir, Helvetica, Arial, sans-serif;
        outline: 0;
    }
    .title{
        width: 100%;
        // height: 5vw;
        // background-color: skyblue;
        font-size: 2vw;
        // color: #fff;
        line-height: 4vw;
        display: flex;
        input{
          width: 60vw;
        }
        .button{
          width: 6vw;
          height: 3vw;
          font-size: 1.2vw;
          text-align: center;
          line-height: 3vw;
          border-radius: 1vw;
          margin: 0.5vw auto;
          background-color: rgb(231, 102, 16);
          color: #fff;
        }
    }
    .publish{
        color: #666;
        display: flex;
        margin-top: 1vw;
        .tip{
            // width: 15vw;
            // text-align: center;
            margin-right: 5vw;
        }
        // justify-content: space-between;
    }
    .addcontent{
        display: flex;
        margin-top: 1vw;
        background-color: skyblue;
        li{ 
            // background-color: skyblue;
            color:#fff;
            display: flex;
            list-style: none;
            // padding: 0 2vw;
            margin-right: 2vw;
            font-size: 1.2vw;
            line-height: 2vw;
            padding: 0 0.5vw;
        }
        li:hover{
            background-color: deepskyblue;
        }
    }
    .content{
        font-size: 1vw;
        line-height: 1.8vw;
        input {
          float: left;
          width: 100%;
          box-sizing: border-box;
          border: 0;
          border-bottom: 0.05vw solid skyblue;
        //   background-color:pink;
        //   text-indent: 0.5em;
          font-family: Avenir, Helvetica, Arial, sans-serif;
        }
        textarea{
            // display: block;
            // overflow: hidden;
            // background-color:skyblue;
            // box-sizing: border-box;
            // border-bottom: 0.05vw solid skyblue;
            resize: none;
            overflow-y: auto;
            min-width: 100%;
            max-width: 100%;
            min-height: 34vw;
            // max-height: 1000vw;
            border: 0;
            // border: 0.05vw solid skyblue;
            // border-bottom: 0.05vw solid skyblue;
            line-height: 1.8vw;
            font-size: 1vw;
            // padding: 0.5vw 0;
            font-family: Avenir, Helvetica, Arial, sans-serif;
            outline: 0;
            // outline: 0.05vw solid skyblue;;
            margin: 0;
            padding: 0;
        }
        // border: 0.05vw solid skyblue;
        min-height: 30vw;
        // margin-top: 1vw;
        h3{
            line-height: 3vw;
            font-size: 1.1vw;
            input{
                line-height: 3vw;
                font-size: 1.1vw;
                font-weight:700;
            }
        }
        h4{
            line-height: 2.5vw;
            font-size: 1vw;
            input{
                line-height: 2.5vw;
                font-size: 1vw;
                font-weight:700;
            }
        }
        img{
           max-width: 100%; 
           margin: 1vw auto;
        }
        // .text{
        //     // white-space: ;
        // }
        p{
          display: block;
          // background-color: #044f7a;
          background-color:#282c34 ;
          color:#9fb2bf;
          padding: 0.2vw 1vw;
          margin: 0.5;
          margin: 1vw  0;
            textarea{
              background-color:#282c34 ;
               color:#9fb2bf;
            }
        }
        div{
          // margin:0.5vw 0;
          // background-color: pink;
          padding: 0.2vw 0;
          margin: 0;
         
          
        }
    }
}
</style>