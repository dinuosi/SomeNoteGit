package cn.tedu.submarine;
/** 水雷:是海洋对象 */
public class Mine extends SeaObject {
    /** 构造方法 */
    Mine(int x,int y){
        super(11,11,x,y,1);
    }

    /** 重写move()移动 */
    void move(){
        y-=speed; //y-(向上)
    }
}

















