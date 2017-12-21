package ctwhs.order.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ctwhs.common.utils.ConvertUtils;
import ctwhs.order.R;
import ctwhs.order.databinding.ActivityLoginBinding;

public class FragmentLogin extends Fragment implements View.OnClickListener {

    ActivityLoginBinding loginBinding;
    private IEvent event;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.activity_login, container, false);

        event = (IEvent)getActivity();
        loginBinding.btnLogin.setOnClickListener(this);

        return loginBinding.getRoot();
    }

    public void onClickVn(View view){
//        ActivityHelper.setViewLanguages(ConstActivity.CONST_ACTIVITY_LANG_VN, this, R.layout.activity_login);
    }

    public void onClickJp(View view){
//        ActivityHelper.setViewLanguages(ConstActivity.CONST_ACTIVITY_LANG_JP, this, R.layout.activity_login);
    }

    public void loginAction(View view){
//        if(!isValid()){
//            return;
//        }
//
//        Intent intent = new Intent(this, MainMenuActivity.class);
//        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        event.onClick(view.getId());
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
