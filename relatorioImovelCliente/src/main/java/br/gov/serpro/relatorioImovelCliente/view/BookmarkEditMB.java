package br.gov.serpro.relatorioImovelCliente.view;

import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.serpro.relatorioImovelCliente.business.BookmarkBC;
import br.gov.serpro.relatorioImovelCliente.business.ConsultaBC;
import br.gov.serpro.relatorioImovelCliente.domain.Bookmark;

@ViewController
@PreviousView("/bookmark_list.xhtml")
public class BookmarkEditMB extends AbstractEditPageBean<Bookmark, Long> {

	private static final long serialVersionUID = 1L;
	
	private String nirf;
	
	@Inject
	private ConsultaBC consultaBC;

	@Inject
	private BookmarkBC bookmarkBC;

	@Override
	@Transactional
	public String delete() {
		this.bookmarkBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		this.bookmarkBC.insert(getBean());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String update() {
		this.bookmarkBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected Bookmark handleLoad(Long id) {
		return this.bookmarkBC.load(id);
	}
	
	
	public String getNirf() {
		return nirf;
	}

	public void setNirf(String nirf) {
		this.nirf = nirf;
	}


	public void gerarRelatorioDadosImovel() {

		try {
			byte[] buffer = consultaBC.obterRelatorioDadosImovelInscricao(this.getNirf());
			if (buffer != null && buffer.length>0) {
				downloadArquivoAplicacao(buffer, "dadosImovel-" + this.getNirf(), ".pdf");
			} else {
			
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
		
	/**
	 * Configura a resposta do servidor para retornar um arquivo para a aplicação.
	 * 
	 * @param arquivo
	 *            Arquivo.
	 * @param filename
	 *            Nome do arquivo.
	 * @param extensao
	 *            Extensão do arquivo.
	 * @throws IOException 
	 */
	private void downloadArquivoAplicacao(byte[] arquivo, String filename, String extensao) throws IOException {

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", "attachment; filename=" + filename + extensao);
		response.setHeader("Cache-Control", "max-age=0");
		response.setHeader("Pragma", "public");

		OutputStream responseStream = response.getOutputStream();
		responseStream.write(arquivo);
		FacesContext.getCurrentInstance().responseComplete();

	}

}
