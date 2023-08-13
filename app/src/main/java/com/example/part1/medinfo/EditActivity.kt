package com.example.part1.medinfo

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.part1.medinfo.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )

        binding.birthdatelayer.setOnClickListener {
            val listner = OnDateSetListener{ _, year, month, dayofMonTh ->
                binding.birthdateTextView.text = "$year-${month.inc()}-$dayofMonTh"
            }
            DatePickerDialog(
                this,
                listner,
                2000,
                1,
                1
            ).show()
        }

        binding.warningCheckBox.setOnCheckedChangeListener { _, isChecked ->
            binding.warningEditText.isInvisible = isChecked
        }

        binding.warningEditText.isVisible = binding.warningCheckBox.isChecked

        binding.saveButton.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        with(getSharedPreferences("userInformation", Context.MODE_PRIVATE).edit()) {
            putString("name", binding.nameEditText.text.toString())
            putString("bloodType", "")
            putString("emergencyContact", binding.emergencyContactEditText.text.toString())
            putString("birthdate", binding.birthdateTextView.text.toString())
            putString("warning", "")
            apply()
        }

        Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
    }
}