package ooday07;
//��Ա�ڲ���
public class InnerClassDemo {
    public static void main(String[] args) {
        Mama m = new Mama();
        //Baby b = new Baby(); //��������ڲ�����ⲻ�߱��ɼ���
    }
}

class Mama{ //�ⲿ��
    private String name;
    void create(){
        Baby b = new Baby(); //��ȷ
    }
    class Baby{ //�ڲ���
        void show(){
            System.out.println(name);
            System.out.println(Mama.this.name); //�ⲿ����.this
            //System.out.println(this.name); //�������this��ʾ��ǰ���󣬵�ǰBaby��û��name����
        }
    }
}



















