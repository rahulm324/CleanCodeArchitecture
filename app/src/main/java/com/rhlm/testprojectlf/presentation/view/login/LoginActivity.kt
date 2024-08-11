package com.rhlm.testprojectlf.presentation.view.login

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rhlm.testprojectlf.R
import com.rhlm.testprojectlf.databinding.ActivityLoginBinding
import com.rhlm.testprojectlf.presentation.view.MainActivity

class LoginActivity : AppCompatActivity() {
    private val tag = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.appCompatButton.setOnClickListener {
                Log.i("TAG", "onCreate: ")
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))

        }
    }
}