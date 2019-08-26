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
        presences = gen[ShortString].value, //Presences
        male = gen[ShortString].value, //Male
        female = gen[ShortString].value, //Female
        age_under_18 = gen[ShortString].value, // Age range < 18
        age_between_18_30 = gen[ShortString].value, // Age range [18, 30]
        age_between_31_40 = gen[ShortString].value, // Age range [18, 30]
        age_between_41_50 = gen[ShortString].value, // Age range [41, 50]
        age_between_51_60 = gen[ShortString].value, // Age range [51, 60]
        age_older_60 = gen[ShortString].value, // Age range > 60
        italian = gen[ShortString].value, // Italian
        foreign = gen[ShortString].value, // Foreign
        business = gen[ShortString].value, // Business
        consumer = gen[ShortString].value //Consumer
      )
    }
  }
}
