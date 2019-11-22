package codes.umair.findthatbook.api;

import codes.umair.findthatbook.models.SearchResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BooksAPI {

    @GET("volumes")
    Call<SearchResult> searchBooks(@Query("q") String query, @Query("maxResults") int maxResults);
}
