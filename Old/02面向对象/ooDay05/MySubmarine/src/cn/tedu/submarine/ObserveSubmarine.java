package cn.tedu.submarine;
/** 侦察潜艇:是海洋对象 */
public class ObserveSubmarine extends SeaObject {
    /** 构造方法 */   //ObserveSubmarine s = new ObserveSubmarine();
    public ObserveSubmarine(){
        super(63,19);
    }

    /** 重写move()移动 */
    public void move(){
        x+=speed; //x+(向右)
    }
}

















