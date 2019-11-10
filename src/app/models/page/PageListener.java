package app.models.page;

import app.models.project.Project;

public interface PageListener {
    default void onPageSelected(Page page) {};
}
