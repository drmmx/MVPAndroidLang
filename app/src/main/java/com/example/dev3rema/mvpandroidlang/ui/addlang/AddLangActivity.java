package com.example.dev3rema.mvpandroidlang.ui.addlang;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dev3rema.mvpandroidlang.R;
import com.example.dev3rema.mvpandroidlang.data.AppDataInjector;
import com.example.dev3rema.mvpandroidlang.data.entity.Lang;

import java.util.List;

public class AddLangActivity extends AppCompatActivity implements AddLangContract.View {

    public static final String EDIT_LANG_ID = "EDIT_LANG_ID";

    AddLangContract.Presenter mPresenter;

    private TextInputEditText mNameLang;
    private TextInputEditText mDescriptionLang;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lang);

        String taskId = getIntent().getStringExtra(AddLangActivity.EDIT_LANG_ID);

        mPresenter = new AddLangPresenter(taskId, AppDataInjector.provideDataRepository(this), this);

        mNameLang = findViewById(R.id.nameEditText);
        mDescriptionLang = findViewById(R.id.descriptionEditText);
        mButton = findViewById(R.id.saveButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addLang(mNameLang.getText().toString(), mDescriptionLang.getText().toString());
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void enterValues(Lang lang) {

    }

    @Override
    public void setData(List<Lang> langs) {

    }
}
