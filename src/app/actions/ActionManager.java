package app.actions;


import javax.swing.Action;

public class ActionManager {

    private static ActionManager instance;

	private ActNodeDelete deleteNode;
	private ActNodeRename renameNode;
	private ActNewNode newNode;

	private ActSaveProject saveProject;
	private ActOpenProject openProject;
    
    private ActionManager() {
    	deleteNode= new ActNodeDelete();
		renameNode = new ActNodeRename();
		newNode = new ActNewNode();
		saveProject = new ActSaveProject();
		openProject = new ActOpenProject();
    }
    
    public static ActionManager getInstance() {
        if (instance == null)
            instance = new ActionManager();

        return instance;
    }

	public ActNodeDelete getDeleteNode() {
		return deleteNode;
	}

	public ActNodeRename getRenameNode() {
		return renameNode;
	}

	public ActNewNode getNewNode() {
		return newNode;
	}

	public ActSaveProject getSaveProject() {
		return saveProject;
	}

	public ActOpenProject getOpenProject() {
		return openProject;
	}
}
