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

import java.util.Random;
import java.awt.Rectangle;

public class Ball extends PongPlayer{

	public double v0=2;
	private double dx;
	private double dy;
	private Rectangle bounds;
	public int enemyScore=0;
	public int playerScore=0;

	public Ball(int SCREEN_WIDTH, int SCREEN_HEIGHT, int SCALE){
		this.SCREEN_WIDTH = SCREEN_WIDTH*SCALE;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT*SCALE;
		this.SCALE = SCALE;
		this.pongPlayerSpeed=this.v0;

		this.giveBallAnStartingAngle();

		this.setPongPlayerSize(5*this.SCALE,5*this.SCALE);
		this.setPongPlayerPosition(this.SCREEN_WIDTH/2,this.SCREEN_HEIGHT/2);
	}

	public void giveBallAnStartingAngle(){

		int numAleatorio = new Random().nextInt(11);
		int angle = 30;

		if(numAleatorio <= 5){
			angle = 210;
		}

		int dangle = new Random().nextInt(120);
		angle = angle+dangle;

		numAleatorio = new Random().nextInt(11);

		this.dx = Math.cos(Math.toRadians((double)angle));
		this.dy = Math.sin(Math.toRadians((double)angle));

		System.out.println(dx+" "+dy);

	}

	public int getBallXPosition(){
		return (int)this.position_x;
	}

	public int getBallYPosition(){
		return (int)this.position_y;
	}

	private void giveBallRandomColisionAngle(){			
			this.dx = Math.cos(Math.toRadians(new Random().nextInt(80)));
			this.dy = Math.sin(Math.toRadians(new Random().nextInt(80)));
	}

	public void updatePongPlayer(Rectangle boundsEnemy, Rectangle boundsPlayer){

		this.bounds = new Rectangle((int)(this.position_x),(int)(this.position_y),this.width,this.height);

		if(this.position_x+this.width > this.SCREEN_WIDTH){
			this.dx *= -1.0;
		}else if(this.position_x <= 0){
			this.dx *= -1.0;
		}

		if(this.bounds.intersects(boundsEnemy)){
			Sound.ballColision.play();
			this.pongPlayerSpeed += 0.1;
			this.giveBallRandomColisionAngle();
			if(dy < 0)		
				this.dy *= -1;
		}else if(this.bounds.intersects(boundsPlayer)){
			Sound.ballColision.play();
			this.pongPlayerSpeed += 0.1;
			this.giveBallRandomColisionAngle();
			if(dy > 0)		
				this.dy *= -1;
		}

		this.position_x += this.dx * this.pongPlayerSpeed;
		this.position_y += this.dy * this.pongPlayerSpeed;

	}

}
