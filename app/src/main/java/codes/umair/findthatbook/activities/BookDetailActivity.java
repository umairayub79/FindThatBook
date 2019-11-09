package codes.umair.findthatbook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import codes.umair.findthatbook.R;

public class BookDetailActivity extends AppCompatActivity {

    TextView title,description,authors;
    ImageView thumbnail;
    String strTitle,strDescription,strAuthors,strThumbLink,strInfoLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        authors = findViewById(R.id.authors);
        thumbnail = findViewById(R.id.thumbnail);



        Intent i = getIntent();
        strTitle = i.getStringExtra("TITLE");
        strAuthors = i.getStringExtra("AUTHORS");
        strDescription = i.getStringExtra("DESCRIPTION");
        strThumbLink = i.getStringExtra("THUMBLINK");
        strInfoLink = i.getStringExtra("INFOLINK");

        Toast.makeText(this, strThumbLink, Toast.LENGTH_SHORT).show();
        Picasso.get()
                .load(strThumbLink)
                .centerCrop()
                .fit()
                .into(thumbnail);
        title.setText(strTitle);
        authors.setText(strAuthors);
        description.setText(strDescription);


    }
}
