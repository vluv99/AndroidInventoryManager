package com.company.inventoryManager.fragments.productView

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.inventoryManager.database.FireBaseDatabase
import com.company.inventoryManager.database.IDatabase
import com.company.inventoryManager.modell.Product
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.toObject

class ProductViewViewModel(val productId:String) : ViewModel() {
    val TAG = "ProductViewViewModel";
    var product: MutableLiveData<Product> = MutableLiveData();
    val database: IDatabase = FireBaseDatabase();

    init {
        database.getProductById(productId).addSnapshotListener { value, error ->
            if (error != null) {
                Log.w(TAG, "ERROR");
                return@addSnapshotListener
            }

            product.value = value?.toObject<Product>();

            /*products.value =
           Product("2","link link link link", "hellooooooooooooooooooooooooooooooooooooo",false, false,
               productId, null, "txrcvgbgagf", null, null, null)*/
        }
    }

    fun delete(): Task<Void> {
        return database.deleteProductById(productId);
    }
}

class ProductViewViewModelFactory(val productId: String): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  ProductViewViewModel(productId) as T;
    }
}