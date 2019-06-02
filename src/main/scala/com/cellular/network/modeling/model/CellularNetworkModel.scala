package com.cellular.network.modeling.model
import com.cellular.network.modeling.entity.CellularNetwork
import com.outworkers.phantom.dsl._
import scala.concurrent.Future

abstract class CellularNetworkModel extends Table[CellularNetworkModel, CellularNetwork] {
  override def tableName: String = "integris"

  object id extends TimeUUIDColumn with PartitionKey {
    override lazy val name = "integris_id"
  }
  object Patata extends StringColumn //Presences
//  object Gm extends StringColumn //Male
//  object Gf extends StringColumn //Female
//  object F1 extends StringColumn // Age range < 18
//  object F2 extends StringColumn // Age range [18, 30]
//  object F3 extends StringColumn // Age range [31, 40]
//  object F4 extends StringColumn // Age range [41, 50]
//  object F5 extends StringColumn // Age range [51, 60]
//  object F6 extends StringColumn // Age range > 60
//  object Ni extends StringColumn // Italian
//  object Ns extends StringColumn // Foreign
//  object Tb extends StringColumn // Business
//  object Tc extends StringColumn  //Consumer


  def getCellularNetworkById(id: UUID): Future[Option[CellularNetwork]] = {
    select
      .where(_.id eqs id)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .one()
  }

  def deleteById(id: UUID): Future[ResultSet] = {
    delete
      .where(_.id eqs id)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .future()
  }

}


