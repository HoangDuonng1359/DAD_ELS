package googleTranslate;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class sound {
    public static final String GOOGLE_TRANSLATE_AUDIO = "http://translate.google.com/translate_tts?";
    static final SecureRandom secureRandom = new SecureRandom();
    static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private static sound audio;

    private sound() {
    }

    public synchronized static sound getInstance() {

        if (audio == null) {
            audio = new sound();
        }
        return audio;
    }

    private static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    private static String generateSpeakURL(String language, String text) {
        return GOOGLE_TRANSLATE_AUDIO + "?ie=UTF-8" +
                "&q=" + URLEncoder.encode(text, StandardCharsets.UTF_8) +
                "&tl=" + language +
                "&tk=" + generateNewToken() +
                "&client=tw-ob";
    }

    public InputStream getAudio(String text, String languageOutput) throws IOException {
        String urlString = generateSpeakURL(languageOutput, text);
        URL url = new URL(urlString);
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        urlConn.setRequestMethod("GET");
        urlConn.setRequestProperty("User-Agent", "Mozilla/5.0");
        InputStream audioSrc = urlConn.getInputStream();
        return new BufferedInputStream(audioSrc);
    }

    public void play(InputStream sound) throws JavaLayerException {
        new Player(sound).play();
    }

    public static void main(String[] args) throws IOException, JavaLayerException {
        getInstance().play(getInstance().getAudio("xin chào", "vi"));
    }

    public static void get_Audio(String text, String language) throws IOException, JavaLayerException {
        getInstance().play(getInstance().getAudio(text, language));
    }
}
