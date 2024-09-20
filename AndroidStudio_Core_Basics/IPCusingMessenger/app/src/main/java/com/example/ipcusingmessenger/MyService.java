package com.example.ipcusingmessenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.NonNull;


public class MyService extends Service {
    private final int JOB_1 = 1;
    private final int JOB_2 = 2;
    // sent respomce to activity
    private final int JOB_1_RESPONSE = 3;
    private final int JOB_2_RESPONSE = 4;
    // now creating object of messenger
    Messenger messenger = new Messenger(new IncomingHandler());

    @Override
    public IBinder onBind(Intent intent) {

        return messenger.getBinder();
    }

    // handler to handel the incoming message from the activity
    class IncomingHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Message MSG;
            String Message;
            Bundle bundle = new Bundle();

            switch (msg.what){
                case JOB_1:
                    Message = "This is first message from Service";
                    // communication is through by sending object
                    MSG = android.os.Message.obtain(null,JOB_1_RESPONSE);
                    bundle.putString("response_message",Message);
                    MSG.setData(bundle);
                    // sending back to activity
                    try {
                        msg.replyTo.send(MSG);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case JOB_2:
                    Message = "This is second message from Service";
                    // communication is through by sending object
                    MSG = android.os.Message.obtain(null,JOB_2_RESPONSE);
                    bundle.putString("response_message",Message);
                    MSG.setData(bundle);
                    // sending back to activity
                    try {
                        msg.replyTo.send(MSG);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                default:
                    super.handleMessage(msg);

            }
            // get all the message from activity in this method
        }
    }
}
