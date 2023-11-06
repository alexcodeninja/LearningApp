package com.example.learningapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Space
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.learningapp.databinding.ActivityMainBinding
import com.example.learningapp.databinding.OrderCardBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var orders: MutableList<View>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		orders = mutableListOf()
		binding.fabAdd.setOnClickListener {
			val card_binding: OrderCardBinding = OrderCardBinding.inflate(layoutInflater)
//			val food: EditText = card_binding.etFood
//			val count: EditText = card_binding.etnCount
			card_binding.twSerialNum.text = (orders.size + 1).toString() + "."
			orders.add(card_binding.root)
			binding.vLayOrders.addView(card_binding.root)
		}

		binding.fabRemove.setOnClickListener{
			print(orders.size)
			if (orders.size == 0){
				Toast.makeText(applicationContext, "Нет заказов для удаления", Toast.LENGTH_SHORT).show()
			}
			else {
				binding.vLayOrders.removeView(orders.last())
				orders.removeLast()
			}
		}

		binding.btnOrder.setOnClickListener{
			Toast.makeText(applicationContext, orders.joinToString(), Toast.LENGTH_LONG).show()
		}


	}
}
