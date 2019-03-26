package com.example.hokikoutsi2019;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewregister;
    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewregister = (TextView) findViewById(R.id.textViewRegister);
        textViewregister.setOnClickListener(this);
        buttonLogin = (Button) findViewById(R.id.buttonLogIn);
        buttonLogin.setOnClickListener(this);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            Log.d("LOL", "User: " +  mAuth.getCurrentUser().toString());
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Log.d("LOL", "User: No User found...");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.textViewRegister))
        {
            Log.i("LOL", "Register text clicked");
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }

        else if (view == findViewById(R.id.buttonLogIn))
        {
            Log.i("LOL", "Login button clicked");

            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            if(TextUtils.isEmpty(email)) {
                editTextEmail.setError(getString(R.string.reg_fill_field));
                return;
            }
            if(TextUtils.isEmpty(password)) {
                editTextPassword.setError(getString(R.string.reg_fill_field));
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Log.i("LOL", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                //updateUI(user);
                            } else {
                                Log.w("LOL", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
