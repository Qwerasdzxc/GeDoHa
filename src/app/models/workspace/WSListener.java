package app.models.workspace;

import app.models.project.Project;

public interface WSListener {
    default void onProjectCreated(Project project) {};
}
