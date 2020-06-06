package com.yash.tongaonkar.homemaderecipe.home

import com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty.ACTIVE
import com.google.android.gms.tasks.Tasks
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.yash.tongaonkar.homemaderecipe.models.Recipe
import java.util.concurrent.TimeUnit
import javax.inject.Inject

//
// Created by Yash Tongaonkar on 06/06/20.
//

interface RecipeDataSource {
    fun getRecipes(): List<Recipe>
}

class FireBaseRecipeDataSource @Inject constructor(
    val firestore: FirebaseFirestore
) : RecipeDataSource {

    override fun getRecipes(): List<Recipe> {
        val task = firestore
            .collection("recipes")
            .get()
        val snapshot = Tasks.await(task, 3, TimeUnit.SECONDS)
        return snapshot.documents.map { parseFeedItem(it) }
    }

    private fun parseFeedItem(snapshot: DocumentSnapshot): Recipe {

        return Recipe(
            image = snapshot["image"] as? String?,
            title = snapshot["title"] as? String?
        )
    }
}