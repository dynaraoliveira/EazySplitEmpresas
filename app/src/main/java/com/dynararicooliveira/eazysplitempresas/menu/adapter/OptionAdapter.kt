package com.dynararicooliveira.eazysplitempresas.menu.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dynararicooliveira.eazysplitempresas.R
import com.dynararicooliveira.eazysplitempresas.model.OptionMenu
import kotlinx.android.synthetic.main.option_row.view.*

class OptionAdapter(
    private val context: Context,
    private val options: List<OptionMenu>,
    private val listener: (OptionMenu) -> Unit
):
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.option_row, parent, false)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.bindView(options[position], listener)
    }

    class OptionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(option: OptionMenu, listener: (OptionMenu) -> Unit) = with(itemView) {
            tvOption.text = option.name
            setOnClickListener { listener(option) }
        }
    }

}