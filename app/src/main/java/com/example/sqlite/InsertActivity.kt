package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqlite.databinding.ActivityInsertBinding
import com.example.sqlite.databinding.ActivityMainBinding

class InsertActivity : AppCompatActivity() {
    internal var helper=DatabaseHelper(this)
    lateinit var binding:ActivityInsertBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnInsert.setOnClickListener {
            helper.insertData(binding.edFirstname.text.toString().trim(),
                binding.edLastname.text.toString().trim(),
                binding.edAge.text.toString().trim(),
                binding.edAddress.text.toString().trim(),
                binding.edDepartment.text.toString().trim())
            Toast.makeText(this,"Inserted",Toast.LENGTH_LONG).show()
        }
    }
}