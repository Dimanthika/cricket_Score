package com.example.dilshanpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button register;
    EditText userName , Password;

    private static final String CORRECT_USERNAME = "dimanthika";
    private static final String CORRECT_PASSWORD = "dimanthika123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.btnLogin);
        userName = findViewById(R.id.editTextUsername);
        Password = findViewById(R.id.editTextPassword);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enteredUsername = userName.getText().toString();
                String enteredPassword = Password.getText().toString();

                if (enteredUsername.equals(CORRECT_USERNAME) && enteredPassword.equals(CORRECT_PASSWORD)) {

                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Select.class);
                    startActivity(intent);
                    finish();
                } else {

                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }

            }
    });
}

}
