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
