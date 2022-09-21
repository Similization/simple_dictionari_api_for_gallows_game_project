# This is simple dictionary api 
###### (it is a part of my big future project)

## It contains
- sql file - to create and fill your own database with any words and pairs you want (used MySQL)
- controller - to track specific queries
```
GET    api/words                 - to get all words
GET    api/words/{id}            - to get word by id
GET    api/words/{id}/same_words - to get same words to word with id 
GET    api/same_words/{id}       - to get pair of same words by id   
POST   api/words                 - to add new word                   (use postman with body row type JSON)
PUT    api/words                 - to update word                    (use postman with body row type JSON)
DELETE api/words/{id}            - to delete word by id
DELETE api/words                 - to delete all words
```
- configuration with configuration class (not xml)
- entities - to work with databases rows like objects
- repository/dao
- service
- aspects - to follow the methods and log information into the logger file
- exception handlers
- logs

#### The main goal of this project is to develop knowledge in this area and gradually move to best practice wherever possible
