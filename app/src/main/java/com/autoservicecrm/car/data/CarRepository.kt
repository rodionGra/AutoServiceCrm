package com.autoservicecrm.car.data

import com.autoservicecrm.car.data.model.Car
import com.autoservicecrm.car.data.model.PostCarDto
import com.autoservicecrm.shared.data.ApiRoutes
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.Url
import javax.inject.Inject

class CarRepository @Inject constructor(
    private val client: HttpClient
) {

    suspend fun getCars(): List<Car>? {
        /*return listOf(
            Car(
                1,
                "Honda",
                "Accord",
                Customer(1, "Rodion", "Gramushniak", "Olexsandrovich", "+38093135013")
            ),
            Car(
                1,
                "Honda",
                "Accord",
                Customer(1, "Rodion", "Gramushniak", "Olexsandrovich", "+38093135013")
            ),
            Car(
                1,
                "Honda",
                "Accord",
                Customer(1, "Rodion", "Gramushniak", "Olexsandrovich", "+38093135013")
            ),
            Car(
                1,
                "Honda",
                "Accord",
                Customer(1, "Rodion", "Gramushniak", "Olexsandrovich", "+38093135013")
            ),
            Car(
                1,
                "Honda",
                "Accord",
                Customer(1, "Rodion", "Gramushniak", "Olexsandrovich", "+38093135013")
            ),
            Car(
                1,
                "Honda",
                "Accord",
                Customer(1, "Rodion", "Gramushniak", "Olexsandrovich", "+38093135013")
            ),
            Car(
                1,
                "Honda",
                "Accord",
                Customer(1, "Rodion", "Gramushniak", "Olexsandrovich", "+38093135013")
            ),
            Car(
                1,
                "Honda",
                "Accord",
                Customer(1, "Rodion", "Gramushniak", "Olexsandrovich", "+38093135013")
            ),
            Car(
                1,
                "Honda",
                "Accord",
                Customer(1, "Rodion", "Gramushniak", "Olexsandrovich", "+38093135013")
            ),
        )*/
        return try {
            client.get<List<Car>>(url = Url(ApiRoutes.CARS))
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: ServerResponseException) {
            // 5xx - response
            println("Error: ${ex.response.status.description}")
            null
        } catch (_: Exception) {
            null
        }
    }

    suspend fun postNewCar(postCarDto: PostCarDto): Unit? {
        return try {
            client.post(url = Url(ApiRoutes.POST_CAR)) {
                this.body = postCarDto
            }
        } catch (_: Exception) {
            null
        }
    }
}
