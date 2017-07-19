package com.example.android.redroom;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by victo on 7/2/2017.
 */

class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private ArrayList<Book> books;

    public BookAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, final int position) {
        holder.cardTitleView.setText(books.get(position).getBookTitle());
        holder.cardAuthorView.setText(books.get(position).getBookAuthor());
        holder.cardImageView.setImageBitmap(books.get(position).getBookImage());

        holder.cardImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = books.get(position).getBookTitle();
                String author = books.get(position).getBookAuthor();
                Bitmap image = books.get(position).getBookImage();
                String previewLink = books.get(position).getBookPreviewLink();
                String webReaderLink = books.get(position).getBookWebReaderLink();
                String textSnippet = books.get(position).getBookTextSnippet();
                String description = books.get(position).getBookDescription();
                String ratingAverage = books.get(position).getBookRatingAverage();
                String ratingCount = books.get(position).getBookRatingCount();

                Intent goToDescriptionActivity = new Intent(v.getContext(), ActivityBookDetail.class);
                goToDescriptionActivity.putExtra("title", title);
                goToDescriptionActivity.putExtra("author", author);
                goToDescriptionActivity.putExtra("image", image);
                goToDescriptionActivity.putExtra("previewLink", previewLink);
                goToDescriptionActivity.putExtra("webReaderLink", webReaderLink);
                goToDescriptionActivity.putExtra("textSnippet", textSnippet);
                goToDescriptionActivity.putExtra("description", description);
                goToDescriptionActivity.putExtra("ratingAverage", ratingAverage);
                goToDescriptionActivity.putExtra("ratingCount", ratingCount);
                v.getContext().startActivity(goToDescriptionActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void AddAll(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView cardTitleView;
        private TextView cardAuthorView;
        private ImageView cardImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardTitleView = (TextView) itemView.findViewById(R.id.book_card_title);
            cardAuthorView = (TextView) itemView.findViewById(R.id.book_card_author);
            cardImageView = (ImageView) itemView.findViewById(R.id.book_card_image);
        }
    }
}
