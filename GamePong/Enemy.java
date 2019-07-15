/*
	 GamePong/Enemy.java (Java)
	 
	 Objetivo: Definir a classe Enemy, adversário do player no jogo.
	 
	 Site: http://www.dirackslounge.online
	 
	 Versão 1.0
	 
	 Programador: Rodolfo Dirack 14/07/2019
	 
	 Email: rodolfo_profissional@hotmail.com
	 
	 Licença: GPL-3.0 <https://www.gnu.org/licenses/gpl-3.0.txt>.
*/

package GamePong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy implements PongPlayer{

	private int SCREEN_WIDTH;
	private int SCREEN_HEIGHT;
	private int SCALE;
	private int enemy_width;
	private int enemy_height;
	private int enemy_x;
	private int enemy_y;
	private boolean right;
	private boolean left;
	private int numero;
	private int VELOCITY=2;

	public Enemy(int SCREEN_WIDTH, int SCREEN_HEIGHT, int SCALE){
		this.SCREEN_WIDTH = SCREEN_WIDTH*SCALE;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT*SCALE;
		this.SCALE = SCALE;
	}

	public void setPongPlayerSizeAndPosition(){
		this.enemy_width=50*this.SCALE;
		this.enemy_height=10*this.SCALE;
		this.enemy_x = 0;
		this.enemy_y = 0;
	}

	public void drawPongPlayer(Graphics g){
		g.fillRect(this.enemy_x,this.enemy_y,this.enemy_width,this.enemy_height);
	}

	public void updatePongPlayer(){

		numero = (int)(Math.random()*11.0);

		if (numero < 5) {
			this.right=true;
		}else{
			this.left=true;
		}

		if(this.right){
			if(this.enemy_width+this.enemy_x+this.VELOCITY*this.SCALE <= this.SCREEN_WIDTH){
				this.enemy_x+=this.VELOCITY*this.SCALE;
			}
		}else if(this.left){
			if(this.enemy_x >= 0){
				this.enemy_x-=this.VELOCITY*this.SCALE;
			}
		}

		this.right = false;
		this.left = false;
	}

}
