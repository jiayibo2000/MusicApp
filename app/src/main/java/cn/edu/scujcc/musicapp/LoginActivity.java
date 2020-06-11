package cn.edu.scujcc.musicapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private final static String TAG = "Music";


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
                    loginSuccess();
                    break;
                case UserLab.USER_LOGIN_FAIL:
                    loginFailed();
                    break;
                case UserLab.USER_LOGIN_PASSWORD_ERROR :
                    loginPasswordError();
                    break;
            }
        }

    };

    private void loginPasswordError() {
        Toast.makeText(LoginActivity.this,
                "密码错误，请重试！",
                Toast.LENGTH_LONG).show();
    }

    private void loginSuccess() {
        Toast.makeText(LoginActivity.this,
                "",
                Toast.LENGTH_LONG).show();



        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void loginFailed() {
        Toast.makeText(LoginActivity.this,
                "",
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
           // TextInputLayout password = findViewById(R.id.password);
           // String u = username.getEditText().getText().toString();
            String p = password.getEditText().getText().toString();
            //TODO 调用Retrofit
            lab.login(user, p, handler);
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this,
                    "登录成功",
                    Toast.LENGTH_LONG).show();

        });

        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);

        });

    }
   // private void saveUser(String u){
   //     SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
   //     SharedPreferences.Editor editor = prefs.edit();
   //     editor.putString("user",u);
   //     editor.apply();
   // }

}