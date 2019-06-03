package com.cellular.network.modeling.entity

import java.util.UUID

/**
  *
  * This is the Scala representation of Network, following the Datastax example
  */
case class CellularNetwork(
                            id: UUID,
                            presences: String, //Presences
                            male: String, //Male
                            female: String, //Female
                            age_under_18: String, // Age range < 18
                            age_between_18_30: String, // Age range [18, 30]
                            age_between_31_40: String, // Age range [18, 30]
                            age_between_41_50: String, // Age range [41, 50]
                            age_between_51_60: String, // Age range [51, 60]
                            age_older_60: String, // Age range > 60
                            italian: String, // Italian
                            foreign: String, // Foreign
                            business: String, // Business
                            consumer: String //Consumer
                          )