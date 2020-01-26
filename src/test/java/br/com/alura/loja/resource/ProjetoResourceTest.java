package br.com.alura.loja.resource;

import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class ProjetoResourceTest {

	@Test
	public void uriProjetosFunciona() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/projetos").request().get(String.class);
		assertTrue(conteudo.contains("<nome>Minha loja"));
		assertTrue(conteudo.contains("<nome>Alura"));
	}
}
