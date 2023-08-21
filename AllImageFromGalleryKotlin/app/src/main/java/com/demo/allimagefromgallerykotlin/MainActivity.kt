package com.demo.allimagefromgallerykotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.demo.allimagefromgallerykotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    companion object {
        var imageList = ArrayList<ModelClass>()
    }

    var boolean_folder = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {
        if (checkPermission()) {

            Toast.makeText(this, "Permission already granted.", Toast.LENGTH_LONG).show()
            fn_imagespath()
        } else {

            requestPermission();
        }

        mainBinding.gvFolder.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(this, PhotosViewActivity::class.java)
            intent.putExtra("value", id)
            startActivity(intent);
        }
    }

    fun fn_imagespath(): java.util.ArrayList<ModelClass> {

        MainActivity.imageList.clear()
        var int_position = 0
        val cursor: Cursor?
        var absolutePathOfImage: String? = null
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection =
            arrayOf(MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        val orderBy = MediaStore.Images.Media.DATE_TAKEN
        cursor = applicationContext.contentResolver.query(
            uri, projection, null, null,
            "$orderBy DESC"
        )
        val column_index_data: Int = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        val column_index_folder_name: Int =
            cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data)
            Log.e("Column", absolutePathOfImage)
            Log.e("Folder", cursor.getString(column_index_folder_name))
            for (i in imageList.indices) {
                if (imageList[i].folderName
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
                val al_path = java.util.ArrayList<String?>()
                al_path.addAll(imageList.get(int_position).imagePath)
                al_path.add(absolutePathOfImage)
                imageList[int_position].imagePath
            } else {
                val al_path = java.util.ArrayList<String?>()
                al_path.add(absolutePathOfImage)
                var Str_folder = cursor.getString(column_index_folder_name)

                val obj_model = ModelClass(Str_folder, al_path)
//                obj_model.setStr_folder(cursor.getString(column_index_folder_name))
//                obj_model.setAl_imagepath(al_path)
                imageList.add(obj_model)
            }
        }
        for (i in imageList.indices) {
            Log.e("FOLDER", imageList[i].folderName)
            for (j in 0 until imageList[i].imagePath.size) {
                Log.e("FILE", imageList[i].imagePath[j].toString())
            }
        }
      var  obj_adapter = Adapter_PhotosFolder(this, imageList)
        mainBinding.gvFolder.setAdapter(obj_adapter)
        return imageList
    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val result1 = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val result2 = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.CAMERA
        )
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ),
            100
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> if (grantResults.isNotEmpty()) {
                val writeExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val readExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (writeExternalStorage && readExternalStorage)
                    Toast.makeText(
                        this,
                        "Permission Granted",
                        Toast.LENGTH_LONG
                    ).show()
                else {
                    Toast.makeText(
                        this,
                        "Permission Denied",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
    }


}