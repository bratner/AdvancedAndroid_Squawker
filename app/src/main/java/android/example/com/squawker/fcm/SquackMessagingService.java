package android.example.com.squawker.fcm;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.example.com.squawker.MainActivity;
import android.example.com.squawker.R;
import android.example.com.squawker.provider.SquawkContract;
import android.example.com.squawker.provider.SquawkProvider;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
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
        ContentResolver resolver = getContentResolver();

        Log.d(TAG, "Got Message "+remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        Log.d(TAG, "with data: "+data);
        if (data != null) {

            createNotification(data.get(SquawkContract.COLUMN_MESSAGE));
            Uri insertUri = SquawkProvider.SquawkMessages.CONTENT_URI;

            ContentValues content = new ContentValues();

            content.put(SquawkContract.COLUMN_AUTHOR, data.get(SquawkContract.COLUMN_AUTHOR));
            content.put(SquawkContract.COLUMN_AUTHOR_KEY, data.get(SquawkContract.COLUMN_AUTHOR_KEY));
            content.put(SquawkContract.COLUMN_DATE, data.get(SquawkContract.COLUMN_DATE));
            content.put(SquawkContract.COLUMN_MESSAGE, data.get(SquawkContract.COLUMN_MESSAGE));
            resolver.insert(insertUri, content);
        }

        /*
        {author=TestAccount,
        date=1526503887647,
        message=Knausgaard microdosing lumbersexual, fashion axe jianbing art party disrupt fap chambray poke man bun iceland hashtag disrupt fap chambray poke man bun iceland austin selfies jean shorts lyft ugh typewriter, put a bird on it crucifix tacos salvia mustache,
        authorKey=key_test}
         */
    }

    private void createNotification(String message) {
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
    PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
    Notification note = new NotificationCompat.Builder(this)
            .setContentTitle("New squwack!")
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_duck)
            .setAutoCancel(true)
            .build();
    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    nm.notify(101, note);


    }
}
