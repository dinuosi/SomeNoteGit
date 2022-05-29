package cn.tedu.submarine;
import java.util.Random;
/** 海洋对象 */
public class SeaObject {
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
        x = -width; //负的潜艇的宽
        Random rand = new Random(); //随机数对象
        y = rand.nextInt(479-height-150+1)+150; //150到(窗口高-潜艇高)之间的随机数
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
    public void move(){ //---------------------------它的最终处理周三讲
        System.out.println("海洋对象移动");
    }
}


















