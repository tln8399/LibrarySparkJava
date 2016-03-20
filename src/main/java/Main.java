/**
 * Created by Tushar on 3/16/2016.
 */

public class Main {
    public static void main(String[] args) {
        new UserController(new UserUtils());
        new BookController(new BookUtils());
    }
}
