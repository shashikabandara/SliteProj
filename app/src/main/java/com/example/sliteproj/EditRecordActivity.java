package com.example.sliteproj;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class EditRecordActivity extends AppCompatActivity {

    private ImageView pImageView;
    private EditText pNameEt, pDateEt, pTimeEt, pTypeEt, pNotesEt, pCnumberEt, pCvcEt, pEdateEt, pAmountEt, pPaidEt;
    Button saveInfoBt;
    ActionBar actionBar;
    ProgressDialog pd;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;

    private String[] cameraPermissions;
    private String[] storagePermissions;

    private Uri imageUri;


    private String id,name, date, time, type, notes, cnumber, cvc, edate, amount, paid, addTimeStamp, updateTimeStamp;
    private boolean editMode = false;
    private DatabaseHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        pd = new ProgressDialog(EditRecordActivity.this);

        pImageView = findViewById(R.id.personImage);
        pNameEt = findViewById(R.id.personName);
        pDateEt = findViewById(R.id.personDate);
        pTimeEt = findViewById(R.id.personTime);
        pTypeEt = findViewById(R.id.personType);
        pNotesEt = findViewById(R.id.personNotes);
        pCnumberEt = findViewById(R.id.personCnumber);
        pCvcEt = findViewById(R.id.personCVC);
        pEdateEt = findViewById(R.id.personEdate);
        pAmountEt = findViewById(R.id.personamount);
        pPaidEt = findViewById(R.id.personpaid);
        saveInfoBt = findViewById(R.id.addInfoBtn);

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("editMode", false);
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        date = intent.getStringExtra("DATE");
        time = intent.getStringExtra("TIME");
        type = intent.getStringExtra("TYPE");
        notes = intent.getStringExtra("NOTES");
        cnumber = intent.getStringExtra("CNUMBER");
        cvc = intent.getStringExtra("CVC");
        edate = intent.getStringExtra("EDATE");
        amount = intent.getStringExtra("AMOUNT");
        paid = intent.getStringExtra("PAID");
        imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
        addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
        updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

        if (editMode) {

            actionBar.setTitle("Update Appointment and Payment Details");
            // update data
            id = intent.getStringExtra("ID");
            name = intent.getStringExtra("NAME");
            date = intent.getStringExtra("DATE");
            time = intent.getStringExtra("TIME");
            type = intent.getStringExtra("TYPE");
            notes = intent.getStringExtra("NOTES");
            cnumber = intent.getStringExtra("CNUMBER");
            cvc = intent.getStringExtra("CVC");
            edate = intent.getStringExtra("EDATE");
            amount = intent.getStringExtra("AMOUNT");
            paid = intent.getStringExtra("PAID");
            imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
            addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
            updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

            pNameEt.setText(name);
            pDateEt.setText(date);
            pTimeEt.setText(time);
            pTypeEt.setText(type);
            pNotesEt.setText(notes);
            pCnumberEt.setText(cnumber);
            pCvcEt.setText(cvc);
            pEdateEt.setText(edate);
            pAmountEt.setText(amount);
            pPaidEt.setText(paid);

            if (imageUri.toString().equals("null")) {
                pImageView.setImageResource(R.drawable.ic_action_addphoto);
            }
            else {
                pImageView.setImageURI(imageUri);
            }
        }
        else {
            actionBar.setTitle("Update Appointment and Payment Details");
            // add data
        }


        dbHelper = new DatabaseHelper(this);

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        pImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();

            }
        });

        saveInfoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name= pNameEt.getText().toString();
                final String date = pDateEt.getText().toString();
                final String time=pTimeEt.getText().toString();
                final String type=pTypeEt.getText().toString();
                final String notes=pNotesEt.getText().toString();
                final String cnumber=pCnumberEt.getText().toString();
                final String cvc=pCvcEt.getText().toString();
                final String edate=pEdateEt.getText().toString();
                final String amount=pAmountEt.getText().toString();
                final String paid=pPaidEt.getText().toString();
                if(name.length()==0 ){
                    pNameEt.requestFocus();
                    pNameEt.setError("FIELD CANNOT BE EMPTY");
                }
                else if(date.length()==0){
                    pDateEt.requestFocus();
                    pDateEt.setError("FIELD CANNOT BE EMPTY");
                }
                else if(time.length()==0){
                    pTimeEt.requestFocus();
                    pTimeEt.setError("FIELD CANNOT BE EMPTY");
                }
                else if(type.length()==0){
                    pTypeEt.requestFocus();
                    pTypeEt.setError("FIELD CANNOT BE EMPTY");
                }
                else if(notes.length()==0){
                    pNotesEt.requestFocus();
                    pNotesEt.setError("FIELD CANNOT BE EMPTY");
                }
                else if(cnumber.length()==0){
                    pCnumberEt.requestFocus();
                    pCnumberEt.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!(cnumber.length()==16)){
                    pCnumberEt.requestFocus();
                    pCnumberEt.setError("CARD NUMBER CONSIT OF 16 DIGITS");
                }
                else if(cvc.length()==0){
                    pCvcEt.requestFocus();
                    pCvcEt.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!(cvc.length()==3)){
                    pCvcEt.requestFocus();
                    pCvcEt.setError("SHOULD CONTAIN 3 DIGITS");
                }
                else if(edate.length()==0){
                    pEdateEt.requestFocus();
                    pEdateEt.setError("FIELD CANNOT BE EMPTY");
                }
                else if(amount.length()==0){
                    pAmountEt.requestFocus();
                    pAmountEt.setError("FIELD CANNOT BE EMPTY");
                }
                else if(paid.length()==0){
                    pPaidEt.requestFocus();
                    pPaidEt.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!name.matches("[a-zA-Z ]+"))
                {
                    pNameEt.requestFocus();
                    pNameEt.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(date.matches("[a-zA-Z ]+"))
                {
                    pDateEt.requestFocus();
                    pDateEt.setError("ENTER ONLY DATE");
                }
                else if(time.matches("[a-zA-Z ]+"))
                {
                    pTimeEt.requestFocus();
                    pTimeEt.setError("ENTER ONLY TIME");
                }
                else if(!type.matches("[a-zA-Z ]+"))
                {
                    pTypeEt.requestFocus();
                    pTypeEt.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(!notes.matches("[a-zA-Z ]+"))
                {
                    pNotesEt.requestFocus();
                    pNotesEt.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(cnumber.matches("[a-zA-Z ]+"))
                {
                    pCnumberEt.requestFocus();
                    pCnumberEt.setError("ENTER ONLY NUMERICAL CHARACTER");
                }
                else if(cvc.matches("[a-zA-Z ]+"))
                {
                    pCvcEt.requestFocus();
                    pCvcEt.setError("ENTER ONLY NUMERICAL CHARACTER");
                }
                else if(edate.matches("[a-zA-Z ]+"))
                {
                    pEdateEt.requestFocus();
                    pEdateEt.setError("ENTER ONLY DATE");
                }
                else if(amount.matches("[a-zA-Z ]+"))
                {
                    pAmountEt.requestFocus();
                    pAmountEt.setError("ENTER ONLY NUMERICAL CHARACTER");
                }
                else if(paid.matches("[a-zA-Z ]+"))
                {
                    pPaidEt.requestFocus();
                    pPaidEt.setError("ENTER ONLY NUMERICAL CHARACTER");
                }
                else {
                    pd.setTitle("Updating");
                    pd.setMessage("Please wait...");
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    getData();
                    startActivity(new Intent(EditRecordActivity.this, MainActivity.class));
                    Toast.makeText(EditRecordActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getData() {

        name = ""+pNameEt.getText().toString().trim();
        date = ""+pDateEt.getText().toString().trim();
        time = ""+pTimeEt.getText().toString().trim();
        type = ""+pTypeEt.getText().toString().trim();
        notes = ""+pNotesEt.getText().toString().trim();
        cnumber = ""+pCnumberEt.getText().toString().trim();
        cvc = ""+pCvcEt.getText().toString().trim();
        edate = ""+pEdateEt.getText().toString().trim();
        amount = ""+pAmountEt.getText().toString().trim();
        paid = ""+pPaidEt.getText().toString().trim();
        if (editMode) {

            String newUpdatedTime = ""+System.currentTimeMillis();
            dbHelper.updateInfo(
                   ""+id,
                    ""+imageUri,
                    ""+name,
                    ""+date,
                    ""+time,
                    ""+type,
                    ""+notes,
                    ""+cnumber,
                    ""+cvc,
                    ""+edate,
                    ""+amount,
                    ""+paid,
                    ""+addTimeStamp,
                    ""+updateTimeStamp
            );
        }
        else {

            String timeStamp = ""+System.currentTimeMillis();

            dbHelper.insertInfo(
                    ""+imageUri,
                    ""+name,
                    ""+date,
                    ""+time,
                    ""+type,
                    ""+notes,
                    ""+cnumber,
                    ""+cvc,
                    ""+edate,
                    ""+amount,
                    ""+paid,
                    ""+addTimeStamp,
                    ""+updateTimeStamp
            );

        }
    }

    private void imagePickDialog() {

        String[] options = {"Camera", "Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Image From");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {
                    if (!checkCameraPermission()) {
                        requestCameraPermission();
                    }
                    else {
                        pickFromCamera();
                    }
                }
                else if (which == 1) {
                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    }
                    else {
                        pickFromGallery();
                    }
                }

            }
        });

        builder.create().show();
    }

    private void pickFromGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);
    }

    private void pickFromCamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Image title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image description");

        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);

    }

    private boolean checkStoragePermission() {

        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);

        return result;
    }

    private void requestStoragePermission() {

        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {

        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);

        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    private void requestCameraPermission() {

        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case CAMERA_REQUEST_CODE: {

                if (grantResults.length>0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && storageAccepted) {
                        pickFromCamera();
                    }

                    else {
                        Toast.makeText(this, "Camera permission required!", Toast.LENGTH_SHORT).show();
                    }

                }

            }
            break;
            case STORAGE_REQUEST_CODE:{

                if (grantResults.length>0) {

                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (storageAccepted) {
                        pickFromGallery();
                    }
                    else {
                        Toast.makeText(this, "Storage permission required!", Toast.LENGTH_SHORT).show();
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

                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);
            }

            else if (requestCode == IMAGE_PICK_CAMERA_CODE) {

                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);
            }

            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

                CropImage.ActivityResult result = CropImage.getActivityResult(data);

                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    imageUri = resultUri;
                    pImageView.setImageURI(resultUri);
                }

                else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                    Exception error = result.getError();
                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
