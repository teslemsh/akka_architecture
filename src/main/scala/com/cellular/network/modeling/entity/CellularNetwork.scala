package com.cellular.network.modeling.entity

import java.util.UUID

/**
  *
  * This is the Scala representation of Network, following the Datastax example
  */
case class CellularNetwork(
                            id: UUID,
                            Patata: String, //Presences
//                            Gm: String, //Male
//                            Gf: String, //Female
//                            F1: String, // Age range < 18
//                            F2: String, // Age range [18, 30]
//                            F3: String, // Age range [31, 40]
//                            F4: String, // Age range [41, 50]
//                            F5: String, // Age range [51, 60]
//                            F6: String, // Age range > 60
//                            Ni: String, // Italian
//                            Ns: String, // Foreign
//                            Tb: String, // Business
//                            Tc: String, //Consumer
                          )