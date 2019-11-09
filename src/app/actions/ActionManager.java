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

    public ActNewPage getNewPageAction() {
		return newPage;
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
}
