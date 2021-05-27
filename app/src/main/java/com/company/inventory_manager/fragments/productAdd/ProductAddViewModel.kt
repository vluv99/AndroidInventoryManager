package com.company.inventory_manager.fragments.productAdd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.inventory_manager.database.FireBaseDatabase
import com.company.inventory_manager.database.IDatabase
import com.company.inventory_manager.modell.Product
import com.company.inventory_manager.modell.ProductStatusType
import com.company.inventory_manager.util.Converter
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.toObject

class ProductAddViewModel(val productId: String?) :ViewModel(){
    val TAG = "ProductAddModel";

    val database: IDatabase = FireBaseDatabase();

    val name: MutableLiveData<String> = MutableLiveData();
    val href: MutableLiveData<String> = MutableLiveData();
    val description: MutableLiveData<String> = MutableLiveData();
    val isBundle: MutableLiveData<Boolean> = MutableLiveData();
    val isCustomerVisible: MutableLiveData<Boolean> = MutableLiveData();
    val orderDate: MutableLiveData<String> = MutableLiveData();
    val startDate: MutableLiveData<String> = MutableLiveData();
    val terminationDate: MutableLiveData<String> = MutableLiveData();
    val productSerialNumber: MutableLiveData<String> = MutableLiveData();
    val status: MutableLiveData<ProductStatusType> = MutableLiveData();

    fun submit(): Task<*> {
        var p = Product();
        p.name = name.value;
        p.href = href.value;
        p.description = description.value;
        p.isBundle = isBundle.value ?:false;
        p.isCustomerVisible = isCustomerVisible.value ?:false;

        p.orderDate = Converter.stringToDate(orderDate.value);
        p.startDate = Converter.stringToDate(startDate.value);
        p.terminationDate = Converter.stringToDate(terminationDate.value);

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
                        orderDate.value = Converter.dateToString(p.orderDate);
                        startDate.value = Converter.dateToString(p.startDate);
                        terminationDate.value = Converter.dateToString(p.terminationDate);
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