package com.example.mob2041_hinhnvph42207;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mob2041_hinhnvph42207.dao.ThuThuDAO;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText edUserName, edPassword;
    TextInputLayout in_User, in_Pass;
    Button btnLogin, btnCancel;
    CheckBox chkRememberPass;

    ThuThuDAO thuThuDAO = new ThuThuDAO(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        in_User = findViewById(R.id.in_User);
        in_Pass = findViewById(R.id.in_Pass);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        chkRememberPass = findViewById(R.id.chkRememberPass);

        // doc user, pass trong SharedPreferences
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUserName.setText(pref.getString("USERNAME",""));
        edPassword.setText(pref.getString("PASSWORD",""));
        chkRememberPass.setChecked(pref.getBoolean("REMEMBER",false));



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUserName.setText("");
                edPassword.setText("");

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 checkLogin();
            }
        });

    }

    public  void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status){
            //xoa tinh trang luu tru truoc do
            edit.clear();
        }else{
            //luu du lieu
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        //luu lai toan bo
        edit.commit();
    }
    public void  checkLogin(){
        String User = edUserName.getText().toString();
        String Pass = edPassword.getText().toString();
        if (User.isEmpty() || Pass.isEmpty()){
            if (User.equals(" ")){
                in_User.setError("Không được để trống tên đăng nhập");
            }else{
                in_User.setError(null);
            }
            if (Pass.equals(" ")){
                in_Pass.setError("Không được để trống mật khẩu");
            }else {
                in_Pass.setError(null);
            }
        }else {
            if (thuThuDAO.checkLogin(User,Pass)){
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(User,Pass,chkRememberPass.isChecked());
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("maTT",User);
                startActivity(i);
                finish();
            }else {
                in_User.setError("Tên đăng nhập hoặc mật khẩu không đúng");
                in_Pass.setError("Tên đăng nhập hoặc mật khẩu không đúng");
            }
        }
    }



}