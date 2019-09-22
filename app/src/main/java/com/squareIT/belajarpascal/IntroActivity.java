package com.squareIT.belajarpascal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.squareIT.belajarpascal.adapter.IntroViewPagerAdapter;
import com.squareIT.belajarpascal.model.IntroItem;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager introViewPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabIndikator;
    private Button btnNext, btnMulai;
    private ConstraintLayout constraintLayout;

    private int position = 0;
    private Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (restorePrefsData()) {
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
            finish();
        }

        //init view
        constraintLayout = (ConstraintLayout) findViewById(R.id.contraint_layout);
        tabIndikator = (TabLayout) findViewById(R.id.tl_tab_indikator);
        btnNext = (Button) findViewById(R.id.btn_intro_next);
        btnMulai = (Button) findViewById(R.id.btn_intro_start);

        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_anim);

        btnMulai.setVisibility(View.GONE);

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

        //setup viewpager
        introViewPager = findViewById(R.id.intro_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, introItemList);
        introViewPager.setAdapter(introViewPagerAdapter);

        tabIndikator.setupWithViewPager(introViewPager);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = introViewPager.getCurrentItem();
                if (position < introItemList.size()) {
                    position++;
                    introViewPager.setCurrentItem(position);
                }

                if (position == introItemList.size() - 1) {
                    loadLastScreen();
                }
            }
        });

        tabIndikator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                switch (position) {
                    case 0:
                        constraintLayout.setBackgroundColor(getResources().getColor(R.color.darkGreen));
                        break;
                    case 1:
                        constraintLayout.setBackgroundColor(getResources().getColor(R.color.purple));
                        break;
                    case 2:
                        constraintLayout.setBackgroundColor(getResources().getColor(R.color.blue));
                        break;
                    case 3:
                        constraintLayout.setBackgroundColor(getResources().getColor(R.color.red));
                        break;
                    default:
                        constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }

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

        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);

                savePrefsData();
                finish();
            }
        });

    }

    private boolean restorePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.GONE);
        btnMulai.setVisibility(View.VISIBLE);
        tabIndikator.setVisibility(View.GONE);
        btnMulai.setAnimation(btnAnim);
    }

    private void showNav() {
        btnNext.setVisibility(View.VISIBLE);
        btnMulai.setVisibility(View.GONE);
        tabIndikator.setVisibility(View.VISIBLE);
    }

}
