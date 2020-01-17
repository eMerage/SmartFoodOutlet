package emerge.project.onmealoutlet.ui.activity.login;


import emerge.project.onmealoutlet.utils.entittes.v2.UpdateToken;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface LoginView {

//
    void userNameEmpty();
    void passwordEmpty();
    void passwordInvalid(String invalidReason);
    void loginValidationSuccessful();
    void loginValidationFail(String msg);
    void loginError(String msg);


    void updateStatus(Boolean status, UpdateToken updateToken);


}
