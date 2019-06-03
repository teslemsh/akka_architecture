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
      presences = "patata", //Presences
      male = "patata", //Male
      female = "patata", //Female
      age_under_18 = "patata", // Age range < 18
      age_between_18_30 = "patata", // Age range [18, 30]
      age_between_31_40 = "patata", // Age range [18, 30]
      age_between_41_50 = "patata", // Age range [41, 50]
      age_between_51_60 = "patata", // Age range [51, 60]
      age_older_60 = "patata", // Age range > 60
      italian = "patata", // Italian
      foreign = "patata", // Foreign
      business = "patata", // Business
      consumer = "patata" //Consumer
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