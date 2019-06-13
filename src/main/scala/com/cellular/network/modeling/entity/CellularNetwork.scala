package com.cellular.network.modeling.entity

import java.util.UUID

/**
  *
  * This is the Scala representation of Network, following the Datastax example
  */
case class CellularNetwork(
                            id: UUID,
                            square_id: Int,
                            time_interval: Long,
                            country_code: Int,
                            sms_in_activity: Float,
                            ms_out_activity: Float,
                            call_in_activity: Float,
                            call_out_activity: Float,
                            internet_traffic_activity: Float
                          )