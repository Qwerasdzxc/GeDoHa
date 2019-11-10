package app.models.page;

public interface PageObserver {
    void addObserver(PageListener listener);
    void removeObserver(PageListener listener);

    void notifyPageSelected(Page page);
}
