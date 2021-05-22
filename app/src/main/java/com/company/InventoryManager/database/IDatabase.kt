package com.company.inventoryManager.database

import com.company.inventoryManager.modell.Product
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference

interface IDatabase {
    fun getAllProducts(): CollectionReference
    fun getProductById(id: String): DocumentReference
    fun addNewProduct(p: Product): Task<DocumentReference>
    fun deleteProductById(id: String): Task<Void>
}