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
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import GamePong.Sound;

public class Game implements Runnable, KeyListener{

	public boolean isRunning=false;
	public boolean gameOver=true;
	private boolean pausedGame=true;
	private int frame=0;
	public static Screen screen;
	public String TITLE;
	public int WIDTH, HEIGHT, SCALE;
	private BufferStrategy bs;
	private BufferedImage layer;
	private Player player;
	private Enemy enemy;
	public Ball ball;
	private int fase=0;

	public Game(String TITLE, int WIDTH, int HEIGHT, int SCALE){
		this.TITLE = TITLE;
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.SCALE = SCALE;
		this.layer = new BufferedImage(this.WIDTH,this.HEIGHT,BufferedImage.TYPE_INT_RGB);
		this.screen = new Screen(TITLE, WIDTH, HEIGHT, SCALE);
		this.player = new Player(WIDTH,HEIGHT,SCALE);
		this.enemy = new Enemy(WIDTH,HEIGHT,SCALE);
		this.ball = new Ball(WIDTH,HEIGHT,SCALE);
		this.screen.canvas.addKeyListener(this);
		screen.showScreen();
		this.screen.canvas.requestFocus();
		this.screen.canvas.createBufferStrategy(3);
		this.bs = this.screen.canvas.getBufferStrategy();
	}

	private void restartGameAfterAPoint(){
		this.ball.setPongPlayerPosition(this.ball.SCREEN_WIDTH/2,this.ball.SCREEN_HEIGHT/2);
		this.ball.pongPlayerSpeed=this.ball.v0;
		this.ball.giveBallAnStartingAngle();
	}

	public void updateGame(){

		if(pausedGame==false){
			player.updatePongPlayer();
			enemy.updatePongPlayer(this.ball.getBallXPosition());
			ball.updatePongPlayer(enemy.getPongPlayerRectangle(),player.getPongPlayerRectangle());
		}

		if(this.ball.getBallYPosition() <= 0 ){
			this.restartGameAfterAPoint();
			this.player.givePongPlayerAPoint();
			Sound.playerPoint.play();
		}else if(this.ball.getBallYPosition() >= (this.HEIGHT * this.SCALE)){
			this.restartGameAfterAPoint();
			this.enemy.givePongPlayerAPoint();
			Sound.enemyPoint.play();
		}

		if(this.enemy.score > 3 || this.fase > 5){
			this.gameOver = true;
		}else if(this.player.score > 3){
			this.fase++;
			this.enemy.pongPlayerSpeed += 0.2;
			this.player.score = 0;
			this.enemy.score = 0;
		}
	}

	private void drawFrame(Graphics g, Color backgroundColor, Color elementsColor){
		g.setColor(backgroundColor);
		this.screen.drawBackground(g);
		g.setColor(elementsColor);
		this.player.drawPongPlayer(g);
		this.enemy.drawPongPlayer(g);
		this.ball.drawPongPlayer(g);
		this.screen.drawScore(g,this.enemy.score,this.player.score);
		g = this.bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE,HEIGHT*SCALE,null);
	}

	private void drawGameOverFrame(Graphics g){
	
		if(this.fase == 0){
			g.setColor(Color.BLACK);
			this.screen.drawBackground(g);
			g.setColor(Color.WHITE);
			this.player.drawPongPlayer(g);
			this.enemy.drawPongPlayer(g);
			this.ball.drawPongPlayer(g);
			g.drawString("Pong Player",(this.ball.SCREEN_WIDTH/2)-30,(this.SCALE*this.HEIGHT)/2-50);
			g.drawString("Controls:",20,(this.SCALE*this.HEIGHT)/2+30);
			g.drawString("     Down    to pause a game",30,(this.SCALE*this.HEIGHT)/2+50);
			g.drawString("     Left       to move left",30,(this.SCALE*this.HEIGHT)/2+65);
			g.drawString("     Right     to move right",30,(this.SCALE*this.HEIGHT)/2+80);
			g.drawString("     UP         to start a new game",30,(this.SCALE*this.HEIGHT)/2+95);
		}else if(this.fase > 5){
			g.setColor(Color.WHITE);
			this.screen.drawBackground(g);
			g.setColor(Color.BLUE);
			g.drawString("YOU WIN!",(this.ball.SCREEN_WIDTH/2)-30,(this.SCALE*this.HEIGHT)/2-10);
			g.drawString("You are the new Pong Player Master! :)",2,(this.SCALE*this.HEIGHT)/2);
			g.drawString("Press UP key to start a new game",20,(this.SCALE*this.HEIGHT)/2+30);
		}else{
			g.setColor(Color.RED);
			g.drawString("GAME OVER",(this.ball.SCREEN_WIDTH/2)-30,(this.SCALE*this.HEIGHT)/2-10);
			g.drawString("Press UP key to start a new game",20,(this.SCALE*this.HEIGHT)/2+30);
		}

		this.ball.setPongPlayerPosition(this.ball.SCREEN_WIDTH/2,this.ball.SCREEN_HEIGHT/2);
		g = this.bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE,HEIGHT*SCALE,null);
	}

	public void renderizeGame(){
		Graphics g = this.layer.getGraphics();
		if(!(gameOver)){

			//TODO: Modificar a forma como passar as cores
			// A primeira é a cor do background
			// A segunda é a cor dos elementos da tela (Player, Enemy e Ball)
			switch(this.fase){
			case 1:
				this.drawFrame(g, Color.BLACK, Color.WHITE);
			break;
			case 2:
				this.drawFrame(g, Color.WHITE, Color.BLACK);
			break;
			case 3:
				this.drawFrame(g, Color.BLUE, Color.ORANGE);
			break;
			case 4:
				this.drawFrame(g, Color.GREEN, Color.DARK_GRAY);
			break;
			case 5:
				this.drawFrame(g, Color.PINK, Color.WHITE);
			break;
			}
		}else{
			this.drawGameOverFrame(g);
		}
		this.bs.show();		
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
				Thread.sleep(1000/60);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}

	}

	public void keyPressed(KeyEvent e){
		if(pausedGame == false && e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.moveRight=true;
		}else if(pausedGame == false && e.getKeyCode()==KeyEvent.VK_LEFT){
			player.moveLeft=true;
		}else if(e.getKeyCode() == KeyEvent.VK_UP){
			if(this.fase == 0 || this.fase > 5 || this.gameOver == true){
				this.enemy.score=0;
				this.player.score=0;
				this.gameOver=false;
				this.fase=1;
				this.enemy.pongPlayerSpeed=1.5;
				this.ball.pongPlayerSpeed=this.ball.v0;
				this.pausedGame=false;
			}
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			if(this.pausedGame == false){
				this.pausedGame=true;
			}else{
				this.pausedGame=false;
			}
		}
	}

	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.moveRight=false;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			player.moveLeft=false;
		}
	}

	public void keyTyped(KeyEvent e){}

}
