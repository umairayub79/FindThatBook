package codes.umair.findthatbook.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import codes.umair.findthatbook.R;
import spencerstudios.com.bungeelib.Bungee;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bungee.zoom(AboutActivity.this);
    }
}
