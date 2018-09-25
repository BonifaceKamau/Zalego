package bonifacekamau.com.mysqldb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText first_name;
    private EditText second_name;
    private EditText user_name;
    private EditText password_txt;
    private Button reg_button;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        first_name = findViewById(R.id.firstName);
        second_name = findViewById(R.id.lastName);
        user_name = findViewById(R.id.userName);
        password_txt = findViewById(R.id.editPassword);

        reg_button = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = first_name.getText().toString();
                String sname = second_name.getText().toString();
                String uname = user_name.getText().toString();
                String upassword = password_txt.getText().toString();

                if(!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(upassword) && !TextUtils.isEmpty(fname) && !TextUtils.isEmpty(sname)){

                        mAuth.createUserWithEmailAndPassword(uname, upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Registration succesful " , Toast.LENGTH_LONG).show();

                                } else {

                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();

                                }


                            }
                        });
                } else {

                    Toast.makeText(RegisterActivity.this, "Confirm Password and Password Field doesn't match.", Toast.LENGTH_LONG).show();

                }
            }


        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            sendToMain();

        }

    }

    private void sendToMain() {

        Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }

    }

