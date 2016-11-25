package com.liyunkun.week6_2letterindexview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

/**
 * Created by liyunkun on 2016/9/27 0027.
 */
public class MyListViewAdapter extends BaseAdapter implements SectionIndexer {

    private List<User> users;
    private Context context;
    private LayoutInflater inflater;

    public MyListViewAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
            holder.userNameTv = (TextView) convertView.findViewById(R.id.userNameTv);
            holder.firstLetterTv = (TextView) convertView.findViewById(R.id.firstLetterTv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        User user = users.get(position);
        holder.userNameTv.setText(user.getUserName());
        int sectionForPosition = getSectionForPosition(position);
        int positionForSection = getPositionForSection(sectionForPosition);
        if (positionForSection == position) {
            holder.firstLetterTv.setVisibility(View.VISIBLE);
            holder.firstLetterTv.setText(user.getFirstLetter());
        } else {
            holder.firstLetterTv.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getFirstLetter().charAt(0) == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return users.get(position).getFirstLetter().charAt(0);
    }

    private class ViewHolder {
        TextView userNameTv;
        TextView firstLetterTv;
    }
}
