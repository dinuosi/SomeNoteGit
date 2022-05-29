package cn.tedu.submarine;
import javax.swing.ImageIcon;
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

    /** 重写getImage()获取对象图片 */
    public ImageIcon getImage(){
        return Images.minesubm; //返回水雷潜艇图片
    }
}
