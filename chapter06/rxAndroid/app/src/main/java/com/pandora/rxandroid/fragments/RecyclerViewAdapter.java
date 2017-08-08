package com.pandora.rxandroid.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pandora.rxandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<RecyclerItem> mItems = new ArrayList<>();
    private PublishSubject<RecyclerItem> mPublishSubject;

    RecyclerViewAdapter() {
        this.mPublishSubject = PublishSubject.create();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final RecyclerItem item = mItems.get(position);
        holder.mImage.setImageDrawable(item.getImage());
        holder.mTitle.setText(item.getTitle());
        holder.getClickObserver(item).subscribe(mPublishSubject);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateItems(List<RecyclerItem> items) {
        mItems.addAll(items);
    }

    public void updateItems(RecyclerItem item) {
        mItems.add(item);
    }

    public PublishSubject<RecyclerItem> getItemPublishSubject() {
        return mPublishSubject;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        ImageView mImage;
        @BindView(R.id.item_title)
        TextView mTitle;

        private MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        Observable<RecyclerItem> getClickObserver(RecyclerItem item) {
            return Observable.create(e -> itemView.setOnClickListener(view -> e.onNext(item)));
        }
    }
}


