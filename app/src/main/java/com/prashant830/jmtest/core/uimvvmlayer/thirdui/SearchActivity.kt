package com.prashant830.jmtest.core.uimvvmlayer.thirdui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.prashant830.jmtest.R
import com.prashant830.jmtest.core.dblayer.Entity
import com.prashant830.jmtest.core.uimvvmlayer.secondui.CatDetailIcoViewModel
import com.prashant830.jmtest.databinding.ActivitySearchBinding
import kotlinx.coroutines.*

class SearchActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding
    lateinit var myAdapter: SerachRecycle
    lateinit var myLayoutManager: LinearLayoutManager
    lateinit var src : String
    private val searchViewModel: SearchViewModel by viewModels()
    private val dbViewModel: CatDetailIcoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_search)

        // recyclerView declaration
        binding.idSerchRV.setHasFixedSize(true)
        myLayoutManager = LinearLayoutManager(this)
        binding.idSerchRV.layoutManager = myLayoutManager

        binding.SearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                src = query.toString()

                CoroutineScope(Dispatchers.Main).launch {
                     fectchResponse(src)
                }
                    return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                src = newText.toString()
                CoroutineScope(Dispatchers.Main).launch {
                    fectchResponse(src)
                }
                return false
            }

        })

    }

    private suspend fun fectchResponse(src: String) {


        val map = mapOf("query" to src, "count" to 10)

        binding.pro2.visibility = View.VISIBLE
        val icon = searchViewModel.fetchApitwo(map as Map<String, String>)!!.icons

        for (i in icon) {
            val format = i.raster_sizes[6].formats

            var entity = Entity(
                format[0].preview_url,
                i.icon_id,
                i.tags.toString(),
                i.is_premium
            )
            dbViewModel.insert(this,entity)
        }

        binding.pro2.visibility = View.GONE

        myAdapter = SerachRecycle( this,icon)
        myAdapter.notifyDataSetChanged()
        binding.idSerchRV.adapter = myAdapter
        myAdapter.setOnItemClickListener(object : SerachRecycle.onItemClickListener {
            override fun onItemClick(position: Int) {
                val v = icon[position].is_premium.toString()
                if (v == "true") {
                    Toast.makeText(
                        baseContext,
                        "You Can't have permission for download Images Because it's have premium",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        baseContext,
                        "You have a permission for download Images Because it's have download option",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
          })
        }
    }
