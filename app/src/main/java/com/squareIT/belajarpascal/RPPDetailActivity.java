package com.squareIT.belajarpascal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.squareIT.belajarpascal.model.RPPItem;

public class RPPDetailActivity extends AppCompatActivity {

    private final static String key = "RPP_ITEM";

    private ImageView imageViewBackButton;

    private TextView rppDetailTitle, textViewNumberPage;
    private PDFView pdfView;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rppdetail);

        currentPage = 1;

        RPPItem rppItem = getIntent().getParcelableExtra("RPP_ITEM");
        rppDetailTitle = (TextView) findViewById(R.id.text_rpp_detail_title);
        textViewNumberPage = (TextView) findViewById(R.id.text_number_page);
        rppDetailTitle.setText(rppItem.getKd());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewBackButton = (ImageView) findViewById(R.id.iv_rpp_detail_back);
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RPPDetailActivity.super.onBackPressed();
            }
        });

        pdfView = findViewById(R.id.pdfView_rpp);
        pdfView.fromAsset("rpp" + rppItem.getId() + ".pdf")
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .enableSwipe(true)
                .autoSpacing(true)
                .defaultPage(0)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        currentPage = page + 1;
                        textViewNumberPage.setText(currentPage + "");
                    }
                })
                .load();

    }
}
