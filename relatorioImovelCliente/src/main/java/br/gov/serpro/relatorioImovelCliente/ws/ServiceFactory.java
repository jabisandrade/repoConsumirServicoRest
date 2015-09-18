package br.gov.serpro.relatorioImovelCliente.ws;

import javax.enterprise.inject.Produces;

import org.jboss.resteasy.client.ProxyFactory;


public class ServiceFactory {

	@Produces @RemoteService
	public CafirService createCafirService() {
		return ProxyFactory.create(CafirService.class, "http://webdesdr-java6.sdr.serpro:8080/itrintegrado/rest");
	}

}
