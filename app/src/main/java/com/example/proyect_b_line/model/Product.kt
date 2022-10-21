package com.example.proyect_b_line.model

data class Product(val Url:String,
                   val urlImage: String,
                   val product_Description:String,
                   val availability:Boolean,
                   val exists:Int,
                   val score:Float,
                   val shippable:Boolean,
                   val costSend:Float,
                   val costProduct:Float =0.0f)
