package by.andersen.dobrov.newsapi.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import by.andersen.dobrov.newsapi.R
import by.andersen.dobrov.newsapi.base.BaseFragment
import by.andersen.dobrov.newsapi.databinding.NewsListFragmentBinding
import by.andersen.dobrov.newsapi.domain.model.News
import by.andersen.dobrov.newsapi.presentation.list.adapter.NewsAdapter
import by.andersen.dobrov.newsapi.presentation.list.dto.RequestDTO
import by.andersen.dobrov.newsapi.util.DateFormatter
import by.andersen.dobrov.newsapi.util.ViewState
import by.andersen.dobrov.newsapi.util.fragment.makeToast
import com.ethanhua.skeleton.Skeleton
import kz.alfabank.alfabusiness.core.ui.skeleton.DefaultSkeletonScreen
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

private const val SKELETON_COUNT = 1

class NewsListFragment : BaseFragment(
    contentLayoutId = R.layout.news_list_fragment,
    toolbarRes = R.id.news_list_toolbar
) {

    private var _binding: NewsListFragmentBinding? = null
    private val binding get() = _binding!!

    private val newsRecyclerView get() = binding.newsRv
    private val newsSearch get() = binding.search
    private val dateFormatter: DateFormatter by inject()

    private val today get() = dateFormatter
    private val viewModel by sharedViewModel<NewsViewModel>()

    //    private lateinit var skeletonScreen: SkeletonScreen
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = NewsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        startObservingVM()
        viewModel.onStart(getRequestDTO())

    }

    private fun startObservingVM() {
        viewModel.viewState.observe(viewLifecycleOwner, {
            loadNews(it)
        })
    }

    private fun loadNews(state: ViewState<List<News.Article>>) = when (state) {
        is ViewState.Loading -> null
        is ViewState.Data -> onDataStateUpdate(state)
        is ViewState.Error -> {

            state.error.message?.let { makeToast(it) }
        }
    }

    private fun onDataStateUpdate(
        state: ViewState.Data<List<News.Article>>,
    ) {

        newsAdapter = NewsAdapter(state.data)
    }

    private fun getRequestDTO() = RequestDTO(
        query = newsSearch.text.toString(),
        from = today.toString()
    )

    private fun initRecycler() {
        newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = newsAdapter
        }
//        skeletonScreen = DefaultSkeletonScreen.RecyclerSkeleton(
//            Skeleton.bind(newsRecyclerView)
//                .adapter(newsAdapter)
//                .load(R.layout.item_news_rv)
//                .count(SKELETON_COUNT)
//        )
    }
}