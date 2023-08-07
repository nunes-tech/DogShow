package com.example.dogshow.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dogshow.R
import com.example.dogshow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemListener {
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

   private val caesFragment by lazy { CaesFragment() }
   private val gatosFragment by lazy { GatosFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        abrirFragment( caesFragment )
        caesFragment.setListener(this)

    }

    private fun abrirFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcvConteudo, fragment )
            .commit()
    }

    /*private fun inicializarMenu() {
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
    }*/

    override fun onItemClick(item: Int) {
        if (item == 1) {
            abrirFragment(caesFragment)
            caesFragment.setListener(this)
        } else if(item == 2) {
            abrirFragment(gatosFragment)
            gatosFragment.setListener(this)
        }
    }

}