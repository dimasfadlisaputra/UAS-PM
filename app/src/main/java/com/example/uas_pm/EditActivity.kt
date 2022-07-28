package com.example.uas_pm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.example.uas_pm.databinding.ActivityEditBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private var myData:ResponsePost.ResponsePostItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        myData = intent.getParcelableExtra<ResponsePost.ResponsePostItem>("data")
        if(intent.hasExtra("data")){
            myData?.apply {
                binding.title.setText(title)
                binding.des.setText(body)

                binding.btnEdit.setOnClickListener {
                    val title = binding.title.text.toString()
                    val des = binding.des.text.toString()
                    when {
                        title != myData?.title && des != myData?.body -> {
                            val body = BodyPut(des, title, myData?.userId ?: 0, myData?.id ?: 0)
                            ApiModule.service.putPost(body, myData?.id ?: 0)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    Toast.makeText(
                                        this@EditActivity,
                                        "berhasil edit post",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    finish()
                                }, {
                                    Log.e("main", it.message.toString())
                                })
                        }
                        title != myData?.title -> {
                            ApiModule.service.patchPost(hashMapOf("title" to title),id)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    Toast.makeText(
                                        this@EditActivity,
                                        "berhasil edit post",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    finish()
                                }, {
                                    Log.e("main", it.message.toString())
                                })
                        }
                        else -> {
                            ApiModule.service.patchPost(hashMapOf("body" to des),id)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    Toast.makeText(
                                        this@EditActivity,
                                        "berhasil edit post",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    finish()
                                }, {
                                    Log.e("main", it.message.toString())
                                })
                        }
                    }
                }
            }
        }else {
            supportActionBar?.title = "Add Post"
            binding.btnEdit.setOnClickListener {
                val title = binding.title.text.toString()
                val des = binding.des.text.toString()

                val body = BodyPost(des, title, myData?.userId ?: 0)
                ApiModule.service.addPost(body)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Toast.makeText(
                            this@EditActivity,
                            "berhasil Add post",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }, {
                        Log.e("main", it.message.toString())
                    })
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}