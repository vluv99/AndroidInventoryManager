package com.company.inventoryManager.database

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FireBaseDatabase: IDatabase {
    override fun getAllProducts() {
        var data = Firebase.firestore.collection("products")
        data.get();

    }

}