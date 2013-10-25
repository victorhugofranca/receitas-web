package br.com.sigen.receitaweb.cardapio;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sigen.receitaweb.cliente.Cliente;
import br.com.sigen.receitaweb.sessao.Sessao;

@Entity
@Table(name = "tb_cardapio")
@SequenceGenerator(name = "sq_tb_cardapio", allocationSize = 1)
public class Cardapio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_cardapio")
	@Basic(optional = false)
	@Column(name = "id_cardapio")
	private Integer idCardapio;

	@OneToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@OneToMany(mappedBy = "cardapio", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Sessao> sessoes;

	@OneToMany(mappedBy = "cardapio", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<CardapioItem> cardapioItens;

	@Basic(optional = false)
	@Column(name = "ds_status")
	@Enumerated(EnumType.STRING)
	private CardapioStatus status;

	public boolean atualizarStatusAprovado() {
		for (Iterator<CardapioItem> iterator = cardapioItens.iterator(); iterator
				.hasNext();) {
			CardapioItem cardapioItem = (CardapioItem) iterator.next();
			if (!cardapioItem.getAprovado())
				return false;
		}

		setStatus(CardapioStatus.APROV);
		return true;
	}

	public Integer getIdCardapio() {
		return idCardapio;
	}

	public void setIdCardapio(Integer idCardapio) {
		this.idCardapio = idCardapio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<CardapioItem> getCardapioItens() {
		return cardapioItens;
	}

	public void setCardapioItens(List<CardapioItem> cardapioItens) {
		this.cardapioItens = cardapioItens;
	}

	public CardapioStatus getStatus() {
		return status;
	}

	public void setStatus(CardapioStatus status) {
		this.status = status;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

}
