package app.models.project;

import java.util.EventObject;

/*Dogadjaj opisuje promenu nad modelom. Ovu klasu je moguce prosiriti tako
  da nosi informacije o tome sta je tacno u modelu promenjeno u cilju optimizacije
  iscrtavanje.*/

public class UpdateEvent extends EventObject{

	public UpdateEvent(Object source) {
		super(source);
	}
}
