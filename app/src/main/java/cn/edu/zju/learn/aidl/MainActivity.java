package cn.edu.zju.learn.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyService myService;
    private IMyAidlInterface myAidlInterface;
    private ServiceConnection mConnection;
    private EditText edit_text;
    private EditText edit_text2;
    private int x;
    private int y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_text=(EditText)findViewById(R.id.editText);
        edit_text2=(EditText)findViewById(R.id.editText2);
        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                String key = edit_text.getText().toString();
                int x = Integer.parseInt(key);
                String key2 = edit_text2.getText().toString();
                int y = Integer.parseInt(key2);
                myAidlInterface = IMyAidlInterface.Stub.asInterface(service);

                try {
                    int result = myAidlInterface.add(x,y);
                    Toast.makeText(getApplicationContext(), result + "円", Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                bindService(intent, mConnection, BIND_AUTO_CREATE);

            }
        });
    }


}
