package com.autoservicecrm.order.data

import com.autoservicecrm.order.data.model.Order
import com.autoservicecrm.order.data.model.PostOrderDto
import com.autoservicecrm.shared.data.ApiRoutes
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.Url
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val client: HttpClient
) {

    suspend fun getAllOrders(): List<Order>? {
        return try {
            client.get(url = Url(ApiRoutes.ORDERS))
        } catch (e: Exception) {
            null
        }
    }

    suspend fun postNewOrder(postOrderDto: PostOrderDto): Unit? {
        return try {
            client.post(url = Url(ApiRoutes.POST_ORDER)) {
                this.body = postOrderDto
            }
        } catch (_: Exception) {
            null
        }
    }
}
