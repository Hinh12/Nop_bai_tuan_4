package com.example.mob2041_hinhnvph42207.Frame;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mob2041_hinhnvph42207.R;
import com.example.mob2041_hinhnvph42207.adapter.Top10Adapter;
import com.example.mob2041_hinhnvph42207.dao.ThongKeDAO;
import com.example.mob2041_hinhnvph42207.model.Sach;

import java.util.ArrayList;


public class frgtop extends Fragment {


    public frgtop() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frgtop, container, false);
        RecyclerView rcv = view.findViewById(R.id.rcv_Top);

        ThongKeDAO thongKeDAO =new ThongKeDAO(getContext());
        ArrayList<Sach> list = thongKeDAO.getTop10();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        Top10Adapter adapter = new Top10Adapter(getContext(),list);
        rcv.setAdapter(adapter);


        return view;
    }
}