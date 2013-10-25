package br.com.sigen.receitaweb.receita;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("receitaFormAction")
@RequestScoped
public class ReceitaFormAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Receita instance;

	@EJB
	private ReceitaBusiness receitaBusiness;

	public ReceitaFormAction() {
	}

	public String edit() {
		receitaBusiness.save(instance);
		return "receitaList";
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

	public Receita getInstance() {
		return instance;
	}

	public void setInstance(Receita instance) {
		this.instance = instance;
	}

}
