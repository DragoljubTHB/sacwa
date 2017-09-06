package de.thb.lambda.feedback;

public class RequestClass {
    private String bucketKey;
    private String query;

    @Override
    public String toString() {
        return "{\"bucketKey\" : " + "\""+bucketKey+"\""+","+
                " \"query\" : \""+query+"\" }";
    }

    public String getBucketKey() {
        return bucketKey;
    }

    public void setBucketKey(String bucketKey) {
        this.bucketKey = bucketKey;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
