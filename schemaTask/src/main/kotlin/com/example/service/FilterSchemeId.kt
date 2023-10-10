package com.example.service

import com.example.client.getDataByID
import com.example.model.*
import com.example.utils.BaseResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FilterSchemeId{
    suspend fun filter(request: FilterRequest): BaseResponse<Response> {

        val result = getDataByID(request.request.schemeID)

        if (result is BaseResponse.SuccessResponse) {

                val presentDate = LocalDate.now()
                val filter = request.request.filter
                val date = result.data?.data
                val list = arrayListOf<Data>()

                val starts = if (filter.endsWith("M")) {
                    presentDate.minusMonths(filter.removeSuffix("M").toLong())
                } else if (filter.endsWith("W")) {
                    presentDate.minusWeeks(filter.removeSuffix("W").toLong())
                } else if (filter.endsWith("Y")) {
                    presentDate.minusYears(filter.removeSuffix("Y").toLong())
                } else {
                    return BaseResponse.ErrorResponse(message = "failed to filter Enter proper details")
                }

            date?.forEach {
                val d = LocalDate.parse(it.date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                if (d.isAfter(starts) && d.isBefore(presentDate) ) {
                    list.add(it)
                }
            }
                result.data?.data  = list
            return result

        }
        else{
            return result
        }

    }
}