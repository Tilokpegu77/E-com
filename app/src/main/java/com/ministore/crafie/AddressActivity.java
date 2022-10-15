package com.ministore.crafie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ministore.crafie.Domain.Address;
import com.ministore.crafie.adapter.AddressAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {
    private RecyclerView mAddressRecyclerView;
    private AddressAdapter mAddressAdapter;
    private Button mPaymentBtn;
    private Button mAddAddress;
    private List<Address>mAddressList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        mAddressRecyclerView=findViewById(R.id.address_recycler);
       mPaymentBtn=findViewById(R.id.payment_btn);
       mAddAddress=findViewById(R.id.add_address_btn);
       mAddressList=new ArrayList<>();
       mAddressAdapter=new AddressAdapter(getApplicationContext(),mAddressList);

        mAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddressActivity.this,AddAddressActivity.class);
                startActivity(intent);
            }
        });
    }
}