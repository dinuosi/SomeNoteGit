package cn.tedu.submarine;
import javax.swing.ImageIcon;
/** 鱼雷潜艇:是海洋对象,也能得分 */
public class TorpedoSubmarine extends SeaObject implements EnemyScore {
    /** 构造方法 */
    public TorpedoSubmarine(){
        super(64,20);
    }

    /** 重写step()移动 */
    public void step(){
        x+=speed; //x+(向右)
    }

    /** 重写getImage()获取图片 */
    public ImageIcon getImage(){
        if(isLive()){ //若活着的
            return Images.torpesubm; //则返回鱼雷潜艇图片
        }else{ //若死了的
            return null; //不返回图片
        }
    }

    /** 重写getScore()得分 */
    public int getScore(){
        return 40; //打掉鱼雷潜艇，玩家得40分
    }
}















