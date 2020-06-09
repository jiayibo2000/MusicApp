package cn.edu.scujcc.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private final static String TAG = "Music";

    private MyPreference prefs = MyPreference.getInstance();
    private TextInputLayout username,password;
    private  String user;

    private Button loginButton;
    private Button registerButton;
    private UserLab lab = UserLab.getInstance();



    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {

            switch (msg.what) {
                case UserLab.USER_LOGIN_SUCCESS:
                    loginSuccess(msg.obj);
                    break;
                case UserLab.USER_LOGIN_FAIL:
                    loginPasswordError();
                    break;
                case UserLab.USER_LOGIN_PASSWORD_ERROR :
                    loginFailed();
                    break;
            }
        }

    };

    private void loginPasswordError() {
        Toast.makeText(LoginActivity.this,
                "密码错误，请重试！",
                Toast.LENGTH_LONG).show();
    }

    private void loginSuccess(Object token) {
        Toast.makeText(LoginActivity.this,
                "登录成功！",
                Toast.LENGTH_LONG).show();
        //FIXME 替换tom，从服务器获取登录成功的真实用户名。
        prefs.saveUser(user,(String) token);
        Log.d(TAG,"用户"+user+"登录成功,token="+token);
        Intent intent = new Intent(LoginActivity.this, playerActivity.class);
        startActivity(intent);
    }

    private void loginFailed() {
        Toast.makeText(LoginActivity.this,
                "登录失败！",
                Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //prefs.setup(getApplicationContext());
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(v ->{
            user = username.getEditText().getText().toString();
            //TextInputLayout username = findViewById(R.id.username);
            //TextInputLayout password = findViewById(R.id.password);
            //String u = username.getEditText().getText().toString();
            String p = password.getEditText().getText().toString();
            //TODO 调用Retrofit
            lab.login(user, p, handler);
        });

        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        });
        prefs.setup(getApplicationContext());
    }
}