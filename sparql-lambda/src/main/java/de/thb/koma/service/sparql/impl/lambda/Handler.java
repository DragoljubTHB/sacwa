package de.thb.koma.service.sparql.impl.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.jena.fuseki.embedded.FusekiServer;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

import java.io.PrintWriter;

public class Handler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("handleRequest "+input);

        getObjectFromS3();

        FusekiController ctrl = new FusekiController();
        ctrl.initServer();




        ctrl.stopServer();


        return "done ";
    }

}
