package app.actions;


public class ActionManager {

    private static ActionManager instance = null;
	private ActNodeDelete deleteNode;
	private ActNodeRename renameNode;
	private ActNewProject newProject;
	private ActNewDocument newDocument;
	private ActNewPage newPage;
    
    private ActionManager() {
    	deleteNode= new ActNodeDelete();
		renameNode = new ActNodeRename();
		newProject = new ActNewProject();
		newDocument = new ActNewDocument();
		newPage = new ActNewPage();
    }
    
    public static ActionManager getInstance() {
        if (instance == null)
            instance = new ActionManager();

        return instance;
    }

	public ActNewProject getNewProject() {
		return newProject;
	}

	public ActNewDocument getNewDocument() {
		return newDocument;
	}

	public ActNewPage getNewPage() {
		return newPage;
	}

	public ActNodeDelete getDeleteNode() {
		return deleteNode;
	}
}
