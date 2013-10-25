package br.com.sigen.receitaweb.cliente;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import br.com.sigen.receitaweb.AbstractListAction;

@Named("clienteListAction")
@Stateless
public class ClienteListAction extends AbstractListAction<Cliente> {

	private static final long serialVersionUID = 1L;

	@EJB
	private ClienteBusiness clienteBusiness;

	@Override
	public void delete(Object object) {
		clienteBusiness.delete(object);
	}

	@Override
	protected List<Cliente> carregarRegistrosParaExibicao(int pageIndex,
			int pageSize) {
		return clienteBusiness.find(pageIndex, pageSize);
	}

	@Override
	protected int getTotalRegistros() {
		return clienteBusiness.count();
	}
}
