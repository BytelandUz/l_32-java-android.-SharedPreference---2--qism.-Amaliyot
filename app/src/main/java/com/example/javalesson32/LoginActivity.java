package com.example.javalesson32;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javalesson32.models.User;
import com.example.javalesson32.singltone.MyGson;
import com.example.javalesson32.singltone.MySharedPreference;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
//    private SharedPreferences sharedPreferences;
//    private SharedPreferences.Editor editor;
    private MySharedPreference mySharedPreference;

    private EditText editText1, editText2;
    private TextView tvRegister;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findBi();

//        sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
//        editor = sharedPreferences.edit();
        mySharedPreference = MySharedPreference.getInstance(this);

        btnLogin.setOnClickListener(view -> {
            String usersJsonString = mySharedPreference.getUsers();
            if (usersJsonString.equals("")) {
                Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
            } else {
                Type type = new TypeToken<List<User>>() {
                }.getType();
                List<User> userList = MyGson.getInstance().getGson().fromJson(usersJsonString, type);

                String userName = editText1.getText().toString();
                String password = editText2.getText().toString();

                boolean isHaveUser = false;
                for (User user : userList) {
                    if (user.getUserName().equals(userName) && user.getPasswrod().equals(password)) {
                        isHaveUser = true;
                        break;
                    }
                }
                if (isHaveUser) {
                    Intent intent = new Intent(this, WelcomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "UserName and Password incorrect", Toast.LENGTH_SHORT).show();
                }
            }

        });

        tvRegister.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            someActivityResultLauncher.launch(intent);
        });

    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String userName = data.getStringExtra("userName");
                        String password = data.getStringExtra("password");
                        editText1.setText(userName);
                        editText2.setText(password);
                    }
                }
            });

    private void findBi() {
        editText1 = findViewById(R.id.ed1);
        editText2 = findViewById(R.id.ed2);
        tvRegister = findViewById(R.id.tvRegistration);
        btnLogin = findViewById(R.id.btnLogin);
    }
}