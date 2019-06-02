package com.cassandra.phantom.modeling.test.utils

import com.cellular.network.modeling.database.{CellularNetworkDatabase, Database}
import com.outworkers.phantom.database.DatabaseProvider
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures

trait CellularNetworkDbProvider extends DatabaseProvider[CellularNetworkDatabase] {
  override def database: CellularNetworkDatabase = Database
}

trait CassandraSpec extends FlatSpec
  with Matchers
  with Inspectors
  with ScalaFutures
  with OptionValues
  with BeforeAndAfterAll
  with CellularNetworkDbProvider