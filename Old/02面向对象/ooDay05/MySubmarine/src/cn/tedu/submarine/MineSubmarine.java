package cn.tedu.submarine;
/** 水雷潜艇:是海洋对象 */
public class MineSubmarine extends SeaObject {
    /** 构造方法 */
    public MineSubmarine(){
        super(63,19);
    }

    /** 重写move()移动 */
    public void move(){
        x+=speed; //x+(向右)
    }
}
