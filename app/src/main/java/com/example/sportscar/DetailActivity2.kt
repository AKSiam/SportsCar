package com.example.sportscar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sportscar.databinding.ActivityDetailBinding

class DetailActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val price = intent.getDoubleExtra("price",0.0)
        val model = intent.getIntExtra("quantity",0)
        val img = intent.getIntExtra("image",0)
        val desc = intent.getStringExtra("desc")

        binding.apply {
            carName.text = name
            carPrice.text = "Price: million" + price.toString()
            carModel.text = "Model: " + model.toString()
            carImg.setImageResource(img)
            carDesc.text = desc
        }
    }
}
