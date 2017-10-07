package org.accessdenied.autohome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by arvind on 10/7/17.
 */

public class HomeApplianceActivity extends AppCompatActivity {


    private DatabaseReference reference;
    private boolean isMasterSwitchOn = false;

    private boolean isLivingRoomFanOn = false;
    private boolean isLivingRoomLightOn = false;
    private boolean isBedRoomFanOn = false;
    private boolean isBedRoomLightOn = false;

    public boolean isLivingRoomDashBoardVisible() {
        LinearLayout livingRoomExpanded = (LinearLayout) findViewById(R.id.living_room_expanded);
        return livingRoomExpanded.getVisibility() == View.GONE;
    }

    public boolean isBedRoomDashBoardVisible() {
        LinearLayout bedRoomExpanded = (LinearLayout) findViewById(R.id.bedroom_expanded);
        return bedRoomExpanded.getVisibility() == View.GONE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_applicance_layout);

        FirebaseDatabase autoDataBase = FirebaseDatabase.getInstance();
        reference = autoDataBase.getReference("/home");

        ///////////////////////////
        // LIVING ROOM CARD VIEW //
        ///////////////////////////

        final CardView livingRoomCard = (CardView) findViewById(R.id.living_room_card_view);
        livingRoomCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout livingRoomExpanded = (LinearLayout) findViewById(R.id.living_room_expanded);
                if(isLivingRoomDashBoardVisible())
                    livingRoomExpanded.setVisibility(View.VISIBLE);
                else
                    livingRoomExpanded.setVisibility(View.GONE);

            }
        });

        ///////////////////////
        // LIVING ROOM LIGHT //
        ///////////////////////

        LinearLayout livingRoomLightLayout = (LinearLayout) findViewById(R.id.living_room_light_layout);
        livingRoomLightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("/LivingRoom/light").setValue(isLivingRoomLightOn ? "0" : "1");
            }
        });

        reference.child("/LivingRoom/light").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ImageView livingRoomLightButton = (ImageView) findViewById(R.id.living_room_light_button);

                if (dataSnapshot.getValue() != null) {
                    String value = dataSnapshot.getValue().toString();
                    isLivingRoomLightOn = value.equals("1");
                    livingRoomLightButton.setImageResource(isLivingRoomLightOn ? R.drawable.bulb_on_image : R.drawable.bulb_off_image);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //////////////////////
        // LIVING ROOM FAN //
        /////////////////////

        LinearLayout livingRoomFanLayout = (LinearLayout) findViewById(R.id.living_room_fan_layout);
        livingRoomFanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("/LivingRoom/fan").setValue(isLivingRoomFanOn ? "0" : "1");

            }
        });

        reference.child("/LivingRoom/fan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    ImageView livingRoomFanButton = (ImageView) findViewById(R.id.living_room_fan_button);

                    String value = dataSnapshot.getValue().toString();
                    isLivingRoomFanOn = value.equals("1");
                    livingRoomFanButton.setImageResource(isLivingRoomFanOn ? R.drawable.fan_on_image : R.drawable.fan_off_image);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ////////////////////////
        // BEDROOM CARD VIEW //
        ///////////////////////

        final CardView bedRoomCard = (CardView) findViewById(R.id.bedroom_card_view);
        bedRoomCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout bedRoomExpanded = (LinearLayout) findViewById(R.id.bedroom_expanded);
                if(isBedRoomDashBoardVisible())
                    bedRoomExpanded.setVisibility(View.VISIBLE);
                else
                    bedRoomExpanded.setVisibility(View.GONE);

            }
        });

        ////////////////////
        // BEDROOM LIGHT //
        ///////////////////

        LinearLayout bedRoomLightLayout = (LinearLayout) findViewById(R.id.bedroom_light_layout);
        bedRoomLightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("/BedRoom/light").setValue(isBedRoomLightOn ? "0" : "1");
            }
        });

        reference.child("/BedRoom/light").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ImageView bedRoomLightButton = (ImageView) findViewById(R.id.bedroom_light_button);

                if (dataSnapshot.getValue() != null) {
                    String value = dataSnapshot.getValue().toString();
                    isBedRoomLightOn = value.equals("1");
                    bedRoomLightButton.setImageResource(isBedRoomLightOn ? R.drawable.bulb_on_image : R.drawable.bulb_off_image);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /////////////////
        // BEDROOM FAN //
        /////////////////

        LinearLayout bedRoomFanLayout = (LinearLayout) findViewById(R.id.bedroom_fan_layout);
        bedRoomFanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("/BedRoom/fan").setValue(isBedRoomFanOn ? "0" : "1");

            }
        });

        reference.child("/BedRoom/fan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    ImageView bedRoomFanButton = (ImageView) findViewById(R.id.bedroom_bar_button);

                    String value = dataSnapshot.getValue().toString();
                    isBedRoomFanOn = value.equals("1");
                    bedRoomFanButton.setImageResource(isBedRoomFanOn ? R.drawable.fan_on_image : R.drawable.fan_off_image);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ////////////////////////
        // THE MASTER SWITCH //
        ///////////////////////

        Button theMasterButton = (Button) findViewById(R.id.master_toggle_button);
        theMasterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout bedRoomExpanded = (LinearLayout) findViewById(R.id.bedroom_expanded);
                LinearLayout livingRoomExpanded = (LinearLayout) findViewById(R.id.living_room_expanded);

                if(!isMasterSwitchOn) {
                    bedRoomExpanded.setVisibility(View.GONE);
                    livingRoomExpanded.setVisibility(View.GONE);
                }

                isMasterSwitchOn = !isMasterSwitchOn;

                reference.child("/LivingRoom/light").setValue("0");
                reference.child("/LivingRoom/fan").setValue("0");
                reference.child("/BedRoom/light").setValue("0");
                reference.child("/BedRoom/fan").setValue("0");

            }
        });

    }

}
