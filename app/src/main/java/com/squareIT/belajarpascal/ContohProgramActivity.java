package com.squareIT.belajarpascal;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.squareIT.belajarpascal.adapter.IntroViewPagerAdapter;
import com.squareIT.belajarpascal.adapter.ProgramPagerAdapter;
import com.squareIT.belajarpascal.model.IntroItem;
import com.squareIT.belajarpascal.model.ProgramItem;

import java.util.ArrayList;
import java.util.List;

public class ContohProgramActivity extends AppCompatActivity {

    private ImageView imageViewBackButton;

    private ViewPager programViewPager;
    private ProgramPagerAdapter programPagerAdapter;
    private TabLayout tabIndikatorProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contoh_program);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewBackButton = (ImageView) findViewById(R.id.iv_contohprogram_back);
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContohProgramActivity.super.onBackPressed();
            }
        });

        tabIndikatorProgram = (TabLayout) findViewById(R.id.program_tab_indikator);

        //Insert Item
        final List<ProgramItem> programItemsList = new ArrayList<>();
        programItemsList.add(new ProgramItem("Program Perkalian", getString(R.string.program1),
                getString(R.string.program1_hasil)));
        programItemsList.add(new ProgramItem("Program Pembanding Angka", getString(R.string.program2),
                getString(R.string.program2_hasil)));
        programItemsList.add(new ProgramItem("Program Jumlah Ganjil Genap", getString(R.string.program3),
                getString(R.string.program3_hasil)));
        programItemsList.add(new ProgramItem("Program Modulus", getString(R.string.program4),
                getString(R.string.program4_hasil)));
        programItemsList.add(new ProgramItem("Program Cetak Ganjil", getString(R.string.program5),
                getString(R.string.program5_hasil)));
        programItemsList.add(new ProgramItem("Program Penjumlahan", getString(R.string.program6),
                getString(R.string.program6_hasil)));
        programItemsList.add(new ProgramItem("Program Contoh Logika", getString(R.string.program7),
                getString(R.string.program7_hasil)));

        //setup viewpager
        programViewPager = findViewById(R.id.program_viewpager);
        programPagerAdapter = new ProgramPagerAdapter(this, programItemsList);
        programViewPager.setAdapter(programPagerAdapter);

        tabIndikatorProgram.setupWithViewPager(programViewPager);


    }
}
