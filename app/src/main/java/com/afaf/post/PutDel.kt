package com.afaf.post

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PutDel: AppCompatActivity() {
    lateinit var newName : EditText
    lateinit var newLocation : EditText
    lateinit var useridn : EditText
    lateinit var updateButton : Button
    lateinit var  deeteButton : Button
    private var userID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.put_del)
        newName = findViewById(R.id.udName)
        newLocation = findViewById(R.id.udLocation)
        useridn = findViewById(R.id.udPk)
        updateButton = findViewById(R.id.btUpdate)
        deeteButton = findViewById(R.id.btDelete)

        updateButton.setOnClickListener{
            if (useridn.text.isNotEmpty() && newLocation.text.isNotEmpty() && newName.text.isNotEmpty()){
                try {
                    userID = useridn.text.toString().toInt()
                    updateUser()

                }catch (e: Exception){

                }
            }

        }

        deeteButton.setOnClickListener{
            try{
                userID = useridn.text.toString().toInt()
                deleteUser()

            }catch (e: Exception){

            }

        }


    }

    private fun updateUser(){
        val apiInterface = APICllient().getClient()?.create(APIInterface::class.java)
        apiInterface!!.updateUser(
            userID,
            dataItem(
                newLocation.text.toString(),
                newName.text.toString(),

                userID

            )
        ).enqueue(object : Callback<dataItem> {

            override fun onResponse(
                call: retrofit2.Call<dataItem>,
                response: Response<dataItem>
            ) {
                Toast.makeText(applicationContext, "User added!", Toast.LENGTH_LONG).show()
                val intent = Intent(this@PutDel, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: retrofit2.Call<dataItem>, t: Throwable) {

            }

        })
    }

    private fun deleteUser(){
        val apiInterface = APICllient().getClient()?.create(APIInterface::class.java)
        apiInterface!!.deleteUser(
            userID)

        .enqueue(object : Callback<Void> {

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(applicationContext, "User added!", Toast.LENGTH_LONG).show()
                val intent = Intent(this@PutDel, MainActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }

        })
    }


}



