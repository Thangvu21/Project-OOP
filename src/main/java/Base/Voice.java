package Base;

import com.sun.speech.freetts.VoiceManager;

import java.util.Locale;
import java.util.Scanner;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class Voice {

    private static final String VOICES = "freetts.voices";
    private static final String VOICE_VALUE = "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory";
    private static final String CENTRAL_DATA = "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral";

    public static void textToSpeed(String text) {
        try {
            System.setProperty(VOICES, VOICE_VALUE);

            Central.registerEngineCentral(CENTRAL_DATA);
            Synthesizer sy = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));

            sy.allocate();
            sy.resume();
            sy.speakPlainText(text, null);
            sy.waitEngineState(Synthesizer.QUEUE_EMPTY);
            sy.deallocate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
