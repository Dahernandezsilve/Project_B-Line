package com.example.proyect_b_line.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.proyect_b_line.model.Product

class DBHandler
    (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + STORE_PRODUCT + " TEXT, "
                + URL_PRODUCT + " TEXT, "
                + URL_IMAGEPRODUCT + " TEXT,"
                + NAME_PRODUCT + " TEXT,"
                + AVAILABILITY_PRODUCT +" TEXT,"
                + EXIST_PRODUCT + " INTEGER,"
                + SCORE_PRODUCT + " FLOAT,"
                + SHIPPABLE_PRODUCT + " TEXT,"
                + COSTSEND_PRODUCT + " FLOAT,"
                + COSTPRODUCT_PRODUCT + " FLOAT)")

        db.execSQL(query)
    }

    fun addNewCourse(
        courseName: String?,
        courseDuration: String?,
        courseDescription: String?,
        courseTracks: String?
    ) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME_PRODUCT, courseName)
        values.put(URL_IMAGEPRODUCT, courseDuration)
        values.put(AVAILABILITY_PRODUCT, courseDescription)
        values.put(EXIST_PRODUCT, courseTracks)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "productsdb"

        private const val DB_VERSION = 1

        private const val TABLE_NAME = "favoriteproducts"

        private const val URL_PRODUCT = "url_product"

        private const val NAME_PRODUCT = "product_name"

        private const val URL_IMAGEPRODUCT = "urlimage"

        private const val AVAILABILITY_PRODUCT = "availability"
        private const val SCORE_PRODUCT = "score"
        private const val SHIPPABLE_PRODUCT = "shippable"
        private const val COSTSEND_PRODUCT = "costsend"
        private const val COSTPRODUCT_PRODUCT = "costproduct"
        private const val STORE_PRODUCT = "costproduct"

        private const val EXIST_PRODUCT = "exist"
    }

    fun readCourses(): ArrayList<Product>? {
        val db = this.readableDatabase

        val cursorCourses: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        val courseModelArrayList: ArrayList<Product> = ArrayList()

        if (cursorCourses.moveToFirst()) {
            do {
                courseModelArrayList.add(
                    Product(
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4).toBoolean(),
                        cursorCourses.getInt(5),
                        cursorCourses.getFloat(6),
                        cursorCourses.getString(7).toBoolean(),
                        cursorCourses.getFloat(8),
                        cursorCourses.getFloat(9)
                    )
                )
            } while (cursorCourses.moveToNext())
        }
        cursorCourses.close()
        return courseModelArrayList
    }
}
