package com.company.inventory_manager.fragments.productList

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.company.inventory_manager.InventoryActivity
import com.company.inventory_manager.MainActivity
import com.company.inventory_manager.R
import com.company.inventory_manager.databinding.FragmentProductListBinding
import com.company.inventory_manager.fragments.productAdd.FragmentAddProduct
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

// generated
class FragmentProductList : Fragment() {

    private val viewModel: ProductListViewModel by viewModels(); //handles lifecycle stuff
    private lateinit var binding: FragmentProductListBinding;
    private lateinit var productListAdapter: ProductListAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
            navController.navigate(FragmentProductListDirections.actionFragmentProductListToFragmentAddProduct(null))
        }

        binding.sortingSetting.setOnCheckedChangeListener { group, checkedId ->
            val id = group.checkedRadioButtonId;
            if (id == R.id.sortByDateRadio){
                viewModel.sortByDate();
            }
            else if (id == R.id.showActiveRadio){
                viewModel.showOnlyActive();
            }
            else if (id == R.id.noneRadio){
                viewModel.showAll();
            }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.log_out -> {
                FirebaseAuth.getInstance().signOut();
                var intent: Intent = Intent(this.context, MainActivity::class.java);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                //intent.putExtra("SECRET_KEY", SECRET_KEY);
                startActivity(intent);
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}