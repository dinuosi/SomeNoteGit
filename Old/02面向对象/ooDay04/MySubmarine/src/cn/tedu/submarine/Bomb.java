package cn.tedu.submarine;
/** 深水炸弹:是海洋对象 */
public class Bomb extends SeaObject {
    /** 构造方法 */
    Bomb(int x,int y){
        super(9,12,x,y,3);
    }

    /** 重写move()移动 */
    void move(){
        y+=speed; //y+(向下)
    }
}













