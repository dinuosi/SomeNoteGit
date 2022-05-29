# JavaScript
- 作用: 负责给页面添加动态效果.
- 语言特点:
	- 属于弱类型语言:    
		- java:   String name = "tom";    int age=18;       name=30;  报错   
		- javascript:  let name="tom";    let age=18;      name=30;   不会报错     
	- 属于脚本语言,不需要编译直接解析执行.  
	- 基于面向对象  
	- 安全性强:  JS语言只能访问浏览器内部的数据,浏览器以外的程序或磁盘中的数据禁止访问.
	- 交互性强:  因为JS语言是嵌入在html页面中的语言, 随着页面一起传递到客户端,运行在客户端的语言,离用户更近一些,java语言是运行在服务器的语言, 所以JS语言交互性更强  
### 如何在html页面中添加JS语言
- 内联:  在标签的事件属性中添加js代码, 当事件触发时执行 .
	- 事件: 系统给提供的特定时间点.
	- 点击事件: 在用户点击元素时触发的时间点 
![在这里插入图片描述](https://img-blog.csdnimg.cn/b86c114e11494bf2aedf85fe9073c3f8.png)
- 内部:在html页面中任意位置添加script标签,在标签体内写JS代码  
![在这里插入图片描述](https://img-blog.csdnimg.cn/4b305582943f443a8d6d667c159b32a9.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAdGVkdeaWjA==,size_20,color_FFFFFF,t_70,g_se,x_16)
- 外部:在单独的js文件中写js代码, 在html 页面中通过script标签的src属性引入  
![在这里插入图片描述](https://img-blog.csdnimg.cn/b4b2dd053f8e4d3d923f99f8d972a7cc.png)

变量,数据类型, 运算符,各种语句,方法, 面向对象

### 变量
- JavaScript属于弱类型语言
		- java:   String name = "tom";    int age=18;       name=30;  报错   
		- javascript:  let name="tom";    let age=18;      name=30;   不会报错     
- let和var关键字的区别 
	- 使用let声明的变量, 作用域和Java语言一样
	- 使用var声明的变量,  相当于声明的是一个全局变量
	- 举例:
		java: 
			for(int i=0;i<10;i++){
				int y=i+1;
			}
			int z = y-i;     报错: i超出了作用域
		JavaScript:
			 for(let i=0;i<10;i++){
				let y=i+1;
			}
			let z = y-i;     不会报错,但是y和i 是访问不到的  
			for(var i=0;i<10;i++){
				var y=i+1;
			}
			var z = y-i;     y和i是可以访问的
### 数据类型
- JavaScript中只有引用类型(对象类型)
- 常见的对象类型:
	- number:  数值类型, 相当于java中所有数值类型的总和   
	- string: 字符串    ,  可以用单引号或双引号修饰     'tom'   "tom"
	- boolean: 布尔值 ,   true/false
	- undefined:  未定义    ,当变量只声明不赋值时,变量的类型为未定义
	- 获取变量类型的方式:    typeof 变量   
### 运算符
- 算术运算符: +-*/%,   JS除法会自动根据结果转换整数还是小数
	- java: int x=5;  int y = 2;  x/y = 2;
	- js:   let x = 5; let y = 2;  x/y = 2.5;
- 关系运算符: > < >= <= !=  = = 和===
	- ==: 先统一等号两边的类型,再比较值       "666"==666    true
	- ===: 先比较类型,类型相同之后再比较值   "666"==666  false
- 逻辑运算符: && ,|| , !   
- 三目运算符:     条件?值1:值2
### 各种语句
- if else 
- while 
- do while 
- for 
- switch case  
### 方法
- Java: public 返回值类型 方法名(参数列表){方法体}
- JS:    function 方法名(参数列表){方法体}
- 常见的四种方法:
	- 无参无返回值
	- 无参有返回值
	- 有参无返回值
	- 有参有返回值
- 声明方法的三种方式:
	1.  function 方法名(参数列表){方法体}
	2. let 方法名 = function(参数列表){方法体}
	3. let 方法名 = new Function("参数1","参数2","方法体");


### NaN
- Not a Number:  不是数   
- isNaN(变量)   判断变量是否是NaN  ,  true代表是NaN  false代表不是NaN

### JavaScript中的对象分类
- 内置对象:  number, string, boolean等  
- BOM:  Browser Object Model   浏览器对象模型,  包含和浏览器相关的内容  
- DOM:  Document Object Model  文档对象模型,  文档指html标签,   包含和页面相关内容   

### BOM浏览器对象模型
- 包含和浏览器相关内容
- window对象:  该对象的属性和方法称为全局属性和全局方法, 访问时可以省略掉window.    
### window对象中常见的方法
- alert("xxx")   弹出提示框
- confirm("xxxx")  弹出确认框  
- prompt("xxx") 弹出文本框
- parseInt()   将字符串或小数转成整数
- parseFloat()  将字符串转成整数或小数
- isNaN()   判断变量是否是NaN   
- console.log()  浏览器控制台输出
- let timer = setInterval(方法,时间间隔)  定时器 
- clearInterval(timer)  停止定时器
- setTimeout(方法,时间间隔)  只执行一次的定时器 
### Window对象中常见的属性
- location 位置
	1. location.href   获取或修改浏览器的请求地址         
	2. location.reload()  刷新页面
	3. location.search  获取浏览器地址栏中的参数
- history 历史(指当前浏览器页卡的历史, 关闭后则清除)
	1. history.length   历史页面数量 
	2. history.back()  返回上一页面 
	3. history.forward()   前往下一页面   
	4. history.go(n)  n是正值前进  负值后退     0代表刷新  
### DOM 文档对象模型
- 和页面相关内容
- 通过元素的id获取元素对象  
	let 元素对象 = document.getElementById("元素id");
- 通过CSS中接触到的选择器获取元素对象
	let 元素对象 = document.querySelector("选择器");
- 获取和修改元素的文本内容
	元素对象.innerText;   //获取
	元素对象.innerText="xxx";    //修改   
- 获取和修改表单中的控件的值  
	控件.value       //获取
	控件.value="xxx"    //修改 
- 创建元素对象
	let 元素对象 = document.createElement("标签名"); 
- 添加元素对象
	document.body.appendChild(元素对象);
- 给元素的属性赋值
	元素对象.src = "../b.jpg";
	元素对象.属性="值";






























