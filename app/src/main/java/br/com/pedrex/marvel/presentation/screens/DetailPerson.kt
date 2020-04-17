package br.com.pedrex.marvel.presentation.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.pedrex.marvel.R
import br.com.pedrex.marvel.databinding.ActivityDetailBinding
import br.com.pedrex.marvel.presentation.model.Character

class DetailPerson : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_detail
        )

        val character =  intent.getSerializableExtra("model")
        character?.let {
            bindViews(it as Character)
        }
    }

    private fun bindViews(character: Character) {
        binding.apply {
            includeToolbar.btAction.setOnClickListener {
                finish()
            }
            includeToolbar.tvTitle.text = character.name
            this.character = character
        }
    }
}
