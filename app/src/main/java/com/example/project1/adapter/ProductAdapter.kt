package com.example.project1.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.ProductEntity
import com.example.project1.R

class ProductAdapter(
    var products: List<ProductEntity>, // Теперь это список ProductEntity
    private val onItemClick: (ProductEntity) -> Unit // Обработчик клика
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newProducts: List<ProductEntity>) {
        products = newProducts
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.product_image)
        val name: TextView = itemView.findViewById(R.id.product_name)
        val price: TextView = itemView.findViewById(R.id.product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.image.setImageResource(product.imageResId)
        holder.name.text = product.name
        holder.price.text = product.price

        // Устанавливаем слушатель нажатия
        holder.itemView.setOnClickListener {
            onItemClick(product) // Передаем весь объект ProductEntity в обработчик клика
        }
    }

    override fun getItemCount() = products.size
}
