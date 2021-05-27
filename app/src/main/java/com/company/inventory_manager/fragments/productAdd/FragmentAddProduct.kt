package com.company.inventory_manager.fragments.productAdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.company.inventory_manager.R
import com.company.inventory_manager.databinding.FragmentAddProductBinding


class FragmentAddProduct : Fragment() {

    val args: FragmentAddProductArgs by navArgs()
    private val viewModel: ProductAddViewModel by viewModels{ ProductAddViewModelFactory(args.productId) }; //handles lifecycle stuff
    lateinit var binding: FragmentAddProductBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.getWindow()
            ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
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

        binding.lifecycleOwner = this;
        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState);

        binding.viewModel = viewModel;
    }
}