package br.com.sigen.receitaweb.cliente;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.sigen.receitaweb.cardapio.CardapioItem;
import br.com.sigen.receitaweb.receita.Receita;

@Named("clienteFormAction")
@ConversationScoped
public class ClienteFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Cliente instance;

	@Inject
	private Conversation conversation;

	@EJB
	private ClienteBusiness clienteBusiness;

	@Inject
	private Receita receitaTemp;

	@Inject
	private CardapioItem itemSessaoTemp;

	public ClienteFormAction() {
	}

	public void beginConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	public void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public String save() {

		if (getInstance().getCardapio() != null)
			getInstance().getCardapio().atualizarStatusAprovado();

		clienteBusiness.save(instance);
		return "clienteList";
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public Cliente getInstance() {
		return instance;
	}

	public void setInstance(Cliente instance) {
		this.instance = instance;
	}

	public Receita getReceitaTemp() {
		return receitaTemp;
	}

	public CardapioItem getItemSessaoTemp() {
		return itemSessaoTemp;
	}

	public void setItemSessaoTemp(CardapioItem itemSessaoTemp) {
		this.itemSessaoTemp = itemSessaoTemp;
	}

	public void setReceitaTemp(Receita receitaTemp) {
		this.receitaTemp = receitaTemp;
	}

	public void onReceitaChosen(SelectEvent event) {
		Receita receita = (Receita) event.getObject();
		setReceitaTemp(receita);
	}

	public void addItemCardapio() {
		CardapioItem cardapioItem = buildNewItemCardapio();
		clienteBusiness.addItemCardapio(getInstance(), cardapioItem);
	}

	public void deleteItemCardapio(CardapioItem cardapioItem) {
		clienteBusiness.deleteItemCardapio(getInstance(), cardapioItem);
	}

	private CardapioItem buildNewItemCardapio() {
		CardapioItem cardapioItem = new CardapioItem();
		cardapioItem.setReceita(receitaTemp);
		return cardapioItem;
	}

}
