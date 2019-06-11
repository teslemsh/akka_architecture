package com.cellular.network.modeling.IntegrisMain
import com.cellular.network.modeling.database.Database
import com.cellular.network.modeling.entity.CellularNetwork
import scala.concurrent.Future
import com.datastax.driver.core.utils.UUIDs
import com.cellular.network.modeling.database.{Database, CellularNetworkDatabase}
import scala.concurrent.Future
import com.outworkers.phantom.dsl._

object IntegrisMain {
  def main(args: Array[String]) {
    val db = new ValueCreator()
    db.database.create()
    val sample = CellularNetwork(
      id = UUIDs.timeBased(),
      square_id = "patata",
      time_interval = "patata",
      country_code = "patata",
      sms_in_activity = "patata",
      ms_out_activity = "patata",
      call_in_activity = "patata",
      call_out_activity= "patata",
      internet_traffic_activity = "patata"
    )
    println(sample)
    this.store(sample)
    return
  }

  /**
    * Utility method to store into both tables
    *
    * @param song the song to be inserted
    * @return a [[Future]] of [[ResultSet]]
    */
  private def store(cellular_network: CellularNetwork) = Database.saveOrUpdate(cellular_network)

  /**
    * Utility method to delete into both tables
    *
    * @param song the song to be deleted
    * @return a [[Future]] of [[ResultSet]]
    */
  private def drop(cellular_network: CellularNetwork) = Database.delete(cellular_network)

  trait CellularNetworkDbProvider extends DatabaseProvider[CellularNetworkDatabase] {
    override def database: CellularNetworkDatabase = Database
  }
  class ValueCreator extends CellularNetworkDbProvider {}
}