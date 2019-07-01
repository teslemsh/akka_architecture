package com.cellular.network.modeling.IntegrisMain
import com.cellular.network.modeling.database.Database
import com.cellular.network.modeling.entity.CellularNetwork
import scala.concurrent.Future
import com.datastax.driver.core.utils.UUIDs
import com.cellular.network.modeling.database.{Database, CellularNetworkDatabase}
import scala.concurrent.Future
import com.outworkers.phantom.dsl._
import scala.io.Source
import  org.joda.time.DateTime
import  org.joda.time.DateTimeZone
import scala.collection.mutable.Map
import akka.actor.{ Actor, ActorLogging, ActorRef, ActorSystem, Props }

object IntegrisMain {
  def main(args: Array[String]) {
    val db = new CellularNetworkDbCreator()
    db.database.create()
    
    val system = ActorSystem("HelloSystem")
    val systemActor = system.actorOf(Props[IngestDataActor], name = "IngestDataActor")
    
    systemActor ! "ingest data"
    systemActor ! "buenos dias"
    
    return
  }
}

class IngestDataActor extends Actor with ActorLogging {
  def receive = {
    case "ingest data" => {
      log.info("Greeting received (from " + sender() + "): ingest data")
      
      val filename = "src/main/scala/com/cellular/network/modeling/IntegrisMain/sms-call-internet-tn-2013-12-30.txt"
      var telecommunicationsTSVReader = new TelecommunicationsTSVReader(filename)
      telecommunicationsTSVReader.readFile()
  
    }
    case _  => log.info("Greeting received (from " + sender() + "):" + "patata")
  }
}

trait CellularNetworkDbProvider extends DatabaseProvider[CellularNetworkDatabase] {
  override def database: CellularNetworkDatabase = Database
}
class CellularNetworkDbCreator extends CellularNetworkDbProvider {}

class TelecommunicationsTSVReader(val fileName: String) {
  /**
    * Utility method to store into both tables
    *
    * @param cellular_network : data related to communication process
    * @return a [[Future]] of [[ResultSet]]
    */
  private def store(cellular_network: CellularNetwork) = Database.saveOrUpdate(cellular_network)
  
  /**
    * Utility method to delete into both tables
    *
    * @param cellular_network the cellular_network to be deleted
    * @return a [[Future]] of [[ResultSet]]
    */
  //    private def drop(cellular_network: CellularNetwork) = Database.delete(cellular_network)
  
  
  def readFile() = {
    val countriesData = this.readCountriesCode("src/main/scala/com/cellular/network/modeling/IntegrisMain/countries_phone_code.csv")
    var country_name = ""
    
    println("starting reading file", fileName)
    
    for (line <- Source.fromFile(fileName, "UTF8").getLines()) {
      var values = line.stripLineEnd.split("\t", -1)
      var cellularNetwork = CellularNetwork(
        id = UUIDs.timeBased(),
        square_id = this.parseToInt(values(0)),
        time_interval = new DateTime(this.parseToLong(values(1)), DateTimeZone.UTC),// 1970-06-14T18:17:33.511Z
        country_code = this.parseToInt(values(2)),
        country_name = countriesData.getOrElse(values(2), "No such state"),
        sms_in_activity = this.parseToFloat(values(3)),
        ms_out_activity = this.parseToFloat(values(4)),
        call_in_activity = this.parseToFloat(values(5)),
        call_out_activity= this.parseToFloat(values(6)),
        internet_traffic_activity = this.parseToFloat(values(7))
      )
      // Stores every entity into cassandra
      this.store(cellularNetwork)
    }
    println("Ingestion completed")
  }
  
  def readCountriesCode(file: String): Map[String,String] = {
    println("starting countries file", file)
    val map = scala.collection.mutable.Map[String,String]()
    for (line <- Source.fromFile(file, "UTF8").getLines()) {
      var cols = line.stripLineEnd.split(",")
      var country_code = cols(1)
      var country_name = cols(0)
      map += country_code -> country_name
    }
    println("Countries data readed")
    return map
  }
  
  def parseToLong(value: String): Long = if(value.nonEmpty) value.toLong else 0
  
  def parseToInt(value:String):Int = if(value.nonEmpty) value.toInt else 0
  
  def parseToFloat(value:String):Float = if(value.nonEmpty) value.toFloat else 0
  
}