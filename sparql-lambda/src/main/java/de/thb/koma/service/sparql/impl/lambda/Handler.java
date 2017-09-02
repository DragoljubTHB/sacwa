package de.thb.koma.service.sparql.impl.lambda;

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
import org.apache.jena.riot.RDFLanguages;
import org.apache.jena.util.FileManager;

import java.io.PrintWriter;

public class Handler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("handleRequest "+input);

        S3Object s3Object = AmazonS3ClientBuilder.defaultClient()
                .getObject("ontology.koma.de", "koma-complex.owl");
        logger.log(String.valueOf(s3Object.getObjectContent()));

        FusekiController ctrl = new FusekiController();
        ctrl.initServer();

        //ctrl.uploadFileToServer(String.valueOf(s3Object.getObjectContent()), RDFLanguages.strLangTurtle);


        ctrl.stopServer();


        return "done ";
    }

}
