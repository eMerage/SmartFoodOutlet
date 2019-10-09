package emerge.project.onmealoutlet.ui.activity.login;


import android.content.Context;

/**
 * Created by Himanshu on 4/4/2017.
 */

public class LoginPresenterImpli implements LoginPresenter,LoginInteractor.OnLoginValidationFinishedListener{


    private LoginView loginView;
    LoginInteractor loginInteractor;


    public LoginPresenterImpli(LoginView loginview) {
        this.loginView = loginview;
        this.loginInteractor = new LoginInteractorImpil();

    }


    @Override
    public void userNameEmpty() {
        loginView.userNameEmpty();
    }

    @Override
    public void passwordEmpty() {
        loginView.passwordEmpty();
    }

    @Override
    public void passwordInvalid(String invalidReason) {
        loginView.passwordInvalid(invalidReason);
    }

    @Override
    public void loginValidationSuccessful() {
        loginView.loginValidationSuccessful();
    }

    @Override
    public void loginValidationFail(String msg) {
        loginView.loginValidationFail(msg);
    }

    @Override
    public void loginError(String msg) {
        loginView.loginError(msg);
    }

    @Override
    public void checkLoginValidation(Context context, String userName, String password) {
        loginInteractor.checkLoginValidation( context,userName,password,this);
    }
}
