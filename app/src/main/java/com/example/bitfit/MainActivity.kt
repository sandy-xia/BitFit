package com.example.bitfit


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.OnLongClickListener
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlist.ItemAdapter


class MainActivity : AppCompatActivity() {

    lateinit var itemList: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submit = findViewById<Button>(R.id.submitButton)
        val inputName = findViewById<EditText>(R.id.foodName)
        val inputCalories = findViewById<EditText>(R.id.foodCalories)

        itemList = ArrayList()

        // Lookup the RecyclerView in activity layout
        val itemsRv = findViewById<RecyclerView>(R.id.itemsRv)

        // Create adapter passing in the list of items
        val adapter = ItemAdapter(itemList)

        // Attach the adapter to the RecyclerView to populate items
        itemsRv.adapter = adapter

        // Set layout manager to position the items
        itemsRv.layoutManager = LinearLayoutManager(this)

        // Fetch the list of items
        submit.setOnClickListener {
            var name = inputName.text.toString()
            var calories = inputCalories.text.toString()
            var currItem = Item(name, calories)
            itemList.add(itemList.lastIndex + 1, currItem)
            closeKeyboard()
            inputName.getText().clear()
            inputCalories.getText().clear()
            adapter.notifyDataSetChanged()
        }

    }


    private fun closeKeyboard() {
        // this will give us the view
        // which is currently focus
        // in this layout
        val view: View? = this.currentFocus

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {

            // now assign the system
            // service to InputMethodManager
            val manager: InputMethodManager = getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            manager
                .hideSoftInputFromWindow(
                    view.getWindowToken(), 0
                )
        }
    }
}
