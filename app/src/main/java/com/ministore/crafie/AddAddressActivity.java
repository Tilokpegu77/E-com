package com.ministore.crafie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {
    private EditText mName;
    private EditText mCity;
    private EditText mCode;
    private EditText mNumber;
    private EditText mAddress;
    private Button mAddAddressbtn;
    private FirebaseFirestore mStore;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        mName=findViewById(R.id.ad_name);
        mNumber=findViewById(R.id.ad_phone);
        mCity=findViewById(R.id.ad_city);
        mAddress=findViewById(R.id.ad_address);
        mCode=findViewById(R.id.ad_code);
        mAddAddressbtn=findViewById(R.id.ad_add_address_btn);
        mStore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();

        mAddAddressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mName.getText().toString();
                String city=mCity.getText().toString();
                String number=mNumber.getText().toString();
                String address=mAddress.getText().toString();
                String code=mCode.getText().toString();
                String final_address="";
                if (!name.isEmpty()){
                    final_address+=name+",";
                }
                if (!code.isEmpty()){
                    final_address+=code+",";
                }
                if (!address.isEmpty()){
                    final_address+=address+",";
                }
                if (!number.isEmpty()){
                    final_address+=number+",";
                }
                if (!city.isEmpty()){
                    final_address+=city+",";
                }
                Map<String,String> mMap=new HashMap<>();
                mMap.put("address",final_address);

                mStore.collection("User")
                        .document(mAuth.getCurrentUser().getUid())
                        .collection("Address").add(mMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(AddAddressActivity.this, "Address Added", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
            }
        });

    }
}