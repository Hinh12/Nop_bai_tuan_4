package com.example.mob2041_hinhnvph42207.Frame;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mob2041_hinhnvph42207.R;
import com.example.mob2041_hinhnvph42207.adapter.LoaiSachAdapter;
import com.example.mob2041_hinhnvph42207.dao.LoaiSachDAO;
import com.example.mob2041_hinhnvph42207.model.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class frgloaisach extends Fragment {

    public frgloaisach() {
        // Required empty public constructor
    }
    FloatingActionButton fltAdd;
    RecyclerView rcvLS;
    LoaiSachDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_frgloaisach, container, false);

        rcvLS = view.findViewById(R.id.rcvLS);
        fltAdd = view.findViewById(R.id.add_LS);
        dao = new LoaiSachDAO(getContext());
        loadData();

        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaLogAddLS();
            }
        });
        return  view;
    }
    public void DiaLogAddLS(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_loaisach,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout in_TenLS = view.findViewById(R.id.in_addTenLS);
        TextInputEditText ed_TenLS = view.findViewById(R.id.ed_addTenLS);
        Button AddLS = view.findViewById(R.id.LS_add);
        Button CancelLS = view.findViewById(R.id.LS_Cancel);

        ed_TenLS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_TenLS.setError("Vui lòng nhập tên loại sách");
                }else{
                    in_TenLS.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        AddLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = ed_TenLS.getText().toString();
                if(tenloai.isEmpty()){
                    if(tenloai.equals("")){
                        in_TenLS.setError("Vui lòng nhập tên loại sách");
                    }else{
                        in_TenLS.setError(null);
                    }
                }else{
                    if(dao.insert(tenloai)){
                        loadData();
                        Toast.makeText(getContext(), "Thêm loại sách thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        CancelLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_TenLS.setText("");
            }
        });

    }

    private void loadData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvLS.setLayoutManager(layoutManager);
        ArrayList<LoaiSach> list = dao.getDSLoaiSach();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(),list);
        rcvLS.setAdapter(adapter);
    }
}