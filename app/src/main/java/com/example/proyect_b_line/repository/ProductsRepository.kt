package com.example.proyect_b_line.repository

import android.util.Half.toFloat
import com.example.proyect_b_line.model.Product
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import kotlin.random.Random

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
            50.0f
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
        )

    )
}

fun getDataWithJsoupEbay(search: String):MutableList<Product>{
    val search2 = search.replace(" ","+")
    val url = "https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2334524.m570.l1313&_nkw="+search2+"&_sacat=0"
    val doc: Document  = Jsoup.connect(url).get()

    var listaProductos = mutableListOf<Product>()

    var i= 1
    try {
        val total = doc.getElementsByClass("s-item__title").size-1
        while (i<total){
            val product_description: Element? = doc.getElementsByClass("s-item__title").get(i)
            val image: Element? = doc.getElementsByClass("s-item__image-img").get(i)
            val price: Element? = doc.getElementsByClass("s-item__price").get(i)
            var calificationP: String = "0"
            var sizeList = doc.getElementsByClass("s-item s-item__pl-on-bottom").size-1
            if (i>sizeList){
                calificationP = "0"
            } else {
                if (sizeList == 0){
                    calificationP = "0"
                } else {
                    var listCalification: Element? = doc.getElementsByClass("s-item s-item__pl-on-bottom").get(i-1)
                    var sizeCalification = listCalification?.getElementsByClass("x-star-rating")?.size
                    if (sizeCalification == null){
                        calificationP = "0"
                    } else{
                        if (sizeCalification == 0){
                            calificationP = "0"
                        } else {
                            val star: Element? = listCalification?.getElementsByClass("x-star-rating")?.get(0)
                            calificationP = star?.text().toString()
                        }
                    }
                }
            }

            if (calificationP.equals("")) {
                calificationP = "0"
            }
            val absHref = image!!.attr("src")
            val text: String = product_description?.text().toString()
            var priceP: String = price?.text().toString()
            var pricej = priceP.replace("Q","").split("a").get(0)
            pricej = pricej.replace(" ","")
            val priceF: Float = pricej.toFloat()
            var calificationT = calificationP.replace("de 5 estrellas.", "")
            var calificationF = calificationT.toFloat()
            var product_name: String = ""
            val text2 = text.replace("Anuncio nuevo","")
            val max = text2.length
            var p = 0
            while (p<26){
                if (max<=p){
                    product_name += ""
                } else{
                    product_name += text2[p]
                }
                p++
            }


            if (max>p){
                product_name += "..."
            }



            listaProductos.add(Product(urlImage = absHref, product_Description = product_name, costProduct = priceF, score = calificationF))
            i++
        }
        val j = 0
    }catch (exceptio:NumberFormatException){
        println(exceptio)
        listaProductos= getProducts()
    }


    return listaProductos

    // image!!.tagName("img").toString() // "http://jsoup.org/"
    // val relHref = image!!.attr("src") // == "/"
}

val listToUsersAgents = listOf("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0","Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6","Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.19582", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:99.0) Gecko/20100101 Firefox/99.0")

fun getDataWithJsoupAmazon(search: String):MutableList<Product>{
    val search2 = search.replace(" ","+")
    val url = "https://www.amazon.com/s?k="+search2
    val listaProductos = mutableListOf<Product>()
    val id = Random.nextInt(0, listToUsersAgents.size)
    var response: Connection.Response = Jsoup.connect(url).userAgent(listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
    var statusCode: Int = response.statusCode()
    while (statusCode == 503) {
        response = Jsoup.connect(url).userAgent(listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
        statusCode = response.statusCode()
    }
    val doc: Document  = Jsoup.connect(url).userAgent(listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).followRedirects(true).get()
    var i:Int = 0
    val total = doc.getElementsByClass("s-image").size-1
    while (i<total){
        val image: Element? = doc.getElementsByClass("s-image").get(i)

        val absHref = image!!.attr("src")

        listaProductos.add(Product(urlImage = absHref))
        i++
    }

    return listaProductos

}