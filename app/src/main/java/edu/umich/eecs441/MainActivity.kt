package edu.umich.eecs441

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley.newRequestQueue
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private var chattArrayList: ArrayList<Chatt?>? = null
    private var chattAdapter: ChattAdapter? = null

    private fun refreshTimeline() {
        chattAdapter!!.clear()
        val queue = newRequestQueue(this)
        val url = "http://3.16.22.144/getchatts/"

        val getRequest = JsonObjectRequest(url,null,
            Response.Listener { response ->
                try {
                    val array = response.getJSONArray("chatts")
                    for (i in 0 until array.length()) {
                        val username = array.getJSONArray(i).getString(0)
                        val message = array.getJSONArray(i).getString(1)
                        val timestamp = array.getJSONArray(i).getString(2)
                        chattAdapter!!.add(Chatt(username, message, timestamp))
                    }
                } catch (e: JSONException){

                }
                refreshContainer.isRefreshing = false
            },
            Response.ErrorListener { refreshContainer.isRefreshing = false }
        )

        queue.add(getRequest)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chattArrayList = ArrayList()
        chattAdapter = ChattAdapter(this, chattArrayList)
        chattListView.setAdapter(chattAdapter)

        //setup refresh container here later ??
        refreshContainer.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { refreshTimeline() })

        refreshTimeline()
    }

    fun postChatt(view: View?) {
        val intent = Intent(this, PostActivity::class.java)
        startActivity(intent)
    }
}
