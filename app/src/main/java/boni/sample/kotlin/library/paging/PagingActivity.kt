package boni.sample.kotlin.library.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import boni.sample.java.R
import kotlinx.android.synthetic.main.activity_paging_kotlin.*

class PagingActivity : AppCompatActivity() {
    private val viewModel by viewModels<PagingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging_kotlin)

        // Create adapter for the RecyclerView
        val adapter = PagingAdapter()
        pagingList.adapter = adapter

        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        // when the list changes
        viewModel.allPaging.observe(this, Observer(adapter::submitList))

        initAddButtonListener()
        initSwipeToDelete()
    }

    private fun addPaging() {
        val newPaging = inputText.text.trim()
        if (newPaging.isNotEmpty()) {
            viewModel.insert(newPaging)
            inputText.setText("")
        }
    }

    private fun initAddButtonListener() {
        addButton.setOnClickListener {
            addPaging()
        }

        // when the user taps the "Done" button in the on screen keyboard, save the item.
        inputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addPaging()
                return@setOnEditorActionListener true
            }
            false // action that isn't DONE occurred - ignore
        }

        // When the user clicks on the button, or presses enter, save the item.
        inputText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addPaging()
                return@setOnKeyListener true
            }
            false // action that isn't DOWN or ENTER occurred - ignore
        }
    }

    private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                    makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = false

            // When an item is swiped, remove the item via the view model. The list item will be
            // automatically removed in response, because the adapter is observing the live list.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as PagingViewHolder).paging?.let {
                    viewModel.remove(it)
                }
            }
        }).attachToRecyclerView(pagingList)
    }
}
