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

public class Enemy extends PongPlayer{

	public Enemy(int SCREEN_WIDTH, int SCREEN_HEIGHT, int SCALE){
		this.SCREEN_WIDTH = SCREEN_WIDTH*SCALE;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT*SCALE;
		this.SCALE = SCALE;

		this.setPongPlayerSize(50*this.SCALE,5*this.SCALE);
		this.setPongPlayerPosition(0,0);

		this.pongPlayerSpeed -= 0.5;
	}

	public void updatePongPlayer(int xBallPosition){

		int numero;
		double dxEnemy;

		dxEnemy = (xBallPosition - this.position_x);

		if(dxEnemy > 0){
			if(this.width+this.position_x+this.pongPlayerSpeed*this.SCALE <= this.SCREEN_WIDTH){
				this.position_x += this.pongPlayerSpeed*this.SCALE;
			}
		}else{
			if(this.position_x >= 0){
				this.position_x -= this.pongPlayerSpeed*this.SCALE;
			}
		}

	}

}
