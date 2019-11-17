package codes.umair.findthatbook.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import codes.umair.findthatbook.R;
import codes.umair.findthatbook.models.Book;

public class BookDetailActivity extends AppCompatActivity {

    TextView title, subtitle, description, authors, pub, pubDate, pageCount;
    ImageView thumbnail;
    Button btn_openLink;
    private static final String THUMBNAIL_URI_KEY = "smallThumbnail";
    String strTitle, strSubTitle, strDescription, strAuthors, strThumbLink, strInfoLink, strPub, strPubDate, strPageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);
        description = findViewById(R.id.description);
        authors = findViewById(R.id.authors);
        thumbnail = findViewById(R.id.thumbnail);
        pageCount = findViewById(R.id.page_count);
        pub = findViewById(R.id.pub);
        pubDate = findViewById(R.id.pub_date);
        btn_openLink = findViewById(R.id.btn_openLink);


        Book book = (Book) getIntent().getSerializableExtra("book");
        final Book.BookInfo info = book.getInfo();
        if (info != null) {
            if (info.getTitle() != null) {
                strTitle = info.getTitle();

            } else {
                strTitle = "Title Unavailable";
            }
            if (info.getSubtitle() != null) {
                strSubTitle = info.getSubtitle();
            } else {
                strSubTitle = "Subtitle Unavailable";
            }
            if (info.getAuthors() != null) {
                strAuthors = TextUtils.join(", ", info.getAuthors());

            } else {
                strAuthors = "Unavailable";
            }
            if (info.getPublisher() != null) {
                strPub = info.getPublisher();

            } else {
                strPub = "Unavailable";
            }
            if (info.getPublishedDate() != null) {
                strPubDate = info.getPublishedDate();

            } else {
                strPubDate = "Unavailable";
            }
            if (info.getPageCount() != null) {
                strPageCount = info.getPageCount();

            } else {
                strPageCount = "Unknown";
            }

            if (info.getDescription() != null) {
                strDescription = info.getDescription();

            } else {
                strDescription = "Description Unavailable";
            }
            if (info.getImageLinks() != null) {
                strThumbLink = info.getImageLinks().get(THUMBNAIL_URI_KEY);
            }
        }
        title.setText(strTitle);
        setTitle(strTitle);
        subtitle.setText(strSubTitle);
        pubDate.setText("Published on " + strPubDate);
        pub.setText("Publisher : " + strPub);
        pageCount.setText(strPageCount + " Pages");
        description.setText(strDescription);
        authors.setText("Authors : " + strAuthors);

        Picasso.get()
                .load(strThumbLink)
                .centerCrop()
                .fit()
                .into(thumbnail);


        if (strInfoLink != null) {

            btn_openLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strInfoLink));
                    startActivity(browserIntent);
                }
            });
        }
    }
}
