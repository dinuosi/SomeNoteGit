package cn.tedu.submarine;
/** 鱼雷潜艇:是海洋对象 */
public class TorpedoSubmarine extends SeaObject {
    /** 构造方法 */
    public TorpedoSubmarine(){
        super(64,20);
    }

    /** 重写move()移动 */
    public void move(){
        x+=speed; //x+(向右)
    }
}














