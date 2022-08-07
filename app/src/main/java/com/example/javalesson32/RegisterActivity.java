package com.example.javalesson32;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javalesson32.models.User;
import com.example.javalesson32.singltone.MyGson;
import com.example.javalesson32.singltone.MySharedPreference;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText ed1, ed2, ed3, ed4, ed5, ed6;
    private Button btnRegister;
//    private SharedPreferences sharedPreferences;
//    private SharedPreferences.Editor editor;
    private MySharedPreference mySharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fundUi();

//        sharedPreferences = getSharedPreferences("Login",MODE_PRIVATE);
//        editor = sharedPreferences.edit();
        mySharedPreference = MySharedPreference.getInstance(this);

        btnRegister.setOnClickListener(view -> {
            if (isValid()) {
                String fullName = ed1.getText().toString();
                String email = ed2.getText().toString();
                String phone = ed3.getText().toString();
                String userName = ed4.getText().toString();
                String passwrod = ed5.getText().toString();

                User user = new User(fullName,email,phone,userName,passwrod);
//                String userGsonString = sharedPreferences.getString("users", "");
                String userGsonString = mySharedPreference.getUsers();

                List<User> userList;

                if (userGsonString.equals("")){
                    userList = new ArrayList<>();
                } else  {
                    Type type = new TypeToken<List<User>>() {
                    }.getType();
                    userList = MyGson.getInstance().getGson().fromJson(userGsonString, type);
                }

                userList.add(user);

                String jsonString = MyGson.getInstance().getGson().toJson(userList);
//                editor.putString("users", jsonString);

                if (mySharedPreference.setUsers(jsonString)) {
                    Intent intent = new Intent();
                    intent.putExtra("userName", userName);
                    intent.putExtra("password", passwrod);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }

    private boolean isValid() {
        if (ed1.getText().toString().isEmpty()) {
            Toast.makeText(this, "FIO kiritilmagan", Toast.LENGTH_LONG).show();
            return false;
        } else if (ed2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Email kiritilmagan", Toast.LENGTH_LONG).show();
            return false;
        } else if (ed3.getText().toString().isEmpty()) {
            Toast.makeText(this, "Phone number kiritilmagan", Toast.LENGTH_LONG).show();
            return false;
        } else if (ed4.getText().toString().isEmpty()) {
            Toast.makeText(this, "user name kiritilmagan", Toast.LENGTH_LONG).show();
            return false;
        } else if (ed5.getText().toString().isEmpty()) {
            Toast.makeText(this, "Password kiritilmagan", Toast.LENGTH_LONG).show();
            return false;
        } else if (ed6.getText().toString().isEmpty()) {
            Toast.makeText(this, "Confirm Password kiritilmagan", Toast.LENGTH_LONG).show();
            return false;
        }else if (!ed6.getText().toString().equals(ed5.getText().toString())) {
            Toast.makeText(this, "Parollar bir xil emas", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void fundUi() {
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
        ed5 = findViewById(R.id.ed5);
        ed6 = findViewById(R.id.ed6);

        btnRegister = findViewById(R.id.btnRegister);
    }
}