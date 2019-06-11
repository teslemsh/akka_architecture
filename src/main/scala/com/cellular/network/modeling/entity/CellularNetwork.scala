package com.cellular.network.modeling.entity

import java.util.UUID

/**
  *
  * This is the Scala representation of Network, following the Datastax example
  */
case class CellularNetwork(
                            id: UUID,
                            square_id: String,
                            time_interval: String,
                            country_code: String,
                            sms_in_activity: String,
                            ms_out_activity: String,
                            call_in_activity: String,
                            call_out_activity: String,
                            internet_traffic_activity: String
                          )