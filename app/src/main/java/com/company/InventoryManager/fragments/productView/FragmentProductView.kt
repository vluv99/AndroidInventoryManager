package com.company.inventoryManager.fragments.productView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.company.inventoryManager.R
import com.company.inventoryManager.databinding.FragmentProductListBinding
import com.company.inventoryManager.databinding.FragmentProductViewBinding
import com.company.inventoryManager.fragments.productList.ProductListAdapter
import com.company.inventoryManager.fragments.productList.ProductListViewModel

class FragmentProductView : Fragment() {

    private val viewModel: ProductViewViewModel by viewModels(); //handles lifecycle stuff
    private lateinit var binding: FragmentProductViewBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductViewBinding.inflate(inflater, container, false);
        binding.lifecycleOwner = this;

        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState);

        binding.viewModel = viewModel;
    }
}