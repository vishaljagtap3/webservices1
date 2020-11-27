package in.bitcode.webservices;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;

class WebThread extends AsyncTask<Object, Object, ArrayList<Place>> {
    
    private Handler handler;

    public WebThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected ArrayList<Place> doInBackground(Object... objects) {
        //WebUtil.webServiceDemo();
        //return WebUtil.downloadImage("http://bitcode.in/images/banner/android_training_in_pune_1280x800.jpg");
        return WebUtil.getPlaces();


    }

    @Override
    protected void onPostExecute(ArrayList<Place> places) {
        super.onPostExecute(places);
        if( places != null) {
            Message message = new Message();
            message.obj = places;
            handler.sendMessage(message);
        }
    }
}
