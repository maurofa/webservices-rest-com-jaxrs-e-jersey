package br.com.alura.loja.resource;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		List<Projeto> projetos = extraiTarget("/projetos").request().get(List.class);

		assertEquals(projetos.get(0).getNome(), "Minha loja");
		assertEquals(projetos.get(1).getNome(), "Alura");
	}

	private WebTarget extraiTarget(String complemento) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(Servidor.URL_PATH);
		return target.path(complemento);
	}
	
	@Test
	public void testTrazProjeto1() {
		Projeto projeto = extraiTarget("/projetos/1").request().get(Projeto.class);
		assertEquals("Minha loja", projeto.getNome());
	}
}
