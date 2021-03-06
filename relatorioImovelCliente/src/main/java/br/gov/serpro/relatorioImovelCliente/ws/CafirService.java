package br.gov.serpro.relatorioImovelCliente.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/cafirservice")
public interface CafirService {


	@POST
	@Path("/obterrelatoriodadosimovel")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract byte[] obterRelatorioDadosImovel(String nirf);
	
	
	//TODO: método para testar se o webservice está ativo
	@GET
	@Path("/test")
	public String test();


}
