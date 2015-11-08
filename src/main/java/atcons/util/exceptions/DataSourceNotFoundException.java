package atcons.util.exceptions;

public class DataSourceNotFoundException extends Exception {

    public DataSourceNotFoundException() {
        super("Data source not found");
    }

    public DataSourceNotFoundException(String s) {
        super("Data source with id=" + s + " not found");
    }
}
