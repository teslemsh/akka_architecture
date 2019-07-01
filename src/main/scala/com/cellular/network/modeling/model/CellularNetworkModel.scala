package com.cellular.network.modeling.model
import com.cellular.network.modeling.entity.CellularNetwork
import com.outworkers.phantom.dsl._

import scala.concurrent.Future

abstract class CellularNetworkModel extends Table[CellularNetworkModel, CellularNetwork] {
  override def tableName: String = "integris"

  object id extends TimeUUIDColumn with PrimaryKey {
    override lazy val name = "id"
  }
  object square_id extends IntColumn {
    override lazy val name = "square_id"
  }
  object time_interval extends DateTimeColumn with PartitionKey {
    override lazy val name = "time_interval"
  }
  object country_code extends IntColumn {
    override lazy val name = "country_code"
  }
  object country_name extends StringColumn
  object sms_in_activity extends FloatColumn
  object ms_out_activity extends FloatColumn
  object call_in_activity extends FloatColumn
  object call_out_activity extends FloatColumn
  object internet_traffic_activity extends FloatColumn
  
  
  
  def getCellularNetworkByTimeInterval(time_interval: DateTime): Future[List[CellularNetwork]] = {
    select
      .where(_.time_interval eqs time_interval)
      .fetch()
  }

//  def deleteById(id: UUID): Future[ResultSet] = {
//    delete
//      .where(_.id eqs id)
//      .consistencyLevel_=(ConsistencyLevel.ONE)
//      .future()
//  }

}


