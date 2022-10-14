package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite.databinding.ActivityDatabaseBinding
import com.example.sqlite.databinding.ActivityInsertBinding

class DatabaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityDatabaseBinding
    internal var helper=DatabaseHelper(this)
    var list1= mutableListOf<Employee>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewAll()
        val recyclerView: RecyclerView =binding.recyclerView
        val adapter=EmployeeAdapter(this,R.layout.employee,list1)
        recyclerView.adapter=adapter
      binding.btnRefresh.setOnClickListener{
            viewAll()
            adapter.notifyDataSetChanged()
        }
    }
    private fun viewAll()
    {
            list1.clear()
            val res = helper.allData
            if (res.count == 0)
            {
                Toast.makeText(this, "No records :(", Toast.LENGTH_LONG).show()
            }
            while (res.moveToNext())
            {
                list1.add(Employee(res.getString(0),
                                  res.getString(1),
                                  res.getString(2),
                                  res.getString(3),
                                  res.getString(4),
                                  res.getString(5)
                )
                )
            }

    }
}