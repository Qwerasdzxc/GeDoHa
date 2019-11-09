package app.models.document;

public interface DocObserver {
    void addObserver(DocListener listener);
    void removeObserver(DocListener listener);
}
