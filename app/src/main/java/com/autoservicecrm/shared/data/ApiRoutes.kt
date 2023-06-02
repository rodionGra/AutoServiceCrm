package com.autoservicecrm.shared.data

object ApiRoutes {
    private const val BASE_URL = "https://a78b-188-231-190-148.ngrok-free.app/api"

    const val CARS = "$BASE_URL/car/getAll"
    const val POST_CAR = "$BASE_URL/car"

    const val MASTERS = "$BASE_URL/master/getAll"
    const val POST_MASTER = "$BASE_URL/master"
}
