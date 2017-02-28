package br.edu.ifpb.pweb.turmas.bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import br.edu.ifpb.pweb.turmas.dao.TurmaDAO;
import br.edu.ifpb.pweb.turmas.model.Turma;

@ManagedBean(name="turmasBean")
@SessionScoped
public class TurmasBean extends BaseBean{
	private List<Turma> turmas;
	private Map<Long, Boolean> editavel;
	private Turma turma;
	
	@PostConstruct
	public void init(){
		this.turma = new Turma();
		this.listar(null);
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Map<Long, Boolean> getEditavel() {
		return editavel;
	}

	public void setEditavel(Map<Long, Boolean> editavel) {
		this.editavel = editavel;
	}
	
	public void listarAlunosTurma(Turma turma){
		
		FacesContext.getCurrentInstance()
		.getExternalContext().getSessionMap().put("turma",turma);
		
		try {
			FacesContext.getCurrentInstance()
			.getExternalContext().dispatch("listar_alunos.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void listar(ActionEvent e){
		TurmaDAO dao = new TurmaDAO();
		this.setTurmas(dao.findAll());
		editavel= new HashMap<Long, Boolean>(this.turmas.size());
		for(Turma t: this.turmas) {
			editavel.put(t.getId(), false);
		}
	}
	
	public void cadastrar(){
		TurmaDAO tDao= new TurmaDAO();
		tDao.beginTransaction();
		tDao.insert(this.turma);
		tDao.commit();
		this.init();
		try {
			FacesContext.getCurrentInstance().
			getExternalContext().redirect("index.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void salvar(Turma turma) {
		TurmaDAO tDao= new TurmaDAO();
		tDao.beginTransaction();
		tDao.update(turma);
		tDao.commit();
		this.editavel.put(turma.getId(), false);
	}
	
	public String excluir(Turma turma) {
		TurmaDAO tDao= new TurmaDAO();
		tDao.beginTransaction();
		tDao.delete(turma);
		tDao.commit();
		this.turmas.remove(turma);
		this.editavel.remove(turma.getId(), false);
		return null;
	}

}
