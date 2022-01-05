package com.delaroystudios.movieapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.delaroystudios.movieapp.BuildConfig
import com.delaroystudios.movieapp.R
import com.delaroystudios.movieapp.data.model.Movie
import com.delaroystudios.movieapp.databinding.ActivityMainBinding
import com.delaroystudios.movieapp.ui.home.adapter.HomeAdpater
import com.delaroystudios.movieapp.ui.home.adapter.RecyclerViewHomeClickListener
import com.delaroystudios.movieapp.ui.home.viewmodel.HomeViewModel
import com.delaroystudios.movieapp.util.Resource
import com.delaroystudios.movieapp.util.contentView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity(), RecyclerViewHomeClickListener {
    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeAdapter: HomeAdpater by lazy { HomeAdpater(this, this@MainActivity) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)//  set status text dark

            getWindow().setStatusBarColor(ContextCompat.getColor(applicationContext,R.color.white))// set status background white

           recyclerView.apply {
               adapter = homeAdapter
           }
        }

        homeViewModel.fetchPopular(BuildConfig.MOVIE_DB_API_TOKEN)
        observeUI()
    }

    private fun observeUI() {
        homeViewModel.moviePopular.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val data = it.data!!.movies
                    homeAdapter.submitList(data!!)
                }
                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    it.message?.let { message ->
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }

        }
    }

    override fun clickOnItem(data: Movie, card: View) {

    }
}