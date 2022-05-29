# API基础第一天：



## 笔记：

1. String：字符串类型

   - java.lang.String使用的final修饰，不能被继承
   - 字符串底层封装了字符数组以及针对字符数组的操作算法
   - Java字符串在内存中采用Unicode编码方式，任何一个字符对应两个字节的编码
   - 字符串一旦创建，对象内容永远无法改变，但字符串引用可以重新赋值

2. 常量池：

   - java对字符串有一个优化措施：字符串常量池(堆中)
   - java推荐我们使用字面量/直接量的方式来创建字符串，并且会缓存所有以字面量形式创建的字符串对象到常量池中，当使用相同字面量再次创建字符串时会重用对象以减少内存开销，避免内存中堆积大量内容相同的字符串对象

   ```java
   /*
     使用字面量创建字符串时:
     1.JVM会检查常量池中是否有该对象:
       1)若没有，则创建该字符串对象并存入常量池
       2)若有，则直接将该对象返回而不再创建一个新的字符串对象
    */
   /*
   String s1 = "123abc"; //常量池还没有，因此创建该字符串对象，并存入常量池
   String s2 = "123abc"; //常量池已有了，直接重用对象
   String s3 = "123abc"; //常量池已有了，直接重用对象
   //引用类型==，比较地址是否相同
   System.out.println(s1==s2); //true
   System.out.println(s1==s3); //true
   System.out.println(s2==s3); //true
   */
   
   
   
   /*
     常见面试题:
       String s = new String("hello");
       问:创建了几个对象?
       答:2个
         第一个:字面量"hello"
               ---java会创建一个String对象表示字面量"hello"，并将其存入常量池
         第二个:new String()
               ---new String()时会再创建一个字符串，并引用hello字符串的内容
    */
   /*
   String s1 = new String("hello"); //s1装的是new String()对象的地址
   String s2 = "hello"; //s2装的是字面量"hello"的地址
   System.out.println("s1:"+s1); //hello
   System.out.println("s2:"+s2); //hello
   System.out.println(s1==s2); //false，因为s1与s2的地址不同
   
   //字符串实际开发中比较相等的需求都是比较字符串的内容
   //因此我们应该使用字符串提供的equals()方法来比较两个字符串的内容
   System.out.println(s1.equals(s2)); //true，因为s1与s2的内容相同
   */
   
   
   /*
   String s1 = "123abc";
   String s2 = "123abc";
   System.out.println(s1==s2); //true，s1与s2地址相同
   
   s1 = s1+"!"; //创建新对象并把地址赋值给s1
   System.out.println(s1==s2); //false，s1为新的对象的地址，与s2不同了
   */
   
   //如下代码:常量池中会有3个存储，一个是123abc的地址，一个是123的地址，一个是abc的地址
   //一个新的对象，它的值也是123abc
   String s1 = "123abc";
   //编译器在编译时，若发现一个计算表达式可以在编译期间确定结果，
   //则直接运算好并将结果保存到表达式中 相当于String s2 = "123abc";
   String s2 = "123"+"abc";
   System.out.println(s1==s2); //true，s1与s2共用常量池中的
   
   String s3 = "123";
   //当字符串拼接产生的内容与常量池是某内容相同时，也不会重用常量池的对象
   String s4 = s3+"abc"; //创建一个新的对象存储123abc
   System.out.println(s4==s1); //false
   ```

3. String常用方法：

   - length()：获取字符串的长度(字符个数)

     ```java
     String str = "我爱Java!";
     int len = str.length(); //获取str的长度
     System.out.println(len); //7
     ```

   - trim()：去除当前字符串两边的空白字符

     ```java
     String str = "  hello world            ";
     System.out.println(str); //  hello world
     str = str.trim(); //去除当前字符串两边的空白字符
     System.out.println(str); //hello world
     ```

   - indexOf(String str)：检索给定字符串在当前字符串的开始位置

     int lastIndexOf(String str):
     检索给定字符串在当前字符串中最后一次出现的位置

     ```java
     //            0123456789012345
     String str = "thinking in java";
     int index = str.indexOf("in"); //检索in在字符串str中出现的开始位置
     System.out.println(index); //2
     
     index = str.indexOf("IN"); //当前字符串不包含给定内容IN，所以返回-1
     System.out.println(index); //-1
     
     index = str.indexOf("in",3); //从第4个字符开始找in第一次出现的位置
     System.out.println(index); //5
     
     index = str.lastIndexOf("in"); //找in最后一次出现的位置
     System.out.println(index); //9
     ```

   - substring(int start,int end)：截取当前字符串中指定范围内的字符串(含头不含尾--包含start，但不包含end)

     ```java
     public class SubstringDemo {
         public static void main(String[] args) {
             /*
             //            01234567890
             String str = "www.tedu.cn";
             String name = str.substring(4,8); //截取第4个到第7个----下标
             System.out.println(name); //tedu
             name = str.substring(4); //从第4个一直截取到字符串末尾----下标
             System.out.println(name); //tedu.cn
             */
             String name = getName("www.tedu.com.cn");
             System.out.println(name); //tedu
             String str = getName("http://www.google.com");
             System.out.println(str); //google
     
         }
     
         /**
          * 获取给定网址中的域名
          * @param line 网址
          * @return 返回域名
          */
         public static String getName(String line){
             //012345678901234
             //www.tedu.com.cn  第一个点到第二个点之间的字符串
             int start = line.indexOf(".")+1; //4，加1目的是为了找到点后的第一个字符的位置
             int end = line.indexOf(".",start); //8，从start往后找第一个.的位置
             return line.substring(start,end);
         }
     }
     ```

   - charAt()：返回当前字符串指定位置上的字符

     ```java
     //            0123456789012345
     String str = "thinking in java";
     char c = str.charAt(9); //获取位置9所对应的字符
     System.out.println(c); //i
     ```

   - startsWith(String str)和endsWith(String str)：判断当前字符串是否是以给定的字符串开始/结尾的

     ```java
     String str = "thinking in java";
     boolean starts = str.startsWith("think"); //判断str是否是以think开头的
     System.out.println("starts:"+starts); //true
     
     boolean ends = str.endsWith(".png"); //判断str是否是以.png结尾的
     System.out.println("ends:"+ends); //false
     ```

   - toUpperCase()和toLowerCase()：将当前字符串中的英文部分转为全大写/全小写

     ```java
     String str = "我爱Java!";
     String upper = str.toUpperCase(); //将str中英文部分转为全大写
     System.out.println(upper); //我爱JAVA!
     
     String lower = str.toLowerCase(); //将str中英文部分转为全小写
     System.out.println(lower); //我爱java!
     ```

   - valueOf()：String类中提供的静态方法，将其它数据类型转换为String

     ```java
     int a = 123;
     String s1 = String.valueOf(a); //将int型变量a转换为String类型并赋值给s1
     System.out.println("s1:"+s1); //123
     
     double dou = 123.456;
     String s2 = String.valueOf(dou); //将double型变量dou转换为String类型并赋值给s2
     System.out.println("s2:"+s2); //123.456
     
     String s3 = a + ""; //任何内容与字符串连接结果都是字符串，效率低(下周一才能体会)
     System.out.println(s3); //123
     ```





补充：

1. ASCII：美国标准编码，是美国最早的字符集，也是计算机最底层的字符集，一个字节
2. GBK：国标编码，中国自己的编码，总共6万多个
3. Unicode：万国码，装全世界所有符号
4. UTF：在Unicode基础之上做的二次编码，里面加入了一个长度信息来标记是按一个字符解析还是按两个字符算

> 结论：互联网上真正使用的并不是unicode，真正传输出的是UTF这种带长度信息的编码，拿到UTF数据后再把长度去掉，还原成unicode编码。







