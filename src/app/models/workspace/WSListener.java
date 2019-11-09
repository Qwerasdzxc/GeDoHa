package app.models.workspace;

import app.models.project.Project;

public interface WSListener {
    void onProjectCreated(Project project);
}
