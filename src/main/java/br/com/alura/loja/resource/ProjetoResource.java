package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;

@Path("projetos")
public class ProjetoResource {

	ProjetoDAO projetoDAO = new ProjetoDAO();

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca() {
		return new XStream().toXML(projetoDAO.trazTodos());
	}
	
	@GET
	@Path("1")
	@Produces(MediaType.APPLICATION_XML)
	public String busca1() {
		return new XStream().toXML(projetoDAO.busca(1l));
	}
}
