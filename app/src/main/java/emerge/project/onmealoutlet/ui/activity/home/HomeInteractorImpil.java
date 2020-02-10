package emerge.project.onmealoutlet.ui.activity.home;


import android.os.Bundle;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.servies.api.ApiClient;
import emerge.project.onmealoutlet.servies.api.ApiInterface;
import emerge.project.onmealoutlet.ui.activity.home.HomeInteractor;
import emerge.project.onmealoutlet.utils.entittes.DeliveryRiders;
import emerge.project.onmealoutlet.utils.entittes.Foods;
import emerge.project.onmealoutlet.utils.entittes.Menus;
import emerge.project.onmealoutlet.utils.entittes.OrderDetail;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.SliderMenuItems;
import emerge.project.onmealoutlet.utils.entittes.TimeSlots;
import emerge.project.onmealoutlet.utils.entittes.User;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrderMenus;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrdersData;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Himanshu on 4/5/2017.
 */

public class HomeInteractorImpil implements HomeInteractor {

    Realm realm = Realm.getDefaultInstance();
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


    JSONObject ordersFullDetailsJSONObject = null;
    JSONObject printOrdersJSONObject = null;
    Boolean updateOrderStatus;

    List<TimeSlots> timeSlotsList;
    JSONObject salesDetails = null;


    List<Orders> data;

    //   ArrayList<Orders> ordersArrayList = new ArrayList<Orders>();

    OrdersData ordersArrayList = new OrdersData();


    Outlet outlet = realm.where(Outlet.class).findFirst();

    @Override
    public void setAllNavigationItem(ArrayList<SliderMenuItems> sliderMenuItems, OnHomeNavigationItemFinishedListener onHomeNavigationItemFinishedListener) {

        if (sliderMenuItems.isEmpty()) {
            sliderMenuItems.add(new SliderMenuItems("About", R.drawable.ic_sidebar_settings));
            sliderMenuItems.add(new SliderMenuItems("Help", R.drawable.ic_sidebar_help));
            sliderMenuItems.add(new SliderMenuItems("Menu", R.drawable.ic_cake_black));
            sliderMenuItems.add(new SliderMenuItems("Settings", R.drawable.ic_build_black_24dp));
            sliderMenuItems.add(new SliderMenuItems("Menu History", R.drawable.ic_sidebar_help));
            sliderMenuItems.add(new SliderMenuItems("Order History", R.drawable.ic_sidebar_help));
            sliderMenuItems.add(new SliderMenuItems("Logout", R.drawable.ic_power_settings_new_black_24dp));

        } else {

        }

        onHomeNavigationItemFinishedListener.onsetAllNavigationItems();
    }

    @Override
    public void getDeliveryTimeSlots(String status, final OngetDeliveryTimeSlotsFinishedListener ongetDeliveryTimeSlotsFinishedListener) {

        final ArrayList<TimeSlots> timeSlots = new ArrayList<TimeSlots>();


        apiService.getOrderFilterationDataByTimeSlot(outlet.getOutletId(), status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<TimeSlots>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TimeSlots> timeSlots) {
                        timeSlotsList = timeSlots;

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        timeSlots.add(new TimeSlots(0, "ALL"));
                        for (int i = 0; i < timeSlotsList.size(); i++) {
                            timeSlots.add(new TimeSlots(timeSlotsList.get(i).getSlotID(), timeSlotsList.get(i).getSlotsName()));
                        }
                        ongetDeliveryTimeSlotsFinishedListener.timeSlots(timeSlots);
                    }
                });


    }

    @Override
    public void getDeliveryRiders(String status, final OngetDeliveryRidersFinishedListener ongetDeliveryRidersFinishedListener) {

        final ArrayList<DeliveryRiders> deliveryRiders = new ArrayList<DeliveryRiders>();


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DeliveryRiders>> call = apiService.getOrderFilterationDataByRider(outlet.getOutletId(), status);
        call.enqueue(new Callback<List<DeliveryRiders>>() {
            @Override
            public void onResponse(Call<List<DeliveryRiders>> call, Response<List<DeliveryRiders>> response) {
                List<DeliveryRiders> deliveryRidersList = response.body();

                deliveryRiders.add(new DeliveryRiders(0, "ALL"));

                for (int i = 0; i < deliveryRidersList.size(); i++) {
                    deliveryRiders.add(new DeliveryRiders(deliveryRidersList.get(i).getRiderID(), deliveryRidersList.get(i).getRiderName()));
                }

                ongetDeliveryRidersFinishedListener.ridersList(deliveryRiders);
            }

            @Override
            public void onFailure(Call<List<DeliveryRiders>> call, Throwable t) {
                call.clone().enqueue(this);
            }
        });

    }

    @Override
    public void logOut(OnLogOutFinishedListener onLogOutFinishedListener) {

        //  Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Outlet> cartDetailTemp = realm.where(Outlet.class).findAll();
                cartDetailTemp.deleteAllFromRealm();
            }
        });

        onLogOutFinishedListener.logoutSuccess();
    }

    @Override
    public void getOrdersFullDetails(ArrayList<OrderMenus> ordermenus, final OnGetOrdersFullDetailsFinishedListener onGetOrdersFullDetailsFinishedListener) {
        onGetOrdersFullDetailsFinishedListener.ordersFullDetails(ordermenus);

    }



/*
    @Override
    public void getOrdersFullDetails(final int orderId, final OnGetOrdersFullDetailsFinishedListener onGetOrdersFullDetailsFinishedListener) {

        onGetOrdersFullDetailsFinishedListener.ordersFullDetailsFeedStart();

        final ArrayList<Menus> menusArrayList = new ArrayList<Menus>();//main

        apiService.orderHistorDetails(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        try {
                            ordersFullDetailsJSONObject = new JSONObject(String.valueOf(jsonObject));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            onGetOrdersFullDetailsFinishedListener.noOrdersFullDetailsAvailable();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        onGetOrdersFullDetailsFinishedListener.ordersFullDetailsTimeOut(orderId);
                    }

                    @Override
                    public void onComplete() {
                        JSONArray orderMenusList;
                        JSONArray orderMenusDetailsList;
                        try {
                            orderMenusList = ordersFullDetailsJSONObject.getJSONArray("orderMenus");
                            orderMenusDetailsList = ordersFullDetailsJSONObject.getJSONArray("orderMenuDetails");

                            for (int i = 0; i < orderMenusList.length(); i++) {
                                JSONObject jsonData = orderMenusList.getJSONObject(i);
                                final ArrayList<Foods> foodsArrayList = new ArrayList<Foods>();//sub
                                for (int k = 0; k < orderMenusDetailsList.length(); k++) {
                                    JSONObject jsonDatafoodsyList = orderMenusDetailsList.getJSONObject(k);
                                    if (jsonDatafoodsyList.getString("cartID").equals(jsonData.getString("cartID"))) {
                                        foodsArrayList.add(new Foods(jsonDatafoodsyList.getString("id"), jsonDatafoodsyList.getString("name"), jsonDatafoodsyList.getInt("foodQty"),
                                                jsonDatafoodsyList.getBoolean("isBase"), jsonDatafoodsyList.getString("foodItemCategory"), jsonDatafoodsyList.getString("foodItemTypeCode")));
                                    } else {

                                    }

                                }
                                menusArrayList.add(new Menus(jsonData.getInt("orderID"), jsonData.getString("id"), jsonData.getString("name"),
                                        jsonData.getString("menuSizeCode"), jsonData.getDouble("menuPrice"), jsonData.getInt("menuQty"), foodsArrayList));

                            }
                            onGetOrdersFullDetailsFinishedListener.ordersFullDetails(menusArrayList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            onGetOrdersFullDetailsFinishedListener.noOrdersFullDetailsAvailable();
                        }

                    }
                });


    }
*/

    @Override
    public void updateOrderStatus(final int orderId, final String statusCode, final String dispatchType, final OnUpdateOrderStatusFinishedListener onUpdateOrderStatusFinishedListener) {


        int userID = 0;
        try {
            Outlet outlet = realm.where(Outlet.class).findFirst();
            userID = outlet.getUserID();
        } catch (Exception ex) {

        }


        onUpdateOrderStatusFinishedListener.updateOrderStatusStart();
        apiService.updateOrderStatus(orderId, statusCode, userID, "M", String.valueOf(orderId) + "-" + statusCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean status) {
                        updateOrderStatus = status;

                    }

                    @Override
                    public void onError(Throwable e) {
                        onUpdateOrderStatusFinishedListener.updateOrderStatusFail(orderId, statusCode, "Communication error, Please try again", dispatchType);
                    }

                    @Override
                    public void onComplete() {
                        int orderCurrentStatus = 0;

                        String status = "";

                        if (statusCode.equals("ODPN")) {
                            orderCurrentStatus = 0;
                            status = "Pending";
                        } else if (statusCode.equals("ODPR")) {
                            orderCurrentStatus = 1;
                            status = "Process";
                        } else if (statusCode.equals("ODPK")) {
                            orderCurrentStatus = 2;
                            status = "Packed";
                        } else if (statusCode.equals("ODDS")) {
                            orderCurrentStatus = 3;
                            status = "Dispatch";
                        }

                        if (updateOrderStatus) {
                            onUpdateOrderStatusFinishedListener.updateOrderStatusSuccessful(orderCurrentStatus, orderId, dispatchType);
                        } else {
                            onUpdateOrderStatusFinishedListener.updateOrderStatusFail(orderId, statusCode, "This Order already change to " + status, dispatchType);
                        }

                    }
                });


    }


    @Override
    public void printOrders(final int orderId, final int printType, final OnPrintOrdersFinishedListener onPrintOrdersFinishedListener) {

        onPrintOrdersFinishedListener.printOrdersStart();

        final ArrayList<Menus> menusArrayList = new ArrayList<Menus>();//main
        final ArrayList<Foods> foodsArrayList = new ArrayList<Foods>();//sub
        final ArrayList<User> userList = new ArrayList<User>();//sub

/*
        apiService.orderHistorDetails(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        try {
                            printOrdersJSONObject = new JSONObject(String.valueOf(jsonObject));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        User user = new User();
                        OrderDetail orderDetail = new OrderDetail();
                        try {

                            JSONArray orderMenusList;
                            JSONArray orderMenusDetailsList;

                            JSONObject userListOject;
                            JSONObject deliveryListOject;

                            orderMenusList = printOrdersJSONObject.getJSONArray("orderMenus");
                            orderMenusDetailsList = printOrdersJSONObject.getJSONArray("orderMenuDetails");
                            userListOject = printOrdersJSONObject.getJSONObject("mealTimeUser");


                            if (printOrdersJSONObject.getString("dispatchType").equals("P")) {
                                orderDetail.setTime(printOrdersJSONObject.getString("pickUpTime"));
                            } else {
                                deliveryListOject = printOrdersJSONObject.getJSONObject("deliveryTime");
                                orderDetail.setTime(deliveryListOject.getString("timeSlot"));
                            }


                            orderDetail.setOrderId(orderId);
                            orderDetail.setQty(printOrdersJSONObject.getInt("orderQty"));
                            orderDetail.setTotalprice(printOrdersJSONObject.getDouble("orderTotal"));
                            orderDetail.setSubTotal(printOrdersJSONObject.getDouble("subTotal"));
                            orderDetail.setDilivery(printOrdersJSONObject.getDouble("deliveryCost"));
                            orderDetail.setRiderID(0);
                            orderDetail.setRiderName("Test");
                            orderDetail.setDate(printOrdersJSONObject.getString("orderDate"));
                            orderDetail.setDispatchType(printOrdersJSONObject.getString("dispatchType"));
                            orderDetail.setPaymentTypeCode(printOrdersJSONObject.getString("paymentTypeCode"));


                            user.setAddressNo(userListOject.getString("addressNo"));
                            user.setAddressRoad(userListOject.getString("addressRoad"));
                            user.setDepartmentName(userListOject.getString("departmentName"));
                            user.setAddress(userListOject.getString("address"));
                            user.setCity(userListOject.getString("city"));
                            user.setMobileNo(userListOject.getString("mobileNo"));
                            user.setName(userListOject.getString("name"));
                            for (int i = 0; i < orderMenusDetailsList.length(); i++) {
                                JSONObject jsonData = orderMenusDetailsList.getJSONObject(i);
                                foodsArrayList.add(new Foods(jsonData.getString("id"),
                                        jsonData.getString("name"),
                                        jsonData.getInt("foodQty"),
                                        jsonData.getBoolean("isBase"),
                                        jsonData.getString("foodItemCategory"),
                                        jsonData.getString("foodItemTypeCode")));

                            }


                            for (int i = 0; i < orderMenusList.length(); i++) {
                                JSONObject jsonData = orderMenusList.getJSONObject(i);
                                menusArrayList.add(new Menus(jsonData.getInt("orderID"), jsonData.getString("id"), jsonData.getString("name"),
                                        jsonData.getString("menuSizeCode"), jsonData.getDouble("menuPrice"), jsonData.getInt("menuQty"), foodsArrayList));

                            }

                            print(orderDetail, user, menusArrayList, foodsArrayList, printType, onPrintOrdersFinishedListener);


                        } catch (JSONException e) {
                            onPrintOrdersFinishedListener.printOrdersFail(e.toString());
                        }

                    }
                });
*/


    }

    @Override
    public void getOrders(String statusCode, int timeSlotId, int riderId, String dispatcType, final OnGetOrdersFinishedListener onGetOrdersFinishedListener) {

/*
        apiService.getOrdersForOutlet(outlet.getOutletId(), statusCode, dispatcType, timeSlotId, riderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Orders>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<Orders> ordersList) {
                        ordersArrayList = ordersList;

                    }

                    @Override
                    public void onError(Throwable e) {
                        onGetOrdersFinishedListener.ordersTimeOut();
                    }

                    @Override
                    public void onComplete() {
                        if (ordersArrayList.isEmpty()) {
                            onGetOrdersFinishedListener.noOrdersList();
                        } else {
                            onGetOrdersFinishedListener.ordersList(ordersArrayList);
                        }
                    }
                });
*/

        try {
            apiService.getOrdersForOutlet(outlet.getOutletId(), statusCode, dispatcType, timeSlotId, riderId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<OrdersData>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(OrdersData ordersList) {
                            ordersArrayList = ordersList;

                        }

                        @Override
                        public void onError(Throwable e) {
                            onGetOrdersFinishedListener.ordersTimeOut();
                        }

                        @Override
                        public void onComplete() {
                            onGetOrdersFinishedListener.ordersList(ordersArrayList);
                        }
                    });

        } catch (Exception ex) {
            onGetOrdersFinishedListener.ordersTimeOut();
        }



    }


    @Override
    public void getIncome(final OnGetIncomeFinishedListener onGetIncomeFinishedListener) {


        apiService.getOutletDailySale(outlet.getOutletId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        try {
                            salesDetails = new JSONObject(String.valueOf(jsonObject));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        try {
                            onGetIncomeFinishedListener.income(String.valueOf(salesDetails.getDouble("orderTotal")), String.valueOf(salesDetails.getInt("orderQty")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }

    @Override
    public void getOutletName(OnGetOutletNameFinishedListener onGetOutletNameFinishedListener) {
        String outletName = "";
        outletName = outlet.getOutletname();
        onGetOutletNameFinishedListener.outlatname(outletName);
    }


    private void print(OrderDetail orderDetail, User user, ArrayList<Menus> menusArrayList, ArrayList<Foods> foodsArrayList, int printType, OnPrintOrdersFinishedListener onPrintOrdersFinishedListener) {
        Bundle bundleToView = new Bundle();


        bundleToView.putInt("PRINTTYPE", printType);
        bundleToView.putParcelable("ORDERDETAIL", orderDetail);
        bundleToView.putParcelable("USER", user);
        bundleToView.putSerializable("MENUSARRAYLIST", menusArrayList);
        bundleToView.putSerializable("FOODSARRAYLIST", foodsArrayList);
        onPrintOrdersFinishedListener.printOrdersSuccessful(bundleToView);

    }


}
