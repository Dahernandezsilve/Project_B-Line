package com.example.proyect_b_line.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.proyect_b_line.model.Product
import org.jsoup.Connection
import org.jsoup.Connection.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

fun getProducts(): MutableList<Product>{
    return mutableListOf(
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

fun getDataWithJsoupEbay(search: String):MutableList<Product>{
    var search2 = search.replace(" ","+")
    val url = "https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2334524.m570.l1313&_nkw="+search2+"&_sacat=0"
    val doc: Document  = Jsoup.connect(url).get()

    val listaProductos = mutableListOf<Product>()

    var i:Int = 0
    while (i<10){
        val product_description: Element? = doc.getElementsByClass("s-item__title").get(i)
        val image: Element? = doc.getElementsByClass("s-item__image-img").get(i)
        val absHref = image!!.attr("src")
        val text: String = product_description?.text().toString()
        var product_name: String = ""
        var max = text.length
        var p = 0
        while (p<27){
            if (max<=p){
                product_name += ""
            } else{
                product_name += text[p]
            }
            p++
        }
        if (max>p){
            product_name += "..."
        }
        listaProductos.add(Product(urlImage = absHref, product_Description = product_name))
        i++
    }

    return listaProductos

    // image!!.tagName("img").toString() // "http://jsoup.org/"
    // val relHref = image!!.attr("src") // == "/"
}

fun getDataWithJsoupAmazon(search: String):MutableList<Product>{
    var search2 = search.replace(" ","+")
    val url = "https://www.amazon.com/s?k="+search2
    val listaProductos = mutableListOf<Product>()

    var response: Connection.Response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(20000).execute()
    var statusCode: Int = response.statusCode()
    while (statusCode == 503) {
        response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(20000).execute()
        statusCode = response.statusCode()
    }
    val doc: Document  = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(20000).followRedirects(true).get()
    var i:Int = 0
    while (i<10){
        val image: Element? = doc.getElementsByClass("s-image").get(i)

        val absHref = image!!.attr("src")

        listaProductos.add(Product(urlImage = absHref))
        i++
    }

    return listaProductos

}