package cn.tedu.submarine;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
/** 整个游戏世界 */
public class World extends JPanel {
    public static final int WIDTH = 641;
    public static final int HEIGHT = 479;

    //测试数据
    private Battleship ship = new Battleship(); //战舰
    private SeaObject[] submarines = {
            new ObserveSubmarine(),
            new TorpedoSubmarine(),
            new MineSubmarine()
    }; //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)数组
    private SeaObject[] thunders = {
            new Torpedo(120,50),
            new Mine(260,100)
    }; //雷(鱼雷、水雷)数组
    private Bomb[] bombs = {
            new Bomb(200,190)
    }; //深水炸弹数组

    /** 重写paint()画 g:画笔 */
    public void paint(Graphics g){
        Images.sea.paintIcon(null,g,0,0); //画海洋图
        ship.paintImage(g); //画战舰
        for(int i=0;i<submarines.length;i++){ //遍历所有潜艇
            submarines[i].paintImage(g); //画潜艇
        }
        for(int i=0;i<thunders.length;i++){ //遍历所有雷
            thunders[i].paintImage(g); //画雷
        }
        for(int i=0;i<bombs.length;i++){ //遍历所有深水炸弹
            bombs[i].paintImage(g); //画深水炸弹
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        World world = new World();
        world.setFocusable(true);
        frame.add(world);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(World.WIDTH+16, World.HEIGHT+39);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); //1)设置窗口可见  2)尽快调用paint()方法
    }
}






















