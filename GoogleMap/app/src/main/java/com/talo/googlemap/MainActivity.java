package com.talo.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText source, destination;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //取得元件
        source = findViewById(R.id.source_location);
        destination = findViewById(R.id.destination_location);
        search = findViewById(R.id.search);


        //點擊事件
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sourceLocation = source.getText().toString();
                String destinationLocation = destination.getText().toString();

                if (sourceLocation.equals("") && destinationLocation.equals("")){
                    Toast.makeText(MainActivity.this, R.string.text, Toast.LENGTH_SHORT).show();
                }else{
                    DisplayTrack(sourceLocation, destinationLocation);
                }
            }
        });
    }

    private void DisplayTrack(String sourceLocation, String destinationLocation) {
        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sourceLocation + " / " +
                    destinationLocation);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            //如果未安裝google maps
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}