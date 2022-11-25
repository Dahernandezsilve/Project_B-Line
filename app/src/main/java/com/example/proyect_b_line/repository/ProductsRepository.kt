package com.example.proyect_b_line.repository

import android.util.Half.toFloat
import com.example.proyect_b_line.model.Product
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import kotlin.random.Random

fun getProducts(): MutableList<Product>{
    return mutableListOf(
        Product(
            Url="https://www.steren.com.gt/media/catalog/product/cache/b69086f136192bea7a4d681a8eaf533d/image/20986abca/audifonos-bluetooth-con-bateria-de-hasta-30-h.jpg",
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
            Url="https://m.media-amazon.com/images/W/WEBP_402378-T1/images/I/41PSrh5lpdL._AC_SX466_.jpg",
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
            Url="https://m.media-amazon.com/images/W/WEBP_402378-T1/images/I/71lcyYiN9rL._AC_UY218_.jpg",
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
            var itemLinks = ""
            if (i>sizeList){
                calificationP = "0"
            } else {
                if (sizeList == 0){
                    calificationP = "0"
                } else {
                    var listCalification: Element? = doc.getElementsByClass("s-item s-item__pl-on-bottom").get(i-1)
                    var links: Element? = doc.getElementsByClass("s-item s-item__pl-on-bottom").get(i)
                    var itemLink: Element? = links?.getElementsByClass("s-item__link")?.get(0)
                    itemLinks = itemLink?.attr("href").toString()
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



            listaProductos.add(Product(urlImage = absHref, product_Description = product_name, costProduct = priceF, score = calificationF, Url = itemLinks))
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

val listToUsersAgents = listOf(
    "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36",
    "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6",
    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.157 Safari/537.36",
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36 Edge/18.19582",
    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36",
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.360",
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36",
    "Mozilla/5.0 (Windows NT 5.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36"
)

fun getDataWithJsoupAmazon(search: String):MutableList<Product>{
    val search2 = search.replace(" ","+")
    val url = "https://www.amazon.com/s?k="+search2
    var listaProductos = mutableListOf<Product>()
    val id = Random.nextInt(0, listToUsersAgents.size)
    var response: Connection.Response = Jsoup.connect(url).userAgent(listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
    var statusCode: Int = response.statusCode()
    while (statusCode == 503) {
        response = Jsoup.connect(url).userAgent(listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
        statusCode = response.statusCode()
    }
    val doc: Document  = Jsoup.connect(url).userAgent(listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).followRedirects(true).get()
    var i: Int= 0

    try{
        val total = doc.getElementsByClass("a-price-whole").size-1
        while (i<total){
            val image: Element? = doc.getElementsByClass("s-image").get(i)
            val product_description: Element? = doc.getElementsByClass("a-size-base-plus a-color-base a-text-normal").get(i)
            val price_product: Element? = doc.getElementsByClass("a-price-whole").get(i)
            val calification_product: Element? = doc.getElementsByClass("a-icon-alt").get(i)
            //score = calification_product.get(0).text().toString()
            val text: String = product_description?.text().toString()
            val price: String = price_product?.text().toString()
            val score: String = calification_product?.text().toString()

            //Variables Aux
            val listaScore = score.split(" ")


            var product_name: String = ""
            val max = text.length
            var p = 0
            while (p<20){
                if (max<=p){
                    product_name += ""
                } else{
                    product_name += text[p]
                }
                p++
            }

            val absHref = image!!.attr("src")
            if (max>p){
                product_name += "..."
            }

            listaProductos.add(Product(urlImage = absHref, product_Description = product_name, costProduct = price.toFloat(),score = listaScore[0].toFloat()))
            i++
        }
    }catch (exceptio:NumberFormatException){
        println(exceptio)
        listaProductos= getProducts()
    }

    return listaProductos

}

fun getDataWithCategorieModa():MutableList<Product>{
    var listaProductos = mutableListOf<Product>()
    try{
        val url = "https://www.amazon.com/s?i=specialty-aps&bbn=16225019011&rh=n%3A7141123011%2Cn%3A16225019011%2Cn%3A1040658&language=es&ref=nav_em__nav_desktop_sa_intl_clothing_0_2_13_2"
        val id = Random.nextInt(0, com.example.proyect_b_line.repository.listToUsersAgents.size)
        var response: Connection.Response = Jsoup.connect(url).userAgent(
            com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
        var statusCode: Int = response.statusCode()
        while (statusCode == 503) {
            response = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
            statusCode = response.statusCode()
        }
        val doc: Document  = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).followRedirects(true).get()
        var i:Int = 0
        val total = doc.getElementsByClass("a-price-whole").size-1
        while (i<total){
            val image: Element? = doc.getElementsByClass("s-image").get(i)
            val product_description: Element? = doc.getElementsByClass("a-size-base-plus a-color-base a-text-normal").get(i)
            val price_product: Element? = doc.getElementsByClass("a-price-whole").get(i)
            val calification_product: Element? = doc.getElementsByClass("a-icon-alt").get(i)

            val text: String = product_description?.text().toString()
            val price: String = price_product?.text().toString()
            val score: String = calification_product?.text().toString()

            //Variables Aux
            val listaScore = score.split(" ")


            var product_name: String = ""
            val max = text.length
            var p = 0
            while (p<20){
                if (max<=p){
                    product_name += ""
                } else{
                    product_name += text[p]
                }
                p++
            }

            val absHref = image!!.attr("src")
            if (max>p){
                product_name += "..."
            }

            listaProductos.add(Product(urlImage = absHref, product_Description = product_name, costProduct = price.toFloat(),score = listaScore[0].toFloat()))
            i++
        }
    }catch (exceptio:NumberFormatException){
        println(exceptio)
        listaProductos= getProducts()
    }
    return listaProductos
}

fun getDataWithCategorieVideojuegos():MutableList<Product>{
    var listaProductos = mutableListOf<Product>()
    try{
        val url = "https://www.amazon.com/-/es/gp/browse.html?node=16225016011&ref_=nav_em__nav_desktop_sa_intl_video_games_0_2_26_2"
        val id = Random.nextInt(0, com.example.proyect_b_line.repository.listToUsersAgents.size)
        var response: Connection.Response = Jsoup.connect(url).userAgent(
            com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
        var statusCode: Int = response.statusCode()
        while (statusCode == 503) {
            response = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
            statusCode = response.statusCode()
        }
        val doc: Document  = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).followRedirects(true).get()
        var i:Int = 0
        val total = doc.getElementsByClass("a-price-whole").size-1
        while (i<total){
            val image: Element? = doc.getElementsByClass("s-image").get(i)
            val product_description: Element? = doc.getElementsByClass("a-size-base-plus a-color-base a-text-normal").get(i)
            val price_product: Element? = doc.getElementsByClass("a-price-whole").get(i)
            val calification_product: Element? = doc.getElementsByClass("a-icon-alt").get(i)

            val text: String = product_description?.text().toString()
            val price: String = price_product?.text().toString()
            val score: String = calification_product?.text().toString()

            //Variables Aux
            val listaScore = score.split(" ")


            var product_name: String = ""
            val max = text.length
            var p = 0
            while (p<20){
                if (max<=p){
                    product_name += ""
                } else{
                    product_name += text[p]
                }
                p++
            }

            val absHref = image!!.attr("src")
            if (max>p){
                product_name += "..."
            }

            listaProductos.add(Product(urlImage = absHref, product_Description = product_name, costProduct = price.toFloat(),score = listaScore[0].toFloat()))
            i++
        }
    }catch (exceptio:NumberFormatException){
        println(exceptio)
        listaProductos= getProducts()
    }
    return listaProductos
}

fun getDataWithCategorieTecnologies():MutableList<Product>{
    var listaProductos = mutableListOf<Product>()
    try{
        val url = "https://www.amazon.com/s?i=specialty-aps&bbn=16225009011&rh=n%3A%2116225009011%2Cn%3A541966&language=es&ref=nav_em__nav_desktop_sa_intl_computers_and_accessories_0_2_5_6"
        val id = Random.nextInt(0, com.example.proyect_b_line.repository.listToUsersAgents.size)
        var response: Connection.Response = Jsoup.connect(url).userAgent(
            com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
        var statusCode: Int = response.statusCode()
        while (statusCode == 503) {
            response = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
            statusCode = response.statusCode()
        }
        val doc: Document  = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).followRedirects(true).get()
        var i:Int = 0
        val total = doc.getElementsByClass("a-price-whole").size-1
        while (i<total){
            val image: Element? = doc.getElementsByClass("s-image").get(i)
            val product_description: Element? = doc.getElementsByClass("a-size-base-plus a-color-base a-text-normal").get(i)
            val price_product: Element? = doc.getElementsByClass("a-price-whole").get(i)
            val calification_product: Element? = doc.getElementsByClass("a-icon-alt").get(i)

            val text: String = product_description?.text().toString()
            val price: String = price_product?.text().toString()
            val score: String = calification_product?.text().toString()

            //Variables Aux
            val listaScore = score.split(" ")


            var product_name: String = ""
            val max = text.length
            var p = 0
            while (p<20){
                if (max<=p){
                    product_name += ""
                } else{
                    product_name += text[p]
                }
                p++
            }

            val absHref = image!!.attr("src")
            if (max>p){
                product_name += "..."
            }

            listaProductos.add(Product(urlImage = absHref, product_Description = product_name, costProduct = price.toFloat(),score = listaScore[0].toFloat()))
            i++
        }
    }catch (exceptio:NumberFormatException){
        println(exceptio)
        listaProductos= getProducts()
    }
    return listaProductos
}

fun getDataWithCategorieDeportes():MutableList<Product>{
    var listaProductos = mutableListOf<Product>()
    try{
        val url = "https://www.amazon.com/s?i=sporting-intl-ship&bbn=16225014011&rh=n%3A10971181011%2Cn%3A706808011&dc&language=es&ds=v1%3AVfTzefizJ%2FCxhIt7v3FR0nea0bZ7IlIhVaSTWKNfDpM&qid=1669143387&rnid=10971181011&ref=sr_nr_n_6"
        val id = Random.nextInt(0, com.example.proyect_b_line.repository.listToUsersAgents.size)
        var response: Connection.Response = Jsoup.connect(url).userAgent(
            com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
        var statusCode: Int = response.statusCode()
        while (statusCode == 503) {
            response = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
            statusCode = response.statusCode()
        }
        val doc: Document  = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).followRedirects(true).get()
        var i:Int = 0
        val total = doc.getElementsByClass("a-price-whole").size-1
        while (i<total){
            val image: Element? = doc.getElementsByClass("s-image").get(i)
            val product_description: Element? = doc.getElementsByClass("a-size-base-plus a-color-base a-text-normal").get(i)
            val price_product: Element? = doc.getElementsByClass("a-price-whole").get(i)
            val calification_product: Element? = doc.getElementsByClass("a-icon-alt").get(i)

            val text: String = product_description?.text().toString()
            val price: String = price_product?.text().toString()
            val score: String = calification_product?.text().toString()

            //Variables Aux
            val listaScore = score.split(" ")


            var product_name: String = ""
            val max = text.length
            var p = 0
            while (p<20){
                if (max<=p){
                    product_name += ""
                } else{
                    product_name += text[p]
                }
                p++
            }

            val absHref = image!!.attr("src")
            if (max>p){
                product_name += "..."
            }

            listaProductos.add(Product(urlImage = absHref, product_Description = product_name, costProduct = price.toFloat(),score = listaScore[0].toFloat()))
            i++
        }
    }catch (exceptio:NumberFormatException){
        println(exceptio)
        listaProductos= getProducts()
    }
    return listaProductos
}

fun getDataWithCategorieSalud():MutableList<Product>{
    var listaProductos = mutableListOf<Product>()
    try{
        val url = "https://www.amazon.com/s?i=specialty-aps&bbn=16225010011&rh=n%3A%2116225010011%2Cn%3A3760941&language=es&ref=nav_em__nav_desktop_sa_intl_health_care_0_2_16_3"
        val id = Random.nextInt(0, com.example.proyect_b_line.repository.listToUsersAgents.size)
        var response: Connection.Response = Jsoup.connect(url).userAgent(
            com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
        var statusCode: Int = response.statusCode()
        while (statusCode == 503) {
            response = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
            statusCode = response.statusCode()
        }
        val doc: Document  = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).followRedirects(true).get()
        var i:Int = 0
        val total = doc.getElementsByClass("a-price-whole").size-1
        while (i<total){
            val image: Element? = doc.getElementsByClass("s-image").get(i)
            val product_description: Element? = doc.getElementsByClass("a-size-base-plus a-color-base a-text-normal").get(i)
            val price_product: Element? = doc.getElementsByClass("a-price-whole").get(i)
            val calification_product: Element? = doc.getElementsByClass("a-icon-alt").get(i)

            val text: String = product_description?.text().toString()
            val price: String = price_product?.text().toString()
            val score: String = calification_product?.text().toString()

            //Variables Aux
            val listaScore = score.split(" ")


            var product_name: String = ""
            val max = text.length
            var p = 0
            while (p<20){
                if (max<=p){
                    product_name += ""
                } else{
                    product_name += text[p]
                }
                p++
            }

            val absHref = image!!.attr("src")
            if (max>p){
                product_name += "..."
            }

            listaProductos.add(Product(urlImage = absHref, product_Description = product_name, costProduct = price.toFloat(),score = listaScore[0].toFloat()))
            i++
        }
    }catch (exceptio:NumberFormatException){
        println(exceptio)
        listaProductos= getProducts()
    }
    return listaProductos
}

fun getDataWithCategorieArte():MutableList<Product>{
    var listaProductos = mutableListOf<Product>()
    try{
        val url = "https://www.amazon.com/s?i=specialty-aps&bbn=4954955011&rh=n%3A4954955011%2Cn%3A%212617942011%2Cn%3A2747968011&language=es&ref=nav_em__nav_desktop_sa_intl_painting_drawing_supplies_0_2_8_2"
        val id = Random.nextInt(0, com.example.proyect_b_line.repository.listToUsersAgents.size)
        var response: Connection.Response = Jsoup.connect(url).userAgent(
            com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
        var statusCode: Int = response.statusCode()
        while (statusCode == 503) {
            response = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
            statusCode = response.statusCode()
        }
        val doc: Document  = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).followRedirects(true).get()
        var i:Int = 0
        val total = doc.getElementsByClass("a-price-whole").size-1
        while (i<total){
            val image: Element? = doc.getElementsByClass("s-image").get(i)
            val product_description: Element? = doc.getElementsByClass("a-size-base-plus a-color-base a-text-normal").get(i)
            val price_product: Element? = doc.getElementsByClass("a-price-whole").get(i)
            val calification_product: Element? = doc.getElementsByClass("a-icon-alt").get(i)

            val text: String = product_description?.text().toString()
            val price: String = price_product?.text().toString()
            val score: String = calification_product?.text().toString()

            //Variables Aux
            val listaScore = score.split(" ")


            var product_name: String = ""
            val max = text.length
            var p = 0
            while (p<20){
                if (max<=p){
                    product_name += ""
                } else{
                    product_name += text[p]
                }
                p++
            }

            val absHref = image!!.attr("src")
            if (max>p){
                product_name += "..."
            }

            listaProductos.add(Product(urlImage = absHref, product_Description = product_name, costProduct = price.toFloat(),score = listaScore[0].toFloat()))
            i++
        }
    }catch (exceptio:NumberFormatException){
        println(exceptio)
        listaProductos= getProducts()
    }
    return listaProductos
}

fun getDataWithCategorieSoftware():MutableList<Product>{
    var listaProductos = mutableListOf<Product>()
    try{
        val url = "https://www.amazon.com/s?i=specialty-aps&bbn=16225008011&rh=n%3A%2116225008011%2Cn%3A229677&language=es&ref=nav_em__nav_desktop_sa_intl_antivirus_security_0_2_22_3"
        val id = Random.nextInt(0, com.example.proyect_b_line.repository.listToUsersAgents.size)
        var response: Connection.Response = Jsoup.connect(url).userAgent(
            com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
        var statusCode: Int = response.statusCode()
        while (statusCode == 503) {
            response = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).execute()
            statusCode = response.statusCode()
        }
        val doc: Document  = Jsoup.connect(url).userAgent(com.example.proyect_b_line.repository.listToUsersAgents.get(id)).timeout(Random.nextInt(22000, 30000)).followRedirects(true).get()
        var i:Int = 0
        val total = doc.getElementsByClass("a-icon-alt").size-2
        while (i<total){
            val image: Element? = doc.getElementsByClass("s-image").get(i)
            val product_description: Element? = doc.getElementsByClass("a-size-medium a-color-base a-text-normal").get(i)
            val price_product: Element? = doc.getElementsByClass("a-price-whole").get(i)
            val calification_product: Element? = doc.getElementsByClass("a-icon-alt").get(i)

            val text: String = product_description?.text().toString()
            val price: String = price_product?.text().toString()
            val score: String = calification_product?.text().toString()

            //Variables Aux
            val listaScore = score.split(" ")


            var product_name: String = ""
            val max = text.length
            var p = 0
            while (p<20){
                if (max<=p){
                    product_name += ""
                } else{
                    product_name += text[p]
                }
                p++
            }

            val absHref = image!!.attr("src")
            if (max>p){
                product_name += "..."
            }

            listaProductos.add(Product(urlImage = absHref, product_Description = product_name, costProduct = price.toFloat(),score = listaScore[0].toFloat()))
            i++
        }
    }catch (exceptio:NumberFormatException){
        println(exceptio)
        listaProductos= getProducts()
    }
    return listaProductos
}