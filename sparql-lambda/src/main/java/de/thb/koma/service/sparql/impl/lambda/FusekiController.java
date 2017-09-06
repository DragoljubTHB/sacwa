package de.thb.koma.service.sparql.impl.lambda;

import org.apache.jena.fuseki.embedded.FusekiServer;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.system.StreamRDF;
import org.apache.jena.util.FileManager;

import java.io.InputStream;
import java.io.PrintWriter;

public class FusekiController {

    String uriKoma = "https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl";
    FusekiServer server;
    Model mModel;

    public void execQuery(String aQuery){
        Query query = QueryFactory.create(aQuery);
        try(QueryExecution qexec = QueryExecutionFactory.create(query, mModel)){
            ResultSet results = qexec.execSelect();
            for ( ; results.hasNext() ; )
            {
                QuerySolution soln = results.nextSolution() ;
                RDFNode x = soln.get("varName") ;       // Get a result variable by name.
                Resource r = soln.getResource("VarR") ; // Get a result variable - must be a resource
                Literal l = soln.getLiteral("VarL") ;   // Get a result variable - must be a literal
            }
        }
    }

    public Model getModelFrom(String uri, String rdfSyntax) {
        return FileManager.get().loadModel(uri, "TURTLE");
    }
    public Model getModelFrom(InputStream in){

        Model m = ModelFactory.createDefaultModel();
        RDFDataMgr.read(m, in, Lang.TURTLE);

        //StreamRDF streamRDF = RDFDataMgr.createIteratorTriples()
        this.mModel = m;
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
