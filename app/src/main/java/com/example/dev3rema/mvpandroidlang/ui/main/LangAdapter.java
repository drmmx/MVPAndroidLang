package com.example.dev3rema.mvpandroidlang.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dev3rema.mvpandroidlang.R;
import com.example.dev3rema.mvpandroidlang.data.entity.Lang;

import java.util.List;

public class LangAdapter extends RecyclerView.Adapter<LangAdapter.ViewHolder> {

    private MainContract.View mView;
    private List<Lang> mLangs;
    private MainContract.Presenter mPresenter;

    public LangAdapter(MainContract.View view,
                       List<Lang> langs,
                       MainContract.Presenter presenter) {
        mView = view;
        mLangs = langs;
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_lang_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.langTextView.setText(
                mLangs.get(holder.getAdapterPosition()).getName()
        );

        holder.descriptionTextView.setText(mLangs.get(holder.getAdapterPosition()).getDescription());

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: click
                mPresenter.deleteLang(
                        mLangs.get(holder.getAdapterPosition())
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLangs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView langTextView;
        TextView descriptionTextView;
        ImageView deleteImageView;

        ViewHolder(View itemView) {
            super(itemView);
            langTextView = itemView.findViewById(R.id.phoneTextView);
            deleteImageView = itemView.findViewById(R.id.deleteImageView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
