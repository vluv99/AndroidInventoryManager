package com.company.inventoryManager.fragments.productList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.inventoryManager.modell.Product

class ProductListViewModel : ViewModel() {
    //var products = mutableListOf<Product>();
    val products : MutableLiveData<MutableList<Product>> = MutableLiveData(mutableListOf());

    init {
        products.value?.add(
            Product("1",null, "hellooooooooooooooooooooooooooooooooooooo",false, false,
                "product name-y", null, null, null, null, null))
    }
}