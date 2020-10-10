package com.example.mad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class AddSupplier extends AppCompatActivity {
    private EditText supplierName, supplierPassword, supplierEmail, supplierAge,supplierNic,supplierPhone;
    private Button regButton;
    private TextView supplierLogin;
    private FirebaseAuth firebaseAuth;
    String email, name, age, password,phone,nic;
    Uri imagePath;
    Supplier supplierProfile;

    private StorageReference storageReference;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance ( );

        storageReference = firebaseStorage.getReference();




        regButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String supplier_email = supplierEmail.getText().toString().trim();
                    String supplier_password = supplierPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(supplier_email, supplier_password).addOnCompleteListener(new OnCompleteListener<AuthResult> () {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                sendEmailVerification();
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(AddSupplier.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent (AddSupplier.this, ManageSupplier.class));
                            }else{
                                Toast.makeText(AddSupplier.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });


    }

    private void setupUIViews(){
        supplierName = (EditText)findViewById(R.id.name );
        supplierPassword = (EditText)findViewById(R.id.password );
        supplierEmail = (EditText)findViewById(R.id.email );
        supplierAge = (EditText)findViewById(R.id.age );
        supplierPhone=(EditText)findViewById ( R.id.phone );
        supplierNic=(EditText)findViewById ( R.id.nic ) ;
        regButton =(Button)findViewById(R.id.btnsave);

        // adminProfilePic = (ImageView)findViewById(R.id.ivProfile);
    }

    private Boolean validate(){
        Boolean result = false;

        name = supplierName.getText().toString();
        password = supplierPassword.getText().toString();
        email = supplierEmail.getText().toString();
        age = supplierAge.getText().toString();
        nic=supplierNic.getText().toString ();
        phone=supplierPhone.getText ().toString ();


        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty()||nic.isEmpty ()||phone.isEmpty ()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }


    private void sendEmailVerification(){
        FirebaseUser user= firebaseAuth.getCurrentUser();
        if(user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
//                        sendUserData();
                        Toast.makeText(AddSupplier.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        //startActivity(new Intent(AddStudent.this, StudentLogin.class));
                    }else{
                        Toast.makeText(AddSupplier.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }}


    private void sendUserData( ){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference().child ( "Supplier" ).child ( firebaseAuth.getUid ());
        StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
        UploadTask uploadTask = imageReference.putFile( Uri.parse ( "imagepath" ) );
        StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = uploadTask.addOnFailureListener ( new OnFailureListener ( ) {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText ( AddSupplier.this, "Upload failed!", Toast.LENGTH_SHORT ).show ( );
            }
        } ).addOnCompleteListener ( new OnCompleteListener<UploadTask.TaskSnapshot> ( ) {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText ( AddSupplier.this, "Upload successful!", Toast.LENGTH_SHORT ).show ( );
            }


        } );









        Supplier supplierProfile = new Supplier (name,age,email,phone,nic,password);
        myRef.setValue(supplierProfile);
    }
}




