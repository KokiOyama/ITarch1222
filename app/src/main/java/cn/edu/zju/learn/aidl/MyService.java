package cn.edu.zju.learn.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub(){
        @Override
        public int add(int a, int b) throws RemoteException{
            return a+a*b/100;
        }
    };
}
