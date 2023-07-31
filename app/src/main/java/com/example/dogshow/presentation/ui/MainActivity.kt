package com.example.dogshow.presentation.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dogshow.R
import com.example.dogshow.databinding.ActivityMainBinding
import com.example.dogshow.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        abrirFragment( CaesFragment() )

        inicializarMenu()

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

    }

    private fun abrirFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcvConteudo, fragment )
            .commit()
    }

    private fun inicializarMenu() {
        addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(
                        R.menu.menu_principal,
                        menu
                    )
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when(menuItem.itemId) {
                        R.id.itemGatos -> abrirFragment( GatosFragment() )

                        R.id.itemCaes -> abrirFragment( CaesFragment() )
                    }
                    return true
                }

            }
        )
    }

}