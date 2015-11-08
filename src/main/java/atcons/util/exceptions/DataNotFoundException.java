package atcons.util.exceptions;

public class DataNotFoundException extends Exception {

    public DataNotFoundException() {
        super("Data source not found");
    }

    public DataNotFoundException(String s) {
        super("Data source [ " + s + " ] not found");
    }

}
