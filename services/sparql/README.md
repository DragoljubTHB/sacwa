#lambda wrapper for sparql
#### endpoint: koma.thb.de/sparql <-- #?
result after query: koma.thb.de/id/

#### queries
#####competencies with associated learner
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
PREFIX koma: <http://www.semanticweb.org/dado/ontologies/2017/7/untitled-ontology-56#>


SELECT ?comp ?learn
  	WHERE {
		?comp rdf:type koma:Competency  .
		?crec koma:associatedCompetency ?comp .
		?learn rdf:type koma:Learner .
		?crec koma:creator ?learn .
}
ORDER BY ASC (?comp) 
-------------

{
    "Buckets": [
        {
            "Name": "koma.thb.de",
            "CreationDate": "2017-07-20T09:02:26.000Z"
        },
        {
            "Name": "ontology.thb.de",
            "CreationDate": "2017-08-10T21:14:53.000Z"
        }
    ],
    "Owner": {
        "DisplayName": "milasino",
        "ID": "39e3083486fec73b9fff21f87738956fd276b81c4fd3942fc900cf619fb5cd23"
    }
}
