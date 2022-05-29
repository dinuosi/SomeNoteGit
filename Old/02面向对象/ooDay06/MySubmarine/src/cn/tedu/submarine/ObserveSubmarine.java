package cn.tedu.submarine;
import javax.swing.ImageIcon;
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

    /** 重写getImage()获取对象图片 */
    public ImageIcon getImage(){
        return Images.obsersubm; //返回侦察潜艇图片
    }
}

















