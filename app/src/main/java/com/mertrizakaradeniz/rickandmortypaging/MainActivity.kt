package com.mertrizakaradeniz.rickandmortypaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mertrizakaradeniz.rickandmortypaging.adapter.RickMortyPagedAdapter
import com.mertrizakaradeniz.rickandmortypaging.databinding.ActivityMainBinding
import com.mertrizakaradeniz.rickandmortypaging.viewmodel.RickMortyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var characterAdapter: RickMortyPagedAdapter
    private val viewModel: RickMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRv()
        loadData()
    }

    private fun loadData() {

        lifecycleScope.launch {
            viewModel.listData.collect { pagingData ->
                characterAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRv() {
        characterAdapter = RickMortyPagedAdapter()
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            adapter = characterAdapter
            setHasFixedSize(true)
        }
    }
}