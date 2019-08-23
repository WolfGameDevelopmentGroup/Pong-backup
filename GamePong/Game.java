/*
	 Game.java (Java)
	 
	 Objetivo: Estabelecer o jogo com os métodos de inicialização e renderização.
	 
	 Site: http://www.dirackslounge.online
	 
	 Versão 1.0
	 
	 Programador: Rodolfo Dirack 14/07/2019
	 
	 Email: rodolfo_profissional@hotmail.com
	 
	 Licença: GPL-3.0 <https://www.gnu.org/licenses/gpl-3.0.txt>.
*/

package GamePong;

import GamePong.Screen;
import GamePong.Player;
import GamePong.Enemy;
import GamePong.Ball;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements Runnable, KeyListener{

	public boolean isRunning=false;
	private int frame=0;
	public static Screen screen;
	public String TITLE;
	public int WIDTH, HEIGHT, SCALE;
	private BufferStrategy bs;

	private Player player;
	private Enemy enemy;
	public Ball ball;

	public Game(String TITLE, int WIDTH, int HEIGHT, int SCALE){
		this.TITLE = TITLE;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.SCALE = SCALE;
		this.screen = new Screen(TITLE, WIDTH, HEIGHT, SCALE);
		this.player = new Player(WIDTH,HEIGHT,SCALE);
		this.player.setPongPlayerSizeAndPosition();
		this.enemy = new Enemy(WIDTH,HEIGHT,SCALE);
		this.enemy.setPongPlayerSizeAndPosition();
		this.ball = new Ball(WIDTH,HEIGHT,SCALE);
		this.ball.setPongPlayerSizeAndPosition();
		this.screen.canvas.addKeyListener(this);
		screen.showScreen();
		this.screen.canvas.requestFocus();
	}

	public void updateGame(){
		player.updatePongPlayer();
		enemy.updateEnemy(this.ball.getBallXPosition());
		ball.updateBall(enemy.getRectangle(),player.getRectangle());

		if(this.ball.getBallYPosition() < 0 || this.ball.getBallYPosition() > (this.HEIGHT * this.SCALE)){
			this.ball.setPongPlayerSizeAndPosition();
		}
	}

	public void renderizeGame(){
		BufferStrategy bs = this.screen.canvas.getBufferStrategy();

		if (bs == null){
			this.screen.canvas.createBufferStrategy(3);
			return;
		}

		Graphics g = this.screen.canvas.getGraphics();
		g.setColor(Color.WHITE);
		this.screen.drawBackground(g);
		g.setColor(Color.BLUE);
		this.player.drawPongPlayer(g);
		g.setColor(Color.RED);
		this.enemy.drawPongPlayer(g);
		g.setColor(Color.BLACK);
		this.ball.drawPongPlayer(g);		
	}

	public synchronized void startGame(){
		this.isRunning = true;
		Thread thread = new Thread(this);
		thread.start();
	}

	public void run(){

		while(isRunning){
			frame++;
			updateGame();
			renderizeGame();
			try{
				Thread.sleep(1000/100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}

	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.right=true;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			player.left=true;
		}
	}

	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.right=false;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			player.left=false;
		}
	}

	public void keyTyped(KeyEvent e){}

}
