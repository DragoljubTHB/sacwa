package de.thb.koma.service.sparql.jena;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.InputStream;
import java.util.Map;

public class Handler implements RequestHandler<RequestClass, String>{

    private final String ENV_BUCKET = "BUCKET";
    private final String INPUT_KEY_BUCKET_KEY = "bucketKey";
    private final String INPUT_KEY_QUERY = "query";

    private Map<String, Object> mappedInput;
    private RequestClass request;
    private LambdaLogger l;
    @Override
    public String handleRequest(RequestClass input, Context context) {
        l = context.getLogger();
        request = input;

        return executeQuery(request.getQuery(), request.getBucketKey());
    }
    public String executeQuery(String aQuery, String bucketKey) {
        Model model = createModel(fetchS3Object(Regions.US_WEST_2.getName()
                , System.getenv(ENV_BUCKET)
                , bucketKey)
        );;

        Query query = QueryFactory.create(aQuery);
        StringBuilder sb = new StringBuilder();
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {

                QuerySolution soln = results.nextSolution();
                Resource r = soln.getResource("comp");
                l.log("RESOURCE : "+r.getLocalName());
                //soln.varNames().forEachRemaining(l::log);
                sb.append(r.toString());
                sb.append("\n");
                //Literal name = soln.getLiteral("comp");
                //System.out.println(name);
            }
        }
        l.log(sb.toString());
        return sb.toString();
    }

    private Model createModel(InputStream in){
        Model m = ModelFactory.createDefaultModel();
        RDFDataMgr.read(m, in, Lang.TURTLE);
        return m;
    }

    private InputStream fetchS3Object(String region, String bucket, String bucketKey){
        return AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .build()
                .getObject(bucket
                        , bucketKey).getObjectContent();
    }
}

/*
        try {
            mappedInput = JSON.std.mapFrom(input.toString());
            mappedInput.forEach((k, v) -> context.getLogger().log(k +" : "+v.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
/*
 String queryString = "" +
            "   PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
            "   PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
            "   PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
            "SELECT ?comp WHERE { " +
            "    ?comp rdf:type owl:Class ." +
            "}";
 */
/*
"query": "PREFIX owl: <http://www.w3.org/2002/07/owl#> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>   PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> SELECT ?comp WHERE { ?comp rdf:type owl:Class .}"
 */