package de.thb.koma.service.sparql.jena;/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.util.FileManager;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ControllerTest {
    final String uriKoma = "https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl";
    //final String uriKoma = "koma-complex.owl";
    final String rdfSyntax = "TURTLE";
    final String PREFIXES ="" +
            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "PREFIX koma: <https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl#>\n" +
            "\n" ;
    final String QUERY_00 = "" +
            "SELECT * WHERE { " +
            "    ?s ?p ?o ." +
            "}";
    final String QUERY_01 = PREFIXES+
            "SELECT ?s  WHERE {\n" +
            "\n" +
            "\t?s rdf:type koma:Competency .\n" +
            "\n" +
            "}";

    final String QUERY_02 = PREFIXES+
            "SELECT ?s WHERE {" +
            "    ?s rdf:type owl:Class ."+
            "}";

    final String QUERY_03 = PREFIXES+
            "SELECT ?cr WHERE {"+
            " ?cr rdf:type koma:CompetencyRecord . \n"+
            " ?comp koma:hasPerformanceIndicator ?perfIndic ."+
            "}";

    private static Model mModel;
    @Before public void fetchModel(){
        mModel = getOntologyTurtle(uriKoma, rdfSyntax);
    }
    @Test public void exeq_query_01(){
        exeq(QUERY_03, mModel);
    }

    @Test
    public void exeq_query_00(){
        exeq(QUERY_00, mModel);
        exeq(QUERY_01, mModel);
        exeq(QUERY_02, mModel);
    }


    private void exeq(String aQuery, Model aModel) {
        ObjectMapper mapper = new ObjectMapper();
        QueryResultWithMap ba = new QueryResultWithMap();
        Map<String, String> ng;
        Query query = QueryFactory.create(aQuery);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, aModel)) {
            ResultSet results = qexec.execSelect();

            results.getResultVars().forEach(System.out::println);
            while (results.hasNext()) {
                ng = new LinkedHashMap<>();
                QuerySolution soln = results.nextSolution();

                for(String v : results.getResultVars()){
                    RDFNode node = soln.get(v);
                    ng.put(v, node.isResource() ?
                            soln.getResource(v).getLocalName() :
                            soln.getLiteral(v).getString());
                }
                ba.getBody().add(ng);

                //QuerySolution soln = results.nextSolution();
                //soln.varNames().forEachRemaining(System.out::println);

                //( ?p = rdf:type ) ( ?o = owl:Class ) ( ?s = <https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl#CompetencyProfile> ) -> [Root]
                //System.out.println(soln.toString());

                //Literal name = soln.getLiteral("comp");
                //System.out.println(name);
            }
            ba.getBody().forEach(System.out::println);
            mapper.writeValue(new FileOutputStream("res.json"), ba);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Model getOntologyTurtle(String uriKoma, String rdfSyntax) {
        return FileManager.get().loadModel(uriKoma, rdfSyntax);
    }

}
