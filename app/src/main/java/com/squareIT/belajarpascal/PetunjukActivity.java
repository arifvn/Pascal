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
import com.squareIT.belajarpascal.model.IntroItem;

import java.util.ArrayList;
import java.util.List;

public class PetunjukActivity extends AppCompatActivity {

    private ImageView imageViewBackButton;

    private ViewPager petunjukViewPager;
    private IntroViewPagerAdapter petunjukViewPagerAdapter;
    private TabLayout tabIndikator;
    private Button btnNext;

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petunjuk);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //init view
        tabIndikator = (TabLayout) findViewById(R.id.tl_tab_indikator_petunjuk);
        btnNext = (Button) findViewById(R.id.btn_petunjuk_next);

        //Insert Item
        final List<IntroItem> introItemList = new ArrayList<>();
        introItemList.add(new IntroItem("Belajar Mudah", "Kembangkan kompetensimu dalam pemrograman Pascal. Pilih materi yang kamu inginkan.",
                R.drawable.intro_4));
        introItemList.add(new IntroItem("Materi Terstruktur", "Terdapat kompetensi dasar beserta penjelasan yang lengkap di tiap materi.",
                R.drawable.intro_2));
        introItemList.add(new IntroItem("Quiz Menarik", "Latih kompetensimu dalam pemrograman Pascal dengan menjawab soal yang tersedia.",
                R.drawable.intro_1));
        introItemList.add(new IntroItem("Glosarium Lengkap", "Bingung dengan syntax yang ada?. Buka Glosarium dan temukan istilah yang sesuai.",
                R.drawable.intro_3));

        imageViewBackButton = (ImageView) findViewById(R.id.iv_petunjuk_back);
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PetunjukActivity.super.onBackPressed();
            }
        });


        //setup viewpager
        petunjukViewPager = findViewById(R.id.petunjuk_viewpager);
        petunjukViewPagerAdapter = new IntroViewPagerAdapter(this, introItemList);
        petunjukViewPager.setAdapter(petunjukViewPagerAdapter);

        tabIndikator.setupWithViewPager(petunjukViewPager);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = petunjukViewPager.getCurrentItem();
                if (position < introItemList.size()) {
                    position++;
                    petunjukViewPager.setCurrentItem(position);
                }

                if (position == introItemList.size() - 1) {
                    loadLastScreen();
                }
            }
        });

        tabIndikator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == introItemList.size() - 1) {
                    loadLastScreen();
                } else {
                    showNav();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void loadLastScreen() {
        btnNext.setVisibility(View.GONE);
    }

    private void showNav() {
        btnNext.setVisibility(View.VISIBLE);
    }

}