package cn.tedu.submarine;
import javax.swing.ImageIcon;
/** 图片类 */
public class Images {
//  公开的  静态的   图片类型    变量名
    public static ImageIcon battleship; //战舰
    public static ImageIcon obsersubm;  //侦察潜艇
    public static ImageIcon torpesubm;  //鱼雷潜艇
    public static ImageIcon minesubm;   //水雷潜艇
    public static ImageIcon bomb;       //深水炸弹
    public static ImageIcon torpedo;    //鱼雷
    public static ImageIcon mine;       //水雷

    public static ImageIcon sea;        //海洋图(运行图)
    public static ImageIcon start;      //启动图
    public static ImageIcon gameover;   //游戏结束图

    static{
        battleship = new ImageIcon("img/battleship.png");
        obsersubm = new ImageIcon("img/obsersubm.png");
        torpesubm = new ImageIcon("img/torpesubm.png");
        minesubm = new ImageIcon("img/minesubm.png");
        bomb = new ImageIcon("img/bomb.png");
        torpedo = new ImageIcon("img/torpedo.png");
        mine = new ImageIcon("img/mine.png");

        sea = new ImageIcon("img/sea.png");
        start = new ImageIcon("img/start.png");
        gameover = new ImageIcon("img/gameover.png");
    }

    public static void main(String[] args) {
        System.out.println(battleship.getImageLoadStatus()); //返回8表示读取成功
        System.out.println(obsersubm.getImageLoadStatus());
        System.out.println(torpesubm.getImageLoadStatus());
        System.out.println(minesubm.getImageLoadStatus());
        System.out.println(bomb.getImageLoadStatus());
        System.out.println(torpedo.getImageLoadStatus());
        System.out.println(mine.getImageLoadStatus());
        System.out.println(sea.getImageLoadStatus());
    }

}






















