package com.didimstory.mangulmangul.Entity

data class purchaseListItem(
    val deliveryStatus: String, val createdAt: String, val artKitList: List<ArtKit>,
    val deliveryCompany: String, val deliveryNumber: String
)