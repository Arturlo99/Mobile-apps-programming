package com.example.gymer

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gymer.databinding.TrainingsListItemBinding
import com.example.gymer.entity.Training

class MyAdapter(private val trainings: ArrayList<Training>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            TrainingsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            with(trainings[position]) {
                binding.tvHeading.text = description
                binding.image.setImageResource(R.drawable.a)
                holder.itemView.setOnClickListener {
                    Toast.makeText(
                        holder.itemView.context, description,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return trainings.size
    }

    inner class MyViewHolder(val binding: TrainingsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}