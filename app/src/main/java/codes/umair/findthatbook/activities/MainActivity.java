package codes.umair.findthatbook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import codes.umair.findthatbook.R;
import spencerstudios.com.bungeelib.Bungee;

public class MainActivity extends AppCompatActivity {

    Button btn_submit;
    EditText edt_query;
    ImageView img_appIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_submit = findViewById(R.id.btn_submit);
        edt_query = findViewById(R.id.edit_query);
        img_appIcon = findViewById(R.id.img_appIcon);
        img_appIcon.startAnimation(inFromTop());
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edt_query.getText().toString().isEmpty()) {
                    String query = edt_query.getText().toString();
                    Intent i = new Intent(MainActivity.this, SearchResultsActivity.class);
                    i.putExtra("query", query);
                    startActivity(i);
                    Bungee.inAndOut(MainActivity.this);
                }

            }
        });
    }


    private Animation inFromTop() {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_logo);
        return animation;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
            Bungee.zoom(MainActivity.this);

        }
        return super.onOptionsItemSelected(item);
    }


}
