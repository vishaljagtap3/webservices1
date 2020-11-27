package in.bitcode.webservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private ArrayList<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);

        new WebThread( new MyHandler() ).execute((Object) null);
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg != null && msg.obj != null) {
                //img.setImageBitmap((Bitmap) msg.obj);
                places = (ArrayList<Place>) msg.obj;
                for(Place place: places) {
                    Log.e("place", place.toString());
                }
            }
        }
    }
}