# ontology.thb.de 
##Large Files: Values of Keys stored in DynamoDB
# https://ec.europa.eu/esco/resources/data/static/model/html/model.xhtml#Skill
# notes on web-semantics

#owl: 
assumptions:
- open world : absence of info != negative info
- no unique name 
owl2 : el ,rule-l, query-l <= description-l <=full 

##shoind -> owl1
Axiom: 
Box:: T: subclass R: subproperty A: facts
Constructors

##shroid -> owl2
syntax -> turtle
Individuals: Class, NamedIndividual (existence on his own)
Properties: Obj-> link to another Resource DataType-> link to literal
          : can restrict domain -> range 
Class:
:s owl:disjointWith :p .
[] a owl:AllDisjointClasses ; owl:members { :c1 :c2 ... } .
:s owl:equivalentWith :p .

ClosedClasses ( Nominals ):
:s owl:oneOf ( :c1, :c2 ) .

ClassCTORS : 
:s a owl:Class; owl:intersectionOf (:c1 :c2) . 
:s a owl:Class; owl:equivalentClass [owl:unionOf (:c1 :c2)] .
:s a owl:Class; rdfs:subClassOf [ owl:complementOf :c1 ] .

Props Restrict: owl: 
vals: hasValue - allValuesFrom - someValuesFrom @4_4
cardinality, nums: - minCardinality - maxCardinality
Prop Rel
Trans Sym asym funct inverFunct Reflex Irreflex

Relationships::
Individuals:: 
:s owl:sameAs/differentFrom :p .
[] a owl:AllDifferent ; owl:distinctMembers { :c1 :c2 ... } .



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

#Turtle syntax: 
. -> end statement
; -> next attrib

#ManyToMany unique-link
multivalued relation repr with: b-node with same properties
states: exists smth with specific props