package com.company.inventory_manager.modell

import com.google.firebase.firestore.DocumentId
import java.util.*

data class Product(
    @DocumentId
    var id: String? = null,
    var href: String? = null,
    var description: String? = null,
    var isBundle: Boolean = false,
    var isCustomerVisible: Boolean = false,
    var name: String? = null,
    var orderDate: Date? = null,
    var productSerialNumber: String? = null,
    var startDate: Date? = null,
    var terminationDate: Date? = null,
    var status: ProductStatusType? = null
){
    fun setProductId(id: String){
        this.id = id;
    }
}