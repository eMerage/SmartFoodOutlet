package emerge.project.onmealoutlet.servies.api;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;


import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.utils.entittes.DeliveryRiders;
import emerge.project.onmealoutlet.utils.entittes.MenuCategoryItems;
import emerge.project.onmealoutlet.utils.entittes.MenuHistoryEntittes;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;
import emerge.project.onmealoutlet.utils.entittes.OrderHistoryEntitte;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.OutletSales;
import emerge.project.onmealoutlet.utils.entittes.TimeSlots;
import emerge.project.onmealoutlet.utils.entittes.User;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by Himanshu on 8/24/2017.
 */

public interface ApiInterface {


    @GET("MealTimeUser/ValidateUser")
    Observable<Outlet> userLoginValidation(@Query("eMail") String email, @Query("Password") String password);

    @GET("MealTimeUser/GetUserByEmail")
    Observable<User> getUserByEmail(@Query("email") String email, @Query("pushTokenId") String pushTokenId);


    @GET("Order/GetOrdersForOutlet")
    Observable<List<Orders>> getOrdersForOutlet(@Query("outletID") int outletID, @Query("orderStatusCode") String orderStatusCode, @Query("dispatchType") String dispatchType, @Query("timeSlotID") int timeSlotID, @Query("riderID") int riderID);


    @GET("Order/GetOrderHistoryByOrder")
    Observable<JsonObject> orderHistorDetails(@Query("OrderID") int orderID);



    @POST("Order/UpdateOrderStatus")
    Observable<Boolean> updateOrderStatus(@Query("OrderID") int orderID, @Query("statusCode") String statusCode, @Query("userID") int userID, @Query("userTypeCode") String userTypeCode, @Query("note") String note);



    @GET("Order/GetOrderFilterationDataByTimeSlot")
    Observable<List<TimeSlots>> getOrderFilterationDataByTimeSlot(@Query("outletID") int outletID, @Query("orderStatusCode") String statusCode);


    @GET("Order/GetOrderFilterationDataByRider")
    Call<List<DeliveryRiders>> getOrderFilterationDataByRider(@Query("outletID") int outletID, @Query("orderStatusCode") String statusCode);


    @GET("Outlet/GetOutletDailySale")
    Observable<JsonObject> getOutletDailySale(@Query("outletID") int orderID);


    @GET("Menu/GetMenuCategoriesForOutlet")
    Observable<List<MenuCategoryItems>> getMenuCategoriesForOutlet(@Query("outletID") int outletID);


    @GET("Menu/GetMenusForOutlet")
    Observable<List<MenuItems>> getMainFoodByOutlet(@Query("outletID") int outletId, @Query("menuCategoryID") int menuCategoryID);



    @POST("Menu/UpdateAvailableQty")
    Observable<Integer> updateAvailableQty(@Query("outletMenuTitleID") int outletMenuTitleID, @Query("availableQty") int availableQty);

    @GET("Outlet/GetOutlet")
    Observable<Outlet> getOutletDetails(@Query("outletID") int outletId);


    @POST("Outlet/UpdateOutletPickupInterval")
    Observable<Integer> updateOutletPickupInterval(@Query("outletID") int outletId,@Query("Interval") int interval);



    @GET("Order/GetOrdersForOutletBetweenDates")
    Observable<ArrayList<OrderHistoryEntitte>> getOrdersForOutletBetweenDates(@Query("outletID") int outletID,
                                                                              @Query("orderStatusCode") String orderStatusCode, @Query("startDate") String startDate,
                                                                              @Query("endDate") String endDate, @Query("dispatchType") String dispatchType);



    @GET("outlet/GetTotalOutletSale")
    Observable<OutletSales> GetTotalOutletSale(@Query("outletID") int outletID, @Query("startDate") String startDate, @Query("endDate") String endDate);


    @GET("Menu/GetMenuSaleForOutlet")
    Observable<ArrayList<MenuHistoryEntittes>> getMenuSaleForOutlet(@Query("outletID") int outletID, @Query("startDate") String startDate, @Query("endDate") String endDate, @Query("menuCategoryID") String menuCategoryID);







}
