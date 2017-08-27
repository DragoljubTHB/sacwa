package de.thb.koma.repository.sparql;

import org.apache.jena.fuseki.embedded.FusekiServer;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
public class Main
{
    /**
     * IRI <- IRI Version :: defined at runtime 1: lambda as param 2:config file
     */
    String host = "http://amazonaws..";
    String ontologyPath = "/ontology/koma-complex.owl";
    String uri = host+"ontology.koma.thb.de/ontology/koma-complex.owl";
    String TURTLE = "TURTLE";

    /**
     * query ::
      *
     */


    public static void main(String[] args) {
        Main main = new Main();
        main.server();
    }

    private void server() {
        Dataset ds = DatasetFactory.createTxnMem();
        FusekiServer server = FusekiServer.create()
                .add("/ds", ds)
                .build() ;
        server.start() ;
        server.stop() ;
    }

    public boolean someLibraryMethod() {
        return true;
    }
}
