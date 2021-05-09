package by.andersen.dobrov.newsapi.util.error;

@SuppressWarnings("unused")
public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String detailMessage) {
        super(detailMessage);
    }

    public BaseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseException exception = (BaseException) o;

        return getMessage() != null && getMessage().equals(exception.getMessage());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
