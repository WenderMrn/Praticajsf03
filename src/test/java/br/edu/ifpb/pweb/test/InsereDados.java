package br.edu.ifpb.pweb.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.edu.ifpb.pweb.turmas.dao.ManagedEMContext;
import br.edu.ifpb.pweb.turmas.dao.PersistenceUtil;
import br.edu.ifpb.pweb.turmas.dao.TurmaDAO;
import br.edu.ifpb.pweb.turmas.model.Aluno;
import br.edu.ifpb.pweb.turmas.model.Turma;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InsereDados {
	
	private static EntityManagerFactory emf;
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	private EntityManager em;

	@BeforeClass
	public static void init() {
		PersistenceUtil.getEntityManagerFactory();
		emf = PersistenceUtil.getEntityManagerFactory();
		ManagedEMContext.bind(emf, emf.createEntityManager());
		System.out.println("init()");
	}

	@AfterClass
	public static void destroy() {
		if (emf != null) {
			emf.close();
			System.out.println("destroy()");
		}
	}

	@Before
	public void initEM() {
		em = emf.createEntityManager();
	};
	
	@Test
	public void all() throws ParseException{
		Aluno a1 = new Aluno();
		a1.setNome("Marcos Silva");
		a1.setEmail("marcos.s@email.com");
		a1.setMatricula("111");
		
		Aluno a2 = new Aluno();
		a2.setNome("Mariana Silva");
		a2.setEmail("mariana.s@email.com");
		a2.setMatricula("222");
		
		Aluno a3 = new Aluno();
		a3.setNome("Tereza Maria");
		a3.setEmail("tezinha@email.com");
		a3.setMatricula("333");
		
		Aluno a4 = new Aluno();
		a4.setNome("Severina Souza");
		a4.setEmail("severa.s@email.com");
		a4.setMatricula("444");
		
		Aluno a5 = new Aluno();
		a5.setNome("Severina Souza");
		a5.setEmail("severa.s@email.com");
		a5.setMatricula("555");
		
		Turma t1 = new Turma();
		t1.setNome("Tópicos Especiais");
		t1.setDataCriacao(fmt.parse("22/02/2017"));
		t1.addAluno(a1);
		t1.addAluno(a2);
		a1.setTurma(t1);
		a2.setTurma(t1);
		
		Turma t2 = new Turma();
		t2.setNome("Fundamentos de Redes");
		t2.setDataCriacao(fmt.parse("22/03/2016"));
		t2.addAluno(a3);
		a2.setTurma(t2);
		
		Turma t3 = new Turma();
		t3.setNome("Estrutura de Dados");
		t3.setDataCriacao(fmt.parse("07/07/2017"));
		t3.addAluno(a4);
		t3.addAluno(a5);
		a4.setTurma(t3);
		a5.setTurma(t3);
		
		TurmaDAO turmadao = new TurmaDAO();
		turmadao.beginTransaction();
		turmadao.insert(t1);
		turmadao.insert(t2);
		turmadao.insert(t3);
		turmadao.commit();
		
	}

}
