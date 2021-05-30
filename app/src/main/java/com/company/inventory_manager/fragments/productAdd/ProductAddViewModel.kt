package com.company.inventory_manager.fragments.productAdd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.inventory_manager.database.FireBaseDatabase
import com.company.inventory_manager.database.IDatabase
import com.company.inventory_manager.modell.Product
import com.company.inventory_manager.modell.ProductStatusType
import com.company.inventory_manager.util.dateToString
import com.company.inventory_manager.util.stringToDate
import com.google.android.gms.common.api.internal.TaskUtil
//import com.company.inventory_manager.util.BindingUtils
import com.google.android.gms.tasks.Task
import com.google.android.play.core.tasks.Tasks
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

    fun validate():Boolean{
        val p = getProduct();
        return p.validate();
    }

    fun submit(): Task<*> {
        var p = getProduct()

        if (productId != null){
            p.setProductId(productId);
            return database.updateProductById(productId, p);
        }else {
            var res = database.addNewProduct(p);
            return res;
        }
    }

    private fun getProduct(): Product {
        var p = Product();
        p.name = name.value;
        p.href = href.value;
        p.description = description.value;
        p.isBundle = isBundle.value ?: false;
        p.isCustomerVisible = isCustomerVisible.value ?: false;

        p.orderDate = stringToDate(orderDate.value);
        p.startDate = stringToDate(startDate.value);
        p.terminationDate = stringToDate(terminationDate.value);

        p.productSerialNumber = productSerialNumber.value;
        p.status = status.value;
        return p
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
                        orderDate.value = dateToString(p.orderDate);
                        startDate.value = dateToString(p.startDate);
                        terminationDate.value = dateToString(p.terminationDate);
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