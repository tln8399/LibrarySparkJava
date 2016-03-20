import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tushar on 3/16/2016.
 */
public class BookUtils {

    private ArrayList<Book> books = new ArrayList<Book>();

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBook(String idStr) {
        int id = Integer.parseInt(idStr);
        return books.get(id);
    }

    public Book createBook(String name, String checkedOutBy, String author) {
        int id = getNextId();
        failIfInvalid(name, checkedOutBy);
        Book book = new Book(id, name, checkedOutBy, author);
        books.add(book);

        return book;
    }

    public Book updateBook(String idStr, String name, String checkedOutBy ) {
        int id = Integer.parseInt(idStr);
        Book book = books.get(id);
        if (book == null) {
            throw new IllegalArgumentException("No book with id '" + id + "' found");
        }
        failIfInvalid(name, checkedOutBy);
        book.setName(name);

        return book;
    }

    public Book findBookByName( String name ) {
        if( books.size() == 0 )
            return null;

        for (Book b: books ) {
            if( b.getName().equals(name) )
                return b;
        }
        return null;
    }

    private void failIfInvalid(String name, String checkedOutBy) {
        if (name == null || name.isEmpty()  ) {
            throw new IllegalArgumentException("Parameter 'book name' cannot be empty");
        }
        else if( !isValidString( name ) ) {
            throw new IllegalArgumentException("Parameter 'book name' '" + name + "' is invalid.");
        }

        if( !isValidString( checkedOutBy ) )
            throw new IllegalArgumentException("Parameter 'Checked out by user name' '" + checkedOutBy + "' is invalid.");
    }

    private boolean isValidString( String str ) {
        for( int i = 0; i < str.length(); i++ ) {
            if( ( str.charAt(i) >= 65 && str.charAt(i) <= 90 ) ||
                    ( str.charAt(i) >= 97 && str.charAt(i) <= 122 ) )
                continue;
            else
                return false;
        }
        return true;
    }

    private int getNextId() {
        return books.size();
    }
}
