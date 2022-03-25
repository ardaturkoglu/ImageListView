package com.example.imagelistview.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.example.imagelistview.R
import com.example.imagelistview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get imageListView
        val imageListView = getImageListView()
        val submitButton = getSubmitButton()
        imageListView.apply {
            //Add RecyclerView Decoration
            addItemDecoration(DividerItemDecoration(this@MainActivity, VERTICAL))

            //Set image list from local json.
            viewModel.getImageList(this@MainActivity)
            setImageListFromJson(viewModel.imageList)
            submitButton.setOnClickListener {
                showToast(getLoadingTimes())
            }
        }
        viewModel.submitImageListLiveData.observe(this){
            if(it)
                Toast.makeText(this, "Request successful!", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Request unsuccessful :(", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showToast(loadingTimes :List<Long>?){
        viewModel.submitImageList(loadingTimes)
    }
    private fun getImageListView() = binding.imageListView
    private fun getSubmitButton() = binding.sendRequestFab
}