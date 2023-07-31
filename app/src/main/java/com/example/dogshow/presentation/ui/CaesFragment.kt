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
import com.example.dogshow.databinding.FragmentCaesBinding
import com.example.dogshow.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CaesFragment : Fragment() {

    @Inject lateinit var adapter: PetsAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val binding = FragmentCaesBinding.inflate( inflater, container, false)

        binding.rvCaes.adapter = adapter
        binding.rvCaes.layoutManager = GridLayoutManager(container?.context, 2)
        mainViewModel.recuperarImagensDogs(10)

        mainViewModel.listaImagensDogs.observe(viewLifecycleOwner) { listImagesPet ->
            adapter.atualizarListaImagensPet(listImagesPet)
        }

        binding.rvCaes.addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    @SuppressLint("SuspiciousIndentation")
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        val ePossivelRolarParaBaixo = recyclerView.canScrollVertically(1)

                        if (!ePossivelRolarParaBaixo) {
                            mainViewModel.recuperarImagensDogs(10)
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
}