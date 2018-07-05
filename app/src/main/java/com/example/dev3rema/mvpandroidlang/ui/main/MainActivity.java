package com.example.dev3rema.mvpandroidlang.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dev3rema.mvpandroidlang.R;
import com.example.dev3rema.mvpandroidlang.data.AppDataInjector;
import com.example.dev3rema.mvpandroidlang.data.entity.Lang;
import com.example.dev3rema.mvpandroidlang.ui.addlang.AddLangActivity;
import com.example.dev3rema.mvpandroidlang.util.AppExecutors;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final int REQUEST_CODE = 1023;
    private FloatingActionButton mFab;
    private RecyclerView mainRecyclerView;

    private AppExecutors mAppExecutors;

    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFab = findViewById(R.id.fab);
        mainRecyclerView = findViewById(R.id.mainRecyclerView);
        mAppExecutors = new AppExecutors();

        createPresenter();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*// TODO: show add dialog
                new MaterialDialog.Builder(v.getContext())
                        .title("Add Android language")
                        .input("", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                // TODO
                                mPresenter.addLang(input.toString());
                            }
                        })
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .positiveText("OK")
                        .negativeText("Cancel")
                        .show();*/
                startActivityForResult(new Intent(v.getContext(), AddLangActivity.class), REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void createPresenter() {
        // creating presenter
        mPresenter = new MainPresenter(
                AppDataInjector.provideDataRepository(getApplicationContext()),
                this);

        mPresenter.start();
    }

    @Override
    public void setData(List<Lang> numbers) {
        LangAdapter adapter = new LangAdapter(this, numbers, mPresenter);

        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(this, 1);
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerView.setAdapter(adapter);
    }

}
