package com.prashant830.jmtest.core.uimvvmlayer.secondui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.prashant830.jmtest.R
import com.prashant830.jmtest.core.dblayer.Entity
import com.prashant830.jmtest.core.uimvvmlayer.thirdui.SearchActivity
import com.prashant830.jmtest.databinding.ActivityCategoryIcoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryIcoActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryIcoBinding
    lateinit var myAdapter: CatDetailIconRecycle
    lateinit var myLayoutManager: LinearLayoutManager
    private val catDetailIcoViewModel: CatDetailIcoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_category_ico)


        binding.idSteIconRV.setHasFixedSize(true)
        myLayoutManager = LinearLayoutManager(this)
        binding.idSteIconRV.layoutManager = myLayoutManager


        CoroutineScope(Dispatchers.Main).launch{
            setIcons()
        }
    }

    fun fechData(view: View) {
        binding.apifetch.visibility = View.GONE;
        CoroutineScope(Dispatchers.Main).launch{
            fectchResponse()
        }
    }

    private suspend fun fectchResponse() {

        var icon = catDetailIcoViewModel.fetchApi()

        var list = icon!!.icons

        for (i in list) {
            val format = i.raster_sizes[6].formats

            var entity = Entity(
                format[0].preview_url,
                i.icon_id,
                i.tags.toString(),
                i.is_premium
            )
            catDetailIcoViewModel.insert(this, entity)
        }
    }

    private suspend fun setIcons() {
        catDetailIcoViewModel.getIconDetail(applicationContext)?.collect{
                entity ->   myAdapter = CatDetailIconRecycle( this,entity)
            myAdapter.notifyDataSetChanged()
            binding.idSteIconRV.adapter = myAdapter
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {

            R.id.icon-> {
                intent = Intent(applicationContext, SearchActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}