package com.example.my_firebase_yeahn;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity3 extends AppCompatActivity {
    DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference();
    StorageReference storeRef = FirebaseStorage.getInstance().getReference();
    StorageReference fileRef;
    UserImage userImage; //Data Model Class
    Uri imageUri;

    EditText idEdit, ageEdit, nameEdit;
    Button regiBtn, searchBtn, deleBtn, imgBtn, cancelBtn;
    ProgressBar progBar;
    ImageView myImage;
    String idString, nameString, imgPath;
    int ageInt;
    Boolean flag = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        this.setTitle("Firebase Realtime Database");

        idEdit = findViewById(R.id.idEdit);
        ageEdit = findViewById(R.id.ageEdit);
        nameEdit = findViewById(R.id.nameEdit);
        regiBtn = findViewById(R.id.regiBtn);
        searchBtn = findViewById(R.id.searchBtn);
        deleBtn = findViewById(R.id.deleBtn);
        imgBtn = findViewById(R.id.imgBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        progBar = findViewById(R.id.progBar);
        myImage = findViewById(R.id.myImage);

        //???????????? ?????????
        regiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEditData(); //?????? ????????? ????????????
                if (flag == false)
                    return;
                else {
                    //????????? ???????????? id??? ?????? ??????
                    dataRef.child("userImage").child(idString).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            userImage = snapshot.getValue(UserImage.class);
                            if (userImage != null) {
                                progBar.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity3.this);
                                dia.setTitle("???????????? ??????");
                                dia.setMessage("?????? ???????????? Id... ??????!!!");
                                dia.setPositiveButton("??????",null);
                                dia.show();
                                return;
                            }else {
                                //Firebase??? Storage??? ????????? ??? ???????????? ?????? ??????
                                imgPath = "images/" + idString + "_image" + "." + getFileExtension(imageUri);
                                progBar.setVisibility(View.VISIBLE);

                                //Firebase??? Storage??? ????????? ??????
                                uploadToFirebase(imageUri);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });
        //???????????? ?????????
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIdState();  //?????? ????????? ??????
                if (flag == false)
                    return;
                else {
                    progBar.setVisibility(View.VISIBLE);
                    //????????? ???????????? id?????? ?????? ??? ??? ??????
                    dataRef.child("userImage").child(idString).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            userImage = snapshot.getValue(UserImage.class);
                            if (userImage == null) { //????????? ???????????? ?????? id
                                progBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity3.this,
                                        "???????????? ?????? Id", Toast.LENGTH_SHORT).show();
                                clearEdit();
                                return;
                            } else {
                                    //????????? ???????????? id??? ?????? ?????? ??? ????????? ????????????
                                    nameEdit.setText(userImage.getName());
                                    ageEdit.setText(userImage.getAge() + "");
                                    imgPath = userImage.getImgPath();
                                    imageUri = Uri.parse(imgPath);
                                    //???????????????????????? ????????? ????????????
                                    downloadFromFirebase(imgPath);
                                }
                            }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity3.this, "?????????????????? ??????!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        //???????????? ?????????
        deleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIdState();
                if (flag == false)
                    return;
                else {
                    progBar.setVisibility(View.VISIBLE);

                    //id??? ???????????? ??????????????? ????????? ?????? ?????? ??????
                    dataRef.child("userImage").child(idString).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            userImage = snapshot.getValue(UserImage.class);
                            if (userImage == null) {
                                progBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity3.this,
                                        "???????????? ?????? Id", Toast.LENGTH_SHORT).show();
                                clearEdit();
                                return;
                            } else {
                                imgPath = userImage.getImgPath();

                                //Firebase?????? ?????? ????????? ????????? ????????????
                                storeRef.child(imgPath).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        //id ???????????? ????????? ??????
                                        dataRef.child("userImage").child(idString).removeValue();
                                        clearEdit();
                                        progBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(MainActivity3.this,
                                                "????????? ?????? ??????!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(MainActivity3.this,
                                                "????????? ?????? ??????!!!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });
        // ??? ??????????????? ?????? ????????????
        /* 1. Gradle?????? dependency ??????
                implementation 'com.github.bumptech.glide:glide:4.11.0'
            2. manifest??? ??????
                <uses-permission android:name="android.permission.INTERNET" />
       */
        ActivityResultLauncher<Intent> imagePickerActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            imageUri = result.getData().getData();
                            myImage.setImageURI(imageUri);
                            //Toast.makeText(MainActivity3.this, imageUri.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        //?????? ?????? ?????????
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/");
                imagePickerActivityResult.launch(galleryIntent);
            }
        });
        //?????? ?????? ?????????
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageUri = null;
                myImage.setImageResource(R.drawable.img_frame);
            }
        });
    }

    //????????????????????? ????????? ?????????
    private void uploadToFirebase(Uri uri) {
        //????????? ?????? ?????? ?????? ??????
        fileRef = storeRef.child(imgPath);

        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Firebase??? ????????? ??????
                userImage = new UserImage(idString, nameString, ageInt, imgPath);
                dataRef.child("userImage").child(idString).setValue(userImage);
                //????????? ????????? ??? ??????????????????
                myImage.setImageResource(R.drawable.img_frame);
                clearEdit();
                progBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity3.this, "????????? ?????? ??????!!!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progBar.setVisibility(View.INVISIBLE);
                AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity3.this);
                dia.setTitle("????????? ????????? ??????");
                dia.setMessage("??????????????? ?????? ???????????????!!!");
                dia.setPositiveButton("??????",null);
                dia.show();
                myImage.setImageResource(R.drawable.img_frame);
            }
        });
    }
    //???????????????????????? ????????? ????????????
    private void downloadFromFirebase(String imgPath) {
        //??????????????? ????????? ???????????? ?????? ?????????
        fileRef = storeRef.child(imgPath);

        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                progBar.setVisibility(View.INVISIBLE);
                // Glide??? ???????????? ???????????? myImage(????????????)??? ??????!!!
                Glide.with(getApplicationContext()).load(uri).into(myImage);
                //Toast.makeText(getApplicationContext(), "???????????? ?????? : "+ uri, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "???????????? ??????", Toast.LENGTH_SHORT).show();
            }
        });

        //????????? ?????? ????????? ???????????? : optional
//        try {
//            int strLen = imgPath.length();
//            String suffix = imgPath.substring(strLen - 4, strLen);
//            String prefix = imgPath.substring(7, strLen - 4);
//
//            //prefix: ?????????, suffix: ?????????
//            File localFile = File.createTempFile(prefix + "_", suffix);
//
//            fileRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    myImage.setImageURI(Uri.parse(localFile.getPath()));
//                    progBar.setVisibility(View.INVISIBLE);
//                    //Toast.makeText(getApplicationContext(), "?????? ?????? ??????", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    progBar.setVisibility(View.INVISIBLE);
//                    Toast.makeText(getApplicationContext(), "?????? ?????? ??????", Toast.LENGTH_SHORT).show();
//                }
//            });
//        } catch (IOException e) {
//            progBar.setVisibility(View.INVISIBLE);
//            Toast.makeText(getApplicationContext(), "?????? ?????? !!!!", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
    }
    //???????????? ????????????
    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    //???????????? ??? ?????? ?????? ????????? ??????
    public void getEditData() {
        idString = idEdit.getText().toString().trim();
        nameString = nameEdit.getText().toString().trim();
        String numString = ageEdit.getText().toString().trim();

        if (idString.isEmpty() | nameString.isEmpty() | numString.isEmpty() | imageUri == null) {
            flag = false;
            Toast.makeText(this, "?????????, ??????, ??????, ???????????? ??????!!", Toast.LENGTH_SHORT).show();
        } else {
            flag = true;
            ageInt = Integer.parseInt(numString);
        }
    }
    //????????????, ???????????? ??? ?????? ????????? ??????
    public void getIdState() {
        idString = idEdit.getText().toString().trim();
        if (idString.isEmpty()) {
            flag = false;
            Toast.makeText(MainActivity3.this,
                    "????????? ?????? ??????!!!", Toast.LENGTH_SHORT).show();
        } else
            flag = true;
    }
    //?????? ??? ??????
    public void clearEdit() {
        idEdit.setText("");
        nameEdit.setText("");
        ageEdit.setText("");
        imageUri = null;
        myImage.setImageResource(R.drawable.img_frame);
    }
}
