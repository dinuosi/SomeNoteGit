package cn.tedu.submarine;
import javax.swing.ImageIcon;
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

    /** 重写getImage()获取对象图片 */
    public ImageIcon getImage(){
        return Images.torpedo; //返回鱼雷图片
    }
}
