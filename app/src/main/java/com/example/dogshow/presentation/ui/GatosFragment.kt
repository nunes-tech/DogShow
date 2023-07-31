package com.example.dogshow.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogshow.adapter.PetsAdapter
import com.example.dogshow.databinding.FragmentGatosBinding
import com.example.dogshow.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GatosFragment : Fragment() {

    private val adapter by lazy { PetsAdapter() }
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val binding = FragmentGatosBinding.inflate( inflater, container, false)

        binding.rvGatos.adapter = adapter
        binding.rvGatos.layoutManager = GridLayoutManager(container?.context, 2)
        mainViewModel.recuperarImagensCats(10)

        mainViewModel.listaImagensCats.observe(viewLifecycleOwner) { listImagesPet ->
            adapter.atualizarListaImagensPet(listImagesPet)
        }

        binding.rvGatos.addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    @SuppressLint("SuspiciousIndentation")
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        val ePossivelRolarParaBaixo = recyclerView.canScrollVertically(1)

                        if (!ePossivelRolarParaBaixo) {
                            Toast.makeText(
                                container?.context,
                                "Carregando mais imagens...",
                                Toast.LENGTH_SHORT).show()
                            mainViewModel.recuperarImagensCats(10)
                        }
                    }
                }
            )

        return binding.root
    }
}