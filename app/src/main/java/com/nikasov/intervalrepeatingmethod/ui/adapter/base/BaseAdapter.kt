package com.nikasov.intervalrepeatingmethod.ui.adapter.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.nikasov.intervalrepeatingmethod.R

abstract class BaseAdapter<Model, ItemBinding : ViewDataBinding>(val context: Context) :
    RecyclerView.Adapter<BaseAdapter<Model, ItemBinding>.BaseViewHolder>() {

    protected abstract var differ: AsyncListDiffer<Model>
    protected abstract var layoutId: Int
    protected var onItemClickListener: ((Model) -> Unit)? = null

    protected val callback = object : DiffUtil.ItemCallback<Model>() {
        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    var items: List<Model>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    fun setItemClickListener(listener: ((Model) -> Unit)) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.fragment_add_word,
                null,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        items[position]?.let { model ->
            bind(holder.binding, model, position)
        }
    }

    inner class BaseViewHolder(val binding: ItemBinding) :
        RecyclerView.ViewHolder((binding as ViewDataBinding).root)

    protected abstract fun bind(binding: ItemBinding, model: Model, position: Int)

}
