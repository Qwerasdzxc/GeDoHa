package app.models.project;

import app.models.document.Document;

public interface ProjListener {
    void onDocumentCreated(Document document);
    void onProjectSelected(Project project);
}
