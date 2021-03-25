package com.nikasov.intervalrepeatingmethod.ui.adapter.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil

abstract class BaseAdapter<Model, ItemBinding : ViewDataBinding>: RecyclerView.Adapter<BaseAdapter<Model, ItemBinding>.BaseViewHolder>() {

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
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        items[position]?.let { model ->
            bind(holder.binding, model, position)
        }
    }

    inner class BaseViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    protected abstract fun bind(binding: ItemBinding, model: Model, position: Int)

}
