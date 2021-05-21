package com.company.inventoryManager.modell

import java.util.*

data class Product(
    val id: String,
    var href: String?,
    var description: String?,
    var isBundle: Boolean,
    var isCustomerVisible: Boolean,
    var name: String?,
    var orderDate: Date?,
    var productSerialNumber: String?,
    var startDate: Date?,
    var terminationDate: Date?,
    var status: ProductStatusType?
)
