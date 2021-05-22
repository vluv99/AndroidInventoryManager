package com.company.inventoryManager.database

import com.company.inventoryManager.modell.Product
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FireBaseDatabase: IDatabase {
    override fun getAllProducts(): CollectionReference {
        var data = Firebase.firestore.collection("products")

        return data;
    }

    override fun getProductById(id: String): DocumentReference {
        var data = Firebase.firestore.collection("products").document(id);

        return data;
    }

    override fun addNewProduct(p: Product): Task<DocumentReference> {
        var database = Firebase.firestore.collection("products");
        return database.add(p);
    }

    override fun deleteProductById(id: String): Task<Void> {
        var database = Firebase.firestore.collection("products");
        return database.document(id).delete();
    }

}