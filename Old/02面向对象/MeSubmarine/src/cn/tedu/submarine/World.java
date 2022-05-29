package cn.tedu.submarine;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.Arrays;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/** 整个游戏世界 */
public class World extends JPanel {
    public static final int WIDTH = 641;  //面板宽
    public static final int HEIGHT = 479; //面板高

    public static final int START = 0;     //启动状态
    public static final int RUNNING = 1;   //运行状态
    public static final int GAME_OVER = 2; //游戏结束状态
    private int state = START; //当前状态(默认为启动状态)

    //如下一堆为窗口中所显示的对象
    private Battleship ship = new Battleship(); //战舰
    private SeaObject[] submarines = {}; //潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)数组
    private SeaObject[] thunders = {}; //雷(鱼雷、水雷)数组
    private Bomb[] bombs = {}; //深水炸弹数组

    /** 生成潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)对象 */
    public SeaObject nextSubmarine(){
        Random rand = new Random(); //随机数对象
        int type = rand.nextInt(20); //0到19之间
        if(type<10){ //0到9时，返回侦察潜艇
            return new ObserveSubmarine();
        }else if(type<15){ //10到14时，返回鱼雷潜艇
            return new TorpedoSubmarine();
        }else{ //15到19时，返回水雷潜艇
            return new MineSubmarine();
        }
    }

    private int subEnterIndex = 0; //潜艇入场计数
    /** 潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)入场 */
    public void submarineEnterAction(){ //每10毫秒走一次
        subEnterIndex++; //每10毫秒增1
        if(subEnterIndex%40==0){ //每400(40*10)毫秒走一次
            SeaObject obj = nextSubmarine(); //获取潜艇对象
            submarines = Arrays.copyOf(submarines,submarines.length+1); //扩容
            submarines[submarines.length-1] = obj; //将潜艇对象添加到submarines最后一个元素上
        }
    }

    private int thunEnterIndex = 0; //雷入场计数
    /** 雷(鱼雷、水雷)入场 */
    public void thunderEnterAction(){ //每10毫秒走一次
        thunEnterIndex++; //每10毫秒增1
        if(thunEnterIndex%100==0){ //每1000(100*10)毫秒走一次
            for(int i=0;i<submarines.length;i++){ //遍历潜艇数组
                SeaObject obj = submarines[i].shootThunder(); //获取潜艇发射的雷
                if(obj!=null){ //若是雷
                    thunders = Arrays.copyOf(thunders,thunders.length+1); //扩容
                    thunders[thunders.length-1] = obj; //将雷添加到thunders的最后一个元素上
                }
            }
        }
    }

    /** 对象移动 */
    public void stepAction(){ //每10毫秒走一次
        for(int i=0;i<submarines.length;i++){ //遍历所有潜艇
            submarines[i].step(); //潜艇移动
        }
        for(int i=0;i<thunders.length;i++){ //遍历所有雷
            thunders[i].step(); //雷移动
        }
        for(int i=0;i<bombs.length;i++){ //遍历所有深水炸弹
            bombs[i].step(); //深水炸弹移动
        }
    }

    /** 深水炸弹入场 */
    public void bombEnterAction(){ //每10毫秒走一次
        Bomb obj = ship.shootBomb(); //获取深水炸弹对象
        bombs = Arrays.copyOf(bombs,bombs.length+1); //扩容
        bombs[bombs.length-1] = obj; //将深水炸弹添加到bombs最后一个元素上
    }

    /** 删除越界的对象----避免内存泄漏 */
    public void outOfBoundsAction(){ //每10毫秒走一次
        for(int i=0;i<submarines.length;i++){ //遍历所有潜艇
            if(submarines[i].isOutOfBounds() || submarines[i].isDead()){ //越界了或者死了的
                submarines[i] = submarines[submarines.length-1]; //将越界潜艇替换为最后一个元素
                submarines = Arrays.copyOf(submarines,submarines.length-1); //缩容
            }
        }

        for(int i=0;i<thunders.length;i++){ //遍历所有雷
            if(thunders[i].isOutOfBounds() || thunders[i].isDead()){ //越界了或者死了的
                thunders[i] = thunders[thunders.length-1]; //将越界雷替换为最后一个元素
                thunders = Arrays.copyOf(thunders,thunders.length-1); //缩容
            }
        }

        for(int i=0;i<bombs.length;i++){ //遍历所有深水炸弹
            if(bombs[i].isOutOfBounds() || bombs[i].isDead()){ //越界了或者死了的
                bombs[i] = bombs[bombs.length-1]; //将越界深水炸弹替换为最后一个元素
                bombs = Arrays.copyOf(bombs,bombs.length-1); //缩容
            }
        }
    }

    private int score = 0; //玩家得分
    /** 深水炸弹与潜艇的碰撞 */
    public void bombBangAction(){ //每10毫秒走一次
        for(int i=0;i<bombs.length;i++){ //遍历所有深水炸弹
            Bomb b = bombs[i]; //获取每个深水炸弹
            for(int j=0;j<submarines.length;j++){ //遍历所有潜艇
                SeaObject s = submarines[j]; //获取每个潜艇
                if(b.isLive() && s.isLive() && s.isHit(b)){ //若都活着并且还撞上了
                    s.goDead(); //潜艇去死
                    b.goDead(); //深水炸弹去死

                    if(s instanceof EnemyScore){ //若能得分
                        EnemyScore es = (EnemyScore)s; //将被撞潜艇强转为得分接口
                        score+=es.getScore(); //玩家得分
                    }
                    if(s instanceof EnemyLife){ //若能得命
                        EnemyLife el = (EnemyLife)s; //将被撞潜艇强转为得命接口
                        int life = el.getLife(); //获取所得的命数
                        ship.addLife(life); //战舰得命
                    }
                }
            }
        }
    }

    /** 雷与战舰的碰撞 */
    public void thunderBangAction(){ //每10毫秒走一次
        for(int i=0;i<thunders.length;i++){ //遍历雷数组
            SeaObject t = thunders[i]; //获取每一个雷
            if(t.isLive() && ship.isLive() && ship.isHit(t)){ //若都活着并且还撞上了
                t.goDead(); //雷去死
                ship.subtractLife(); //战舰减命
            }
        }
    }

    /** 检测游戏结束 */
    public void checkGameOverAction(){ //每10毫秒走一次
        if(ship.getLife()<=0){ //若战舰命数<=0，表示游戏结束了
            state = GAME_OVER; //将当前状态修改为GAME_OVER游戏结束状态
        }
    }

    /** 启动程序的执行 */
    public void action(){ //测试代码
        KeyAdapter k = new KeyAdapter() { //键盘侦听器
            /** 重写keyPressed()键盘按下事件 */
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){ //按下空格键
                    switch(state){ //根据不同状态做不同的处理
                        case START:          //启动状态时
                            state = RUNNING; //修改为运行状态
                            break;
                        case RUNNING:          //运行状态时
                            bombEnterAction(); //深水炸弹入场
                            break;
                        case GAME_OVER:    //游戏结束状态时
                            score = 0; //清理现场
                            ship = new Battleship();
                            submarines = new SeaObject[0];
                            thunders = new SeaObject[0];
                            bombs = new Bomb[0];
                            state = START; //修改为启动状态
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){ //按下左箭头键
                    ship.moveLeft(); //战舰左移
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){ //按下右箭头键
                    ship.moveRight(); //战舰右移
                }
            }
        };
        this.addKeyListener(k); //添加键盘侦听器

        Timer timer = new Timer(); //定时器对象
        int interval = 10; //定时间隔(以毫秒为单位)
        timer.schedule(new TimerTask() {
            public void run() { //定时干的事(每10毫秒走一次)
                if(state==RUNNING){ //仅在运行状态时执行
                    submarineEnterAction(); //潜艇入场
                    thunderEnterAction();   //雷入场
                    stepAction();           //对象移动
                    outOfBoundsAction();    //删除越界的海洋对象
                    bombBangAction();       //深水炸弹与潜艇的碰撞
                    thunderBangAction();    //雷与战舰的碰撞
                    checkGameOverAction();  //检测游戏结束
                }
                repaint(); //重画(重新调用paint()方法)
            }
        }, interval, interval); //定时计划
    }

    /** 重写paint()画  g:画笔 */
    public void paint(Graphics g){ //每10毫秒走一次
        switch(state){ //根据当前状态做不同的处理
            case START: //启动状态时画启动图
                Images.start.paintIcon(null,g,0,0);
                break;
            case GAME_OVER: //游戏结束状态时画游戏结束图
                Images.gameover.paintIcon(null,g,0,0);
                break;
            case RUNNING: //运行状态时画海洋、对象、分和命
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
                g.drawString("SCORE: "+score,200,50);
                g.drawString("LIFE: "+ship.getLife(),400,50);
        }



    }

    /** 画窗口 */
    public void paintWorld(){
        JFrame frame = new JFrame();
        this.setFocusable(true);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH+16, HEIGHT+39);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); //1)设置窗口可见  2)尽快调用paint()方法
    }

    public static void main(String[] args) {
        World w = new World();
        w.paintWorld(); //画窗口
        w.action(); //启动程序的执行
    }
}











/*
 1.问:为何要将那一堆引用设计在main的外面?
   答:若将引用设计在main中，意味着引用只能在main中使用，
      而World类中后期会设计很多很多的方法，都需要用到那一堆引用，
      所以将引用设计在main的外面，以此来扩大作用范围
 2.问:为何要单独创建action()方法来测试?
   答:因为main方法是static的，而在static的方法中是无法访问那一堆引用的，
      所以单独创建一个非static的action()方法来测试
      ----关于static的内容，面向对象第五天详细讲解
 3.问:为何要先创建World对象，再调用action()方法?
   答:因为main方法是static的，在static方法中不能直接调用非static的方法
     所以先创建当前World类对象，再调用action()方法
     ----关于static的内容，面向对象第五天详细讲解
 */






















