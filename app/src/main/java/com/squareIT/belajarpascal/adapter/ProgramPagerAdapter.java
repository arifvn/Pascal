package com.squareIT.belajarpascal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareIT.belajarpascal.R;
import com.squareIT.belajarpascal.model.ProgramItem;

import java.util.List;

public class ProgramPagerAdapter extends PagerAdapter {
    private Context context;
    private List<ProgramItem> programItemList;

    public ProgramPagerAdapter(Context context, List<ProgramItem> programItemList) {
        this.context = context;
        this.programItemList = programItemList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.contoh_program_item, null);


        TextView textViewTitle = layoutScreen.findViewById(R.id.text_program_title);
        TextView textViewInput = layoutScreen.findViewById(R.id.text_program_input);
        final TextView textViewOutput = layoutScreen.findViewById(R.id.text_program_output);

        textViewOutput.setVisibility(View.INVISIBLE);

        Button btnEksekusi = layoutScreen.findViewById(R.id.btn_program_eksekusi);
        btnEksekusi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewOutput.setVisibility(View.VISIBLE);
            }
        });


        textViewTitle.setText(programItemList.get(position).getProgramTitle());
        textViewInput.setText(programItemList.get(position).getProgramInput());
        textViewOutput.setText(programItemList.get(position).getProgramOutput());

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public int getCount() {
        return programItemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
