package in.bitcode.webservices;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

class WebThread extends AsyncTask<Object, Object, Object> {
    
    private Handler handler;

    public WebThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected Object doInBackground(Object... objects) {
        //WebUtil.webServiceDemo();
        return WebUtil.downloadImage("http://bitcode.in/images/banner/android_training_in_pune_1280x800.jpg");
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if( o != null) {
            Message message = new Message();
            message.obj = o;
            handler.sendMessage(message);
        }
    }
}
