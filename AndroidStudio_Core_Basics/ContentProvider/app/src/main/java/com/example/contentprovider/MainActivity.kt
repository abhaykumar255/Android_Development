package com.example.contentprovider

import android.content.Context
import android.content.pm.PackageManager
import android.media.tv.TvContract.Channels.CONTENT_URI
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Contacts
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.contentprovider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding : ActivityMainBinding
     get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun getContactList() : MutableSet<String>{
        sdkIntAboveOreo {
            // requesting the permission to access
            var contentResolver = applicationContext.contentResolver
            var cursor = contentResolver.query(Contacts,CONTENT_URI,null,null,null)
        }
    }

    // requesting permission
    private val registerActivityResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if (it){
                getContactList()
            }

        }

    // version checking
    inline fun sdkIntAboveOreo(call: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            call.invoke()
        }
    }

    // checking the permission
    inline fun isPermissionGranted(context: Context,permission:String,call:(Boolean)->Unit){
        if(ContextCompat.checkSelfPermission(context,permission)==PackageManager.PERMISSION_GRANTED){
            call.invoke(true)
        }
        else{
            call.invoke(false)
        }
    }
}