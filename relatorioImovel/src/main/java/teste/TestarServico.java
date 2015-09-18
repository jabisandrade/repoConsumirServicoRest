package teste;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.Date;

import javax.swing.JOptionPane;

public class TestarServico {
	

	public static void main(String[] args) {
		try{
			//String url = "http://127.0.0.1:8080/itrintegrado/rest/cafirservice/obterPdfSitfis";
			String url = "http://webdesdr-java6.sdr.serpro:8080/itrintegrado/rest/cafirservice/obterPdfSitfis";
			String nirf = "00140597";
			String nomeArquivo= System.getProperty("user.dir") + File.separator + nirf+"-"+new Date().toString()+".pdf";
			
			byte[] pdf = obterRelatorioPDF(nirf, url);
			if (pdf!=null){
				Util.gerarArquivo(new File(nomeArquivo), pdf);	
				JOptionPane.showMessageDialog(null, "Arquivo gerado em "+nomeArquivo);
			}else{
				JOptionPane.showMessageDialog(null, "PDF vazio");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado.");
		}
		
	}
	
	@SuppressWarnings("restriction")
	public static byte[] obterRelatorioPDF(String nirf, String urlWebService) {
		byte[] pdf = null;
		try {		
			java.net.URL url;
			java.net.HttpURLConnection con;
			url = new java.net.URL(urlWebService);
			con = (java.net.HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setUseCaches(false);			
			con.setRequestProperty("Content-Type", "text/plain");
			//con.setRequestProperty("Accept", "application/json");
			//con.setRequestProperty("Content-Length", Integer.toString(requestJson.length()));
									
			java.io.DataOutputStream wr = new java.io.DataOutputStream(con.getOutputStream());
			wr.writeBytes(nirf);
			wr.flush();
			wr.close();
		
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK){
				java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer respostaBuffer = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					respostaBuffer.append(inputLine);
				}
				in.close();				
				pdf = new sun.misc.BASE64Decoder().decodeBuffer(respostaBuffer.toString());				
			}else{
				System.out.println("Codigo retornado: "+con.getResponseCode());
				System.out.println("Resposta do Servidor: "+con.getResponseMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pdf;


	}


}
