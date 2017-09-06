package de.thb.koma.service.sparql.jena;/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.apache.jena.base.Sys;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;
import org.junit.Test;


import java.io.PrintWriter;
import java.util.StringJoiner;

import static org.junit.Assert.*;

public class HandlerTest {
    String uriKoma = "https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl";
    @Test
    public void query_owl() {
        Model model = getOntologyTurtle();
        String queryString = "" +
                "   PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "   PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "   PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "SELECT ?comp WHERE { " +
                "    ?comp rdf:type owl:Class ." +
                "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                //soln.varNames().forEachRemaining(System.out::println);
                System.out.println(soln.getResource("comp").toString());
                //Literal name = soln.getLiteral("comp");
                //System.out.println(name);
            }
        }
    }

    private Model getOntologyTurtle() {
        return FileManager.get().loadModel(uriKoma, "TURTLE");
    }
}
