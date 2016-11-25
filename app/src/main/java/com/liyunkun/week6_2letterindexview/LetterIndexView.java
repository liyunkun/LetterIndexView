package com.liyunkun.week6_2letterindexview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by liyunkun on 2016/9/27 0027.
 */
public class LetterIndexView extends View {
    private String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private Paint paint;
    private int currentPosition = 0;
    private TextView showLetterTv;

    public void setShowLetterTv(TextView showLetterTv) {
        this.showLetterTv = showLetterTv;
    }

    public LetterIndexView(Context context) {
        this(context, null);
    }

    public LetterIndexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterIndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()));
        paint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        int letterHeight = height / letters.length;
        for (int i = 0; i < letters.length; i++) {
            String letter = letters[i];
            if (currentPosition == i) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.parseColor("#55000000"));
            }
            canvas.drawText(letter, width / 2, (i + 1) * letterHeight, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int height = getMeasuredHeight();
        float y = event.getY();
        int letterHeight = height / letters.length;
        int action = event.getAction();
        currentPosition = (int) (y / letterHeight);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                setBackgroundColor(Color.parseColor("#11000000"));
                if (showLetterTv != null) {
                    if (currentPosition > -1 && currentPosition < letters.length) {
                        showLetterTv.setVisibility(VISIBLE);
                        showLetterTv.setText(letters[currentPosition]);
                        if (updateListViewItem != null) {
                            updateListViewItem.updateListViewItem(letters[currentPosition].charAt(0));
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.TRANSPARENT);
                if (showLetterTv != null) {
                    showLetterTv.setVisibility(GONE);
                }
                break;
        }
        invalidate();
        return true;
    }

    public interface UpdateListViewItem {
        void updateListViewItem(int selection);
    }

    private UpdateListViewItem updateListViewItem;

    public void setUpdateListViewItem(UpdateListViewItem updateListViewItem) {
        this.updateListViewItem = updateListViewItem;
    }

    public void updateLetterArray(int position) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i].charAt(0) == position) {
                currentPosition = i;
                invalidate();
            }
        }
    }
}
