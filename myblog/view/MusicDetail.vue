<template>
    <Main>
        <template v-slot:manager>
          <div class="musicList">
               <h3>更多音乐</h3>
               <div class="item" v-for="item in musicList" :key="item.id" @click="toMusic(item.id)">
                    <div class="item_left">
                        <img :src="item.cover" alt="">
                    </div>
                    <div class="item_right">
                        <div class="item_hd">{{ item.title }}</div>
                        <div class="item_bd">{{ item.musician }}</div>
                    </div>
                   
               </div>
          </div>
              
      </template>
        <div class="muicDetail">

            <div class="right item">
                  <audio :src="audioSrc"  ref="audio" @playing="getmusicTime"></audio>
                  <!-- 歌词区域 -->
                  <div class="music_area">
                      <div class="music_title">
                          <!-- <h2 v-if="musictitle.cn&&!musictitle.en">{{ musictitle.cn }}</h2>
                          <h3 v-if="musictitle.en">{{ musictitle }}</h3>
                          <h4 v-if="musictitle.cn">{{ musictitle }}</h4> -->
                          <h3>{{ musictitle }}</h3>
                          <h4>{{ musician }}</h4>
                      </div>
                      <div class="music_word" ref="musicWord">
                           
                           <ul v-for="(item,index) in musicdata" :key="item.en+item.cn" ref="ul">
                               <li v-show="item.cn&&!item.en" @click="changeMusicTime(item.time,index)">
                                   <h4>{{ item.cn }}</h4>
                               </li>
                               <li v-show="item.cn&&item.en" @click="changeMusicTime(item.time,index)"> 
                                   <h5>{{ item.en }}</h5>
                                   <h5>{{ item.cn }}</h5>
                               </li>
                           </ul>
                           <li  ref="li"></li>
                      </div>
                  </div>
                  <!-- 音乐控件 -->
                  <div class="music_control">
                      <div class="music_left">
                           <img :src="audioImg" alt="">
                      </div>
                      <div class="music_right">
                          <div class="music_btn">
                               <div class="btn" @click="changeMusicData(-1)" >
                                   <img src="../src/assets/icon/pre.png" alt="">
                               </div>
                               <div @click="playMusic" class="btn">
                                  <img src="../src/assets/icon/play.png" alt="" v-if="!playtag">
                                  <img src="../src/assets/icon/pause.png" alt="" v-if="playtag">
                                </div>
                                <div class="btn" @click="changeMusicData(1)">
                                   <img src="../src/assets/icon/next.png" alt="">
                               </div>
                          </div>
                            <div class="music_time">
                                 {{ shownowTime }}
                                 <div class="line_box">
                                      <div class="time_line" ref="timeLine"> </div>
                                  </div>
                                  {{ showendTime }}
                           </div>
                      </div>
                      
                      
                  </div>
                 
             </div>

        </div>

    </Main>
</template>

<script>
import Main from '../src/components/Main.vue'
// import musicdata from "../src/assets/musicdata.json"
export default{
    components:{
        Main:Main,

    },
    data(){
       return{
           musicList:[],
        //    musicSrc:[
        //                require("../src/assets/travelinglight.mp3"),
        //                require("../src/assets/Whatever It Takes.mp3"),
        //                require("../src/assets/再飞行.mp3")
        //            ],
           audioSrc:"",
           playtag:false,
           nowtime:0,
           endtime:0,
           shownowTime:'00:00',
           showendTime:'00:00',
           musictimer:null,
           duration:null,
           musictitle:'',
           musician:'',
           musicdata:'',
           musicIndex:0,
        //    musicImg:[
        //          require("../src/assets/music0.jpg"),
        //          require("../src/assets/music1.jpg"),
        //          require("../src/assets/music2.jpg"),
        //    ],
           audioImg:'',
           wordIndex:1,
           breaktag:false,
           gotoTimer:null,
   
           clickAble:true,
           musicLoading:false,
           ulLi:null,        
       }
    },
  mounted(){
    //  console.log(musicdata.data);
     this.getMusicData()
     this.getdataPage(this.$route.query.size,this.$route.query.page)
     
  },
  beforeUnmount(){
        // clearInterval(this.musictimer)
        if(this.playtag){
            this.playMusic()
        }
  },
//   beforeRouteLeave(to, from, next) {
//        if(this.playtag){
//            this.$refs.audio.parse()
//        }
//     },
  unmounted(){
    // this.$refs.audio.pause() 
  },
  watch:{
     nowtime(){
        let min = Math.floor((this.nowtime / 60)).toFixed(0)
        let sec = Math.floor((this.nowtime % 60)).toFixed(0)
        min = (min<10? '0':'') + min
        sec = (sec<10? '0':'') + sec
        this.shownowTime = min +':'+sec
     },
     endtime(){
        
        let min = Math.floor((this.endtime / 60)).toFixed(0)
        let sec = Math.floor((this.endtime % 60)).toFixed(0)
        min = (min<10? '0':'') + min
        sec = (sec<10? '0':'') + sec
        this.showendTime = min +':'+sec
     }
  },
  methods:{
    toMusic(id){
            this.$router.replace({path:'/musicDetail', query:{id:id,size:this.$route.query.size,page:this.$route.query.page} })
            this.getMusicData()
     },
    getdataPage(size,index){
            this.axios.get("/musicPage/" + size +'/'+ index)
           .then(res=>{
               console.log(res);
               this.totalPages = res.data.totalPages
               this.pageshow = true
               this.musicList = res.data.content
           })
    },
    getMusicData(){
        
        // this.musictitle = musicdata.data[0].title.en + musicdata.data[0].title.cn;
        // this.musician = musicdata.data[0].musician
        // this.musicdata = musicdata.data[0].word;   
        // this.endtime = musicdata.data[0].duration
        // this.duration = musicdata.data[0].duration
        // this.duration = this.$refs.audio.duration;

        // this.audioSrc = this.musicSrc[0]
        // this.audioImg = this.musicImg[0]
        // console.log(this.audioSrc);

       this.axios.get(`/music/${this.$route.query.id}`)
       .then(res=>{
           this.musictitle = res.data.title
           this.musician = res.data.musician
           this.musicdata =JSON.parse(res.data.word)
           console.log(this.musicdata);
           this.endtime = res.data.duration
           this.duration = res.data.duration
           
           this.audioSrc = res.data.src
           this.audioImg = res.data.cover
           this.wordIndex =1
           
           setTimeout(()=>{
               this.playMusic()
           },5000)
           
           
       })

    },
    async changeMusicData(index){
        this.wordIndex = 1;
        let length = musicdata.data.length
        // console.log(length);
        // console.log(index+0);
        if(index>0){ this.musicIndex += index }
        if(index<0){ this.musicIndex -= (-index) }
        // this.musicIndex += index
        // console.log(this.musicIndex);
        if(this.musicIndex<0){
            this.musicIndex = length-1
        }
        if(this.musicIndex>=length){
            this.musicIndex = 0
        }
        this.wordIndex = 1;
        console.log(this.musicIndex);
        if(this.playtag){
            this.playMusic()
            // await this.loadingChangeTime()
        }
        this.wordIndex = 1
        // this.musictitle = musicdata.data[this.musicIndex].title.en + musicdata.data[0].title.cn;
        // this.musician = musicdata.data[this.musicIndex].musician
        // this.musicdata = musicdata.data[this.musicIndex].word;
        // this.duration = musicdata.data[this.musicIndex].duration
        // console.log(this.duration);
        // this.endtime = musicdata.data[this.musicIndex].duration
        // this.audioSrc = this.musicSrc[this.musicIndex]
        // this.audioImg = this.musicImg[this.musicIndex]
        
        this.musictitle = this.musicList[this.musicIndex].title
        this.musician = this.musicList[this.musicIndex].musician
        this.musicdata = JSON.parse(this.musicList[this.musicIndex].word);
        this.duration = this.musicList[this.musicIndex].duration
        console.log(this.duration);
        this.endtime = this.musicList[this.musicIndex].duration
        this.audioSrc = this.musicList[this.musicIndex].src
        this.audioImg = this.musicList[this.musicIndex].cover
        this.wordIndex = 1
        // this.duration = this.$refs.audio.duration;
        await this.loadingChangeTime()
        setTimeout(()=>{
            this.ulLi = this.$refs.ul.reverse()
            this.playMusic()
        },2000)
        // this.playMusic()
        // if(!this.playtag){
        //     this.playMusic()
        // }else{
        //      //先暂停 // 
        //     this.playMusic()
        //     await this.loadingChangeTime()
        //     //再开启
        //     this.playMusic()
        // }

    },
    gettime(){
       let date = new Date()
       console.log(date);
       this.year = date.getFullYear() 
       this.hours= date.getHours()
       this.minutes= date.getMinutes()
       this.seconds = date.getSeconds()
       this.mouth = date.getMonth()+1
       this.day = date.getDate() 
       let weekday=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
       this.datetype = weekday[Number(date.getDay())]
       this.hours = (this.hours<10? '0' :'' ) +this.hours
       this.minutes = (this.minutes<10? '0' :'' ) +this.minutes
    //    this.seconds = (this.seconds<10? '0' :'' ) +this.seconds
       let leftSeconds = 60 - date.getSeconds()
       let trueTime = new Promise((resolve)=>{
          let secondsTimer = setInterval(()=>{
               leftSeconds--
               if(leftSeconds==0){
                   clearInterval(secondsTimer)
                   this.minutes++
                   resolve()
               }
          },1000)
       })
       trueTime.then(()=>{
           let timer=null
           clearInterval(timer)
           timer = setInterval(()=>{
               this.minutes++
               if(this.minutes == 60){
                 this.hours++
                 this.minutes = 0
               } 
               this.minutes = (this.minutes<10? '0' :'' ) +this.minutes
               if(this.hours==24){
                 this.day++
                 this.hours =0
                 this.datetype = weekday[Number(new Date().getDay())]
               } 
               this.hours = (this.hours<10? '0' :'' ) +this.hours
               
           },1000*60)
       })
       
       
    },
    playMusic(){
        this.duration = this.$refs.audio.duration;
        this.endtime = this.duration.toFixed(2)
        console.log(this.duration);
        this.endtime = this.duration.toFixed(2)
        let current
        if(!this.playtag){
            this.$refs.audio.play()
            this.playtag=!this.playtag 
            this.musictimer = setInterval(()=>{
                //  let current = this.$refs.audio.currentTime
                 current = this.$refs.audio.currentTime
                 this.nowtime = current.toFixed(2)
                 let nowLine  = (current/this.duration)*100
                 this.$refs.timeLine.style.width =  nowLine +'%'
           },1000)  
           
          this.musicWordPlay()
         
        }else{
            this.playtag=!this.playtag
            // console.log(this.musictimer);
             console.log('@@@'+this.$refs.audio.currentTime.toFixed(2));
            //  this.$refs.audio.currentTime = 150
            clearInterval(this.musictimer)
            clearTimeout(this.gotoTimer)  //清除定时器
            this.$refs.audio.pause()   
       }   
    },
    getmusicTime(){
    //    console.log('@@@');
    //    console.log(this.$refs.audio.currentTime);
    },
    async changeMusicTime(time,index){
        this.wordIndex = index+1
        this.$refs.audio.currentTime = time
        console.log("当前"+this.wordIndex);
        if(!this.playtag){
            
            this.playMusic()
        }
        else{
           
            //先暂停 // 
            this.playMusic()
            await this.loadingChangeTime()
            this.wordIndex = index+1
            this.$refs.audio.currentTime = time
            //再开启
            this.playMusic()
        }
       
    },
    //等待循环截止函数
    async loadingChangeTime(){
         return new Promise((resolve,reject)=>{
              if(this.clickAble){
                  resolve()
              }else{
                   let loadtimer = setInterval(()=>{
                      if(this.clickAble){
                          clearInterval(loadtimer)
                          resolve()
                      }
                 },1000)
              }      
         })
    },
    async gotime(time){
        this.gotoTimer = null
        return new Promise((resolve,reject)=>{
            if(!this.playtag){
                clearTimeout(timer)
                reject('循环中止')
            }
             this.gotoTimer= setTimeout(()=>{  
                  resolve()
             },time)
             if(!this.gotoTimer){
                reject('本次循环成功结束')
             }
        })
    },
    async musicWordPlay(){
        // await this.loadingChangeTime()  //等待再次开启循环
        if(!this.clickAble){return}
        let musicWord = this.$refs.musicWord    //设置滚动
        let ul = this.$refs.ul
        if(this.ulLi){
            ul = this.ulLi
        }       //设置孩子组件高亮
        console.log(ul);
        
        let musicdata =this.musicdata   //获取歌词时间
        console.log(this.musicdata);
        // console.log(this.$refs.li);
        // console.log(this.$refs.li.offsetHeight);  
        let liHeight = this.$refs.li.offsetHeight
        console.log("音乐播放重新开始了");
        for(let i = this.wordIndex; i<this.musicdata.length;i++){
             console.log("i值"+ i);
             try {
                ul[i-1].className = 'playnow'
                // this.clickAble = false //禁止再次开启循环
                await this.gotime((musicdata[i].time-musicdata[i-1].time)*1000)
               
                // musicWord.scrollTop = liHeight*(i-2)
                // ul[i-1].className = ''
             } catch(e) {
                this.wordIndex = i-1;
                ul[i-1].className = ''
                console.log(e);
                this.clickAble = true  //可以点击
                break;
             }
             this.clickAble = true  //可以再次开启循环
             this.wordIndex = i;
            //  console.log("this.breaktag:"+this.breaktag +"---------play:"+ this.playtag);
            
             if(!this.playtag) break;  
            //  if(i==this.musicdata.length-1) this.wordIndex=1   
             musicWord.scrollTop = liHeight*(i-2)
             ul[i-1].className = ''
             console.log('time值'+(musicdata[i].time-musicdata[i-1].time));
        }
    },
  },
}
</script>

<style lang="scss">
.item{
     flex: 3;
     height: 43vw;
     background:rgba(255,255,255,0.4);
     margin: 0.5vw;
     border-radius: 0.5vw;
     box-sizing: border-box;
  }
  .right{

background:rgba(255,255,255,0.5);
// //  margin: 10px;
//  border-radius: 10px;
position: relative;
.music_area{
   width: 100%;
   height: 36vw;
   // background-color: #fff;
  
   .music_title{
       // background-color:skyblue;
       height: 5vw;
       color: deepskyblue;
       h2{
           text-align: center;
           line-height: 5vw;
       }
       h3{
           text-align: center;
           line-height: 2.5vw;
           font-size: 1.2vw;
       }
       h4{
           text-align: center;
           line-height: 2.5vw;
           font-size: 1.2vw;
       }
   }
   .music_word{
       width: 100%;
       height: 32vw;
       overflow-y: scroll;
       white-space: wrap;
       list-style: none;
       color: #666;
       ul{
           list-style: none;
       }
       li{
           width: 100%;
           height: 5vw;
           text-align: center;
           line-height: 5vw;
           // margin-bottom: 2vh;
           padding: 0.1vw 0;
           box-sizing: border-box;
           font-size: 1.2vw;
           
           h4{
              text-align: center;
              width: 100%;
              line-height: 5vw;
              font-size: 1.1vw;
           }
           h5{
               text-align: center;
               width: 100%;
               line-height: 2.5vw;
               font-size: 1vw;
           }
       }
       li:hover{
           color: deepskyblue;
       }
       .playnow{
        //    color: deepskyblue;
            color: #000;
            h4{
                font-size: 1.3vw;
            }
            h5{
                font-size: 1.2vw;
            }
           // color: #000 !important;
       }
       &::-webkit-scrollbar {
           width: 0.3vw;
           height: 0.3vw;
       }
        
       &::-webkit-scrollbar-thumb {
           border-radius: 0.2vw;
           -webkit-box-shadow: inset 0 0 0.3vw rgba(0,0,0,0.2);
           // background-color:rgba(255,255,255,1);
           background-color: #99a9bf;
       }
        
       &::-webkit-scrollbar-track {
           -webkit-box-shadow: inset 0 0 0.3vw rgba(0,0,0,0.2);
           border-radius: 0.2vw;
           // background-color: rgba(255,255,255,0.4);
           background-color: #d3dce6;
       }


       
   }
}
.music_control{
   position: absolute;
   bottom: 0;
   left: 0;
   width: 100%;
   height: 6vw;
   // background-color: #fff;
   display: flex;
   padding: 0.5vw;
   box-sizing: border-box;
   .music_left{
      width: 20%;
      text-align: center;
      img{
         width: 5vw;
         height: 5vw;
         border-radius: 2.5vw;
      }
   }
   .music_right{
       width: 65%;
       display: flex;
       flex-direction: column;
       justify-content: center;
       align-items: center;
      .music_btn{
           //  width: 5vw;
           //  height: 3vw;
            display: flex;
            justify-content: space-around;
            align-items: center;
            margin-top: 0.6vw;
           .btn{
                 width: 5vw;
                 img{
                     width: 2.5vw;
                     height: 2.5vw;
                 }
            }
      }
      .music_time{
            width: 100%;
            height: 2.5vw;
            line-height: 2.5vw;
            font-size: 0.3vw;
           //  padding: 0 1vw;
           //  background-color: #fff;
            display: flex;
            justify-content: space-around;
            .line_box{
               position: relative;
               width: 76%;
               margin-top: 1vw;    
               height: 0.4vw;
               box-sizing: border-box;
               border: 0.25vw solid rgba(255, 255, 255,0.8);
               border-radius: 0.25vw;

            }
            .time_line{
               position: absolute;
               top: 0;
               left: 0;
               width: 0%;
               margin: -0.2vw;
               height: 0.4vw;
               box-sizing: border-box;
               border: 0.26vw solid deepskyblue;
               border-radius: 0.25vw;
            }
      }
   }
}

}
.musicList{
    h3{
        line-height: 3vw;
        background-color: skyblue;
        color: #fff;
        font-size: 1.2vw;
        line-height: 3vw;
    }
    .item{
       width: 17.5vw;
       height: 6vw;
       margin: 0.5vw 0;
      
       box-sizing: border-box;
    //    background-color: pink;
       border-bottom: 0.1vw solid #eee;
       display: flex;
       .item_left{
          width: 6vw;
          height: 6vw;    
          img{
            width: 4vw;
            height: 4vw;
            margin: 1vw;
          }  
        }
        .item_right{
            width: 12vw;
            margin: 1vw 0;
            line-height: 2vw;
            font-size: 1vw;
            text-align: left;
            .item_hd{
               width: 11vw;
               height: 2vw;
               overflow: hidden;
            }
            .item_bd{
                width: 11vw;
               height: 2vw;
               overflow: hidden;
            }
        }
    }
    .item:hover{
          color: skyblue;
    }
}


@media screen and (max-width: 900px){
    .item{
     flex: 3;
    //  height: 43vw;
    //  background:rgba(255,255,255,0.4);
     margin: 0.5vw;
     border-radius: 0.5vw;
     box-sizing: border-box;
  }
.right{

background:rgba(255,255,255,0.5);
// //  margin: 10px;
//  border-radius: 10px;
// position: relative;
height: 100vh;
.music_area{
   width: 100vw;
   height: 76vh;
   // background-color: #fff;
  
   .music_title{
       // background-color:skyblue;
       height: 16vh;
       color: deepskyblue;
       h2{
           text-align: center;
           line-height: 8vh;
           font-size: 6vw;
       }
       h3{
           text-align: center;
           line-height: 8vh;
           font-size: 5vw;
       }
       h4{
           text-align: center;
           line-height: 8vh;
           font-size: 4vw;
       }
   }
   .music_word{
       width: 100%;
       height: 60vh;
       overflow-y: scroll;
       white-space: wrap;
       list-style: none;
       color: #666;
       ul{
           list-style: none;
       }
       li{
           width: 100%;
           height: 10vh;
           text-align: center;
           line-height: 5h;
           // margin-bottom: 2vh;
           padding: 1vw 0;
           box-sizing: border-box;
           font-size: 3vw;
           
           h4{
              text-align: center;
              width: 100%;
              height: 5vh;
              line-height: 5vh;
              font-size: 3vw;
              overflow: hidden;
           }
           h5{
               text-align: center;
               width: 100%;
               line-height: 5vh;
               height: 5vh;
               font-size: 3vw;
               overflow: hidden;
           }
       }
       li:hover{
           color: deepskyblue;
       }
       .playnow{
        //    color: deepskyblue;
            color: #000;
            h4{
                font-size: 4vw;
                overflow: hidden;
            }
            h5{
                font-size: 4vw;
                overflow: hidden;
            }
           // color: #000 !important;
       }
       &::-webkit-scrollbar {
           width: 0.3vw;
           height: 0.3vw;
       }
        
       &::-webkit-scrollbar-thumb {
           border-radius: 0.2vw;
           -webkit-box-shadow: inset 0 0 0.3vw rgba(0,0,0,0.2);
           // background-color:rgba(255,255,255,1);
           background-color: #99a9bf;
       }
        
       &::-webkit-scrollbar-track {
           -webkit-box-shadow: inset 0 0 0.3vw rgba(0,0,0,0.2);
           border-radius: 0.2vw;
           // background-color: rgba(255,255,255,0.4);
           background-color: #d3dce6;
       }


       
   }
}
.music_control{

   width: 100vw;
   height: 20vh;
   // background-color: #fff;
   display: flex;
   padding: 1vw;
   box-sizing: border-box;
   .music_left{
      width: 20vw;
      text-align: center;
      img{
         width: 16vw;
         height: 16vw;
         border-radius: 2.5vw;
         margin-top: 5vh;
      }
   }
   .music_right{
       width: 75%;
       display: flex;
       flex-direction: column;
       justify-content: center;
       align-items: center;
      .music_btn{
           //  width: 5vw;
           //  height: 3vw;
            width: 100%;
            display: flex;
            justify-content: space-around;
            align-items: center;
            margin-top: 2vw;
           .btn{
                 width: 5vw;
                 img{
                     width: 10vw;
                     height: 10vw;
                 }
            }
      }
      .music_time{
            width: 100%;
            height: 10vw;
            line-height: 10vw;
            font-size: 3vw;
           //  padding: 0 1vw;
           //  background-color: #fff;
            display: flex;
            justify-content: space-around;
            .line_box{
               position: relative;
               width: 76%;
               margin-top: 1vw;    
               height: 2vw;
               box-sizing: border-box;
               border: 1vw solid rgba(255, 255, 255,0.8);
               border-radius: 1vw;

            }
            .time_line{
               position: absolute;
               top: 0;
               left: 0;
               width: 0%;
               margin: -0.2vw;
               height: 1vw;
               box-sizing: border-box;
               border: 1vw solid deepskyblue;
               border-radius: 1vw;
            }
      }
   }
}

}
}
</style>