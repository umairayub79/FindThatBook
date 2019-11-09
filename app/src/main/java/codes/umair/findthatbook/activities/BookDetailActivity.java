package codes.umair.findthatbook.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import codes.umair.findthatbook.R;

public class BookDetailActivity extends AppCompatActivity {

    TextView title,description,authors;
    String strTitle,strDescription,strAuthors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        authors = findViewById(R.id.authors);



        Intent i = getIntent();
        strTitle = i.getStringExtra("TITLE");
        strAuthors = i.getStringExtra("AUTHORS");
        strDescription = i.getStringExtra("DESCRIPTION");

        title.setText(strTitle);
        authors.setText(strAuthors);
        description.setText(strDescription);


    }
}
