//#full-example
package com.cellular.network.processing

import akka.actor.{ Actor, ActorLogging, ActorRef, ActorSystem, Props }
import com.cellular.network.modeling.model.{CellularNetworkModel}
import com.cellular.network.modeling.database.{Database, CellularNetworkDatabase}
import com.outworkers.phantom.dsl._
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._


trait CellularNetworkDbProvider extends DatabaseProvider[CellularNetworkDatabase] {
  override def database: CellularNetworkDatabase = Database
}
class CellularNetworkDbCreator extends CellularNetworkDbProvider {}

class HelloActor extends Actor with ActorLogging {
  val db = new CellularNetworkDbCreator()
  def receive = {
    case "hello" => {
      var carlota = Await.result(db.database.CellularNetworkModel.getCellularNetworkById(5910, 34), 10.seconds)
      log.info("Greeting received (from " + sender() + "):" + carlota)
    }
    case _       => log.info("Greeting received (from " + sender() + "):" + "patata")
  }
}

object Main extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  helloActor ! "hello"
  helloActor ! "buenos dias"
  
}
