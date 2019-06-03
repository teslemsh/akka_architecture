//package com.cellular.network.modeling.IntegrisMain
//
//import com.cellular.network.modeling.database.{CellularNetworkDatabase, Database}
//
//trait CellularNetworkDbProvider extends DatabaseProvider[CellularNetworkDatabase] {
//  override def database: CellularNetworkDatabase = Database
//}
//
//trait CassandraSpec extends FlatSpec
//  with Matchers
//  with Inspectors
//  with ScalaFutures
//  with OptionValues
//  with BeforeAndAfterAll
//  with CellularNetworkDbProvider