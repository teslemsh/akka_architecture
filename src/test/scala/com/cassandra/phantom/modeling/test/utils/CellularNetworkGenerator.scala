package com.cassandra.phantom.modeling.test.utils

import com.cellular.network.modeling.entity.CellularNetwork
import com.datastax.driver.core.utils.UUIDs
import com.outworkers.util.samplers.Sample
import com.outworkers.util.testing.{ShortString, gen}

trait CellularNetworkGenerator {
  implicit object SongGenerator extends Sample[CellularNetwork] {
    override def sample: CellularNetwork  = {
      CellularNetwork(
        id = UUIDs.timeBased(),
        Patata = gen[ShortString].value, //Presences
//        Gm = gen[ShortString].value, //Male
//        Gf = gen[ShortString].value, //Female
//        F1 = gen[ShortString].value, // Age range < 18
//        F2 = gen[ShortString].value, // Age range [18, 30]
//        F3 = gen[ShortString].value, // Age range [31, 40]
//        F4 = gen[ShortString].value, // Age range [41, 50]
//        F5 = gen[ShortString].value, // Age range [51, 60]
//        F6 = gen[ShortString].value, // Age range > 60
//        Ni = gen[ShortString].value, // Italian
//        Ns = gen[ShortString].value, // Foreign
//        Tb = gen[ShortString].value, // Business
//        Tc = gen[ShortString].value //Consumer
      )
    }
  }
}
