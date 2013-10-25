package br.com.sigen.receitaweb.receita;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

@Named("receitaSelectDialogAction")
@Stateless
public class ReceitaSelectDialogAction implements Serializable {

	private static final long serialVersionUID = 1L;

	public ReceitaSelectDialogAction() {
	}

	public void pesquisar(ActionEvent event) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);

		RequestContext.getCurrentInstance().openDialog(
				"/modulo/admin/receita/receitaSelectDialog", options, null);
	}

	public void select(Receita receita) {
		RequestContext.getCurrentInstance().closeDialog(receita);
	}

	protected Logger getLogger(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class for logger is required.");
		}
		return Logger.getLogger(clazz.getName());
	}

}
