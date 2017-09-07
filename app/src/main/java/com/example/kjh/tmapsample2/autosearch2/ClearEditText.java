package com.example.kjh.tmapsample2.autosearch2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.kjh.tmapsample2.R;
import com.example.kjh.tmapsample2.autosearch.AutoCompleteAdapter;

/**
 * Created by KIM on 2017-09-07.
 */

public class ClearEditText extends AppCompatEditText implements TextWatcher, View.OnTouchListener, View.OnFocusChangeListener{

    private Drawable logoDrawable;
    private Drawable clearDrawable;
    private OnFocusChangeListener onFocusChangeListener;
    private OnTouchListener onTouchListener;
    private IconClickListener iconClickListener;
    private AutoCompleteAdapter dataAdapter;

    public void setListViewAdpater(AutoCompleteAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
    }

    public interface IconClickListener {
        public void onClick();
    }

    public ClearEditText(Context context) {
        super(context);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setIconClickListener(IconClickListener iconClickListener) {
        this.iconClickListener = iconClickListener;
    }


    private void init() {
        Drawable tempDrawable = ContextCompat.getDrawable(getContext(), android.R.drawable.ic_notification_clear_all);
        clearDrawable = DrawableCompat.wrap(tempDrawable);
        DrawableCompat.setTintList(clearDrawable, getHintTextColors());
        clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth(), clearDrawable.getIntrinsicHeight());

        tempDrawable = ContextCompat.getDrawable(getContext(), android.R.drawable.ic_menu_search);
        logoDrawable = DrawableCompat.wrap(tempDrawable);
        DrawableCompat.setTintList(logoDrawable, getHintTextColors());
        logoDrawable.setBounds(0, 0, logoDrawable.getIntrinsicWidth(), logoDrawable.getIntrinsicHeight());
        
        setClearIconVisible(false);
        setSearchIconVisible(true);

        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    private void setSearchIconVisible(boolean visible) {
        logoDrawable.setVisible(visible, false);
        setCompoundDrawables(visible ? logoDrawable : null, null, null, null);
    }

    private void setClearIconVisible(boolean visible) {
        clearDrawable.setVisible(visible, false);
        setCompoundDrawables(null, null, visible ? clearDrawable : null, null);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.onFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isFocused()) {
            setClearIconVisible(s.length() > 0);
            dataAdapter.filter(s.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setSearchIconVisible(true);
            setClearIconVisible(false);
        }

        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int x = (int)event.getX();
        if(clearDrawable.isVisible() && x > getWidth() - getPaddingRight() - clearDrawable.getIntrinsicWidth()) {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                setError(null);
                setText(null);
            }
            return true;
        }

        if(onTouchListener != null) {
            return onTouchListener.onTouch(v, event);
        } else {
            return false;
        }
    }
}
