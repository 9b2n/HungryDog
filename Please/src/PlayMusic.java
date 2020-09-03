import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayMusic {
	File soundFile = null;
	AudioInputStream audioIn = null;
	Clip clip;

	public void startMusic() { // ������� �޾ƿͼ� �ݺ�����ϱ�
		try {
			soundFile = new File("Music/playMusic.wav");
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start(); // ���� ���
			clip.loop(Clip.LOOP_CONTINUOUSLY); // ���ѹݺ�
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void stopMusic() { // ���� ���߱�
		clip.stop();
	}

	public void start() { // ���� ����ϱ�
		clip.start();
	}

	public void moveMusic() { // ������ ȿ���� �޾ƿͼ� ����ϱ�
		try {
			soundFile = new File("Music/Bark.wav");
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
