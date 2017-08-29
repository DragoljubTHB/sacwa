#lambda wrapper for sparql
#### endpoint: koma.thb.de/sparql <-- #?
result after query: koma.thb.de/id/

#### queries
#####competencies with associated learner
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
PREFIX koma: <https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl#>


SELECT ?comp ?learn
  	WHERE {
		?comp rdf:type koma:Competency  .
		?crec koma:associatedCompetency ?comp .
		?learn rdf:type koma:Learner .
		?crec koma:creator ?learn .
}
ORDER BY ASC (?comp) 
