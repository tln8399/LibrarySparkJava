/**
 * Created by Tushar on 3/16/2016.
 */
import static spark.Spark.*;

public class UserController extends JsonUtil {

    public UserController(final UserUtils UserUtil) {

        get("/getUsers", (req, res) -> {
            return UserUtil.getAllUsers();
        }, json());

        get("/getUser/:id", (req, res) -> {
            String id = req.params(":id");
            User user = UserUtil.getUser(id);
            if (user != null) {
                return user;
            }
            res.status(400);
            return new ResponseError("No user with id '%s' found", id);
        }, json());

        post("/createUser", (req, res) -> UserUtil.createUser(
                req.queryParams("fName"),
                req.queryParams("mName"),
                req.queryParams("lName"),
                req.queryParams("age"),
                req.queryParams("gender"),
                req.queryParams("phone"),
                req.queryParams("zip")
        ), json());

        put("/updateUser/:id", (req, res) -> UserUtil.updateUser(
                req.queryParams("idStr"),
                req.queryParams("fName"),
                req.queryParams("mName"),
                req.queryParams("lName"),
                req.queryParams("age"),
                req.queryParams("gender"),
                req.queryParams("phone"),
                req.queryParams("zip")
        ), json());

        //after((req, res) -> {
        //    res.type("application/json");
        //}, json());

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(JsonUtil.toJson(new ResponseError(e)));
        });
    }
}

