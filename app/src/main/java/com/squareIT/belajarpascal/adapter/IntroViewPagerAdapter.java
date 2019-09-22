package com.squareIT.belajarpascal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareIT.belajarpascal.R;
import com.squareIT.belajarpascal.model.IntroItem;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<IntroItem> introItemList;

    public IntroViewPagerAdapter(Context context, List<IntroItem> introItemList) {
        this.context = context;
        this.introItemList = introItemList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.intro_item, null);

        ImageView imgIntro = layoutScreen.findViewById(R.id.iv_intro_image);
        TextView textViewTitle = layoutScreen.findViewById(R.id.tv_intro_title);
        TextView textViewCaption = layoutScreen.findViewById(R.id.tv_intro_caption);

        imgIntro.setImageResource(introItemList.get(position).getImageId());
        textViewTitle.setText(introItemList.get(position).getIntroTitle());
        textViewCaption.setText(introItemList.get(position).getIntroCaption());

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public int getCount() {
        return introItemList.size();
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
