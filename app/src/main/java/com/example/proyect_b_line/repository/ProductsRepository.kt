package com.example.proyect_b_line.repository

import androidx.compose.runtime.mutableStateListOf
import com.example.proyect_b_line.model.Product
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
    var ebayItems = listOf<Elements>()
    val doc: Document  = Jsoup.connect(url).get()

    val listaProductos = mutableListOf<Product>()

    var i:Int = 0
    while (i<10){
        val image: Element? = doc.getElementsByClass("s-item__image-img").get(i)
        val absHref = image!!.attr("src")
        listaProductos.add(Product(urlImage = absHref))
        i++
    }

    return listaProductos

    // image!!.tagName("img").toString() // "http://jsoup.org/"
    // val relHref = image!!.attr("src") // == "/"
}

fun getDataWithJsoupAmazon(search: String):MutableList<Product>{
    var search2 = search.replace(" ","+")
    val url = "https://www.amazon.com/s?k="+search2+"&__mk_es_US=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=3GQXNXJFPJLID&sprefix="+search2+"%2Caps%2C163&ref=nb_sb_noss_1"
    var ebayItems = listOf<Elements>()
    val doc: Document  = Jsoup.connect(url).get()

    val listaProductos = mutableListOf<Product>()

    var i:Int = 0
    while (i<10){
        val image: Element? = doc.getElementsByClass("s-image").get(i)
        val description: Element? = doc.getElementsByClass("a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal").get(i)
        val desHref = description!!.attr("href")

        val docDescription: Document = Jsoup.connect("https://www.amazon.com/-/es"+desHref).get()
        val descriptionFinal: Elements = docDescription.select("#productTitle")
        //val costProduct: Element? = doc.getElementsByClass("a-row a-size-base a-color-base").get(i)

        val absHref = image!!.attr("src")
        val desc = descriptionFinal[1]!!.attr("")
        //val cost = costProduct!!.attr("").toFloat()

        listaProductos.add(Product(urlImage = absHref))
        listaProductos.add(Product(product_Description = desc))
        //listaProductos.add(Product(costProduct = cost))
        i++
    }

    return listaProductos
    // image!!.tagName("img").toString() // "http://jsoup.org/"
    // val relHref = image!!.attr("src") // == "/"
}