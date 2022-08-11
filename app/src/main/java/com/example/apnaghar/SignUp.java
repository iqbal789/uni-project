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

public class SignUp extends AppCompatActivity {

    EditText inputemail,inputpassword,inputconfirmpassword,inputname;
    Button btnSignUp, signin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signin = (Button) findViewById(R.id.register_signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensignin_page();
            }
        });
        inputname = (EditText) findViewById(R.id.inputname);
        inputemail = (EditText) findViewById(R.id.inputemail);
        inputpassword = (EditText) findViewById(R.id.inputpassword);
        inputconfirmpassword = (EditText) findViewById(R.id.inputconfirmpassword);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });
    }

    private void opensignin_page() {
        Intent intent = new Intent(SignUp.this,SignIn.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();
    }

    private void PerforAuth() {
        String name=inputname.getText().toString().trim();
        String email=inputemail.getText().toString();
        String password=inputpassword.getText().toString();
        String confirmpassword=inputconfirmpassword.getText().toString();

        if(name.isEmpty()){
            inputname.setError("Name is required");
            inputname.requestFocus();
            return;
        }
        if(!email.matches(emailPattern))
        {
            inputemail.setError("Enter Correct Email");
        }else if(password.isEmpty() || password.length()<6)
        {
            inputpassword.setError("Enter Proper Password");
        }else if(!password.equals(confirmpassword))
        {
            inputconfirmpassword.setError("Password didnt match both field");
        } else
        {
            progressDialog.setMessage("Please Wait While Registration");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextAcitivity();
                        Toast.makeText(SignUp.this, "Registration Succesful", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextAcitivity() {
        Intent intent = new Intent(SignUp.this, SignIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

}