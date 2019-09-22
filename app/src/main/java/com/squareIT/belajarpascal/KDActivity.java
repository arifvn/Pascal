package com.squareIT.belajarpascal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.squareIT.belajarpascal.adapter.KDAdapter;
import com.squareIT.belajarpascal.model.KDItem;
import com.squareIT.belajarpascal.utils.KDBottomSheetDialog;

import java.util.ArrayList;

public class KDActivity extends AppCompatActivity {

    ImageView imageViewBackButton;

    private RecyclerView recyclerView;
    private ArrayList<KDItem> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private KDAdapter kdAdapter;

    private Button btnInfo;
    private KDBottomSheetDialog kdBottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kd);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        kdBottomSheetDialog = new KDBottomSheetDialog();

        btnInfo = (Button) findViewById(R.id.btn_show_info);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kdBottomSheetDialog.show(getSupportFragmentManager(), "kdBottomSheet");
            }
        });

        imageViewBackButton = (ImageView) findViewById(R.id.iv_kd_back);
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KDActivity.super.onBackPressed();
            }
        });

        setKDItemArrayList();
        setRecyclerView();
    }

    private void setKDItemArrayList() {
        arrayList = new ArrayList<KDItem>();

        arrayList.add(new KDItem("KD 3.1", "Memahami alur logika pemrograman komputer",
                "KD 4.1", "Membuat alur logika pemrograman Komputer"));
        arrayList.add(new KDItem("KD 3.2", "Memahami perangkat lunak bahasa pemrograman",
                "KD 4.2", "Melakukan Instalasi perangkat lunak bahasa pemrograman"));
        arrayList.add(new KDItem("KD 3.3", "Menerapkan alur pemrograman dengan struktur bahasa pemrograman komputer",
                "KD 4.3", "Menulis kode pemrogram sesuai dengan aturan dan sintaks bahasa pemrograman"));
        arrayList.add(new KDItem("KD 3.4", "Menerapkan penggunaan tipe data, variabel, konstanta, operator, dan ekspresi",
                "KD 4.4", "Membuat kode program dengan tipe data, variabel, konstanta, operator dan ekspresi"));
        arrayList.add(new KDItem("KD 3.5", "Menerapkan operasi aritmatika dan  logika",
                "KD 4.5", "Membuat kode program dengan operasi aritmatika dan logika"));
        arrayList.add(new KDItem("KD 3.6", "Menerapkan struktur kontrol Percabangan dalam bahasa pemrograman",
                "KD 4.6", "Membuat kode program struktur kontrol percabangan"));
        arrayList.add(new KDItem("KD 3.7", "Menerapkan struktur kontrol Perulangan dalam bahasa pemrograman",
                "KD 4.7", "Membuat kode program struktur kontrol perulangan"));
    }

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_kd);

        linearLayoutManager = new LinearLayoutManager(this);

        kdAdapter = new KDAdapter(arrayList, this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(kdAdapter);
    }
}
