package com.example.apnaghar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    EditText inputemail, inputpassword, inputconfirmpassword;
    Button btnSignIn;
    Button signup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        signup = findViewById(R.id.login_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensignup_page();
            }
        });
        inputemail = findViewById(R.id.inputemail);
        inputpassword = findViewById(R.id.inputpassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforSignIn();
            }
        });
    }

    private void opensignup_page() {
        Intent intent = new Intent(SignIn.this,SignUp.class);
        startActivity(intent);
    }

    private void perforSignIn() {

        String email = inputemail.getText().toString();
        String password = inputpassword.getText().toString();

        if (!email.matches(emailPattern)) {
            inputemail.setError("Enter Correct Email");
        } else if (password.isEmpty() || password.length() < 6) {
            inputpassword.setError("Enter Proper Password");
        } else {
            progressDialog.setMessage("Please Wait While Login...");
            progressDialog.setTitle("Login...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(SignIn.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SignIn.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(SignIn.this, home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}