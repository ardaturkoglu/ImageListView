package com.example.imagelistview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imagelistview.databinding.DemoPageBinding
import com.example.imagelistview.view.presentation.ImageListView


class DemoPage : AppCompatActivity() {
    private lateinit var binding: DemoPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Set binding
        binding = DemoPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get imageListView
        val imageListView = getImageListView()

        imageListView.apply {
            //Set image list from local json.
            setImageListFromJson("image-list.json")
            //Call submit list.
            setSubmitButtonOnClickListener(this)
        }
    }

    private fun setSubmitButtonOnClickListener (imageListView: ImageListView){
        val submitButton = getSubmitButton()
        submitButton.setOnClickListener {
            imageListView.submitImageList()
        }
    }
    private fun getImageListView() = binding.imageListView
    private fun getSubmitButton() = binding.sendRequestFab
}