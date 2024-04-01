<template>
    <div id="index">
       
        <div class="header">
            <div class="hd_left">
                 <img src="../src/assets/logo.jpg" alt="">
                 &ensp;
                 辰的博客
            </div>
            <div class="hd_right">
                 <ul>
                    <li>动态</li>
                    <li>文章</li>
                    <li>项目</li>
                    <li>音乐</li>
                    <li>Vlog</li>
                    <li>资源</li>
                 </ul>
            </div>
        </div>
        <Carousel/> 
         <div class="main">
             <div class="left item">
                <div class="logo_box">
                    <img src="../src/assets/logo2.jpg" alt="" class="logo">
                     <div class="pulse1"></div>
                     <div class="pulse2"></div>
                     <div class="pulse3"></div>
                </div>
                <h2>辰</h2>
                <div class="left_down">
                    <h3>前端开发、Vue、React、JavaScript</h3>
                    <h4>Java、SpringBoot、Mysql</h4>
                    <h5>人工智能、Python、C++</h5>
                </div>
             </div>
             <div class="center item">
                <div class="datebox">
                    {{ year }}年 {{ mouth }}月 {{day}}号 {{datetype}} <br>
                </div>
                 <div class="timebox">
                    <div class="time_item">{{ hours }}</div>
                    <div class="time_item">{{ minutes }}</div>
                    <!-- <div class="time_item">{{ seconds }}</div> -->
                    <!-- 当前时间:{{hours}}:{{minutes}}:{{seconds}} -->
                 </div>
                  
             </div>
             <div class="right item">
                  <audio :src="audioSrc"  ref="audio" @playing="getmusicTime" loop></audio>
                  <!-- 歌词区域 -->
                  <div class="music_area">
                      <div class="music_title">
                          <h2 v-if="musictitle.cn&&!musictitle.en">{{ musictitle.cn }}</h2>
                          <h3 v-if="musictitle.en">{{ musictitle.en }}</h3>
                          <h4 v-if="musictitle.cn">{{ musictitle.cn }}</h4>
                      </div>
                      <div class="music_word" ref="musicWord">
                           
                           <ul v-for="(item,index) in musicdata" :key="item.time" ref="ul">
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
                           <img src="../src/assets/music1.jpg" alt="">
                      </div>
                      <div class="music_right">
                          <div class="music_btn">
                               <div class="btn">
                                   <img src="../src/assets/icon/pre.png" alt="">
                               </div>
                               <div @click="playMusic" class="btn">
                                  <img src="../src/assets/icon/play.png" alt="" v-if="!playtag">
                                  <img src="../src/assets/icon/pause.png" alt="" v-if="playtag">
                                </div>
                                <div class="btn">
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
         
        
         
    </div>
   
</template>

<script>
import Carousel from "./Carousel.vue";
import musicdata from "../src/assets/musicdata.json";
export default{
  components:{
    Carousel,
  },
  data(){
     return{
        year:'',
        mouth:'',
        day:'',
        datetype:'',
        hours:'',
        minutes:'',
        seconds:'',
        audioSrc:require("../src/assets/travelinglight.mp3"),
        playtag:false,
        nowtime:0,
        endtime:0,
        shownowTime:'00:00',
        showendTime:'00:00',
        musictimer:null,
        duration:null,
        musictitle:'',
        musicdata:'',
        wordIndex:1,
     }
  },
  created(){
    this.gettime()
  },
  mounted(){
     console.log(musicdata.data);
     this.getMusicData()
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
        
        let min = (this.endtime / 60).toFixed(0)
        let sec = (this.endtime % 60).toFixed(0)
        min = (min<10? '0':'') + min
        sec = (sec<10? '0':'') + sec
        this.showendTime = min +':'+sec
     }
  },
  methods:{
    getMusicData(){
        this.musictitle = musicdata.data.title;
        this.musicdata = musicdata.data.word;
        
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
        if(!this.playtag){
            this.$refs.audio.play()
            this.playtag=!this.playtag 
            this.musictimer = setInterval(()=>{
                 let current = this.$refs.audio.currentTime
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
            this.$refs.audio.pause()   
       }   
    },
    getmusicTime(){
    //    console.log('@@@');
    //    console.log(this.$refs.audio.currentTime);
    },
    changeMusicTime(time,index){
        this.wordIndex = index+1
        this.$refs.audio.currentTime = time
        console.log("当前"+this.wordIndex);
        if(!this.playtag){
            
            this.playMusic()
        }
        else{
            //先暂停再开启
            this.playMusic()
            this.wordIndex = index+1
            this.$refs.audio.currentTime = time
            setTimeout(()=>{
                this.playMusic()
            },1000)
        }
       
    },
    async gotime(time){
        let timer = null
        if(!this.playtag){
            return new Promise((resolve,reject) => {
                clearTimeout(timer)
                reject()
            })
        }
        return new Promise((resolve)=>{
             timer= setTimeout(()=>{   
                  resolve()
             },time)
        })
    },
    async musicWordPlay(){
        let musicWord = this.$refs.musicWord    //设置滚动
        let ul = this.$refs.ul         //设置孩子组件高亮
        let musicdata =this.musicdata
        // console.log(this.$refs.li);
        // console.log(this.$refs.li.offsetHeight);  
        let liHeight = this.$refs.li.offsetHeight
        
        for(let i = this.wordIndex; i<this.musicdata.length;i++){
            console.log("i值"+ i);
             try {
                ul[i-1].className = 'playnow'
                await this.gotime((musicdata[i].time-musicdata[i-1].time)*1000)
                // musicWord.scrollTop = liHeight*(i-2)
                // ul[i-1].className = ''
             } catch {
                this.wordIndex = i+2;
             }
             if(!this.playtag) break;  
             musicWord.scrollTop = liHeight*(i-2)
             ul[i-1].className = ''
             console.log('----------'+(musicdata[i].time-musicdata[i-1].time));
        }
    },
  },
}
</script>

<style lang="scss">

#index{
   width: 100vw;
   height: 100vh;
   padding: 0 ;
   margin: 0;
   min-width: 1080px;
   min-height: 300px;
}
.header{
    width: 100vw;
    height: 5vh;
    line-height: 5vh;
    font-size: 15px;
    color: #fff;
    display: flex;
	position: sticky;/*兼容*/
	top: 0;
	left: 0;
    // background-color: rgba(255,255,255,0.8);
    // box-shadow: 1px 1px 1px rgba(0,0,0,0.1);
   .hd_left{
      flex: 3;
      text-align: center;
      line-height: 5vh;
      img{
        width: 2.8vh;
        height: 2.8vh;
        opacity: 0.8;
        vertical-align: middle;
      }
     

   }
   .hd_right{
    flex: 6;
    
   }
   ul{
      width: 100%;
      list-style: none;
      li{
        display: inline-block;
        padding: 0 2vw;
              
      }
      li:hover{
        // color: rgb(0, 132, 255);
        color: deepskyblue;
        // text-decoration: underline;
      }
   }
}
.header:hover{
    background-color: rgba(0,0,0,0.6);
}
.main{
    position:absolute;
    top:12vh;
    left: 5vw;
    width: 90vw;
    height: 80vh;
    background-color:rgba(255,255,255, 0.3);
    margin: auto;
    border-radius: 1vw;
    display: flex;
}
.item{
     flex: 3;
     height: 77vh;
     background:rgba(255,255,255,0.4);
     margin: 10px;
     border-radius: 10px;
     box-sizing: border-box;
  }
.left{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top:10vh ;
     img{
        width: 12vw;
        height: 12vw;
        border-radius: 6vw;
        margin: auto;
     }
     h2{
        line-height: 10vh;
    }
     .left_down{
        text-align: center;
        // line-height: 3vh;
        width: 100%;
        height: 38vh;
        h2{
            line-height: 10vh;
        }
        h3{
            line-height: 5vh;
        }
        h4{
            line-height: 4vh;
        }
        h5{
            line-height: 5vh;
        }
     }

     //波纹
     .logo_box{
        position: relative;
     }
     .logo_box div[class^="pulse"]{
            /* 保证我们小波纹在父盒子水平垂直居中，放大之后就会中心向四周发散 */
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
            width: 12vw;
            height: 12vw;
            box-shadow: 0 0 12px #6fcae8;
            border-radius: 50%;
            animation: pulse 3s linear infinite;
        }
        .logo_box div.pulse2{
            animation-delay: 1s;
        }
        .logo_box div.pulse3{
            animation-delay: 2s;
        }

        @keyframes pulse{
            0%{}
            70%{
                width: 15vw;
                height: 15vw;
                opacity: 1;
            }
            100%{
               width: 17vw;
               height: 17vw;
               opacity: 0; 
            }
        }
  }
  .center{
    display: flex;
    flex-direction: column;
    padding-top: 5vw;
    .datebox{
        width: 100%;
        text-align: center;
        height: 10vw;
        line-height: 10vw;
        font-size: 2vw;
    }
    .timebox{
        width: 100%;
        height: 20vw;
        display: flex;
        justify-content: center;
        margin:0 auto;
        .time_item{
          margin-right: 2vh;
          width: 25%;
          height: 6vw;
          font-size: 3vw;
          background-color:rgba(0,0,0,0.7);
          color: white;
          text-align: center;
          line-height: 6vw;
          border-radius: 1vw;
        }
        .time_item :last-child{
            margin-right: 0;
        }
    }
  }
  .right{
     flex: 3;
     
     background:rgba(255,255,255,0.5);
     margin: 10px;
     border-radius: 10px;
     position: relative;
     .music_area{
        width: 100%;
        height: 65vh;
        // background-color: #fff;
       
        .music_title{
            // background-color:skyblue;
            height: 10vh;
            color: deepskyblue;
            h2{
                text-align: center;
                line-height: 10vh;
            }
            h3{
                text-align: center;
                line-height: 5vh;
            }
            h4{
                text-align: center;
                line-height: 5vh;
            }
        }
        .music_word{
            width: 100%;
            height: 54vh;
            overflow-y: scroll;
            white-space: wrap;
            list-style: none;
            color: #666;
            ul{
                list-style: none;
            }
            li{
                width: 100%;
                height: 8vh;
                text-align: center;
                line-height: 8vh;
                // margin-bottom: 2vh;
                padding: 1vh;
                box-sizing: border-box;
                font-size: 1vw;
                
                h4{
                   text-align: center;
                   width: 100%;
                   line-height: 8vh;
                   font-size: 0.9vw;
                }
                h5{
                    text-align: center;
                    width: 100%;
                    line-height: 4vh;
                }
            }
            li:hover{
                color: deepskyblue;
            }
            .playnow{
                // color: deepskyblue;
                color: #000 !important;
            }
            &::-webkit-scrollbar {
                width: 4px;
                height: 4px;
            }
             
            &::-webkit-scrollbar-thumb {
                border-radius: 5px;
                -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
                // background-color:rgba(255,255,255,1);
                background-color: #99a9bf;
            }
             
            &::-webkit-scrollbar-track {
                -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
                border-radius: 5px;
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
            width: 80%;
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
</style>