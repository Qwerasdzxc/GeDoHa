package app.models.project;

import app.models.document.Document;

public interface ProjObserver {
    void addObserver(ProjListener listener);
    void removeObserver(ProjListener listener);

    void notifyDocumentCreated(Document document);
    void notifyProjectSelected();
}
