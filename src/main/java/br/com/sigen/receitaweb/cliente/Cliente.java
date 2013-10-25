package br.com.sigen.receitaweb.cliente;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.sigen.receitaweb.cardapio.Cardapio;

@Entity
@Table(name = "tb_cliente")
@SequenceGenerator(name = "sq_tb_cliente", allocationSize = 1)
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_cliente")
	@Basic(optional = false)
	@Column(name = "id_cliente")
	private Integer idCliente;

	@Basic(optional = false)
	@Column(name = "ds_nome")
	private String nome;

	@Basic(optional = false)
	@Column(name = "ds_login")
	private String login;

	@Basic(optional = false)
	@Column(name = "ds_senha")
	private String senha;

	@Basic(optional = false)
	@Column(name = "ds_email")
	private String email;

	@Basic(optional = true)
	@Column(name = "ds_preferencias_alimentacao")
	private String preferenciasAlimentacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cardapio")
	private Cardapio cardapio;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPreferenciasAlimentacao() {
		return preferenciasAlimentacao;
	}

	public void setPreferenciasAlimentacao(String preferenciasAlimentacao) {
		this.preferenciasAlimentacao = preferenciasAlimentacao;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

}
