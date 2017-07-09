package com.example.android.redroom;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by victo on 7/2/2017.
 */

class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private ArrayList<Book> volumes;

    public BookAdapter(ArrayList<Book> volumes) {
        this.volumes = volumes;
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, int position) {
        holder.title.setText(volumes.get(position).getVolumeTitle());
        holder.author.setText(volumes.get(position).getVolumeAuthor());

    }

    @Override
    public int getItemCount() {
        return volumes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView author;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.volume_card_title);
            author = (TextView) itemView.findViewById(R.id.volume_card_author);
        }
    }

}
