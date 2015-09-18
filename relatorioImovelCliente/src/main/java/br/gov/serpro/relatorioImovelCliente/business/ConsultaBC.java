package br.gov.serpro.relatorioImovelCliente.business;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.serpro.relatorioImovelCliente.domain.Bookmark;
import br.gov.serpro.relatorioImovelCliente.persistence.BookmarkDAO;
import br.gov.serpro.relatorioImovelCliente.ws.CafirService;
import br.gov.serpro.relatorioImovelCliente.ws.RemoteService;

public class ConsultaBC extends DelegateCrud<Bookmark, Long, BookmarkDAO>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	@RemoteService
	protected CafirService cafirService;

	public byte[] obterRelatorioDadosImovelInscricao(String nirf) {
		return cafirService.obterRelatorioDadosImovel(nirf); 
	}
}
