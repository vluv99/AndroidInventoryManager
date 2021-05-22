package com.company.inventoryManager.fragments.productAdd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.company.inventoryManager.R
import com.company.inventoryManager.databinding.FragmentAddProductBinding
import com.company.inventoryManager.fragments.productList.FragmentProductListDirections
import com.company.inventoryManager.fragments.productView.FragmentProductViewArgs
import com.company.inventoryManager.fragments.productView.ProductViewViewModel
import com.company.inventoryManager.fragments.productView.ProductViewViewModelFactory

class FragmentAddProduct : Fragment() {

    private val viewModel: ProductAddViewModel by viewModels(); //handles lifecycle stuff
    lateinit var binding: FragmentAddProductBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddProductBinding.inflate(inflater, container, false);

        val spinner: Spinner = binding.spinner
        // Create an ArrayAdapter using the string array and a default spinner layout
        this.context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.status_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }

        binding.addProductButton.setOnClickListener {
            viewModel.submit().addOnSuccessListener {
                var navController = findNavController();
                navController.popBackStack()
                //navController.navigate(FragmentProductListDirections.actionFragmentProductListToFragmentAddProduct())
            };
        }

        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState);

        binding.viewModel = viewModel;
    }
}