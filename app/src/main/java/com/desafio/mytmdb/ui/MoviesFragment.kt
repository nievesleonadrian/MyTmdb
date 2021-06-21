package com.desafio.mytmdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.desafio.mytmdb.common.MyTmdbApplication
import com.desafio.mytmdb.R
import com.desafio.mytmdb.common.mvi.MviUi
import com.desafio.mytmdb.common.ui.AttrsFullPageErrorTemplate
import com.desafio.mytmdb.data.remote.MoviesRestApi
import com.desafio.mytmdb.databinding.FragmentMoviesListBinding
import com.desafio.mytmdb.presentation.MoviesViewModel
import com.desafio.mytmdb.presentation.model.UiMovie
import com.desafio.mytmdb.presentation.states.MoviesUiState
import com.desafio.mytmdb.presentation.userintents.MoviesUserIntent
import com.desafio.mytmdb.presentation.userintents.MoviesUserIntent.RetrySeeMoviesUserIntent
import com.desafio.mytmdb.presentation.userintents.MoviesUserIntent.SeeMoviesInitialUserIntent
import com.desafio.mytmdb.ui.di.DaggerMoviesComponent
import com.desafio.mytmdb.ui.provider.UiMoviesComponentProvider
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MoviesFragment : Fragment(), MviUi<MoviesUserIntent, MoviesUiState> {

    private var binding: FragmentMoviesListBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var componentProvider: UiMoviesComponentProvider

    @Inject
    lateinit var gridMoviesAdapter: GridMoviesAdapter

    private val viewModel: MoviesViewModel by viewModels { viewModelFactory }

    private val retryPublish = PublishSubject.create<RetrySeeMoviesUserIntent>()

    @Inject
    lateinit var api: MoviesRestApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjection()
    }

    private fun setupInjection() {
        DaggerMoviesComponent
            .builder()
            .applicationComponent(MyTmdbApplication.getApplicationComponent())
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        processIntents()
        subscribeToUiStates()
    }

    private fun processIntents() = viewModel.processUserIntents(userIntents())

    override fun userIntents(): Observable<MoviesUserIntent> {
        return Observable.merge(
            Observable.just(SeeMoviesInitialUserIntent),
            retryPublish
        )
    }

    private fun subscribeToUiStates() {
        viewModel.liveData().observe(viewLifecycleOwner, Observer(::renderUiStates))
    }

    override fun renderUiStates(uiState: MoviesUiState) {
        when(uiState) {
            is MoviesUiState.LoadingUiState -> showLoading()
            is MoviesUiState.ErrorUiState -> showErrorTemplate()
            is MoviesUiState.DisplayingMoviesUiState -> displayMoviesList(uiState.movies)
        }
    }

    private fun setupAdapter() {
        binding?.lisMovies?.adapter = gridMoviesAdapter
    }

    private fun showLoading() {
        binding?.loadingView?.visibility = View.VISIBLE
    }

    private fun showErrorTemplate() {
        binding?.loadingView?.visibility = View.GONE
        binding?.templateError?.visibility = View.VISIBLE

        val attrs = AttrsFullPageErrorTemplate(
            title = context?.getString(R.string.error_title).orEmpty(),
            description = context?.getString(R.string.error_description).orEmpty(),
            btnName = context?.getString(R.string.retry).orEmpty(),
            icon = context?.getDrawable(R.drawable.ic_clouderror)
        ){
            retryPublish.onNext(RetrySeeMoviesUserIntent)
        }
        binding?.templateError?.setAttrs(attrs)
    }

    private fun displayMoviesList(movies: List<UiMovie>) {
        binding?.loadingView?.visibility = View.GONE

        gridMoviesAdapter.setData(movies)

        binding?.lisMovies?.visibility = View.VISIBLE

    }
}