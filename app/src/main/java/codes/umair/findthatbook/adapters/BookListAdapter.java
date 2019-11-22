package codes.umair.findthatbook.adapters;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import codes.umair.findthatbook.R;
import codes.umair.findthatbook.activities.BookDetailActivity;
import codes.umair.findthatbook.models.Book;



/*
 Created by Umair Ayub on 17 Sept 2019.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.PostViewHolder> {
    private Context context;
    private static final String THUMBNAIL_URI_KEY = "smallThumbnail";
    private List<Book> data = new ArrayList<>();


    @Override
    public void onBindViewHolder(final PostViewHolder holder, final int position) {
        final Book book = data.get(position);
        final Book.BookInfo info = book.getInfo();
        if (info != null) {

            //Title
            holder.title.setText(info.getTitle());

            //Authors
            if (info.getAuthors() != null) {
                holder.authors.setText(TextUtils.join(", ", info.getAuthors()));
            } else {
                holder.authors.setText("Authors Unavailable");
            }

            //ImageLinks
            if (info.getImageLinks() != null) {
                holder.path = info.getImageLinks().get(THUMBNAIL_URI_KEY);
                Picasso.get()
                        .load(holder.path)
                        .centerCrop()
                        .fit()
                        .into(holder.image);

            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(context, BookDetailActivity.class);
                detailIntent.putExtra("book", book);
                context.startActivity(detailIntent);

            }
        });


    }


    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new PostViewHolder(itemView);
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(Context context, List<Book> data) {
        this.data = data;
        notifyDataSetChanged();
        this.context = context;
    }

    public List<Book> getData() {
        return data;
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView authors;
        public TextView description;
        public String path;


        public PostViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            authors = itemView.findViewById(R.id.authors);


        }
    }
}