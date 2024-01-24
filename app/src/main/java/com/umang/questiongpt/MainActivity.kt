package com.umang.questiongpt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonObject
import com.umang.questiongpt.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {

            val input = binding.inputEdit.text.toString()

            var chatGptInterface = RetrofitClient.getInstance().create(ChatGptInterface::class.java)


            var call: Call<JsonObject> = chatGptInterface.getResponse(input)

            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    Log.d("GPT", response.body().toString())
                    Toast.makeText(
                        it.context,
                        "result: " + response.body().toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.outputText.text = response.body().toString()
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("GPT Error", t.toString())!!
                }
            })
        }
    }
}