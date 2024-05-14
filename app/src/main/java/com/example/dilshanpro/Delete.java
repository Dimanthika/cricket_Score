package com.example.dilshanpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    private EditText playerIdEditText;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        playerIdEditText = findViewById(R.id.playerIdEditText);
        dbHelper = new DbHelper(this, Constants.DB_NAME, null, Constants.DB_VERSION);


    }

    public void searchPlayer(View view) {
        String regNo = playerIdEditText.getText().toString().trim();
        Player player = dbHelper.getPlayer(regNo);
        if (player != null) {
            // Found player, show player details
            // Update UI with player details
            Toast.makeText(this, "Player found: " + player.getName(), Toast.LENGTH_SHORT).show();
        } else {
            // Player not found
            Toast.makeText(this, "No player found with the provided registration number", Toast.LENGTH_SHORT).show();
        }
    }

    public void deletePlayer(View view) {
        String regNo = playerIdEditText.getText().toString().trim();
        int deletedRows = dbHelper.deletePlayer(regNo);
        if (deletedRows > 0) {
            Toast.makeText(this, "Player record deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No player found with the provided registration number", Toast.LENGTH_SHORT).show();
        }
        // Clear EditText after deletion
        Intent intent = new Intent(Delete.this, Select.class);
        startActivity(intent);
 }


}