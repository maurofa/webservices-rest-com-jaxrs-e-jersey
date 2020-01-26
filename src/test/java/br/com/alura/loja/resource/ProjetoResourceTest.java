package br.com.alura.loja.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.Servidor;
import br.com.alura.loja.modelo.Projeto;

public class ProjetoResourceTest {
	
	private HttpServer server;

	@Before
	public void inicializaServidor() throws IOException {
		server = Servidor.inicializaServidor();
	}
	
	@After
	public void paraServidor() {
		server.stop();
	}

	@Test
	public void testTrazProjetos() {
		String conteudo = extraiConteudo("/projetos");
		assertTrue(conteudo.contains("<nome>Minha loja"));
		assertTrue(conteudo.contains("<nome>Alura"));
	}

	private String extraiConteudo(String complemento) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path(complemento).request().get(String.class);
		return conteudo;
	}
	
	@Test
	public void testTrazProjeto1() {
		String conteudo = extraiConteudo("/projetos/1");
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		assertEquals("Minha loja", projeto.getNome());
	}
}
