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
        strTitle = info.getTitle();
        strSubTitle = info.getSubtitle();
        strAuthors = TextUtils.join(",", info.getAuthors());
        strDescription = info.getDescription();
        strThumbLink = info.getImageLinks().get(THUMBNAIL_URI_KEY);
        strInfoLink = info.getInfoLink();
        strPageCount = info.getPageCount();
        strPub = info.getPublisher();
        strPubDate = info.getPublishedDate();


        if (strTitle != null) {
            title.setText(strTitle);
            setTitle(strTitle);

        }
        if (strSubTitle != null) {
            subtitle.setText(strSubTitle);

        }
        if (strPubDate != null) {
            pubDate.setText("Published on " + strPubDate);

        }
        if (strPub != null) {
            pub.setText("Publisher : " + strPub);

        }
        if (strPageCount != null) {
            pageCount.setText(strPageCount + " Pages");

        }
        if (strDescription != null) {
            description.setText(strDescription);

        }
        if (strThumbLink != null) {
            Picasso.get()
                    .load(strThumbLink)
                    .centerCrop()
                    .fit()
                    .into(thumbnail);
        }
        if (strAuthors != null) {
            authors.setText("Authors : " + strAuthors);

        }

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
