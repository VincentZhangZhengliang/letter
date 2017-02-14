package com.szxkbl.lettersnavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Vincent
 * @time : 2017/2/14 17:34.
 * @Discription :
 */

public class MyAdapter extends BaseAdapter {
    //上下文
    private Context mContext;
    private List<Bean> mList = new ArrayList<>();
    //布局加载器
    private LayoutInflater mInflater;

    public MyAdapter(Context context, List<Bean> list) {
        mContext = context;
        mList = list;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 通过首字母获取该首字母要显示的第一个人的下标
     *
     * @param position
     * @return
     */
    public int getPositionForNmae(int position) {
        for (int i = 0; i < getCount(); i++) {
            String letter = mList.get(i).getLetter();
            //首字母显示
            char firstChar = letter.toUpperCase().charAt(0);
            if (firstChar == position) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据名称拿到下标
     *
     * @param position
     * @return
     */
    private int getNmaeForPosition(int position) {
        return mList.get(position).getLetter().charAt(0);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //获取首字母显示人
        int firstPosition = getNmaeForPosition(i);
        //第一个
        int index = getPositionForNmae(firstPosition);

        if (index == i) {
            holder.mLetter.setVisibility(View.VISIBLE);
            holder.mLetter.setText(mList.get(i).getLetter().substring(0, 1));
        } else {
            holder.mLetter.setVisibility(View.GONE);
        }
        holder.mName.setText(mList.get(i).getName());
        return view;
    }

    class ViewHolder {
        View     mView;
        TextView mLetter;
        TextView mName;

        public ViewHolder(View view) {
            mView = view;
            mLetter = (TextView) mView.findViewById(R.id.id_tv_letter);
            mName = (TextView) mView.findViewById(R.id.id_tv_name);
        }
    }
}
