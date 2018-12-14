package tsiukaris;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //change to any other targeted language
        String lang = "ru";
        String key = "trnsl.1.1.20181214T133006Z.3644873a15581c06.bf34c021fb645b63c0eae3ad8508169b742dbed8";
        String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=".concat(key);

        Scanner scanner = new Scanner(System.in);
        String phrase = scanner.nextLine();

        try{
            URL url = new URL(urlStr);

            //supports POST request
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            //write
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes("text=" + URLEncoder.encode(phrase, "UTF-8") + "&lang=" + lang);

            //parse response body
            InputStream response = connection.getInputStream();
            String jsonText = new Scanner(response).nextLine();
//java -jar target/yandex-translator-1.0-SNAPSHOT.jar
            //hardcoded start and end of translated phrase
            String translated = jsonText.substring(jsonText.indexOf("[") + 2, jsonText.indexOf("]") - 1);
            System.out.println(translated);

        } catch (MalformedURLException m){

        } catch (ProtocolException p){

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
