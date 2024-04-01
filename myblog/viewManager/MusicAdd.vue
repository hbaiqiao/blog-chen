<template>
     <Main>
         <div class="musicAdd">
               <div class="musicAdd_hd">
                    <div class="imgbox" @click="uploadImg">
                         <!-- <span>点击上传封面</span> -->
                        <img src="../src/assets/upload.png" alt="" ref="cover">
                        上传封面
                    </div>
                    <div class="infobox">
                        <div class="title">
                            乐名 <input type="text" v-model="title" placeholder="输入音乐名字">
                        </div> 
                        <div class="author">
                            作者  <input type="text" v-model="musician" placeholder="输入作者名字">
                        </div>
                        <!-- <div class="info">

                        </div> -->
                    </div>
                    <span class="button" @click="publish">发布</span>
               </div>
               <div class="musicAdd_bd">
                     <!-- <div class="uploadmusic">上传音乐</div> -->
                     <input type="file" ref="musicfile" @change="selectMusic">
                     <audio src="" controls  ref="audio"></audio>
               </div>
               <div class="musicAdd_fd">
                    <div class="musicword">
                        上传歌词
                        <div class="btn" @click="addword">新增</div>
                    </div>
                    <div class="addword">
                         <ul v-for="(item,index) in word" :key="index">
                             <li>
                                {{ index }}&emsp;
                                <input type="text" v-model="item.cn" placeholder="中文">
                                <input type="text" v-model="item.en" placeholder="外文">
                                <input type="text" v-model="item.time" placeholder="时间" style="width: 10vw;">
                                <div class="btndel" @click="delword(index)">删除</div>
                            </li>
                        </ul>
                    </div>
               </div>
         </div>
    </Main>
</template>

<script>
import Main from "../src/components/Main.vue"
import newfile from "../js/newfile.js"
export default{
    components:{
       Main:Main
    },
    data(){
      return{
        cover:'',
        title:'',
        author:'',
        musician:'',
        duration:'',
        src:'',
        word:[{en:'',cn:'',time:null}],
        imgFile:null,
        musicFile:null,
      }
    },
    methods:{
       uploadImg(){
          let fileSelect = document.createElement('input')
          fileSelect.type = 'file'
          fileSelect.click()
          fileSelect.addEventListener('change',()=>{
               let file = fileSelect.files[0]
               if(file.type.search(/image/)==-1){
                  alert('选择文件非图片类型')
                  return
               }
               console.log(file);
               let fr = new FileReader();
               fr.readAsDataURL(file);
	              	fr.onloadend = ()=>{
	              		// document.getElementById("img").src = fr.result;
                         this.$refs.cover.src = fr.result
	              	}
              //  let date = new Date()
              //  let imgName = '' + date.getFullYear() + date.getMonth() + date.getDay() + date.getHours()+date.getMinutes() +date.getSeconds()+Math.floor(Math.random()*1000)+Math.floor(Math.random()*1000) + file.name.substr(file.name.lastIndexOf("."));
              //  let newFile = new File([file],imgName,{type:file.type});
              //  this.imgFile = newFile
              //  this.cover = 'http://127.0.0.1:6660/api/file/musicImg/'+ imgName
               let newFile = newfile.newfile(file)
               this.imgFile = newFile
              //  this.cover = 'http://127.0.0.1:6660/api/file/musicImg/'+ newFile.name  
               this.cover = this.axios.defaults.baseURL + '/file/musicImg/'+ newFile.name 
               console.log(this.cover);
          })   


       },
       selectMusic(){
           let file = this.$refs.musicfile.files[0]
           if(file.type.search(/audio/)==-1){
                  alert('选择文件非音乐类型')
                  return
           }
           let fr = new FileReader()
           fr.readAsDataURL(file);
	         	fr.onloadend = ()=>{
                 this.$refs.audio.src = fr.result
                 this.$refs.audio.play()
                 setTimeout(()=>{
                    this.duration =  this.$refs.audio.duration
                    this.duration = Math.floor((this.duration)).toFixed(0)
                    console.log(this.duration);
                    this.$refs.audio.pause()
                 },500)
                //  this.duration =  this.$refs.audio.duration
	         	}
            // this.$refs.audio.play()
            // this.duration =  this.$refs.audio.duration

            // this.$refs.audio.pause()
          //  let date = new Date()
          //  let musicName = '' + date.getFullYear() + date.getMonth() + date.getDay() + date.getHours()+date.getMinutes() +date.getSeconds()+Math.floor(Math.random()*1000)+Math.floor(Math.random()*1000) + file.name.substr(file.name.lastIndexOf("."));
          //  let newFile = new File([file],musicName,{type:file.type});
          //  this.musicFile = newFile
          //  this.src = 'http://127.0.0.1:6660/api/file/music/'+ musicName
           let newFile = newfile.newfile(file)
          //  this.src ='http://127.0.0.1:6660/api/file/music/' + newFile.name
          this.src =this.axios.defaults.baseURL + '/file/music/' + newFile.name
           this.musicFile = newFile
           console.log(this.src);
           console.log(this.$refs.audio.duration);
       },
       addword(){
          this.word.push({en:'',cn:''})
       },
       delword(index){
          if(index == 0){
              return
          }
          this.word.splice(index,1)
       },
       publish(){
               if(!this.title||!this.musician||!this.cover||!this.src){
                  alert('参数缺少')
                  return
               }
               if(this.word.length <= 3){
                     this.word = [{en:'',cn:'暂无歌词',time:0},{en:'',cn:'暂无歌词',time:5},{en:'',cn:'暂无歌词',time:10}]
               }
              //上传封面
               const formDataImg = new FormData();
               formDataImg.append('file', this.imgFile)

               this.axios.post('/musicImgFile',
                  formDataImg
               ).then(res=>{
                   console.log(res);
               })

               //上传音乐
               const formDataMusic = new FormData();
               formDataMusic.append('file', this.musicFile)

               this.axios.post('/musicFile',
                  formDataMusic
               ).then(res=>{
                   console.log(res);
               })

               //上传数据
               this.axios.post('/addMusic',{
                  author:this.$store.state.user.name,
                  time:new Date(),
                  title:this.title,
                  cover:this.cover,
                  musician:this.musician,
                  duration:this.duration,                
                  src:this.src,
                  word:JSON.stringify(this.word),
               })
               .then(res=>{
                   console.log(res);
               })
       },
    }
}
</script>

<style lang="scss">
.musicAdd{
   .musicAdd_hd{
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
        // span{
        //     position: absolute;
        //     top: 0;
        //     left: 0;
        //     z-index: 2;
        // }
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
   .musicAdd_bd{
      width: 100%;
      height: 6vw;
    //   background-color: pink;
      box-sizing: border-box;
      display: flex;
      padding: 1.5vw;
      input{
        height: 3vw;
        line-height: 3vw;
        font-size: 1.2vw;
      }
   }
   .musicAdd_fd{
      .musicword{
        display: flex;
        line-height: 2vw;
        margin-bottom: 1vw;
      }
      .btn{
        width: 6vw;
        height: 2vw;
        line-height: 2vw;
        background-color: orchid;
        text-align: center;
        color: #fff;
        border-radius: 0.5vw;
        margin-left: 56vw;
    
      }
      .addword{
        width: 68vw;
        ul{
           list-style: none;
        }
        li{
           width: 68vw;
           height: 2vw;
           line-height: 2vw; 
           font-size: 1.2vw;
           text-indent: 1em;
           display: flex;
           margin-bottom: 0.5vw;
           input{
              width: 22vw;
              height: 2vw;
              line-height: 2vw; 
              font-size: 1.2vw;
              text-indent: 1em;
              outline: 0;
              border: 0.05vw solid palevioletred;
              margin-right: 1vw;
              border-radius: 0.5vw;
           }
           .btndel{
              width: 6vw;
              height: 2vw;
              line-height: 2vw;
            //   background-color: orchid;
              text-align: center;
              color: #000;
              border: 0.05vw solid #666;
              text-indent: 0;
              border-radius: 0.5vw;
            //   margin-left: 50vw;
              z-index: 2;
            }
        }
      }
   }
}
</style>