package in.bitcode.webservices;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

class WebUtil {

    public static ArrayList<Place> getPlaces() {
        //String url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=Museum%20of%20Contemporary%20Art%20Australia&inputtype=textquery&fields=photos,formatted_address,name,rating,opening_hours,geometry&key=AIzaSyCkC8zr3VnxvJk7M4LLy3_S6DDH8eU6N9E";
        String apiUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyCkC8zr3VnxvJk7M4LLy3_S6DDH8eU6N9E";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();

            /*
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream out = httpURLConnection.getOutputStream();
            out.write(data);
            out.close();
            */

            httpURLConnection.connect();

            if ( httpURLConnection.getResponseCode() != 200) {
                return null;
            }

            StringBuilder resJson = new StringBuilder();
            int count;
            byte [] data = new byte[1024*2];

            InputStream in = httpURLConnection.getInputStream();
            while( (count = in.read(data)) != -1) {
                resJson.append(new String(data, 0, count));
            }
            in.close();
            httpURLConnection.disconnect();

            Log.e("tag", resJson.toString());


            JSONObject jRes = new JSONObject(resJson.toString());
            if( ! jRes.getString("status").equals("OK")) {
                return null;
            }

            JSONArray jPlaces = jRes.getJSONArray("results");
            ArrayList<Place> places = new ArrayList<>();

            for(int i = 0; i < jPlaces.length(); i++) {
                JSONObject jPlace = jPlaces.getJSONObject(i);

                String name = jPlace.getString("name");
                String iconUrl = jPlace.getString("icon");
                double rating = jPlace.getDouble("rating");
                String vicinity = jPlace.getString("vicinity");

                JSONObject jGeo = jPlace.getJSONObject("geometry");
                JSONObject jLoc = jGeo.getJSONObject("location");
                double lat = jLoc.getDouble("lat");
                double lng = jLoc.getDouble("lng");

                Place place = new Place(name, iconUrl, vicinity, rating, lat, lng);
                places.add(place);
            }

            return places;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void webServiceDemo() {


        try {
            URL url = new URL("http://www.bitcode.in");
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
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
