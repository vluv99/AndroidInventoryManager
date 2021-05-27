package com.company.inventory_manager.fragments.productView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.company.inventory_manager.R
import com.company.inventory_manager.databinding.FragmentProductListBinding
import com.company.inventory_manager.databinding.FragmentProductViewBinding
import com.company.inventory_manager.fragments.productList.ProductListAdapter
import com.company.inventory_manager.fragments.productList.ProductListViewModel

class FragmentProductView : Fragment() {

    val args: FragmentProductViewArgs by navArgs()
    private val viewModel: ProductViewViewModel by viewModels{ ProductViewViewModelFactory(args.productID) }; //handles lifecycle stuff
    private lateinit var binding: FragmentProductViewBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductViewBinding.inflate(inflater, container, false);
        binding.lifecycleOwner = this;

        binding.deleteButton.setOnClickListener {
            viewModel.delete().addOnSuccessListener {
                var navController = findNavController();
                navController.popBackStack()
                //navController.navigate(FragmentProductListDirections.actionFragmentProductListToFragmentAddProduct())
            };
        }

        binding.UpdateButton.setOnClickListener {
            var navController = findNavController();
            navController.navigate(FragmentProductViewDirections.actionFragmentProductViewToFragmentAddProduct(viewModel.productId));
        }

        return binding.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState);

        binding.viewModel = viewModel;
    }
}