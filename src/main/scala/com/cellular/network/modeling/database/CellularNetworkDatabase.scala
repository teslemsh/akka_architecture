package com.cellular.network.modeling.database
import com.cellular.network.modeling.connector.Connector._
import com.cellular.network.modeling.entity.CellularNetwork
import com.cellular.network.modeling.model.{CellularNetworkModel}
import com.outworkers.phantom.connectors.CassandraConnection
import com.outworkers.phantom.dsl._

import scala.concurrent.Future

/**
  * This is our Database object that wraps our two existing tables,
  * giving the ability to receive different connectors
  * for example: One for production and other for testing
  */
class CellularNetworkDatabase(override val connector: CassandraConnection) extends Database[CellularNetworkDatabase](connector) {
  object CellularNetworkModel extends CellularNetworkModel with connector.Connector

  /**
    * Save a cellular_network
    *
    * @param cellular_network
    * @return
    */
  def saveOrUpdate(cellular_network: CellularNetwork): Future[ResultSet] = {
    Batch.logged
      .add(CellularNetworkModel.store(cellular_network))
      .future()
  }

  /**
    * Delete a cellular_network
    *
    * @param cellular_network
    * @return
    */
  def delete(cellular_network: CellularNetwork): Future[ResultSet] = {
    Batch.logged
      .add(CellularNetworkModel.delete.where(_.square_id eqs cellular_network.square_id))
      .future()
  }
}

/**
  * This is the database, it connects to a cluster with multiple contact points
  */
object Database extends CellularNetworkDatabase(connector)
