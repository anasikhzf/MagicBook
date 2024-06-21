//package com.example.magicbook_projekakhirpember
//
//import android.annotation.SuppressLint
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import androidx.recyclerview.widget.StaggeredGridLayoutManager
//import com.example.magicbook_projekakhirpember.Adapter.HiburanAdapter
//import com.example.magicbook_projekakhirpember.Adapter.HiburanAdapterGrid
//import com.example.magicbook_projekakhirpember.Adapter.HiburanAdapterStaggered
//import com.example.magicbook_projekakhirpember.data.HiburanData
//import com.example.magicbook_projekakhirpember.data.HiburanDataList
//import com.google.android.material.textview.MaterialTextView
//
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var hiburanAdapter: HiburanAdapter
//    private lateinit var hiburanAdapterGrid: HiburanAdapterGrid
//    private lateinit var hiburanAdapterStaggered: HiburanAdapterStaggered
//    private lateinit var hiburanAdapterHorizontal: HiburanAdapter
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var recyclerviewHorizontal: RecyclerView
//    private var changeRV = 0
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//
//        // Menetapkan aksi ketika tombol diklik, maka akan mengubah tampilan dari RecyclerView
//        val btnChangeRecyclerView = findViewById<Button>(R.id.btnChangeRV)
//        btnChangeRecyclerView.setOnClickListener {
//            changeRV++
//            if (changeRV > 2) {
//                changeRV = 0
//            }
//            changeRecyclerView() // Panggil fungsi changeRecyclerView()
//        }
//
//
//        // Menghubungkan variabel dengan komponen di layout
//        recyclerView = findViewById(R.id.rv_hiburan_vertikal)
//        recyclerviewHorizontal = findViewById(R.id.viewBacaBuku)
//
//        // Menginisialisasi semua adapter dengan data
//        hiburanAdapter = HiburanAdapter(HiburanDataList.hiburanAdapterStaggeredValue)
//        hiburanAdapterGrid = HiburanAdapterGrid(HiburanDataList.hiburanAdapterStaggeredValue)
//        hiburanAdapterStaggered = HiburanAdapterStaggered(HiburanDataList.hiburanAdapterStaggeredValue)
//        hiburanAdapterHorizontal = HiburanAdapter(HiburanDataList.hiburanDataValue)
//
//        // Menampilkan RecyclerView
//        showRecyclerList()
//    }
//
//    private fun changeRecyclerView() {
//        when (changeRV) {
//            0 -> recyclerView.layoutManager = LinearLayoutManager(this)
//            1 -> recyclerView.layoutManager = GridLayoutManager(this, 2)
//            2 -> recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        }
//        recyclerView.adapter?.notifyDataSetChanged() // Jika adapter mungkin perlu diperbarui
//    }
//
//    // Fungsi untuk menampilkan RecyclerView Default
//    private fun showRecyclerList() {
//        // Mengatur layoutManager dan adapter untuk RecyclerView
//        recyclerView.layoutManager = GridLayoutManager(this,2)
//        recyclerView.adapter = hiburanAdapterGrid
//
//        // Mengatur layoutManager dan adapter untuk RecyclerView horizontal
//        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerviewHorizontal.adapter = hiburanAdapterHorizontal
//
//        // Menetapkan aksi ketika item di RecyclerView diklik
//        hiburanAdapter.setOnItemClickCallback(object : HiburanAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: HiburanData) {
//                showSelectedHiburan(data)
//            }
//        })
//
//        hiburanAdapterGrid.setOnItemClickCallback(object : HiburanAdapterGrid.OnItemClickCallback {
//            override fun onItemClicked(data: HiburanData) {
//                showSelectedHiburan(data)
//            }
//        })
//
//        hiburanAdapterStaggered.setOnItemClickCallback(object : HiburanAdapterStaggered.OnItemClickCallback {
//            override fun onItemClicked(data: HiburanData) {
//                showSelectedHiburan(data)
//            }
//        })
//
//        hiburanAdapterHorizontal.setOnItemClickCallback(object : HiburanAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: HiburanData) {
//                showSelectedHiburan(data)
//            }
//        })
//    }
//
//    private fun showSelectedHiburan(data: HiburanData) {
//        // Membuat intent untuk berpindah ke DetailPlayerActivity
//        val navigateToDetail = Intent(this, DetailHiburanActivity::class.java)
//
//        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailPlayerActivity
//        navigateToDetail.putExtra("title", data.title)
//        navigateToDetail.putExtra("penulis", data.penulis)
//        navigateToDetail.putExtra("description", data.description)
//        navigateToDetail.putExtra("image", data.image)
//
//        // Memulai activity baru
//        startActivity(navigateToDetail)
//    }
//
//
//}

package com.example.magicbook_projekakhirpember

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.magicbook_projekakhirpember.adapter.HiburanAdapter
import com.example.magicbook_projekakhirpember.adapter.HiburanAdapterGrid
import com.example.magicbook_projekakhirpember.adapter.HiburanAdapterStaggered
import com.example.magicbook_projekakhirpember.data.HiburanData
import com.example.magicbook_projekakhirpember.data.HiburanDataList

class MainActivity : AppCompatActivity() {
    private lateinit var hiburanAdapter: HiburanAdapter
    private lateinit var hiburanAdapterGrid: HiburanAdapterGrid
    private lateinit var hiburanAdapterStaggered: HiburanAdapterStaggered
    private lateinit var hiburanAdapterHorizontal: HiburanAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerviewHorizontal: RecyclerView
    private var changeRV = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menetapkan aksi ketika tombol diklik, maka akan mengubah tampilan dari RecyclerView
        val btnChangeRecyclerView = findViewById<Button>(R.id.btnChangeRV)
        btnChangeRecyclerView.setOnClickListener {
            changeRV++
            if (changeRV > 2) {
                changeRV = 0
            }
            changeRecyclerView() // Panggil fungsi changeRecyclerView()
        }

        // Menghubungkan variabel dengan komponen di layout
        recyclerView = findViewById(R.id.rv_hiburan_vertikal)
        recyclerviewHorizontal = findViewById(R.id.viewBacaBuku)

        // Menginisialisasi semua adapter dengan data
        hiburanAdapter = HiburanAdapter(HiburanDataList.hiburanDataValue)
        hiburanAdapterGrid = HiburanAdapterGrid(HiburanDataList.hiburanDataValue)
        hiburanAdapterStaggered = HiburanAdapterStaggered(HiburanDataList.hiburanDataValue)
        hiburanAdapterHorizontal = HiburanAdapter(HiburanDataList.hiburanDataValue)

        // Menampilkan RecyclerView
        showRecyclerList()
    }

    private fun changeRecyclerView() {
        when (changeRV) {
            0 -> recyclerView.layoutManager = LinearLayoutManager(this)
            1 -> recyclerView.layoutManager = GridLayoutManager(this, 2)
            2 -> recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        recyclerView.adapter?.notifyDataSetChanged() // Jika adapter mungkin perlu diperbarui
    }

    // Fungsi untuk menampilkan RecyclerView Default
    private fun showRecyclerList() {
        // Mengatur layoutManager dan adapter untuk RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = hiburanAdapterGrid

        // Mengatur layoutManager dan adapter untuk RecyclerView horizontal
        recyclerviewHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerviewHorizontal.adapter = hiburanAdapterHorizontal

        // Menetapkan aksi ketika item di RecyclerView diklik
        hiburanAdapter.setOnItemClickCallback(object : HiburanAdapter.OnItemClickCallback {
            override fun onItemClicked(data: HiburanData) {
                showSelectedHiburan(data)
            }
        })

        hiburanAdapterGrid.setOnItemClickCallback(object : HiburanAdapterGrid.OnItemClickCallback {
            override fun onItemClicked(data: HiburanData) {
                showSelectedHiburan(data)
            }
        })

        hiburanAdapterStaggered.setOnItemClickCallback(object : HiburanAdapterStaggered.OnItemClickCallback {
            override fun onItemClicked(data: HiburanData) {
                showSelectedHiburan(data)
            }
        })

        hiburanAdapterHorizontal.setOnItemClickCallback(object : HiburanAdapter.OnItemClickCallback {
            override fun onItemClicked(data: HiburanData) {
                showSelectedHiburan(data)
            }
        })
    }

    private fun showSelectedHiburan(data: HiburanData) {
        // Membuat intent untuk berpindah ke DetailHiburanActivity
        val navigateToDetail = Intent(this, DetailHiburanActivity::class.java)

        // Menambahkan dan membawa data pemain ke intent dengan tujuan ke DetailHiburanActivity
        navigateToDetail.putExtra("title", data.title)
        navigateToDetail.putExtra("penulis", data.penulis)
        navigateToDetail.putExtra("description", data.description)
        navigateToDetail.putExtra("image", data.image)

        // Memulai activity baru
        startActivity(navigateToDetail)
    }
    fun home(view: View) {
        val intent = Intent(this@MainActivity, MainActivity::class.java)
        startActivity(intent)
    }
    fun bookmark(view: View) {
        val intent = Intent(this@MainActivity, Bookmark::class.java)
        startActivity(intent)
    }
    fun profile(view: View) {
        val intent = Intent(this@MainActivity, Profil::class.java)
        startActivity(intent)
    }
    fun add(view: View) {
        val intent = Intent(this@MainActivity, AddPostActivity::class.java)
        startActivity(intent)
    }
}
