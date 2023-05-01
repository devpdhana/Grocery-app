package com.dhana.pn_storeadmin.Model

data class AddProductModel(
    var productName : String? = "",
    var productDescription : String? = "",
    var productCoverImage : String? = "",
    var productCategory : String? = "",
    var productId : String? = "",
    var productMrp : String? = "",
    var productSp : String? = "",
    var productImages : ArrayList<String>

)
