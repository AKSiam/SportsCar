package com.example.sportscar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportscar.adapter.CarAdapter
import com.example.sportscar.databinding.ActivityMainBinding
import com.example.sportscar.model.Car

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var carAdapter: CarAdapter
    val car = ArrayList<Car>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        car.add(Car("Audi", "R8", "$25 Milion", "Description", R.drawable.audi1, ))
        car.add(Car("Dodge", "challenger", "$20 Milion", "Description", R.drawable.dodge))
        car.add(Car("Dodge", "Challenger", "$35 Milion", "Description", R.drawable.dodge1))
        car.add(Car("Dodge", "Challenger", "$20 Milion", "Description", R.drawable.dodge2))
        car.add(Car("Ford", "Mustang", "$30 Milion", "Description", R.drawable.fordmustang))
        car.add(Car("Ford", "Mustang", "$10 Milion", "Description", R.drawable.fordmustang1))
        car.add(Car("Mercedes", "Benz", "$20 Milion", "Description", R.drawable.mercedes))
        car.add(Car("Mercedes", "Benz", "$40 Milion", "Description", R.drawable.mercedesgt))
        car.add(Car("Range Rover", "Sport", "$40 Milion", "Description", R.drawable.rangerover))
        car.add(Car("Toyota", "Supra", "$10 Milion", "Description", R.drawable.tototasupra))
        car.add(Car("Toyota", "Supra", "$10 Milion", "Description", R.drawable.tototasupre1))


                carAdapter = CarAdapter(car)
                binding.recyclerView.adapter = carAdapter

                carAdapter.onClick={
            val intent = Intent(this, DetailActivity2::class.java)
            intent.putExtra("name", it.carName)
            intent.putExtra("price", it.carPrice)
            intent.putExtra("quantity", it.carModel)
            intent.putExtra("desc", it.carDesc)
            intent.putExtra("image", it.carImg)
            startActivity(intent)
        }

                binding.addBtn.setOnClickListener {
            showCarAddDialog()
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                car.removeAt(viewHolder.adapterPosition)
                carAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }

        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)




    }

    private fun showCarAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.add_car_layout, null)
        val nameEt = dialogView.findViewById<EditText>(R.id.carNameEt)
        val priceEt = dialogView.findViewById<EditText>(R.id.carPriceEt)
        val modelEt = dialogView.findViewById<EditText>(R.id.carModelEt)
        val descEt = dialogView.findViewById<EditText>(R.id.carDescEt)

        AlertDialog.Builder(this)
            .setTitle("Add Fruit")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameEt.text.toString()
                val price = priceEt.text.toString()
                val model = modelEt.text.toString()
                val desc = descEt.text.toString()
                val img = R.drawable.audi1
                car.add(Car(name, price, model, desc, img))
                carAdapter.notifyItemInserted(car.size - 1)
            }
            .setNegativeButton("Cancel", null)
            .show()




    }
}