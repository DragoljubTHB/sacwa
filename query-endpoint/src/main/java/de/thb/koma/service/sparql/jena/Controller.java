package de.thb.koma.service.sparql.jena;

import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    private String bucket;
    private String region;

    public Controller(String bucket, String region) {
        this.bucket = bucket;
        this.region = region;
    }

    public String executeQuery(String aQuery, String bucketKey) {
        String toClient;
        Model model = createModel(fetchS3Object(
                  region
                , bucket
                , bucketKey)
        );

        ObjectMapper mapper = new ObjectMapper();
        QueryResultWithMap resultWithMap =
                new QueryResultWithMap();

        Query query = QueryFactory.create(aQuery);
        List<String> vars = query.getResultVars();
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {

            Map<String, String> yeah = new LinkedHashMap<>();


            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {

                vars.forEach(v -> yeah.put(v, results.nextSolution().getResource(v).toString()));

                resultWithMap.getBody().add(yeah);

            }
            toClient =  mapper.writeValueAsString(resultWithMap);
        } catch (JsonProcessingException e) {
            toClient = e.getMessage();
        }
        return toClient;
    }

    private Model createModel(InputStream in) {
        Model m = ModelFactory.createDefaultModel();
        RDFDataMgr.read(m, in, Lang.TURTLE);
        return m;
    }

    private InputStream fetchS3Object(String region, String bucket, String bucketKey) {
        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .build()
                .getObject(bucket
                        , bucketKey).getObjectContent();
    }
}
