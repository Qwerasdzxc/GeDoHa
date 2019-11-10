package app.models.project;

import app.models.document.Document;

public interface ProjListener {
    default void onDocumentCreated(Document document) {};
    default void onProjectSelected(Project project) {};
}
