package com.squareIT.belajarpascal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.squareIT.belajarpascal.model.MateriDetailItem;

public class MateriDetailActivity extends AppCompatActivity {

    private final static String key = "MATERI_ITEM";

    private ImageView imageViewBackButton;

    private PDFView pdfView;

    private EditText editTextCurrent, editTextTotal;
    private TextView materiTitle, materiKd, textViewPageNumber;
    private LinearLayout linearLayout;

    private int currentPage, totalPage;
    private int maxLength = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_detail);

        MateriDetailItem materiDetailItem = getIntent().getParcelableExtra("MATERI_ITEM");

        //INIT
        pdfView = findViewById(R.id.pdfView);
        editTextCurrent = (EditText) findViewById(R.id.edittext_halaman);
        editTextTotal = (EditText) findViewById(R.id.edittextTotal);
        materiTitle = (TextView) findViewById(R.id.materi_title);
        textViewPageNumber = (TextView) findViewById(R.id.text_number_page_materi);
        materiKd = (TextView) findViewById(R.id.materi_kd);
        linearLayout = (LinearLayout) findViewById(R.id.linearTouch);

        materiTitle.setText(materiDetailItem.getKd());
        materiKd.setText(materiDetailItem.getCaption());

        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(maxLength);
        editTextCurrent.setFilters(filters);

        pdfView.fromAsset(materiDetailItem.getNumber() + ".pdf")
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        currentPage = page + 1;
                        totalPage = pageCount;
                        editTextCurrent.setText(currentPage + "");
                        editTextTotal.setText("/" +totalPage);
                        textViewPageNumber.setText(currentPage + "");
                    }
                })
                .pageFling(true)
                .enableSwipe(true)
                .autoSpacing(true)
                .defaultPage(0)
                .load();

        editTextCurrent.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    currentPage = Integer.parseInt( editTextCurrent.getText().toString()) - 1;
                    linearLayout.setFocusableInTouchMode(true);
                    linearLayout.setFocusable(true);
                    pdfView.jumpTo(currentPage);

                    return true;
                }
                return false;
            }
        });


        editTextCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCurrent.setText("");
                maxLength = 2;
            }
        });


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewBackButton = (ImageView) findViewById(R.id.iv_detail_back);
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MateriDetailActivity.super.onBackPressed();
            }
        });
    }
}
