package com.squareIT.belajarpascal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareIT.belajarpascal.adapter.MateriAdapter;
import com.squareIT.belajarpascal.model.MateriDetailItem;
import com.squareIT.belajarpascal.model.MateriItem;

import java.util.ArrayList;

public class MateriActivity extends AppCompatActivity {

    private ImageView imageViewBackButton;

    private RecyclerView recyclerView;
    private ArrayList<MateriItem> arrayList;
    private LinearLayoutManager linearLayoutManager;
    private MateriAdapter materiAdapter;
    private Intent intent;

    private final static String key = "MATERI_ITEM";
    private MateriDetailItem materiDetailItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewBackButton = (ImageView) findViewById(R.id.iv_materi_back);
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MateriActivity.super.onBackPressed();
            }
        });

        setMateriItemList();
        setRecyclerView();

    }

    private void setMateriItemList() {
        arrayList = new ArrayList<MateriItem>();

        arrayList.add(new MateriItem("1", "Alur Logika Pemrograman",
                "Dilihat dari istilahnya algoritma, berasal dari nama seorang ..."));
        arrayList.add(new MateriItem("2", "Perangkat Lunak Bahasa Pemrograman",
                "Kalian tentu tidak asing lagi dengan program komputer ..."));
        arrayList.add(new MateriItem("3", "Struktur Bahasa Pemrograman",
                "Pada bagian ini saya menyarankan anda untuk memahami ..."));
        arrayList.add(new MateriItem("4", "Tipe Data, Variabel, Konstanta, Operator, dan Ekspresi",
                "Kalian masih ingat apakah arti variabel? Iya ..."));
        arrayList.add(new MateriItem("5", "Operasi Aritmatika dan  Logika",
                "Operasi Artimatika (+) adalah operator untuk ..."));
        arrayList.add(new MateriItem("6", "Struktur Kontrol Percabangan",
                "Kondisi percabangan adalah sebuah struktur dalam ..."));
        arrayList.add(new MateriItem("7", "Struktur Kontrol Perulangan",
                "Dua struktur utama, percabangan dan perulangan merupakan ..."));
    }

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_materi);

        linearLayoutManager = new LinearLayoutManager(this);

        materiAdapter = new MateriAdapter(arrayList, this);
        materiAdapter.setOnItemClickListener(new MateriAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        materiDetailItem = new MateriDetailItem(
                                getResources().getString(R.string.kd1),
                                getResources().getString(R.string.caption1),
                                getResources().getString(R.string.number1));
                        intent = new Intent(MateriActivity.this, MateriDetailActivity.class);
                        intent.putExtra(key, materiDetailItem);
                        startActivity(intent);
                        break;
                    case 1:
                        materiDetailItem = new MateriDetailItem(
                                getResources().getString(R.string.kd2),
                                getResources().getString(R.string.caption2),
                                getResources().getString(R.string.number2));
                        intent = new Intent(MateriActivity.this, MateriDetailActivity.class);
                        intent.putExtra(key, materiDetailItem);
                        startActivity(intent);
                        break;
                    case 2:
                        materiDetailItem = new MateriDetailItem(
                                getResources().getString(R.string.kd3),
                                getResources().getString(R.string.caption3),
                                getResources().getString(R.string.number3));
                        intent = new Intent(MateriActivity.this, MateriDetailActivity.class);
                        intent.putExtra(key, materiDetailItem);
                        startActivity(intent);
                        break;
                    case 3:
                        materiDetailItem = new MateriDetailItem(
                                getResources().getString(R.string.kd4),
                                getResources().getString(R.string.caption4),
                                getResources().getString(R.string.number4));
                        intent = new Intent(MateriActivity.this, MateriDetailActivity.class);
                        intent.putExtra(key, materiDetailItem);
                        startActivity(intent);
                        break;
                    case 4:
                        materiDetailItem = new MateriDetailItem(
                                getResources().getString(R.string.kd5),
                                getResources().getString(R.string.caption5),
                                getResources().getString(R.string.number5));
                        intent = new Intent(MateriActivity.this, MateriDetailActivity.class);
                        intent.putExtra(key, materiDetailItem);
                        startActivity(intent);
                        break;
                    case 5:
                        materiDetailItem = new MateriDetailItem(
                                getResources().getString(R.string.kd6),
                                getResources().getString(R.string.caption6),
                                getResources().getString(R.string.number6));
                        intent = new Intent(MateriActivity.this, MateriDetailActivity.class);
                        intent.putExtra(key, materiDetailItem);
                        startActivity(intent);
                        break;
                    case 6:
                        materiDetailItem = new MateriDetailItem(
                                getResources().getString(R.string.kd7),
                                getResources().getString(R.string.caption7),
                                getResources().getString(R.string.number7));
                        intent = new Intent(MateriActivity.this, MateriDetailActivity.class);
                        intent.putExtra(key, materiDetailItem);
                        startActivity(intent);
                        break;
                }
            }
        });

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(materiAdapter);
    }
}
