package cn.tedu.submarine;
import javax.swing.ImageIcon;
/** 侦察潜艇:是海洋对象，也能得分 */
public class ObserveSubmarine extends SeaObject implements EnemyScore {
    /** 构造方法 */
    public ObserveSubmarine(){
        super(63,19);
    }

    /** 重写step()移动 */
    public void step(){
        x+=speed; //x+(向右)
    }

    /** 重写getImage()获取图片 */
    public ImageIcon getImage(){
        if(isLive()){ //若活着的
            return Images.obsersubm; //则返回侦察潜艇图片
        }else{ //若死了的
            return null; //不返回图片
        }
    }

    /** 重写getScore()得分 */
    public int getScore(){
        return 10; //打掉侦察潜艇，玩家得10分
    }
}











