package edu.uw.uidemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "**DEMO**";

    private static final int REQUEST_PICTURE_CODE = 1;

    private boolean hideActionBar = false;

    private int mClickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //View launchButton = findViewById(R.id.btnLaunch);
        //View callButton = findViewById(R.id.btnDial);
        //View cameraButton = findViewById(R.id.btnPicture);
        //View messageButton = findViewById(R.id.btnMessage);
        //View clickerButton = findViewById(R.id.btnClicker);

    }

    public void startSecondActivity(View v) {
        Log.v(TAG, "Launch button pressed");

        //Explicit intent
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("edu.uw.uidemo.message", "Hello number 2!");
        startActivity(intent);
    }

    public void callNumber(View v) {
        Log.v(TAG, "Call button pressed");

        //implicit intent
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:206-685-1622"));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void takePicture(View v) {
        Log.v(TAG, "Camera button pressed");

        //implicit intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_PICTURE_CODE);
        }
    }

    public void sendMessage(View v) {
        Log.v(TAG, "Message button pressed");
        //...
    }


    private static final int CLICK_NOTIFICATION_ID = 0;

    public void clickerPressed(View v) {
        Log.v(TAG, "Clicker button pressed");
        //...

        //SHOW Notification
        //Required: icon, content title, content text
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name2)
                .setContentTitle("Click Counting")
                .setContentText("I work!")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MainActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        //TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the Intent that starts the Activity to the top of the stack
        //stackBuilder.addNextIntent(resultIntent);
        //PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(CLICK_NOTIFICATION_ID, mBuilder.build());

        //get access to actionbar
        /*
        ActionBar actionBar = getSupportActionBar(); //for reference

        if (hideActionBar == false) {
            hideActionBar = true;
            actionBar.hide();
        } else {
            hideActionBar = false;
            actionBar.show();
        }
        */

    }

    //create option menu and set the layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //instatiate a menu inflater and inflate
        MenuInflater inflater = getMenuInflater();
        inflater.inflate((R.menu.main_menu), menu);

        return true;
    }

    //decides what to do when the item is clicked based on its id
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ((item.getItemId())) {
            case R.id.menu_item1:
                callNumber(null);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
