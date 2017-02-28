package br.edu.ifpb.pweb.turmas.bean;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

public abstract class BaseBean {
	private FacesContext fc = FacesContext.getCurrentInstance();
	
	
	public void addMessage(String message,Severity type){
		FacesMessage msg = new FacesMessage(message);
		msg.setSeverity(type);
		fc.addMessage(null, msg);
	}
	
}
