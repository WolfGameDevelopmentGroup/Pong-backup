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

public class Game implements Runnable{

	public boolean isRunning=false;
	private int frame=0;
	public Screen screen;

	public Game(String TITLE, int WIDTH, int HEIGHT, int SCALE){
		this.screen = new Screen(TITLE, WIDTH, HEIGHT, SCALE);
		screen.showScreen();
	}

	public void updateGame(){
		System.out.println("Atualizando o jogo...");
	}

	public void renderizeGame(){
		System.out.println("Renderizando o jogo...");
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
			System.out.println(frame);
			try{
				Thread.sleep(1000/60);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}

	}

}
