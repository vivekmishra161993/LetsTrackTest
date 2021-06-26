package com.app.lets_track_test.news_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.lets_track_test.databinding.FragmentNewsDetailsBinding
import com.app.lets_track_test.news.models.ArticlesItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment(private val articlesItem: ArticlesItem):Fragment() {
    private lateinit var binding:FragmentNewsDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentNewsDetailsBinding.inflate(inflater,container,false)
        binding.model=articlesItem
        return binding.root
    }
}