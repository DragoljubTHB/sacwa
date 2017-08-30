package de.thb.koma.service.sparql.impl.lambda;

import org.apache.jena.fuseki.embedded.FusekiServer;
import org.apache.jena.query.ARQ;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.system.Txn;

public class SparqlFuseki {
    /**
     * IRI <- IRI Version :: defined at runtime 1: lambda as param 2:config file
     */
    String localUri = "./src/main/resources/ds";
    String uri = "https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl";
    String querySimple = "" +
            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "\n" +
            "SELECT ?comp WHERE {\n" +
            "\t?comp rdf:type owl:Class .\n" +
            "}\n" +
            "ORDER BY ?label";
    String query = "" +
            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
            "PREFIX koma: <https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl#>\n" +
            "\n" +
            "\n" +
            "SELECT ?comp ?learn\n" +
            "  \tWHERE {\n" +
            "\t\t?comp rdf:type koma:Competency  .\n" +
            "\t\t?crec koma:associatedCompetency ?comp .\n" +
            "\t\t?learn rdf:type koma:Learner .\n" +
            "\t\t?crec koma:creator ?learn .\n" +
            "}\n" +
            "ORDER BY ASC (?comp) ";

    public void server() {
        //Dataset ds = DatasetFactory.createTxnMem();
        ARQ.init();
        FusekiServer server = FusekiServer.create().setPort(8080)
                .build();
        server.start();
        try (RDFConnection conn = RDFConnectionFactory.connect(uri)) {
            Txn.executeRead(conn, ()->{
                conn.querySelect(querySimple, qs -> {
                            Resource subject = qs.getResource("s");
                            System.out.println("Subject: " + subject);
                        }
                );
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        server.stop();
    }

}
