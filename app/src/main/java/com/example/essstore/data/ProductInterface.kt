package com.example.essstore.data

import com.example.essstore.common.Common.ALL_PRODUCTS
import com.example.essstore.common.Common.CHECKOUT
import com.example.essstore.common.Common.GET_PRODUCT
import com.example.essstore.common.Common.GET_PROMOTIONS
import com.example.essstore.common.Common.GET_USER
import com.example.essstore.common.Common.GET_WISH_LIST
import com.example.essstore.common.Common.HOT_PRODUCTS
import com.example.essstore.common.Common.LOGIN_USER
import com.example.essstore.common.Common.MAKE_REQUEST
import com.example.essstore.common.Common.NEW_ARRIVALS
import com.example.essstore.common.Common.ORDERS
import com.example.essstore.common.Common.REGISTER_USER
import com.example.essstore.common.Common.UPDATE_USER
import com.example.essstore.userInfo.userLoginResponse
import retrofit2.Response
import retrofit2.http.*

interface ProductInterface {
    @GET(HOT_PRODUCTS)
    suspend fun getHotProducts(@Query("key") key: String): Response<List<product>>

    @GET(GET_PROMOTIONS)
    suspend fun getPromotions(@Query("key") key: String): Response<List<product>>

    @GET(NEW_ARRIVALS)
    suspend fun getNewArrivals(@Query("key") key: String): Response<List<product>>

    @GET(ALL_PRODUCTS)
    suspend fun getAllProducts(@Query("key") key: String): Response<List<product>>

    @GET(GET_WISH_LIST)
    suspend fun getWishList(): Response<List<wishListResponse>>

    @GET(GET_USER)
    suspend fun getUser(@Query("key") key: String): Response<user>

    @GET(GET_PRODUCT)
    suspend fun getProduct(): Response<product>

    @GET(ORDERS)
    suspend fun getOrders(): Response<List<orderHistoryResponse>>

    @GET(GET_USER)
    suspend fun getUserInfo(): Response<userPersonalInfo>

    @PUT(UPDATE_USER)
    suspend fun updateUserInfo(@Body body: updatedUserData): Response<updatedUserData>

    @POST(REGISTER_USER)
    suspend fun registerUser(@Body body: registerUser): Response<userLoginResponse>

    @POST(LOGIN_USER)
    suspend fun loginRegisteredUser(@Body body: loginUser): Response<userLoginResponse>

    @POST(CHECKOUT)
    suspend fun checkout(@Body order: orderCheckout, @Header("Authorization") auth: String): Response<orderCheckout>

    @POST(MAKE_REQUEST)
    suspend fun makeRequest(@Body request: makeRequest, @Header("Authorization") auth: String): Response<makeRequest>
}

//@Query("key") key: String