package app.actions;

public class ActionManager {

    private static ActionManager instance;

	private ActNodeDelete deleteNode;
	private ActNodeRename renameNode;
	private ActNewNode newNode;

	private ActSaveWorkspace saveWorkspace;
	private ActSwitchWorkspace switchWorkspace;

	private ActSaveProject saveProject;
	private ActSaveProjectAs saveProjectAs;
	private ActOpenProject openProject;

    private ActionManager() {
    	deleteNode= new ActNodeDelete();
		renameNode = new ActNodeRename();
		newNode = new ActNewNode();
		saveWorkspace = new ActSaveWorkspace();
		switchWorkspace = new ActSwitchWorkspace();
		saveProject = new ActSaveProject();
		saveProjectAs = new ActSaveProjectAs();
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

	public ActSaveWorkspace getSaveWorkspace() {
		return saveWorkspace;
	}

	public ActSwitchWorkspace getSwitchWorkspace() {
		return switchWorkspace;
	}

	public ActSaveProject getSaveProject() {
		return saveProject;
	}

	public ActOpenProject getOpenProject() {
		return openProject;
	}

	public ActSaveProjectAs getSaveProjectAs() {
		return saveProjectAs;
	}
}
