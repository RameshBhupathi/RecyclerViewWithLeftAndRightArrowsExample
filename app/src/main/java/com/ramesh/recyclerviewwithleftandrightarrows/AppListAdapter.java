package com.ramesh.recyclerviewwithleftandrightarrows;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ramesh.recyclerviewwithleftandrightarrows.model.App;

import java.util.List;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.MyViewHolder> {

    Context context;
    List<App> appList;
    private int SHOW_INTERSTITIAL_AD = 5080;
    private String writingsCatageory;
    private int width, height, itemWidth;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        ImageView imageViewIcon;

        public MyViewHolder(View view) {
            super(view);
            imageViewIcon = (ImageView) view.findViewById(R.id.imageViewIcon);
            textViewName = (TextView) view.findViewById(R.id.textViewName);

            //Setting Item width to 80% of device width
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(itemWidth,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
        }
    }

    public AppListAdapter(Context context, List<App> appList) {
        this.context = context;
        this.appList = appList;

        //Calculate the width and height of device
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        this.width = size.x;
        this.height = size.y;

        //80% of screen width
        itemWidth = (width * 80 / 100);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final App app = appList.get(position);
        holder.textViewName.setText(app.name);
        Glide.with(context)
                .load(app.drawable)
                .into(holder.imageViewIcon);

    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

}

