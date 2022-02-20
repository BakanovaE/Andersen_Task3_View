package org.martellina.task3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.util.concurrent.Executors

class PicturesActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var buttonPicasso: Button
    private lateinit var buttonGlide: Button
    private lateinit var buttonStandard: Button
    private lateinit var imageView: ImageView
    private val picasso = Picasso.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictures)
        buttonPicasso = findViewById(R.id.button_picasso)
        buttonGlide = findViewById(R.id.button_glide)
        buttonStandard = findViewById(R.id.button_standard)
        editText = findViewById(R.id.editText)
        imageView = findViewById(R.id.imageView)
    }

    fun downloadWithPicasso(view: View) {
        picasso
            .load(editText.text.toString())
            .placeholder(R.drawable.placeholder)
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    return
                }

                override fun onError(e: Exception?) {
                    Toast.makeText(applicationContext, "Can`t load image because of $e", Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun downloadWithGlide(view: View) {
        Glide
            .with(this)
            .load(editText.text.toString())
            .placeholder(R.drawable.placeholder)
            .listener(object : RequestListener<Drawable> {

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Toast.makeText(applicationContext, "Can`t load image because of $e", Toast.LENGTH_SHORT).show()
                    return true
                }
            })
            .into(imageView)
    }

    fun standardDownload(view: View) {
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        var picture: Bitmap? = null

        executor.execute {
            try {
                val loader = java.net.URL(editText.text.toString()).openStream()
                picture = BitmapFactory.decodeStream(loader)
                handler.post {
                    imageView.setImageBitmap(picture)
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(applicationContext, "Can`t load image because of $e", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}