package cn.tedu.submarine;
/** 鱼雷:是海洋对象 */
public class Torpedo extends SeaObject {
    /** 构造方法 */  //Torpedo t = new Torpedo(100,200);
    public Torpedo(int x,int y){
        super(5,18,x,y,1);
    }

    /** 重写move()移动 */
    public void move(){
        y-=speed; //y-(向上)
    }
}
