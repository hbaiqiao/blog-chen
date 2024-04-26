<h1>个人博客——chen</h1>  
项目搭建时间 2023-07-20 -  2023-08-05   部署网址 <a href="http://47.106.177.94:9990/" title= "在线地址">在线地址</a>     阿里云Centos OS </br>
前端使用Vue和JavaScript搭建，使用js完成原生组件的编写和封装。 后端使用Java和SpringBoot框架进行搭建。数据库使用Mysql。使用Springboot进行博客上传文件的本地和服务器存储。    

<h4>内容：本博客系统实现多媒体文件的上传和读写、富文本编辑器、轮播图、歌词滚动、图文发布等功能。</h4></br>
<h4>要点: </br>
1、使用Js 完成 轮播图、富文本编辑器、分页器、文件预览和随机命名、上传系统、图文动态功能的封装。</br>
2、使用 Promise、async 、await 完成异步任务时钟时间的校准、音乐播放器歌词延时滚动的功能。</br>
3、使用Vuex存储用户信息、Vue-Router管理系统路由，使用路由守卫进行用户的权限限制。</br>
4、视口布局，flex布局实现响应式布局、使用媒体查询兼容移动端页面的显示。</br>
</h4>

<h2>笔记一、博客建站记录</h2>      

<h3>一、技术选型</h3>     
<h4>前端</h4>   
采用Vue框架进行博客前端的搭建，使用 js 完成 轮播图、自定义音乐播放器、歌词滚动、富文本编辑器、分页器等组件的封装。采用 Nginx 来部署前端打包后的 前端文件    
<h4>后端</h4>    
采用SpringBoot 框架来进行博客后端的搭建，连接数据库和本地服务器资源文件夹，读取数据和文件返回前端接口，接收前端的数据和数据操作请求来对服务器数据库的数据和本地资源文件图片、音频、视频等文件进行读写操作。运行 SpringBoot 打包后的 Jar / War 文件，内置 Tomcat ,开启后端服务    
<h4>数据库</h4>
数据库采用 Mysql 进行数据存储，建立各数据表来存储各个数据项，通过后端来实现数据库表的 列表查询，分页查询，按项查找，模糊搜索，新增数据，数据修改，数据删除等操作。    
<h4>文件资源</h4>
文件存储是博客的重要部分，前端通过文件选择器选择文件，前端通过文件缓存流读取预览文件，复制文件，进行时间和随机字符的重命名，返回新文件，防止服务器文件重名，提交请求，发送新命名的文件给后端接口，后端打开文件夹，接收文件，将文件存入服务器文件夹。
后端建立服务器文件夹与 api / file 接口的映射，通过 api链接 + 文件名 即可访问服务器文件。前端将 api链接 + 新命名文件名字符串 作为数据项存入数据库，在使用文件时获取文件的服务器地址，访问服务器文件。  
<h4>服务器</h4>
服务器使用 腾讯云 / 阿里云 / 华为云 的服务器皆可，新用户哪个便宜用哪个。本次使用阿里云 轻量云服务器，Linux Centos 版本。 镜像采用 宝塔Linux 面板。安装 Mysql、 Java JDK 、Nginx 应用。方便进行管理。  
前端 Nginx 可使用 码云 或 github 的网站托管服务，可以通过码云链接来访问前端。 通过码云的个人信息验证 使用 gitee Page 服务开启 

![image](https://github.com/hbaiqiao/blog-chen/assets/79921484/66c03c9f-3e79-4950-bb54-4bb80a594296)


<h3>二、要点记录</h3>
1、在服务器部署时，后端的配置文件中 运行的IP地址 要为 127.0.0.1 或服务器的 内网地址</br>
2、后端配置文件的数据库 的用户名 和 密码 要与服务器的数据库用户名和密码一致，端口默认 3306 注意 </br>
3、在配置 Nginx serve 是注意端口的配置， 和后端 Jar 运行端口的配置， 在云服务器管理的防火墙中 开放端口，才能通过访问权限 </br>
4、服务器文件夹要 开放读写权限， 主要注意文件写入的权限，未开放后端在接收 前端的文件时，写入文件会发生错误


例如上方所示，未开放文件写权限导致
5、注意使用绝对路径，Linux 根路径 / 


<h2>笔记二、前端文件操作</h2>
<h3>一、文件的选择</h3>
以图片为例，创建input file 节点，js 调用 input file 点击事件，打开文件选择器，选择一个或者多个文件，选择的文件存放与input.files属性中，通过input.files[0]可以访问选择的第一个文件
<h3>二、文件上传类型的限制</h3>
获取文件选择器的文件file, 使用file.type属性判断文件类型，imges/jpg,imges/png, vedio/mp4, audio/mp3, 以文件主类型+ 文件具体类型。要对文件上传类型的图片、音频、视频格式的限制，可以通过查找file.type 属性的主类型  image/vedio/ audio 来实现。
<code>
   if(file.type.search(/image/)==-1){
         alert('选择文件非图片类型')
           return
    }
</code>
<h3>三、文件预览</h3>
文件预览通过文件读取器 FileReader() 来实现，将文件转化为二进制缓存流， 在前端实现文件的预览
<code>
          let file = fileSelect.files[0]
          let fr = new FileReader();
               fr.readAsDataURL(file);
                fr.onloadend = ()=>{
                 // document.getElementById("img").src = fr.result;
                         this.$refs.cover.src = fr.result
                }
</code>
<h3>四、文件重命名</h3>
Js 不允许直接对文件进行重命名，可以通过复制文件，随机字符串命名，指定文件类型来实现，以下是封装的文件复制命名函数
<code>
const newfile = {
    //传入文件 获取随机字符 重命名 复制新文件 返回新文件
    newfile:function(file){
        let date = new Date()
        let year = date.getFullYear()
        let mouth = date.getMonth()+1
        let day = date.getDate()
        let hour = date.getHours()
        let minute = date.getMinutes()
        let second = date.getSeconds()
        let filename = ''+ year + mouth + day + hour + minute + second + this.getNum() + this.getNum() + this.getNum() + this.getNum() +this.getNum() + this.getNum() +file.name.substr(file.name.lastIndexOf(".")); 
        let newFile = new File([file],filename,{type:file.type});
        console.log(newFile.name);
        return newFile
    },
    getNum:function(){
        //随机0-9
        return  Math.floor(Math.random() * 10);  
    } 
}
export default newfile
</code>
传入文件对象，通过时间日期和6位随机数字来实现重命名，防止文件重复，复制新文件，返回文件对象
<h3> 五、文件上传</h3>
系统采用数据库数据项存放文件的服务器地址，将文件存放于服务器的专属文件夹中，
<code>
               let newFile = newfile.newfile(file)
               this.imgFile = newFile
              //  this.cover = 'http://127.0.0.1:6660/api/file/musicImg/'+ newFile.name  
               this.cover = this.axios.defaults.baseURL + '/file/musicImg/'+ newFile.name 
</code>

axios上传文件
<code>
               const formDataImg = new FormData();
               formDataImg.append('file', this.imgFile)
               this.axios.post('/musicImgFile',
                  formDataImg
               ).then(res=>{
                   console.log(res);
               })
</code>
<h3>六、图文模块图片上传代码</h3>
<code>
       addPic(){
          let fileSelect = document.createElement('input')
          fileSelect.type = 'file'
          let H3 = document.createElement('h3')
          let content = this.$refs.content
          H3.appendChild(fileSelect)
        //   content.appendChild(H3)
          let img = document.createElement('img')
          fileSelect.click()
          fileSelect.addEventListener('change',()=>{
            if(!fileSelect.files[0]&&img.src) {
                for(let i = 0;i<length;i++)               
                   if(img.dataName == this.filesData[i].name){
                           this.filesData.splice(i,1)
                           console.log(this.filesData);
                     }
                }
                content.removeChild(img)
                return
            }
            console.log(fileSelect.files[0]);
            let file = fileSelect.files[0]
             if(file.type.search(/image/)==-1){
                  alert('选择文件非图片类型')
                  return
               }
            console.log(file.name);
            let date = new Date()
            //完成文件的随机命名
            img.dataName ='' + date.getFullYear() + date.getMonth() + date.getDay() + date.getHours()+date.getMinutes() +date.getSeconds()+Math.floor(Math.random()*1000)+Math.floor(Math.random()*1000) + file.name.substr(file.name.lastIndexOf("."));
            //复制文件重命名
            var newFile = new File([file],img.dataName,{type:file.type});
            console.log(newFile.name);
            //添加进文件组
            this.filesData.push(newFile)
            //完成路径src的封装
            // img.dataSrc = 'http://127.0.0.1:6660/api/file/blog/'+ img.dataName
            img.dataSrc = this.axios.defaults.baseURL +'/file/blog/'+ img.dataName
             if(window.FileReader){
           //创建读取文件的对象
           var fr = new FileReader();
           //以读取文件字符串的方式读取文件 但是不能直接读取file 
           //因为文件的内容是存在file对象下面的files数组中的
           //该方法结束后图片会以data:URL格式的字符串（base64编码）存储在fr对象的result中
           fr.readAsDataURL(file);
           fr.onloadend = ()=>{
            // document.getElementById("img").src = fr.result;
                    img.src = fr.result
           }
          }
             content.appendChild(img)
          })
          //更改图片   
          img.addEventListener('click',()=>{
               fileSelect.click()             
          })
       },
</code>
<h3>七、音乐模块上传封面、代码精简</h3>
<code>
       uploadImg(){
          let fileSelect = document.createElement('input')
          fileSelect.type = 'file'
          fileSelect.click()
          fileSelect.addEventListener('change',()=>{
               let file = fileSelect.files[0]
               if(file.type.search(/image/)==-1){
                  alert('选择文件非图片类型')
                  return
               }
               console.log(file);
               let fr = new FileReader();
               fr.readAsDataURL(file);
                fr.onloadend = ()=>{
                 // document.getElementById("img").src = fr.result;
                         this.$refs.cover.src = fr.result
                }
               let newFile = newfile.newfile(file)
               this.imgFile = newFile
               this.cover = this.axios.defaults.baseURL + '/file/musicImg/'+ newFile.name 
               console.log(this.cover);
          })   
       },
</code>

<h2>效果展示</h2>

![image](https://github.com/hbaiqiao/blog-chen/assets/79921484/bd2b7848-783a-4f9f-aee2-18f81743a9b2)

![image](https://github.com/hbaiqiao/blog-chen/assets/79921484/4a8845a1-c86b-42e3-b614-1a21f361b68b)

![image](https://github.com/hbaiqiao/blog-chen/assets/79921484/d3283eea-47a1-464a-8b28-b4c1701546f9)

![image](https://github.com/hbaiqiao/blog-chen/assets/79921484/15450da9-c5b2-4a4d-ae22-e6a792db1a98)

![image](https://github.com/hbaiqiao/blog-chen/assets/79921484/3f9849c9-7c5f-4aec-9da8-69924e4789d5)

![image](https://github.com/hbaiqiao/blog-chen/assets/79921484/46d90f0c-0571-4783-b09e-6b151da5cd96)

![image](https://github.com/hbaiqiao/blog-chen/assets/79921484/b6ad0983-4cd9-4ccc-8a42-3bfb30caa0ff)

![image](https://github.com/hbaiqiao/blog-chen/assets/79921484/f41bc33d-0b1e-4c3e-9b9a-09c6a07d02b2)

![image](https://github.com/hbaiqiao/blog-chen/assets/79921484/9213791a-3c4a-4963-b243-1b32224b7d1f)


![fb46e02027321f0502cd05fdd9299ad](https://github.com/hbaiqiao/blog-chen/assets/79921484/9e052821-a7bd-4db4-8675-e026c44de325)







