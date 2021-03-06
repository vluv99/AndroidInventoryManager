package com.company.inventory_manager.database

import com.company.inventory_manager.modell.Product
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference

interface IDatabase {
    fun getAllProducts(): CollectionReference
    fun getProductById(id: String): DocumentReference
    fun addNewProduct(p: Product): Task<DocumentReference>
    fun deleteProductById(id: String): Task<Void>
    fun updateProductById(id: String, p: Product): Task<Void>
}