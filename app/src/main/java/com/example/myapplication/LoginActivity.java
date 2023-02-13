package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DTO.ThuThu;

public class LoginActivity extends AppCompatActivity {
    EditText edTaiKhoan, edMatKhau, edNhacLai;
    Button btnDangNhap;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ThuThu thuThu = new ThuThu();
        intent = getIntent();
        edTaiKhoan = findViewById(R.id.edTaiKhoan);
        edMatKhau = findViewById(R.id.edMatKhau);
        edNhacLai = findViewById(R.id.edNhacLaiMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tk = edTaiKhoan.getText().toString();
                String mk = edMatKhau.getText().toString();
                String rmk = edNhacLai.getText().toString();

                if (mk.matches(rmk)  ){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
               
                else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                }






            }
        });
    }

}
