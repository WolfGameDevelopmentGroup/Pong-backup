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

public class Player extends PongPlayer{

	public Player(int SCREEN_WIDTH, int SCREEN_HEIGHT, int SCALE){
		this.SCREEN_WIDTH = SCREEN_WIDTH*SCALE;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT*SCALE;
		this.SCALE = SCALE;

		int playerWidth = 50*this.SCALE;
		int playerHeight = 5*this.SCALE;
		int player_x = (int)(this.SCREEN_WIDTH/2);
		int player_y = this.SCREEN_HEIGHT-playerHeight;

		this.setPongPlayerSize(playerWidth,playerHeight);
		this.setPongPlayerPosition(player_x,player_y);
	}

}
