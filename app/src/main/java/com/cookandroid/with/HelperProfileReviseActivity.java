package com.cookandroid.with;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/*ProfileReviseActivity에 필요한 기능 정리해보자
 * 1. 프로필 사진 변경
 * 2. 저장 버튼 기능
 * 3. 뒤로가기 버튼 기능
 * 4. EditText 입력값을 저장 버튼 눌렀을 때 TextView로 바꿔주는 기능
 * 5. 자격증 추가 버튼을 누르면 레이아웃이 추가되는 기능
 * 6. 도움 가능 종류를 고르는 기능
 * */

public class HelperProfileReviseActivity extends AppCompatActivity {

    /*1. 프로필 사진 변경을 위해 넣은 코드입니다. - 2줄 */
    private final int GET_GALLERY_IMAGE=200;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_profile_revise);

        /*1. 프로필 사진 변경 기능*/
        ImageView imageView = findViewById(R.id.btn_profile_revise);
        imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent,GET_GALLERY_IMAGE);

            }
        });


        /*뒤로가기 버튼 구현하기*/
        ImageView backBtn = (ImageView) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HelperProfileActivity.class);
                startActivity(intent);//액티비티 띄우기
            }
        });

        /*2. 자격증 추가 버튼 코드입니다.*/
        /*다시 만들어볼 예정입니다.*/

    }

    /*프로필 사진 변경을 위해 넣은 코드입니다. */
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);
        }
    }
}