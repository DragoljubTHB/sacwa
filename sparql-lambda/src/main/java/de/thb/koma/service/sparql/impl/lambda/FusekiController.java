package de.thb.koma.service.sparql.impl.lambda;

import org.apache.jena.fuseki.embedded.FusekiServer;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.system.StreamRDF;
import org.apache.jena.util.FileManager;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;

public class FusekiController {

    String uriKoma = "https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl";
    FusekiServer server;

    public Model getModelFrom(String uri, String rdfSyntax) {
        return FileManager.get().loadModel(uri, "TURTLE");
    }
    public Model getModelFrom(InputStream in){

        Model m = ModelFactory.createDefaultModel();
        RDFDataMgr.read(m, in, Lang.TURTLE);

        //StreamRDF streamRDF = RDFDataMgr.createIteratorTriples()

        return m;
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
     * @param model
     */
    public void uploadModelToServer(Model model) {
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
