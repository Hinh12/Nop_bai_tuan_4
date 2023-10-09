package com.example.mob2041_hinhnvph42207.Frame;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mob2041_hinhnvph42207.LoginActivity;
import com.example.mob2041_hinhnvph42207.R;
import com.example.mob2041_hinhnvph42207.dao.ThuThuDAO;
import com.example.mob2041_hinhnvph42207.model.ThuThu;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class ChangePassFragment extends Fragment {
    TextInputLayout in_PassOld,in_PassChange,in_RePassChange;
    TextInputEditText edPassOld, edPass, edRePass;
    Button btnSave, btnCancel;
    private Context context;

    public ChangePassFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);

        in_PassOld = view.findViewById(R.id.in_PassOld);
        in_PassChange = view.findViewById(R.id.in_PassChange);
        in_RePassChange = view.findViewById(R.id.in_RePassChange);
        edPassOld = view.findViewById(R.id.edPassOld);
        edPass = view.findViewById(R.id.edPassChange);
        edRePass = view.findViewById(R.id.edRePassChange);
        btnSave = view.findViewById(R.id.btnSaveUserChange);
        btnCancel = view.findViewById(R.id.btnCancelUserChange);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doiMK();
            }
        });
        return view;
    }

    public void doiMK(){
        String oldPass = edPassOld.getText().toString();
        String newPass = edPass.getText().toString();
        String rePass = edRePass.getText().toString();
        if (newPass.equals(rePass)){
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE",getContext().MODE_PRIVATE);
            String matt = sharedPreferences.getString("USERNAME","");
            String mk = sharedPreferences.getString("PASSWORD","");
            //cập nhật
            ThuThuDAO thuThuDAO = new ThuThuDAO(getContext());
            boolean check = thuThuDAO.updatePass(matt,oldPass,newPass);
            if (oldPass.equals(mk)){
                if (check){
                    Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }else {
                in_PassOld.setError("Mật khẩu hiện tại không đúng");
            }
        }else {
            in_PassChange.setError("Mật khẩu không khớp");
            in_RePassChange.setError("Mật khẩu không khớp");
        }
    }

}