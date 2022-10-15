package com.ministore.crafie.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ministore.crafie.Domain.BestSell;
import com.ministore.crafie.Domain.Category;
import com.ministore.crafie.Domain.Feature;
import com.ministore.crafie.R;
import com.ministore.crafie.adapter.BestSellAdapter;
import com.ministore.crafie.adapter.CategoryAdapter;
import com.ministore.crafie.adapter.FeatureAdapter;

import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends Fragment {
    private FirebaseFirestore mstore;
    //category tab
    private List<Category> mCategorylist;
    private CategoryAdapter mCategoryAdapter;
    private RecyclerView mCatRecyclerView;
    //featured tab
    private List<Feature> mFeaturelist;
    private FeatureAdapter mFeatureAdapter;
    private RecyclerView mFeatureRecyclerView;
    //BestSell tab
    private List<BestSell> mBestSellList;
    private BestSellAdapter mBestSellAdapter;
    private RecyclerView mBestSellRecyclerView;


   public HomeFragment(){

   }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home3, container, false);
        mstore=FirebaseFirestore.getInstance();
        mCatRecyclerView=view.findViewById(R.id.category_recycler);
        mFeatureRecyclerView=view.findViewById(R.id.feature_recycler);
        mBestSellRecyclerView=view.findViewById(R.id.bestsell_recycler);


        //category
        mCategorylist =new ArrayList<>();
        mCategoryAdapter=new CategoryAdapter(getContext(), mCategorylist);
        mCatRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        mCatRecyclerView.setAdapter(mCategoryAdapter);



        //feature
        mFeaturelist =new ArrayList<>();
        mFeatureAdapter=new FeatureAdapter(getContext(), mFeaturelist);
        mFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        mFeatureRecyclerView.setAdapter(mFeatureAdapter);

        //BestSell
        mBestSellList =new ArrayList<>();
        mBestSellAdapter=new BestSellAdapter(getContext(),mBestSellList);
        mBestSellRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        mBestSellRecyclerView.setAdapter(mBestSellAdapter);


        //get category
        mstore.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              Category category=document.toObject(Category.class);
                              mCategorylist.add(category);
                              mCategoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
        //get feature
        mstore.collection("Feature")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                            Feature category=document.toObject(Feature.class);
                               mFeaturelist.add(category);
                                mFeatureAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
        //get Best Sell
        mstore.collection("BestSell")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                BestSell bestSell=document.toObject(BestSell.class);
                                mBestSellList.add(bestSell);
                               mBestSellAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
        return view;
    }
}