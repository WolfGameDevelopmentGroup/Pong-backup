/*
	 Sound.java (java)
	 
	 Objetivo: Música do jogo Pong Player.
	 
	 Versão 1.0
	 
	 Site: http://www.dirackslounge.online
	 
	 Programador: Rodolfo A. C. Neves (Dirack) 23/08/2019
	 
	 Email: rodolfo_profissional@hotmail.com
	 
	 Licença: Software de uso livre e código aberto.
*/

package GamePong;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	private AudioClip clip;

	public static final Sound ballColision = new Sound("../sounds/beep.wav");
	public static final Sound enemyPoint= new Sound("../sounds/pontoEnemy.wav");
	public static final Sound playerPoint = new Sound("../sounds/pontoPlayer.wav");

	public Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		}catch(Throwable e) {}
	}
	
	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		}catch(Throwable e) {}
	}
	
	public void loop() {
		try {
			new Thread() {
				public void run() {
					clip.loop();
				}
			}.start();
		}catch(Throwable e) {}
	}
}
