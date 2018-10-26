package com.javaee.controllers;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javaee.domain.Veiculo;
import com.javaee.service.VeiculoServiceImpl;

@Path("/veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoController {
	private static final Logger logger = LoggerFactory.getLogger(VeiculoController.class);
	private VeiculoServiceImpl veiculoService;
	
	public VeiculoController() {
		veiculoService = new VeiculoServiceImpl();
	}
	
	@GET
	public List<Veiculo> getAll() {
		logger.info("getAllVeiculos: {}");
		List<Veiculo> veiculos = veiculoService.buscarTodos();
		return veiculos;
	}
	
	@GET
	@Path("{id : \\d+}")
	public Response getById(@PathParam("id") Integer id) {
		logger.info("getById: {}", id);
		Veiculo veiculo = veiculoService.buscarPorId(id);
		if (veiculo == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(veiculo).build();
	}
	
	@POST
    public Response create(Veiculo veiculo, @Context UriInfo uriInfo) {
        logger.info("create: {}", veiculo);
        Veiculo veiculoSalvo = veiculoService.salvarVeiculo(veiculo);
        logger.debug("Vehicle created with id = ", veiculoSalvo.getId());
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(veiculoSalvo.getId().toString());
        return Response.created(builder.build()).entity(veiculoSalvo).build();
    }
    
    @PUT
    @Path("/{id : \\d+}")
    public Response update(@PathParam("id") Integer id, Veiculo veiculo) {
        logger.info("Vehicle ID: {} ", id, veiculo);
        Veiculo vehicleSaved = veiculoService.buscarPorId(id);
        if (vehicleSaved == null) {
        	return Response.status(Status.NOT_FOUND).build();
        }
        vehicleSaved = veiculoService.salvarVeiculo(veiculo);
        return Response.ok().entity(vehicleSaved).build();
    }
    
    @DELETE
    @Path("/{id : \\d+}")
    public Response delete(@PathParam("id") Integer id) {
    	logger.info("Vehicle ID: {} ", id);
    	veiculoService.deletarPorId(id);
    	return Response.noContent().build();
}
	
}
