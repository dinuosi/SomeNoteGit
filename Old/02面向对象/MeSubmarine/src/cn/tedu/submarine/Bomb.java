package cn.tedu.submarine;
import javax.swing.*;
/** 深水炸弹:是海洋对象 */
public class Bomb extends SeaObject {
    /** 构造方法 */
    public Bomb(int x,int y){ //深水炸弹初始坐标依赖于战舰，所以数据不能写死，需传参写活
        super(9,12,x,y,3);
    }

    /** 重写step()移动 */
    public void step(){
        y+=speed; //y+(向下)
    }

    /** 重写getImage()获取图片 */
    public ImageIcon getImage(){
        if(isLive()){ //若活着的
            return Images.bomb; //则返回深水炸弹图片
        }else{ //若死了的
            return null; //不返回图片
        }
    }

    /** 重写isOutOfBounds()检测深水炸弹越界 */
    public boolean isOutOfBounds(){
        return this.y>=World.HEIGHT; //炸弹的y>=窗口的高，即为越界了
    }
}










