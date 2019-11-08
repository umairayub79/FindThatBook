package codes.umair.findthatbook.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Book {

    private String id;
    @SerializedName("volumeInfo")
    private BookInfo info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BookInfo getInfo() {
        return info;
    }

    public void setInfo(BookInfo info) {
        this.info = info;
    }

    public class BookInfo {

        private String title;

        private List<String> authors;

        private String description;

        private String infoLink;

        private Map<String, String> imageLinks;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getAuthors() {
            return authors;
        }

        public void setAuthors(List<String> authors) {
            this.authors = authors;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getInfoLink() {
            return infoLink;
        }

        public void setInfoLink(String infoLink) {
            this.infoLink = infoLink;
        }

        public Map<String, String> getImageLinks() {
            return imageLinks;
        }

        public void setImageLinks(Map<String, String> imageLinks) {
            this.imageLinks = imageLinks;
        }
    }
}
