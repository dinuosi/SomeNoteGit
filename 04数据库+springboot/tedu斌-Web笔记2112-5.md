### JavaScript中自定义对象

```
 //定义一个空的Person对象
  function Person() {}
  //实例化一个Person对象
  let p1 = new Person();
  //动态添加属性
  p1.name = "张三";
  p1.age = 18;
  //动态添加方法
  p1.run = function () {
    console.log("我叫"+this.name+"今年"+this.age);
  }
  //调用方法
  p1.run();
  //不需要定义,直接实例化对象
  let p2 = {};
  p2.name = "李白";
  p2.age = 20;
  p2.run = function () {
      console.log("李白的方法执行了!");
  }
  p2.run();
  //实例化自带属性和方法的对象
  let p3 = {
      name:"刘备",
      age:30,
      run:function () {
          alert(this.name+":"+this.age);
      }
  }
  p3.run();
  // 通过自定义对象封装数据
    let arr = [{name:"小米手机",price:"3000",count:500},
        {name:"小米电视",price:"3000",count:500},
        {name:"华为手机",price:"3000",count:500},
        {name:"华为电视",price:"3000",count:500}]
```
### 前端MVC设计模式
- MVC设计模式:实际上就是把前端实现一个业务功能的代码划分为3部分
- Model : 模型 ,   指数据模型 对应的是从服务器获取到数据的部分代码.
- View: 视图 ,    指页面部分代码
- Controller: 控制器,  把数据模型的内容 展示到页面中的过程称为控制器部分 

- 前端MVC设计模式的弊端: 需要在Controller部分 频繁进行DOM操作(遍历查找元素过程)会影响效率, 从而影响用户的体验度 
### M,V,VM设计模式
- Model : 模型 ,   指数据模型 对应的是获取到数据的部分代码.
- View: 视图 ,    指页面部分代码
- VM: 视图模型,  将页面中可能发生改变的元素在内存中和某一个变量进行绑定, 当变量的值发生改变时,会自动从内存中找到和变量对应的元素并进行修改, 不需要像MVC设计模式中每次进行遍历查找,  从而提高了效率,提高了用户体验度.

### VUE框架
- VUE框架是基于MVVM设计模式的前端框架.       
- 工作原理: Vue框架中的Vue对象是MVVM设计模式中的VM视图模型, 此对象负责将页面中改变的元素和data里面的变量进行绑定, 初始化时,变量的值是什么页面就显示成什么, 并且Vue对象会一直监听着data里面变量值得改变, 当值发生改变时,会自动找到与其绑定的元素并且让元素跟着发生改变,改变元素的过程是不需要程序员参与的, 从而提高了开发效率, 由于这个过程不需要频繁的进行DOM操作(遍历查找元素) 从而提高了执行效率.

### 引入前端 三方框架的两种方式:
1. 把框架文件下载到本地,复制到自己的工程中进行引入 
2. 从CDN服务器直接引入到自己工程.  

### Vue常用指令
1. {{变量}} : 插值, 让此处的文本内容和变量进行绑定 ,不需要依赖某个标签存在
2. v-text="变量":  让元素的文本内容和变量进行绑定, 需要依赖标签
3. v-html="变量":  让元素的标签内容和变量进行绑定  
4. v-bind:属性名="变量"  ,  让元素的某个属性的值和变量进行绑定 , 简写可以把v-bind去掉
5. v-model="变量":  让控件的value属性和某个变量进行双向绑定,  双向绑定: 变量会影响页面显示的内容,页面元素的内容改变也会影响变量,  当需要从代码中获取控件的值时使用双向绑定
6. v-on:事件名="方法",  绑定元素的事件, 方法需要声明在Vue对象里面的methods属性里面, 简写是@事件名="方法"
	- @click  点击事件
	- @change 值改变事件
	- @blur   失去焦点事件   

### idea中安装Vue插件
1. File->Settings->Plugins  搜索Vue   如图所示 , 安装完之后重启idea     
![在这里插入图片描述](https://img-blog.csdnimg.cn/19935906fc7a46aa9e655173f50b5dd8.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAdGVkdeaWjA==,size_20,color_FFFFFF,t_70,g_se,x_16)

### idea中添加自定义模板代码
- File->Editor->Live Templates
![在这里插入图片描述](https://img-blog.csdnimg.cn/9640c54afaba48faaf74bfc7e3cd05a5.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAdGVkdeaWjA==,size_20,color_FFFFFF,t_70,g_se,x_16)











