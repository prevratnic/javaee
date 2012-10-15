import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Author: Ilya Varlamov aka privr@tnik
 * Date: 13.10.12
 * Time: 19:43
 */
public class Main {

    public static void main(String[] args) {

        final String path = "http://degas24.com/"; //"http://developers.sensis.com.au/docs/examples/java";
        InputStream stream = null;
        StringBuilder builder = new StringBuilder();

        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                stream = conn.getInputStream();
                int tmp;

                while((tmp = stream.read()) != -1){
                    builder.append((char) tmp);
                }
                parserPage(builder);
            }

        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(stream != null){
                closeStream(stream);
            }
        }
    }

    private static void closeStream(@NotNull Closeable cloneable){
        try {
            cloneable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parserPage(@NotNull CharSequence sequence){
        StringBuilder builder = null;

        if(sequence instanceof StringBuilder){
            builder = (StringBuilder) sequence;
            String tmp = builder.toString().replaceAll("<(title)+>.*</title+>", "").
                    replaceAll("<[^>]+>", "").
                    replaceAll("&nbsp;", "");

            System.out.print(tmp);
        }
    }

}
