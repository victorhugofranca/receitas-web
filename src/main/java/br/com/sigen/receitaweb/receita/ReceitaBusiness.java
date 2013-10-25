package br.com.sigen.receitaweb.receita;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ReceitaBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Receita receita) {

		if (receita.getIdReceita() != null) {
			entityManager.merge(receita);
		} else {
			entityManager.persist(receita);
		}
	}

	public void delete(Object entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public List<Receita> find(Integer pageIndex, Integer pageSize) {
		return entityManager
				.createQuery(
						"select receita from Receita receita order by receita.titulo",
						Receita.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(receita) from Receita receita").getSingleResult();
		return Integer.valueOf(count.toString());
	}
}
