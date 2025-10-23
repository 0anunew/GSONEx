package com.example.gsonex

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gsonex.model.Comment
import com.google.gson.Gson
import com.google.gson.*
import retrofit2.*
import com.example.gsonex.model.JSONPlaceholderAPI
import com.example.gsonex.model.Post
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var textViewResult: TextView
    private lateinit var jsonPlaceHolderApi: JSONPlaceholderAPI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val gson = Gson()
        val familyMembers = arrayOf(
            FamilyMember("Father", 32),
            FamilyMember("Mother", 30),
            FamilyMember("Daughter", 1),
            FamilyMember("Son", 5)
        )

        val address = Address("123 Main St", "Anytown", "CA", "12345")

        // Password is Transient so will not be serialized and displayed in the JSON output
        val employee = Employee(
            address,
            30, "John Doe",
            "askdf@sdahfa.com",
            familyMembers,
            "123-456-7890"
        )

        val employeeString = gson.toJson(employee)
        println("Employee JSON String: $employeeString")

        val employeeObject = gson.fromJson(employeeString, Employee::class.java)
        println("Employee JSON Object: $employeeObject")

        // Only first name and email are excluded from serialize so will be shown
        val gson1 = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
        val employeeString1 = gson1.toJson(employee)
        println("Employee JSON String Expose: $employeeString1")


        textViewResult = findViewById(R.id.text_view_result)
        textViewResult.text = ""

        val baseURLString: String = "https://jsonplaceholder.typicode.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURLString)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonPlaceHolderApi = retrofit.create(JSONPlaceholderAPI::class.java)

        //getPosts()
        getComments(1)

    }

    private fun getPosts() {


        //val getCall = jsonPlaceHolderApi.getPosts()
        //val getCallByUserID = jsonPlaceHolderApi.getPostsByUserId(6)

        val parametersGET = mutableMapOf<String,String>()
        parametersGET.put("userId","6")
        parametersGET.put("_sort","id")
        parametersGET.put("_order","desc")
        val getCallByUserIDAndSortDesc = jsonPlaceHolderApi.getPostsByUserIdAndSort(parametersGET)

        getCallByUserIDAndSortDesc.enqueue(object : Callback<MutableList<Post>> {
            override fun onResponse(
                call: Call<MutableList<Post>>,
                response: Response<MutableList<Post>>
            ) {
                if (response.isSuccessful) {
                    textViewResult.append("Success\n")
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                    val posts = response.body()
                    for (post in posts!!) {

                        println("Post User ID ${post}")

                        var content = ""
                        content += "ID: ${post.id}\n"
                        content += "User ID: ${post.userID}\n"
                        content += "Title: ${post.title}\n"
                        content += "Text: ${post.text}\n\n"

                        textViewResult.append(content)

                    }
                }
            }

            override fun onFailure(
                call: Call<MutableList<Post>?>,
                t: Throwable
            ) {
                Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getComments(postIDComments: Int) {

        if (postIDComments < 0) {
            Toast.makeText(this@MainActivity, "Invalid Post ID", Toast.LENGTH_SHORT).show()
            return
        }


        //val getCall = jsonPlaceHolderApi.getComments(postIDComments)
        val getURL = "posts/$postIDComments/comments"
        val getCall = jsonPlaceHolderApi.getComments(getURL)
        getCall.enqueue(object : Callback<MutableList<Comment>> {
            override fun onResponse(
                call: Call<MutableList<Comment>?>,
                response: Response<MutableList<Comment>?>
            ) {
                if (response.isSuccessful) {

                    textViewResult.text = ""
                    textViewResult.append("Success\n")
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                    val comments = response.body()
                    for (comment in comments!!) {
                        var content = ""
                        content += "ID: ${comment.id}\n"
                        content += "Post ID: ${comment.postId}\n"
                        content += "Name: ${comment.name}\n"
                        content += "Email: ${comment.email}\n"
                        content += "Text: ${comment.bodyText}\n\n"
                        textViewResult.append(content)
                    }
                }
            }

            override fun onFailure(
                call: Call<MutableList<Comment>?>,
                t: Throwable
            ) {
                Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
            }
        })

    }
}