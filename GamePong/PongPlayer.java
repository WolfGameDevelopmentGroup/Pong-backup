/*
	 PongPlayer.java (Java)
	 
	 Objetivo: Superclasse para as classes Player, Enemy e Ball.
	 
	 Site: http://www.dirackslounge.online
	 
	 Versão 1.0
	 
	 Programador: Rodolfo Dirack 14/07/2019
	 
	 Email: rodolfo_profissional@hotmail.com
	 
	 Licença: GPL-3.0 <https://www.gnu.org/licenses/gpl-3.0.txt>.
*/

package GamePong;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PongPlayer{

	protected int SCREEN_WIDTH;
	protected int SCREEN_HEIGHT;
	protected int SCALE;
	protected int width;
	protected int height;
	protected int position_x;
	protected int position_y;
	public boolean moveRight=false;
	public boolean moveLeft=false;
	protected double pongPlayerSpeed=2;
	protected Rectangle rectangle;
	protected int score=0;

	public void setPongPlayerSize(int WIDTH,int HEIGHT){
		this.width=WIDTH;
		this.height=HEIGHT;
	}

	public void setPongPlayerPosition(int X,int Y){
		this.position_x = X;
		this.position_y = Y;
	}

	public void drawPongPlayer(Graphics g){
		g.fillRect(this.position_x,this.position_y,this.width,this.height);
	}

	public Rectangle getPongPlayerRectangle(){
		return this.rectangle = new Rectangle(this.position_x,this.position_y,this.width,this.height); 
	}

	public void updatePongPlayer(){
		if(this.moveRight){
			if(this.width+this.position_x+this.pongPlayerSpeed*this.SCALE <= this.SCREEN_WIDTH){
				this.position_x+=this.pongPlayerSpeed*this.SCALE;
			}
		}else if(this.moveLeft){
			if(this.position_x >= 0){
				this.position_x-=this.pongPlayerSpeed*this.SCALE;
			}
		}
	}

	public void givePongPlayerAPoint(){
		this.score++;
	}

}







