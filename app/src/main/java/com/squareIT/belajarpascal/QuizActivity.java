package com.squareIT.belajarpascal;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareIT.belajarpascal.model.Question;
import com.squareIT.belajarpascal.utils.QuizDbHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    public static final String EXTRA_CATEGORY = "extraCategory";

    private static final long COUNTDOWN_IN_MILLIS = 120000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_CATEGORY = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQestionList";

    private TextView textViewQuestion, textViewScore, textViewQuestionCount, textViewCountDown, textViewCorrectInfo;
    private RadioGroup rbGroup;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRadioButton;
    private ColorStateList textColorDefaultCountDown;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;
    private static Toast toastMessage = null;

    private long backPressedTime;
    private ProgressBar progressBar;
    private int i;

    int categoryId;

    private static final String TAG = "QuizActivity";

    private TextView textViewCategoryResult, textViewCategoryPoint;
    private Button kembaliKeLatihan;
    private ConstraintLayout quisResultConstraint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Initialize
        textViewQuestion = (TextView) findViewById(R.id.tv_quiz_question);
        textViewScore = (TextView) findViewById(R.id.tv_quiz_score);
        textViewQuestionCount = (TextView) findViewById(R.id.textview_questions_count);
        textViewCountDown = (TextView) findViewById(R.id.tv_qountdown);
        rbGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioButton1 = (RadioButton) findViewById(R.id.radiobutton_1);
        radioButton2 = (RadioButton) findViewById(R.id.radiobutton_2);
        radioButton3 = (RadioButton) findViewById(R.id.radiobutton_3);
        radioButton4 = (RadioButton) findViewById(R.id.radiobutton_4);
        radioButton5 = (RadioButton) findViewById(R.id.radiobutton_5);
        buttonConfirmNext = (Button) findViewById(R.id.button_confirm_next);
        textViewCorrectInfo = (TextView) findViewById(R.id.textview_correct_info);
        textViewCorrectInfo.setVisibility(View.INVISIBLE);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        //HASIL QUIZ
        quisResultConstraint = (ConstraintLayout) findViewById(R.id.result_quiz);
        textViewCategoryResult = (TextView) findViewById(R.id.tv_result_title);
        textViewCategoryPoint = (TextView) findViewById(R.id.tv_result_poin);
        kembaliKeLatihan = (Button) findViewById(R.id.btn_quiz_result);

        quisResultConstraint.setVisibility(View.GONE);

        //Ambil warna default radio button (hitam)
        textColorDefaultRadioButton = radioButton1.getTextColors();
        textColorDefaultCountDown = textViewCountDown.getTextColors();

        Intent intent = getIntent();
        categoryId = intent.getIntExtra(LatihanActivity.EXTRA_CATEGORY_ID, 0);
        String categoryName = intent.getStringExtra(LatihanActivity.EXTRA_CATEGORY_NAME);

        switch (categoryId) {
            case 1:
                textViewCategoryResult.setText("Latihan Soal 1");
                break;
            case 2:
                textViewCategoryResult.setText("Latihan Soal 2");
                break;
            case 3:
                textViewCategoryResult.setText("Latihan Soal 3");
                break;
        }

        if (savedInstanceState == null) {
            //Panggil data dari database
            QuizDbHelper quizDbHelper = QuizDbHelper.getInstance(this);

            questionList = quizDbHelper.getQuestions(categoryId);

            //jumlah object question yang di retrieve
            questionCountTotal = questionList.size();

            Collections.shuffle(questionList);

            showNextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            if (questionList == null) {
                finish();
            }
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            score = savedInstanceState.getInt(KEY_SCORE);
            currentQuestion = questionList.get(questionCounter - 1);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);

            if (!answered) {
                startCountDown();
            } else {
                updateCountDownTextView();
                showSolution();
            }
        }

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (radioButton1.isChecked() || radioButton2.isChecked()
                            || radioButton3.isChecked() || radioButton4.isChecked() || radioButton5.isChecked()) {
                        checkAnswer();
                    } else {
                        //Mencegah toast message tumpuk
                        if (toastMessage != null) {
                            toastMessage.cancel();
                        }
                        toastMessage = Toast.makeText(QuizActivity.this, "Pilih salah satu jawaban!", Toast.LENGTH_SHORT);
                        toastMessage.show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });

    }

    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();

        RadioButton radioButtonSelected = (RadioButton) findViewById(rbGroup.getCheckedRadioButtonId());
        int answeredQuestion = rbGroup.indexOfChild(radioButtonSelected) + 1;

        if (answeredQuestion == currentQuestion.getAnswerNumber()) {
            score++;
            textViewScore.setText("" + score);
        }

        showSolution();
    }

    private void showSolution() {
        radioButton1.setTextColor(getResources().getColor(R.color.red));
        radioButton2.setTextColor(getResources().getColor(R.color.red));
        radioButton3.setTextColor(getResources().getColor(R.color.red));
        radioButton4.setTextColor(getResources().getColor(R.color.red));
        radioButton5.setTextColor(getResources().getColor(R.color.red));

        switch (currentQuestion.getAnswerNumber()) {
            case 1:
                radioButton1.setTextColor(getResources().getColor(R.color.lightGreen));
                textViewCorrectInfo.setVisibility(View.VISIBLE);
                textViewCorrectInfo.setText("A");
                break;
            case 2:
                radioButton2.setTextColor(getResources().getColor(R.color.lightGreen));
                textViewCorrectInfo.setVisibility(View.VISIBLE);
                textViewCorrectInfo.setText("B");
                break;
            case 3:
                radioButton3.setTextColor(getResources().getColor(R.color.lightGreen));
                textViewCorrectInfo.setVisibility(View.VISIBLE);
                textViewCorrectInfo.setText("C");
                break;
            case 4:
                radioButton4.setTextColor(getResources().getColor(R.color.lightGreen));
                textViewCorrectInfo.setVisibility(View.VISIBLE);
                textViewCorrectInfo.setText("D");
                break;
            case 5:
                radioButton5.setTextColor(getResources().getColor(R.color.lightGreen));
                textViewCorrectInfo.setVisibility(View.VISIBLE);
                textViewCorrectInfo.setText("E");
                break;
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Lanjut");
        } else {
            buttonConfirmNext.setText("Selesai");
        }
    }

    private void showNextQuestion() {
        textViewCorrectInfo.setVisibility(View.INVISIBLE);
        radioButton1.setTextColor(textColorDefaultRadioButton);
        radioButton2.setTextColor(textColorDefaultRadioButton);
        radioButton3.setTextColor(textColorDefaultRadioButton);
        radioButton4.setTextColor(textColorDefaultRadioButton);
        radioButton5.setTextColor(textColorDefaultRadioButton);
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            radioButton1.setText(currentQuestion.getOption1());
            radioButton2.setText(currentQuestion.getOption2());
            radioButton3.setText(currentQuestion.getOption3());
            radioButton4.setText(currentQuestion.getOption4());
            radioButton5.setText(currentQuestion.getOption5());

            //Karena dimulai dari 0 counter langsung menjadi 1
            questionCounter++;
            textViewQuestionCount.setText("Soal: " + questionCounter + " dari " + questionCountTotal);

            answered = false;
            buttonConfirmNext.setText("Jawab");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {

        i = 120;

        progressBar.setMax(120);
        progressBar.setProgress(120);

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownTextView();
                progressBar.setProgress(i - 1);
                i--;
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownTextView();
                progressBar.setProgress(0);
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownTextView() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(getResources().getColor(R.color.red));
        } else {
            textViewCountDown.setTextColor(textColorDefaultCountDown);
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        resultIntent.putExtra(EXTRA_CATEGORY, categoryId);
        setResult(RESULT_OK, resultIntent);

        textViewCategoryPoint.setText(score+"");
        quisResultConstraint.setVisibility(View.VISIBLE);

        kembaliKeLatihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            //Mencegah toast message tumpuk
            if (toastMessage != null) {
                toastMessage.cancel();
            }
            toastMessage = Toast.makeText(QuizActivity.this, "Tekan tombol kembali lagi untuk selesai", Toast.LENGTH_SHORT);
            toastMessage.show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_CATEGORY, categoryId);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}
