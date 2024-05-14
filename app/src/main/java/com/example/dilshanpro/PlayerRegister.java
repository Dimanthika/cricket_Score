package com.example.dilshanpro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PlayerRegister extends AppCompatActivity {

    ImageView PImg;
    EditText PName, PAge, PId, PScore, POvers, PCen, PHlfCen, PWickets;
    Button btnSlct;
    RadioGroup Radio;
    RadioButton Position;
    AlertDialog.Builder builder;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;
    private static final int IMAGE_PICK_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageUri;

    private  String name, age, regno, score, overs, centuries, hcenturies, wicket, position;

    private DbHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        PImg = findViewById(R.id.PImage);
        PName = findViewById(R.id.txtpname);
        PAge = findViewById(R.id.txtAge);
        PId = findViewById(R.id.txtregno);
        PScore = findViewById(R.id.txttotscore);
        POvers = findViewById(R.id.txttotovers);
        PCen = findViewById(R.id.txttotCenturies);
        PHlfCen = findViewById(R.id.txtTotHlfcen);
        PWickets = findViewById(R.id.txttotWickets);
        Radio = findViewById(R.id.rgPosition);


        btnSlct = findViewById(R.id.btnSelect);

        dbHelper = new DbHelper(this, Constants.DB_NAME, null, Constants.DB_VERSION);


        cameraPermissions = new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE};


        PImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imagePickDialog();
            }

        });

        btnSlct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputData();
            }
        });
    }

    private void inputData() {
        name = PName.getText().toString().trim();
        age = PAge.getText().toString().trim();
        regno = PId.getText().toString().trim();
        score = PScore.getText().toString().trim();
        overs = POvers.getText().toString().trim();
        centuries = PCen.getText().toString().trim();
        hcenturies = PHlfCen.getText().toString().trim();
        wicket = PWickets.getText().toString().trim();

        int selectedId = Radio.getCheckedRadioButtonId();
        if (selectedId != -1) {
            Position = findViewById(selectedId);
            position = Position.getText().toString().trim();
        } else {

            Toast.makeText(this, "Please select a position", Toast.LENGTH_SHORT).show();
            return;
        }


        String timestamp = ""+System.currentTimeMillis();
        long id = dbHelper.insertRecord(
                "i"+imageUri,
                ""+name,
                ""+age,
                ""+regno,
                ""+score,
                ""+overs,
                ""+centuries,
                ""+hcenturies,
                ""+wicket,
                ""+position);

        Toast.makeText(this, "Record Added to ID: "+id, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(PlayerRegister.this, Select.class);
        startActivity(intent);


    }



    private void imagePickDialog() {

        String[] options = {"Camera", "Gallery"};
        builder = new AlertDialog.Builder(PlayerRegister.this);
        builder.setTitle("Pick Image From");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    if (!checkCameraPermissions()) {
                        requestCameraPermission();
                    } else {
                        pickFromCamera();
                    }
                } else if (which == 1) {
                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        pickFromGallery();
                    }
                }
            }
        });

        builder.create().show();
    }

    private void pickFromGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Image Title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image Description");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return result;
    }

    private void requestStoragePermission() {

        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
    }

    public boolean checkCameraPermissions() {

        boolean result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    private void requestCameraPermission() {

        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case CAMERA_REQUEST_CODE: {

                if (grantResults.length > 0) {
                    Boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    Boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted) {
                        pickFromCamera();
                    } else {
                        Toast.makeText(this, "Camera & Storage permission are required", Toast.LENGTH_SHORT).show();
                    }

                }


            }
            break;

            case STORAGE_REQUEST_CODE: {

                if (grantResults.length > 0) {

                    Boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (storageAccepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Storage permission are required", Toast.LENGTH_SHORT).show();
                    }

                }

            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    PImg.setImageBitmap(imageBitmap);
                }
            } else if (requestCode == IMAGE_PICK_CODE) {
                // Set the captured image from the URI to the ImageView
                PImg.setImageURI(imageUri);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
}





}
