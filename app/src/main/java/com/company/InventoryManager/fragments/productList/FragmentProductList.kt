package com.company.inventoryManager.fragments.productList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.company.inventoryManager.databinding.FragmentProductListBinding
import com.company.inventoryManager.fragments.productAdd.FragmentAddProduct

// generated
class FragmentProductList : Fragment() {

    private val viewModel: ProductListViewModel by viewModels(); //handles lifecycle stuff
    private lateinit var binding: FragmentProductListBinding;
    private lateinit var productListAdapter: ProductListAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductListBinding.inflate(inflater, container, false);
        binding.lifecycleOwner = this;
        productListAdapter = ProductListAdapter(ProductListAdapter.ProductListener {
            var navController = findNavController();
            navController.navigate(FragmentProductListDirections.actionFragmentProductListToFragmentProductView(it))
        });

        binding.recycleView.adapter = productListAdapter;

        binding.addButton.setOnClickListener {
            var navController = findNavController();
            navController.navigate(FragmentProductListDirections.actionFragmentProductListToFragmentAddProduct())
        }

        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.products.observe(this.viewLifecycleOwner, Observer { product ->
            // Update the UI, in this case, a TextView.
            productListAdapter.data = product;
        })

        viewModel.products.value?.let {
            productListAdapter.data = it;
        }
    }


}