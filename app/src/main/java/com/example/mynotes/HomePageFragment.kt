package com.example.mynotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home_page.*
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import java.lang.Exception
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomePageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomePageFragment : Fragment()  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private  var mainActivity : MainActivity = MainActivity()
    private var layoutManger : RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_page, container, false)
        val menuButton: ImageButton = view.findViewById(R.id.menuButton)
        val floatingButton: FloatingActionButton = view.findViewById(R.id.floatingActionButton)

        floatingButton.setOnClickListener {
            try {

                startActivity(Intent(view.context, create::class.java))
            } catch (e: Exception) {
                Toast.makeText(view.context, e.toString(), Toast.LENGTH_LONG).show()
                Log.d("TAG", "onCreateView", e);
            }
        }



        menuButton.setOnClickListener {
            try {
            } catch (e: Exception) {
                Toast.makeText(view.context, e.toString(), Toast.LENGTH_LONG).show()
                Log.d("TAG", "onCreateView", e);

            }
        }

        // read notes from DB
        val listUser: MutableList<User> = DataBaseHandler(view.context).readData()
        try {
            // display notes in card
            layoutManger = LinearLayoutManager(view.context)
            view.recyclerView.layoutManager = layoutManger
            adapter = RecyclerAdapter(view.context, listUser)
            view.recyclerView.adapter = adapter
        } catch (e: Exception) {
            Toast.makeText(view.context, e.toString(), Toast.LENGTH_LONG).show()
            Log.d("TAG", "onCreateView", e);
        }

        return view

    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomePageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomePageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}