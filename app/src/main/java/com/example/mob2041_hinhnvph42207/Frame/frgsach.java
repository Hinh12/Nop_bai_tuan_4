package com.example.mob2041_hinhnvph42207.Frame;

import android.annotation.SuppressLint;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mob2041_hinhnvph42207.R;
import com.example.mob2041_hinhnvph42207.adapter.SachAdapter;
import com.example.mob2041_hinhnvph42207.dao.LoaiSachDAO;
import com.example.mob2041_hinhnvph42207.dao.SachDao;
import com.example.mob2041_hinhnvph42207.model.LoaiSach;
import com.example.mob2041_hinhnvph42207.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class frgsach extends Fragment {

    public frgsach(){
        // Required empty public constructor
    }

    RecyclerView rcv;
    FloatingActionButton fltAdd;
    ArrayList<Sach> list;
    SachDao sachDao;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frgsach,container,false);
        rcv = view.findViewById(R.id.rcv_S);
        fltAdd = view.findViewById(R.id.add_S);

        sachDao = new SachDao(getContext());
        loadData();

        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAllSach();
            }
        });

        return view;
    }

    @SuppressLint("MissingInflatedId")
    private void dialogAllSach(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_sach,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout in_TenSach = view.findViewById(R.id.in_addTenS);
        TextInputLayout in_GiaThue = view.findViewById(R.id.in_addGiaThue);
        TextInputEditText ed_TenSach = view.findViewById(R.id.ed_addTenS);
        TextInputEditText ed_GiaThue = view.findViewById(R.id.ed_addGiaThue);
        Spinner spnSach = view.findViewById(R.id.spnSach);
        Button add = view.findViewById(R.id.S_add);
        Button cancel = view.findViewById(R.id.S_Cancel);

        ed_TenSach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_TenSach.setError("Vui lòng không để trống tên sách");
                }else{
                    in_TenSach.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_GiaThue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_GiaThue.setError("Vui lòng không để trống giá thuê");
                }else{
                    in_GiaThue.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spnSach.setAdapter(simpleAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensach = ed_TenSach.getText().toString();
                String checktien = ed_GiaThue.getText().toString();;
                HashMap<String, Object> hs = (HashMap<String, Object>) spnSach.getSelectedItem();
                int maloai = (int) hs.get("maLoai");


                if(tensach.isEmpty() || checktien.isEmpty()){
                    if(tensach.equals("")){
                        in_TenSach.setError("Vui lòng không để trống tên sách");
                    }else{
                        in_TenSach.setError(null);
                    }

                    if(checktien.equals("")){
                        in_GiaThue.setError("Vui lòng không để trống giá thuê");
                    }else{
                        in_GiaThue.setError(null);
                    }
                }else{
                    int tien = Integer.parseInt(checktien);
                    boolean check = sachDao.insert(tensach,tien,maloai);
                    if(check){
                        loadData();
                        Toast.makeText(getContext(), "Thêm thành công sách", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Thêm không thành công sách", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_TenSach.setText("");
                ed_GiaThue.setText("");
            }
        });
    }

    private ArrayList<HashMap<String , Object>> getDSLoaiSach(){
        LoaiSachDAO loaisach = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list1 = loaisach.getDSLoaiSach();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (LoaiSach ls : list1){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maLoai", ls.getMaLoai());
            hs.put("tenLoai", ls.getTenLoai());
            listHM.add(hs);
        }
        return listHM;
    }

    private void loadData(){
        list = sachDao.getDSSach();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        SachAdapter adapter = new SachAdapter(getContext(),list, getDSLoaiSach(), sachDao);
        rcv.setAdapter(adapter);
    }

}