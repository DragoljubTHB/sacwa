# ontology.thb.de 
##Large Files: Values of Keys stored in DynamoDB
# https://ec.europa.eu/esco/resources/data/static/model/html/model.xhtml#Skill
# notes on web-semantics
Bnodes = multi-valued relation -> [ notation for turtle ]

#debugging: 
cURL to check operability for:
normal web browser: curl [-I (for headears)] <url>
semantic web browser: curl [-I -H "accept: application/rdf+xml"] <url>

#linking
http://www.example.com/id/alice
Identifier for Alice, the person
http://www.example.com/people/alice
Alice's homepage
http://www.example.com/data/alice
RDF document with description of Alice
