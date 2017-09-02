package de.thb.koma.repository.sparql;

import org.apache.jena.fuseki.embedded.FusekiServer;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

import java.io.PrintWriter;

public class KomaSparqlEndpoint {
    String uriKoma = "https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl";
    FusekiServer server;

    private Model getFileFrom(String uri, String rdfSyntax) {
        return FileManager.get().loadModel(uri, "TURTLE");
    }

    public void initServer(){
        Dataset ds = DatasetFactory.createTxnMem();
        server = FusekiServer.create()
                .setPort(3030)
                .add("/dataset", ds)
                .build();
        server.start();
    }
    public void stopServer(){
        server.stop();
    }

    /**
     * Server shoud be running
     * @param uri
     */
    public void uploadFileToServer(String uri, String rdfSyntax) {
        Model model = getFileFrom(uri, rdfSyntax);

        // upload a model
        DatasetAccessor accessor = connectToService();
        accessor.putModel(model);

        accessor.getModel().write(new PrintWriter(System.out));

    }

    static DatasetAccessor connectToService() {
        return DatasetAccessorFactory.createHTTP(serviceGSP());
    }

    static DatasetAccessor connectToDataset() {
        return DatasetAccessorFactory.createHTTP(urlDataset());
    }

    public static final int port() {
        return 3030;
    }

    public static final String datasetPath() {
        return "/dataset";
    }

    public static final String urlDataset() {
        return "http://localhost:" + port() + datasetPath();
    }

    public static final String serviceUpdate() {
        return "http://localhost:" + port() + datasetPath() + "/update";
    }

    public static final String serviceQuery() {
        return "http://localhost:" + port() + datasetPath() + "/query";
    }

    public static final String serviceGSP() {
        return "http://localhost:" + port() + datasetPath() + "/data";
    }



}
