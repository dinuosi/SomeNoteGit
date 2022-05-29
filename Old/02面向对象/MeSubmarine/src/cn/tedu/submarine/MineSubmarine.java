package cn.tedu.submarine;
import javax.swing.*;
/** 水雷潜艇:是海洋对象，也能得命 */
public class MineSubmarine extends SeaObject implements EnemyLife {
    /** 构造方法 */
    public MineSubmarine(){
        super(63,19);
    }

    /** 重写step()移动 */
    public void step(){
        x+=speed; //x+(向右)
    }

    /** 重写getImage()获取图片 */
    public ImageIcon getImage(){
        if(isLive()){ //若活着的
            return Images.minesubm; //则返回水雷潜艇图片
        }else{ //若死了的
            return null; //不返回图片
        }
    }

    /** 重写getLife()得命 */
    public int getLife(){
        return 1; //打掉水雷潜艇，得1条命
    }
}













