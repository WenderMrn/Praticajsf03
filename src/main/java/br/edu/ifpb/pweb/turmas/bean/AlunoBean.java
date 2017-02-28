package br.edu.ifpb.pweb.turmas.bean;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;

import br.edu.ifpb.pweb.turmas.dao.AlunoDAO;
import br.edu.ifpb.pweb.turmas.dao.TurmaDAO;
import br.edu.ifpb.pweb.turmas.model.Aluno;
import br.edu.ifpb.pweb.turmas.model.Turma;

@ManagedBean(name="alunoBean")
@SessionScoped
public class AlunoBean {
	private Turma turma;
	private Aluno aluno;
	
	@PostConstruct
	public void init(){
		this.aluno = new Aluno();
		this.turma = new Turma();
		this.listar();
	}
	
	public Turma getTurma() {
		return turma;
	}


	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void listar(){
		Turma t = (Turma)FacesContext.getCurrentInstance().
				getExternalContext().getSessionMap().get("turma");
		
		if(t != null){
			// buscar a turma no banco para manter a turma atualizada
			TurmaDAO turmadao = new TurmaDAO();
			this.turma = turmadao.find(t.getId());
		}
		
	}
	
	public void TelaCadastroAluno(){
		try {
			FacesContext.getCurrentInstance()
			.getExternalContext().dispatch("cadastrar_aluno.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void matricular(){
		AlunoDAO alunodao = new AlunoDAO();
		alunodao.beginTransaction();
		this.aluno.setTurma(this.turma);
		alunodao.insert(this.aluno);
		alunodao.commit();
		this.aluno = new Aluno();
		try {
			FacesContext.getCurrentInstance().
			getExternalContext().redirect("listar_alunos.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
