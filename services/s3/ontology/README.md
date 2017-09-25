# KOMA Ontologie 
### s3://ontology.thb.de

### Datenbasis für den konzeptionellen Beweis 



-----------
## Notizen

##Large Files: Values of Keys stored in DynamoDB
# https://ec.europa.eu/esco/resources/data/static/model/html/model.xhtml#Skill
Intelligent Learning Infrastructures in Knowledge Intensive Organizations: A Semantic Web Perspective
# notes on web-semantics

#ontol-eng 101
determine-scope: 
Umsetzung der Rahmenlehrpläne in eine Ontologie

Consider reuse: aktuell keine umgesetzte Ontologie veröffentlicht: 
mix von moodle - RCD
Enumerate Terms:  Terminologie
Kompetenz: 
KompetenzProfil: 
Skill/Fertigkeit: 
Wissen/Knowledge: 
Andere/Other: 
KompetenzNiveau/Level: 

LehrVeranstalltung:
KlassenStufe: 
LearnActivity: 

Define Classes and Hierarchies:


Define Properties/Attributes of Classes: 

#ontol- engineering for methodology 
types : top - domain - app
semantic gap:: how to find out whether 2 ontologies mean the same thing
ontologies enable interoperability of metadata: 
design for develop
mapping for comparison
merging for efficient combination of ontologies
learning for learn new ontos from given sets
##desing: 
activities: management - develop - support
###management
scheduling - control - quality assurance
###development
pre - develop - post
###suport
knowledge acquisition - eval - integr - merge - align->map - doc - config man

## design:app
welche stand von kompetenzen hat eine Klassenstufe?
kompetenz-rückstände/auffälligkeit von eine Klassenstufe?
gegeben sein ein stand, welche leistung kann ich von Klassenstufe erwarten?
wurde skill-x zu Klassenstuf-y vergeben? 


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


Schema 
ALC :: Description Logics TBOX + ABOX :: Concepts + roles, Individual + Operators/Combinators
ALC :: Atomic Types + Constructors



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
. -> pred + subj
; -> pred      


#ManyToMany unique-link
multivalued relation repr with: b-node with same properties
states: exists smth with specific props

# example code
:lv_I :seq ( :la_I :la_II :la_III ) .

:lv_I :seq _:ID1, _:ID2 .

_:ID1 :hasAtendant :s_I ; :hasDate "2017-08-22"^^xsd:date .
_:ID2 :hasAtendant :s_II ; :hasDate "2017-08-22"^^xsd:date .

:lv_I rdf:type owl:NamedIndividual ,
               :LV ;
      :seq [ :hasAtendant :s_I ;
             :hasDate "2017-08-22"^^xsd:date .
           ] ,
           [ :hasAtendant "att_1"^^xsd:string ;
             :hasDate "2017-08-22"^^xsd:date .
           ] ,
           "la"^^xsd:string .
###sync s3
aws --profile dado-admin s3 sync ontology/ s3://ontology.thb.de --acl public-read
