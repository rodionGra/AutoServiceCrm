package com.autoservicecrm.master.data

import com.autoservicecrm.master.data.model.Master
import com.autoservicecrm.master.data.model.PostMasterDto
import com.autoservicecrm.shared.data.ApiRoutes
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.Url
import javax.inject.Inject


class MasterRepository @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getMasters(): List<Master>? {
        return try {
            client.get<List<Master>>(url = Url(ApiRoutes.MASTERS))
        } catch (_: Exception) {
            null
        }
    }

    suspend fun postMaster(postMasterDto: PostMasterDto): Unit? {
        return try {
            client.post(url = Url(ApiRoutes.POST_MASTER)) {
                this.body = postMasterDto
            }
        } catch (_: Exception) {
            null
        }
    }
}
