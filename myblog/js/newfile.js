const newfile = {
    //传入文件 获取随机字符 重命名 复制新文件 返回新文件
    newfile:function(file){
        let date = new Date()
        let year = date.getFullYear()
        let mouth = date.getMonth()+1
        let day = date.getDate()
        let hour = date.getHours()
        let minute = date.getMinutes()
        let second = date.getSeconds()

        let filename = ''+ year + mouth + day + hour + minute + second + this.getNum() + this.getNum() + this.getNum() + this.getNum() +this.getNum() + this.getNum() +file.name.substr(file.name.lastIndexOf("."));
        
        let newFile = new File([file],filename,{type:file.type});
        console.log(newFile.name);
        return newFile

    },
    getNum:function(){
        //随机0-9
        return  Math.floor(Math.random() * 10);  
    } 
}
export default newfile