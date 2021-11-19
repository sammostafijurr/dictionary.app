package com.example.dictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private val key = "Word_Definition"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val queue = Volley.newRequestQueue(this)
        find_button.setOnClickListener {
            val word = edit_text.text
            val apikey = "88923f69-c088-45b0-ac01-46fd0c1e9e40"
         val url = "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apikey"
            val stringRequest = StringRequest(Request.Method.GET,url, { response ->
                try {
                    extractDefinitionFromJson(response)
                } catch (exception:Exception){
                    exception.printStackTrace()
                }


            },
                { error ->
                    Toast.makeText(this,  error.message, Toast.LENGTH_SHORT).show()

                }
                    )
            queue.add(stringRequest)
        }
    }
    private fun extractDefinitionFromJson(response : String ){
        val jsonArray = JSONArray (response)
            val firstIndex = jsonArray.getJSONObject(0)
        val getShortDefinition = firstIndex.getJSONArray("shortdef")
        val firstShortDifinition = getShortDefinition.get(0)
       val intent = Intent(this,DefinitionActivity ::class.java)
        intent.putExtra(key,firstShortDifinition.toString())
        startActivity(intent)
    }
}