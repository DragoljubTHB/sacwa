# BA : KOMA

## sparQL

queries: 
all competencies of Alice
competencies of Alice in Fach


= query lang, protokol layer, output spec.
service : federated graphs link to this.endpoint

http query :: parts:
URI of sparql endpoint
named-graph-uri=http://...rdf
query=SELECT+%...WHERE...GRAPH

key word:

ASK -> at least one res, true/false, xml/json
DEFINE
CONSTRUCT
FILTER
... 

GRAPH? 
select distinct ?g 
where { 
GRAPH ?g { ?s ?p ?o . }
}
