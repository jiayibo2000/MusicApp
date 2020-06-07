package cn.edu.scujcc.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;

import android.widget.Button;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputLayout;



public class RegisterActivity extends AppCompatActivity {
    private final static String TAG = "Music";
    private Button registerButton;

    private UserLab lab = UserLab.getInstance();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg != null) {
                switch (msg.what) {
                    case UserLab.USER_REGISTER_SUCCESS:
                        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    case UserLab.USER_REGISTER_FAIL:
                        Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = findViewById(R.id.zc);
        registerButton.setOnClickListener(v -> {
            register();
        });
    }

        private void register(){
            User u = new User();
            boolean error = false;
            String errorMessage;
            //获得用户名
            TextInputLayout usernameInput = findViewById(R.id.username);
            Editable username = usernameInput.getEditText().getText();
            u.setUsername(username != null ? username.toString() : "");

            //检查密码是否一致
            TextInputLayout passwordInput1 = findViewById(R.id.password);
            TextInputLayout passwordInput2 = findViewById(R.id.password2);
            Editable password1 = passwordInput1.getEditText().getText();
            Editable password2 = passwordInput2.getEditText().getText();
            if (password1 != null && password2 != null) {
                if (!password2.toString().equals(password1.toString())) { //两次密码不相同
                    error = true;
                    errorMessage = "两次密码不相同";
                } else {
                    u.setPassword(password1.toString());
                }
            }


            //把u发送到服务器
            lab.register(u, handler);
        }

    }

