package activitytest.example.com.logandregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstRetrieveActivity extends AppCompatActivity {
    private Button btn_validate;
    private String userName,security,vlSecurity,psw,pswAgain;
    private EditText et_user_name,et_security,et_psw,et_psw_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_psw);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        btn_validate=findViewById(R.id.btn_validate);
        et_user_name=findViewById(R.id.et_user_name);
        et_security=findViewById(R.id.et_security);
        et_psw=findViewById(R.id.et_psw);
        et_psw_again=findViewById(R.id.et_psw_again);

        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=et_user_name.getText().toString().trim();
                security=et_security.getText().toString().trim();
                psw=et_psw.getText().toString().trim();
                pswAgain=et_psw_again.getText().toString().trim();
                vlSecurity=readSecurity(userName);
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(FirstRetrieveActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(security)){
                    Toast.makeText(FirstRetrieveActivity.this, "请输入密保", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(FirstRetrieveActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(FirstRetrieveActivity.this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(security.equals(vlSecurity)){
                    Toast.makeText(FirstRetrieveActivity.this, "认证成功", Toast.LENGTH_SHORT).show();
                    saveValidateStatus(true, userName);
                    saveRegisterPassword(userName, psw);
                    Intent data=new Intent();
                    data.putExtra("isValidate",true);
                    setResult(RESULT_OK,data);
                    Intent intent=new Intent();
                    startActivityForResult(intent, 1);
                }else if((vlSecurity!=null&&!TextUtils.isEmpty(vlSecurity)&&!security.equals(vlSecurity))){
                    Toast.makeText(FirstRetrieveActivity.this, "输入的用户名和密保不一致", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!psw.equals(pswAgain)) {
                    Toast.makeText(FirstRetrieveActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(FirstRetrieveActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String readSecurity(String userName){
        SharedPreferences sp=getSharedPreferences("validateInfo", MODE_PRIVATE);
        return sp.getString(userName , "");
    }

    private void saveValidateStatus(boolean status,String userName){
        SharedPreferences sp=getSharedPreferences("validateInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isValidate", status);
        editor.putString("validateUserName", userName);
        editor.commit();
    }

    private void saveRegisterPassword(String userName,String psw){
        String md5Psw = MD5Utils.md5(psw);
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(userName, md5Psw);
        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            String userName=data.getStringExtra("userName");
            if(!TextUtils.isEmpty(userName)){
                et_user_name.setText(userName);
                et_user_name.setSelection(userName.length());
            }
        }
    }
}
