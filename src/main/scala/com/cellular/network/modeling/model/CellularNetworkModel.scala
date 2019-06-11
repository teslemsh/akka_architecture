package com.cellular.network.modeling.model
import com.cellular.network.modeling.entity.CellularNetwork
import com.outworkers.phantom.dsl._
import scala.concurrent.Future

abstract class CellularNetworkModel extends Table[CellularNetworkModel, CellularNetwork] {
  override def tableName: String = "integris"

  object id extends TimeUUIDColumn with PartitionKey {
    override lazy val name = "integris_id"
  }
  object square_id extends StringColumn
  object time_interval extends StringColumn
  object country_code extends StringColumn
  object sms_in_activity extends StringColumn
  object ms_out_activity extends StringColumn
  object call_in_activity extends StringColumn
  object call_out_activity extends StringColumn
  object internet_traffic_activity extends StringColumn
  
  
  
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


