package com.codingwitharul.bookmyslot.data.repo

import com.codingwitharul.bookmyslot.data.db.DatabaseHelper
import com.codingwitharul.bookmyslot.data.networking.ApiClientHelper
import com.codingwitharul.bookmyslot.data.networking.EndPoints
import com.codingwitharul.bookmyslot.data.networking.apiEndPoint
import com.codingwitharul.bookmyslot.data.networking.models.ApiResponse.Error
import com.codingwitharul.bookmyslot.data.networking.models.ApiResponse.Success
import com.codingwitharul.bookmyslot.data.networking.models.ErrorType
import com.codingwitharul.bookmyslot.data.networking.models.getErrorData
import com.codingwitharul.bookmyslot.data.networking.safeRequest
import com.codingwitharul.bookmyslot.domain.model.DiscoverModel
import com.codingwitharul.bookmyslot.domain.repo.DiscoverRepo
import com.codingwitharul.bookmyslot.utils.toThrowable
import io.ktor.http.HttpMethod

class DiscoverRepoImpl(
    val db: DatabaseHelper, val apiClientHelper: ApiClientHelper
) : DiscoverRepo {

    override suspend fun fetchDiscoverList(refresh: Boolean): Result<DiscoverModel> {
        val resp = apiClientHelper.client.safeRequest<DiscoverModel, Unit> {
            apiEndPoint(EndPoints.Discover)
            method = HttpMethod.Get
        }
        return when (resp) {
            is Error -> {
                Result.failure("Error: ${resp.getErrorData(ErrorType.LOGIN)}".toThrowable())
            }

            is Success<DiscoverModel> -> {
                val data: DiscoverModel = resp.body
                db.saveDiscoveries(data)
                Result.success(data)
            }
        }
    }
}