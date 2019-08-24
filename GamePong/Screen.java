/*
	 Screen.java (Java)
	 
	 Objetivo: Gerar a tela do jogo.
	 
	 Site: http://www.dirackslounge.online
	 
	 Versão 1.0
	 
	 Programador: Rodolfo Dirack 14/07/2019
	 
	 Email: rodolfo_profissional@hotmail.com
	 
	 Licença: GPL-3.0 <https://www.gnu.org/licenses/gpl-3.0.txt>.
*/

package GamePong;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;

public class Screen extends Canvas{

	public static JFrame jframe;
	public static Canvas canvas;
	private String jframeTitle;
	private int WIDTH;
	private int HEIGHT;
	private int SCALE;

	public Screen(String jframeTitle, int WIDTH, int HEIGHT, int SCALE){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.SCALE = SCALE;

		this.canvas = new Canvas();
		this.canvas.setPreferredSize(new Dimension(this.WIDTH*this.SCALE, this.HEIGHT*this.SCALE));		

		this.jframeTitle = jframeTitle;
		this.jframe = new JFrame(this.jframeTitle);
		this.jframe.setPreferredSize(new Dimension(this.WIDTH*this.SCALE, this.HEIGHT*this.SCALE));

		this.jframe.add(this.canvas);
		this.jframe.setResizable(false);
		this.jframe.pack();
		this.jframe.setLocationRelativeTo(null);
		this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

	public void showScreen(){
		this.jframe.setVisible(true);
	}

	public void drawBackground(Graphics g,int enemyScore, int playerScore){
		g.fillRect(0,0,this.WIDTH*this.SCALE, this.HEIGHT*this.SCALE);
		g.setColor(Color.WHITE);
		g.fillRect(0,this.SCALE*((this.HEIGHT/2)-2),this.WIDTH*this.SCALE,2);
		g.drawString(""+enemyScore,this.SCALE*(this.WIDTH/2),this.SCALE*(this.HEIGHT/4));
		g.drawString(""+playerScore,this.SCALE*(this.WIDTH/2),this.SCALE*(3*this.HEIGHT/4));
	}

}
