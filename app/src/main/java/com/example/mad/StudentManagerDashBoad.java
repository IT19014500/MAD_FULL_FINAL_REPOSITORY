package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentManagerDashBoad extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button button4,button5;
    FirebaseDatabase database=FirebaseDatabase.getInstance ();
    DatabaseReference reference;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_student_manager_dash_boad );


        button4 = (Button) findViewById ( R.id.button4 );
        button4.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                openActivity2 ( );

            }


        } );

        button5 = (Button) findViewById ( R.id.button5);
        button5.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                openActivity3 ( );

            }


        } );

    }
        public void openActivity2(){

            Intent intent=new Intent (this, AddStudent.class);

            startActivity ( intent );

        }
        public void openActivity3(){

            Intent intent=new Intent (this, ManageStudent.class);

            startActivity ( intent );

        }

    private void Logout(){

        firebaseAuth = FirebaseAuth.getInstance();
      firebaseAuth.signOut();
        finish();
        startActivity(new Intent(StudentManagerDashBoad.this, StudentManagerLogin.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }



    }








