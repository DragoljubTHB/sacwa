# dynamoDB
## small, structured data: Values in S3

## Structure: 
### rdb row => dynamodDB item
### item: hash-pk | attr#1 | attr#2
### item: hash-pk | range-pk | attr#1 | attr2

## Characteristics: 
### eventual consistency
### read: eventual|strong consistent read
### AttributesDefinitions | KeySchema | ProvisionedThroughtput

## Use: 
### hash-pk to find the URL in S3 
### in request: 
### if binary vals => base64 encoded or NULL and not empty

#### table: S
#### table: P
#### table: O


## persistency: 

### 1: create S3 with .rdf files => ontology
### 2: build dynamoDB tables based on 1
### 3: create lambda to access the data

#### 1.0: er-diagram to ontology conceptual diagram
#### 1.1: protege => .rdf 

#### 2.0: create lambda to read from s3 and update dynamoDB

#### 3.0: create gui to query through api-gateway => lambda => storage
