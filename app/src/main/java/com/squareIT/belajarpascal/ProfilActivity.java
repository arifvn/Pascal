package com.squareIT.belajarpascal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ProfilActivity extends AppCompatActivity {

    private ImageView imageViewBackButton;

    private ImageView imgProfil, imgMail, imgIg, imgWa;

    private LinearLayout toEmail, toIg, toWA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        imgProfil = (ImageView) findViewById(R.id.img_profil);
        imgMail = (ImageView) findViewById(R.id.img_email);
        imgIg = (ImageView) findViewById(R.id.img_ig);
        imgWa = (ImageView) findViewById(R.id.img_wa);

        toEmail = (LinearLayout) findViewById(R.id.email_sent);
        toIg = (LinearLayout) findViewById(R.id.ig_open);
        toWA = (LinearLayout) findViewById(R.id.wa_sent);

        toEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.setData(Uri.parse("mailto:taufananuwari@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Aplikasi Belajar Pascal");
                startActivity(Intent.createChooser(intent, "Kirim Email ke Pengembang"));
            }
        });

        toIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                String scheme = "http://instagram.com/_u/taufan_anwari";
                String path = "https://instagram.com/taufan_anwari";
                String nomPackageInfo = "com.instagram.android";
                try {
                    ProfilActivity.this.getPackageManager().getPackageInfo(nomPackageInfo, 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scheme));
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
                }
                startActivity(intent);
            }
        });

        toWA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.setAction(Intent.ACTION_VIEW);
                sendIntent.setPackage("com.whatsapp");
                String url = "https://api.whatsapp.com/send?phone=" + "62895339627217" + "&text=" + "Assalamualaikum Mas Taufan, Pengembang Aplikasi Belajar Pascal.";
                sendIntent.setData(Uri.parse(url));
                if(sendIntent.resolveActivity(ProfilActivity.this.getPackageManager()) != null){
                    startActivity(sendIntent);
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imgProfil.setImageResource(R.drawable.profil);
                        imgMail.setImageResource(R.drawable.ios_send);
                        imgIg.setImageResource(R.drawable.logo_instagram);
                        imgWa.setImageResource(R.drawable.logo_whatsapp);
                    }
                });
            }
        }).start();


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewBackButton = (ImageView) findViewById(R.id.iv_profil_back);
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfilActivity.super.onBackPressed();
            }
        });
    }
}
