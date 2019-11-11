package codes.umair.findthatbook.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import codes.umair.findthatbook.R;

public class BookDetailActivity extends AppCompatActivity {

    TextView title, subtitle, description, authors, pub, pubDate, pageCount;
    ImageView thumbnail;
    Button btn_openLink;
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


        Intent i = getIntent();
        strTitle = i.getStringExtra("TITLE");
        strSubTitle = i.getStringExtra("SUBTITLE");
        strAuthors = i.getStringExtra("AUTHORS");
        strDescription = i.getStringExtra("DESCRIPTION");
        strThumbLink = i.getStringExtra("THUMBLINK");
        strInfoLink = i.getStringExtra("INFOLINK");
        strPageCount = i.getStringExtra("PAGES");
        strPub = i.getStringExtra("PUB");
        strPubDate = i.getStringExtra("PUBDATE");


        setTitle(strTitle);
        Picasso.get()
                .load(strThumbLink)
                .centerCrop()
                .fit()
                .into(thumbnail);
        title.setText(strTitle);
        subtitle.setText(strSubTitle);
        pubDate.setText("Published on " + strPubDate);
        pub.setText("Publisher : " + strPub);
        pageCount.setText(strPageCount + " Pages");
        authors.setText("Authors : " + strAuthors);
        description.setText(strDescription);


        if (!strInfoLink.equals("Info Link Not Available")) {

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
