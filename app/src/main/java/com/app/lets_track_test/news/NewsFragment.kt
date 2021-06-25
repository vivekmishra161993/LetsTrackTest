package com.app.lets_track_test.news

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.lets_track_test.databinding.FragmentNewsBinding
import com.app.lets_track_test.news.models.ArticlesItem
import com.app.lets_track_test.utils.DialogUtility
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var binding: FragmentNewsBinding
    private lateinit var dialogUtility: DialogUtility
    private var newsList = ArrayList<ArticlesItem>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClasses()
        observeNewsResponse()
        observeNewsFromDbResponse()
        dialogUtility.showProgressDialog(requireContext())
        viewModel.getNewsFromDb()
    }

    private fun initClasses() {
        dialogUtility = DialogUtility.getInstance()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

    }

    private fun observeNewsResponse() {
        viewModel.newsResponse.observe(viewLifecycleOwner, {
            dialogUtility.hideProgressDialog()
            if (it.first == 200) {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                it.second?.let { list ->
                    newsList.clear()
                    newsList.addAll(list)

                    setAdapter(newsList)
                    saveImagesToStorage()

                }
            } else {
                Toast.makeText(requireContext(), "Fail", Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun observeNewsFromDbResponse() {
        viewModel.dbNewsResponse.observe(viewLifecycleOwner, {
            dialogUtility.hideProgressDialog()
            newsList.clear()
            newsList.addAll(it)
            if (newsList.size == 0) {
                if (viewModel.isOnline()) {

                    dialogUtility.showProgressDialog(requireContext())
                    viewModel.getNews()
                } else {
                    Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT)
                            .show()

                }
            } else {
                setAdapter(newsList)
            }

        })
    }

    private fun setAdapter(newsList: ArrayList<ArticlesItem>) {
        val newsAdapter = NewsAdapter(newsList)
        binding.rvNews.adapter = newsAdapter
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
        newsAdapter.notifyDataSetChanged()

    }

    private fun saveImagesToStorage() {
        newsList.forEach { item ->
            Glide.with(requireContext())
                    .asBitmap()
                    .load(item.urlToImage)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            val imagePath = saveImage(resource)
                            item.img_path = imagePath
                            viewModel.saveNewsToDb(item)

                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }

                    })


        }
    }

    private fun saveImage(image: Bitmap): String? {
        var savedImagePath: String? = null
        val imageFileName = "IMG_" + System.currentTimeMillis() + ".jpg"
        val storageDir = File(requireContext().filesDir.absolutePath + "/news_images")
        var success = true
        if (!storageDir.exists()) {
            success = storageDir.mkdirs()
        }
        if (success) {
            val imageFile = File(storageDir, imageFileName)
            savedImagePath = imageFile.absolutePath
            try {
                val fOut: OutputStream = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return savedImagePath
    }


}