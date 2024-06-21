package com.example.magicbook_projekakhirpember.utils

import android.content.Context
import com.example.magicbook_projekakhirpember.room.AppDatabase
import com.example.magicbook_projekakhirpember.room.PostRepository

object DependencyInjection {
    fun provideRepository(context: Context): PostRepository {
        val database = AppDatabase.getDatabase(context)
        val appExecutors = AppExecutors()
        val dao = database.postDao()
        return PostRepository.getInstance(dao, appExecutors)
    }
}