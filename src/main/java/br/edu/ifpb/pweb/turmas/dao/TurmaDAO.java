package br.edu.ifpb.pweb.turmas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.jboss.logging.Logger;

import br.edu.ifpb.pweb.turmas.model.Turma;

public class TurmaDAO extends GenericDAOJPAImpl<Turma, Long> {

	private static Logger logger = Logger.getLogger(TurmaDAO.class);
	
	public TurmaDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}
	
	public TurmaDAO(EntityManager em) {
		super(em); 
	}

	/**Recupera todas as turmas numa lista.
	 * @return
	 * @throws DAOException
	 */
	public List<Turma> findAll() throws DAOException{
		List<Turma> turmas = null;
		try {
			Query q = this.getEntityManager()
					.createQuery("from Turma t");
			turmas = (List<Turma>) q.getResultList();
		} catch (HibernateException e) {
			throw new DAOException("Erro ao tentar pegar Turmas", e);
		}
		return turmas;
	}

}
