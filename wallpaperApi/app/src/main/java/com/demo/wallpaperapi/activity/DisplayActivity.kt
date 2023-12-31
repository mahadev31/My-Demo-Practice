package com.demo.wallpaperapi.activity

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.demo.wallpaperapi.R
import com.demo.wallpaperapi.databinding.ActivityDisplayBinding
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.Executors

class DisplayActivity : AppCompatActivity() {
    lateinit var displayBinding: ActivityDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayBinding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(displayBinding.root)

        permision()
        initView()
    }


    private fun initView() {
        var url = intent.getStringExtra("url")

        Log.e("TAG", "initView: " + url)
        Glide.with(this)
            .load(url)
            .placeholder(
                R.drawable.ic_image
            ).into(displayBinding.imgView)


        var mImage: Bitmap?

        // Declaring a webpath as a string
        val mWebPath = url

        // Declaring and initializing an Executor and a Handler
        val myExecutor = Executors.newSingleThreadExecutor()
        val myHandler = Handler(Looper.getMainLooper())

        displayBinding.btnDownload.setOnClickListener {
            myExecutor.execute {
                mImage = mWebPath?.let { it1 -> mLoad(it1) }
                myHandler.post {
                    displayBinding.imgView.setImageBitmap(mImage)
                    if (mImage != null) {
                        mSaveMediaToStorage(mImage)
                    }
                }
            }

        }
    }

    // Function to establish connection and load image
    private fun mLoad(string: String): Bitmap? {
        val url: URL = mStringToURL(string)!!
        val connection: HttpURLConnection?
        try {
            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream: InputStream = connection.inputStream
            val bufferedInputStream = BufferedInputStream(inputStream)
            return BitmapFactory.decodeStream(bufferedInputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
        }
        return null
    }

    // Function to convert string to URL
    private fun mStringToURL(string: String): URL? {
        try {
            return URL(string)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return null
    }

    // Function to save image on the device.
    // Refer: https://www.geeksforgeeks.org/circular-crop-an-image-and-save-it-to-the-file-in-android/
    private fun mSaveMediaToStorage(bitmap: Bitmap?) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }
        fos?.use {
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this, "Saved to Gallery", Toast.LENGTH_SHORT).show()
        }
    }


    private fun permision() {
        if (checkPermission()) {

            Toast.makeText(this, "Permission already granted.", Toast.LENGTH_LONG).show()

        } else {

            requestPermission();
        }
    }
    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(applicationContext,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val result1 = ContextCompat.checkSelfPermission(applicationContext,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val result2 = ContextCompat.checkSelfPermission(applicationContext,
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
            100 -> if (grantResults.size > 0) {
                val writeExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val readExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED
                val camera = grantResults[2] == PackageManager.PERMISSION_GRANTED
                if (writeExternalStorage && readExternalStorage && camera)
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