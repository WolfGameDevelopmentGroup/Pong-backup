/*
	 GamePong/Player.java (Java)
	 
	 Objetivo: Jogador no jogo pong.
	 
	 Site: http://www.dirackslounge.online
	 
	 Versão 1.0
	 
	 Programador: Rodolfo Dirack 14/07/2019
	 
	 Email: rodolfo_profissional@hotmail.com
	 
	 Licença: GPL-3.0 <https://www.gnu.org/licenses/gpl-3.0.txt>.
*/

package GamePong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player implements PongPlayer{

	private int SCREEN_WIDTH;
	private int SCREEN_HEIGHT;
	private int SCALE;
	private int player_width;
	private int player_height;
	private int player_x;
	private int player_y;
	public boolean right=false;
	public boolean left=false;
	private int VELOCITY=2;
	private Rectangle rectangle;

	public Player(int SCREEN_WIDTH, int SCREEN_HEIGHT, int SCALE){
		this.SCREEN_WIDTH = SCREEN_WIDTH*SCALE;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT*SCALE;
		this.SCALE = SCALE;
	}

	public void setPongPlayerSizeAndPosition(){
		this.player_width=50*this.SCALE;
		this.player_height=5*this.SCALE;
		this.player_x = (int)(this.SCREEN_WIDTH/2);
		this.player_y = this.SCREEN_HEIGHT-this.player_height;
	}

	public void drawPongPlayer(Graphics g){
		g.fillRect(this.player_x,this.player_y,this.player_width,this.player_height);
	}

	public Rectangle getRectangle(){
		return this.rectangle = new Rectangle(this.player_x,this.player_y,this.player_width,this.player_height); 
	}

	public void updatePongPlayer(){
		if(this.right){
			if(this.player_width+this.player_x+this.VELOCITY*this.SCALE <= this.SCREEN_WIDTH){
				this.player_x+=this.VELOCITY*this.SCALE;
			}
		}else if(this.left){
			if(this.player_x >= 0){
				this.player_x-=this.VELOCITY*this.SCALE;
			}
		}
	}
}
