package br.com.sigen.receitaweb.receita;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.sigen.receitaweb.AbstractListAction;

@Named("receitaListAction")
@Stateless
public class ReceitaListAction extends AbstractListAction<Receita> {

	private static final long serialVersionUID = 1L;

	@EJB
	private ReceitaBusiness receitaBusiness;

	@Override
	public void delete(Object object) {
		receitaBusiness.delete(object);
	}

	@Override
	protected List<Receita> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return receitaBusiness.find(pageIndex, pageSize);
	}

	@Override
	protected int getTotalRegistros() {
		return receitaBusiness.count();
	}
}
