package de.thb.koma.service.sparql.impl.lambda;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.jena.fuseki.embedded.FusekiServer;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFLanguages;
import org.apache.jena.riot.system.StreamRDF;
import org.apache.jena.util.FileManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

public class Handler implements RequestHandler<OntologyKey, String> {
    String expectedEnv = "BUCKET";

    @Override
    public String handleRequest(OntologyKey input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("handleRequest " + input);

        FusekiController ctrl = new FusekiController();
        ctrl.initServer();
        logger.log("server init");

        ctrl.uploadModelToServer(ctrl.getModelFrom(
                AmazonS3ClientBuilder.standard()
                        .withRegion(Regions.US_WEST_2)
                        .build()
                        .getObject(System.getenv(expectedEnv)
                                , input.getKey()).getObjectContent()));

        ctrl.stopServer();


        return "done ";
    }

}
