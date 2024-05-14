package com.example.dilshanpro;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ShowRecords extends AppCompatActivity {

    EditText playerIdEditText;
    Button retrieveButton;
    ImageView playerImageView;
    TextView playerNameTextView, playerAgeTextView, playerScoreTextView, playerRegTextView, playerOverTextView, playerCenTextView, playerHcenTextView, playerWicketTextView, playerPositionTextView, playerStrickRateTextView, playerEconomyTextView;
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewrecord);

        playerIdEditText = findViewById(R.id.playerIdEditText);
        retrieveButton = findViewById(R.id.retrieveButton);
        playerImageView = findViewById(R.id.playerImageView);
        playerRegTextView = findViewById(R.id.playerRegTextView);
        playerNameTextView = findViewById(R.id.playerNameTextView);
        playerAgeTextView = findViewById(R.id.playerAgeTextView);
        playerScoreTextView = findViewById(R.id.playerScoreTextView);
        playerCenTextView = findViewById(R.id.playerCenTextView);
        playerHcenTextView = findViewById(R.id.playerHcenTextView);
        playerWicketTextView = findViewById(R.id.playerWicketTextView);
        playerPositionTextView = findViewById(R.id.playerPositionTextView);
        playerStrickRateTextView = findViewById(R.id.playerStrickRateTextView);
        playerEconomyTextView = findViewById(R.id.playerEconomyTextView);
        playerOverTextView=findViewById(R.id.playerOverTextView);

        dbHelper = new DbHelper(this, Constants.DB_NAME, null, Constants.DB_VERSION); // Initialize dbHelper here

        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrievePlayerData();
            }
        });
    }

    private void retrievePlayerData() {
        // Get the player ID entered by the user
        String playerId = playerIdEditText.getText().toString().trim();

        // Fetch player data from the database based on the player ID
        Player player = dbHelper.getPlayer(playerId);

        if (player != null) {

            playerNameTextView.setText(player.getName());
            playerRegTextView.setText(player.getRegn());
            playerAgeTextView.setText(player.getAge());
            playerScoreTextView.setText(player.getScore());
            playerOverTextView.setText(player.getOvers());
            playerCenTextView.setText(player.getCent());
            playerHcenTextView.setText(player.getHcen());
            playerWicketTextView.setText(player.getWicket());
            playerPositionTextView.setText(player.getPosition());

            String scoreString = player.getScore();
            String oversString = player.getOvers();
            if (!TextUtils.isEmpty(scoreString) && !TextUtils.isEmpty(oversString)) {
                double score = Double.parseDouble(scoreString);
                double overs = Double.parseDouble(oversString);
                double economyRate = score / overs;
                double strikeRate = score / (overs*6)*100;
                playerEconomyTextView.setText(String.valueOf(economyRate));
                playerStrickRateTextView.setText(String.valueOf(strikeRate));
            } else {
                // Handle the case where either score or overs is empty
                playerEconomyTextView.setText("N/A");
                playerStrickRateTextView.setText("N/A");
            }


            if (player.getImage() != null) {
                playerImageView.setImageBitmap(Bitmap.createScaledBitmap(player.getImage(), 100, 100, false));
            } else {

                playerImageView.setImageResource(R.drawable.default_image);
            }
        } else {
            // If no player data is found for the entered ID, display an error message or handle it as needed
            Toast.makeText(this, "Player not found", Toast.LENGTH_SHORT).show();
   }
}


}
