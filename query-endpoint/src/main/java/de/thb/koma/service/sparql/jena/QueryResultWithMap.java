package de.thb.koma.service.sparql.jena;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryResultWithMap {
    private List<Map<String, String>> body;

    public QueryResultWithMap() {
        this.body = new ArrayList<>();
    }

    public List<Map<String, String>> getBody() {
        return body;
    }

    public void setBody(List<Map<String, String>> body) {
        this.body = body;
    }
}
