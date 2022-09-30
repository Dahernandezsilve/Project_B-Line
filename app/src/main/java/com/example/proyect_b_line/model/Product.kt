package com.example.proyect_b_line.model

data class Product(val Url:String, val urlImage: String, val producDescrip:String, val disponibility:Boolean, val exists:Int, val score:Float, val sendly:Boolean, val costSend:Float, val costProduct:Float =0.0f)
