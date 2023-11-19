package com.example.learningapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Space
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.learningapp.databinding.ActivityMainBinding
import com.example.learningapp.databinding.OrderCardBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
	private lateinit var database: FirebaseDatabase
	private lateinit var binding: ActivityMainBinding
	private lateinit var orders: MutableList<View>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		database = Firebase.database
		orders = mutableListOf()
		binding.btnOrder.visibility = View.GONE
		binding.fabAdd.setOnClickListener {
			val card_binding: OrderCardBinding = OrderCardBinding.inflate(layoutInflater)
			val food: EditText = card_binding.etFood
			val count: EditText = card_binding.etnCount
			val deButton=card_binding.del
			deButton.setOnClickListener{
				binding.vLayOrders.removeView(card_binding.root)
				orders.remove(card_binding.root)
				if(orders.size==0){
					binding.btnOrder.visibility = View.GONE
				}
			}
			card_binding.twSerialNum.text = (orders.size + 1).toString() + "."
			orders.add(card_binding.root)
			if(orders.size!=0){
				binding.btnOrder.visibility = View.VISIBLE
			}
			binding.vLayOrders.addView(card_binding.root)
		}

		binding.btnOrder.setOnClickListener{
			val myRef = database.getReference("food")
			var foods:MutableList<Food> = mutableListOf()
			myRef.addValueEventListener(object: ValueEventListener{
				override fun onDataChange(snapshot: DataSnapshot) {
					for (i in orders) {
						val ord_count: String = (i.findViewById(R.id.etn_count) as EditText).text.toString()
						val ord_foood: String = (i.findViewById(R.id.et_food) as EditText).text.toString()
						if (ord_count.length != 0 || ord_foood.length != 0){
							val name = ord_foood
							val count = ord_count.toInt()
							val price: Double =
								if (snapshot.child(name).getValue() != null) snapshot.child(name)
									.getValue().toString().toDouble() else 0.0
							if (price != 0.0) foods.add(Food(name, count, price))
						} else {
							Toast.makeText(applicationContext, "Форма заказа заполнена неверно.", Toast.LENGTH_LONG).show()
							return
						}
					}
					if (foods.size == orders.size) {
						Toast.makeText(applicationContext, foods.joinToString(), Toast.LENGTH_LONG).show()
						val intent = Intent(applicationContext, DetailActivity::class.java)
						val stringJson = Gson().toJson(foods)
						intent.putExtra("orders",stringJson)
						startActivity(intent)
					} else{
						Snackbar.make(it, "Заказы введены неверно.", Snackbar.LENGTH_LONG).show()
					}

				}
				override fun onCancelled(error: DatabaseError) {

				}
			})
			}
		}


	}
