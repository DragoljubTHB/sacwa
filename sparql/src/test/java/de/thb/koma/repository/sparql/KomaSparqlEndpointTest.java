package de.thb.koma.repository.sparql;

import org.apache.jena.riot.RDFLanguages;
import org.junit.Test;

public class KomaSparqlEndpointTest {
    String uriKoma = "https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl";

    @Test public void mainFlow(){
        KomaSparqlEndpoint endpoint = new KomaSparqlEndpoint();
        endpoint.initServer();
        endpoint.uploadFileToServer(uriKoma, RDFLanguages.strLangTurtle);

        endpoint.stopServer();
    }
}
