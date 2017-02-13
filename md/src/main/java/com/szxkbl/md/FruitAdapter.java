package com.szxkbl.md;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author : Vincent
 * @time : 2017/2/13 16:23.
 * @Discription :
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    Context mContext;
    private List<Fruit> mFruitList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.mFruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //        private final ImageView mFruitImage;
        private final TextView mFruitName;
        private final CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
            //            mFruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
            mFruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(Context context, List<Fruit> fruits) {
        mFruitList = fruits;
        mContext = context;
    }
}
