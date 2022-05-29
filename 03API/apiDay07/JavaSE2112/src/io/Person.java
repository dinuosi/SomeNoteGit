package io;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 使用当前类测试对象流的序列化与反序列化操作
 * 对象序列化:将一个java对象按照其结构转换为一组字节的过程
 *
 */
public class Person implements Serializable {
    /*
        当一个类实现了可序列化接口时，最好显示的定义下面的属性:serialVersionUID
        即:序列化版本号
        当对象输出流在进行对象序列化时，会查看是否有显示的定义版本号，如果没有则会根据
        当前类的结构计算版本号(当前类(这里是Person)的结构只要没有发生过变化，那么无论
        何时序列化版本号始终不会变化，只要改变过，那么序列化版本号一定会改变)并将该版本
        号保存在序列化后的字节中。
        重点:
        当使用对象输入流反序列化时，对象输入流会检查要反序列化的对象与其对应的类(比如
        我们反序列化一个之前的Person对象)的版本号是否一致，若不一致，则会抛出异常:
        java.io.InvalidClassException
        比如OOSDemo序列化一个Person对象并写入文件person.obj后。我们在Person上添加
        一个新的属性salary,此时Person类发生了变化，那么再使用OISDemo反序列化person.obj
        文件中之前序列化的对象时就会发生异常InvalidClassException(因为Person类改变
        了结构，版本号不一致了)。

        如果后期修改了类结构，又希望原来的对象还可以进行反序列化，则需要显示的定义出来
        序列化版本号，这样一来，当一个对象序列化后，当前类结构改变了，只要版本号不变，
        那么之前的对象仍然可以进行反序列化。此时对象输入流会采取兼容方式，即:能还原的
        属性都会进行还原，没有的属性则采用默认值。
     */
    static final long serialVersionUID = 1L;

    private String name;//姓名
    private int age;//年龄
    private String gender;//性别
    /*
        当一个属性被关键字transient修饰后，那么当进行对象序列化时，该属性的值会被忽略。
        忽略不必要的属性可以达到对象"瘦身"的目的，提高程序响应速度，减少资源开销。
        当然，反序列化时，该属性只会采用默认值。

        transient:短暂的，转瞬即逝
     */
    private transient String[] otherInfo;//其他信息

//    private int salary;//工资

    //alt+insert 自动生成代码的快捷键

    public Person(String name, int age, String gender, String[] otherInfo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.otherInfo = otherInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String[] otherInfo) {
        this.otherInfo = otherInfo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", otherInfo=" + Arrays.toString(otherInfo) +
                '}';
    }
}




