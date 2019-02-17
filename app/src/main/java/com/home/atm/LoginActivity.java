package com.home.atm;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText edUserid ;
    private EditText edPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserid = findViewById(R.id.userId);
        edPasswd = findViewById(R.id.passwd);
    }

    public void login(View view){
        String userid = edUserid.getText().toString();
        final String passwd = edPasswd.getText().toString();
        FirebaseDatabase.getInstance().getReference("users").child(userid).child("password")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String pw = (String) dataSnapshot.getValue();
                        if (pw.equals(passwd)){
                            setResult(RESULT_OK);
                            finish();
                        }else{
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("ERROR")
                                    .setMessage("ERROR")
                                    .setPositiveButton("OK", null  )
                                    .show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


    }

    public void quit(View view){

    }
}
