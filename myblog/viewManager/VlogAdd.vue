<template>
     <Main>
         <div class="vlogAdd">
                <div class="vlogAdd_hd">
                    <div class="imgbox" @click="uploadImg">
                         <!-- <span>点击上传封面</span> -->
                        <img src="../src/assets/upload.png" alt="" ref="cover">
                        
                    </div>
                    <div class="infobox">
                        <div class="title">
                            标题 <input type="text" v-model="title" placeholder="输入标题名字">
                        </div> 
                        <div class="author">
                            描述  <input type="text" v-model="info" placeholder="输入视频描述">
                        </div>
                        <!-- <div class="info">info

                        </div> -->
                    </div>
                    <span class="button" @click="publish">发布</span>
                </div>
                 <h5 @click="uploadVlog">点击上传视频</h5>
                <div class="vlogAdd_bd"  ref="vediobox">
                    
                </div>
         </div>
     </Main>
</template>

<script>
import Main from "../src/components/Main.vue"
import newfile from "../js/newfile.js"
export default{
    components:{
       Main:Main,
    },
    data(){
        return{
           title:'',
           author:'',
           time:null,
           cover:'',
           src:'',
           info:'',
           duration:null,
           imgFile:null,
           vlogFile:null,
        }
    },
    methods:{
        uploadImg(){
          let fileSelect = document.createElement('input')
          fileSelect.type = 'file'
          fileSelect.click()
          fileSelect.addEventListener('change',()=>{
               let file = fileSelect.files[0]
               console.log(file.type);
               if(file.type.search(/image/)==-1){
                  alert('选择文件非图片类型')
                  return
               }
               let fr = new FileReader();
               fr.readAsDataURL(file);
	              	fr.onloadend = ()=>{
	              		// document.getElementById("img").src = fr.result;
                         this.$refs.cover.src = fr.result
	              	}
               let newFile = newfile.newfile(file)
               this.imgFile = newFile
              //    this.cover = 'http://127.0.0.1:6660/api/file/vlogImg/'+ newFile.name
               this.cover = this.axios.defaults.baseURL + '/file/vlogImg/' + newFile.name
               console.log(this.cover);
          })   


       },

       uploadVlog(){
          
          let fileSelect = document.createElement('input')
          let vedio = document.createElement('video')
          vedio.controls = true
          fileSelect.type = 'file'
          fileSelect.click()
          fileSelect.addEventListener('change',()=>{
               let file = fileSelect.files[0]
              
               if(!fileSelect.files[0]&&this.src){
                   this.$refs.vediobox.removeChild(vedio)
                   this.src = ''
                   this.vlogFile = null
                   return
               }
               if(file.type.search(/video/)==-1){
                  alert('选择文件非视频类型')
                  return
               }
               console.log(file);
               let fr = new FileReader();
               fr.readAsDataURL(file);
	              	fr.onloadend = ()=>{
                         vedio.src = fr.result
                         this.$refs.vediobox.appendChild(vedio)
                         vedio.play()
                         setTimeout(()=>{
                            this.duration =  vedio.duration
                            this.duration = Math.floor((this.duration)).toFixed(0)
                            console.log(this.duration);
                            vedio.pause()
                         },500)
	              	}
               let newFile = newfile.newfile(file)
               this.vlogFile = newFile
            //    this.src = 'http://127.0.0.1:6660/api/file/vlog/'+ newFile.name
               this.src = this.axios.defaults.baseURL + '/file/vlog/' + newFile.name
               //    console.log(this.cover);
          }) 
          vedio.addEventListener('click',()=>{
              fileSelect.click()
          })  
       },
       
       publish(){
              if(!this.title||!this.cover||!this.src){
                  alert('参数缺少')
                  return
               }
               
              //上传封面
               const formDataImg = new FormData();
               formDataImg.append('file', this.imgFile)

               this.axios.post('/vlogImgFile',
                  formDataImg
               ).then(res=>{
                   console.log(res);
               })

               //上传音乐
               const formDataMusic = new FormData();
               formDataMusic.append('file', this.vlogFile)

               this.axios.post('/vlogFile',
                  formDataMusic
               ).then(res=>{
                   console.log(res);
               })

               //上传数据
               this.axios.post('/addVlog',{
                  author:this.$store.state.user.name,
                  time:new Date(),
                  title:this.title,
                  cover:this.cover,
                  info:this.info,
                  duration:this.duration,                
                  src:this.src,
                 
               })
               .then(res=>{
                   console.log(res);
               })
       }
    }
}
</script>

<style lang="scss">
.vlogAdd{
    .vlogAdd_hd{
      display: flex;
      .imgbox{
        width: 10vw;
        height: 10vw;
        text-align: center;
        line-height: 10vw;
        box-sizing: border-box;
        border: 2px solid deepskyblue;
        margin-left: 5vw;
        img{
            width: 100%;
            height: 100%;
        }
      }
      .infobox{
        margin-left: 5vw;
        width: 40vw;
        height: 10vw;
        
        .title{
            width: 50vw;
            height: 5vw;
            line-height: 5vw;
            font-size: 1.5vw;
            box-sizing: border-box;
            input{
                width: 30vw;
                line-height: 4vw;
                font-size: 1.5vw;
                outline: 0;
                border: 0;
                border-bottom: 0.03vw solid deepskyblue;
                text-indent: 1em;
            }
        }
        .author{
            margin-top: 0.1vw;
             width: 50vw;
             height: 5vw;
             line-height: 5vw;
             font-size: 1.5vw;
              box-sizing: border-box;
              input{
                width: 30vw;
                line-height: 4vw;
                font-size: 1.5vw;
                outline: 0;
                border: 0;
                border-bottom: 0.03vw solid deepskyblue;
                text-indent: 1em;
              }
        }
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
   h5{
    text-align: center;
    line-height: 3vw;
    font-size: 1.2vw;
    width: 10vw;
    background-color: orange;
    color: #fff;
    border-radius: 1vw;
    margin: 1vw auto;
   }
   .vlogAdd_bd{
      width: 60vw;
      min-height: 28vw;
      border: 0.05vw solid orangered;  
      margin: 0 auto;
      vedio{
         width: 100%;
         height:100%;
         margin: 0 auto;
      }
   }
}
</style>