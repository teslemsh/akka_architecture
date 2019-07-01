//#full-example
package com.cellular.network.processing

import akka.actor.{ Actor, ActorLogging, ActorRef, ActorSystem, Props }
import com.cellular.network.modeling.model.{CellularNetworkModel}
import com.cellular.network.modeling.database.{Database, CellularNetworkDatabase}
import com.outworkers.phantom.dsl._
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import java.io.{File, FileWriter, IOException, Writer}
import net.liftweb.json._
import net.liftweb.json.Serialization.write
import  org.joda.time.DateTime
import org.joda.time.{ DateTime, DateTimeZone }
import org.joda.time.format.DateTimeFormat

trait CellularNetworkDbProvider extends DatabaseProvider[CellularNetworkDatabase] {
  override def database: CellularNetworkDatabase = Database
}
class CellularNetworkDbCreator extends CellularNetworkDbProvider {}

class HelloActor extends Actor with ActorLogging {
  val db = new CellularNetworkDbCreator()
  def receive = {
    case "getCellularNetworkByTimeInterval" => {
      log.info("Greeting received (from " + sender() + "):")
      
      val dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(DateTimeZone.UTC)
      var start_date = dateFormatter.parseDateTime("2013-12-30 11:40:00.000")
      var end_date = dateFormatter.parseDateTime("2013-12-30 11:40:00.000")
      
      var cellularNetworkActivityByTimeInterval = Await.result(db.database.CellularNetworkModel.getCellularNetworkByTimeInterval(start_date), 10.seconds)
      
      val w: Writer = new FileWriter("patata.json")
      implicit val formats = DefaultFormats
      val jsonString = write(cellularNetworkActivityByTimeInterval)

      println(jsonString)
      try {
        w.write(jsonString)
        w.close()
      } catch {
        case e: IOException => e.printStackTrace()
      }
    }
    case _       => log.info("Greeting received (from " + sender() + "):" + "patata")
  }
}

object Main extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  helloActor ! "getCellularNetworkByTimeInterval"
  helloActor ! "buenos dias"
  
}