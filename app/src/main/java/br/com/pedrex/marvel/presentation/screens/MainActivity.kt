package br.com.pedrex.marvel.presentation.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pedrex.marvel.R
import br.com.pedrex.marvel.databinding.ActivityMainBinding
import br.com.pedrex.marvel.presentation.model.Character
import br.com.pedrex.marvel.presentation.screens.adapters.ItemAdapter
import br.com.pedrex.marvel.presentation.screens.states.States
import br.com.pedrex.marvel.presentation.screens.viewmodel.CharactersViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var adapterItems: ItemAdapter
    private lateinit var viewModel: CharactersViewModel
    private lateinit var binding: ActivityMainBinding
    private var isLoading: Boolean = false
    private val list: ArrayList<Character> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        bindViews()

        viewModel = CharactersViewModel()

        bindAdapter(binding)

        bindObserverViewModel()

        viewModel.input.populateList()
    }

    private fun bindViews() {
        binding.apply {
            includeToolbar.apply {
                tvTitle.text = "Marvel"
            }
            includeList.also {
                it.isLoading = true
                it.items.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                        if (!isLoading) {
                            if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == list.size - 1) {
                                //bottom of list!
                                loadMore()
                                isLoading = true
                            }
                        }
                    }
                })
            }

        }
    }

    private fun bindObserverViewModel() {
        viewModel.output.also {
            it.selectCarLiveData.observe(this@MainActivity, Observer { item ->
                showDetail(item)
            })
            it.populateCarsLiveData.observe(this@MainActivity, Observer { state ->
                handlerState(state)
            })
        }
    }

    private fun loadMore() {
        viewModel.input.populateList()
    }

    private fun handlerState(state: States) {
        binding.includeList.isLoading = false
        isLoading = false
        when (state) {
            is States.Error -> {
                showError(state.message)
            }
            is States.LoadCharacters -> {
                populateList(state.characters)
            }
        }
    }

    private fun showError(message: String) {
        this.let {
            val snack = Snackbar.make(
                it.findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_INDEFINITE
            )
            snack.setAction("Ok") {
                snack.dismiss()
            }
            snack.show()
        }
    }

    private fun bindAdapter(
        binding: ActivityMainBinding
    ) {
        adapterItems = ItemAdapter(list)
        binding.includeList.items.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterItems
        }
    }

    private fun populateList(list: List<Character>) {
        this.list.addAll(list)
        adapterItems.notifyDataSetChanged()
    }

    private fun showDetail(car: Character) {

    }
}
