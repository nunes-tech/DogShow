package com.example.dogshow.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.example.dogshow.R
import com.example.dogshow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    private lateinit var menu: Menu


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        abrirFragment( CaesFragment() )

        inicializarMenu()

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
                        R.id.itemGatos -> {
                            abrirFragment( GatosFragment() )
                        }

                        R.id.itemCaes -> {
                            abrirFragment(CaesFragment())
                        }
                    }
                    return true
                }

            }
        )
    }

}