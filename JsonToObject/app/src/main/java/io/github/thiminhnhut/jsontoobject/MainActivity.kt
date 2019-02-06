package io.github.thiminhnhut.jsontoobject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parse()
    }

    private fun parse() {
        val stringUser = getStringToFile()
        val jsonUser = JSONObject(stringUser)
        val gson = Gson()
        val retMap: HashMap<String, User> = gson.fromJson<HashMap<String, User>>(stringUser,
            object : TypeToken<HashMap<String, User>>(){}.type)
        println("DEBUG = $retMap")
        for (entry in retMap) {
            println("DEBUG = ${entry.key}: ${entry.value}")
        }
    }

    private fun getStringToFile(): String {
        val inputStream = resources.openRawResource(R.raw.user)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuffer()
        var s = bufferedReader.readLine()
        while (s != null) {
            stringBuilder.append(s)
            stringBuilder.append("\n")
            s = bufferedReader.readLine()
        }
        return stringBuilder.toString()
    }
}
