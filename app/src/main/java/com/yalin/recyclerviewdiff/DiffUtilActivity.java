package com.yalin.recyclerviewdiff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiffUtilActivity extends AppCompatActivity {
    private static final String TAG = "DiffUtilActivity";
    private RecyclerView recyclerView;

    private Item[] initItemArray = new Item[]{
            new Item(1, "A1"),
            new Item(2, "B2"),
            new Item(3, "C3"),
            new Item(4, "D4"),
            new Item(5, "E5"),
            new Item(6, "F6"),
            new Item(7, "G7"),
            new Item(8, "H8"),
            new Item(9, "I9"),
            new Item(10, "J10"),

            new Item(11, "K11"),
            new Item(12, "L12"),
            new Item(13, "M13"),
            new Item(14, "N14"),
            new Item(15, "O15"),
            new Item(16, "P16"),
            new Item(17, "Q17"),
            new Item(18, "R18"),
            new Item(19, "S19"),
            new Item(20, "T20")
    };

    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_util);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        itemAdapter = new ItemAdapter();
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.updateItems(Arrays.asList(initItemArray));
    }

    public void diff(View view) {
        List<Item> newList = Arrays.asList(initItemArray);
        newList.set(2, new Item(100, "Z100"));
        newList.set(6, new Item(101, "Z101"));
        newList.set(10, new Item(102, "Z102"));
        newList.set(14, new Item(103, "Z103"));

        newList.set(4, new Item(5, "X5"));
        newList.set(8, new Item(9, "X9"));
        newList.set(12, new Item(13, "X13"));
        newList.set(16, new Item(17, "X17"));

        itemAdapter.updateItems(newList);
    }

    static class Item {
        int id;
        String name;

        public Item(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        final View rootView;
        final TextView itemText;

        public ItemViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            itemText = (TextView) rootView.findViewById(R.id.item_text);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        List<Item> itemList = new ArrayList<>();

        public void updateItems(final List<Item> newItemList) {
            final List<Item> oldItemList = itemList;
            DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return oldItemList == null ? 0 : oldItemList.size();
                }

                @Override
                public int getNewListSize() {
                    return newItemList == null ? 0 : newItemList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    Item oldItem = oldItemList.get(oldItemPosition);
                    Item newItem = newItemList.get(newItemPosition);
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Item oldItem = oldItemList.get(oldItemPosition);
                    Item newItem = newItemList.get(newItemPosition);
                    return TextUtils.equals(oldItem.name, newItem.name);
                }
            }).dispatchUpdatesTo(this);
            itemList.clear();
            itemList.addAll(newItemList);
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(DiffUtilActivity.this)
                    .inflate(R.layout.view_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            Item item = itemList.get(position);
            holder.itemText.setText(item.name);
        }

        @Override
        public int getItemCount() {
            return itemList == null ? 0 : itemList.size();
        }
    }
}
