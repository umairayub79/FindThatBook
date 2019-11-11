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
        Book book = data.get(position);
        final Book.BookInfo info = book.getInfo();
        if (info != null) {

            //Title
            holder.strTitle = info.getTitle();
            holder.title.setText(holder.strTitle);

            //Subtitle
            holder.strSubtitle = info.getSubtitle();

            //Info Link
            holder.infoLink = info.getInfoLink();

            //PageCount
            holder.strPageCount = info.getPageCount();

            //Publisher
            holder.strPublisher = info.getPublisher();

            //PublishDate
            holder.strPublishDate = info.getPublishedDate();

            //Authors
            if (info.getAuthors() != null) {
                holder.strAuthors = TextUtils.join(", ", info.getAuthors());
                holder.authors.setText(holder.strAuthors);
            } else {
                holder.strAuthors = "Unavailable";
                holder.authors.setText(holder.strAuthors);
            }

            //Description
            if (info.getDescription() != null) {
                holder.strDescription = info.getDescription();
                holder.description.setText(holder.strDescription);
            } else {
                holder.strDescription = "No Description available";
                holder.description.setText(holder.strDescription);
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

                if (holder.path != null) {
                    detailIntent.putExtra("THUMBLINK", holder.path);

                }
                if (info.getInfoLink() != null) {
                    detailIntent.putExtra("INFOLINK", info.getInfoLink());

                } else {
                    detailIntent.putExtra("INFOLINK", "Info Link Not Available");

                }
                if (holder.strTitle != null) {
                    detailIntent.putExtra("TITLE", holder.strTitle);

                } else {
                    detailIntent.putExtra("TITLE", "Title Not Available");

                }
                if (holder.strSubtitle != null) {
                    detailIntent.putExtra("SUBTITLE", holder.strSubtitle);

                } else {
                    detailIntent.putExtra("SUBTITLE", "Subtitle Not Available");

                }
                if (holder.strDescription != null) {
                    detailIntent.putExtra("DESCRIPTION", holder.strDescription);

                } else {
                    detailIntent.putExtra("DESCRIPTION", "Description Not Available");

                }
                if (holder.strAuthors != null) {
                    detailIntent.putExtra("AUTHORS", holder.strAuthors);

                } else {
                    detailIntent.putExtra("AUTHORS", "Not Available");

                }
                if (holder.strPublishDate != null) {
                    detailIntent.putExtra("PUBDATE", holder.strPublishDate);

                } else {
                    detailIntent.putExtra("PUBDATE", "Not Available");

                }
                if (holder.strPublisher != null) {
                    detailIntent.putExtra("PUB", holder.strPublisher);

                } else {
                    detailIntent.putExtra("PUB", "Not Available");

                }
                if (holder.strPageCount != null) {
                    detailIntent.putExtra("PAGES", holder.strPageCount);

                } else {
                    detailIntent.putExtra("PAGES", "Uknown number of ");

                }

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
        public String infoLink;
        public String path;
        public String strTitle;
        public String strDescription;
        public String strAuthors;
        public String strPublisher;
        public String strPublishDate;
        public String strSubtitle;
        public String strPageCount;


        public PostViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            authors = itemView.findViewById(R.id.authors);


        }
    }
}