package ctwhs.order.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ctwhs.common.utils.ConvertUtils;
import ctwhs.order.R;
import ctwhs.order.common.activity.ActivityHelper;
import ctwhs.order.constant.ConstActivity;
import ctwhs.order.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding loginBinding;
    Intent langIntent;
    Bundle langBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    public void onClickVn(View view){
        ActivityHelper.setViewLanguages(ConstActivity.CONST_ACTIVITY_LANG_VN, this, R.layout.activity_login);
    }

    public void onClickJp(View view){
        ActivityHelper.setViewLanguages(ConstActivity.CONST_ACTIVITY_LANG_JP, this, R.layout.activity_login);
    }

    public void loginAction(View view){
        if(!isValid()){
            return;
        }

        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    /**
     * check validation before open MainMenu
     * @return
     */
    private boolean isValid(){
        boolean isValid = true;
        if(ConvertUtils.nullOrBlank(loginBinding.txtUserName.getText())){
            loginBinding.txtUserName.setError(getResources().getText(R.string.err_input_username));
            isValid = false;
        }

        if(ConvertUtils.nullOrBlank(loginBinding.txtPasswword.getText())){
            loginBinding.txtPasswword.setError(getResources().getText(R.string.err_input_password));
            isValid = false;
        }

        return isValid;
    }
}
