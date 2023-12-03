package Base;


import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

public class Voice {

    private static final String API_KEY = "7f7e8aa4dfa84aec95c756c15ceb8109";

    public static String Name = "Linda";

    public static void speakWord(String word, String language) throws Exception {
        Thread thread = new Thread(() -> {
            try {
                VoiceProvider tts = new VoiceProvider(API_KEY);
                VoiceParameters params = new VoiceParameters(word, Languages.English_UnitedStates);
                params.setCodec(AudioCodec.WAV);
                params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
                params.setLanguage(language);
                params.setVoice(Name);
                params.setBase64(false);
                params.setSSML(false);
                params.setRate(0);
                byte[] voice = tts.speech(params);
                playAudio(voice);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
    private static void playAudio(byte[] audioData) throws Exception {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(audioData));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        Thread.sleep(clip.getMicrosecondLength() / 1000);
        clip.close();
        audioInputStream.close();
    }

    public static void main(String args[]) throws Exception {
        speakWord("Xin chào các bạn", "vi-vn");
        speakWord("Hello", "en-gb");
    }
}
