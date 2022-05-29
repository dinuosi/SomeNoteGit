package cn.tedu.submarine;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Graphics;
/** 海洋对象 */
public abstract class SeaObject {
    public static final int LIVE = 0; //活着的
    public static final int DEAD = 1; //死了的
    protected int state = LIVE; //当前状态(默认为活着的)

    protected int width;  //宽
    protected int height; //高
    protected int x;      //x坐标
    protected int y;      //y坐标
    protected int speed;  //移动速度
    /** 专门给侦察潜艇、鱼雷潜艇、水雷潜艇提供的 */
    //因为三种潜艇的宽/高是不同的，所以数据不能写死，需传参写活
    //因为三种潜艇的x/y/speed是相同的，所以数据直接写死，不需要传参
    public SeaObject(int width,int height){
        this.width = width;
        this.height = height;
        x = width; //负的潜艇的宽
        Random rand = new Random(); //随机数对象
        y = rand.nextInt(World.HEIGHT-height-150+1)+150; //150到(窗口高-潜艇高)之间的随机数
        speed = rand.nextInt(3)+1; //1到3之内的随机数
        /*
          y:150到460之间的随机数
          y = rand.nextInt(311);     //0到310之间的
          y = rand.nextInt(311)+150; //150到460之间的
          y = rand.nextInt(460-150+1)+150; //150到460之间的
          y = rand.nextInt(479-height-150+1)+150; //150到460之间的
         */
    }

    /** 专门给战舰、鱼雷、水雷、深水炸弹提供的 */
    //因为如上对象的宽/高/x/y/speed都是不同的，所以数据都不能写死，需传参写活
    public SeaObject(int width,int height,int x,int y,int speed){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    /** 海洋对象移动 */
    public abstract void move();

    /** 获取对象的图片 */
    public abstract ImageIcon getImage();

    /** 判断是否是活着的 */
    public boolean isLive(){
        return state==LIVE; //若当前状态为LIVE，表示为活着的，则返回true，否则返回false
    }

    /** 判断是否是死了的 */
    public boolean isDead(){
        return state==DEAD; //若当前状态为DEAD，表示为死了的，则返回true，否则返回false
    }

    /** 画对象 g:画笔 */
    public void paintImage(Graphics g){
        if(isLive()){ //若活着的
            this.getImage().paintIcon(null,g,this.x,this.y); //画对象
        }
    }

}


















