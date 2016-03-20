/**
 * Created by Tushar on 3/16/2016.
 */
public class Book {

    private int id;
    private String name;
    private String author;
    private String checkedOutBy;

    public Book( int id,  String name, String checkedOutBy, String author ) {
        this.id = id;
        this.name = name;
        this.checkedOutBy = checkedOutBy;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(String userName) {
        this.checkedOutBy = userName;
    }

    public String getAuthors() {
        return author;
    }

    public void setAuthors(String author) {
        this.author = author;
    }
}
