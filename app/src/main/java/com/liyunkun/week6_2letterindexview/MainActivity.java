package com.liyunkun.week6_2letterindexview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<User> allUser;//所有的用户集合
    private ListView lv;
    private ChineseToPinyinHelper helper;//汉字转拼音的类
    private TextView showLetterTv;
    private LetterIndexView letterIndexView;
    private MyListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = ((ListView) findViewById(R.id.lv));
        showLetterTv = ((TextView) findViewById(R.id.showLetterTv));
        letterIndexView = ((LetterIndexView) findViewById(R.id.letterIndexView));
        letterIndexView.setShowLetterTv(showLetterTv);
        helper = new ChineseToPinyinHelper();
        //填充数据
        initData();
        adapter = new MyListViewAdapter(allUser, this);
        lv.setAdapter(adapter);
        letterIndexView.setUpdateListViewItem(new LetterIndexView.UpdateListViewItem() {
            @Override
            public void updateListViewItem(int selection) {
                int section = adapter.getPositionForSection(selection);
                lv.setSelection(section);
            }
        });
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int sectionForPosition = adapter.getSectionForPosition(firstVisibleItem);
                letterIndexView.updateLetterArray(sectionForPosition);
            }
        });
    }

    private void initData() {
        allUser = new ArrayList<>();
        //获取strings.xml
        String[] names = getResources().getStringArray(R.array.arrUsernames);
        for (int i = 0; i < names.length; i++) {
            String userName = names[i];
            String pinyin = helper.getPinyin(userName);
            String firstLetter = pinyin.substring(0, 1).toUpperCase();
            if (!firstLetter.matches("[A-Z]")) {
                firstLetter = "#";
            }
            int img = R.mipmap.ic_launcher;
            User user = new User(userName, pinyin, firstLetter, img);
            allUser.add(user);
        }
        Collections.sort(allUser, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                if (user1.getFirstLetter().equals("#")) {
                    return 1;
                } else if (user2.getFirstLetter().equals("#")) {
                    return -1;
                } else {
                    return user1.getFirstLetter().compareTo(user2.getFirstLetter());
                }
            }
        });
    }
}
