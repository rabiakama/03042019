package com.example.asus.a03042019.model


data class Videos (
    val id: String? = null,
    val name: String? = null,
    val url: String? = null,
    val results:List<Videos>
)