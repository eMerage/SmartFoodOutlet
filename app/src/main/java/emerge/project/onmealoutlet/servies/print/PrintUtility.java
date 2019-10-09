package emerge.project.onmealoutlet.servies.print;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.data.db.PrinterMac;
import emerge.project.onmealoutlet.utils.entittes.Foods;
import emerge.project.onmealoutlet.utils.entittes.Menus;
import emerge.project.onmealoutlet.utils.entittes.OrderDetail;
import emerge.project.onmealoutlet.utils.entittes.User;
import io.realm.Realm;

public class PrintUtility extends Activity {

    final String ERROR_MESSAGE = "There has been an error in printing the bill.";
    BluetoothAdapter mBTAdapter;
    BluetoothSocket mBTSocket = null;
    Dialog dialogProgress;
    String BILL, TRANS_ID;
    String PRINTER_MAC_ID;

    Realm realm;

    private static OutputStream outputStream;


    ArrayList<Menus> menusArrayList;
    ArrayList<Foods> foodsArrayList;

    User user;
    OrderDetail orderDetail;

    int printType;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    Toast.makeText(PrintUtility.this, device.getName() + " found", Toast.LENGTH_LONG).show();

                    System.out.println("***" + device.getName() + " : " + device.getAddress());

                    if (device.getAddress().equalsIgnoreCase(PRINTER_MAC_ID)) {
                        mBTAdapter.cancelDiscovery();
                        dialogProgress.dismiss();
                        Toast.makeText(PrintUtility.this, device.getName() + " Printing data", Toast.LENGTH_LONG).show();
                        printBillToDevice(PRINTER_MAC_ID);

                    }
                }
            } catch (Exception e) {
                Log.e("Class  ", "My Exe ", e);

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        try {

            realm = Realm.getDefaultInstance();
            PrinterMac printerMac = realm.where(PrinterMac.class).findFirst();
            if (printerMac == null) {
                PRINTER_MAC_ID = "";
            } else {
                PRINTER_MAC_ID = printerMac.getMacAddress();
            }


            if (PRINTER_MAC_ID != "") {
                String id = "";

                for (int i = 0; i < PRINTER_MAC_ID.length(); i++) {

                    if (i % 2 == 0 && i != 0) {
                        id = id + ":";
                    }
                    id = id + PRINTER_MAC_ID.charAt(i);

                }

                PRINTER_MAC_ID = id;

                Bundle extras = getIntent().getExtras();


                printType = extras.getInt("PRINTTYPE");

                orderDetail = extras.getParcelable("ORDERDETAIL");
                user = extras.getParcelable("USER");

                menusArrayList = (ArrayList<Menus>) extras.getSerializable("MENUSARRAYLIST");
                foodsArrayList = (ArrayList<Foods>) extras.getSerializable("FOODSARRAYLIST");


                mBTAdapter = BluetoothAdapter.getDefaultAdapter();

                dialogProgress = new Dialog(PrintUtility.this);

                System.out.println("*** MAC ID :" + PRINTER_MAC_ID);


                try {
                    if (mBTAdapter.isDiscovering())
                        mBTAdapter.cancelDiscovery();
                    else
                        mBTAdapter.startDiscovery();
                } catch (Exception e) {
                    Log.e("Class ", "My Exe ", e);
                }
                System.out.println("BT Searching status :" + mBTAdapter.isDiscovering());


                if (mBTAdapter == null) {
                    Toast.makeText(PrintUtility.this, "Device has no bluetooth capability", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    if (!mBTAdapter.isEnabled()) {
                        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(i, 0);
                    }


                    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                    registerReceiver(mReceiver, filter); // Don't forget to

                    dialogProgress.setTitle("Finding printer ");
                    dialogProgress.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        public void onDismiss(DialogInterface dialog) {
                            dialog.dismiss();
                            setResult(RESULT_CANCELED);
                            finish();
                        }
                    });
                    dialogProgress.show();

                }

            } else {

                Toast.makeText(PrintUtility.this, "No MAC address saved, Please enter your printer MAC address in preferences.", Toast.LENGTH_LONG).show();

                setResult(RESULT_CANCELED);
                finish();

            }

        } catch (Exception e) {
            Log.e("Class ", "My Exe ", e);
        }

    }

    public void printBillToDevice(final String address) {
        new Thread(new Runnable() {

            public void run() {
                runOnUiThread(new Runnable() {

                    public void run() {
                        dialogProgress.setTitle("Connecting...");
                        dialogProgress.show();
                    }

                });

                mBTAdapter.cancelDiscovery();


                try {
                    System.out.println("**************************#****connecting");
                    BluetoothDevice mdevice = mBTAdapter.getRemoteDevice(address);
                    Method m = mdevice.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
                    mBTSocket = (BluetoothSocket) m.invoke(mdevice, 1);

                    mBTSocket.connect();

                    outputStream = mBTSocket.getOutputStream();


                    if (printType == 0) {
                        billPrint();
                    } else {
                        billPrintCustomerCopy();
                    }

                    outputStream.flush();
                    if (mBTAdapter != null) {
                        mBTAdapter.cancelDiscovery();
                    }
                    mBTSocket.close();
                    setResult(RESULT_OK);

                    finish();
                } catch (Exception e) {
                    //  Toast.makeText(PrintUtility.this, "Print Error Try Again", Toast.LENGTH_LONG).show();Log.e("Class ", "My Exe ", e);
                    e.printStackTrace();
                    setResult(RESULT_CANCELED);
                    finish();

                }

                runOnUiThread(new Runnable() {

                    public void run() {
                        try {
                            dialogProgress.dismiss();
                        } catch (Exception e) {
                            Log.e("Class ", "My Exe ", e);
                        }
                    }

                });

            }

        }).start();
    }

    @Override
    protected void onDestroy() {
        Log.i("Dest ", "Checking Ddest");
        super.onDestroy();
        try {
            if (dialogProgress != null)
                dialogProgress.dismiss();
            if (mBTAdapter != null)
                mBTAdapter.cancelDiscovery();
            this.unregisterReceiver(mReceiver);
        } catch (Exception e) {
            Log.e("Class ", "My Exe ", e);
        }
    }

    @Override
    public void onBackPressed() {
        try {
            if (mBTAdapter != null)
                mBTAdapter.cancelDiscovery();
            this.unregisterReceiver(mReceiver);
        } catch (Exception e) {
            Log.e("Class ", "My Exe ", e);
        }
        setResult(RESULT_CANCELED);
        finish();
    }


    private void printCustom(String msg, int size) {

        byte[] cc = new byte[]{0x1B, 0x21, 0x03};  // 0- normal size text
        byte[] bb = new byte[]{0x1B, 0x21, 0x08};  // 1- only bold text
        byte[] bb2 = new byte[]{0x1B, 0x21, 0x20}; // 2- bold with medium text
        byte[] bb3 = new byte[]{0x1B, 0x21, 0x10}; // 3- bold with large text
        byte[] bb4 = new byte[]{0x1B, 0x21, 0x00}; // 3- bold with large text
        try {
            switch (size) {
                case 0:
                    outputStream.write(cc);
                    break;
                case 1:
                    outputStream.write(bb);
                    break;
                case 2:
                    outputStream.write(bb2);
                    break;
                case 3:
                    outputStream.write(bb3);
                    break;
                case 4:
                    outputStream.write(bb4);
                    break;
            }


            outputStream.write(msg.getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void billPrint() {
        realm = Realm.getDefaultInstance();

        Outlet outlet = realm.where(Outlet.class).findFirst();


        int outletNamelength = outlet.getOutletname().length();
        int outletNameSpace = (46 - outletNamelength) / 2;
        String outletSpace = "";

        if (outletNameSpace < 0) {

        } else {
            for (int i = 0; i < outletNameSpace; i++) {
                outletSpace = " " + outletSpace;
            }
        }

        printCustom(outletSpace + outlet.getOutletname() + "\n", 1);

        String KOTSpace = "          ";
        printCustom(KOTSpace + "KOT\n\n", 2);


        printCustom("Order #: ", 1);
        printCustom(orderDetail.getOrderId() + "", 0);


        int orderIDlength = String.valueOf(orderDetail.getOrderId()).length();
        String dateSpace = "";

        for (int i = 0; i < (46 - (orderIDlength + 26)); i++) {
            dateSpace = " " + dateSpace;
        }


        printCustom(dateSpace + " Date : ", 1);
        printCustom(orderDetail.getDate().substring(0, 10) + "\n", 0);

        printCustom("Time : ", 1);
        printCustom(orderDetail.getTime() + "", 0);


        int timelength = orderDetail.getTime().length();
        String dispatchType = orderDetail.getDispatchType();
        for (int i = 0; i < 5; i++) {
            dispatchType = dispatchType + " ";
        }
        String dispatchSpace = "";
        for (int i = 0; i < (46 - (timelength + 23)); i++) {
            dispatchSpace = " " + dispatchSpace;
        }
        printCustom(dispatchSpace + "Dispatch : ", 1);
        printCustom(dispatchType + "\n", 0);

        printCustom("Total Qty: ", 1);
        printCustom(orderDetail.getQty() + "\n\n", 0);


        for (Menus menusarraylist : menusArrayList) {

            List<String> foodCatagoryList = new ArrayList<>();

            printCustom("  Parcel #: ", 1);
            printCustom("" + menusarraylist.getCartID() + "\n", 0);

            printCustom("  Menu Name: ", 1);
            printCustom("" + menusarraylist.getOutletMenuName() + "\n", 0);


            printCustom("  Size: ", 1);
            printCustom("" + menusarraylist.getSize() + "", 0);


            printCustom("                    Qty: ", 1);
            printCustom("" + menusarraylist.getQty() + "\n\n", 0);


            printCustom("    Qty Category Item \n", 1);
            printCustom("    ------------------------------------------\n", 1);

            for (Foods foodsList : foodsArrayList) {

                int qtylength = String.valueOf(foodsList.getQty()).length();
                String qtySpace = "";

                for (int i = 0; i < 4 - qtylength; i++) {
                    qtySpace = qtySpace + " ";
                }

                printCustom("    " + foodsList.getQty() + qtySpace, 0);

                String catagory;

                if (foodsList.isBaseFood()) {
                    catagory = "BASE";
                } else if (foodsList.getFoodItemTypeCode().equals("EX")) {
                    catagory = "EXTRA";
                } else {
                    catagory = foodsList.getFoodItemCategory();

                }


                int catagorylength = catagory.length();
                String catagorySpace = "";

                for (int i = 0; i < 10 - catagorylength; i++) {
                    catagorySpace = catagorySpace + " ";
                }
                printCustom(catagory + catagorySpace, 0);
                printCustom(foodsList.getOutletMenuName() + "\n", 0);


            }

            printCustom("\n\n\n", 0);

        }


        String onmealSpace = "";
        for (int i = 0; i < 16; i++) {
            onmealSpace = " " + onmealSpace;
        }
        printCustom(onmealSpace + "www.onmeal.lk\n", 1);


        String onmealNumSpace = "";
        for (int i = 0; i < 19; i++) {
            onmealNumSpace = " " + onmealNumSpace;
        }
        printCustom(onmealNumSpace + "0115 960 960\n", 1);


        String softwareBySpace = "";
        for (int i = 0; i < 5; i++) {
            softwareBySpace = " " + softwareBySpace;
        }
        printCustom(softwareBySpace + "Software by Emerge Solutions(PVT)LTD.\n", 1);


    }


    private void billPrintCustomerCopy() {

        realm = Realm.getDefaultInstance();
        Outlet outlet = realm.where(Outlet.class).findFirst();

        int orderIDlength = String.valueOf(orderDetail.getOrderId()).length();
        int orderIDSpacelength = (46 - orderIDlength) / 2;
        String orderIDSpace = "";

        if (orderIDSpacelength < 0) {

        } else {
            for (int i = 0; i < orderIDSpacelength; i++) {
                orderIDSpace = " " + orderIDSpace;
            }
        }
        printCustom(orderIDSpace + orderDetail.getOrderId() + "\n\n", 1);


       /* String onMealSpace = "";
        for (int i = 0; i < 21; i++) {
            onMealSpace = " " + onMealSpace;
        }
        printCustom(onMealSpace + "OnMeal\n", 1);*/
        printCustom("                    OnMeal\n", 1);
        printCustom("                   INVOICE\n", 1);

      /*  String invoiceSpace = "";
        for (int i = 0; i < 20; i++) {
            invoiceSpace = " " + invoiceSpace;
        }
        printCustom(invoiceSpace + " INVOICE\n", 1);*/





        String addresslSpace = "";
        for (int i = 0; i < 9; i++) {
            addresslSpace = " " + addresslSpace;
        }
        printCustom(addresslSpace + "No.112,Narahapitha Rd,Nawala \n\n", 1);

       printCustom("Inv# :", 1);
        printCustom(String.valueOf(orderDetail.getOrderId()), 1);


        String dateSpace = "";
        for (int i = 0; i < (46 - (orderIDlength + 26)); i++) {
            dateSpace = " " + dateSpace;
        }
        printCustom(dateSpace + " Date : ", 1);
        printCustom(orderDetail.getDate().substring(0, 10) + "\n", 0);
        printCustom("Time : ", 1);
        printCustom(orderDetail.getTime() + "\n", 0);


        printCustom("----------------------------------------------\n", 0);
        printCustom("  NAME                         QTY  TOTAL\n", 1);
        printCustom("----------------------------------------------\n", 0);

        for (Menus menusarraylist : menusArrayList) {
             Double tot = menusarraylist.getQty() * menusarraylist.getPrice();


            String menuName = menusarraylist.getOutletMenuName();
            String menuNameSpace = "";
            if (menuName.length() > 31) {
                menuName = menusarraylist.getOutletMenuName().substring(0, 31);
            } else {
                for (int i = 0; i < (31 - menuName.length()); i++) {
                    menuNameSpace = " " + menuNameSpace;
                }
                menuName = menusarraylist.getOutletMenuName();
            }
            printCustom("  " + menuName + menuNameSpace + "", 0);


            int qtylenth = String.valueOf(menusarraylist.getQty()).length();
            String qtySpace = "";
            for (int i = 0; i < (5 - qtylenth); i++) {
                qtySpace = " " + qtySpace;
            }
            printCustom(menusarraylist.getQty() + qtySpace + "", 0);


            int totlenth = String.valueOf(tot).length();
            String totSpace = "";

            for (int i = 0; i < (6 - totlenth); i++) {
                totSpace = " " + totSpace;
            }
            printCustom(String.format("%.2f", tot) + totSpace + "\n", 0);
            printCustom("     Parcel #: ", 1);
            printCustom(menusarraylist.getCartID()+" ", 0);

            printCustom("Size: ", 1);
            printCustom(menusarraylist.getSize()+"\n", 0);



        }

        printCustom("----------------------------------------------\n", 0);


        String subtotSpace = "";
         int sublenth = String.valueOf(orderDetail.getSubTotal()).length();
        Double dSub = orderDetail.getSubTotal();



        for (int i = 0; i < (8 - sublenth); i++) {
            subtotSpace = subtotSpace + " ";
        }

        printCustom("Subtotal ", 0);
        // printCustom("                           " + subtotSpace + String.format("%.2f", orderDetail.getSubTotal()) + "\n", 0);
        printCustom("                           " + subtotSpace + String.format("%.2f", dSub) + "\n", 0);


        String totQtySpace = "";
        int totq = orderDetail.getQty();

        //  int totlQtyenth = String.valueOf(orderDetail.getQty()).length();
        int totlQtyenth = String.valueOf(totq).length();

        for (int i = 0; i < (9 - totlQtyenth); i++) {
            totQtySpace = totQtySpace + " ";
        }


        printCustom("Total Quantity ", 0);
        //  printCustom("                     " + totQtySpace + orderDetail.getQty() + "\n", 0);
        printCustom("                     " + totQtySpace + totq + "\n", 0);


        String deliChagSpace = "";
        Double delverC = orderDetail.getDilivery();

        //  int deliChaglenth = String.valueOf(orderDetail.getDilivery()).length();
        int deliChaglenth = String.valueOf(delverC).length();

        for (int i = 0; i < (6 - deliChaglenth); i++) {
            deliChagSpace = deliChagSpace + " ";
        }


        printCustom("Delivery Charges ", 0);
        // printCustom("                     " + deliChagSpace + String.format("%.2f", orderDetail.getDilivery()) + "\n", 0);
        printCustom("                      " + deliChagSpace + String.format("%.2f", delverC) + "\n\n", 0);


        String totalSpace = "";


        Double Tot = orderDetail.getTotalprice();

        // int totallenth = String.valueOf(orderDetail.getTotalprice()).length();
        int totallenth = String.valueOf(Tot).length();

        for (int i = 0; i < (7 - totallenth); i++) {
            totalSpace = totalSpace + " ";
        }


        printCustom("Total ", 1);
        // printCustom("                                " + totalSpace + orderDetail.getTotalprice() + "\n", 1);
        printCustom("                               " + totalSpace + Tot + "\n", 1);


        printCustom("----------------------------------------------\n", 0);


        String paymentType;


        if (orderDetail.getPaymentTypeCode().equals("CC")) {
            paymentType = "Credit Card";
        } else if (orderDetail.getPaymentTypeCode().equals("MP")) {
            paymentType = "Mobile";
        } else {
            paymentType = "Cash";
        }


        String customerAddress;

        customerAddress = user.getAddress().trim() + user.getAddressNo().trim() + user.getAddressRoad().trim() + user.getCity();


        printCustom("Payment Type :     " + paymentType.trim() + "\n", 0);
        printCustom("Customer :         " + user.getName().trim() + "\n", 0);
        printCustom("Customer Number :  " + user.getMobileNo().trim() + "\n", 0);
        printCustom("Customer Address : " + customerAddress.trim() + "\n", 0);

        printCustom("----------------------------------------------\n", 0);


        String thankyouSpace = "";
        for (int i = 0; i < 11; i++) {
            thankyouSpace = " " + thankyouSpace;
        }
        printCustom(thankyouSpace + "Thank you for your order\n", 1);


        String onmealSpace = "";
        for (int i = 0; i < 16; i++) {
            onmealSpace = " " + onmealSpace;
        }
        printCustom(onmealSpace + "www.onmeal.lk\n", 1);


        String onmealNumSpace = "";
        for (int i = 0; i < 19; i++) {
            onmealNumSpace = " " + onmealNumSpace;
        }
        printCustom(onmealNumSpace + "0115 960 960\n", 1);


        String softwareBySpace = "";
        for (int i = 0; i < 5; i++) {
            softwareBySpace = " " + softwareBySpace;
        }
        printCustom(softwareBySpace + "Software by Emerge Solutions(PVT)LTD.\n", 1);

    }


}
