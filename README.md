# JsonCsvGenerator

Cardinality interview

# Requirements
gitbash or similar <br />
docker <br />
postman or similar tool <br />
# How to run 
Clone repo <br />
Run command docker-compose up <br />
Application JsonApplication should be available on http://localhost:9000 <br />
Application CsvConverter should be available on http://localhost:9001 <br />

# API
**Generate JSON list with size parameter** <br />
_/generate/json/{size}_

**Fetch JSON list from JsonGenerator and convert to CSV** <br />
_/generate/csv/{size}_

**Fetch JSON list from JsonGenerator and convert to CSV with indivdual parameters** <br />
_/generate/csv/{size}/filter?property_

# GENERATE JSON LIST 
**Use endpoint /generate/json/{size} with POST method and set the size as pathvariable** <br />
**JsonGenerator will return your JSON list in response** <br />
_e.g. http://localhost:9000/generate/json/100_

# GENERATE JSON LIST AND CONVERT TO CSV
**Use endpoint /generate/csv/{size} with POST method and set the size as pathvariable** <br />
_e.g. http://localhost:9001/generate/csv/100_ <br />
CsvConverter will fetch generated JSON list from JsonGenerator and convert it .csv file under: {user.dir}

**Use endpoint /generate/csv/{size} with POST method and set the size as pathvariable** <br />
_e.g. http://localhost:9001/generate/csv/10000/filter?property=_id&property=latitude&property=longitude_ <br />
**CsvConverter will fetch generated JSON list from JsonGenerator and convert it .csv file under:_{user.dir}_ with only parameters set**
