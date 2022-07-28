package com.example.uas_pm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.uas_pm.databinding.ActivityDetail2Binding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetail2Binding
    private var myData:ResponsePost.ResponsePostItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        myData = intent.getParcelableExtra<ResponsePost.ResponsePostItem>("data")
myData?.apply {
    binding.title.text = title
    binding.des.text = body
}
        binding.btnEdit.setOnClickListener {
            Intent(this,EditActivity::class.java).apply {
                putExtra("data",myData)
                startActivity(this)
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detil,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnDelete -> {
                ApiModule.service.deletePost(myData?.id ?: 0)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe ({
                        Toast.makeText(this, "delete post berhasil", Toast.LENGTH_SHORT).show()
                        finish()
                    },{
                        Log.e("main",it.message.toString())
                    })
            }
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}