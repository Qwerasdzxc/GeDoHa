package app.actions;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DocumentFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		return (f.isDirectory() || 
                f.getName().toLowerCase().endsWith(".geru"));
	}

	@Override
	public String getDescription() {
		return "GeRuDok Project Files (*.geru)";		
	}

}
