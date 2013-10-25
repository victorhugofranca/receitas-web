package br.com.sigen.receitaweb.sessao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.sigen.receitaweb.cardapio.Cardapio;
import br.com.sigen.receitaweb.cardapio.CardapioItem;

@Entity
@Table(name = "tb_sessao")
@SequenceGenerator(name = "sq_tb_sessao", allocationSize = 1)
public class Sessao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_sessao")
	@Basic(optional = false)
	@Column(name = "id_sessao")
	private Integer idSessao;

	@Basic(optional = false)
	@Column(name = "dt_data", unique = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@ManyToOne
	@JoinColumn(name = "id_cardapio")
	private Cardapio cardapio;

	@OneToMany(mappedBy = "sessao", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<CardapioItem> cardapioItens;

	public Integer getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(Integer idSessao) {
		this.idSessao = idSessao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<CardapioItem> getCardapioItens() {
		return cardapioItens;
	}

	public void setCardapioItens(List<CardapioItem> cardapioItens) {
		this.cardapioItens = cardapioItens;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

}
