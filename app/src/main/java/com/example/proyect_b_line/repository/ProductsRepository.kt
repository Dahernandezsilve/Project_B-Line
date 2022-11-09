package com.example.proyect_b_line.repository


import android.icu.util.RangeValueIterator
import android.util.Log
import com.example.proyect_b_line.model.Product
import org.jsoup.Jsoup
import org.jsoup.select.Elements

fun getProducts(): List<Product>{
    return listOf(
        Product(
            Url="",
            urlImage = "https://www.steren.com.gt/media/catalog/product/cache/b69086f136192bea7a4d681a8eaf533d/image/20986abca/audifonos-bluetooth-con-bateria-de-hasta-30-h.jpg",
            "Audifonos L",
            false,
            19,
            2.4232323f,
            false,
            433.49005f,
            13.0f,
            false
        ),
        Product(
            Url="",
            urlImage = "https://m.media-amazon.com/images/W/WEBP_402378-T1/images/I/41PSrh5lpdL._AC_SX466_.jpg",
            "Joi Cons",
            false,
            12,
            4.423232f,
            false,
            433.49005f,
            50.0f,
            false
        ),
        Product(
            Url="",
            urlImage = "https://m.media-amazon.com/images/W/WEBP_402378-T1/images/I/71lcyYiN9rL._AC_UY218_.jpg",
            "Nintendo Wii usada",
            true,
            49,
            2.4232323f,
            false,
            433.49005f,
            100.0f,
            false
        ),
        Product(
            Url="",
            urlImage = "https://m.media-amazon.com/images/W/WEBP_402378-T1/images/I/41PSrh5lpdL._AC_SX466_.jpg",
            "Joi Cons",
            false,
            12,
            4.423232f,
            false,
            433.49005f,
            50.0f,
            false
        ),
        Product(
            Url="",
            urlImage = "https://i.pinimg.com/564x/df/0c/86/df0c86955745151d0291e698e9b2d528.jpg",
            "Pintura de Lobazo",
            false,
            29,
            2.4232323f,
            true,
            2333.49f,
            23.0f,
            false
        ),
        Product(
            Url="",
            urlImage = "https://images-na.ssl-images-amazon.com/images/W/WEBP_402378-T1/images/I/31uagjqD8DL._SX300_SY300_QL70_FMwebp_.jpg",
            "Kit de VR",
            true,
            49,
            2.4232323f,
            true,
            93.49004f,
            10000.0f,
            false
        )

    )
}
val store = "https://guatemaladigital.com/"

@RequiresApi(Build.VERSION_CODES.N)
fun getDataFromGuateDigi():String{

    var urlProduct = mutableStateOf("")
    val doc = Jsoup.connect(store).get()    // <1>
    doc.select("bloque--cat-CD:first-of-type bloque--cat--item image:first-of-type a")    // <2>
        .map { col -> col.attr("scr") }    // <3>
        .parallelStream()    // <4>
        .map { urlProduct.value= it }    // <5>
        .filter { it != null }
        .forEach { println(it) }
    return urlProduct.value
}


fun getDataWithJsoup():String{
    val url = "https://www.ebay.com/e/latam/sneakers"
    var ebayItems = listOf<Elements>()
    Jsoup.connect(url).get().also {
        //return it.title().toString()
        ebayItems = listOf(it.getElementsByClass("s-item__wrapper clearfix"))
        return it.getElementsByClass("s-item__wrapper clearfix").toString()
        //val elements = it.getElementsByClass("a-size-medium a-color-base a-text-normal").toString()
    }
}