package cn.tedu.submarine;
import javax.swing.JFrame;
import javax.swing.JPanel; //1.
/** 整个游戏世界 */
public class World extends JPanel { //2.
    public static void main(String[] args) {
        JFrame frame = new JFrame(); //3.
        World world = new World();
        world.setFocusable(true);
        frame.add(world);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(641+16, 479+39);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);




        SeaObject[] submarines = new SeaObject[5]; //潜艇数组
        submarines[0] = new ObserveSubmarine(); //向上造型
        submarines[1] = new ObserveSubmarine();
        submarines[2] = new TorpedoSubmarine();
        submarines[3] = new TorpedoSubmarine();
        submarines[4] = new MineSubmarine();
        for(int i=0;i<submarines.length;i++){ //遍历所有潜艇
            SeaObject s = submarines[i]; //获取每一个潜艇
            System.out.println(s.x+","+s.y); //输出每个潜艇的x和y坐标
            s.move(); //每个潜艇都移动
        }

        SeaObject[] thunders = new SeaObject[3]; //雷数组
        thunders[0] = new Torpedo(100,200); //向上造型
        thunders[1] = new Torpedo(200,300);
        thunders[2] = new Mine(245,356);
        for(int i=0;i<thunders.length;i++){
            SeaObject t = thunders[i];
            System.out.println(t.x+","+t.y);
            t.move();
        }

        Battleship s = new Battleship();

        Bomb[] bs = new Bomb[2];
        bs[0] = new Bomb(100,200);
        bs[1] = new Bomb(200,400);
        for(int i=0;i<bs.length;i++){
            //深水炸弹移动
            //深水炸弹击打三种潜艇
        }

    }
}






















