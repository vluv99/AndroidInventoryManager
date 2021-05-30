package com.company.inventory_manager.fragments.productList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.inventory_manager.database.FireBaseDatabase
import com.company.inventory_manager.database.IDatabase
import com.company.inventory_manager.modell.Product
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject

class ProductListViewModel : ViewModel() {
    //var products = mutableListOf<Product>();
    val TAG = "ProductListViewModel";

    val products : MutableLiveData<List<Product>> = MutableLiveData(listOf());
    val database: IDatabase = FireBaseDatabase();

    lateinit var collRef: Query;

    var listener: ListenerRegistration? = null;

    init {
        showAll();
    }


    fun sortByDate(){
        listener?.remove();

        collRef = database.getAllProducts().orderBy("startDate");
        startListening();
    }

    fun showOnlyActive(){
        listener?.remove();

        collRef = database.getAllProducts().whereEqualTo("status","active");
        startListening();
    }


    fun showAll(){
        listener?.remove();

        collRef = database.getAllProducts();
        startListening();
    }


    fun startListening(){
        listener = collRef.addSnapshotListener { value, error ->
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