
### Git地址
https://gitee.com/teduliu/cgb2112.git
### 课程介绍
- web前端：  学习如何搭建页面，如何美化页面，如何给页面添加动态效果 
- MySQL数据库：学习如何对数据进行增删改查 
- SpringBoot: 学习如何接收客户端请求，以及如何响应请求，并将页面展示，业务逻辑，数据存储三大部分整合 
- 项目
### HTML
- 作用： 负责搭建页面结构和内容  （盖房子毛坯房）
- HyperTextMarkupLanguage：  超文本标记语言    
- 超文本： 指不仅仅是纯文本，还包括字体和多媒体（图片，音频，视频）
- 标记语言：    
-   <开始标签 属性=“xxx”>标签体</结束标签>
### 常见的文本标签
- 内容标题：h1-h6  
	特点： 独占一行， 字体加粗， 自带上下间距
- 水平分割线：hr
- 段落标签：p
	特点： 独占一行 自带上下间距
- 换行： br
- 加粗：b
- 斜体：i
- 下划线：u
- 删除线：s
### 列表标签
- 无序列表： ul和 li
- 有序列表： ol和li
- 列表嵌套： 有序列表和无序列表可以任意无限嵌套       
### 图片标签img
- src：资源路径
	- 	相对路径：访问站内资源使用
		- 	图片和页面在同级目录： 直接写图片名
		-  图片在页面的上级目录： `../图片名`  
		-  图片在页面的下级目录： 文件夹名/图片名
	- 绝对路径： 访问站外资源使用, 称为图片盗链,存在找不到图片的风险
- alt： 当图片不能正常显示时显示的文本
- title: 鼠标在图片上悬停时显示的文本
- width/height:设置图片的宽高, 两种赋值方式: 1. 像素  2. 百分比    如果只设置宽度高度会等比例缩放
### 超链接a
- href: 资源路径 作用类似图片标签的src
- a标签包裹文本为文本超链接, 包裹图片为图片超链接
- 页面内部跳转, 在目的地元素里面添加id属性,  然后通过超链接href="#id"的方式跳转到目的地元素的位置
### 表格标签table
- 相关标签: table表格  tr表示行 td表示列  th表头 caption表格标题   
- 相关属性: border边框    colspan跨列   rowspan跨行     
### 表单form
- 作用:获取用户输入的各种信息并且把这些信息提交给服务器  
- 需要学习form表单中的各种控件, 包括: 文本框,密码框 单选,多选,下拉选,日期选择器,文件选择器, 各种按钮等 
- 相关代码：

```
<!--action设置提交地址-->
<form action="http://www.tmooc.cn">
    <!--placeholder占位文本 value设置默认值 maxlength最大字符长度-->
    用户名:<input type="text" name="username" maxlength="5" value="tom"
               placeholder="请输入用户名" readonly><br>
    密码:<input type="password" placeholder="请输入密码"><br>
    <!--单选框中value必须写 不写则提交on checked设置默认选中-->
    性别:<input type="radio" name="gender" value="m" id="r1">
    <label for="r1">男</label>
    <input type="radio" name="gender" checked value="w" id="r2">
    <label for="r2">女</label><br>
    兴趣爱好:<input type="checkbox" name="hobby" value="cy">抽烟
    <input type="checkbox" name="hobby" checked value="hj">喝酒
    <input type="checkbox" name="hobby" value="tt">烫头<br>
    生日:<input type="date" name="birthday"><br>
    靓照:<input type="file" name="pic"><br>
    所在地:<select name="city">
    <option value="bj">北京</option>
    <option value="sh">上海</option>
    <!--selected设置默认选中-->
    <option value="gz" selected>广州</option>
</select><br>
    <!--提交按钮-->
    <input type="submit" value="注册">
    <!--重置按钮-->
    <input type="reset">
    <!--自定义按钮-->
    <input type="button" value="自定义按钮">
    <button type="submit">提交</button>
    <button type="reset">重置</button>
    <button type="button">自定义按钮</button>
</form>
```
# CSS
- 层叠样式表
- 作用： 负责美化页面     （装修）
### 如何在html页面中添加CSS样式代码
- 内联样式：在标签的style属性中添加样式代码, 弊端:不能复用
- 内部样式：在head标签里面添加style标签, 通过选择器找到元素后再添加样式代码, 可以复用但是不能多页面复用
- 外部样式：在单独的css样式文件中写样式代码, 在html页面中通过link标签引入, 可以多页面复用,可以将样式代码和html代码分离开.
- 工作中外部样式使用的最多, 学习过程中内部样式用的多 
### 选择器  
#### 标签名选择器
- 格式: 标签名{样式代码} 
- 匹配页面中所有同名标签  
#### id选择器
- 格式: #id{样式代码}
- 当需要选择页面中某一个元素时使用 
#### 类选择器
- 格式: .class{样式代码}
- 当需要选择页面中多个不相关元素时, 给多个元素添加相同的class属性值即可
#### 分组选择器
- 格式: p,#abc,.xyz{样式代码}
- 将多个选择器合并为一个选择器的写法
#### 属性选择器
- 格式: 标签名[属性名='值']{样式代码}

### 选择器练习
![在这里插入图片描述](https://img-blog.csdnimg.cn/a266f8b111fc4284a0b0dbc0787598d2.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAdGVkdeaWjA==,size_20,color_FFFFFF,t_70,g_se,x_16)
### 分区标签
- 可以理解为是一个容器,将多个有相关性的标签添加到一个分区标签里面, 便于统一管理
- 常见的两种分区标签:
	- div:块级分区元素, 特点: 独占一行   
	- span: 行内分区元素, 特点: 共占一行  
- 如何对页面内容进行分区:
	- 一个页面至少分为3大区,  头/体/脚, 每一个大区再划分n个小区 

```
<div>头</div>
<div>体</div>
<div>脚</div>
```
- html5标准中新增了几个分区标签,作用和div一样,为了提高代码的可读性 ,包括: header头,footer脚,section区域, article文章/正文, nav导航
```
<header></header>
<section></seciont>或<article></article>
<footer></footer>
```
### 选择器(续)
#### 子孙后代选择器
- 格式: body div div p{样式代码}
- 上面格式匹配的是body里面的div里面的div里面的所有p(包括子元素和后代元素)
#### 子元素选择器
- 格式: body>div>div>p{样式代码}
- 上面格式匹配的是body里面的div里面的div里面的所有子元素p(不包含后代元素)
#### 伪类选择器
- 选择的是元素的状态,包括以下四种状态
- a:link未访问/visited访问过/hover悬停/active点击{样式代码}



### 选择器回顾
- 标签名    div{}
- id选择器     #id{}
- 类选择器     .class{}
- 分组选择器    div,#id,.class{}
- 属性选择器   input[属性名='值']{}
- 任意元素      *{}  
- 子孙后代      div div p{}  
- 子元素选择器   div>div>p{}  
- 伪类选择器    a:link/visited/hover/active        








 

