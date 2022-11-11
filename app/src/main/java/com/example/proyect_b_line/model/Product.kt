package com.example.proyect_b_line.model

data class Product(val Url:String="",
                   val urlImage: String,
                   val product_Description:String="",
                   val availability:Boolean=false,
                   val exists:Int=0,
                   val score:Float=0.0f,
                   val shippable:Boolean=false,
                   val costSend:Float=0.0f,
                   val costProduct:Float =0.0f,
                    val favorite:Boolean = false
                   )
