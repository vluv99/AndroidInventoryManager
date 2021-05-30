package com.company.inventory_manager.fragments.productList

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.company.inventory_manager.MainActivity
import com.company.inventory_manager.R
import com.company.inventory_manager.databinding.FragmentProductListBinding
import com.google.firebase.auth.FirebaseAuth


// generated
class FragmentProductList : Fragment() {

    private val REQUEST_CODE_ASK_PERMISSIONS = 123;

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
    ): View {
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

        binding.cameraButton.setOnClickListener {
            takePicture();
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

    fun takePicture(){
        if (checkUserPermission()) {
            try {
                val intent = Intent("com.google.zxing.client.android.SCAN")
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE") // "PRODUCT_MODE for bar codes
                startActivityForResult(intent, 0)
            } catch (e: Exception) {
                val marketUri: Uri = Uri.parse("market://details?id=com.google.zxing.client.android")
                val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                startActivity(marketIntent)
            }
        }
    }

    fun checkUserPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(this.requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_CODE_ASK_PERMISSIONS
            )
            return false;
        }

        return true;
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //
            } else {
                Toast.makeText(this.requireContext(), "Permission denied!", Toast.LENGTH_SHORT).show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

}