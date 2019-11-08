package codes.umair.findthatbook.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {

    private int totalItems;

    @SerializedName("items")
    private List<Book> books;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
