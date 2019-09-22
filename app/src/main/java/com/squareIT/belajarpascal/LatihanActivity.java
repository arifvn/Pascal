package com.squareIT.belajarpascal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareIT.belajarpascal.utils.QuizBottomSheetDialog;
import com.squareIT.belajarpascal.utils.QuizDbHelper;

public class LatihanActivity extends AppCompatActivity implements QuizBottomSheetDialog.BottomSheetListener {

    private ImageView imageViewBackButton;

    private TextView buttonPilihQuiz;
    private QuizBottomSheetDialog quizBottomSheetDialog;
    private Button buttonMulaiQuiz;
    private static Toast toastMessage = null;
    private TextView textViewSoalTerpilih;
    private int idSoal = 0;
    private String quizCategoryName = "";

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_CATEGORY_ID = "extraCategoryId";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";

    private static int currentHighscore1, currentHighscore2, currentHighscore3;

    private static final String TAG = "LatihanActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewBackButton = (ImageView) findViewById(R.id.iv_latihan_back);
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatihanActivity.super.onBackPressed();
            }
        });

        textViewSoalTerpilih = (TextView) findViewById(R.id.tv_quiz_soal_terpilih);
        textViewSoalTerpilih.setText("");
        textViewSoalTerpilih.setVisibility(View.INVISIBLE);

        buttonMulaiQuiz = (Button) findViewById(R.id.btn_quiz_mulai);
        buttonMulaiQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textViewSoalTerpilih.getText() == "") {
                    //Mencegah toast message tumpuk
                    if (toastMessage != null) {
                        toastMessage.cancel();
                    }
                    toastMessage = Toast.makeText(LatihanActivity.this, "Pilih salah satu Kuiz!", Toast.LENGTH_SHORT);
                    toastMessage.show();
                } else {
                    startQuiz();
                }
            }
        });

        quizBottomSheetDialog = new QuizBottomSheetDialog();

        buttonPilihQuiz = (TextView) findViewById(R.id.btn_quiz_pilih);
        buttonPilihQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("score1", currentHighscore1);
                bundle.putInt("score2", currentHighscore2);
                bundle.putInt("score3", currentHighscore3);
                quizBottomSheetDialog.setArguments(bundle);
                quizBottomSheetDialog.show(getSupportFragmentManager(), "quizBottomSheet");
            }
        });
    }

    @Override
    public void onButtonClicked(String text, int id, String categoryName, int highscore1, int highscore2, int highscore3) {
        idSoal = id;
        Log.e(TAG, "onButtonClicked: " + id);

        switch (id) {
            case 1:
                currentHighscore1 = highscore1;
                break;
            case 2:
                currentHighscore2 = highscore2;
                break;
            case 3:
                currentHighscore3 = highscore3;
                break;
        }

        quizCategoryName = categoryName;
        textViewSoalTerpilih.setText(text);
        textViewSoalTerpilih.setVisibility(View.VISIBLE);
    }

    private void startQuiz() {
        //Ambli category yang dipilih kirim ke QuizActivity
        Intent intent = new Intent(LatihanActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, idSoal);
        intent.putExtra(EXTRA_CATEGORY_NAME, quizCategoryName);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                int categoryId = data.getIntExtra(QuizActivity.EXTRA_CATEGORY, 0);

                switch (categoryId) {
                    case 1:
                        if (score > currentHighscore1) {
                            currentHighscore1 = score;
                            updateHighscoreToDatabase(score, categoryId);
                        }
                        break;
                    case 2:
                        if (score > currentHighscore2) {
                            currentHighscore2 = score;
                            updateHighscoreToDatabase(score, categoryId);
                        }
                        break;
                    case 3:
                        if (score > currentHighscore3) {
                            currentHighscore3 = score;
                            updateHighscoreToDatabase(score, categoryId);
                        }
                        break;
                }
            }
        }
    }

    public void updateHighscoreToDatabase(int newScore, int categoryId) {
        QuizDbHelper quizDbHelper = QuizDbHelper.getInstance(this);
        quizDbHelper.updateHighscore(newScore, categoryId);
    }
}
