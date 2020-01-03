package emerge.project.onmealoutlet.utils.entittes.v2.Orders;



import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryOrders;

public class OrdersStatus {


    @SerializedName("totalQty")
    int totalQty;


    @SerializedName("totalValue")
    Double totalValue;



    @SerializedName("order")
    ArrayList<OrderHistoryOrders> OrderHistoryOrders = new ArrayList<OrderHistoryOrders>();




}
