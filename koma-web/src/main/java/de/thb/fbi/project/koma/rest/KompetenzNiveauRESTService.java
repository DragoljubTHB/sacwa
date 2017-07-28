package de.thb.fbi.project.koma.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;

import de.thb.fbi.project.koma.data.KompNiveau;
import de.thb.fbi.project.koma.service.api.KompNiveauService;

@RequestScoped
@Path("kompetenzniveaus")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class KompetenzNiveauRESTService {

	@Inject
	private Logger logger;

	@Inject
	private KompNiveauService knsi;

	@POST
	public Response create(final KompNiveau kompniveau) {
		logger.info("----> kompNiveu REST :: create {}", kompniveau.toString());
		knsi.create(kompniveau);

		// you may want to use the following return statement, assuming that
		// KompNiveau#getId() or a similar method
		// would provide the identifier to retrieve the created KompNiveau
		// resource:
		return Response.created(UriBuilder.fromResource(KompetenzNiveauRESTService.class)
				.path(String.valueOf(kompniveau.getId())).build()).build();
		// return Response.created(null).build();
	}

	@GET
	@Path("/{id:[1-9][0-9]*}")
	public Response findById(@PathParam("id") final int id) {
		logger.info("----> kompNiveu REST :: findById ");
		KompNiveau kompniveau = knsi.find(id);
		if (kompniveau == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(kompniveau).build();
	}

	@GET
	public List<KompNiveau> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		logger.info("----> kompNiveau REST :: listAll ");
		final List<KompNiveau> kompniveaus = knsi.findAll();
		return kompniveaus;
	}

	@PUT
	@Path("/{id:[1-9][0-9]*}")
	public Response update(@PathParam("id") int id, final KompNiveau kompniveau) {
		logger.info("----> kompNiveau REST :: update {}", kompniveau.toString());
		knsi.update(kompniveau);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[1-9][0-9]*}")
	public Response deleteById(@PathParam("id") final int id) {
		logger.info("----> kompNiveau REST :: update {}", id);
		knsi.delete(id);
		return Response.noContent().build();
	}

}
