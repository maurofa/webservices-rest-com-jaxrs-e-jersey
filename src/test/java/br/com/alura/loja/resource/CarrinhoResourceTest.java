package br.com.alura.loja.resource;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.Servidor;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

public class CarrinhoResourceTest {

	private HttpServer server;

	@Before
	public void iniciaServidor() throws IOException {
		server = Servidor.inicializaServidor();
	}
	
	@After
	public void paraServidor() {
		server.stop();
	}
	
	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
        Carrinho carrinho = (Carrinho) new XStream().fromXML(extraiConteudo("/carrinhos/1"));
		assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	}
	
	@Test
	public void testIncluirCarrinho() {
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314l, "Tablet", 999, 1));
		carrinho.setRua("Rua Vergueiro");
		carrinho.setCidade("São Paulo");
		String xml = carrinho.toXml();
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		Response resposta = pegaOTarget().path("/carrinhos").request().post(entity);
		assertEquals(Status.CREATED.getStatusCode(), resposta.getStatus());
	}

	private String extraiConteudo(String complemento) {
		String conteudo = pegaOTarget().path(complemento).request().get(String.class);
		return conteudo;
	}

	private WebTarget pegaOTarget() {
		Client client = ClientBuilder.newClient();
		return client.target("http://localhost:8080");
	}
}
