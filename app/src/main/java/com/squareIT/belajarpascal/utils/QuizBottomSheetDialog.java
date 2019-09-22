package com.squareIT.belajarpascal.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareIT.belajarpascal.R;
import com.squareIT.belajarpascal.model.Category;

import java.util.ArrayList;

public class QuizBottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetListener listener;

    //Some stuff of Quiz
    private TextView textViewCategory1, textViewCategory2, textViewCategory3;
    private TextView textViewHigh1, textViewHigh2, textViewHigh3;

    private int highscore1, highscore2, highscore3;

    private Context mContext;

    private ArrayList<Category> categoryArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quiz_bottom_sheet_layout, container, false);

        //Initialize
        textViewCategory1 = (TextView) view.findViewById(R.id.tv_quiz_title1);
        textViewCategory2 = (TextView) view.findViewById(R.id.tv_quiz_title2);
        textViewCategory3 = (TextView) view.findViewById(R.id.tv_quiz_title3);

        textViewHigh1 = (TextView) view.findViewById(R.id.tv_quiz_high1);
        textViewHigh2 = (TextView) view.findViewById(R.id.tv_quiz_high2);
        textViewHigh3 = (TextView) view.findViewById(R.id.tv_quiz_high3);

        Button btnSoal1 = view.findViewById(R.id.btn_quiz_soal1);
        Button btnSoal2 = view.findViewById(R.id.btn_quiz_soal2);
        Button btnSoal3 = view.findViewById(R.id.btn_quiz_soal3);

        btnSoal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonClicked("Soal 1 Terpilih", 1, "Latihan Soal 1", highscore1, highscore2, highscore3);
                dismiss();
            }
        });

        btnSoal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonClicked("Soal 2 Terpilih", 2, "Latihan Soal 2", highscore1, highscore2, highscore3);
                dismiss();
            }
        });

        btnSoal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonClicked("Soal 3 Terpilih", 3, "Latihan Soal 3", highscore1, highscore2, highscore3);
                dismiss();
            }
        });

        Bundle bundle = getArguments();
        highscore1 = bundle.getInt("score1", highscore1);
        highscore2 = bundle.getInt("score2", highscore2);
        highscore3 = bundle.getInt("score3", highscore3);

        loadCategories();
        loadHighscore();

        return view;
    }

    public interface BottomSheetListener {
        void onButtonClicked(String text, int id, String categoryName, int highScore1, int highScore2, int highScore3);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;

        try {
            listener = (BottomSheetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must Implement BottomSheetListener");
        }

    }

    private void loadCategories() {
        //Buat table disini sebagai instance
        QuizDbHelper quizDbHelper = QuizDbHelper.getInstance(mContext);
        categoryArrayList = quizDbHelper.getAllCategory();

        textViewCategory1.setText(String.valueOf(categoryArrayList.get(0)));
        textViewCategory2.setText(String.valueOf(categoryArrayList.get(1)));
        textViewCategory3.setText(String.valueOf(categoryArrayList.get(2)));
    }

    public void loadHighscore() {
        highscore1 = categoryArrayList.get(0).getHighscore();
        highscore2 = categoryArrayList.get(1).getHighscore();
        highscore3 = categoryArrayList.get(2).getHighscore();

        textViewHigh1.setText("Highscore : " + highscore1);
        textViewHigh2.setText("Highscore : " + highscore2);
        textViewHigh3.setText("Highscore : " + highscore3);
    }
}
