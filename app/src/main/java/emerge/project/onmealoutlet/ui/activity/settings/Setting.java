package emerge.project.onmealoutlet.ui.activity.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.data.db.PrinterMac;
import emerge.project.onmealoutlet.servies.network.NetworkAvailability;
import io.realm.Realm;
import io.realm.RealmResults;

public class Setting extends Activity implements SettingsView {


    Realm realm;

    @BindView(R.id.textview_mac)
    TextView textviewMac;


    @BindView(R.id.relativelayout_setting_printer_address_adding)
    RelativeLayout relativelayoutSettingPrinterAddressAdding;

    @BindView(R.id.editText_macaddress)
    EditText editTextMacaddress;


    @BindView(R.id.textvie_current_timeinterval)
    TextView textvieCurrentTimeinterval;


    @BindView(R.id.editText_timeinterval)
    EditText editTextTimeinterval;


    SettingsPresenter settingsPresenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting);

        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();


        settingsPresenter = new SettingsPresenterImpli(this);
        settingsPresenter.getOutletDetails();


        textviewMac.setText(getMACaddress());


    }


    @OnClick(R.id.btn_update_timeinterval)
    public void onClickUpdateTime(View view) {
        if (NetworkAvailability.isNetworkAvailable(getApplicationContext())) {
            int time = 0;
            try {
                time = Integer.parseInt(editTextTimeinterval.getText().toString());
            }catch (NumberFormatException num){

            }
            settingsPresenter.updateTimeInterval(time);
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Warning");
            alertDialogBuilder.setMessage("No Internet Access, Please try again ");
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
            alertDialogBuilder.show();
        }

    }







    @OnClick(R.id.relativeLayout_slider_menu)
    public void onClickSliderMenue(View view) {

        finish();
    }

    @OnClick(R.id.relativelayout_printer_add)
    public void onClickPrinterAdd(View view) {
        relativelayoutSettingPrinterAddressAdding.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.button_printer_address_save)
    public void onClickAdressSave(View view) {


        realm.beginTransaction();
        RealmResults<PrinterMac> allTransactions = realm.where(PrinterMac.class).findAll();
        realm.commitTransaction();


        if(editTextMacaddress.getText().toString().equals("") || editTextMacaddress.getText().toString().isEmpty() ){
            Toast.makeText(this, "No Printer address added", Toast.LENGTH_SHORT).show();
            
        }else if(allTransactions.size()==0){

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {

                    PrinterMac printerMac = bgRealm.createObject(PrinterMac.class,1);
                    printerMac.setMacAddress(editTextMacaddress.getText().toString());
                    textviewMac.setText(editTextMacaddress.getText().toString());

                }
            });

            Toast.makeText(this, "Printer added successfully", Toast.LENGTH_SHORT).show();

        }else {

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm bgRealm) {
                    PrinterMac printerMac = bgRealm.where(PrinterMac.class).equalTo("rowid",1).findFirst();
                    printerMac.setMacAddress(editTextMacaddress.getText().toString());
                    textviewMac.setText(editTextMacaddress.getText().toString());
                }
            });

            Toast.makeText(this, "Printer update successfully", Toast.LENGTH_SHORT).show();
            
        }
        
        
        
        relativelayoutSettingPrinterAddressAdding.setVisibility(View.GONE);
    }




    private String getMACaddress(){
        PrinterMac printerMac  = realm.where(PrinterMac.class).findFirst();
        String mac;

        if(printerMac==null){
            mac="No Printer";
        }else {
            mac= printerMac.getMacAddress();
        }


        return mac;

    }


    @Override
    public void getOutletDetails(Outlet outletItems) {
        textvieCurrentTimeinterval.setText("Current Interval  "+String.valueOf(outletItems.getPickupTimeInterval())+" Min");

    }

    @Override
    public void getOutletDetailsFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateTimeIntervalSuccess(int time) {
        Toast.makeText(this, "Time Interval update successfully", Toast.LENGTH_SHORT).show();
        textvieCurrentTimeinterval.setText("Current Interval  "+String.valueOf(time)+" Min");
    }

    @Override
    public void updateTimeIntervalFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
