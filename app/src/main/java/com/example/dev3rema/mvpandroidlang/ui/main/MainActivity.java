package com.example.dev3rema.mvpandroidlang.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dev3rema.mvpandroidlang.R;
import com.example.dev3rema.mvpandroidlang.data.AppDataInjector;
import com.example.dev3rema.mvpandroidlang.data.entity.Lang;
import com.example.dev3rema.mvpandroidlang.ui.addlang.AddLangActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private FloatingActionButton mFab;
    private RecyclerView mainRecyclerView;

    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFab = findViewById(R.id.fab);
        mainRecyclerView = findViewById(R.id.mainRecyclerView);

        createPresenter();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AddLangActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    private void createPresenter() {
        // creating presenter
        mPresenter = new MainPresenter(
                AppDataInjector.provideDataRepository(getApplicationContext()),
                this);

        mPresenter.start();
    }

    @Override
    public void setData(List<Lang> langs) {
        LangAdapter adapter = new LangAdapter(this, langs, mPresenter);

        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(this, 1);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerView.setAdapter(adapter);
    }

    @Override
    public void startAddLang(int id) {
        Intent intent = new Intent(this, AddLangActivity.class);
        intent.putExtra(AddLangActivity.EDIT_LANG_ID, String.valueOf(id));
        startActivity(intent);
    }

}
