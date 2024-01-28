package com.example.onlineshoppingapp.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.example.onlineshoppingapp.R
import com.example.onlineshoppingapp.data.api.ApiClient
import com.example.onlineshoppingapp.data.model.CategoryModel
import com.example.onlineshoppingapp.data.model.DataState
import com.example.onlineshoppingapp.data.repository.search.SearchRepositoryImpl
import com.example.onlineshoppingapp.databinding.FragmentSearchBinding
import com.example.onlineshoppingapp.ui.loadingprogress.LoadingProgressBar
import com.example.onlineshoppingapp.ui.main.search.adapter.CategoryAdapter
import com.example.onlineshoppingapp.ui.main.search.adapter.SearchAdapter
import com.example.onlineshoppingapp.ui.main.search.viewmodel.SearchViewModel
import com.example.onlineshoppingapp.ui.main.search.viewmodel.SearchViewModelFactory

class SearchFragment : Fragment(), CategoryClickListener, SearchView.OnQueryTextListener {

    private lateinit var bnd: FragmentSearchBinding
    private lateinit var loadingProgressBar: LoadingProgressBar
    private val viewModel by viewModels<SearchViewModel> {
        SearchViewModelFactory(
            SearchRepositoryImpl(
                ApiClient.getApiService()
            )
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bnd = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return bnd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        loadingProgressBar = LoadingProgressBar(requireContext())

        viewModel.searchLiveData.observe(viewLifecycleOwner){

            when (it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let { safeData ->

                        val searchAdapter = SearchAdapter(findNavController(), safeData)
                        bnd.rvSearch.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        bnd.rvSearch.setHasFixedSize(true)
                        bnd.rvSearch.adapter = searchAdapter

                    } ?: run {
                        Snackbar.make(bnd.root, getString(R.string.no_data), Snackbar.LENGTH_LONG).show()
                    }
                }
                is DataState.Error -> {
                    loadingProgressBar.hide()
                    Snackbar.make(bnd.root, it.message, Snackbar.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
            }

        }

        viewModel.categoryLiveData.observe(viewLifecycleOwner){

            when (it) {
                is DataState.Success -> {
                    it.data?.let { safeData ->

                        val categoryAdapter = CategoryAdapter(safeData, this)
                        bnd.rvCategory.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        bnd.rvCategory.setHasFixedSize(true)
                        bnd.rvCategory.adapter = categoryAdapter

                    } ?: run {
                        Snackbar.make(bnd.root, getString(R.string.no_data), Snackbar.LENGTH_LONG).show()
                    }
                }
                is DataState.Error -> {
                    Snackbar.make(bnd.root, it.message, Snackbar.LENGTH_LONG).show()
                }
                is DataState.Loading -> {}
            }

        }

        bnd.searchView.setOnQueryTextListener(this)

    }

    private fun searchQuery(query:String?){

        if(query != null && query.length > 2){
            viewModel.searchProducts(true, query.lowercase())
        }else{
            viewModel.searchProducts()
        }

    }

    override fun onClickCategory(category: CategoryModel) {
        clearSearchView()
        viewModel.getProductsByCategoryCheck(category)
    }

    private fun clearSearchView(){
        bnd.searchView.setQuery("", false)
        bnd.searchView.clearFocus()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchQuery(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchQuery(newText)
        return false
    }

}

interface CategoryClickListener{
    fun onClickCategory(category: CategoryModel)
}