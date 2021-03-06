package codes.umair.findthatbook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import codes.umair.findthatbook.App;
import codes.umair.findthatbook.R;
import codes.umair.findthatbook.adapters.BookListAdapter;
import codes.umair.findthatbook.api.BooksAPI;
import codes.umair.findthatbook.models.SearchResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spencerstudios.com.bungeelib.Bungee;
import umairayub.madialog.MaDialog;
import umairayub.madialog.MaDialogListener;

public class SearchResultsActivity extends AppCompatActivity {

    public static final int MAX_RESULTS = 40;
    BookListAdapter adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    BooksAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        recyclerView = findViewById(R.id.recyclerV);
        progressBar = findViewById(R.id.progress_circular);

        adapter = new BookListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        App app = (App) getApplication();
        api = app.getApi();

        Intent i = getIntent();
        String query = i.getStringExtra("query");
        SearchBook(query);
    }

    public void SearchBook(final String query) {
        progressBar.setVisibility(View.VISIBLE);
        Call<SearchResult> call = api.searchBooks(query, MAX_RESULTS);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                SearchResult result = response.body();
                if (result != null && result.getTotalItems() > 0) {
                    adapter.setData(SearchResultsActivity.this, result.getBooks());
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                new MaDialog.Builder(SearchResultsActivity.this)
                        .setTitle("Whoops!")
                        .setMessage("Failed to Load Books")
                        .setPositiveButtonText("Try again")
                        .setPositiveButtonListener(new MaDialogListener() {
                            @Override
                            public void onClick() {
                                SearchBook(query);
                            }
                        })
                        .build();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bungee.slideDown(SearchResultsActivity.this);
    }
}
