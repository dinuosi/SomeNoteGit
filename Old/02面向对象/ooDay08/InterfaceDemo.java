package ooday08;
//�ӿڵ���ʾ
public class InterfaceDemo {
    public static void main(String[] args) {
        //Inter5 o = new Inter5(); //������󣬽ӿڲ��ܱ�ʵ����
        Inter5 o1 = new Doo(); //��������
        Inter4 o2 = new Doo(); //��������
    }
}

//��ʾ�ӿڼ̳нӿ�
interface Inter4{
    void show();
}
interface Inter5 extends Inter4{
    void test();
}
class Doo implements Inter5{
    public void test(){}
    public void show(){}
}











//��ʾ�ӿڵĶ�ʵ�֣��ּ̳���ʵ��
interface Inter2{
    void show();
}
interface Inter3{
    void test();
}
abstract class Boo{
    abstract void say();
}
class Coo extends Boo implements Inter2,Inter3{
    public void show(){}
    public void test(){}
    void say(){}
}













//��ʾ�ӿڵ�ʵ��
interface Inter1{
    void show();
    void test();
}
class Aoo implements Inter1{
    public void show(){} //��д�ӿ��еĳ��󷽷�ʱ�������publicȨ��
    public void test(){}
}










//��ʾ�ӿڵ��﷨
interface Inter{
    public static final int NUM = 5;
    public abstract void show();
    int COUNT = 6; //Ĭ��public static final
    void test(); //Ĭ��public abstract
    //int number; //������󣬳�����������ͬʱ��ʼ��
    //void say(){} //������󣬳��󷽷������з�����
}























