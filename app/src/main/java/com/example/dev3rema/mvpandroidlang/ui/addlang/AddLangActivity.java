package com.example.dev3rema.mvpandroidlang.ui.addlang;

import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dev3rema.mvpandroidlang.R;
import com.example.dev3rema.mvpandroidlang.data.AppDataInjector;
import com.example.dev3rema.mvpandroidlang.data.entity.Lang;

public class AddLangActivity extends AppCompatActivity implements AddLangContract.View {

    public static final String EDIT_LANG_ID = "EDIT_LANG_ID";

    AddLangContract.Presenter mPresenter;

    private TextInputEditText mNameLang;
    private TextInputEditText mDescriptionLang;
    private Button mButton;

    private Lang mLang;

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
                mLang.setName(mNameLang.getText().toString());
                mLang.setDescription(mDescriptionLang.getText().toString());
                mPresenter.saveLang(mLang);
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
        if (mLang == null) {
            mLang = lang;
        }
        mNameLang.setText(mLang.getName());
        mDescriptionLang.setText(mLang.getDescription());
    }
}
