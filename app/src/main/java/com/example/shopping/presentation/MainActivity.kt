package com.example.shopping.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.R
import com.example.shopping.databinding.ActivityMainBinding
import com.example.shopping.databinding.ActivityShopItemBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),ShopItemFragment.OnEditingFinshedListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var shopListAdapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }
        binding.buttonAddShopItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else{

                launchFragment(ShopItemFragment.newInstanceAddItem())
            }
        }

    }

    private fun isOnePaneMode(): Boolean {
        return binding.shopItemContainer == null
    }
    private fun launchFragment (fragment: Fragment){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container,fragment)
            .addToBackStack(null)
            .commit()

    }

   override fun onEditingFinished(){
       Toast.makeText(this@MainActivity,"Success", Toast.LENGTH_SHORT).show()
       supportFragmentManager.popBackStack()
    }

    private fun setupRecyclerView() {
        with(binding.recyclerView) {
            shopListAdapter = ShopListAdapter()
            this?.adapter = shopListAdapter
            this?.recycledViewPool?.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            this?.recycledViewPool?.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        binding.recyclerView?.let { setupSwipeListener(it) }
    }

    private fun setupSwipeListener(rvShopList: RecyclerView) {
        val callBack =
            object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                    viewModel.deleteShopItem(item)
                }

            }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setupClickListener() {
        shopListAdapter.onShopItemClickListener = {
            if(isOnePaneMode()) {
            val intent = ShopItemActivity.newIntentEditItem(this, it.id)
            startActivity(intent)
        } else{
            launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
        }
    }
    }

    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changesShopItem(it)
        }
    }
}