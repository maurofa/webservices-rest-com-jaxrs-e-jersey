package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {

	public static final String URL_PATH = "http://localhost:8081/";
	public static void main(String[] args) throws IOException {
		HttpServer server = inicializaServidor();
        System.in.read();
        server.stop();
	}

	public static HttpServer inicializaServidor() throws IOException {
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		URI uri = URI.create(URL_PATH);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        System.out.println("Servidor rodando");
		return server;
	}

}
