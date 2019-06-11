# Pending to write

### Install 

* Clone this repository with Git
* Install Docker and Docker Compose
* Install Java and Sbt, check dependencies in build.sbt(version of sbt)

### Data source

This repo works with data from Spazio. It's a public data, to get more examples please
authenticate in their page.
* https://dandelion.eu/datagems/SpazioDati/telecom-sms-call-internet-tn/resource/

### Run project

* docker-compose up
* sbt compile 
* sbt run(compile life cycle, compile no needed)

### Data model

<pre>
CellularNetwork(
    id: UUID,
    square_id: String,
    time_interval: String,
    country_code: String,
    sms_in_activity: String,
    ms_out_activity: String,
    call_in_activity: String,
    call_out_activity: String,
    internet_traffic_activity: String
)
</pre>
