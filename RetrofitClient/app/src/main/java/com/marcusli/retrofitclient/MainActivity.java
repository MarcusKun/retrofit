package com.marcusli.retrofitclient;

import android.graphics.drawable.Drawable;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marcusli.retrofitclient.entity.User;
import com.marcusli.retrofitclient.presenter.DataPresenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvMsg;
    private Button btnSubmit;

    private DataPresenter presenter;

    private String[] titles;
    private ArrayList<Button> btnList;

    private LinearLayout rootView;

    public TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initRootView();
        initData();
    }

    private void initData() {
        titles = new String[]{getResources().getString(R.string.query), getResources().getString(R.string.query_map),
                getResources().getString(R.string.field), getResources().getString(R.string.field_map),
                getResources().getString(R.string.part1),getResources().getString(R.string.part2),
                getResources().getString(R.string.part_map)};
        presenter = new DataPresenter(this);
        btnList = new ArrayList<>();
        tvShow = new TextView(this);
        tvShow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        tvShow.setText("显示从服务器传回来的数据");
        rootView.addView(tvShow);
        for (int i = 0; i < titles.length; ++i) {
            Button btn = new Button(this);
            btn.setText(titles[i]);
            btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            btn.setAllCaps(false);
            btn.setOnClickListener(this);
            btnList.add(btn);
            rootView.addView(btn);
        }
    }

    private void initRootView() {
        rootView = new LinearLayout(this);
        rootView.setGravity(Gravity.CENTER_HORIZONTAL);
        rootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        rootView.setOrientation(LinearLayout.VERTICAL);
        setContentView(rootView);
    }

    @Override
    public void onClick(View v) {
        if (v == btnList.get(0)) {
            presenter.getUserbyQuery("Query");
        } else if (v == btnList.get(1)) {
            User user = new User();
            user.setUsername("QueryMap");
            user.setPassword(5678);
            user.setAddress("中国");
            user.setMessage("来自客户端的消息");
            presenter.getUserByQueryMap(user);
        } else if (v == btnList.get(2)) {
            presenter.getUserByField("Field");
        } else if (v == btnList.get(3)) {
            User user = new User();
            user.setUsername("FieldMap");
            user.setPassword(8954);
            user.setAddress("俄罗斯");
            user.setMessage("来自客户端的消息");
            presenter.getUserByFieldMap(user);
        } else if (v == btnList.get(4)) {
            String path = this.getFilesDir().getPath();
            File file = new File(path, "1.txt");
            try {
                file.createNewFile();
                presenter.getDataByPart1(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (v == btnList.get(5)) {
            presenter.getDataByPart2("part2");
        } else if (v == btnList.get(6)) {
            String path = this.getFilesDir().getPath();
            File file = new File(path, "1.txt");
            try {
                file.createNewFile();
                presenter.getDataByPartMap("partMap",file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
