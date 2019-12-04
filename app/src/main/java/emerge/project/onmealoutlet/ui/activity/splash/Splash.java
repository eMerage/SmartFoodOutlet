package emerge.project.onmealoutlet.ui.activity.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;


import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.ui.activity.home.Home;
import emerge.project.onmealoutlet.ui.activity.login.Login;
import emerge.project.onmealoutlet.ui.activity.menus.MenusActivity;
import io.realm.Realm;
import io.realm.RealmResults;

public class Splash extends Activity {


    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        final Intent intent;
        RealmResults<Outlet> allTransactions = realm.where(Outlet.class).findAll();
        realm.commitTransaction();

        if(allTransactions.size()==0){
            intent = new Intent(Splash.this, Login.class);
        }else {
            intent = new Intent(Splash.this, MenusActivity.class);
        }

        new Handler().postDelayed(new Runnable() {
            public void run()  {
                startActivity(intent);
                finish();
            }
        }, 2000);



    }
}
