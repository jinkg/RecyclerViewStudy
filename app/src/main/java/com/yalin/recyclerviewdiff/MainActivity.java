package com.yalin.recyclerviewdiff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

/**
 * YaLin
 * 2016/11/22.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new ItemAdapter(this, getItems()));

        final int cardVerticalMargin = getResources().getDimensionPixelSize(R.dimen.spacing_normal);
        recyclerView.addItemDecoration(new ItemMarginDecoration(0, cardVerticalMargin, 0, cardVerticalMargin));
    }

    private List<Item> getItems() {
        return Arrays.asList(
                new Item("DiffUtil", DiffUtilActivity.class),
                new Item("NestedRecyclerView", NestedRecyclerViewActivity.class)
        );
    }

    private class Item {
        String title;
        Class targetClazz;

        public Item(String title, Class targetClazz) {
            this.title = title;
            this.targetClazz = targetClazz;
        }
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public ItemViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.button);
        }
    }

    private static class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        private final Activity mHost;

        private final LayoutInflater mInflater;

        private List<Item> mItems;


        public ItemAdapter(Activity activity, List<Item> items) {
            mHost = activity;
            mInflater = LayoutInflater.from(activity);
            mItems = items;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final ItemViewHolder holder = new ItemViewHolder(mInflater.inflate(R.layout.main_item, parent, false));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    if (position == RecyclerView.NO_POSITION) return;
                    Item item = mItems.get(position);
                    Intent intent = new Intent(mHost, item.targetClazz);
                    mHost.startActivity(intent);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            Item item = mItems.get(position);
            holder.button.setText(item.title);
        }

        @Override
        public int getItemCount() {
            return mItems == null ? 0 : mItems.size();
        }
    }
}
