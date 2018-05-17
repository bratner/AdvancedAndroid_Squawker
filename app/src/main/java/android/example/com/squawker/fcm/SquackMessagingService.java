package android.example.com.squawker.fcm;

import android.content.ContentResolver;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by bratner on 5/16/18.
 */

public class SquackMessagingService extends FirebaseMessagingService {
    public final String TAG = SquackMessagingService.class.getSimpleName();
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        ContentResolver resolver = getApplicationContext().getContentResolver();
        Log.d(TAG, "Got Message "+remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        Log.d(TAG, "with data: "+data);
    }
}
