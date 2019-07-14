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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

	public boolean isRunning=false;
	private int frame=0;
	public static Screen screen;
	public String TITLE;
	public int WIDTH, HEIGHT, SCALE;
	private BufferStrategy bs;

	public Game(String TITLE, int WIDTH, int HEIGHT, int SCALE){
		this.TITLE = TITLE;
		this.WIDTH = WIDTH;
		this.SCALE = SCALE;
		this.screen = new Screen(TITLE, WIDTH, HEIGHT, SCALE);
		screen.showScreen();
	}

	public void updateGame(){
		System.out.println("Atualizando o jogo...");
	}

	public void renderizeGame(){
		BufferStrategy bs = this.screen.canvas.getBufferStrategy();

		if (bs == null){
			this.screen.canvas.createBufferStrategy(3);
			return;
		}

		Graphics g = this.screen.canvas.getGraphics();
		g.setColor(Color.WHITE);
		this.screen.drawBackground(g);
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
