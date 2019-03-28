package com.maiajam.sofa.data.restApi.retrofit;

import android.app.Notification;

import com.maiajam.sofa.data.models.MyOffers;
import com.maiajam.sofa.data.models.RestData.RestDetials;
import com.maiajam.sofa.data.models.RestFoodItems.RestFoodItems;
import com.maiajam.sofa.data.models.addTocart.AddToCart;
import com.maiajam.sofa.data.models.catagory.CatagoryList;
import com.maiajam.sofa.data.models.cityList.CityList;
import com.maiajam.sofa.data.models.commission.Commission;
import com.maiajam.sofa.data.models.confirmOrder.ConfirmOrder;
import com.maiajam.sofa.data.models.declineOrder.DeclineOrder;
import com.maiajam.sofa.data.models.login.Login;
import com.maiajam.sofa.data.models.myOrders.MyOrders;
import com.maiajam.sofa.data.models.notifcation.NotifcationList;
import com.maiajam.sofa.data.models.register.Register;
import com.maiajam.sofa.data.models.registerRestOwner.RegisterRestOwner;
import com.maiajam.sofa.data.models.restList.RestList;
import com.maiajam.sofa.data.models.restReview.RestReviwes;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServeces {

    @GET("restaurants")
    Call<RestList> getRestList(@Query("page") int page);

    @GET("restaurants")
    Call<RestList> getRestListByRegion(@Query("region") int region);


    @GET("cities")
    Call<CityList> getCitytList();

    @GET("regions")
    Call<com.maiajam.sofa.data.models.region.Region> getRegionList(@Query("city_id") int id);


    @GET("restaurant")
    Call<RestDetials> getRestDeta(@Query("restaurant_id") int id);



    @GET("items")
    Call<RestFoodItems> getRestitems(@Query("restaurant_id") int id);

    @GET("categories")
    Call<CatagoryList> getCatagoriList();


    @GET("restaurant_id_review")
    Call<RestReviwes> getRestReviews(@Query("restaurant_id") int id,
                                     @Query("api_token") String api_token,
                                     @Query("page") int page);

    @GET("offers")
    Call<com.maiajam.sofa.data.models.Offers.Data> ListOffOffers();


    @GET("client/my-offers")
    Call<MyOffers> getMyOffers(@Query("api_token") String api_token,
                               @Query("state") String state,
                               @Query("page") int page);



    @GET("client/my-orders")
    Call<MyOrders> getMyOrders(@Query("api_token") String api_token,
                               @Query("state") String state,
                               @Query("page") int page);

    @GET("restaurant/commissions")
    Call<Commission> getCommssion(@Query("api_token") String api_token);

    @GET("client/notifications")
    Call<NotifcationList> getNotifcationList(@Query("api_token") String api_token);

    @POST("client/login")
    @FormUrlEncoded
    Call<Login> Login(@Field("email") String email ,
                      @Field("password") String password);

    @POST("client/register")
    @FormUrlEncoded
    Call<Register> Register(@Field("name") String name ,
                            @Field("email") String email ,
                            @Field("password") String password ,
                            @Field("passwordCon") String passwordCon,
                            @Field("phone") String phone ,
                            @Field("address") String address ,
                            @Field("region_id") int region_id);

    @POST("restaurant/register")
    Call<RegisterRestOwner> RegisterRestOwner(@Body String name , @Body String email ,
                                              @Body String password , @Body String passwordCon, @Body String phone , @Body String whatsNo,
                                              @Body int region_id,@Body ArrayList<String> categories[],@Body String delivery_cost,
                                              @Body String minimum_charger,@Body String photo,@Body String availability);



    @POST("client/add-item-to-cart")
    Call<AddToCart> AddTOCart (@Query("api_token") String api_token , @Body int item_ID , @Body int Quantity , @Body String note);


    @POST("client/confirm-order")
    Call<ConfirmOrder> ConfirmOrder(@Body String api_token, @Body int id);

    @POST("client/decline-order")
    Call<DeclineOrder> DelcineOrder(@Query("api_token") String api, @Body int id);

    /*@POST("restaurant/new-offer")
    Call<> addNewOffer(@Field("name") String productName,
                       @Field("name") String productDesc,
                       @Field("name") int cost,
                       @Field("name") String timeFrom,
                       @Field("name") String timeTo);
                       */
}
