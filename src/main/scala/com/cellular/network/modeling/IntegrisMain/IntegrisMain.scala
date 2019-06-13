package com.cellular.network.modeling.IntegrisMain
import com.cellular.network.modeling.database.Database
import com.cellular.network.modeling.entity.CellularNetwork
import scala.concurrent.Future
import com.datastax.driver.core.utils.UUIDs
import com.cellular.network.modeling.database.{Database, CellularNetworkDatabase}
import scala.concurrent.Future
import com.outworkers.phantom.dsl._
import scala.io.Source

object IntegrisMain {
  def main(args: Array[String]) {
    val db = new CellularNetworkDbCreator()
    db.database.create()
    val filename = "src/main/scala/com/cellular/network/modeling/IntegrisMain/sms-call-internet-tn-2013-12-30.txt"
    var patata = new TelecommunicationsTSVReader(filename)
    patata.readFile()
    
    return
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
    private def drop(cellular_network: CellularNetwork) = Database.delete(cellular_network)
    
    def readFile() = {
      for (line <- Source.fromFile(fileName, "UTF8").getLines()) {
        var values = line.stripLineEnd.split("\t", -1)
        var sample = CellularNetwork(
          id = UUIDs.timeBased(),
          square_id = this.parseToInt(values(0)),
          time_interval = this.parseToLong(values(1)),
          country_code = this.parseToInt(values(2)),
          sms_in_activity = this.parseToFloat(values(3)),
          ms_out_activity = this.parseToFloat(values(4)),
          call_in_activity = this.parseToFloat(values(5)),
          call_out_activity= this.parseToFloat(values(6)),
          internet_traffic_activity =this.parseToFloat(values(7))
        )
        this.store(sample)
      }
    }
    
    def parseToLong(value: String): Long = if(value.nonEmpty) value.toLong else 0
    
    def parseToInt(value:String):Int = if(value.nonEmpty) value.toInt else 0
    
    def parseToFloat(value:String):Float = if(value.nonEmpty) value.toFloat else 0
  
  }
}