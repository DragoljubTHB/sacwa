package de.thb.koma.service.sparql.impl.lambda;

import com.amazonaws.handlers.RequestHandler2;
import de.thb.koma.repository.sparql.SparqlFuseki;

public class Handler extends RequestHandler2 {
    public String handler(){
        SparqlFuseki fuseki = new SparqlFuseki();
        fuseki.server();
        return "";
    }
}
