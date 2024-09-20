package com.example.ipcusingmessenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// activity send command to some service and service send some reply back to activity
public class MainActivity extends AppCompatActivity {

    private final int JOB_1 = 1;
    private final int JOB_2 = 2;
    // sent response to activity
    private final int JOB_1_RESPONSE = 3;
    private final int JOB_2_RESPONSE = 4;
    Messenger messenger = null;
    boolean isBind = false;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bind the service
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);

        textView = (TextView) findViewById(R.id.textView);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            // iBinder service
            isBind = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
            isBind = false;
        }
    };
    public void getMessage(View view){
        String button_text = (String)((Button)view).getText();
        Message msg;
        if (button_text.equals("Get First Message")){
            msg = Message.obtain(null,JOB_1);
            // reply-handler
            msg.replyTo = new Messenger(new ResponseHandler());
            // message to the service
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
        else if(button_text.equals("Get Second Message")){
            msg = Message.obtain(null,JOB_2);
            msg.replyTo = new Messenger(new ResponseHandler());
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // handler to handle the reply from the service
    class ResponseHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            String message;
            switch (msg.what){
                case JOB_1_RESPONSE:
                    // getting the message from the message object
                    message = msg.getData().getString("response_message");
                    textView.setText(message);
                    break;

                case JOB_2_RESPONSE:
                    // getting the message from the message object
                    message = msg.getData().getString("response_message");
                    textView.setText(message);
                    break;
            }
            super.handleMessage(msg);
        }
    }
    @Override
    protected void onStop() {
        unbindService(serviceConnection);
        isBind = false;
        messenger =null;
        super.onStop();
    }
}