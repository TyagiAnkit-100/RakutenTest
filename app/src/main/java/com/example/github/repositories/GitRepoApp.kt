package com.example.github.repositories

import android.app.Application

class GitRepoApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: GitRepoApp? = null

        fun applicationContext(): GitRepoApp {
            return instance as GitRepoApp
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}