<template>
    <div id="carousel">
        <div class="focus" @mouseenter="mouseenter" @mouseleave="mouseleave" ref="foc">
            <div class="pre" ref="pre"> &lt;</div>
            <div class="next" ref="next">&gt;</div>

            <!-- <div v-html="ul_item">

            </div> -->
            <ul >
                <!-- <li><img :src="item"></li> -->
                <li><img src="../src/assets/carousel/01.jpg" alt=""></li>
                <li><img src="../src/assets/carousel/02.jpg" alt=""></li>
                <li><img src="../src/assets/carousel/03.jpg" alt=""></li>
                <li><img src="../src/assets/carousel/04.jpg" alt=""></li>
                <li><img src="../src/assets/carousel/05.jpg" alt=""></li>
                <li><img src="../src/assets/carousel/06.jpg" alt=""></li>
            </ul>
            <ol class="circle">
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ol>
        </div>

    </div>
</template>

<script>
  export default{
     name:'Carousel',
     data(){
        return{
           circle_li:'',
           ul_item:'',
           image:[
           "../src/assets/carousel/01.jpg",
           "../src/assets/carousel/02.jpg",
           "../src/assets/carousel/03.jpg",
           "../src/assets/carousel/04.jpg",
           ]
        }
     },
     methods:{
        mouseenter(){

        },
        mouseleave(){

        }
     },
     created(){
       
     },
     mounted(){
        // let pre = document.querySelector('.pre')
        // let next = document.querySelector('.next')
        // let foc = document.querySelector('.focus')
        let pre = this.$refs.pre
        let next = this.$refs.next
        let foc = this.$refs.foc
        //2.鼠标经过，显示两边按钮 //播放暂停
        // foc.addEventListener('mouseenter', function () {
        //     pre.style.display = 'block'
        //     next.style.display = 'block'
        //     clearInterval(timer)
        //     timer=null
        // })
        // foc.addEventListener('mouseleave', function () {
        //     pre.style.display = 'none'
        //     next.style.display = 'none'
        //     timer = setInterval(function(){
        //        next.click();
        //      },10000)
        // })
        
        //3.动态生成小圆点，有几张图片，就生成几个小圆点、
        let ul = foc.querySelector('ul')
        let ol = foc.querySelector('ol')
        console.log(ul.children.length);
        console.log(ol.children.length);
        for (let i = 0; i < ol.children.length; i++) {
            // 创建小li
            // let li = document.createElement('li')
            // 记录当前小圆圈的索引号，通过自定义属性来做
            // li.setAttribute('index', i)

            //将小li加入ol           
            // ol.appendChild(li)
            
            ol.children[i].setAttribute('index', i)
            //4. 小圆圈排他思想在生成小圆圈的同时直接绑定点击事件
            ol.children[i].addEventListener('click', function () {
                // 把所有小li清除current 类名
                for (let i = 0; i < ol.children.length; i++) {
                    ol.children[i].className = ''
                }
                this.className = 'current'
                //5. 点击小圆点，移动图片，移动的是ul
                // ul 的移动距离，小圆圈的索引号 乘 图片的宽度，注意是负值
                //当我们点击了某个小li 就拿到当前小 li 的索引号
                let index = this.getAttribute('index')
                //当我们点击了某个li,把index给num
                num=index
                circle=index
                let focusWidth = foc.offsetWidth
                console.log(focusWidth);
                console.log(index);
                console.log(-index * focusWidth)
                animate(ul, -index * focusWidth)
            })

        }
      
        ol.children[0].className = 'current'
        //6克隆第一张图片，放到ul最后面
        let first = ul.children[0].cloneNode(true)
        ul.appendChild(first)
        console.log(ul.children.length);
        
        function animate(obj, target, callback) {
            
            if (target - obj.offsetLeft < 0) {               
                let timer = setInterval(function () {
                    if (obj.offsetLeft <= target + 40) {
                        clearInterval(timer)
                    }
                    obj.style.left = obj.offsetLeft - 25 + 'px'
                }, 5)
            }
            else {                
               let timer = setInterval(function () {
                    if (obj.offsetLeft >= target -40) {
                        clearInterval(timer)
                    }
                    obj.style.left = obj.offsetLeft + 25 + 'px'
                }, 5)
            }
        }
        //7.点击右侧左侧按钮，图片滚动一张
        let num = 0
        //circle控制小圆圈播放
        let circle = 0
        next.addEventListener('click', ()=> {
            let focusWidth = foc.offsetWidth
            //如果走到最后一张，此时要快速复原
            if (num == 6) {
                ul.style.left = 0
                num = 0
            }
            num++;
            animate(ul, -num * focusWidth)
            //8.点击右侧按钮，小圆圈跟随一起变化，可以再声明一个变量控制小圆圈的播放、
            circle++;
            if(circle == 6){
                circle=0
            }
            for(let i=0; i<ol.children.length;i++){
                ol.children[i].className=""
            }
            ol.children[circle].className="current"
        })
        pre.addEventListener('click', ()=> {
            let focusWidth = foc.offsetWidth
            //如果第一张，走到最后一张
            if (num == 0) {
                num = ul.children.length-1
                ul.style.left = -(ul.children.length-1)*focusWidth + 'px' //600*4
            }
            num--
            animate(ul, -num * focusWidth)
            circle--
            if(circle==-1){
                circle= 5
            }
            for(let i=0; i<ol.children.length;i++){
                ol.children[i].className=""
            }
            ol.children[circle].className="current"
        })
        //10.自动播放轮播图, 手动调用点击事件
         let timer = setInterval(function(){
            next.click();
        },10000)
     }
  }
        

</script>

<style scoped>
    #carousel{
         width: 100vw;
         height: 50vw;
         background: skyblue;
         background-size: cover;
         /* position: absolute;
         top: 0;
         z-index: -1; */
    }
    ul {
            position: absolute;
            top: 0;
            left: 0;
            list-style: none;
            width: 700%;
            height: 100%;
        }

        .focus {
            position: relative;
            margin: 0 auto;
            list-style: none;
            background-color: #fff;
            width: 100%;
            height: 100%;
            text-align: center;
            
            overflow: hidden;
        }

        li {
            float: left;
            width: 100vw;
            /* height: 100vh; */
            height: 50vw;
        }

        .focus li img {
            width: 100%;
            height: 100%;
        }

        .pre {
            display: none;
            position: absolute;
            top: 50%;
            left: 0;
            transform: translateY(-50%);
            width: 40px;
            height: 60px;
            /* background: rgba(0, 0, 0, 0.3); */
            border-radius: 0 15px 15px 0;
            color: #fff;
            font-size: 3vw;
            font-weight: 500;
            line-height: 60px;
            text-align: center;
            z-index: 2;
        }

        .next {
            display: none;
            position: absolute;
            top: 50%;
            right: 0;
            transform: translateY(-50%);
            width: 40px;
            height: 60px;
            /* background: rgba(0, 0, 0, 0.3); */
            border-radius: 15px 0 0 15px;
            color: #fff;
            font-size: 3vw;
            font-weight: 500;
            line-height: 60px;
            text-align: center;
            z-index: 2;
        }

        .circle {
            position: absolute;
            left: 50%;
            bottom: 2vw;
            transform: translateX(-50%);
            list-style: none;
            height: 0.25vw;
            padding: 0.25vw;
            background-color: rgba(0, 0, 0, 0.3);
            border-radius: 8px;


        }

        .circle li {
            width: 2.5vw;
            height: 0.25vw;
            border-radius: 0.25vw;
            background-color: #fff;
            opacity: 0.6;
            margin: 0 3px;
        }

        .current {
            /* background-color: orange !important; */
            background-color: deepskyblue !important;
            opacity: 1 !important;
        }   
</style>