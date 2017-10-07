package org.accessdenied.autohome;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ceylonlabs.imageviewpopup.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * Created by arvind on 10/7/17.
 */

public class WatchPiActivity extends AppCompatActivity {

    public int notificationId = 000;
    public String imageURL;
    FirebaseDatabase watchPiDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = watchPiDatabase.getReference("/url");

    NotificationCompat.Builder watchPiNotification = new NotificationCompat.Builder(WatchPiActivity.this);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watchpi_layout);

        ////////////////////////////////
        // IMAGE POP-UP FUNCTIONALITY //
        ////////////////////////////////

        final ImagePopup imagePopup = new ImagePopup(this);
        imagePopup.setWindowHeight(800);
        imagePopup.setWindowWidth(800);
        imagePopup.setBackgroundColor(Color.parseColor("#FFFFFF"));
        imagePopup.setHideCloseIcon(true);
        imagePopup.setImageOnClickClose(true);

        final ImageView imageView = (ImageView) findViewById(R.id.intruder_image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePopup.initiatePopup(imageView.getDrawable());
                imagePopup.viewPopup();
            }
        });

        reference.child("/name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.getValue().toString().equals("null")) {
                    String personName = dataSnapshot.getValue().toString();
                    NotificationManager watchPiTrustedNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    watchPiNotification.setSmallIcon(R.mipmap.ic_launcher_round)
                            .setContentTitle("WatchPi")
                            .setContentText("Trusted Person Entry : " + personName);
                    notificationId++;
                    watchPiTrustedNotificationManager.notify(notificationId, watchPiNotification.build());
                    TextView personStatus = (TextView) findViewById(R.id.person_status);
                    TextView personStatusBody = (TextView) findViewById(R.id.person_status_body);
                    personStatus.setText("Trusted Person " + personName + " Spotted");
                    personStatusBody.setText("Your Home is Safe");
                    imageView.setImageResource(R.mipmap.ic_launcher_round);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        reference.child("/img").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                imageURL = dataSnapshot.getValue().toString();
                if(!imageURL.equals("null")) {
                    Log.d("RAAA", imageURL);
                    NotificationCompat.Builder watchPiNotification = new NotificationCompat.Builder(WatchPiActivity.this);
                    NotificationManager watchPiUnknownNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    watchPiNotification.setSmallIcon(R.mipmap.ic_launcher_round)
                            .setContentTitle("WatchPi")
                            .setContentText("Unknown Person Entered, Open App for Details");
                    notificationId++;
                    watchPiUnknownNotificationManager.notify(notificationId, watchPiNotification.build());
                    ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
                    bar.setIndeterminate(true);
                    Picasso.with(WatchPiActivity.this).load(imageURL).into(imageView);
                    bar.setIndeterminate(false);
                    TextView personStatus = (TextView) findViewById(R.id.person_status);
                    TextView personStatusBody = (TextView) findViewById(R.id.person_status_body);
                    personStatus.setText("Intruder Spotted");
                    personStatusBody.setText("Please take the necessary action");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
