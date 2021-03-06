package com.tarena.shoot;

import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShootGame extends JPanel {
	public static final int WIDTH = 400; // 面板宽
	public static final int HEIGHT = 654; // 面板高
	/** 游戏的当前状态: START RUNNING PAUSE GAME_OVER */
	private int state;
	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;


	private int score = 0; // 得分
	private Timer timer; // 定时器

	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	public static BufferedImage bullet;
	public static BufferedImage airplane;
	public static BufferedImage[] airplaneEmber=new BufferedImage[4];
	public static BufferedImage bee;
	public static BufferedImage[] beeEmber=new BufferedImage[4];;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage[] heroEmber=new BufferedImage[4];;
	public static BufferedImage bigPlane;
	public static BufferedImage[] bigPlaneEmber=new BufferedImage[4];;

	private FlyingObject[] flyings = {}; // 敌机数组
	private Bullet[] bullets = {}; // 子弹数组
	private Hero hero = new Hero(); // 英雄机
	private Ember[] embers = {}; // 灰烬

	static {// 静态代码块
		try {
			background = ImageIO.read(ShootGame.class
					.getResource("background.png"));
			bigPlane = ImageIO
					.read(ShootGame.class.getResource("bigplane.png"));
			airplane = ImageIO
					.read(ShootGame.class.getResource("airplane.png"));
			bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			gameover = ImageIO
					.read(ShootGame.class.getResource("gameover.png"));
			start = ImageIO
					.read(ShootGame.class.getResource("start.png"));
			for(int i=0; i<4; i++){
				beeEmber[i] = ImageIO.read(
						ShootGame.class.getResource("bee_ember"+i+".png"));
				airplaneEmber[i] = ImageIO.read(
						ShootGame.class.getResource("airplane_ember"+i+".png"));
				bigPlaneEmber[i] = ImageIO.read(
						ShootGame.class.getResource("bigplane_ember"+i+".png"));
				heroEmber[i] = ImageIO.read(
						ShootGame.class.getResource("hero_ember"+i+".png"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null); // 画背景图
		paintEmber(g);
		paintHero(g); // 画英雄机
		paintBullets(g); // 画子弹
		paintFlyingObjects(g); // 画飞行物
		paintScore(g); // 画分数
		paintState(g); // 画游戏状态
	}

	/** 画英雄机 */
	public void paintHero(Graphics g) {
		g.drawImage(hero.getImage(), hero.getX(), hero.getY(), null);
	}
	
	public void paintEmber(Graphics g) {
		for (int i = 0; i < embers.length; i++) {
			Ember e = embers[i];
			g.drawImage(e.getImage(), e.getX(), e.getY(), null);
		}
	}

	/** 画子弹 */
	public void paintBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if(! b.isBomb())
				g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2, b.getY(),
					null);
		}
	}

	/** 画飞行物 */
	public void paintFlyingObjects(Graphics g) {
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			g.drawImage(f.getImage(), f.getX(), f.getY(), null);
		}
	}

	/** 画分数 */
	public void paintScore(@NotNull Graphics g) {
		int x = 10;
		int y = 25;
		Font font = new Font(Font.SANS_SERIF,Font.BOLD, 14);
		g.setColor(new Color(0x3A3B3B));
		g.setFont(font); // 设置字体
		g.drawString("SCORE:" + score, x, y); // 画分数
		y+=20;
		g.drawString("LIFE:" + hero.getLife(), x, y);
	}

	/** 画游戏状态 */
	public void paintState(Graphics g) {
		switch (state) {
		case START:
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Shoot Game");
		ShootGame game = new ShootGame(); // 面板对象
		frame.add(game); // 将面板添加到JFrame中
		frame.setSize(WIDTH, HEIGHT); // 大小
		frame.setAlwaysOnTop(true); // 其总在最上
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默认关闭操作
		frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // 设置窗体的图标
		frame.setLocationRelativeTo(null); // 设置窗体初始位置
		frame.setVisible(true); // 尽快调用paint

		game.action(); // 启动执行
	}

	public void action() { // 启动执行代码
		// 鼠标监听事件
		MouseAdapter l = new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) { // 鼠标移动
				if (state == RUNNING) { // 运行时移动英雄机
					int x = e.getX();
					int y = e.getY();
					hero.moveTo(x, y);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) { // 鼠标进入
				if (state == PAUSE) { // 暂停时运行
					state = RUNNING;
				}
			}

			@Override
			public void mouseExited(MouseEvent e) { // 鼠标退出
				if (state != GAME_OVER) {
					state = PAUSE; // 游戏未结束，则设置其为暂停
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) { // 鼠标点击
				switch (state) {
				case START:
					state = RUNNING;
					break;
				case GAME_OVER: // 游戏结束，清理现场
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					hero = new Hero();
					score = 0;
					state = START;
					break;
				}
			}
		};
		this.addMouseListener(l); // 处理鼠标点击操作
		this.addMouseMotionListener(l); // 处理鼠标滑动操作

		timer = new Timer(); // 主流程控制
		// 时间间隔(毫秒)
		int intervel = 1000 / 100;
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (state == RUNNING) {
					enterAction(); // 飞行物入场
					stepAction(); // 走一步
					shootAction(); // 射击
					bangAction(); // 子弹打飞行物
					outOfBoundsAction(); // 删除越界飞行物及子弹
					checkGameOverAction(); // 检查游戏结束
					emberAction();
				}
				repaint(); // 重绘，调用paint()方法
			}


		}, intervel, intervel);
	}

	private void emberAction() {
		Ember[] live = new Ember[embers.length];
		int index = 0;
		for (int i = 0; i < embers.length; i++) {
			Ember ember = embers[i];
			if(! ember.burnDown()){
				live[index++]=ember;
			}
		}
		embers = Arrays.copyOf(live, index);
	}
	int flyEnteredIndex = 0; // 飞行物入场计数

	/** 飞行物入场 */
	public void enterAction() {
		flyEnteredIndex++;
		if (flyEnteredIndex % 40 == 0) { // 300毫秒--10*30
			FlyingObject obj = nextOne(); // 随机生成一个飞行物
			flyings = Arrays.copyOf(flyings, flyings.length + 1);
			flyings[flyings.length - 1] = obj;
		}
	}

	
	public void stepAction() {
		/** 飞行物走一步 */
		for (int i = 0; i < flyings.length; i++) { // 飞行物走一步
			FlyingObject f = flyings[i];
			f.step();
		}

		/** 子弹走一步 */
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			b.step();
		}
		hero.step();
	}

	int shootIndex = 0; // 射击计数

	/** 射击 */
	public void shootAction() {
		shootIndex++;
		if (shootIndex % 30 == 0) { // 100毫秒发一颗
			Bullet[] bs = hero.shoot(); // 英雄打出子弹
			bullets = Arrays.copyOf(bullets, bullets.length + bs.length); // 扩容
			System.arraycopy(bs, 0, bullets, bullets.length - bs.length,
					bs.length); // 追加数组
		}
	}

	/** 子弹与飞行物碰撞检测 */
	public void bangAction() {
		for (int i = 0; i < bullets.length; i++) { // 遍历所有子弹
			Bullet b = bullets[i];
			bang(b);
		}
	}

	/** 删除越界飞行物及子弹 */
	public void outOfBoundsAction() {
		int index = 0;
		FlyingObject[] flyingLives = new FlyingObject[flyings.length]; // 活着的飞行物
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject f = flyings[i];
			if (!f.outOfBounds()) {
				flyingLives[index++] = f; // 不越界的留着
			}
		}
		flyings = Arrays.copyOf(flyingLives, index); // 将不越界的飞行物都留着

		index = 0; // 重置为0
		Bullet[] bulletLives = new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet b = bullets[i];
			if (!b.outOfBounds()) {
				bulletLives[index++] = b;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index); // 将不越界的子弹留着
	}

	/** 检查游戏结束 */
	public void checkGameOverAction() {
		if (isGameOver()) {
			state = GAME_OVER; // 改变状态
		}
	}

	/** 检查游戏是否结束 */
	public boolean isGameOver() {
		int index = -1;
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if (hero.hit(obj)) { // 检查英雄机与飞行物是否碰撞
				hero.subtractLife();
				hero.setDoubleFire(0);
				index = i;
				
				Ember ember = new Ember(hero);
				embers = Arrays.copyOf(embers, embers.length+1);
				embers[embers.length-1]=ember;
			}
		}
		if(index!=-1){
			FlyingObject t = flyings[index];
			flyings[index] = flyings[flyings.length-1];
			flyings[flyings.length-1] = t;
			flyings = Arrays.copyOf(flyings, flyings.length-1);
			
			Ember ember = new Ember(t);
			embers = Arrays.copyOf(embers, embers.length+1);
			embers[embers.length-1]=ember;
		}
		return  hero.getLife() <= 0;
	}

	/** 子弹和飞行物之间的碰撞检查 */
	public void bang(Bullet bullet) {
		int index = -1; // 击中的飞行物索引
		for (int i = 0; i < flyings.length; i++) {
			FlyingObject obj = flyings[i];
			if (obj.shootBy(bullet)) { // 判断是否击中
				index = i; // 记录被击中的飞行物的索引
				break;
			}
		}
		if (index != -1) { // 有击中的飞行物
			FlyingObject one = flyings[index]; // 记录被击中的飞行物

			FlyingObject temp = flyings[index]; // 被击中的飞行物与最后一个飞行物交换
			flyings[index] = flyings[flyings.length - 1];
			flyings[flyings.length - 1] = temp;

			flyings = Arrays.copyOf(flyings, flyings.length - 1); // 删除最后一个飞行物(即被击中的)

			// 检查one的类型 如果是敌人, 就算分
			if (one instanceof Enemy) { // 检查类型，是敌人，则加分
				Enemy e = (Enemy) one; // 强制类型转换
				score += e.getScore(); // 加分
			} 
			
			if (one instanceof Award) { // 若为奖励，设置奖励
				Award a = (Award) one;
				int type = a.getType(); // 获取奖励类型
				switch (type) {
				case Award.DOUBLE_FIRE:
					hero.addDoubleFire(); // 设置双倍火力
					break;
				case Award.LIFE:
					hero.addLife(); // 设置加命
					break;
				}
			}
			
			//飞行物变成灰烬
			Ember ember = new Ember(one);
			embers = Arrays.copyOf(embers, embers.length+1);
			embers[embers.length-1]=ember;
		}
	}

	/**
	 * 随机生成飞行物
	 * 
	 * @return 飞行物对象
	 */
	public static FlyingObject nextOne() {
		Random random = new Random();
		int type = random.nextInt(20); // [0,4)
		if (type==0) {
			return new Bee();
		}else if(type<=2){
			return new BigPlane();
		}else{
			return new Airplane();
		}
	}
}
