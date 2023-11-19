package com.example.learningapp

import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.learningapp.databinding.ActivityDetailBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DetailActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding=ActivityDetailBinding.inflate(layoutInflater)
		setContentView(binding.root)
		var arguments=intent.extras
		if(arguments!=null){
			var orders=arguments.getString("orders")
			var typeToken=object : TypeToken<List<Food>>(){}.type
			var foods=Gson().fromJson<List<Food>>(orders,typeToken)
			val tableLayout = binding.table
			for (i in foods) {
				val tableRow = TableRow(this)
				val textView1 = TextView(this)
				textView1.text = i.Name
				textView1.textSize=20f
				textView1.gravity=Gravity.CENTER
				tableRow.addView(
					textView1, TableRow.LayoutParams(
						TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f
					)
				)
				val textView2 = TextView(this)
				textView2.text = i.Price.toString()
				textView2.textSize=20f
				textView2.gravity=Gravity.CENTER
				tableRow.addView(
					textView2, TableRow.LayoutParams(
						TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f
					)
				)
				val textView3 = TextView(this)
				textView3.text = i.Couantity.toString()
				textView3.textSize=20f
				textView3.gravity=Gravity.CENTER
				tableRow.addView(
					textView3, TableRow.LayoutParams(
						TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 0.5f
					)
				)
				tableLayout.addView(tableRow)
			}
		}
	}
}