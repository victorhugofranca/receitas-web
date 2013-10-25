package br.com.sigen.receitaweb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("service")
public class ProcessosService {

	@Context
	ResourceContext rc;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("teste")
	public List<String> load() {

		List<String> list = new ArrayList<String>();
		list.add("teste1");
		list.add("teste2");

		return list;
	}
}
