package de.thb.fbi.project.koma.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.thb.fbi.project.koma.data.Stunde;
import de.thb.fbi.project.koma.service.api.StundeService;

@Path("/einzelthemas")
@RequestScoped
public class EinzelthemaRESTService {

	@Inject
	private StundeService esi;

	@GET
	@Path("/{id:[1-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Stunde lookupEinzelthemaById(@PathParam("id") int id) {

		Stunde einzelthema = esi.find(id);
		if (einzelthema == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return einzelthema;
	}
}
