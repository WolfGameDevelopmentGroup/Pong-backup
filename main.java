/*
	 main.java (Java)
	 
	 Objetivo: Projeto do curso de desenvolvimento de Games da Danki code, Jogo Pong.
	 
	 Site: http://www.dirackslounge.online
	 
	 Versão 1.0
	 
	 Programador: Rodolfo Dirack 14/07/2019
	 
	 Email: rodolfo_profissional@hotmail.com
	 
	 Licença: GPL-3.0 <https://www.gnu.org/licenses/gpl-3.0.txt>.
*/

import GamePong.Game;

public class main{

	public static int WIDTH=400;
	public static int HEIGHT=300;
	public static int SCALE=2;
	public static String TITLE="Game Pong#1";

	public static void main(String[] args){

		Game game = new Game(TITLE, WIDTH, HEIGHT, SCALE);
		
		game.startGame();
	}

}
