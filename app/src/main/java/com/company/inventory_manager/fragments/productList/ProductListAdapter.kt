package com.company.inventory_manager.fragments.productList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.inventory_manager.databinding.ListItemProductBinding
import com.company.inventory_manager.modell.Product

class ProductListAdapter(val clickListener: ProductListener) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    var data = listOf<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        );
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data.elementAt(position).let {
            holder.bind(it, data.size, clickListener)
        }
    }

    override fun getItemCount(): Int {
        return data.size;
    }

    class ViewHolder(val binding: ListItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, size: Int, clickListener: ProductListener) {
            binding.product = product;
            binding.executePendingBindings();
            binding.root.setOnClickListener {
                clickListener.onClick(product);
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context);

                var binding = ListItemProductBinding.inflate(layoutInflater, parent, false);

                return ViewHolder(
                    binding
                );
            }
        }
    }

    class ProductListener(val clickListener: (name: String) -> Unit) {
        fun onClick(product: Product) = product.id?.let { clickListener(it) }
    }

}