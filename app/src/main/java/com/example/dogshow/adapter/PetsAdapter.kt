package com.example.dogshow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.dogshow.R
import com.example.dogshow.databinding.RecyclerItemBinding
import com.example.dogshow.domain.model.ImagensPets
import com.squareup.picasso.Picasso

class PetsAdapter : Adapter<PetsAdapter.MyViewHolder>() {

    var lista = listOf<ImagensPets>()
    lateinit var binding : RecyclerItemBinding

    /*fun atualizarListaImagensPet(l : List<ImagemPet>){
        this.lista = l
        notifyDataSetChanged()
    }*/

    fun atualizarListaImagensPet(l : List<ImagensPets>){
        this.lista += l
        notifyDataSetChanged()
    }


    inner class MyViewHolder(
        private val binding: RecyclerItemBinding
    ) : ViewHolder(binding.root){

        fun bind(link : String){
            Picasso.get()
                .load( link )
                .placeholder( R.drawable.image_default )
                .error( R.drawable.image_default ) //Imagem padrão em caso de falha
                .into(binding.imagePet)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from( parent.context )
        binding = RecyclerItemBinding.inflate( layoutInflater )

        return MyViewHolder(
            RecyclerItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val linkImagem = lista[position].link

        if (linkImagem.endsWith(".jpg") || linkImagem.endsWith(".png")) {
            holder.bind( linkImagem )
        }

    }

}