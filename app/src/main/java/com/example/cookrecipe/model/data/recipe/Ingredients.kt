package com.example.cookrecipe.model.data.recipe

import kotlin.jvm.internal.Ref.ObjectRef


data class Ingredients(
    val id:Int?,
    val name: String?,
    val nameClean:String?,
    val amount: Float?,
    val unit: String?,
    val original:String?,
    val originalName:String?,
    val consistency:String?,
    val measures: ObjectRef<Measures>?
)



