package com.example.dogshow.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogshow.R
import com.example.dogshow.adapter.PetsAdapter
import com.example.dogshow.databinding.FragmentGatosBinding
import com.example.dogshow.presentation.viewmodel.GatosViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GatosFragment : Fragment() {

    @Inject lateinit var adapter: PetsAdapter
    lateinit var gatosViewModel: GatosViewModel
    private lateinit var onItemListener: OnItemListener

    fun setListener(listener: OnItemListener){
        this.onItemListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        gatosViewModel = ViewModelProvider(this)[GatosViewModel::class.java]
        val binding = FragmentGatosBinding.inflate( inflater, container, false)

        binding.rvGatos.adapter = adapter
        binding.rvGatos.layoutManager = GridLayoutManager(container?.context, 2)
        gatosViewModel.recuperarImagensCats(10)

        gatosViewModel.listaImagensCats.observe(viewLifecycleOwner) { listImagesPet ->
            adapter.atualizarListaImagensPet( listImagesPet )
        }

        binding.rvGatos.addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    @SuppressLint("SuspiciousIndentation")
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        val ePossivelRolarParaBaixo = recyclerView.canScrollVertically(1)

                        if (!ePossivelRolarParaBaixo) {
                            gatosViewModel.recuperarImagensCats(10)
                            Toast.makeText(
                                container?.context,
                                "Carregando mais imagens...",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_principal, menu)

        menu.findItem(R.id.itemCaes).isVisible = true
        menu.findItem(R.id.itemGatos).isVisible = false

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {

        when(menuItem.itemId) {

            R.id.itemCaes -> {
                onItemListener.onItemClick(1)
            }

            R.id.itemGatos -> {
                onItemListener.onItemClick(2)
            }

        }

        return super.onOptionsItemSelected(menuItem)
    }

}