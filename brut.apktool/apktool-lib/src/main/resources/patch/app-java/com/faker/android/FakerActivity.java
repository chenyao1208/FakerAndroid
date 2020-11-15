/**
 * Generated by FakerAndroid for doc or help by https://github.com/Efaker/FakerAndroid
 */
package com.faker.android;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Window;
import android.widget.ImageView;
import {R};
/**
 * TODO NOTE:this is a demo act if you want run it you should mode the manifest and extends the original main act best
 * TODO NOTE:if the original act is a final class you cna mod smali to change it public then run it run you got the public javascaffoding api to extends it
 * TODO NOTE: you can extend the original main act
 */
public class FakerActivity extends Activity implements JniBridge {
    public native void registerCallBack(Object object);
    static final int HANDLER_MSG_CALLJAVA = 1000;
    final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_MSG_CALLJAVA:
                    String cmsg = (String) msg.obj;
                    callJava(cmsg);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        registerCallBack(this);

    }
    @Override
    public void onJniCall(String msg) {// unity player isnot main thread transfer method to main thread
        Message message = new Message();
        message.what =HANDLER_MSG_CALLJAVA;
        message.obj = msg;
        handler.sendMessage(message);
    }

    private void callJava(String msg){
        Logger.log(msg);
    }
}
