package codes.umair.findthatbook.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import codes.umair.findthatbook.App;
import codes.umair.findthatbook.R;
import codes.umair.findthatbook.adapters.BookListAdapter;
import codes.umair.findthatbook.api.BooksAPI;
import codes.umair.findthatbook.models.SearchResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btn_submit;
    EditText edt_query;

    BookListAdapter adapter;
    RecyclerView recyclerView;
    BooksAPI api;
    public static final int MAX_RESULTS = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_submit = findViewById(R.id.btn_submit);
        edt_query = findViewById(R.id.edit_query);
        recyclerView = findViewById(R.id.recyclerV);

        adapter = new BookListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        App app = (App) getApplication();
        api = app.getApi();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edt_query.getText().toString().isEmpty()) {
                    String query = edt_query.getText().toString();
                    SearchBook(query);
                }

            }
        });
    }

    public void SearchBook(String query) {
        Call<SearchResult> call = api.searchBooks(query, MAX_RESULTS);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                SearchResult result = response.body();
                if (result != null && result.getTotalItems() > 0){
                    adapter.setData(MainActivity.this,result.getBooks());
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

            }
        });

    }
}
