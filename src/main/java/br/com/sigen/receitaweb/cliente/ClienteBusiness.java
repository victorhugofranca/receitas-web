package br.com.sigen.receitaweb.cliente;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.codec.binary.Base64;

import br.com.sigen.receitaweb.SystemException;
import br.com.sigen.receitaweb.cardapio.Cardapio;
import br.com.sigen.receitaweb.cardapio.CardapioBusiness;
import br.com.sigen.receitaweb.cardapio.CardapioItem;

@Stateless
public class ClienteBusiness {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private CardapioBusiness cardapioBusiness;

	public void save(Cliente cliente) {

		if (cliente.getCardapio() == null) {
			criarNovoCardapioParaCliente(cliente);
		}

		if (cliente.getIdCliente() != null) {
			entityManager.merge(cliente);
		} else {
			generatePassword(cliente);
			entityManager.persist(cliente);
		}
	}

	private void criarNovoCardapioParaCliente(Cliente cliente) {
		Cardapio cardapio = cardapioBusiness.buildNewCardapio();
		cardapio.setCliente(cliente);
		cliente.setCardapio(cardapio);
	}

	private void generatePassword(Cliente cliente) {

		SecureRandom random = new SecureRandom();
		String password = new BigInteger(130, random).toString(32);
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new SystemException("Erro ao gerar senha");
		}
		byte[] passwordDigest = messageDigest.digest(password.getBytes());
		String passwordDigestBase64 = Base64.encodeBase64String(passwordDigest);
		cliente.setSenha(passwordDigestBase64);
	}

	public void delete(Object entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	public List<Cliente> find(Integer pageIndex, Integer pageSize) {
		return entityManager
				.createQuery(
						"select cliente from Cliente cliente order by cliente.nome",
						Cliente.class).setFirstResult(pageIndex)
				.setMaxResults(pageSize).getResultList();
	}

	public Integer count() {
		Long count = (Long) entityManager.createQuery(
				"select count(cliente) from Cliente cliente").getSingleResult();
		return Integer.valueOf(count.toString());
	}

	public void addItemCardapio(Cliente cliente, CardapioItem cardapioItem) {
		if (cliente.getCardapio() == null) {
			criarNovoCardapioParaCliente(cliente);
		}

		cardapioItem.setCardapio(cliente.getCardapio());
		cardapioItem.setAprovado(false);
		cliente.getCardapio().getCardapioItens().add(cardapioItem);
	}

	public void deleteItemCardapio(Cliente cliente, CardapioItem cardapioItem) {
		cliente.getCardapio().getCardapioItens().remove(cardapioItem);
	}

}
