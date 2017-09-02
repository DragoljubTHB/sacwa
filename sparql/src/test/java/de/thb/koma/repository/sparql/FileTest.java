package de.thb.koma.repository.sparql;

import org.apache.jena.fuseki.Fuseki;
import org.apache.jena.fuseki.embedded.FusekiServer;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.StringJoiner;

public class FileTest {
    String uriKoma = "https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl";
    public static final int port(){return 3030;}
    public static final String datasetPath(){return "/dataset";}

    public static final String urlDataset()     { return "http://localhost:"+port()+datasetPath() ; }
    public static final String serviceUpdate()  { return "http://localhost:"+port()+datasetPath()+"/update" ; }
    public static final String serviceQuery()   { return "http://localhost:"+port()+datasetPath()+"/query" ; }
    public static final String serviceGSP()     { return "http://localhost:"+port()+datasetPath()+"/data" ; }


    @Test public void getFileFromWWW(){
        Model model = FileManager.get().loadModel(uriKoma, "TURTLE");
        model.write(new PrintWriter(System.out));
    }

    @Test public void uploadFileToServer(){
        Model model = FileManager.get().loadModel(uriKoma, "TURTLE");
        Dataset ds = DatasetFactory.createTxnMem();
        FusekiServer server = FusekiServer.create()
                .setPort(3030)
                .add("/dataset", ds)
                .build();
        server.start();
        printMeta(server.server);

        DatasetAccessor accessor = connectToService();
        accessor.putModel(model);

        server.stop();
    }

    private void printMeta(Server server){

        System.out.println(server.getURI().toString());
        for (Connector connector : server.getConnectors()) {
            System.out.println(connector.toString());
        }
        System.out.println( server.toString());;

    }
    static DatasetAccessor connectToService()
    {
        return DatasetAccessorFactory.createHTTP(serviceGSP()) ;
    }

    static DatasetAccessor connectToDataset()
    {
        return DatasetAccessorFactory.createHTTP(urlDataset()) ;
    }


}
