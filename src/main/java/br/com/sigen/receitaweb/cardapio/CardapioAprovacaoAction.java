package br.com.sigen.receitaweb.cardapio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("cardapioAprovacaoAction")
@ViewScoped
public class CardapioAprovacaoAction implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cardapio instance;

	@EJB
	private CardapioBusiness cardapioBusiness;

	@PostConstruct
	private void init() {
		Cardapio cardapio = cardapioBusiness.find(0, 10, new Cardapio()).get(0);
		atribuirValorAprovadoItensCardapio(cardapio);
		setInstance(cardapio);
	}

	private void atribuirValorAprovadoItensCardapio(Cardapio cardapio) {
		for (Iterator<CardapioItem> iterator = cardapio.getCardapioItens()
				.iterator(); iterator.hasNext();) {
			CardapioItem cardapioItem = (CardapioItem) iterator.next();
			if (cardapioItem.getAprovado() == null) {
				cardapioItem.setAprovado(true);
			}
		}
	}

	public Cardapio getInstance() {
		return instance;
	}

	public void setInstance(Cardapio instance) {
		this.instance = instance;
	}
	
	public List<CardapioItem> getItensCardapioRejeitados() {
		List<CardapioItem> rejeitados = new ArrayList<CardapioItem>();

		for (Iterator<CardapioItem> iterator = getInstance().getCardapioItens()
				.iterator(); iterator.hasNext();) {
			CardapioItem cardapioItem = (CardapioItem) iterator.next();
			if (cardapioItem.getAprovado() != null
					&& !cardapioItem.getAprovado()) {
				rejeitados.add(cardapioItem);
			}
		}

		return rejeitados;
	}

	public String substituirReceitas() {
		getInstance().setStatus(CardapioStatus.PEND_SUBST);
		cardapioBusiness.save(getInstance());
		return "/modulo/usuario/index";
	}
}
