package com.squareIT.belajarpascal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareIT.belajarpascal.adapter.RPPAdapter;
import com.squareIT.belajarpascal.model.RPPItem;

import java.util.ArrayList;

public class RPPActivity extends AppCompatActivity {

    private ImageView imageViewBackButton;

    private RecyclerView recyclerView;
    private ArrayList<RPPItem> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private RPPAdapter rppAdapter;
    private Intent intent;

    private final static String key = "RPP_ITEM";
    private RPPItem rppItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpp);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewBackButton = (ImageView) findViewById(R.id.iv_rpp_back);
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RPPActivity.super.onBackPressed();
            }
        });

        setRPPItemList();
        setRecyclerView();
    }

    private void setRPPItemList() {
        arrayList = new ArrayList<RPPItem>();

        arrayList.add(new RPPItem(1,"RPP 1", "Alur Logika Pemrograman"));
        arrayList.add(new RPPItem(2,"RPP 2", "Perangkat Lunak Bahasa Pemrograman"));
        arrayList.add(new RPPItem(3,"RPP 3", "Struktur Bahasa Pemrograman"));
        arrayList.add(new RPPItem(4,"RPP 4", "Tipe Data, Variabel, Konstanta, Operator, dan Ekspresi"));
        arrayList.add(new RPPItem(5,"RPP 5", "Operasi Aritmatika dan  Logika"));
        arrayList.add(new RPPItem(6,"RPP 6", "Struktur Kontrol Percabangan"));
    }

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_rpp);

        linearLayoutManager = new LinearLayoutManager(this);

        rppAdapter = new RPPAdapter(arrayList, this);
        rppAdapter.setOnItemClickListener(new RPPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        rppItem = new RPPItem(arrayList.get(0).getId(), arrayList.get(0).getKd(), arrayList.get(0).getTitle());
                        intent = new Intent(RPPActivity.this, RPPDetailActivity.class);
                        intent.putExtra(key, rppItem);
                        startActivity(intent);
                        break;
                    case 1:
                        rppItem = new RPPItem(arrayList.get(1).getId(), arrayList.get(1).getKd(), arrayList.get(1).getTitle());
                        intent = new Intent(RPPActivity.this, RPPDetailActivity.class);
                        intent.putExtra(key, rppItem);
                        startActivity(intent);
                        break;
                    case 2:
                        rppItem = new RPPItem(arrayList.get(2).getId(), arrayList.get(2).getKd(), arrayList.get(2).getTitle());
                        intent = new Intent(RPPActivity.this, RPPDetailActivity.class);
                        intent.putExtra(key, rppItem);
                        startActivity(intent);
                        break;
                    case 3:
                        rppItem = new RPPItem(arrayList.get(3).getId(), arrayList.get(3).getKd(), arrayList.get(3).getTitle());
                        intent = new Intent(RPPActivity.this, RPPDetailActivity.class);
                        intent.putExtra(key, rppItem);
                        startActivity(intent);
                        break;
                    case 4:
                        rppItem = new RPPItem(arrayList.get(4).getId(), arrayList.get(4).getKd(), arrayList.get(4).getTitle());
                        intent = new Intent(RPPActivity.this, RPPDetailActivity.class);
                        intent.putExtra(key, rppItem);
                        startActivity(intent);
                        break;
                    case 5:
                        rppItem = new RPPItem(arrayList.get(5).getId(), arrayList.get(5).getKd(), arrayList.get(5).getTitle());
                        intent = new Intent(RPPActivity.this, RPPDetailActivity.class);
                        intent.putExtra(key, rppItem);
                        startActivity(intent);
                        break;
                }
            }
        });

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(rppAdapter);
    }


}
