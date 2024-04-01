<template>
    <div id="index">
       
        <!-- <div class="header">
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
        </div> -->
        
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
                    <h3>Vue、React、JavaScript</h3>
                    <h4>Java、SpringBoot、Mysql</h4>
                    <h5>Linux、Python、C++</h5>
                    <br>
                    <h4>邮箱: hbaiqiao@163.com</h4>
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
         
        
         
    </div>
   
</template>

<script>
import Carousel from "./Carousel.vue";
// import musicdata from "../src/assets/musicdata.json";
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
        // musicSrc:[
        //             require("../src/assets/travelinglight.mp3"),
        //             require("../src/assets/Whatever It Takes.mp3"),
        //             require("../src/assets/再飞行.mp3")
        //         ],
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
        // musicImg:[
        //       require("../src/assets/music0.jpg"),
        //       require("../src/assets/music1.jpg"),
        //       require("../src/assets/music2.jpg"),
        // ],
        audioImg:'',
        wordIndex:1,
        breaktag:false,
        gotoTimer:null,

        clickAble:true,
        musicLoading:false,
        ulLi:null,

        musicList:[],
     }
  },
  created(){
    this.gettime()
    this.getdataPage(10,1)
  },
  mounted(){
    //  console.log(musicdata.data);
    //  this.getMusicData()
  },
  beforeUnmount(){
        // clearInterval(this.musictimer)
        if(this.playtag){
            this.playMusic()
        }
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
    getdataPage(size,index){
            this.axios.get("/musicPage/" + size +'/'+ index)
           .then(res=>{
               console.log(res);
               this.musicList = res.data.content
               this.musicList.forEach(item=>{
                   item.word = JSON.parse(item.word)
               })
               this.getMusicData()
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

        this.musictitle = this.musicList[0].title
        this.musician = this.musicList[0].musician
        this.musicdata = this.musicList[0].word;   
        this.endtime = this.musicList[0].duration
        this.duration = this.musicList[0].duration
        this.duration = this.$refs.audio.duration;
       
        this.audioSrc = this.musicList[0].src
        this.audioImg = this.musicList[0].cover
        console.log(this.audioSrc);

        
    },
    async changeMusicData(index){
        this.wordIndex = 1;
        let length = this.musicList.length
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
        this.musicdata = this.musicList[this.musicIndex].word;
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
        },1000)
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
            //先暂停再开启
            // this.playMusic()
            // this.wordIndex = index+1
            // this.$refs.audio.currentTime = time
            // setTimeout(()=>{
            //     this.playMusic()
            // },1000)


            // let stop = new Promise((resolve)=>{
            //      this.playMusic()
                
            //      resolve()
            // })
            // stop.then(()=>{
            //     this.playMusic()
            // })

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
        // let timer = null
        // if(!this.playtag){
        //     return new Promise((resolve,reject) => {
        //         console.log("暂停暂停");
        //         clearTimeout(timer)
        //         this.breaktag = true   //结束上个进程的for循环
        //         reject('循环中止')
        //     })
        // }
        // return new Promise((resolve)=>{
        //      timer= setTimeout(()=>{  
        //         //  this.breaktag = false   //循环通过
        //           resolve()
        //      },time)
        // })

        // let timer = null
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

<style lang="scss" scoped>
.test{
    color: orange;
    // width: 100vw;
    // height: 100vh;
    h2{
        width: 100%;
    }
}
#index{
//    width: 100vw;
//    height: 100vh;
   padding: 0 ;
   margin: 0;
   min-width: 1080px;
   min-height: 300px;
}
.header{
    width: 100vw;
    height: 2.5vw;
    line-height: 2.5vw;
    font-size: 1vw;
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
    top:5vw;
    left: 5vw;
    width: 90vw;
    height: 40vw;
    background-color:rgba(255,255,255, 0.3);
    margin: auto;
    border-radius: 1vw;
    display: flex;
}
.item{
     flex: 3;
     height: 38.5vw;
     background:rgba(255,255,255,0.4);
     margin: 0.5vw;
     border-radius: 0.5vw;
     box-sizing: border-box;
  }
.left{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top:5vw ;
    
     img{
        width: 12vw;
        height: 12vw;
        border-radius: 6vw;
        margin: auto;
     }
     h2{
        line-height: 5vw;
        font-size: 1.5vw;
        font-weight: 400;
    }
     .left_down{
        text-align: center;
        // line-height: 3vh;
        width: 100%;
        height: 19vw;
        h2{
            line-height: 5vw;
            font-size: 1.2vw;
            font-weight: 400;
        }
        h3{
            line-height: 2.5vw;
            font-size: 1.2vw;
            font-weight: 400;
        }
        h4{
            line-height: 2vw;
            font-size: 1.1vw;
            font-weight: 400;
        }
        h5{
            line-height: 2.5vw;
            font-size: 1vw;
            font-weight: 400;
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
        font-weight: 300;
    }
    .timebox{
        width: 100%;
        height: 20vw;
        display: flex;
        justify-content: center;
        margin:0 auto;
        .time_item{
          margin-right: 1vw;
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

     background:rgba(255,255,255,0.5);
    // //  margin: 10px;
    //  border-radius: 10px;
     position: relative;
     .music_area{
        width: 100%;
        height: 32.5vw;
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
            }
            h4{
                text-align: center;
                line-height: 2.5vw;
            }
        }
        .music_word{
            width: 100%;
            height: 27vw;
            overflow-y: scroll;
            white-space: wrap;
            list-style: none;
            color: #666;
            ul{
                list-style: none;
            }
            li{
                width: 100%;
                height: 4vw;
                text-align: center;
                line-height: 4vw;
                // margin-bottom: 2vh;
                padding: 0.1vw 0;
                box-sizing: border-box;
                font-size: 0.9vw;
                
                h4{
                   text-align: center;
                   width: 100%;
                   line-height: 4vw;
                   font-size: 0.9vw;
                }
                h5{
                    text-align: center;
                    width: 100%;
                    line-height: 2vw;
                    font-size: 0.9vw;
                }
            }
            li:hover{
                color: deepskyblue;
            }
            .playnow{
                color: deepskyblue;
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


//媒体查询

@media screen and (max-width: 992px){
    .right{
        display: none;
    }
    .main{
        width: 70vw;
        // margin: 0 15vw;
        left:15vw;
    }
    .left{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top:5vw ;
    
     img{
        width: 12vw;
        height: 12vw;
        border-radius: 6vw;
        margin: auto;
     }
     h2{
        line-height: 3vw;
        font-size: 1.2vw;
        font-weight: 400;
    }
     .left_down{
        text-align: center;
        // line-height: 3vh;
        width: 100%;
        height: 19vw;
        h2{
            // line-height: 3vw;
            font-size: 8px;
            font-weight: 400;
        }
        h3{
            // line-height: 2.5vw;
            font-size: 7px;
            font-weight: 400;
        }
        h4{
            // line-height: 2vw;
            font-size: 6px;
            font-weight: 400;
        }
        h5{
            // line-height: 2vw;
            font-size: 5px;
            font-weight: 400;
        }
     }

    
  }

    

}
</style>