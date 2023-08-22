package com.demo.allimagefromgallerykotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.demo.allimagefromgallerykotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    var boolean_folder = false
    lateinit var obj_adapter: Adapter_PhotosFolder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    mainBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

     mainBinding.gvFolder.onItemClickListener =
            OnItemClickListener { adapterView, view, i, l ->
                val intent = Intent(applicationContext, PhotosViewActivity::class.java)
                intent.putExtra("value", i)
                startActivity(intent)
            }
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) && ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
            } else {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    REQUEST_PERMISSIONS
                )
            }
        } else {
            Log.e("Else", "Else")
            fn_imagespath()
        }
    }

    fun fn_imagespath(): ArrayList<ModelClass> {
        al_images.clear()
        var int_position = 0
        val uri: Uri
        val cursor: Cursor?
        val column_index_data: Int
        val column_index_folder_name: Int
        var absolutePathOfImage: String? = null
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection =
            arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        val orderBy = MediaStore.Images.Media.DATE_TAKEN
        cursor = applicationContext.contentResolver.query(
            uri, projection, null, null,
            "$orderBy DESC"
        )
        column_index_data = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        column_index_folder_name =
            cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data)
            Log.e("Column", absolutePathOfImage)
            Log.e("Folder", cursor.getString(column_index_folder_name))
            for (i in al_images.indices) {
                if (al_images[i].folderName
                        .equals(cursor.getString(column_index_folder_name))
                ) {
                    boolean_folder = true
                    int_position = i
                    break
                } else {
                    boolean_folder = false
                }
            }
            if (boolean_folder) {
                val al_path = ArrayList<String>()
                al_path.addAll(al_images[int_position].imagePath)
                al_path.add(absolutePathOfImage)
                al_images[int_position].imagePath = al_path
            } else {
                val al_path = ArrayList<String>()
                al_path.add(absolutePathOfImage)
                var folderName = cursor.getString(column_index_folder_name)
                var imagePath = al_path
                val obj_model = ModelClass(folderName, imagePath)


                al_images.add(obj_model)
            }
        }
        for (i in al_images.indices) {
            Log.e("FOLDER", al_images[i].folderName)
            for (j in 0 until al_images[i].imagePath.size) {
                Log.e("FILE", al_images[i].imagePath[j])
            }
        }
        obj_adapter = Adapter_PhotosFolder(applicationContext, al_images)
        mainBinding.gvFolder.adapter = obj_adapter
        return al_images
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_PERMISSIONS -> {
                var i = 0
                while (i < grantResults.size) {
                    if (grantResults.isNotEmpty() && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        fn_imagespath()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    i++
                }
            }
        }
    }

    companion object {
        var al_images: ArrayList<ModelClass> = ArrayList<ModelClass>()
        var REQUEST_PERMISSIONS = 100
    }
}