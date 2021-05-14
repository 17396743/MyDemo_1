package com.example.myapplication16.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication16.R;

import java.util.ArrayList;

/**
 * @创建时间 2021/5/14 15:38
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.HomeHolder> {
    private Context context;
    private ArrayList<String> strings;

    public RecyclerViewAdapter(Context context, ArrayList<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_one, parent, false);
        return new HomeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        holder.tvTextItem.setText("  " + strings.get(position) + "   ");
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onClick(position);
                return false;
            }
        });

    }

    private OnItemClickListener listener;

    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    //第二步， 写一个公共的方法
    public void setOnLongItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder {
        TextView tvTextItem;
        CardView cvItemOne;


        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            tvTextItem = (TextView) itemView.findViewById(R.id.tv_text_item);

            cvItemOne = (CardView) itemView.findViewById(R.id.cv_item_one);
        }
    }

}
