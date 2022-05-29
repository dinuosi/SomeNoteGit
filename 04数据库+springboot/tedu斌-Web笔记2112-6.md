### Vue指令(续)
1. v-for="(变量,i) in 数组变量";  让元素的显示数量和数组进行绑定, 同时遍历数组中的每一个对象,变量代表的是数组中的 每一个对象, i代表的是当前遍历的下标 
2. v-if="变量", 元素的显示状态和变量进行绑定, true显示,false不显示(删除元素)
3. v-else和v-if指令结合使用, 显示状态和v-if的元素取反 
4. v-show="变量",元素的显示状态和变量进行绑定, true显示,false不显示(通过样式隐藏了元素), 需要元素需要频繁切换显示状态使用v-show的执行效率会高于v-if

### idea中安装element插件
- 先从老师工程中得到element.jar 文件
- File->Settings->Plugins     如下图  操作  找到element.jar文件  最后重启idea即可
![在这里插入图片描述](https://img-blog.csdnimg.cn/ae3d7b7b3b2a4c96891181f4e4f588b4.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAdGVkdeaWjA==,size_20,color_FFFFFF,t_70,g_se,x_16)

