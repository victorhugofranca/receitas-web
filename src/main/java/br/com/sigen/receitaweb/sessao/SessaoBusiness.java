package br.com.sigen.receitaweb.sessao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SessaoBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private SessaoBusiness sessaoBusiness;

	public void save(Sessao sessao) {

		if (sessao.getIdSessao() != null) {
			entityManager.merge(sessao);
		} else {
			entityManager.persist(sessao);
		}
	}

	public void delete(Object entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public List<Sessao> find(Integer pageIndex, Integer pageSize) {
		return entityManager
				.createQuery(
						"select sessao from Sessao sessao order by sessao.data",
						Sessao.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(sessao) from Sessao sessao").getSingleResult();
		return Integer.valueOf(count.toString());
	}

}
