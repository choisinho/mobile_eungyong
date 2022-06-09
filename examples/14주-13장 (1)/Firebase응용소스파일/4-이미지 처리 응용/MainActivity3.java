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

        //신규등록 이벤트
        regiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEditData(); //입력 데이터 가져오기
                if (flag == false)
                    return;
                else {
                    //기존에 존재하는 id는 등록 거절
                    dataRef.child("userImage").child(idString).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            userImage = snapshot.getValue(UserImage.class);
                            if (userImage != null) {
                                progBar.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity3.this);
                                dia.setTitle("신규등록 실패");
                                dia.setMessage("이미 존재하는 Id... 중복!!!");
                                dia.setPositiveButton("확인",null);
                                dia.show();
                                return;
                            }else {
                                //Firebase의 Storage에 업로드 할 이미지의 경로 생성
                                imgPath = "images/" + idString + "_image" + "." + getFileExtension(imageUri);
                                progBar.setVisibility(View.VISIBLE);

                                //Firebase의 Storage에 이미지 저장
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
        //정보조회 이벤트
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIdState();  //입력 아이디 체크
                if (flag == false)
                    return;
                else {
                    progBar.setVisibility(View.VISIBLE);
                    //기존에 존재하는 id인지 체크 한 후 처리
                    dataRef.child("userImage").child(idString).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            userImage = snapshot.getValue(UserImage.class);
                            if (userImage == null) { //기존에 존재하지 않는 id
                                progBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity3.this,
                                        "존재하지 않는 Id", Toast.LENGTH_SHORT).show();
                                clearEdit();
                                return;
                            } else {
                                    //기존에 존재하는 id의 정보 조회 및 이미지 가져오기
                                    nameEdit.setText(userImage.getName());
                                    ageEdit.setText(userImage.getAge() + "");
                                    imgPath = userImage.getImgPath();
                                    imageUri = Uri.parse(imgPath);
                                    //파이어베이스에서 이미지 가져오기
                                    downloadFromFirebase(imgPath);
                                }
                            }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity3.this, "데이터베이스 에러!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        //자료삭제 이벤트
        deleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIdState();
                if (flag == false)
                    return;
                else {
                    progBar.setVisibility(View.VISIBLE);

                    //id에 해당되는 파일경로를 찾아서 해당 파일 삭제
                    dataRef.child("userImage").child(idString).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            userImage = snapshot.getValue(UserImage.class);
                            if (userImage == null) {
                                progBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity3.this,
                                        "존재하지 않는 Id", Toast.LENGTH_SHORT).show();
                                clearEdit();
                                return;
                            } else {
                                imgPath = userImage.getImgPath();

                                //Firebase에서 해당 경로의 파일만 삭제하기
                                storeRef.child(imgPath).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        //id 기준으로 데이터 삭제
                                        dataRef.child("userImage").child(idString).removeValue();
                                        clearEdit();
                                        progBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(MainActivity3.this,
                                                "데이터 삭제 완료!!!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(MainActivity3.this,
                                                "이미지 삭제 실패!!!", Toast.LENGTH_SHORT).show();
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
        // 폰 갤러리에서 사진 첨부하기
        /* 1. Gradle에서 dependency 추가
                implementation 'com.github.bumptech.glide:glide:4.11.0'
            2. manifest에 추가
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
        //사진 첨부 이벤트
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/");
                imagePickerActivityResult.launch(galleryIntent);
            }
        });
        //사진 취소 이벤트
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageUri = null;
                myImage.setImageResource(R.drawable.img_frame);
            }
        });
    }

    //파이어베이스에 이미지 업로드
    private void uploadToFirebase(Uri uri) {
        //업로드 시킬 파일 참조 생성
        fileRef = storeRef.child(imgPath);

        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Firebase에 데이터 등록
                userImage = new UserImage(idString, nameString, ageInt, imgPath);
                dataRef.child("userImage").child(idString).setValue(userImage);
                //이미지 업로드 후 초기화면으로
                myImage.setImageResource(R.drawable.img_frame);
                clearEdit();
                progBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity3.this, "데이터 등록 완료!!!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progBar.setVisibility(View.INVISIBLE);
                AlertDialog.Builder dia = new AlertDialog.Builder(MainActivity3.this);
                dia.setTitle("이미지 업로드 실패");
                dia.setMessage("첨부파일을 다시 선택하세요!!!");
                dia.setPositiveButton("확인",null);
                dia.show();
                myImage.setImageResource(R.drawable.img_frame);
            }
        });
    }
    //파이어베이스에서 이미지 다운로드
    private void downloadFromFirebase(String imgPath) {
        //다운로드할 파일을 가르키는 참조 만들기
        fileRef = storeRef.child(imgPath);

        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                progBar.setVisibility(View.INVISIBLE);
                // Glide로 다운로드 이미지를 myImage(이미지뷰)에 출력!!!
                Glide.with(getApplicationContext()).load(uri).into(myImage);
                //Toast.makeText(getApplicationContext(), "다운로드 성공 : "+ uri, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "다운로드 실패", Toast.LENGTH_SHORT).show();
            }
        });

        //휴대폰 로컬 영역에 저장하기 : optional
//        try {
//            int strLen = imgPath.length();
//            String suffix = imgPath.substring(strLen - 4, strLen);
//            String prefix = imgPath.substring(7, strLen - 4);
//
//            //prefix: 파일명, suffix: 확장자
//            File localFile = File.createTempFile(prefix + "_", suffix);
//
//            fileRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    myImage.setImageURI(Uri.parse(localFile.getPath()));
//                    progBar.setVisibility(View.INVISIBLE);
//                    //Toast.makeText(getApplicationContext(), "파일 저장 성공", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    progBar.setVisibility(View.INVISIBLE);
//                    Toast.makeText(getApplicationContext(), "파일 저장 실패", Toast.LENGTH_SHORT).show();
//                }
//            });
//        } catch (IOException e) {
//            progBar.setVisibility(View.INVISIBLE);
//            Toast.makeText(getApplicationContext(), "예외 발생 !!!!", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
    }
    //파일타입 가져오기
    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    //신규등록 시 필수 입력 데이터 체크
    public void getEditData() {
        idString = idEdit.getText().toString().trim();
        nameString = nameEdit.getText().toString().trim();
        String numString = ageEdit.getText().toString().trim();

        if (idString.isEmpty() | nameString.isEmpty() | numString.isEmpty() | imageUri == null) {
            flag = false;
            Toast.makeText(this, "아이디, 이름, 나이, 사진첨부 필수!!", Toast.LENGTH_SHORT).show();
        } else {
            flag = true;
            ageInt = Integer.parseInt(numString);
        }
    }
    //정보조회, 자료삭제 시 입력 아이디 체크
    public void getIdState() {
        idString = idEdit.getText().toString().trim();
        if (idString.isEmpty()) {
            flag = false;
            Toast.makeText(MainActivity3.this,
                    "아이디 필수 입력!!!", Toast.LENGTH_SHORT).show();
        } else
            flag = true;
    }
    //입력 창 리셋
    public void clearEdit() {
        idEdit.setText("");
        nameEdit.setText("");
        ageEdit.setText("");
        imageUri = null;
        myImage.setImageResource(R.drawable.img_frame);
    }
}
