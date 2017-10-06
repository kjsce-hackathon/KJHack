package org.accessdenied.autohome;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference reference;
    private boolean isLivingRoomMasterOn = false;
    private boolean isLivingRoomFanOn = false;
    private boolean isLivingRoomLightOn = false;
    private boolean isBedRoomMasterOn = false;
    private boolean isBedRoomFanOn = false;
    private boolean isBedRoomLightOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase autoDataBase = FirebaseDatabase.getInstance();
        reference = autoDataBase.getReference("/home");

        final Button livingRoomMasterButton = (Button) findViewById(R.id.living_room_toggle_button);

        livingRoomMasterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout homeExpandedLayout = (LinearLayout) findViewById(R.id.home_expanded);

                // LivingRoom Elements are turned off, forcefully
                reference.child("/LivingRoom/light").setValue("0");
                reference.child("/LivingRoom/fan").setValue("0");

                if(homeExpandedLayout.getVisibility() == View.GONE) {
                    homeExpandedLayout.setVisibility(View.VISIBLE);
                    reference.child("/LivingRoom").setValue("1");
                    livingRoomMasterButton.setBackgroundColor(Color.parseColor("#4CAF50"));
                    livingRoomMasterButton.setText("ON");
                }
                else {
                    homeExpandedLayout.setVisibility(View.GONE);
                    reference.child("/LivingRoom").setValue("0");
                    livingRoomMasterButton.setBackgroundColor(Color.parseColor("#F44336"));
                    livingRoomMasterButton.setText("OFF");
                }
            }
        });

        Button livingRoomLightButton = (Button) findViewById(R.id.living_room_light_button);
        livingRoomLightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("/LivingRoom/light").setValue(isLivingRoomLightOn ? "0" : "1");
            }
        });

        reference.child("/LivingRoom/light").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    String value = dataSnapshot.getValue().toString();
                    isLivingRoomFanOn = value.equals("1");
                    //ImageView livingRoomLight = findViewById(R.id.livingRoomImg);
                    //livingRoomLight.setImageResource(isLivingRoomLightOn ? R.drawable.bulb_on : R.drawable.bulb_off);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button livingRoomFanButton = (Button) findViewById(R.id.home_bar_button);
        livingRoomFanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("/LivingRoom/fan").setValue(isLivingRoomFanOn ? "0" : "1");

            }
        });

        reference.child("/LivingRoom/fan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    String value = dataSnapshot.getValue().toString();
                    isLivingRoomFanOn = value.equals("1");
                    //ImageView livingRoomLight = findViewById(R.id.livingRoomImg);
                    //livingRoomLight.setImageResource(isLivingRoomLightOn ? R.drawable.bulb_on : R.drawable.bulb_off);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
