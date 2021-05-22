package com.company.inventoryManager.fragments.productAdd

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.inventoryManager.database.FireBaseDatabase
import com.company.inventoryManager.database.IDatabase
import com.company.inventoryManager.modell.Product
import com.company.inventoryManager.modell.ProductStatusType
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.toObject
import java.util.*

class ProductAddViewModel :ViewModel(){
    val TAG = "ProductAddModel";

    val database: IDatabase = FireBaseDatabase();

    val name: MutableLiveData<String> = MutableLiveData();
    val href: MutableLiveData<String> = MutableLiveData();
    val description: MutableLiveData<String> = MutableLiveData();
    val isBundle: MutableLiveData<Boolean> = MutableLiveData();
    val isCustomerVisible: MutableLiveData<Boolean> = MutableLiveData();
    val orderDate: MutableLiveData<Date> = MutableLiveData();
    val startDate: MutableLiveData<Date> = MutableLiveData();
    val terminationDate: MutableLiveData<Date> = MutableLiveData();
    val productSerialNumber: MutableLiveData<String> = MutableLiveData();
    val status: MutableLiveData<ProductStatusType> = MutableLiveData();

    fun submit(): Task<DocumentReference> {
        var p = Product();
        p.name = name.value;
        p.href = href.value;
        p.description = description.value;
        p.isBundle = isBundle.value ?:false;
        p.isCustomerVisible = isCustomerVisible.value ?:false;
        p.orderDate = orderDate.value;
        p.startDate = startDate.value;
        p.terminationDate = terminationDate.value;
        p.productSerialNumber = productSerialNumber.value;
        p.status = status.value;

        var res = database.addNewProduct(p);
        return res;
    }
}