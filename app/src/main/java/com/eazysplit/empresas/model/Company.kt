package com.eazysplit.empresas.model

data class Company (
    val companyName: String = "",
    val companyEmail: String = "",
    val companyPhone: String = "",
    val companyDocument: String = "",
    var companyImage: String = ""
)