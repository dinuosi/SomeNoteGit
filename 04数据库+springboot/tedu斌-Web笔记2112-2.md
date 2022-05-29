### 选择器练习:
1. 把刘备改成红色
2. 张飞和苹果改成绿色
3. 文本框和所有水果背景黄色
4. p2 字体粉色
5. p2和p3背景蓝色
6. 超链接未访问绿色,访问过红色,悬停黄,点击红
	
### 颜色赋值
- 三原色: 红绿蓝     RGB   Red Green Blue ,     每个颜色的取值范围0-255  
- 五种颜色赋值方式:
	- 颜色单词:   red/blue/yellow
	- 6位16进制赋值:  #ff0000     
	- 3位16进制赋值: #f00     
	- 3位10进制赋值: rgb(255,0,0)
	- 4位10进制赋值: rgba(255,0,0,0-1)    a=alpha透明度   值越小越透明
### 背景图片
- background-image:url("路径") ; 设置背景图片
- background-repeat:no-repeat;  禁止重复
- background-size: 100px 200px;  设置背景图片尺寸   宽 高
- background-position: 横向  纵向;  控制位置, 像素偏移值  和 百分比两种方式
### 文本和字体相关样式
- text-align: left/right/center; 文本水平对齐方式
- text-decoration: overline/underline/line-through/none  文本修饰
- line-height:20px;  行高, 多行文本可以控制行间距,  单行文本可以实现垂直居中
- font-size: 20px;  设置字体大小
- font-weight:bold/normal;  设置加粗或去掉加粗
- font-style:italic;  设置斜体
- font-family:xxx,xxx,xxx;  设置字体
- font: 20px xxx,xxx,xxx;  设置字体大小和字体
- text-shadow: 颜色 x偏移值 y偏移值 浓度（值越大越模糊）
### 综合练习
1. 刘德华 宽度100 高度30 
 绿色背景 红色字体 
 横向和纵向居中
2. 苹果和香蕉 字体大小25px 斜体  
3. 冰箱去掉加粗 
4. 洗衣机添加下划线 
5. 百度去掉下划线字体加粗 
字体大小20px 

### 元素的显示方式display
- block: 块级元素的默认值 , 特点: 独占一行,可以修改元素的宽高 , 包括: div,h1-h6,p  
- inline: 行内元素的默认值, 特点:共占一行, 不可以修改元素的宽高, 包括: span, b,i,s,u,超链接a
- inline-block: 行内块元素的默认值, 特点:共占一行, 可以修改宽高,  包括:img,input
- none:隐藏元素  
- 行内元素不能修改宽高, 如必须修改 则需要先把display改成block或inline-block

### 盒子模型
- 作用: 控制元素的显示效果包括: 尺寸,元素位置,边框,元素内容的位置  
- 盒子模型: 内容+外边距+边框+内边距
- 内容content: 负责控制元素的尺寸
- 外边距margin:负责控制元素的位置
- 边框border:负责控制元素的边框
- 内边距padding:负责控制元素内容的位置  
### 盒子模型之内容Content
- width/height
- 赋值方式:
	- 像素:  200px
	- 上级元素的百分比:  50%
- 	行内元素不能修改宽高
### 盒子模型之外边距
- 外边距: 元素距上级元素或相邻兄弟元素的距离称为外边距
- 赋值方式:
	- margin-left/right/top/bottom:10px;  单独某个方向赋值
	- margin:10px;  四个方向赋值
	- margin:10px 20px; 上下10  左右20
	- margin:10px 20px 30px 40px;  上右下左 顺时针赋值
- 上下相邻彼此添加外边距取最大值 ,左右相邻相加 
- 行内元素上下外边距无效  
- 粘连问题: 当元素的上边缘和上级元素的上边缘重叠时,给元素添加上外边距会出现粘连问题,给上级元素添加overflow:hidden解决
- 部分标签会自带外边距,比如: body ,h1-h6,p,列表标签
### 盒子模型之边框
- 赋值方式:
	- border-left/right/top/bottom:粗细 样式 颜色; 单独某一个方向添加边框
	- border:粗细 样式 颜色; 四个方向添加边框
	- border-radius: 20px ;   圆角,  值越大越圆, 超过宽高的一半为正圆 
### 盒子模型之内边距
- 内边距: 元素边缘距内容的距离为内边距
- 赋值方式:  和外边距类似
	- padding-left/right/top/bottom:10px;  单独某个方向赋值
	- padding:10px;  四个方向赋值
	- padding:10px 20px; 上下10  左右20
	- padding:10px 20px 30px 40px;  上右下左 顺时针赋值
- 给元素添加内边距会影响元素的宽高 
### CSS的三大特性
1. 继承性: 元素可以继承上级元素文本和字体相关的样式, 部分标签自带的效果不受继承影响,如:超链接的字体颜色
2. 层叠性: 多个选择器可能选择到同一个元素, 添加的样式不同的时候会全部层叠生效, 如果作用的样式相同则由优先级决定哪个生效
3. 优先级: 作用范围越小 优先级越高,  id> class>标签名>继承(继承属于间接选中优先级比较低)
   








