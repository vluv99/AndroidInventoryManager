package com.company.inventory_manager.fragments.productList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.inventory_manager.database.FireBaseDatabase
import com.company.inventory_manager.database.IDatabase
import com.company.inventory_manager.modell.Product
import com.google.firebase.firestore.ktx.toObject

class ProductListViewModel : ViewModel() {
    //var products = mutableListOf<Product>();
    val TAG = "ProductListViewModel";

    val products : MutableLiveData<List<Product>> = MutableLiveData(listOf());
    val database: IDatabase = FireBaseDatabase();

    init {
        database.getAllProducts().addSnapshotListener { value, error ->
            if (error != null){
                Log.w(TAG, "ERROR");
                products.value = null;
                return@addSnapshotListener
            }

            var savedProductsList = mutableListOf<Product>();
            for (prod in value!!){
                var item = prod.toObject<Product>()
                savedProductsList.add(item);
            }

            products.value = savedProductsList;
        }
    }
}