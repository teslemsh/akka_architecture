package com.cellular.network.processing

import akka.actor.{ Actor, ActorLogging, ActorRef, ActorSystem, Props }
import com.outworkers.phantom.dsl._
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import java.io.{File, FileWriter, IOException, Writer}
import net.liftweb.json._
import net.liftweb.json.Serialization.write
import  org.joda.time.DateTime
import org.joda.time.{ DateTime, DateTimeZone }
import org.joda.time.format.DateTimeFormat

import com.cellular.network.modeling.model.{CellularNetworkModel}
import com.cellular.network.modeling.database.{Database, CellularNetworkDatabase}

trait CellularNetworkDbProvider extends DatabaseProvider[CellularNetworkDatabase] {
  override def database: CellularNetworkDatabase = Database
}
class CellularNetworkDbCreator extends CellularNetworkDbProvider {}

class ProcessDataActor extends Actor with ActorLogging {
  val db = new CellularNetworkDbCreator()
  def receive = {
    case "start to process data" => {
      log.info("Message received (from " + sender() + "): start to processing data")
      
      val dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(DateTimeZone.UTC)
      val start_date = dateFormatter.parseDateTime("2013-12-30 11:40:00.000")
      var end_date = dateFormatter.parseDateTime("2013-12-30 11:40:00.000")
      implicit val formats = DefaultFormats
      
      val cellularNetworkActivityByTimeInterval = Await.result(db.database.CellularNetworkModel.getCellularNetworkByTimeInterval(start_date), 10.seconds)
      
      val w: Writer = new FileWriter(start_date + "_activity_by_time_interval.json")
      val jsonString = write(cellularNetworkActivityByTimeInterval)

      try {
        w.write(jsonString)
        w.close()
        sender ! "Writed data into file: " + start_date + "_activity_by_time_interval.json"
      } catch {
        case e: IOException => e.printStackTrace()
      }
    }
  }
}