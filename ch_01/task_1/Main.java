import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 13.10.12
 * Time: 19:43
 */
public class Main {

    public static void main(String[] args) throws IOException {

        final String path = "http://www.fontanka.ru/";

        StringBuilder modelPage = null;
        BufferedReader bufferedReader = null;

        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){

                bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "Windows-1251"));
                modelPage = new StringBuilder();

                String tmp;

                while((tmp = bufferedReader.readLine()) != null) {
                    modelPage.append(tmp);
                    modelPage.append("\n\r");
                }
                parserPage(modelPage);
            }

        }catch (MalformedURLException e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader != null){
                bufferedReader.close();
            }
        }
    }

    private static void parserPage(@NotNull StringBuilder builder) {

        String tmp = builder.toString().
                replaceAll("<(title)+>.*</title+>", "").
                replaceAll("<[^>]+>", "").
                replaceAll("&nbsp;", "");

        System.out.print(tmp);

    }

}
