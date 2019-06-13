package com.example.namegame


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.namegame.data.Profile
import com.example.namegame.service.ProfilesApi
import kotlinx.android.synthetic.main.fragment_game.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class GameFragment : Fragment() {
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://willowtreeapps.com/api/v1.0/").addConverterFactory(
        MoshiConverterFactory.create()).build()
    private val profilesApi: ProfilesApi = retrofit.create(ProfilesApi::class.java)
    private val call = profilesApi.profiles

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        getProfiles()
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    private fun getProfiles() {
        call.enqueue(object : Callback<List<Profile>> {
            override fun onResponse(call: Call<List<Profile>>, response: Response<List<Profile>>) {

                if (!response.isSuccessful) {
                    textViewTest.text = "Code: ${response.code()}"
                } else {
                    val profiles: List<Profile> = response.body()!!

                    for (profile in profiles) {
                        var content = ""
                        content += "First Name: " + profile.firstName + "\n"
                        content += "Last Name: " + profile.lastName + "\n\n"

                        textViewTest.append(content)
                    }
                }
            }

            override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
                textViewTest.text = t.message
            }
        })
    }
}
