package com.example.dev3rema.mvpandroidlang.ui.addlang;

import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dev3rema.mvpandroidlang.R;
import com.example.dev3rema.mvpandroidlang.data.AppDataInjector;
import com.example.dev3rema.mvpandroidlang.data.entity.Lang;

import java.util.List;

public class AddLangActivity extends AppCompatActivity implements AddLangContract.View {

    AddLangContract.Presenter mPresenter;

    private TextInputEditText mNameLang;
    private TextInputEditText mDescriptionLang;
    private MaterialButton mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lang);

        mPresenter = new AddLangPresenter(AppDataInjector.provideDataRepository(this), this);

        mNameLang = findViewById(R.id.nameEditText);
        mDescriptionLang = findViewById(R.id.descriptionEditText);
        mButton = findViewById(R.id.saveButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void setData(List<Lang> langs) {

    }
}
