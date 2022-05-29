package cn.tedu.submarine;
import javax.swing.*;
/** 水雷:是海洋对象 */
public class Mine extends SeaObject {
    /** 构造方法 */
    public Mine(int x,int y){
        super(11,11,x,y,1);
    }

    /** 重写step()移动 */
    public void step(){
        y-=speed; //y-(向上)
    }

    /** 重写getImage()获取图片 */
    public ImageIcon getImage(){
        if(isLive()){ //若活着的
            return Images.mine; //则返回水雷图片
        }else{ //若死了的
            return null; //不返回图片
        }
    }

    /** 重写isOutOfBounds()检测水雷越界 */
    public boolean isOutOfBounds(){
        return this.y<=150-this.height; //水雷的y<=150-水雷的高，即为越界了
    }
}















