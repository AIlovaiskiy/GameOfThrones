package ru.skillbranch.gameofthrones.data.storage

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import ru.skillbranch.gameofthrones.data.remote.res.CharacterRes
import ru.skillbranch.gameofthrones.data.remote.res.HouseRes

interface RemoteStorage {
    @GET("houses?pageSize=50")
    suspend fun getAllHouses(@Query("page") page: Int): Response<List<HouseRes>>

    @GET("houses")
    suspend fun getNeedHouse(@Query("name") houseName: String): Response<List<HouseRes>>

    @GET
    suspend fun getCharacter(@Url url: String): Response<CharacterRes>
}