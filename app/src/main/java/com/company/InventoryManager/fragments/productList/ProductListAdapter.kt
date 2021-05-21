package com.company.inventoryManager.fragments.productList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.inventoryManager.databinding.ListItemProductBinding
import com.company.inventoryManager.modell.Product

class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    var data = listOf<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        );    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data.elementAt(position).let {
            holder.bind(it, data.size)
        }
    }

    override fun getItemCount(): Int {
        return data.size;
    }

    class ViewHolder(val binding: ListItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(it: Product, size: Int) {
            binding.product = it;
            binding.executePendingBindings();
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context);

                var binding = ListItemProductBinding.inflate(layoutInflater, parent, false);
                /*binding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                );*/
                return ViewHolder(
                    binding
                );
            }
        }
    }


}