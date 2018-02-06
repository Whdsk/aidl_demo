package example.haha;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import example.haha.aidl.Magicist;
import example.haha.aidl.MagicistAidl;

public class MainActivity extends AppCompatActivity {

    MagicistAidl magicistAidl;
    private Button button;
    List<Magicist> magicists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.bt);
        bindService();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Magicist magicist=new Magicist();
                magicist.setAge(12);
                magicist.setName("bobo");
                magicist.setPart("fire");
                try {
                    magicists=magicistAidl.add(magicist);
                    Log.d("Magic", magicists.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    ServiceConnection conn = new ServiceConnection() {
        //绑定服务时
        @Override public void onServiceConnected(ComponentName name, IBinder service) {
            //拿到了远程的服务
            magicistAidl = MagicistAidl.Stub.asInterface(service);
            Toast.makeText(MainActivity.this,"绑定了",Toast.LENGTH_LONG).show();

        }

        //当服务断开时
        @Override public void onServiceDisconnected(ComponentName name) {
            //回收资源
            magicistAidl = null;
        }
    };
    private void bindService() {
        //远程调用
        //1、获取服务端
        Intent intent = new Intent();
        //Android 5.0之后不支持隐式意图，必须是显式意图来启动绑定服务


        //绑定的时候包名要一致，不然无法绑定，Service要在package名字后面
        intent.setComponent(
                new ComponentName("example.haha.aidl", "example.haha.aidl.MagicistService"));
        //第三个参数是一个flag，绑定时自动启动

        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
