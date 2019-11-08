package app.views.hierarchy;

import javax.swing.tree.DefaultTreeModel;

import app.models.Document;
import app.models.Page;
import app.models.Project;
import app.models.Workspace;

public class HierarchyModel extends DefaultTreeModel {

    public HierarchyModel() {
        // Root object
        super(new Workspace("Workspace"));
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof Page) {
            return null;
        } else if (parent instanceof Workspace) {
            return ((Workspace) parent).getProject(index);
        } else if (parent instanceof Project) {
            return ((Project) parent).getDocument(index);
        } else if (parent instanceof Document) {
            return ((Document) parent).getPage(index);
        }
        return getRoot();
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof Page) {
            return 0;
        } else if (parent instanceof Project) {
            return ((Project) parent).getDocumentCount();
        } else if (parent instanceof Workspace) {
            return ((Workspace) parent).getProjectsCount();
        } else if (parent instanceof Document) {
            return ((Document) parent).getPageCount();
        }
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        return (node instanceof Page);
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof Page) {
            return -1;
        } else if (parent instanceof Workspace) {
            if (child instanceof Project)
                return ((Workspace) parent).getProjectIndex((Project) child);
        } else if (parent instanceof Project) {
            return ((Project) parent).getDocumentIndex((Document) child);
        } else if (parent instanceof Document) {
            return ((Document) parent).getPageIndex((Page) child);
        }
        return -1;
    }

    public void addProject(Project project) {
        ((Workspace) getRoot()).addProject(project);
    }
}