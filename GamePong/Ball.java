/*
	 Ball.java (Java)
	 
	 Objetivo: Define a bola do Jogo Pong.
	 
	 Site: http://www.dirackslounge.online
	 
	 Versão 1.0
	 
	 Programador: Rodolfo Dirack 23/08/2019
	 
	 Email: rodolfo_profissional@hotmail.com
	 
	 Licença: GPL-3.0 <https://www.gnu.org/licenses/gpl-3.0.txt>.
*/

package GamePong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

public class Ball{

	private int SCREEN_WIDTH;
	private int SCREEN_HEIGHT;
	private int SCALE;
	private int ball_width;
	private int ball_height;
	private double ball_x;
	private double ball_y;
	private boolean right;
	private boolean left;
	private int numero;
	private double VELOCITY=4;
	private double dx;
	private double dy;
	private Rectangle bounds;

	public Ball(int SCREEN_WIDTH, int SCREEN_HEIGHT, int SCALE){
		this.SCREEN_WIDTH = SCREEN_WIDTH*SCALE;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT*SCALE;
		this.SCALE = SCALE;
	}

	public int getBallXPosition(){
		return (int)this.ball_x;
	}

	public int getBallYPosition(){
		return (int)this.ball_y;
	}

	public void setPongPlayerSizeAndPosition(){

		int angle = new Random().nextInt(80);

		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
		this.ball_width=5*this.SCALE;
		this.ball_height=5*this.SCALE;
		this.ball_x = (this.SCREEN_WIDTH/2);
		this.ball_y = (this.SCREEN_HEIGHT/2);
	}

	public void drawPongPlayer(Graphics g){
		g.fillRect((int)this.ball_x,(int)this.ball_y,this.ball_width,this.ball_height);
	}

	public void updateBall(Rectangle boundsEnemy, Rectangle boundsPlayer){

		this.bounds = new Rectangle((int)(this.ball_x),(int)(this.ball_y),this.ball_width,this.ball_height);

		if(this.ball_x+this.ball_width > this.SCREEN_WIDTH){
			this.dx *= -1;
		}else if(this.ball_x <= 0){
			this.dx *= -1;
		}

		if(this.bounds.intersects(boundsEnemy)){
			this.dy = Math.sin(Math.toRadians(new Random().nextInt(80)));
			if(dy < 0)		
				this.dy *= -1;
		}else if(this.bounds.intersects(boundsPlayer)){
			this.dy = Math.sin(Math.toRadians(new Random().nextInt(80)));
			if(dy > 0)		
				this.dy *= -1;
		}

		this.ball_x += this.dx * this.VELOCITY;
		this.ball_y += this.dy * this.VELOCITY;
	}

}
