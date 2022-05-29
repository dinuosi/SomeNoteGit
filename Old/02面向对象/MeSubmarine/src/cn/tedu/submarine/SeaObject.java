package cn.tedu.submarine;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;
/** 海洋对象 */
public abstract class SeaObject{
    public static final int LIVE = 0; //活着的
    public static final int DEAD = 1; //死了的
    protected int state = LIVE; //当前状态(默认为活着的)

    protected int width;  //宽
    protected int height; //高
    protected int x;      //x坐标
    protected int y;      //y坐标
    protected int speed;  //移动速度

    /** 专门给侦察潜艇、鱼雷潜艇、水雷潜艇提供的 */
    //因为三种潜艇的宽和高是不一样的，所以数据不能写死，需传参写活
    //因为三种潜艇的x/y/speed都是一样的，所以数据写死
    public SeaObject(int width,int height){
        this.width = width;
        this.height = height;
        x = -width;
        Random rand = new Random();
        y = rand.nextInt(World.HEIGHT-height-150)+150; //150到(窗口高-潜艇高)之间的随机数
        speed = rand.nextInt(3)+1; //1到3之间的随机数
    }

    /** 专门给战舰、深水炸弹、水雷、鱼雷提供的 */
    //因为这四种对象的宽/高/x/y/speed都是不一样的，所以数据不能写死，需传参写活
    public SeaObject(int width,int height,int x,int y,int speed){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    /** 移动 */
    public abstract void step();

    /** 获取对象的图片 */
    public abstract ImageIcon getImage();

    /** 判断对象是否是活着的 */
    public boolean isLive(){
        return state==LIVE; //若当前状态为LIVE，表示为活着的，返回true，否则返回false
    }
    /** 判断对象是否是死了的 */
    public boolean isDead(){
        return state==DEAD; //若当前状态为DEAD，表示为死了的，返回true，否则返回false
    }

    /** 画图片 g:画笔 */
    public void paintImage(Graphics g){
        if(isLive()){ //若活着的
            this.getImage().paintIcon(null,g,this.x,this.y); //画对象
        }
    }

    /** 潜艇发射雷----生成雷对象 this:代表当前潜艇 */
    public SeaObject shootThunder(){
        int x = this.x+this.width; //x:潜艇的x+潜艇的宽
        int y = this.y-5;          //y:潜艇的y-5
        if(this instanceof TorpedoSubmarine){ //若当前对象为鱼雷潜艇
            return new Torpedo(x,y); //则返回鱼雷对象
        }else if(this instanceof MineSubmarine){ //若当前对象为水雷潜艇
            return new Mine(x,y); //则返回水雷对象
        }else{ //若当前对象为侦察潜艇
            return null; //则不发雷
        }
    }

    /** 检测潜艇越界 */
    public boolean isOutOfBounds(){
        return this.x>=World.WIDTH; //潜艇的x>=窗口的宽，即为越界了
    }

    /** 检测碰撞 */
    public boolean isHit(SeaObject other){
        //假设this指代潜艇，other指代炸弹
        int x1 = this.x-other.width;  //x1:潜艇的x-炸弹的宽
        int x2 = this.x+this.width;   //x2:潜艇的x+潜艇的宽
        int y1 = this.y-other.height; //y1:潜艇的y-炸弹的高
        int y2 = this.y+this.height;  //y2:潜艇的y+潜艇的高
        int x = other.x; //x:炸弹的x
        int y = other.y; //y:炸弹的y

        return x>=x1 && x<=x2
               &&
               y>=y1 && y<=y2; //x在x1与x2之间，并且，y在y1与y2之间，即为撞上了
    }

    /** 飞行物去死 */
    public void goDead(){
        state = DEAD; //将状态修改为DEAD死了的
    }
}














