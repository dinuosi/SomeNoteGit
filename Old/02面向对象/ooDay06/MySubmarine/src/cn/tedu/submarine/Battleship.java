package cn.tedu.submarine;
import javax.swing.ImageIcon;
/** 战舰:是海洋对象 */
public class Battleship extends SeaObject {
    private int life; //命
    /** 构造方法 */
    public Battleship(){
        super(66,26,260,124,20);
        life = 5;
    }

    /** 重写move()移动 */
    public void move(){
        //搁置
    }

    /** 重写getImage()获取对象图片 */
    public ImageIcon getImage(){
        return Images.battleship; //返回战舰图片
    }
}

















