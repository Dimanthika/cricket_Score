package com.example.dilshanpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePlayer extends AppCompatActivity {

    EditText searchPlayerEditText, playerRegEditText, playerAgeEditText, playerScoreEditText, playerOversEditText, playerCenturiesEditText, playerHalfCenturiesEditText, playerWicketsEditText;
    Button searchButton, updateButton;
    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_player);


        searchPlayerEditText = findViewById(R.id.playerIdEditText);
        playerAgeEditText = findViewById(R.id.playerAgeTextView);
        playerScoreEditText = findViewById(R.id.playerScoreTextView);
        playerOversEditText = findViewById(R.id.playerOverTextView);
        playerCenturiesEditText = findViewById(R.id.playerCenTextView);
        playerHalfCenturiesEditText = findViewById(R.id.playerHcenTextView);
        playerWicketsEditText = findViewById(R.id.playerWicketTextView);
        searchButton = findViewById(R.id.retrieveButton);
        updateButton = findViewById(R.id.updateButton);

        dbHelper = new DbHelper(this, Constants.DB_NAME, null, Constants.DB_VERSION);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regNo = searchPlayerEditText.getText().toString().trim();
                Player player = dbHelper.getPlayer(regNo);
                if (player != null) {
                    // Found player, populate EditText fields with player details

                    playerAgeEditText.setText(player.getAge());
                    playerScoreEditText.setText(player.getScore());
                    playerOversEditText.setText(player.getOvers());
                    playerCenturiesEditText.setText(player.getCent());
                    playerHalfCenturiesEditText.setText(player.getHcen());
                    playerWicketsEditText.setText(player.getWicket());
                } else {
                    // Player not found, show message or handle accordingly
                    Toast.makeText(UpdatePlayer.this, "Player not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve updated player details from EditText fields
                String regNo = playerRegEditText.getText().toString().trim();
                String age = playerAgeEditText.getText().toString().trim();
                String score = playerScoreEditText.getText().toString().trim();
                String overs = playerOversEditText.getText().toString().trim();
                String centuries = playerCenturiesEditText.getText().toString().trim();
                String halfCenturies = playerHalfCenturiesEditText.getText().toString().trim();
                String wickets = playerWicketsEditText.getText().toString().trim();

                // Update player record in database
                long id = dbHelper.updatePlayer(regNo, age, score, overs, centuries, halfCenturies, wickets);
                if (id != -1) {
                    Toast.makeText(UpdatePlayer.this, "Player record updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdatePlayer.this, "Failed to update player record", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(UpdatePlayer.this, Select.class);
                startActivity(intent);
            }
   });
}

}