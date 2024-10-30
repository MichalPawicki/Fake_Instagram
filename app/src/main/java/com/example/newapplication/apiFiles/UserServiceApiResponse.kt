package com.example.newapplication.apiFiles

import com.google.gson.annotations.SerializedName


data class UserServiceApiResponse(

    @SerializedName("results") var results: ArrayList<Results> = arrayListOf(),
    @SerializedName("info") var info: Info? = Info(),
    //@SerializedName("dob") var dob: Dob? = Dob()

)
