<template>
    <Main>
        <div class="photoAdd">
            <div class="photoAdd_hd">
                <h5 @click="uploadImg">点击上传图片</h5>
                <span class="button" @click="publish">发布</span>
            </div>
             
             <div class="photobox" >
                     <img :src="src" alt="" ref="photo">
             </div>
        </div>
    </Main>
    
</template>

<script>
import Main from "../src/components/Main.vue"
import newfile from "../js/newfile"
export default{
    components:{
        Main:Main,
    },
    data(){
       return{
        imgFile:null,
        src:null,
        srcWidth:null,
        srcHeight:null,
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
                         this.$refs.photo.src = fr.result
	              	}
               let newFile = newfile.newfile(file)
               this.imgFile = newFile
               this.srcWidth = this.axios.defaults.baseURL + '/file/photoWidth/' + newFile.name
               this.srcHeight = this.axios.defaults.baseURL + '/file/photoHeight/' + newFile.name
             
          })   


       },
       publish(){
        //   console.log(this.$refs.photo.offsetWidth);
        //   console.log(this.$refs.photo.offsetHeight);
          let width = this.$refs.photo.offsetWidth
          let height = this.$refs.photo.offsetHeight
          console.log(this.$store.state.user.name);
          //图片分类
          if(width>=height){
              //    console.log('电脑壁纸');
              //上传封面
               const formDataImg = new FormData();
               formDataImg.append('file', this.imgFile)

               this.axios.post('/photoWidthFile',
                  formDataImg
               ).then(res=>{
                   console.log(res);
               })

               this.axios.post('/addPhotoWidth',{
                   author:this.$store.state.user.name,
                   time:new Date(),
                   src:this.srcWidth,
               })
               .then(res=>{
                   console.log(res);
               })
          }
          else{
             // console.log('手机壁纸');
               const formDataImg = new FormData();
               formDataImg.append('file', this.imgFile)

               this.axios.post('/photoHeightFile',
                  formDataImg
               ).then(res=>{
                   console.log(res);
               })


               this.axios.post('/addPhotoHeight',{
                   author:this.$store.state.user.name,
                   time:new Date(),
                   src:this.srcHeight,
               })
               .then(res=>{
                   console.log(res);
               })
          }
       }
       
    }
}
</script>

<style lang="scss">

.photoAdd{
   .photoAdd_hd{
    display: flex;
    justify-content: space-between;
    // background-color: pink;
    h5{ 
        text-align: center;
        line-height: 3vw;
        font-size: 1.2vw;
        width: 10vw;
        background-color: orange;
        color: #fff;
        border-radius: 1vw;
        margin: 1vw 0;
        margin-left: 25vw;
    }
    .button{
          width: 6vw;
          height: 3vw;
          font-size: 1.2vw;
          text-align: center;
          line-height: 3vw;
          border-radius: 1vw;
          margin: 1vw 0;
          background-color: rgb(231, 102, 16);
          color: #fff;
          margin-right: 4vw;
        }
    }

    .photobox{
         width: 64vw;
         min-height: 37vw;
         border: 0.05vw solid orangered;  
         margin: 0 auto;
         position: relative;
         img{
            max-width: 64vw;
            max-height:37vw;
            // margin: 0 auto;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
         }
    }
}
</style>