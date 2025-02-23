package Music;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import GUI.ErrorFrame;

/**
 * ±≥æ∞“Ù¿÷≤•∑≈
 * @author Chenyanqian
 */
public class MainFrameBGM extends Applet{

	private static final long serialVersionUID = 1L;
	    File file;
	    AudioClip ac;
	    URI uri;
	    URL url;
	 public MainFrameBGM(String filename) {
		 try {
		     file = new File(filename);
		     url = file.toURI().toURL();
			 ac = Applet.newAudioClip(url);
			 ac.loop();
			 ac.play();
		 } catch (MalformedURLException e) {
			 new ErrorFrame();
		}

	 } 
	 public void StopBGM() {
		ac.stop();
	 }
	 public void ChangeBGM(String filename) {
		 ac.stop();
		 try {
		     file = new File(filename);
		     url = file.toURI().toURL();
			 ac = Applet.newAudioClip(url);
			 ac.loop();
			 ac.play();
		 } catch (MalformedURLException e) {
			e.printStackTrace();
		}
	 }	 
}
