package com.example.roushan.mnitflightbookiing;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ROUSHAN on 16-04-2017.
 */

public class Imageslide extends PagerAdapter {
    private  int [] image_resource={R.drawable.sky1,R.drawable.sky2,R.drawable.sky3,R.drawable.sky4};
    private Context etc;
    private LayoutInflater layoutInflater;
    public  Imageslide(Context etc)
            {
                this.etc=etc;
            }
    @Override
    public int getCount() {
        return image_resource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater =(LayoutInflater)etc.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.swap,container,false);
        ImageView imageView=(ImageView)item_view.findViewById(R.id.image);
        TextView textView=(TextView)item_view.findViewById(R.id.text);
        imageView.setImageResource(image_resource[position]);
     //   textView.setText("image:"+position);
        container.addView(item_view);


        return  item_view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
