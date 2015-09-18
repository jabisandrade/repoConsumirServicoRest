package teste;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

public class Util {
	
	public static String inputStreamToString(InputStream is) throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	public static String toJson(Object objeto) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter jsonValue = new StringWriter();
		mapper.writeValue(new PrintWriter(jsonValue), objeto);
		return jsonValue.toString();
	}


	public static Object fromJson(String json, Class objectClass) throws Exception {
		JsonFactory f = new MappingJsonFactory();
		JsonParser jp = f.createJsonParser(json);
		Object obj = jp.readValueAs(objectClass);
		return obj;
	}
	
	public static void gerarArquivo(File file, InputStream pConteudo) throws Exception {

		OutputStream outputStream = null;
		try {			
			if (file.exists()) {
				file.delete();
			}
			byte[] buffer = new byte[pConteudo.available()];
			pConteudo.read(buffer);				
			outputStream = FileUtils.openOutputStream(file);
			outputStream.write(buffer);
		} catch (Exception e) {
			throw e;
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
	}

	public static void gerarArquivo(File file, byte[] pConteudo) throws Exception {

		OutputStream outputStream = null;
		try {
			if (file.exists()) {
				file.delete();
			}	

			outputStream = FileUtils.openOutputStream(file);
			outputStream.write(pConteudo);

		} catch (Exception e) {
			throw e;
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
	}

}
