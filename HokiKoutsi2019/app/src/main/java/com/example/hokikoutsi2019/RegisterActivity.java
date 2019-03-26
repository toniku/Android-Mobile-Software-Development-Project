package com.example.hokikoutsi2019;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hokikoutsi2019.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;

    private EditText editTextFirstname;
    private EditText editTextLastname;
    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConPassword;

    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String conPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);

        editTextFirstname = (EditText) findViewById(R.id.editTextFirstname);
        editTextLastname = (EditText) findViewById(R.id.editTextLastname);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null)
                {
                    //Do this if user is already logged in. Go to another activity for eaxample
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onClick(View view) {

        if (view == findViewById(R.id.buttonRegister))
        {
            firstname = editTextFirstname.getText().toString();
            lastname = editTextLastname.getText().toString();
            email = editTextEmail.getText().toString();
            username = editTextUsername.getText().toString();
            password = editTextPassword.getText().toString();
            conPassword = editTextConPassword.getText().toString();

            if(TextUtils.isEmpty(firstname)) {
                editTextFirstname.setError(getString(R.string.reg_fill_field));
                return;
            }
            if(TextUtils.isEmpty(lastname)) {
                editTextLastname.setError(getString(R.string.reg_fill_field));
                return;
            }
            if(TextUtils.isEmpty(email)) {
                editTextEmail.setError(getString(R.string.reg_fill_field));
                return;
            }
            if(TextUtils.isEmpty(username)) {
                editTextUsername.setError(getString(R.string.reg_fill_field));
                return;
            }
            if(TextUtils.isEmpty(password)) {
                editTextPassword.setError(getString(R.string.reg_fill_field));
                return;
            }
            if(TextUtils.isEmpty(conPassword)) {
                editTextConPassword.setError(getString(R.string.reg_fill_field));
                return;
            }

            Log.i("RegisterButton", "Firstname: " + firstname);
            Log.i("RegisterButton", "Lastname: " + lastname);
            Log.i("RegisterButton", "Email: " + email);
            Log.i("RegisterButton", "Username: " + username);
            Log.i("RegisterButton", "Password: " + password);
            Log.i("RegisterButton", "Confirmed Password: " + conPassword);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                User user = new User();
                                user.setFirstname(firstname);
                                user.setLastname(lastname);
                                user.setEmail(email);
                                user.setUsername(username);

                                Toast.makeText(RegisterActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful())
                                                {
                                                    Toast.makeText(RegisterActivity.this, "Registeration Success!", Toast.LENGTH_LONG).show();
                                                }
                                                else
                                                {
                                                    Toast.makeText(RegisterActivity.this, "Error In Registeration adding to datbase", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });

                            } else {
                                Log.w("LOL", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

            /*mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                //We will store additional fields in the database
                                /*User user = new User(firstname, lastname, email, username);
                                FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful())
                                        {
                                            Toast.makeText(RegisterActivity.this, "Registeration Success!", Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(RegisterActivity.this, "Error In Registeration adding to datbase", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                                databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");


                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this, "Error In Registeration lol", Toast.LENGTH_LONG).show();
                            }
                        }
                    });*/
        }
    }
}
