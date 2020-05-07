package edu.umich.eecs441

import android.net.sip.SipSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley.newRequestQueue
import kotlinx.android.synthetic.main.chatt_item.*
import org.json.JSONObject
import javax.xml.transform.ErrorListener

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
    }

    fun submitChatt(view: View?){
        val queue = newRequestQueue(this)
        val url = "http://3.16.22.144/addchatt/"
        val username = usernameTextView.text.toString()
        val message = messageTextView.text.toString()
        val params = mapOf(
            "username" to username,
            "message" to message
        )

        val postRequest = JsonObjectRequest(url, JSONObject(params),
            Response.Listener { finish() }, Response.ErrorListener { finish() })

        queue.add(postRequest)
    }
}
