package com.example.ilovecats.Helpers

import android.R.attr
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class Helpers {
    companion object{
        fun saveImage(url: String, requireContext: Context) {

        }

        var paginationPage: String? = ""
        var paginationCount: String? = ""



        /*fun saveImage(url: String?, context: Context) {
            var picasso = Picasso.get()
            picasso.load(url)
                .into(object : Target {
                    override fun onBitmapLoaded(bitmap: Bitmap, from: LoadedFrom) {
                        try {
                            val imageOutStream: OutputStream
                            imageOutStream = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) ({
                                val values = ContentValues()
                                values.put(
                                    Media.DISPLAY_NAME,
                                    "image_screenshot.jpg"
                                )
                                values.put(Media.MIME_TYPE, "image/jpeg")
                                values.put(
                                    Media.RELATIVE_PATH,
                                    Environment.DIRECTORY_PICTURES
                                )
                                val uri: Uri? = context.getContentResolver().insert(
                                    Media.EXTERNAL_CONTENT_URI,
                                    values
                                )
                                context.getContentResolver().openOutputStream(uri!!)
                            })!! else {
                                val imagesDir =
                                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                                        .toString()
                                val image = File(imagesDir, "image_screenshotjpg")
                                FileOutputStream(image)
                            }


                            attr.bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageOutStream)
                            imageOutStream.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        Toast.makeText(
                            context,
                            "Image Downloaded",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {

                    }


                    override fun onPrepareLoad(placeHolderDrawable: Drawable) {}
                })
        }*/
    }

    

}