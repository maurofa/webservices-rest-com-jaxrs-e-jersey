package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {

	ProjetoDAO projetoDAO = new ProjetoDAO();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca() {
		return new XStream().toXML(projetoDAO.trazTodos());
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String busca1(@PathParam("id") long id) {
		return projetoDAO.busca(id).toXML();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public Response adiciona(String xml) {
		Projeto projeto = (Projeto) new XStream().fromXML(xml);
		projetoDAO.adiciona(projeto);
		return Response.created(URI.create("/projetos/"+projeto.getId())).build();
	}
	
	@Path("{id}")
	@DELETE
	public Response remove(@PathParam("id") long id) {
		projetoDAO.remove(id);
		return Response.ok().build();
	}
}
