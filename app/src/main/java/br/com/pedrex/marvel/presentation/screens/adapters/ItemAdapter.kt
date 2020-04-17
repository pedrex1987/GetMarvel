package br.com.pedrex.marvel.presentation.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.pedrex.marvel.R
import br.com.pedrex.marvel.databinding.ItemsBinding
import br.com.pedrex.marvel.presentation.model.Character
import br.com.pedrex.marvel.presentation.screens.viewmodel.CharactersViewModel

class ItemAdapter(
    private val items: ArrayList<Character>,
    val viewModel: CharactersViewModel
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.items,
                    parent,
                    false
                )
        )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding?.let {
            val character = items[position]
            it.character = character
            it.viewModel = viewModel
            it.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemsBinding? = DataBindingUtil.bind(view)
    }
}