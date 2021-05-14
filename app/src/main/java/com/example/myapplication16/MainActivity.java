package com.example.myapplication16;

import android.content.Context;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication16.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView rvMain;
    private RelativeLayout relativeLayout;
    private EditText etMain;
    private Button btnMain;
    private ArrayList<String> strings;
    private RecyclerViewAdapter recyclerViewAdapter;
    //输入法软键盘调用
    private InputMethodManager inputMethodManager;
    //RecyclerView列表的点击监听
    private int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }


    private void initData() {
        //布局适配器
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        //列表适配器
        recyclerViewAdapter = new RecyclerViewAdapter(this, strings);
        //设置适配器
        rvMain.setAdapter(recyclerViewAdapter);
        //按钮监听
        btnMain.setOnClickListener(this);
        //写入框监听
        etMain.setOnClickListener(this);
        //列表监听
        recyclerViewAdapter.setOnLongItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                pos = position;
            }
        });


    }

    //上下文菜单的设置
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "删除");
    }

    //上下文菜单的点击监听
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0: {
                strings.remove(pos);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        }
        return super.onContextItemSelected(item);
    }

    //初始化
    private void initView() {
        strings = new ArrayList<>();
        rvMain = (RecyclerView) findViewById(R.id.rv_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        etMain = (EditText) findViewById(R.id.et_main);
        btnMain = (Button) findViewById(R.id.btn_main);
        registerForContextMenu(rvMain);//为每个项创建上下文菜单

    }

    //点击监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_main: {
                //列表显示最大值（列表显示最新发送的信息）
                rvMain.smoothScrollToPosition(strings.size());
                //输入框是否写入数据
                if (!"".equals(etMain.getText().toString())) {

                    strings.add(etMain.getText().toString());
                    recyclerViewAdapter.notifyDataSetChanged();
                    etMain.setText("");

                    //隐藏输入法
                    inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(btnMain.getWindowToken(), 0); //隐藏


                } else {
                    //当输入框没有数据 自动显示输入法
                    Toast.makeText(this, "请填写内容！", Toast.LENGTH_SHORT).show();
                    //显示输入法
                    inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); //显示

                    //设置焦点
                    etMain.setFocusable(true);
                    etMain.setFocusableInTouchMode(true);
                    etMain.requestFocus();
                    etMain.requestFocusFromTouch();

                }

                break;
            }
            case R.id.et_main: {
                //列表显示最大值（列表显示最新发送的信息）
                rvMain.smoothScrollToPosition(strings.size());
                break;
            }
        }


    }
}