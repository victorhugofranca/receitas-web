package br.com.sigen.receitaweb.receita;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_receita")
@SequenceGenerator(name = "sq_tb_receita", allocationSize = 1)
public class Receita implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_receita")
	@Basic(optional = false)
	@Column(name = "id_receita")
	private Integer idReceita;

	@Basic(optional = false)
	@Column(name = "ds_titulo")
	private String titulo;

	@Basic(optional = false)
	@Column(name = "ds_ingredentes")
	private String ingredientes;

	@Basic(optional = false)
	@Column(name = "ds_modo_preparo")
	private String modoPreparo;

	public Integer getIdReceita() {
		return idReceita;
	}

	public void setIdReceita(Integer idReceita) {
		this.idReceita = idReceita;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getModoPreparo() {
		return modoPreparo;
	}

	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}

}
