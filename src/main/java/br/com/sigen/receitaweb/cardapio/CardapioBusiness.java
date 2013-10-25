package br.com.sigen.receitaweb.cardapio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CardapioBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Cardapio cardapio) {

		if (cardapio.getIdCardapio() != null) {
			entityManager.merge(cardapio);
		} else {
			entityManager.persist(cardapio);
		}
	}

	public void delete(Object entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public List<Cardapio> find(Integer pageIndex, Integer pageSize,
			Cardapio cardapioFilter) {

		StringBuffer hql = new StringBuffer();

		hql.append("select cardapio from Cardapio cardapio");
		hql.append(" where 1 = 1");
		if (cardapioFilter.getStatus() != null) {
			hql.append(" and cardapio.status = :cardapioStatus");
		}

		hql.append(" order by cardapio.idCardapio");

		Query query = entityManager.createQuery(hql.toString(), Cardapio.class)
				.setFirstResult(pageIndex).setMaxResults(pageSize);

		if (cardapioFilter.getStatus() != null)
			query.setParameter("cardapioStatus", cardapioFilter.getStatus());

		return query.getResultList();
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(cardapio) from Cardapio cardapio")
				.getSingleResult();
		return Integer.valueOf(count.toString());
	}

	public Cardapio buildNewCardapio() {
		Cardapio cardapio = new Cardapio();
		cardapio.setCardapioItens(new ArrayList<CardapioItem>());
		cardapio.setStatus(CardapioStatus.AVAL);
		return cardapio;
	}

}
