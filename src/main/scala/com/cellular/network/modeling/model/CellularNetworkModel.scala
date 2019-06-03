package com.cellular.network.modeling.model
import com.cellular.network.modeling.entity.CellularNetwork
import com.outworkers.phantom.dsl._
import scala.concurrent.Future

abstract class CellularNetworkModel extends Table[CellularNetworkModel, CellularNetwork] {
  override def tableName: String = "integris"

  object id extends TimeUUIDColumn with PartitionKey {
    override lazy val name = "integris_id"
  }
  object presences extends StringColumn//Presences
  object male extends StringColumn//Male
  object female extends StringColumn//Female
  object age_under_18 extends StringColumn// Age range < 18
  object age_between_18_30 extends StringColumn// Age range [18, 30]
  object age_between_31_40 extends StringColumn// Age range [18, 30]
  object age_between_41_50 extends StringColumn// Age range [41, 50]
  object age_between_51_60 extends StringColumn// Age range [51, 60]
  object age_older_60 extends StringColumn// Age range > 60
  object italian extends StringColumn// Italian
  object foreign extends StringColumn// Foreign
  object business extends StringColumn// Business
  object consumer extends StringColumn //Consumer


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


