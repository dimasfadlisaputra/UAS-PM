package com.example.uas_pm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uas_pm.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myAdapter = MainAdapter({
            Intent(this, DetailActivity::class.java).apply {
                putExtra("data", it)
                startActivity(this)
            }
        })

        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = myAdapter

        binding.btnAdd.setOnClickListener {
            Intent(this, EditActivity::class.java).apply {
                startActivity(this)
            }
        }

        ApiModule.service.getPost()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                data.value = it
            }, {
                Log.e("main", it.message.toString())
            })
    }

    override fun onResume() {
        super.onResume()
        data.observe(this) {
            myAdapter.setData(it)
        }
    }

    companion object {
        val data = MutableLiveData<ResponsePost>()
    }
}