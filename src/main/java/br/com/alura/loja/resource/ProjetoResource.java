package br.com.alura.loja.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {

	ProjetoDAO projetoDAO = new ProjetoDAO();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Projeto> busca() {
		return projetoDAO.trazTodos();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Projeto busca1(@PathParam("id") long id) {
		return projetoDAO.busca(id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public Response adiciona(Projeto projeto) {
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
