package com.afaf.post


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var uName: EditText
    lateinit var ulocation: EditText
    lateinit var addButton: Button
    lateinit var rvAdapter: recyclerAdapter
    lateinit var Data: ArrayList<dataItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        uName = findViewById(R.id.etName)
        ulocation = findViewById(R.id.etLocation)
        recyclerView = findViewById(R.id.rvUsers)
        addButton = findViewById(R.id.btAdd)
        Data = arrayListOf()
        rvAdapter = recyclerAdapter(Data)
        recyclerView.layoutManager = LinearLayoutManager(this)
        gettUser()

        addButton.setOnClickListener {
            addUser()
        }

    }

    private fun gettUser(){
        val apiInterface = APICllient().getClient()?.create(APIInterface::class.java)

        apiInterface?.getUser()?.enqueue(object : Callback<data> {

            override fun onResponse(
                call: retrofit2.Call<data>,
                response: Response<data>
            ) {
                Data = response.body()!!
                recyclerView.adapter = recyclerAdapter(Data)
            }

            override fun onFailure(call: retrofit2.Call<data>, t: Throwable) {

            }
        })
    }

    private fun addUser(){
        val apiInterface = APICllient().getClient()?.create(APIInterface::class.java)
        apiInterface!!.addUser(
            dataItem(
                ulocation.text.toString(),
                uName.text.toString(),
                0
            )
        ).enqueue(object : Callback<dataItem> {

            override fun onResponse(
                call: retrofit2.Call<dataItem>,
                response: Response<dataItem>
            ) {
                Toast.makeText(applicationContext, "User added!", Toast.LENGTH_LONG).show()
                recreate()
            }

            override fun onFailure(call: retrofit2.Call<dataItem>, t: Throwable) {

            }

        })
    }


}