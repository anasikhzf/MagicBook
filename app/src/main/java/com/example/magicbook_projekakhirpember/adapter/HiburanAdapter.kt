package com.example.magicbook_projekakhirpember.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.magicbook_projekakhirpember.R
import com.example.magicbook_projekakhirpember.data.HiburanData
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

// Kelas adapter untuk RecyclerView dengan tampilan LinearLayoutManager
class HiburanAdapter(private val hiburanList: List<HiburanData>) : RecyclerView.Adapter<HiburanAdapter.HiburanViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: HiburanData)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class HiburanViewHolder
        (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hiburanTitle: MaterialTextView = itemView.findViewById(R.id.titleTxt)
        val hiburanPenulis: MaterialTextView = itemView.findViewById(R.id.penulisTxt)
//        val hiburanDescription: MaterialTextView = itemView.findViewById(R.id.deskripsiTxt)
        val hiburanImage: ShapeableImageView = itemView.findViewById(R.id.imageViewbuku)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HiburanViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_hiburan, parent, false)
        return HiburanViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: HiburanViewHolder, position: Int) {
        val data = hiburanList[position]

        holder.hiburanTitle.text = data.title
        holder.hiburanPenulis.text = data.penulis
//        holder.hiburanDescription.text = data.description
        holder.hiburanImage.setImageResource(data.image)

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(hiburanList[holder.adapterPosition]) }
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = hiburanList.size

    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}