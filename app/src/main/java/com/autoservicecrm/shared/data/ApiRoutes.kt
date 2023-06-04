package com.autoservicecrm.shared.data

object ApiRoutes {
    private const val BASE_URL = "https://32b5-188-231-190-148.ngrok-free.app/api"

    const val CARS = "$BASE_URL/car/getAll"
    const val POST_CAR = "$BASE_URL/car"

    const val MASTERS = "$BASE_URL/master/getAll"
    const val POST_MASTER = "$BASE_URL/master"

    const val ORDERS = "$BASE_URL/order/getAll"
    const val POST_ORDER = "$BASE_URL/order"
    const val DELETE_ORDER = "$BASE_URL/order/"

    const val CUSTOMERS = "$BASE_URL/customer/getAll"
    const val POST_CUSTOMER = "$BASE_URL/customer"
}
