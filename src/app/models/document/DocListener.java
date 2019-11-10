package app.models.document;

import app.models.page.Page;

public interface DocListener {
    default void onDocumentSelected(Document document) {};
    default void onPageCreated(Page page) {};
    default void onPageDeleted(Page page) {};
}
