import static spark.Spark.*;
import static spark.Spark.exception;

/**
 * Created by Tushar on 3/16/2016.
 */
public class BookController extends JsonUtil {
    public BookController(final BookUtils BookUtil) {

        get("/GetAllBooks", (req, res) -> {
            return BookUtil.getAllBooks();
        }, json());

        get("/GetBookById/:id", (req, res) -> {
            String id = req.params(":id");
            Book book = BookUtil.getBook(id);
            if (book != null) {
                return book;
            }
            res.status(400);
            return new ResponseError("No book with id '%s' found", id);
        }, json());

        post("/AddBook", (req, res) -> BookUtil.createBook(
                req.queryParams("name"),
                req.queryParams("checkedByUser"),
                req.queryParams("author")
        ), json());

        put("/UpdateBook/:id", (req, res) -> BookUtil.updateBook(
                req.params(":id"),
                req.queryParams("name"),
                req.queryParams("checkedByUser")
        ), json());

        //fter((req, res) -> {
        //    res.type("application/json");
        //}, json());

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(JsonUtil.toJson(new ResponseError(e)));
        });
    }
}
