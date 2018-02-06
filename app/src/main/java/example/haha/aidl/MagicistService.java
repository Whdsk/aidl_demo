package example.haha.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import example.haha.aidl.Magicist;
import example.haha.aidl.MagicistAidl;

/**
 * Created by lenovocabulary on 2018/2/5.
 */

public class MagicistService extends Service {
    private List<Magicist> magicist1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        magicist1=new ArrayList<>();

        return iBinder;
    }

    private IBinder iBinder=new MagicistAidl.Stub(){
        @Override
        public List<Magicist> add(Magicist magicist) throws RemoteException {
            return magicist1;
        }
    };

}
