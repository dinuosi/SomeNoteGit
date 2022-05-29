package cn.tedu.submarine;
import javax.swing.ImageIcon;
/** 鱼雷:是海洋对象 */
public class Torpedo extends SeaObject {
    /** 构造方法 */
    public Torpedo(int x,int y){
        super(5,18,x,y,1);
    }

    /** 重写step()移动 */
    public void step(){
        y-=speed; //y-(向上)
    }

    /** 重写getImage()获取图片 */
    public ImageIcon getImage(){
        if(isLive()){ //若活着的
            return Images.torpedo; //则返回鱼雷图片
        }else{ //若死了的
            return null; //不返回图片
        }
    }

    /** 重写isOutOfBounds()检测鱼雷越界 */
    public boolean isOutOfBounds(){
        return this.y<=-this.height; //鱼雷的y<=负的鱼雷的高，即为越界了
    }
}

















