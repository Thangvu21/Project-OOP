package Base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {
    public static String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbwzlKRftVMgsANJrKnkRI8D_gy8kYTzU5yoO7OjS3OVwDB20WH8bR7ZSPfmO2_6hN9KNQ/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // "Mozilla/5.0" đại diện cho 1 trình duyệt web giả mạo là 1 trình duyệt để gửi yêu cầu lên như trình duyệt web thông thường
        // Điều này có thể hữu ích khi máy chủ cần biết thông tin về loại trình duyệt để xử lý yêu cầu đúng cách.
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        in.close();
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        try {
            String text = "xin chào";
            //Translated text: Hallo Welt!
            System.out.println("Translated text: " + Translator.translate("vi", "en", text));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
