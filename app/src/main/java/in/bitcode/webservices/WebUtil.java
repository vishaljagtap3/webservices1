package in.bitcode.webservices;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class WebUtil {

    public static void webServiceDemo() {

        try {
            URL url = new URL("http://www.bitcode.in");
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            Log.e("res", "Res Code " +  httpURLConnection.getResponseCode() );
            Log.e("res", "Res Message " +  httpURLConnection.getResponseMessage() );
            Log.e("res", "Header " +  httpURLConnection.getHeaderField(0) );

            InputStream in = httpURLConnection.getInputStream();
            byte [] data = new byte[1024*2];
            int count;
            while( (count = in.read(data)) != -1) {
                Log.e("tag", new String(data, 0, count));
            }

            in.close();
            httpURLConnection.disconnect();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Bitmap downloadImage(String imageUrl) {

        try {
            URL url = new URL(imageUrl);
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            Log.e("res", "Res Code " +  httpURLConnection.getResponseCode() );
            Log.e("res", "Res Message " +  httpURLConnection.getResponseMessage() );
            Log.e("res", "Content Type " +  httpURLConnection.getContentType());
            Log.e("res", "Content len " +  httpURLConnection.getContentLength());
            Log.e("res", "Header " +  httpURLConnection.getHeaderField(0) );


            InputStream in = httpURLConnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(in);

            in.close();
            httpURLConnection.disconnect();

            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
