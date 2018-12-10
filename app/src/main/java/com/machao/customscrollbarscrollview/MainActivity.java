package com.machao.customscrollbarscrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.machao.customscrollbarscrollview.customscrollbar.CustomScrollViewLayout;

/**
 * create by：mc on 2018/12/10 10:09
 * email:
 * simple
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((CustomScrollViewLayout) findViewById(R.id.mScrollView)).addContentView(View.inflate(this, R.layout.scroll_content, null));
    }
}
