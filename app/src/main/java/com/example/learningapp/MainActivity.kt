package com.example.learningapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Space
import androidx.cardview.widget.CardView
import com.example.learningapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var orders: MutableList<View>
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		orders = mutableListOf()
		binding.fabAdd.setOnClickListener{
			val view = layoutInflater.inflate(R.layout.order_card, null) // вопрос по inflater / binding
			val food: EditText = view.findViewById(R.id.et_food) // ?
			val count: EditText = view.findViewById(R.id.etn_count) // ?
			orders.add(view)
			orders.add(Space(this))
			binding.vLayOrders.addView(view)
		}



	}
}