package com.company.inventoryManager.fragments.productList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.inventoryManager.R
import com.company.inventoryManager.databinding.FragmentProductListBinding
import com.company.inventoryManager.modell.Product

// generated
class FragmentProductList : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding = FragmentProductListBinding.inflate(inflater, container, false);
        var productListAdapter = ProductListAdapter();

        binding.recycleView.adapter = productListAdapter;
        /*productListAdapter.data = listOf(Product("1",null, "hellooooooooooooooooooooooooooooooooooooo",false, false,
            "name", null, null, null, null, null));*/

        return binding.root;
    }
}