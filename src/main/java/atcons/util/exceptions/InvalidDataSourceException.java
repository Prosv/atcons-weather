package atcons.util.exceptions;

public class InvalidDataSourceException extends Exception {
    public InvalidDataSourceException() {
        super("Invalid data source: URL or type is null");
    }
}
