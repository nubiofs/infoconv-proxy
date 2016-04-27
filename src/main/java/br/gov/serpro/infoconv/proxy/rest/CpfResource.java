package br.gov.serpro.infoconv.proxy.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.gov.serpro.infoconv.proxy.businesscontroller.ConsultaCpfBC;
import br.gov.serpro.infoconv.proxy.exception.CpfNaoEncontradoException;

@Path("cpf")
public class CpfResource {
	
	// TODO Para as consultas é necessário informar o cpf do consultante. Está
	// hardcoded na classe cpfBC. Deveria vir no request.

	@Inject
	ConsultaCpfBC cpfBC;

	@GET
	@Produces("application/json")
	public Response find(@QueryParam("lista") String lista, @QueryParam("perfil") String perfil) throws Exception {
		List<Object> resultado = cpfBC.consultarListaDeCpfPorPerfil(lista, perfil);
		return Response.ok().entity(resultado).build();
	}

	@GET
	@Path("{cpf}")
	@Produces("application/json")
	public Response load(@PathParam("cpf") String cpf, @QueryParam("perfil") String perfil) throws Exception {	
		List<Object> resultado = cpfBC.consultarListaDeCpfPorPerfil(cpf, perfil);
		return Response.ok().entity(resultado.get(0)).build();
	}

}
