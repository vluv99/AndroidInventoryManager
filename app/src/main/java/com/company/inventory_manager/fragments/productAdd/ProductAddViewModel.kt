package com.company.inventoryManager.fragments.productAdd

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.company.inventoryManager.database.FireBaseDatabase
import com.company.inventoryManager.database.IDatabase
import com.company.inventoryManager.fragments.productView.ProductViewViewModel
import com.company.inventoryManager.modell.Product
import com.company.inventoryManager.modell.ProductStatusType
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.SuccessContinuation
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

class ProductAddViewModel(val productId: String?) :ViewModel(){
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

    fun submit(): Task<*> {
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

        if (productId != null){
            p.setProductId(productId);
            return database.updateProductById(productId, p);
        }else {
            var res = database.addNewProduct(p);
            return res;
        }
    }

    init {
        //name.value = "asd";
        if (productId != null){
                database.getProductById(productId).get().addOnSuccessListener {
                    var p: Product? = it?.toObject<Product>();

                    if (p != null){
                        name.value = p.name.toString();
                        //name.postValue(p.name);
                        href.value = p.href;
                        description.value = p.description;
                        isBundle.value = p.isBundle;
                        isCustomerVisible.value = p.isCustomerVisible;
                        orderDate.value = p.orderDate;
                        startDate.value = p.startDate;
                        terminationDate.value = p.terminationDate;
                        productSerialNumber.value = p.productSerialNumber;
                        status.value = p.status;
                    }
                };
        }
    }
}

class ProductAddViewModelFactory(val productId: String?): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  ProductAddViewModel(productId) as T;
    }
}