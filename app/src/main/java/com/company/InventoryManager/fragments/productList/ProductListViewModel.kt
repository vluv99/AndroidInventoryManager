package com.company.inventoryManager.fragments.productList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.inventoryManager.modell.Product

class ProductListViewModel : ViewModel() {
    var products = mutableListOf<Product>();
    val productsLivaData : MutableLiveData<MutableList<Product>> = MutableLiveData(mutableListOf());

    init {
        productsLivaData.value?.add(
            Product("1",null, "hellooooooooooooooooooooooooooooooooooooo",false, false,
                "name", null, null, null, null, null))
    }
}