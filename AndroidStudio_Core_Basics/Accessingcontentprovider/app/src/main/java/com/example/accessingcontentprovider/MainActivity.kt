package com.example.accessingcontentprovider

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var CONTENT_URI = Uri.parse("content://com.example.contentprovidersinandroid.MyContentProvider/users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("Range")
    fun onClickShowDetails(view: View?) {
        val resultView = findViewById<View>(R.id.res) as TextView
        // creating a cursor object of the content URI
        val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)

        // iteration of the cursor to print table
        if (cursor!!.moveToFirst()) {
            val strBuild = StringBuilder()
            while (!cursor.isAfterLast) {
                strBuild.append("""
	${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}
	""".trimIndent())
                cursor.moveToNext()
            }
            resultView.text = strBuild
        } else {
            resultView.text = "No Records Found"
        }
    }
}

