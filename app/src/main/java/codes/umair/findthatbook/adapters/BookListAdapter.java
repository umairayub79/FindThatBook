package codes.umair.findthatbook.adapters;


import android.content.Context;
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
import codes.umair.findthatbook.models.Book;



/*
 Created by Umair Ayub on 17 Sept 2019.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.PostViewHolder> {
    private Context context;
    private static final String THUMBNAIL_URI_KEY = "smallThumbnail";
    private List<Book> data = new ArrayList<>();
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        Book book = data.get(position);
        Book.BookInfo info = book.getInfo();
        if (info != null){
            holder.title.setText(info.getTitle());
            if (info.getAuthors() != null){
                holder.authors.setText(TextUtils.join(", ",info.getAuthors()));
            }
            holder.description.setText(info.getDescription());
            if (info.getImageLinks() != null){
                String path = info.getImageLinks().get(THUMBNAIL_URI_KEY);
                Picasso.get()
                        .load(path)
                        .centerCrop()
                        .fit()
                        .into(holder.image);

            }
        }


    }


    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new PostViewHolder(itemView);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
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

    public void setData(List<Book> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public List<Book> getData(){
        return data;
    }
    class PostViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView authors;
        public TextView description;


        public PostViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            authors = itemView.findViewById(R.id.authors);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}