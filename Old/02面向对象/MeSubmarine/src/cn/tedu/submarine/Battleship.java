package cn.tedu.submarine;
import javax.swing.ImageIcon;
/** 战舰:是海洋对象 */
public class Battleship extends SeaObject {
    private int life;   //命
    /** 构造方法 */
    public Battleship(){
        super(66,26,270,124,20);
        life = 5;
    }

    /** 重写step()移动 */
    public void step(){

    }

    /** 重写getImage()获取图片 */
    public ImageIcon getImage(){
        return Images.battleship; //返回战舰图片
    }

    /** 发射深水炸弹---生成深水炸弹对象 */
    public Bomb shootBomb(){
        return new Bomb(this.x,this.y); //深水炸弹的坐标与战舰的坐标一致
    }

    /** 战舰左移 */
    public void moveLeft(){
        x-=speed; //x-(向左)
    }
    /** 战舰右移 */
    public void moveRight(){
        x+=speed; //x+(向右)
    }

    /** 战舰增命 */
    public void addLife(int life){
        this.life+=life; //命数增life
    }

    /** 获取战舰命数 */
    public int getLife(){
        return life; //返回命数
    }

    /** 战舰减命 */
    public void subtractLife(){
        life--; //命数减1
    }

}















