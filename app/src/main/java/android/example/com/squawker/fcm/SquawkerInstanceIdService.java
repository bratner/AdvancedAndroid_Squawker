package android.example.com.squawker.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by bratner on 5/16/18.
 */

public class SquawkerInstanceIdService extends FirebaseInstanceIdService {
    public static String TAG = SquawkerInstanceIdService.class.getSimpleName();


    @Override
    public void onTokenRefresh() {
        String refreshedToke = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Service Token ID is : "+refreshedToke);
    }
}
