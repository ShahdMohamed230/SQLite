package com.example.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDatabaseDb.setOnClickListener {
            val intent=Intent(this,DatabaseActivity::class.java)
            startActivity(intent)
        }
        binding.btnInsertDb.setOnClickListener {
            val intent=Intent(this,InsertActivity::class.java)
            startActivity(intent)
        }
    }

}