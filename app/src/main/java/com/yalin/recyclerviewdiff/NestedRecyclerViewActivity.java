package com.yalin.recyclerviewdiff;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * YaLin
 * 2016/11/22.
 */

public class NestedRecyclerViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_recycler_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new ParentAdapter(this, getItems()));

        final int cardVerticalMargin = getResources().getDimensionPixelSize(R.dimen.spacing_normal);
        recyclerView.addItemDecoration(new ItemMarginDecoration(0, cardVerticalMargin, 0, cardVerticalMargin));
    }

    private List<ParentItem> getItems() {
        return Arrays.asList(
                new ParentItem("A", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)}),
                new ParentItem("B", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)}),
                new ParentItem("C", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)}),
                new ParentItem("D", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)}),
                new ParentItem("E", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)}),
                new ParentItem("F", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)}),
                new ParentItem("G", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)}),
                new ParentItem("H", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)}),
                new ParentItem("I", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)}),
                new ParentItem("J", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)}),
                new ParentItem("K", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)}),
                new ParentItem("L", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)}),
                new ParentItem("M", new ChildItem[]{new ChildItem(0),
                        new ChildItem(1),
                        new ChildItem(2),
                        new ChildItem(3),
                        new ChildItem(4),
                        new ChildItem(5),
                        new ChildItem(6),
                        new ChildItem(7),
                        new ChildItem(8)})
        );
    }

    static class ParentItem {
        String title;
        ChildItem[] children;

        public ParentItem(String title, ChildItem[] children) {
            this.title = title;
            this.children = children;
        }
    }

    static class ChildItem {
        int id;

        public ChildItem(int id) {
            this.id = id;
        }
    }

    static class ParentViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        RecyclerView children;

        ParentViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.parent_item_text);
            children = (RecyclerView) itemView.findViewById(R.id.parent_recycler_view);
        }
    }

    static class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView id;

        ChildViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    static class ParentAdapter extends RecyclerView.Adapter<ParentViewHolder> {

        private List<ParentItem> mParents;

        private final Context mContext;
        private final LayoutInflater mInflater;

        private SparseArrayCompat<Parcelable> mChildrenState;

        public ParentAdapter(Context context, List<ParentItem> parents) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
            mParents = parents;

            mChildrenState = new SparseArrayCompat<>(getItemCount());
        }

        @Override
        public ParentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ParentViewHolder holder = new ParentViewHolder(mInflater.inflate(R.layout.view_parent_item, parent, false));

            final int cardVerticalMargin = mContext.getResources().getDimensionPixelSize(R.dimen.spacing_normal);
            holder.children.addItemDecoration(new ItemMarginDecoration(0, cardVerticalMargin, 0, cardVerticalMargin));
            return holder;
        }

        @Override
        public void onBindViewHolder(ParentViewHolder holder, int position) {
            ParentItem parentItem = mParents.get(position);
            holder.title.setText(parentItem.title);
            holder.children.setAdapter(new ChildAdapter(mInflater, parentItem.children));
            holder.children.getLayoutManager().onRestoreInstanceState(
                    mChildrenState.get(getId(parentItem)));
        }

        @Override
        public void onViewRecycled(ParentViewHolder holder) {
            int position = holder.getAdapterPosition();
            if (position == RecyclerView.NO_POSITION) return;
            ParentItem parentItem = mParents.get(position);
            int id = getId(parentItem);
            mChildrenState.put(id,
                    holder.children.getLayoutManager().onSaveInstanceState());

            super.onViewRecycled(holder);
        }

        @Override
        public int getItemCount() {
            return mParents == null ? 0 : mParents.size();
        }


        private int getId(ParentItem parentItem) {
            return parentItem.title.hashCode();
        }
    }

    static class ChildAdapter extends RecyclerView.Adapter<ChildViewHolder> {

        private ChildItem[] mChildren;
        private LayoutInflater mInflater;

        ChildAdapter(LayoutInflater inflater, ChildItem[] children) {
            mInflater = inflater;
            mChildren = children;
        }

        @Override
        public ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ChildViewHolder(mInflater.inflate(R.layout.view_child_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ChildViewHolder holder, int position) {
            holder.id.setText(String.valueOf(mChildren[position].id));
        }

        @Override
        public int getItemCount() {
            return mChildren == null ? 0 : mChildren.length;
        }
    }


}
