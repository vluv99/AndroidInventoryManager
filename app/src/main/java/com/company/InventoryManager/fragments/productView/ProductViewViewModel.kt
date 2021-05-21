package com.company.inventoryManager.fragments.productView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.inventoryManager.modell.Product

class ProductViewViewModel : ViewModel()  {
    val products : MutableLiveData<Product> = MutableLiveData();

    init {
        products.value =
            Product("2","link link link link", "hellooooooooooooooooooooooooooooooooooooo",false, false,
                "product name-y", null, "txrcvgbgagf", null, null, null)
    }
}