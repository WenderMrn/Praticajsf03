package br.edu.ifpb.pweb.turmas.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.edu.ifpb.pweb.turmas.dao.TurmaDAO;
import br.edu.ifpb.pweb.turmas.model.Turma;

@ManagedBean(name="turmasBean")
public class TurmasBean {
	private List<Turma> turmas;
	private Map<Long, Boolean> editavel;
	
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public Map<Long, Boolean> getEditavel() {
		return editavel;
	}

	public void setEditavel(Map<Long, Boolean> editavel) {
		this.editavel = editavel;
	}

	public void listar(ActionEvent e){
		TurmaDAO dao = new TurmaDAO();
		this.setTurmas(dao.findAll());
		editavel= new HashMap<Long, Boolean>(this.turmas.size());
		for(Turma t: this.turmas) {
			editavel.put(t.getId(), false);
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
		this.editavel.remove(turma.getId(), false);
		return null;
	}

}
