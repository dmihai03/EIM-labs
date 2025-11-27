package ro.pub.cs.systems.eim.calculatorwebservice

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CalculatorService {
    @GET("expr/expr_get.php")
    fun getOperation(
        @Query("operation") operation: String,
        @Query("t1") operator1: String,
        @Query("t2") operator2: String
    ): Call<String>

    @FormUrlEncoded
    @POST("expr/expr_post.php")
    fun postOperation(
        @Field("operation") operation: String,
        @Field("t1") operator1: String,
        @Field("t2") operator2: String
    ): Call<String>
}
