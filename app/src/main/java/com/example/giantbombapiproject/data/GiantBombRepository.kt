package com.example.giantbombapiproject.data

import com.example.giantbombapiproject.api.GiantBombApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GiantBombRepository @Inject constructor(private val giantBombApi: GiantBombApi) {
}