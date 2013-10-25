package br.com.sigen.receitaweb.cardapio;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sigen.receitaweb.receita.Receita;
import br.com.sigen.receitaweb.sessao.Sessao;

@Entity
@Table(name = "tb_cardapio_item")
@SequenceGenerator(name = "sq_tb_cardapio_item", allocationSize = 1)
public class CardapioItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_cardapio_item")
	@Basic(optional = false)
	@Column(name = "id_cardapio_item")
	private Integer idCardapioItem;

	@ManyToOne
	@JoinColumn(name = "id_cardapio")
	private Cardapio cardapio;

	@ManyToOne
	@JoinColumn(name = "id_sessao")
	private Sessao sessao;

	@ManyToOne
	@JoinColumn(name = "id_receita")
	private Receita receita;

	@Column(name = "ds_motivo_rejeicao")
	private String motivoRejeicao;

	@Column(name = "bl_aprovado")
	private Boolean aprovado;

	public Integer getIdCardapioItem() {
		return idCardapioItem;
	}

	public void setIdCardapioItem(Integer idCardapioItem) {
		this.idCardapioItem = idCardapioItem;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		if (aprovado.equals(Boolean.TRUE))
			setMotivoRejeicao(null);

		this.aprovado = aprovado;
	}

	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}

	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

}
